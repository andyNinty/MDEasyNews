package andy.lee.mdeasynews.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import andy.lee.mdeasynews.bean.NewsList;


/**
 * andy.lee.easynews.holder
 * Created by andy on 16-11-8.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindHolder(NewsList list);
}
