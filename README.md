To run this app, download and import into Android Studio and run.  The app will allow drawing on the opening screen with a default color of red.
Clicking show tools will bring up the brush color, size, and opacity buttons,
as well as the clear button and split brush switch (into 4 mirrored brushes).

Clicking on the buttons will bring up an alert dialog to allow you to change the features.
Clicking cancel will revert the changes selected.

The app was tested using a Nexus 4 API 24.
The features are color selection (using rgb sliders), brush size selection (slider), opacity selection (slider),
and brush split into brushes, mirrored horizontally, vertically, and diagonally (one mirroring for each brush).

References:
Android developer documentation, including Views, Color, AlertDialog, and Path
https://developer.android.com/reference/android/

Stack Overflow, including
http://stackoverflow.com/questions/7269706/how-to-change-dialog-background-color-programatically
http://stackoverflow.com/questions/18346920/change-the-background-color-of-a-pop-up-dialog
http://stackoverflow.com/questions/18022364/how-to-convert-rgb-color-to-int-in-java
http://stackoverflow.com/questions/18470538/toggle-button-doesnt-work-at-the-first-time-on-android
http://stackoverflow.com/questions/24523110/how-to-show-hide-layout-on-button-click

Design Inspiration from past course designs:
Eddie Krahe: color selection arrangement with title and sliders.
Thomas McHale: Color previewing
Thomas McHale and http://weavesilk.com/: Splitting/mirroring brush.
