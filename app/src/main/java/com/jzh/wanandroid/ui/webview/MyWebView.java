package com.jzh.wanandroid.ui.webview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;


import com.jzh.wanandroid.R;
import com.jzh.wanandroid.ui.base.BaseActivity;
import com.jzh.wanandroid.utils.MeasureUtils;
import com.jzh.wanandroid.widget.TextAutoZoom;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cc.shinichi.library.ImagePreview;
import cc.shinichi.library.bean.ImageInfo;

/**
 * Author:jzh
 * desc:my web view
 * Date:2018/05/28 09:56
 * Email:jzh970611@163.com
 * company:畅盈科技
 */

public class MyWebView extends BaseActivity {
    @BindView(R.id.afet_tv_title)
    TextAutoZoom afetTvTitle;
    @BindView(R.id.ll_include_title)
    LinearLayout llIncludeTitle;
    @BindView(R.id.webview_base_progress)
    ProgressBar pbWebBase;
    @BindView(R.id.webview_base)
    WebView webBase;
    private String webPath;
    public static final String REQUEST_WEBVIEW_URL_KEY = "request_webview_url_key";
    private List<ImageInfo> datas = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.ac_webview;
    }


    @Override
    protected void initWidget() {
        setUnBinder(ButterKnife.bind(this));
        setHeadVisibility(View.GONE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        initImmersionBar();
        initAutoFitEditText();
        initData();
    }

    private void initData() {
        pbWebBase.setMax(100);//设置加载进度最大值
        webPath = getIntent().getStringExtra(REQUEST_WEBVIEW_URL_KEY);//加载的URL
        if (TextUtils.isEmpty(webPath)) {
            webPath = "http://www.wanandroid.com";
        }
        WebSettings webSettings = webBase.getSettings();
        if (Build.VERSION.SDK_INT >= 19) {
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//加载缓存否则网络
        }

        if (Build.VERSION.SDK_INT >= 19) {
            webSettings.setLoadsImagesAutomatically(true);//图片自动缩放 打开
        } else {
            webSettings.setLoadsImagesAutomatically(false);//图片自动缩放 关闭
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            webBase.setLayerType(View.LAYER_TYPE_SOFTWARE, null);//软件解码
        }
        webBase.setLayerType(View.LAYER_TYPE_HARDWARE, null);//硬件解码

        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        webSettings.setSupportZoom(true);// 设置可以支持缩放
        webSettings.setBuiltInZoomControls(true);// 设置出现缩放工具 是否使用WebView内置的缩放组件，由浮动在窗口上的缩放控制和手势缩放控制组成，默认false

        webSettings.setDisplayZoomControls(false);//隐藏缩放工具
        webSettings.setUseWideViewPort(true);// 扩大比例的缩放

        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//自适应屏幕
        webSettings.setLoadWithOverviewMode(true);

        webSettings.setDatabaseEnabled(true);//
        webSettings.setSavePassword(true);//保存密码
        webSettings.setDomStorageEnabled(true);//是否开启本地DOM存储  鉴于它的安全特性（任何人都能读取到它，尽管有相应的限制，将敏感数据存储在这里依然不是明智之举），Android 默认是关闭该功能的。
        webBase.setSaveEnabled(true);
        webBase.setKeepScreenOn(true);
        if (null == webBase || null == pbWebBase || null == afetTvTitle) {
            return;
        }

        // 设置setWebChromeClient对象
        webBase.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (null != afetTvTitle)
                    afetTvTitle.setText(title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO Auto-generated method stub
                if (null != pbWebBase)
                    pbWebBase.setProgress(newProgress);
                super.onProgressChanged(view, newProgress);
            }
        });
        //设置此方法可在WebView中打开链接，反之用浏览器打开
        webBase.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                view.getSettings().setJavaScriptEnabled(true);
                super.onPageFinished(view, url);
                // 获取页面内容
