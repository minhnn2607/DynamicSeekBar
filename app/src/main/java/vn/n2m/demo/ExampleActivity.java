package vn.n2m.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;

import vn.nms.dynamic_seekbar.DynamicSeekBarView;


public class ExampleActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    DynamicSeekBarView dynamicSeekBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dynamicSeekBarView = (DynamicSeekBarView)findViewById(R.id.dynamicSeekbar);
        dynamicSeekBarView.setSeekBarChangeListener(this);
        dynamicSeekBarView.setMax(200);
        dynamicSeekBarView.setProgress(150);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        dynamicSeekBarView.setInfoText("Brightness "+i + "%",i);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
