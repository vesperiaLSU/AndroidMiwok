package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    private MediaPlayer.OnCompletionListener completionListener =
            new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    releaseMedia();
                }
            };

    private AudioManager.OnAudioFocusChangeListener audioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        // pause the playback
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(mediaPlayer.getCurrentPosition());
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // resume playback
                        mediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        // stop and release media player
                        releaseMedia();
                    }
                }
            };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);

        final List<Word> words = new ArrayList<>();
        words.add(new Word("one", "1", R.drawable.number_one, R.raw.Wishing));
        words.add(new Word("two", "2", R.drawable.number_two, R.raw.Wishing));
        words.add(new Word("three", "3", R.drawable.number_three, R.raw.Wishing));
        words.add(new Word("four", "4", R.drawable.number_four, R.raw.Wishing));
        words.add(new Word("five", "5", R.drawable.number_five, R.raw.Wishing));
        words.add(new Word("six", "6", R.drawable.number_six, R.raw.Wishing));
        words.add(new Word("seven", "7", R.drawable.number_seven, R.raw.Wishing));
        words.add(new Word("eight", "8", R.drawable.number_eight, R.raw.Wishing));
        words.add(new Word("nine", "9", R.drawable.number_nine, R.raw.Wishing));
        words.add(new Word("ten", "10", R.drawable.number_ten, R.raw.Wishing));

        WordAdapter wordAdapter = new WordAdapter(this, words, R.color.category_numbers);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(wordAdapter);
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Get the Word object at the given position the user clicked on
                Word currentWord = words.get(position);

                int requestResult = audioManager.requestAudioFocus(
                        //global audio focus change listener
                        audioFocusChangeListener,
                        // stream type is playing music
                        AudioManager.STREAM_MUSIC,
                        // request permanent focus, us AUDIOFOCUS_GAIN_TRANSIENT only request
                        AudioManager.AUDIOFOCUS_GAIN);

                if (requestResult == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // we have audio focus now

                    // Release the media player if it currently exists as we are about to play a different song
                    releaseMedia();

                    //Create and setup the MediaPlayer for the audio resource associated with the current word
                    mediaPlayer = MediaPlayer.create(NumbersActivity.this, currentWord.getSoundID());
                    mediaPlayer.start();

                    // Clear MediaPlayer resources on completion
                    mediaPlayer.setOnCompletionListener(completionListener);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        // When the activity is stopped, the media player resource should be recycled
        releaseMedia();
    }

    private void releaseMedia() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        mediaPlayer = null;

        // abandon audio focus
        audioManager.abandonAudioFocus(audioFocusChangeListener);
    }
}

