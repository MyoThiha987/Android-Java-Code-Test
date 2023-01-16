package com.cloudninedeveloper.codetest.adapter;

import static com.cloudninedeveloper.codetest.utils.Utils.dateFromServerDate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cloudninedeveloper.codetest.R;
import com.cloudninedeveloper.codetest.data.vos.NewsVo;
import com.cloudninedeveloper.codetest.ui.newlist.MainActivity;
import com.cloudninedeveloper.codetest.utils.Utils;
import com.cloudninedeveloper.codetest.viewholder.NewsViewHolder;

import java.text.ParseException;
import java.util.List;

/**
 * @Author myothiha
 * Created 15/01/2023 at 2:56 PM.
 **/

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {
    private Context context;
    private List<NewsVo> articleList;
    private MainActivity mainActivity;

    public NewsAdapter(Context context, List<NewsVo> articleList) {
        this.context = context;
        this.articleList = articleList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.tvAuthor.setText(articleList.get(position).getAuthor());
        holder.tvTitle.setText(articleList.get(position).getTitle());
        holder.tvDate.setText(articleList.get(position).getPublishedAt());
        Glide.with(context).load(articleList.get(position).getUrlToImage()).into(holder.imvArticle);
        try {
            holder.tvDate.setText(Utils.DateTimeStandard(dateFromServerDate(articleList.get(position).getPublishedAt())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.cardView.setOnClickListener(view -> {
            mainActivity.onItemClick(articleList.get(position));
        });

    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public void setOnClickListener(MainActivity mainActivity) {
        this.mainActivity = mainActivity;

    }

    public interface ItemClickCallback {
        void onItemClick(NewsVo newsVo);
    }

}

