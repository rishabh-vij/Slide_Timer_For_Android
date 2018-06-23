package com.rish.slidetimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView textView;
    Button button;
    Boolean timerisactive=false;
    CountDownTimer countDownTimer;

    public void start(View view) {

        if (timerisactive) {
            reset();
        } else {
            timerisactive=true;

            button.setText("STOP!");
            seekBar.setEnabled(false);
            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long l) {
                    update((int) l / 1000);


                }

                @Override
                public void onFinish() {
                    update(0);
                    MediaPlayer media = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    media.start();
                    reset();

                }
            }.start();


        }
    }
    public void update(int time){
        int min=time/60;
        int sec=time-(min*60);
        String seconds=Integer.toString(sec);
        if (sec<10) {
            seconds = "0"+seconds;
        }
        textView.setText(Integer.toString(min)+":"+seconds);

    }

    public void reset(){
        button.setText("START!");
        textView.setText("00:30");
        seekBar.setProgress(30);
        seekBar.setEnabled(true);
        countDownTimer.cancel();
        timerisactive=false;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar=findViewById(R.id.seekBar);
        textView=findViewById(R.id.textView2);
        button=findViewById(R.id.button);
        seekBar.setMax(600);
        seekBar.setProgress(30);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                update(i);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
