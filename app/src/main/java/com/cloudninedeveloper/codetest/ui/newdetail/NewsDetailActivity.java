package com.cloudninedeveloper.codetest.ui.newdetail;

import static com.cloudninedeveloper.codetest.utils.Utils.dateFromServerDate;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.cloudninedeveloper.codetest.R;
import com.cloudninedeveloper.codetest.data.vos.NewsVo;
import com.cloudninedeveloper.codetest.utils.Utils;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_author)
    public AppCompatTextView tvAuthor;
    @BindView(R.id.tv_title)
    public AppCompatTextView tvTitle;
    @BindView(R.id.tv_date)
    public AppCompatTextView tvDate;
    @BindView(R.id.tv_desc)
    public AppCompatTextView tvDesc;
    @BindView(R.id.imv_article)
    public AppCompatImageView imvArticle;
    @BindView(R.id.imvBack)
    public AppCompatImageView imvBack;
    private NewsVo newsVo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);
        newsVo = (NewsVo) getIntent().getSerializableExtra("NEWS");
        bindDataToUi(newsVo);
        onBackPress();
    }

    //Bind Data to UI
    private void bindDataToUi(NewsVo newsVo) {
        tvAuthor.setText(newsVo.getAuthor());
        tvDesc.setText(newsVo.getDescription());
        tvTitle.setText(newsVo.getTitle());
        try {
            tvDate.setText(Utils.DateTimeStandard(dateFromServerDate(newsVo.getPublishedAt())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Glide.with(this).load(newsVo.getUrlToImage()).into(imvArticle);
    }

    //Go back to NewList Screen
    private void onBackPress() {
        imvBack.setOnClickListener(view -> onBackPressed());
    }
}