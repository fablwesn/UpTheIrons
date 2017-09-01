package com.fablwesn.www.uptheirons;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class PlayMusicActivity extends AppCompatActivity {

    //globals
    String _chosenTitle;
    String _chosenAlbum;

    SeekBar _musicSeekBar;
    ImageView _btnPlayPause;
    View _btnStop;

    CountDownTimer _songDurationTimer; //simulated song length for the SeekBar
    boolean _isPlaying; //if the song is currently being played
    boolean _seekBarHeld; //if the user is dragging the SeekBar

    int _secondsTillToast = 2; //interval for Toasts being displayed instead of music being played

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        //Get the bundle
        Bundle bundle = getIntent().getExtras();
        //Extract the data
        _chosenTitle = bundle.getString("titleChoice");
        _chosenAlbum = bundle.getString("albumChoice");

        //override the activity label to the currently playing song
        this.setTitle(getResources().getText(R.string.play_act_label) + " " + _chosenTitle);

        //find views
        _musicSeekBar = (SeekBar) findViewById(R.id.play_act_seek_bar);
        _btnPlayPause = (ImageView) findViewById(R.id.play_act_play_pause_img);
        _btnStop = findViewById(R.id.play_act_stop_img);

        //create and store the SongTimer
        _songDurationTimer = createTimer();

        //display correct lyrics and song details
        updateText();
        //onClickListener for the Play/Pause Button
        playbackListenerPlayPause();
        //onClickListener for the Stop Button
        playbackListenerStop();
        //Listener for the SeekBar
        playbackSeekBarListener();

        //display the Pause Button (music starts on load)
        _btnPlayPause.setImageResource(R.drawable.img_playback_pause);

        //start the song
        _songDurationTimer.start();
    }

    /**
     * adds another function to the physical back button, which just "turns off" the music additionally
     */
    @Override
    public void onBackPressed() {
        _songDurationTimer.cancel();
        finish();
    }

    /**
     * creates a CountDownTimer which simulates a song being played.
     *
     * @return
     */
    private CountDownTimer createTimer() {
        CountDownTimer timer = new CountDownTimer(_musicSeekBar.getMax() * 1000, 1000) {
            @Override
            public void onTick(long l) {
                //when the SeekBar reaches it's end, stop
                if (_musicSeekBar.getProgress() == _musicSeekBar.getMax()) {
                    _btnPlayPause.setImageResource(R.drawable.img_playback_play);
                    _isPlaying = false;
                    cancel();
                }
                // if not, "play" the music - displaying a random toast every few seconds
                else {
                    _isPlaying = true;
                    _secondsTillToast++;
                    _musicSeekBar.setProgress(_musicSeekBar.getProgress() + 1);
                    if (!_seekBarHeld && _secondsTillToast >= 3) {
                        playbackMusicToast();
                        _secondsTillToast = 0;
                    }
                }
            }

            @Override
            //start again if finished. only stops when the bar reaches it's end or when the user want's to
            public void onFinish() {
                start();
            }
        };
        return timer;
    }

    /**
     * displays the correct title and lyrics for the chosen song
     */
    private void updateText() {
        TextView titleText = (TextView) findViewById(R.id.play_act_lyrics_title);
        TextView lyricsText = (TextView) findViewById(R.id.play_act_lyrics);

        titleText.setText(getResources().getString(R.string.play_act_lyrics_title, _chosenTitle));

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            switch (_chosenAlbum) {
                case "Iron Maiden ":
                    lyricsText.setText(getString(R.string.play_act_lyrics_00));
                    break;
                case "Killers ":
                    lyricsText.setText(getString(R.string.play_act_lyrics_01));
                    break;
                case "The Number of the Beast ":
                    lyricsText.setText(getString(R.string.play_act_lyrics_02));
                    break;
                case "Pieces of Mind ":
                    lyricsText.setText(getString(R.string.play_act_lyrics_03));
                    break;
                case "Powerslave ":
                    lyricsText.setText(getString(R.string.play_act_lyrics_04));
                    break;
                case "Somewhere in Time ":
                    lyricsText.setText(getString(R.string.play_act_lyrics_05));
                    break;
                case "Seventh Son of a Seventh Son ":
                    lyricsText.setText(getString(R.string.play_act_lyrics_06));
                    break;
                case "No Prayer for the Dying ":
                    lyricsText.setText(getString(R.string.play_act_lyrics_07));
                    break;
                case "Fear of the Dark ":
                    lyricsText.setText(getString(R.string.play_act_lyrics_08));
                    break;
                case "The X Factor ":
                    lyricsText.setText(getString(R.string.play_act_lyrics_09));
                    break;
                case "Virtual XI ":
                    lyricsText.setText(getString(R.string.play_act_lyrics_10));
                    break;
                case "Brave New World ":
                    lyricsText.setText(getString(R.string.play_act_lyrics_11));
                    break;
                case "Dance of Death ":
                    lyricsText.setText(getString(R.string.play_act_lyrics_12));
                    break;
                case "A Matter of Life and Death ":
                    lyricsText.setText(getString(R.string.play_act_lyrics_13));
                    break;
                case "The Final Frontier ":
                    lyricsText.setText(getString(R.string.play_act_lyrics_14));
                    break;
                case "The Book of Souls ":
                    lyricsText.setText(getString(R.string.play_act_lyrics_15));
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Listener for the Play/Pause Button
     * <p>
     * 2 reactions depending on the isPlaying bool
     * on true:
     * sets bool to false and its img to play while canceling the timer
     * on false:
     * sets its img to pause and starts the counter again, if the bar is at maxProgress, restart the song
     */
    private void playbackListenerPlayPause() {
        _btnPlayPause.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (_isPlaying) {
                    _isPlaying = false;
                    _btnPlayPause.setImageResource(R.drawable.img_playback_play);
                    _songDurationTimer.cancel();
                } else {
                    _btnPlayPause.setImageResource(R.drawable.img_playback_pause);
                    if (_musicSeekBar.getProgress() == _musicSeekBar.getMax())
                        _musicSeekBar.setProgress(0);
                    _songDurationTimer.start();
                }
            }
        });
    }

    /**
     * Listener for the Stop Button
     */
    private void playbackListenerStop() {
        _btnStop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //cancel timer
                _songDurationTimer.cancel();
                _isPlaying = false;
                //change play/pause btn image
                _btnPlayPause.setImageResource(R.drawable.img_playback_play);
                //set progress to zero
                _musicSeekBar.setProgress(0);
            }
        });
    }


    /**
     * 333333333Listener for the SeekBar
     */
    private void playbackSeekBarListener() {
        // perform seek bar change listener event used for getting the progress value
        _musicSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            //grays out the navigation buttons on the bottom and disables them while holding, also stops toasts from being shown
            public void onStartTrackingTouch(SeekBar seekBar) {
                _seekBarHeld = true;
                _btnPlayPause.setAlpha(0.3f);
                _btnStop.setAlpha(0.3f);
                _btnPlayPause.setClickable(false);
                _btnStop.setClickable(false);
            }

            //display and activate the buttons again
            public void onStopTrackingTouch(SeekBar seekBar) {
                _seekBarHeld = false;
                _btnPlayPause.setAlpha(1f);
                _btnStop.setAlpha(1f);
                _btnPlayPause.setClickable(true);
                _btnStop.setClickable(true);
            }
        });
    }


    /**
     * displays a Toast to be shown
     * Content and Location are being randomized
     */
    private void playbackMusicToast() {

        //create an empty toast
        Toast musicToast = Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT);

        //create the generator
        Random randomGen = new Random();

        //get the string array containing all possible content
        final String[] musicTexts = getResources().getStringArray(R.array.play_act_music_toast);

        //get a random number, size of the array and apply it as the toasts text
        final int randomTextIndex = randomGen.nextInt(musicTexts.length);
        musicToast.setText(musicTexts[randomTextIndex]);

        //get a random number from 0 to 8 deciding where to display the Toast
        final int randomPositionIndex = randomGen.nextInt(9);
        switch (randomPositionIndex) {
            case 0:
                musicToast.setGravity(Gravity.CENTER, 0, 0);
                break;
            case 1:
                musicToast.setGravity(Gravity.LEFT | Gravity.TOP, 0, 0);
                break;
            case 2:
                musicToast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP, 0, 0);
                break;
            case 3:
                musicToast.setGravity(Gravity.RIGHT | Gravity.TOP, 0, 0);
                break;
            case 4:
                musicToast.setGravity(Gravity.LEFT | Gravity.BOTTOM, 0, 0);
                break;
            case 5:
                musicToast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 0);
                break;
            case 6:
                musicToast.setGravity(Gravity.RIGHT | Gravity.BOTTOM, 0, 0);
                break;
            case 7:
                musicToast.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT, 0, 0);
                break;
            case 8:
                musicToast.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT, 0, 0);
                break;
            default:
                break;
        }
        //display the Toast
        musicToast.show();
    }
}
