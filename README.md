## Description

A compound view which combines a TextView object with a horizontal SeekBar. The TextView is automatically
updated with the value of the SeekBar as the user adjusts it. There's also a "spacing" 
attribute in the TextSeekBar's XML tag which lets you specify the distance between
  the TextView and its SeekBar.

![Screenshot](https://github.com/albertcbraun/TextSeekBar/blob/master/screenshot.png "Screen shot")

## How To Use

It's an Android Studio project with a gradle build files. There's a lib and a demo project which uses that lib.

Check out the project in Android Studio with the menu command: 
    VCS | Checkout From Version Control | GitHub
and the url 
    https://github.com/albertcbraun/TextSeekBar.git

There's a custom parameter named "spacing" which adjusts the space between the
 TextView and the actual SeekBar: 

    <com.albertcbraun.textseekbarlib.TextSeekBarView
        android:id="@+id/text_seek_bar_view0"
        android:layout_width="500dp"
        custom:spacing="0"
        android:layout_height="wrap_content"
    />

See the TextSeekBarDemo app's main_activity.xml for a couple more examples.