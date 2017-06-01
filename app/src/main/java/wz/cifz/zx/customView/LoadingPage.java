package wz.cifz.zx.customView;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import wz.cifz.zx.R;
import wz.cifz.zx.utils.UIUtils;

/**
 * desc 加载页面
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/25.
 */

public abstract class LoadingPage extends FrameLayout {

    private static final int PAGE_LOADING_STATE = 1;

    private static final int PAGE_ERROR_STATE = 2;

    private static final int PAGE_EMPTY_STATE = 3;

    private static final int PAGE_SUCCESS_STATE = 4;

    private int PAGE_CURRENT_STATE = 1;

    private View loadingView;

    private View errorView;

    private View emptyView;

    private View successView;

    private LayoutParams lp;

    private Context mConext;

    public LoadingPage(@NonNull Context context) {
        super(context);
        init();
    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mConext = context;
        init();
    }

    private void init() {
        lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if (loadingView == null) {
            loadingView = UIUtils.getXmlView(R.layout.page_loading);
            addView(loadingView, lp);
        }
        
        if (errorView == null) {
            errorView = UIUtils.getXmlView(R.layout.page_error);
            addView(errorView, lp);
        }
        
        if (emptyView == null) {
            emptyView = UIUtils.getXmlView(R.layout.page_empty);
            addView(emptyView, lp);
        }

//        if (successView == null) {
//            successView = View.inflate(mConext, LayoutId(), null);
//            addView(successView, lp);
//        }
        
        showSafePage();
    }

    private void showSafePage() {
        UIUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                showPage();
            }
        });
    }

    private void showPage() {
        loadingView.setVisibility(PAGE_CURRENT_STATE == PAGE_LOADING_STATE ? View.VISIBLE : View.GONE);
        errorView.setVisibility(PAGE_CURRENT_STATE == PAGE_ERROR_STATE ? View.VISIBLE : View.GONE);
        emptyView.setVisibility(PAGE_CURRENT_STATE == PAGE_EMPTY_STATE ? View.VISIBLE : View.GONE);
        if (successView == null) {
            successView = View.inflate(mConext, LayoutId(), null);
            addView(successView, lp);
        }
        successView.setVisibility(PAGE_CURRENT_STATE == PAGE_SUCCESS_STATE ? View.VISIBLE : View.GONE);
    }

    public void show(int state) {
        //状态归位
        if (PAGE_CURRENT_STATE != PAGE_LOADING_STATE) {
            PAGE_CURRENT_STATE = PAGE_LOADING_STATE;
        }

        loadPage(state);

    }

    private void loadPage(int state) {
        switch (state) {
            case 1:
                //当前状态为1显示加载页面
                PAGE_CURRENT_STATE = 1;
                break;
            case 2:
                //当前状态设置为2，显示错误界面
                PAGE_CURRENT_STATE = 2;
                 break;
            case 3:
                //当前状态设置为3，显示空界面
                PAGE_CURRENT_STATE = 3;
                break;
            case 4:
                //当前状态设置为4，显示成功界面
                PAGE_CURRENT_STATE = 4;
                break;
        }
        showSafePage();
        
        if (PAGE_CURRENT_STATE == 4) {
            OnSuccess(successView);
        }
    }

    protected abstract void OnSuccess(View successView);

    //    protected abstract void OnSuccess(ResultState resultState, View successView);
//
    public abstract int LayoutId();

//    public enum ResultState {
//
//        ERROR(2), EMPTY(3), SUCCESS(4);
//
//        private int state;
//
//        private String content;
//
//        ResultState(int state) {
//            this.state = state;
//        }
//
//        public int getState() {
//            return state;
//        }
//
//        public void setState(int state) {
//            this.state = state;
//        }
//
//        public String getContent() {
//            return content;
//        }
//
//        public void setContent(String content) {
//            this.content = content;
//        }
//    }

}
