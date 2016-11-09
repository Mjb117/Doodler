package mjb.doodler;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ToggleButton toggle = (ToggleButton) findViewById(R.id.toggleTools);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Button colorButton = (Button) findViewById(R.id.colorButton);
                    colorButton.setVisibility(View.VISIBLE);

                    Button sizeButton = (Button) findViewById(R.id.sizeButton);
                    sizeButton.setVisibility(View.VISIBLE);

                    Button opacityButton = (Button) findViewById(R.id.opacityButton);
                    opacityButton.setVisibility(View.VISIBLE);

                    Button clearButton = (Button) findViewById(R.id.clearButton);
                    clearButton.setVisibility(View.VISIBLE);

                    Switch splitSwitch = (Switch) findViewById(R.id.splitSwitch);
                    splitSwitch.setVisibility(View.VISIBLE);
                } else {
                    Button colorButton = (Button) findViewById(R.id.colorButton);
                    colorButton.setVisibility(View.GONE);

                    Button sizeButton = (Button) findViewById(R.id.sizeButton);
                    sizeButton.setVisibility(View.GONE);

                    Button opacityButton = (Button) findViewById(R.id.opacityButton);
                    opacityButton.setVisibility(View.GONE);

                    Button clearButton = (Button) findViewById(R.id.clearButton);
                    clearButton.setVisibility(View.GONE);

                    Switch splitSwitch = (Switch) findViewById(R.id.splitSwitch);
                    splitSwitch.setVisibility(View.GONE);
                }
            }
        });

        final DoodleView doodleView = (DoodleView) findViewById(R.id.doodleV);
        Switch splitSwitch = (Switch) findViewById(R.id.splitSwitch);
        splitSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    doodleView.setSplit(true);
                } else {
                    doodleView.setSplit(false);
                }
            }
        });
    }

    public void onClearButtonClick(View v) {
        DoodleView doodleView = (DoodleView) findViewById(R.id.doodleV);
        doodleView.clear();
    }


    private int saveRed;
    private int saveGreen;
    private int saveBlue;
    private int progRed;
    private int progGreen;
    private int progBlue;
    public void onClickSetColor(View v){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final DoodleView doodleView = (DoodleView) findViewById(R.id.doodleV);
        saveRed = doodleView.currentRed;
        saveGreen = doodleView.currentGreen;
        saveBlue = doodleView.currentBlue;
        progRed = saveRed;
        progGreen = saveGreen;
        progBlue = saveBlue;

        // Get the layout inflater. LayoutInflaters take a layout XML file and create its
        // corresponding View objects. Never create LayoutInflaters directly. Always use the
        // factory method getLayoutInflater. See https://developer.android.com/reference/android/view/LayoutInflater.html
        LayoutInflater inflater = this.getLayoutInflater();

        // Inflate the dialog_color.xml layout and create the View
        View dialogView = inflater.inflate(R.layout.dialog_color, null);

        final ViewGroup diaColorView = (ViewGroup)findViewById(R.id.dialogColorPage);

        // Get access to the seekbars on this dialog.
        SeekBar redSeekBar = (SeekBar)dialogView.findViewById(R.id.redSeekbar);
        SeekBar greenSeekBar = (SeekBar)dialogView.findViewById(R.id.greenSeekbar);
        SeekBar blueSeekBar = (SeekBar)dialogView.findViewById(R.id.blueSeekbar);

        redSeekBar.setProgress(saveRed);
        greenSeekBar.setProgress(saveGreen);
        blueSeekBar.setProgress(saveBlue);


        // This is the method that allows us to use our own custom view. We set the AlertDialog builder
        // to the view we created with the inflater above.
        builder.setView(dialogView)
                // Add action buttons
                .setPositiveButton("OK!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    doodleView.setRedValue(saveRed);
                    doodleView.setGreenValue(saveGreen);
                    doodleView.setBlueValue(saveBlue);
                    }
                });


        final AlertDialog alertDialog = builder.create();

        redSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                doodleView.setRedValue(progress);
                progRed = progress;

                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.rgb(progRed, progGreen, progBlue)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //not implemented
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //not implemented
            }
        });

        greenSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                doodleView.setGreenValue(progress);
                progGreen = progress;

                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.rgb(progRed, progGreen, progBlue)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //not implemented
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //not implemented
            }
        });

        blueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                doodleView.setBlueValue(progress);
                progBlue = progress;

                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.rgb(progRed, progGreen, progBlue)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //not implemented
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //not implemented
            }
        });
        alertDialog.show();
    }

    private int saveSize;
    public void onClickSetSize(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final DoodleView doodleView = (DoodleView) findViewById(R.id.doodleV);
        saveSize = doodleView.currentSize;

        // Get the layout inflater. LayoutInflaters take a layout XML file and create its
        // corresponding View objects. Never create LayoutInflaters directly. Always use the
        // factory method getLayoutInflater. See https://developer.android.com/reference/android/view/LayoutInflater.html
        LayoutInflater inflater = this.getLayoutInflater();

        // Inflate the dialog_color.xml layout and create the View
        View dialogView = inflater.inflate(R.layout.dialog_size, null);

        // Get access to the seekbars on this dialog.
        SeekBar sizeSeekBar = (SeekBar)dialogView.findViewById(R.id.sizeSeekbar);

        sizeSeekBar.setProgress(saveSize);

        sizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                doodleView.setSizeValue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //not implemented
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //not implemented
            }
        });

        // This is the method that allows us to use our own custom view. We set the AlertDialog builder
        // to the view we created with the inflater above.
        builder.setView(dialogView)
                // Add action buttons
                .setPositiveButton("OK!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        doodleView.setSizeValue(saveSize);
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private int saveOpacity;
    public void onClickSetOpacity(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final DoodleView doodleView = (DoodleView) findViewById(R.id.doodleV);
        saveOpacity = doodleView.currentOpacity;

        // Get the layout inflater. LayoutInflaters take a layout XML file and create its
        // corresponding View objects. Never create LayoutInflaters directly. Always use the
        // factory method getLayoutInflater. See https://developer.android.com/reference/android/view/LayoutInflater.html
        LayoutInflater inflater = this.getLayoutInflater();

        // Inflate the dialog_color.xml layout and create the View
        View dialogView = inflater.inflate(R.layout.dialog_opacity, null);

        // Get access to the seekbars on this dialog.
        SeekBar opacitySeekBar = (SeekBar)dialogView.findViewById(R.id.opacitySeekbar);

        opacitySeekBar.setProgress(255 - saveOpacity);

        opacitySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                doodleView.setOpacityValue(255 - progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //not implemented
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //not implemented
            }
        });

        // This is the method that allows us to use our own custom view. We set the AlertDialog builder
        // to the view we created with the inflater above.
        builder.setView(dialogView)
                // Add action buttons
                .setPositiveButton("OK!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        doodleView.setOpacityValue(saveOpacity);
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
