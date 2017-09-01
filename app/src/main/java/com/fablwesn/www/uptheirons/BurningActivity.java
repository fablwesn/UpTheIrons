package com.fablwesn.www.uptheirons;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Starts a counter which terminates the app on finish
 */
public class BurningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burning);

        //find View to be animated
        View fireView = findViewById(R.id.burning_act_animation);
        //set background
        fireView.setBackgroundResource(R.drawable.animation_burn);

        //handle the background as an animationDrawable
        AnimationDrawable fireAnim = (AnimationDrawable) fireView.getBackground();
        //start the animation
        fireAnim.start();

        //start countDown till exit
        startShutdownTimer();
    }

    /**
     * Overriding the onPressed Function of the physical back button, so the user can't escape back to safety
     * displays a Toast instead
     * Eddie warned you.
     */
    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),getString(R.string.eddie_case_nero), Toast.LENGTH_SHORT).show();
    }

    /**
     * Timer used, 10seconds long and closes the app on finish
     */
    private void startShutdownTimer(){
       new CountDownTimer(10000, 5000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                finish();
                System.exit(0);
            }
        }.start();
    }
}
