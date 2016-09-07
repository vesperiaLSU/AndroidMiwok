package com.example.android.miwok;

public class Word {
    // English translation for the word
    private String english;

    // Miwok translation for the word
    private String miwok;

    // The image resource ID
    private int src;

    private static final int NO_IMAGE_PROVIDED = -1;

    public Word(String eng, String miwok) {
        english = eng;
        this.miwok = miwok;
        src = NO_IMAGE_PROVIDED;
    }

    public Word(String eng, String miwok, int src) {
        english = eng;
        this.miwok = miwok;
        this.src = src;
    }

    public String getEnglish() {
        return english;
    }

    public String getMiwok() {
        return miwok;
    }

    public int getImageID() {
        return src;
    }

    public boolean hasImage() {
        return src != NO_IMAGE_PROVIDED;
    }
}
