package andy.lee.mdeasynews.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import andy.lee.mdeasynews.R;
import andy.lee.mdeasynews.bean.HttpResult;
import andy.lee.mdeasynews.bean.NewsList;
import andy.lee.mdeasynews.constant.Constant;
import andy.lee.mdeasynews.ui.activity.NewsDetailActivity;
import andy.lee.mdeasynews.ui.adapter.MyViewAdapter;
import andy.lee.mdeasynews.ui.base.LazyFragment;
import andy.lee.mdeasynews.ui.ife.INewsView;
import andy.lee.mdeasynews.ui.presenter.NewsPresenter;

public class NewsFragment extends LazyFragment implements INewsView,
        MyViewAdapter.OnRecyclerViewItemClickListener, XRecyclerView.LoadingListener {

    private NewsPresenter mPresenter;
    private XRecyclerView mRecyclerView;
    private MyViewAdapter mAdapter;
    private List<NewsList> mLists;

    private boolean isPrepared;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new NewsPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        initRecyclerView(view);
        return view;
    }

    private void initRecyclerView(View view) {
        mRecyclerView = (XRecyclerView) view.findViewById(R.id.recycle_view);
        mAdapter = new MyViewAdapter(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallScaleMultiple);

        mRecyclerView.setLoadingListener(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        isPrepared = true;
        lazyLoad();
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }
        mPresenter.request(1);
    }

    @Override
    public void showErrorMsg() {
        new AlertDialog.Builder(getContext())
                .setMessage("异常，请重试")
                .setTitle("提示您")
                .setPositiveButton("确认", (dialogInterface, i) -> mActivity.finish()).create().show();
    }

    @Override
    public void showSuccessMsg() {
        if (mLists != null) {
            mAdapter.addList(mLists);
        } else {
            Toast.makeText(getActivity(), "api访问次数已达上限", Toast.LENGTH_SHORT).show();
        }
//        mAdapter.notifyDataSetChanged();
        mRecyclerView.loadMoreComplete();
    }

    @Override
    public void getResultData(HttpResult<NewsList> result) {
        mLists = result.getNewslist();
    }

    @Override
    public Context getCtx() {
        return getContext();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        int i = (int) (Math.random() * 100 + 1);
        mPresenter.request(i);
    }

    @Override
    public void onItemClick(NewsList data, ImageView imageView) {
        Intent intent = new Intent();
        intent.putExtra(Constant.PIC_URL, data.getPicUrl());
        intent.putExtra(Constant.NEWS_URL, data.getUrl());
        intent.setClass(getActivity(), NewsDetailActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(getActivity(), imageView, Constant.PIC_ANIMATION);
        ActivityCompat.startActivity(getActivity(), intent, options.toBundle());


    }

    @Override
    public void onLikeClick(NewsList data) {

    }

    @Override
    public void onUnLikeClick(NewsList data) {

    }

}
