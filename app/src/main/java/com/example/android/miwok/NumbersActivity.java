package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        List<String> words = new ArrayList<>();
        words.add("one");
        words.add("two");
        words.add("three");
        words.add("four");
        words.add("five");

//        LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);
//        for (String s : words) {
//            TextView wordView = new TextView(this);
//            wordView.setText(s);
//            rootView.addView(wordView);
//        }

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(this, R.layout.list_item, words);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemsAdapter);
    }
}

