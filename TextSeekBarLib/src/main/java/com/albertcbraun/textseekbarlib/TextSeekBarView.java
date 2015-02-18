package com.albertcbraun.textseekbarlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Space;
import android.widget.TextView;


/**
 * A seek bar plus text view (on the left side). The text view
 * is update to indicate the current value of the seek bar.
 * 
 * Created by albertb on 2/12/2015.
 */
public final class TextSeekBarView extends LinearLayout {

    private static final String TAG = TextSeekBarView.class.getSimpleName();

    private TextView textView;
    private SeekBar seekBar;

    public TextSeekBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = null;

        try {
            LinearLayout textSeekBarView = (LinearLayout)LayoutInflater.from(context)
                    .inflate(R.layout.text_seek_bar, this);

            typedArray = context.getTheme().obtainStyledAttributes(
                    attrs, R.styleable.TextSeekBarView, 0, 0);
            
            // set the view object refs
            textView = (TextView)textSeekBarView.getChildAt(0);
            Space space = (Space) textSeekBarView.getChildAt(1);
            LinearLayout.LayoutParams spaceLayoutParams = generateLayoutParams(attrs);
            spaceLayoutParams.width = typedArray.getInt(R.styleable.TextSeekBarView_spacing, 0);
            space.setLayoutParams(spaceLayoutParams);
            seekBar = (SeekBar)textSeekBarView.getChildAt(2);
            seekBar.setOnSeekBarChangeListener(new SeekBarChangeListener(textView));
            
        } finally {
            if (typedArray != null) {
                typedArray.recycle();
            }
        }
    }

    @Override
    protected void dispatchSaveInstanceState(@NonNull SparseArray<Parcelable> container) {
        // dispatchFreezeSelfOnly tells Android not to save state of our children. We'll do that.
        super.dispatchFreezeSelfOnly(container);
    }
    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        return new SavedState(superState, seekBar.getProgress());
    }
    
    @Override
    protected void dispatchRestoreInstanceState(@NonNull SparseArray<Parcelable> container) {
        // dispatchThawSelfOnly tells Android not to restore state of our children. We'll do that.
        super.dispatchThawSelfOnly(container);
    }
    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        SavedState savedState = (SavedState)state;
        super.onRestoreInstanceState(savedState.getSuperState());
        int currentValue = savedState.getCurrentValue();
        textView.setText(String.valueOf(currentValue));
        seekBar.setProgress(currentValue);
    }


    /**
     * Voodoo magic class to save and restore state.
     */
    protected static class SavedState extends BaseSavedState {

        private int currentValue;
        
        private SavedState(Parcelable superState, int currentValue) {
            super(superState);
            this.currentValue = currentValue;
        }

        private SavedState(Parcel in) {
            super(in);
        }
        
        private int getCurrentValue() {
            return this.currentValue;
        }

        @Override
        public void writeToParcel(@NonNull Parcel destination, int flags) {
            super.writeToParcel(destination, flags);
            destination.writeInt(currentValue);
        }

        public static final Parcelable.Creator<SavedState> CREATOR = new Creator<SavedState>() {

            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };

    }


    /**
     * Custom class to transmit value of progress bar to text view when value is changed.
     */
    private class SeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

        private TextView companionTextView = null;

        public SeekBarChangeListener(TextView companionTextView) {
            this.companionTextView = companionTextView;
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            companionTextView.setText(String.format("%d", progress));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }

    }


}
