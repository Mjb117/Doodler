package mjb.doodler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onToolButtonClick(View v) {
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
                } else {
                    Button colorButton = (Button) findViewById(R.id.colorButton);
                    colorButton.setVisibility(View.GONE);

                    Button sizeButton = (Button) findViewById(R.id.sizeButton);
                    sizeButton.setVisibility(View.GONE);

                    Button opacityButton = (Button) findViewById(R.id.opacityButton);
                    opacityButton.setVisibility(View.GONE);
                }
            }
        });
    }

    public void onClearButtonClick(View v) {
        DoodleView.clear();
    }
}
