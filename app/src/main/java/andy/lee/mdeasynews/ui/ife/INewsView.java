package andy.lee.mdeasynews.ui.ife;

import android.content.Context;

import andy.lee.mdeasynews.bean.HttpResult;
import andy.lee.mdeasynews.bean.NewsList;

/**
 * andy.lee.mdeasynews.ui.ife
 * Created by andy on 16-12-7.
 */

public interface INewsView {

    void showErrorMsg();

    void showSuccessMsg();

    void  getResultData(HttpResult<NewsList> result);

    Context getCtx();
}
