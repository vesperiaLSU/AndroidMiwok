package com.example.android.miwok;

public class Word {
    // English translation for the word
    private String english;

    // Miwok translation for the word
    private String miwok;

    // The image resource ID
    private int imageID;

    // The sound resource ID
    private int soundID;

    private static final int NO_IMAGE_PROVIDED = -1;

    public Word(String eng, String miwok, int soundID) {
        english = eng;
        this.miwok = miwok;
        imageID = NO_IMAGE_PROVIDED;
        this.soundID = soundID;
    }

    public Word(String eng, String miwok, int imageID, int soundID) {
        english = eng;
        this.miwok = miwok;
        this.imageID = imageID;
        this.soundID = soundID;
    }

    public String getEnglish() {
        return english;
    }

    public String getMiwok() {
        return miwok;
    }

    public int getImageID() {
        return imageID;
    }

    public boolean hasImage() {
        return imageID != NO_IMAGE_PROVIDED;
    }

    public int getSoundID() {
        return soundID;
    }
}
