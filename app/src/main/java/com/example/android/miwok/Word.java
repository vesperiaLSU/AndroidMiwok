package com.example.android.miwok;

public class Word {
    // English translation for the word
    private int englishID;

    // Miwok translation for the word
    private int miwokID;

    // The image resource ID
    private int imageID;

    // The sound resource ID
    private int soundID;

    private static final int NO_IMAGE_PROVIDED = -1;

    public Word(int eng, int miwok, int sound) {
        englishID = eng;
        miwokID = miwok;
        imageID = NO_IMAGE_PROVIDED;
        soundID = sound;
    }

    public Word(int eng, int miwok, int image, int sound) {
        englishID = eng;
        miwokID = miwok;
        imageID = image;
        soundID = sound;
    }

    public int getEnglishID() {
        return englishID;
    }

    public int getMiwokID() {
        return miwokID;
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
