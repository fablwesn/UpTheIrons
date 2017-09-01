package com.fablwesn.www.uptheirons;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * display text and images for every item in the list and sets an onClickListener on each of them, leading to the PlayMusicActivity. In this case,
 * strings are being stored to be used later on
 * sets a onClickListener for the purchase button, leading to the PurchaseActivity.
 *
 * return with the physical back button
 */

public class SampleListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    //globals used
    String _songYear;
    String _songAlbum;
    String _songTitle;
    String[] _sampleLibrary;
    int _songCover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_list);

        //get the string array
        _sampleLibrary = getResources().getStringArray(R.array.sample_act_titles);

        createSongList(); //List with adapter set
        setPurchaseButtonListener(); //onClickListener
    }

    /**
     *
     */
    private void createSongList() {

        ArrayList<SampleListTitle> titles = new ArrayList<>();

        //set appropriate text and images for every item in the array
        for (int i = 0; i < _sampleLibrary.length; i++) {

            //split the array, using the first part as the album, the second as the year and the third as the song title
            StringTokenizer tokens = new StringTokenizer(_sampleLibrary[i], ":");
            _songAlbum = tokens.nextToken();
            _songYear = tokens.nextToken();
            _songTitle = tokens.nextToken();

            //set cover, depending on the album
            switch (_songAlbum) {
                case "Iron Maiden ":
                    _songCover = R.drawable.img_album_ironmaiden;
                    break;
                case "Killers ":
                    _songCover = R.drawable.img_album_killers;
                    break;
                case "The Number of the Beast ":
                    _songCover = R.drawable.img_album_numberbeast;
                    break;
                case "Pieces of Mind ":
                    _songCover = R.drawable.img_album_piecemind;
                    break;
                case "Powerslave ":
                    _songCover = R.drawable.img_album_powerslave;
                    break;
                case "Somewhere in Time ":
                    _songCover = R.drawable.img_album_somewheretime;
                    break;
                case "Seventh Son of a Seventh Son ":
                    _songCover = R.drawable.img_album_seventhson;
                    break;
                case "No Prayer for the Dying ":
                    _songCover = R.drawable.img_album_noprayer;
                    break;
                case "Fear of the Dark ":
                    _songCover = R.drawable.img_album_feardark;
                    break;
                case "The X Factor ":
                    _songCover = R.drawable.img_album_xfactor;
                    break;
                case "Virtual XI ":
                    _songCover = R.drawable.img_album_virtualxi;
                    break;
                case "Brave New World ":
                    _songCover = R.drawable.img_album_bravenewworld;
                    break;
                case "Dance of Death ":
                    _songCover = R.drawable.img_album_dancedeath;
                    break;
                case "A Matter of Life and Death ":
                    _songCover = R.drawable.img_album_matterlifedeath;
                    break;
                case "The Final Frontier ":
                    _songCover = R.drawable.img_album_finalfrontier;
                    break;
                case "The Book of Souls ":
                    _songCover = R.drawable.img_album_booksouls;
                    break;
                default:
                    break;
            }

            titles.add(new SampleListTitle(_songYear, _songAlbum, _songTitle, _songCover));
        }

        //set the custom adapter and listener
        ListView sampleList = (ListView) findViewById(R.id.sample_act_list);
        SampleListAdapter adapter = new SampleListAdapter(this, titles);
        sampleList.setAdapter(adapter);
        sampleList.setOnItemClickListener(this);
    }

    /**
     *
     * store the chosen song strings for the PlayMusicActivity
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        TextView itemTitleView = view.findViewById(R.id.sample_act_title);
        TextView itemAlbumView = view.findViewById(R.id.sample_act_album);

        String title = itemTitleView.getText().toString();
        String album = itemAlbumView.getText().toString();

        Intent intent = new Intent(this, PlayMusicActivity.class);

        //Create the bundle
        Bundle bundle = new Bundle();

        //Add data to bundle
        bundle.putString("titleChoice", title);
        bundle.putString("albumChoice", album);

        //Add the bundle to the intent
        intent.putExtras(bundle);

        //Fire that second activity
        startActivity(intent);
    }

    /**
     * Sets a Listener to the Purchase Button, starting the PurchaseActivity
     */
    private void setPurchaseButtonListener() {
        //find view needed
        View purchaseBtn = findViewById(R.id.sample_act_purchase_btn);

        // Set a click listener on that View
        purchaseBtn.setOnClickListener(new View.OnClickListener() {
            // this method will be executed when the 8-Bit TextView is clicked on.
            @Override
            public void onClick(View view) {

                // Create a new intent to open the {@link SampleListActivity}
                Intent purchaseAct = new Intent(SampleListActivity.this, PurchaseActivity.class);
                // Start the new activity
                startActivity(purchaseAct);
            }
        });
    }
}
