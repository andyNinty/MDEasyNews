package andy.lee.mdeasynews.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import andy.lee.mdeasynews.R;
import andy.lee.mdeasynews.bean.NewsList;
import andy.lee.mdeasynews.utils.ImageLoader;


/**
 * andy.lee.easynews.holder
 * Created by andy on 16-11-8.
 */

public class NewsViewHolderOne extends BaseViewHolder {

    private ImageView image;
    private TextView mDescription;
    private TextView mTitle;
    private TextView mTime;
    private Context mContext;

    public NewsViewHolderOne(View itemView, Context context) {
        super(itemView);
        mContext = context;
        image = (ImageView) itemView.findViewById(R.id.pic);
        mDescription = (TextView) itemView.findViewById(R.id.description);
        mTitle = (TextView) itemView.findViewById(R.id.title);
        mTime = (TextView) itemView.findViewById(R.id.time);
    }

    @Override
    public void bindHolder(NewsList list) {
        ImageLoader.loadImage(mContext, list.getPicUrl(), image);
        mDescription.setText(list.getDescription());
        mTitle.setText(list.getTitle());
        mTime.setText(list.getCtime());
    }

    public ImageView getImage() {
        return image;
    }
}
