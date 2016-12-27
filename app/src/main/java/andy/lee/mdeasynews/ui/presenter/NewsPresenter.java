package andy.lee.mdeasynews.ui.presenter;

import andy.lee.mdeasynews.bean.HttpResult;
import andy.lee.mdeasynews.bean.NewsList;
import andy.lee.mdeasynews.net.HttpRequest;
import andy.lee.mdeasynews.ui.ife.INewsView;
import rx.Subscriber;

/**
 * andy.lee.mdeasynews.ui.presenter
 * Created by andy on 16-12-7.
 */

public class NewsPresenter {
    private INewsView mIView;

    public NewsPresenter(INewsView IView) {
        mIView = IView;
    }

    public void request(int page) {
        Subscriber<HttpResult<NewsList>> subscriber = new Subscriber<HttpResult<NewsList>>() {
            @Override
            public void onCompleted() {
                mIView.showSuccessMsg();
            }

            @Override
            public void onError(Throwable e) {
                mIView.showErrorMsg();
            }

            @Override
            public void onNext(HttpResult<NewsList> result) {
                mIView.getResultData(result);
            }
        };
        HttpRequest.getInstance().getNews(subscriber, 10, page, "eb610a0d01c779bdb05b2071b6d555c5");
    }
}
