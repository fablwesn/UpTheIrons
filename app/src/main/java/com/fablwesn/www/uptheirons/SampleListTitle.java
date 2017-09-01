package com.fablwesn.www.uptheirons;

/**
 * this class is responsible to get the correct song with its corresponding sub-data
 */

public class SampleListTitle {

    private String year;
    private String album;
    private String title;
    private int imageResId;

    // Default constructor
    public SampleListTitle(String albumYear, String albumName, String exampleTitle, int imageId) {
        year = albumYear;
        album = albumName;
        title = exampleTitle;
        imageResId = imageId;
    }

    /**
     * This method gets album year
     *
     * @return year
     */
    public String getYear() {
        return year;
    }

    /**
     * This method gets album name
     *
     * @return album
     */
    public String getAlbum() {
        return album;
    }

    /**
     * This method gets song name
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method gets image resource id
     * @return image resource id
     */
    public int getImageResId() {
        return imageResId;
    }
}