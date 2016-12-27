package andy.lee.mdeasynews.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import andy.lee.mdeasynews.R;
import andy.lee.mdeasynews.constant.Constant;
import andy.lee.mdeasynews.utils.ImageLoader;

public class NewsDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mBackDrop;
    private WebView mWebView;
    private String newsUrl;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        initView();
        initAction();
        displayWebView();
    }


    private void initView() {
        mBackDrop = (ImageView) findViewById(R.id.backdrop);
        mWebView = (WebView) findViewById(R.id.webView);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        toolbar.setTitle("新闻详情");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    private void initAction() {
        ViewCompat.setTransitionName(mBackDrop, Constant.PIC_ANIMATION);
        String picUrl = getIntent().getStringExtra(Constant.PIC_URL);
        newsUrl = getIntent().getStringExtra(Constant.NEWS_URL);
        ImageLoader.loadImage(this, picUrl, mBackDrop);
    }


    private void displayWebView() {
        if (mWebView != null) mWebView.loadUrl(newsUrl);
        // 覆盖WebView默认通过第三方或者是系统浏览器打开网页的行为，使得网页可以在WebVIew中打开
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                    startActivity(intent);
                } else if (url.startsWith("http:") || url.startsWith("https:")) {
                    view.loadUrl(url);
                }
                //返回值是true的时候控制网页在WebView中去打开，如果为false调用系统浏览器或第三方浏览器去打开
                return true;
            }
            //WebViewClient帮助WebView去处理一些页面控制和请求通知
        });
        //启用支持JavaScript
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        //设置加载进来的页面自适应手机屏幕
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        //WebView加载页面优先使用缓存加载
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                }
            }
        });
        //android support customTabs
//        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
//
//        CustomTabsIntent customTabsIntent = builder.build();
//        customTabsIntent.launchUrl(this, Uri.parse(newsUrl));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "欢迎访问我的个人博客" +
                        " \n" + "http://andyleeblog.cn");
                intent.putExtra(Intent.EXTRA_SUBJECT, "my blog");
                intent = Intent.createChooser(intent, "分享到");
                startActivity(intent);
        }
    }

    /**
     * 需要重写此方法 返回键才能有效果
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
            default:
                return super.onOptionsItemSelected(item);

        }
        return true;
    }
}
