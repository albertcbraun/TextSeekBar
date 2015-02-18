## Description

A compound view which places a TextView to the left of a horizontal SeekBar.
The TextView is automatically updated with the value of the SeekBar as the user adjusts it. 

There's a "spacing" attribute in the TextSeekBar's XML tag which lets you specify the distance between
the TextView and its SeekBar.

![Screenshot](https://github.com/albertcbraun/TextSeekBar/blob/master/screenshot.png "Screen shot")

## How To Use

It's an Android Studio project with gradle build files. 

There's a lib module (TextSeekBarLib) and a demo module (TextSeekBarDemo) 
which depends on the lib module.

You can check out the project from GitHub in Android Studio using the menu command:
 
    VCS | Checkout From Version Control | GitHub

and the url:

    https://github.com/albertcbraun/TextSeekBar.git

The custom "spacing" attribute adjusts the space between the
TextView and the SeekBar:

    <com.albertcbraun.textseekbarlib.TextSeekBarView
        android:id="@+id/text_seek_bar_view0"
        android:layout_width="500dp"
        custom:spacing="100"
        android:layout_height="wrap_content"
    />

See the TextSeekBarDemo app's activity_main.xml for a couple more examples.