package andy.lee.mdeasynews.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import andy.lee.mdeasynews.R;
import andy.lee.mdeasynews.bean.NewsList;
import andy.lee.mdeasynews.holder.NewsViewHolderOne;


/**
 * andy.lee.easynews.ui.adapter
 * Created by andy on 16-11-7.
 */

public class MyViewAdapter extends XRecyclerView.Adapter<NewsViewHolderOne> {

    private LayoutInflater mLayoutInflater;
    private List<NewsList> mList = new ArrayList<>();
    private Context mContext;
    private View mView;

    public MyViewAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);

    }

    public void addList(List<NewsList> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public NewsViewHolderOne onCreateViewHolder(ViewGroup parent, int viewType) {
        mView = mLayoutInflater.inflate(R.layout.item_news, parent, false);
        NewsViewHolderOne viewHolder = new NewsViewHolderOne(mView, mContext);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final NewsViewHolderOne holder, final int position) {
        holder.bindHolder(mList.get(position));
        mView.setOnClickListener(v -> mListener.onItemClick(mList.get(position), holder.getImage()));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public interface OnRecyclerViewItemClickListener {
        void onItemClick(NewsList data, ImageView imageView);

        void onLikeClick(NewsList data);

        void onUnLikeClick(NewsList data);
    }

    private OnRecyclerViewItemClickListener mListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mListener = listener;
    }

}
