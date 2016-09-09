package com.example.android.miwok;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

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
                Word currentWord = words.get(position);
                mediaPlayer = MediaPlayer.create(NumbersActivity.this, currentWord.getSoundID());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {

                    }
                });
            }
        });
    }
}

