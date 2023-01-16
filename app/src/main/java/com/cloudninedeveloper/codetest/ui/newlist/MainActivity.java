package com.cloudninedeveloper.codetest.ui.newlist;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.cloudninedeveloper.codetest.R;
import com.cloudninedeveloper.codetest.adapter.NewsAdapter;
import com.cloudninedeveloper.codetest.base.BaseActivity;
import com.cloudninedeveloper.codetest.data.vos.NewsVo;
import com.cloudninedeveloper.codetest.receiver.ConnectivityReceiver;
import com.cloudninedeveloper.codetest.ui.newdetail.NewsDetailActivity;
import com.cloudninedeveloper.codetest.utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements NewsAdapter.ItemClickCallback {

    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.recyclerview)
    RecyclerView newsRecyclerView;

    //ViewModel
    private NewsViewModel viewModel;
    //NewsAdapter
    private NewsAdapter newsAdapter;
    //ConnectivityBroadcast Receiver
    private ConnectivityReceiver connectivityReceiver;
    boolean doubleBackToExitPressedOnce = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        viewModel = new NewsViewModel(getApplication());
        setUpConnectivityReceiver();
        refreshArticleList();
        setUpObserver();
    }

    //setup connectivity broadcast receiver
    private void setUpConnectivityReceiver() {
        connectivityReceiver = new ConnectivityReceiver();
        registerReceiver(connectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    //refresh Article List
    private void refreshArticleList() {
        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(true);
            viewModel.fetchArticleList();
        });
    }

    //Observe for data changes
    private void setUpObserver() {
        viewModel.fetchArticleList();
        swipeRefreshLayout.setRefreshing(true);
        newsListObserver();
        connectivityObserver();
    }


    private void newsListObserver() {
        //observe data changes
        viewModel.articlesList.observe(this, newsVoArrayList -> {
            setUpRecyclerview(newsVoArrayList);
            swipeRefreshLayout.setRefreshing(false);
        });
    }

    private void connectivityObserver() {
        //check network is available
        Utils.isOnline.observe(this, isOnline -> {
            if (isOnline) {
                showToastMessage(getResources().getString(R.string.connected));
                swipeRefreshLayout.setRefreshing(true);
                viewModel.fetchArticleList();
            } else showToastMessage(getResources().getString(R.string.no_connected));
        });
    }

    //setup recyclerview
    private void setUpRecyclerview(List<NewsVo> newsList) {
        newsRecyclerView.setHasFixedSize(true);
        newsAdapter = new NewsAdapter(this, newsList);
        newsRecyclerView.setAdapter(newsAdapter);
        newsAdapter.setOnClickListener(this);
    }

    @Override
    public void onItemClick(NewsVo newsVo) {
        Intent intent = new Intent(this, NewsDetailActivity.class);
        intent.putExtra("NEWS", newsVo);
        this.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(connectivityReceiver);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        newsRecyclerView.smoothScrollToPosition(0);
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        showToastMessage(getResources().getString(R.string.double_back_press));

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}