package com.fablwesn.www.uptheirons;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /**
     * global variables for this activity
     */
    //global variable counting the times user has clicked on the Eddie the Head (MetalHead)ImageView (saved to InstanceStates)
    private int _tapsOnEddie;
    //global toast acting as the speech bubble for Eddie the Head
    private Toast _eddieToast;

    //checks if Eddie is on rampage, if it's true, onCreate quits this app
    private boolean _burnDown;

    //global CountDownTimer started on 17 _tapsOnEddie, canceled when switching activities
    private CountDownTimer _timerTillBurn;

    //key name for savedInstanceStates
    static final String STATE_TAPS_INT = "tapsOnEddie";
    static final String STATE_BURN_BOOL = "burnAppDown";

    /**
     * save _tapsOnEddie value to the InstanceStates, so Eddie the Head's counter doesn't reset on screen rotation
     * save _burnDown value, to see if App should be closed
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_TAPS_INT, _tapsOnEddie);
        outState.putBoolean(STATE_BURN_BOOL, _burnDown);
        super.onSaveInstanceState(outState);
    }

    /**
     * onCreate of this Activity (@Reviewer: can the activity be called class?)
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check whether we're recreating a previously destroyed instance
        if (savedInstanceState != null) {
            // Restore value of members from saved state
            _tapsOnEddie = savedInstanceState.getInt(STATE_TAPS_INT);
            _burnDown = savedInstanceState.getBoolean(STATE_BURN_BOOL);
        }

        //if coming from the BurningActivity, close App
        if (_burnDown) {
            _burnDown = false;
            finish();
            System.exit(0);
        }

        //set onClickListener of this activity
        setAboutBandListener();
        setSampleListListener();
        setMetalHeadListener();

        //style MetalHeads speech bubble Toast
        _eddieToast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG);
        _eddieToast.setGravity(Gravity.CENTER, 0, 0);
    }

    /**
     * attaches an OnClickListener for the About Band TextView, loading its activity on touch, called in onCreate
     */
    private void setAboutBandListener() {
        //find view needed
        View aboutText = findViewById(R.id.main_act_text_about);

        // Set a click listener on that View
        aboutText.setOnClickListener(new View.OnClickListener() {
            // this method will be executed when the 8-Bit TextView is clicked on.
            @Override
            public void onClick(View view) {
                //reset Eddie the Head
                _tapsOnEddie = 0;
                //stop the countdown for Eddie's Pyromania
                if (_timerTillBurn != null) {
                    _timerTillBurn.cancel();
                    _burnDown = false;
                }
                // Create a new intent to open the {@link AboutBandActivity}
                Intent aboutBandAct = new Intent(MainActivity.this, AboutBandActivity.class);
                // Start the new activity
                startActivity(aboutBandAct);
            }
        });
    }

    /**
     * attaches an OnClickListener for the About Band TextView, loading its activity on touch, called in onCreate
     */
    private void setSampleListListener() {
        //find view needed
        View sampleText = findViewById(R.id.main_act_text_sample);

        // Set a click listener on that View
        sampleText.setOnClickListener(new View.OnClickListener() {
            // this method will be executed when the 8-Bit TextView is clicked on.
            @Override
            public void onClick(View view) {
                //reset Eddie the Head
                _tapsOnEddie = 0;
                //stop the countdown for Eddie's Pyromania
                if (_timerTillBurn != null) {
                    _timerTillBurn.cancel();
                    _burnDown = false;
                }

                // Create a new intent to open the {@link SampleListActivity}
                Intent sampleListAct = new Intent(MainActivity.this, SampleListActivity.class);
                // Start the new activity
                startActivity(sampleListAct);
            }
        });
    }

    /**
     * attaches an OnClickListener for the MetalHead ImageView, responding to the number of clicks
     */
    private void setMetalHeadListener() {
        //find view needed
        View metalHead = findViewById(R.id.main_act_image_metal_head);

        // Set a click listener on that View
        metalHead.setOnClickListener(new View.OnClickListener() {
            // this method will be executed when the 8-Bit TextView is clicked on.
            @Override
            public void onClick(View view) {
                //increase the times tapped on Eddie
                _tapsOnEddie++;

                //react depending on how often poor Eddie has been tapped
                switch (_tapsOnEddie) {
                    case 1:
                        _eddieToast.setText(getString(R.string.eddie_case_one));
                    case 2:
                        _eddieToast.show();
                        break;
                    case 3:
                        _eddieToast.setText(getString(R.string.eddie_case_three));
                    case 4:
                        _eddieToast.show();
                        break;
                    case 5:
                        _eddieToast.setText(getString(R.string.eddie_case_five));
                    case 6:
                        _eddieToast.show();
                        break;
                    case 7:
                        _eddieToast.setText(getString(R.string.eddie_case_seven));
                    case 8:
                    case 9:
                        _eddieToast.show();
                        break;
                    case 10:
                        _eddieToast.setText(getString(R.string.eddie_case_ten));
                    case 11:
                    case 12:
                        _eddieToast.show();
                        break;
                    case 14:
                        _eddieToast.setText(getString(R.string.eddie_case_fourteen));
                        break;
                    case 17:
                        _eddieToast.setText(getString(R.string.eddie_case_warning));
                        //start the countdown for Eddie's Pyromania
                        _timerTillBurn = eddieBurningApp();
                        _timerTillBurn.start();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * CountdownTimer starting when _tapsOnEddie reach 17.
     * Will open the Burning Activity onFinish, after 10 seconds.
     * <p>
     * canceled when the user opens the AboutBand - or the SampleListTitle Activity
     *
     * @return
     */
    private CountDownTimer eddieBurningApp() {
        CountDownTimer timerTillBurn = new CountDownTimer(10000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                _eddieToast.show();
            }

            @Override
            public void onFinish() {

                //Eddie's last laughter
                _eddieToast.setText(getString(R.string.eddie_case_nero));
                _eddieToast.show();

                //reset Eddie the Head
                _tapsOnEddie = 0;
                _burnDown = true;

                // Create a new intent to open the {@link BurningActivity}
                Intent finalAct = new Intent(MainActivity.this, BurningActivity.class);
                // Start the new activity
                startActivity(finalAct);
            }
        };
        return timerTillBurn;
    }
}
