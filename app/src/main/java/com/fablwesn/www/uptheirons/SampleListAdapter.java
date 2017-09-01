package com.fablwesn.www.uptheirons;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Custom adapter, used fellow students project as a reference to learn about this class
 *
 *  >> aditib: https://discussions.udacity.com/t/simply-music-my-music-app-approved-version/246264 <<
 */

public class SampleListAdapter extends ArrayAdapter<SampleListTitle> {

    public SampleListAdapter(Activity context, ArrayList<SampleListTitle> sampleListTitles) {
        super(context, 0, sampleListTitles);
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup viewParent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_song_titles, viewParent, false);
        }

        //get the selected item
        SampleListTitle currentElement = getItem(pos);

        //fill information
        TextView albumYear = listItemView.findViewById(R.id.sample_act_year);
        albumYear.setText(currentElement.getYear());

        TextView albumName = listItemView.findViewById(R.id.sample_act_album);
        albumName.setText(currentElement.getAlbum());

        TextView exampleTitle = listItemView.findViewById(R.id.sample_act_title);
        exampleTitle.setText(currentElement.getTitle());

        ImageView albumCover = listItemView.findViewById(R.id.sample_act_cover);
        albumCover.setImageResource(currentElement.getImageResId());

        return listItemView;
    }
}