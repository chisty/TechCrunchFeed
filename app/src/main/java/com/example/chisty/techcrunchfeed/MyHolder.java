package com.example.chisty.techcrunchfeed;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by chisty on 1/24/2016.
 */
public class MyHolder {
    TextView articleTitleText;
    TextView articlePublishedDateText;
    ImageView articleImage;
    TextView articleDescriptionText;

    public MyHolder(View view) {
        articleTitleText= (TextView) view.findViewById(R.id.articleTitleText);
        articlePublishedDateText= (TextView) view.findViewById(R.id.articlePublishedDateText);
        articleImage= (ImageView) view.findViewById(R.id.articleImage);
        articleDescriptionText= (TextView) view.findViewById(R.id.articleDescriptionText);
    }
}
