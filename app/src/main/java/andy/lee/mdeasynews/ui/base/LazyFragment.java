package andy.lee.mdeasynews.ui.base;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * andy.lee.mdeasynews.ui.base
 * Created by andy on 16-12-8.
 */

public abstract class LazyFragment extends Fragment {
    protected boolean isVisible;
    protected Activity mActivity;

    /**
     * 在这里实现Fragment数据的缓加载.
     *
     * @param isVisibleToUser 对用户是否可见
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected abstract void lazyLoad();

    protected void onInvisible() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity) context;
    }
}
