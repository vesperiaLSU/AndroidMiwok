package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {

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

    @Override
    public void onStop() {
        super.onStop();

        // when the activity is stopped, release the media player resource
        releaseMedia();
    }

    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.word_list, container, false);

        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final List<Word> words = new ArrayList<>();
        words.add(new Word(R.string.num_1, R.string.num_1d, R.drawable.number_one, R.raw.wishing));
        words.add(new Word(R.string.num_2, R.string.num_2d, R.drawable.number_two, R.raw.wishing));
        words.add(new Word(R.string.num_3, R.string.num_3d, R.drawable.number_three, R.raw.wishing));
        words.add(new Word(R.string.num_4, R.string.num_4d, R.drawable.number_four, R.raw.wishing));
        words.add(new Word(R.string.num_5, R.string.num_5d, R.drawable.number_five, R.raw.wishing));
        words.add(new Word(R.string.num_6, R.string.num_6d, R.drawable.number_six, R.raw.wishing));
        words.add(new Word(R.string.num_7, R.string.num_7d, R.drawable.number_seven, R.raw.wishing));
        words.add(new Word(R.string.num_8, R.string.num_8d, R.drawable.number_eight, R.raw.wishing));
        words.add(new Word(R.string.num_9, R.string.num_9d, R.drawable.number_nine, R.raw.wishing));
        words.add(new Word(R.string.num_10, R.string.num_10d, R.drawable.number_ten, R.raw.wishing));

        WordAdapter wordAdapter = new WordAdapter(getActivity(), words, R.color.category_numbers);
        ListView listView = (ListView) rootView.findViewById(R.id.list);
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
                    mediaPlayer = MediaPlayer.create(getActivity(), currentWord.getSoundID());
                    mediaPlayer.start();

                    // Clear MediaPlayer resources on completion
                    mediaPlayer.setOnCompletionListener(completionListener);
                }
            }
        });
        return rootView;
    }

    private void releaseMedia() {
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();
        }

        // Set the media player back to null. For our code, we've decided that
        // setting the media player to null is an easy way to tell that the media player
        // is not configured to play an audio file at the moment.
        mediaPlayer = null;

        // Regardless of whether or not we were granted audio focus, abandon it. This also
        // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
        audioManager.abandonAudioFocus(audioFocusChangeListener);
    }

}
