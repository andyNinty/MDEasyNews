package andy.lee.mdeasynews.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * 使用glide加载图片
 * andy.lee.easynews.util
 * Created by andy on 16-11-4.
 */

public class ImageLoader {

    //设置crossFade()动画，不使用占位符
    public static void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
    }

    public static void loadImage(Activity activity, String url, ImageView imageView) {
        Glide.with(activity).load(url).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageView);
    }

    //禁止内存缓存，清除内存缓存，全部从网络加载
    public static void loadAllImage(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).crossFade().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
    }

    public static void loadAllImage(Activity activity, String url, ImageView imageView) {
        Glide.with(activity).load(url).crossFade().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
    }
}