//                view.loadUrl("javascript:window.java_obj.showSource(document.getElementsByTagName('body')[0].innerHTML);");
//                view.loadUrl("javascript:window.java_obj.showSource(document.body.innerHTML);");
//                datas.clear();
//                addImageClickListener(view);
                if (null != webBase && null != pbWebBase) {
                    if (!webBase.getSettings().getLoadsImagesAutomatically()) {
                        webBase.getSettings().setLoadsImagesAutomatically(true);
                    }
                    pbWebBase.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                view.getSettings().setJavaScriptEnabled(true);
//                datas.clear();
                if (null != pbWebBase) {
                    pbWebBase.setVisibility(View.VISIBLE);
                }
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url.startsWith("http:") || url.startsWith("https:")) {
                    view.loadUrl(url);
                    return false;
                }
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        });
        webBase.loadUrl(webPath);
        InJavaScriptLocalObj inJavaScriptLocalObj = new InJavaScriptLocalObj();
//        webBase.addJavascriptInterface(inJavaScriptLocalObj, "java_obj");
//        webBase.addJavascriptInterface(inJavaScriptLocalObj, "imagelistener");
    }

    private ImageInfo info;

    /**
     * js接口
     */
    class InJavaScriptLocalObj {

        @JavascriptInterface
        public void showSource(String url) {
            if (TextUtils.isEmpty(url)) {
                return;
            }
            info = new ImageInfo();
            info.setOriginUrl(url);
            info.setThumbnailUrl(url);
            datas.add(info);
            info = null;
        }

        @JavascriptInterface
        public void openImage(int index) {
            if (datas == null || datas.size() <= 0) {
                return;
            }
            try {
                ImagePreview.getInstance()
                        .setContext(MyWebView.this)
                        .setIndex((index >= 0 && index < datas.size()) ? index : 0)// 默认索引，从0开始
                        .setImageInfoList(datas)// 图片源
                        .setShowDownButton(true)// 是否显示下载按钮
                        .setLoadStrategy(ImagePreview.LoadStrategy.AlwaysThumb)// 加载策略，具体看下面介绍
                        .setFolderName("ImageDownLoad")// 保存图片到SD卡根目录的文件夹名称
                        .setScaleLevel(1, 3, 8)// 最小、中等、最大的缩放比例
                        .setZoomTransitionDuration(300)// 缩放的动画时长
                        .start();
            } catch (Exception e) {
                onToastFail(R.string.unknown_error);
            }
        }
    }

    private void addImageClickListener(WebView webView) {
        webView.loadUrl("javascript:(function(){" +
                "var imgs = document.getElementsByTagName(\"img\");\n" +
                "\t\t\tfor(let i = 0; i < imgs.length; i++) {\n" +
                "\t\t\t\twindow.imagelistener.showSource(imgs[i].src);\n" +
                "\t\t\t}\n" +
                "\t\t\tfor(let i = 0; i < imgs.length; i++) {\n" +
                "\t\t\t\tif(imgs[i].parentNode.tagName.toLowerCase() != 'a') {\n" +
                "\t\t\t\t\t(function(i) {\n" +
                "\t\t\t\t\t\timgs[i].addEventListener(\"mousemove\", function() {\n" +
                "\t\t\t\t\t\t\twindow.imagelistener.openImage(i);\n" +
                "\t\t\t\t\t\t});\n" +
                "\t\t\t\t\t})(i);\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}" +
                "})()");
    }

    /**
     * 初始化标题文本
     */
    public void initAutoFitEditText() {
        afetTvTitle.clearFocus();
        afetTvTitle.setEnabled(false);
        afetTvTitle.setFocusableInTouchMode(false);
        afetTvTitle.setFocusable(false);
        afetTvTitle.setEnableSizeCache(false);
        //might cause crash on some devices
        afetTvTitle.setMovementMethod(null);
        // can be added after layout inflation;
        afetTvTitle.setMaxHeight(MeasureUtils.dip2px(this, 55f));
        //don't forget to add min text size programmatically
        afetTvTitle.setMinTextSize(37f);
        TextAutoZoom.setNormalization(this, llIncludeTitle, afetTvTitle);
        hideSoftInput(this);
    }

    /**
     * 动态隐藏软键盘
     *
     * @param activity activity
     */
    public static void hideSoftInput(Activity activity) {
        View view = activity.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputManger = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle paramBundle) {
        super.onSaveInstanceState(paramBundle);
        paramBundle.putString("url", webBase.getUrl());
    }

    @OnClick(R.id.iv_finish)
    public void onViewClicked() {
        if (webBase.canGoBack()) {
            webBase.goBack();
        } else {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        if (webBase.canGoBack()) {
            webBase.goBack();
        } else {
            finish();
        }
    }
}
