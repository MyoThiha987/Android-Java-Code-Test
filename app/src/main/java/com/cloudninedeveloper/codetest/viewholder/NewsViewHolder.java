package com.cloudninedeveloper.codetest.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cloudninedeveloper.codetest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author myothiha
 * Created 15/01/2023 at 3:07 PM.
 **/

public class NewsViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_author)
    public AppCompatTextView tvAuthor;

    @BindView(R.id.tv_title)
    public AppCompatTextView tvTitle;

    @BindView(R.id.tv_date)
    public AppCompatTextView tvDate;

    @BindView(R.id.imv_article)
    public AppCompatImageView imvArticle;

    @BindView(R.id.cardview)
    public CardView cardView;

    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}

