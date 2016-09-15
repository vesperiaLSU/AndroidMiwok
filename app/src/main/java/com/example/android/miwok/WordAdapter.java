package com.example.android.miwok;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class WordAdapter extends ArrayAdapter<Word> {

    private int backgroundColor;

    private static final String LOG_TAG = WordAdapter.class.getSimpleName();

    public WordAdapter(Activity context, List<Word> words, int category_numbers) {
        super(context, 0, words);
        backgroundColor = category_numbers;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Word currentWord = getItem(position);

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView engTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        engTextView.setText(currentWord.getEnglishID());

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(currentWord.getMiwokID());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image_view);
        if (currentWord.hasImage()) {
            imageView.setImageResource(currentWord.getImageID());

            // Need to set the visibility to VISIBLE again because views get recycled and reused
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }

        View textContainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), backgroundColor);
        textContainer.setBackgroundColor(color);

        return listItemView;
    }
}
