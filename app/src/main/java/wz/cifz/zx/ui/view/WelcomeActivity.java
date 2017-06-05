package wz.cifz.zx.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import wz.cifz.zx.R;
import wz.cifz.zx.customView.SimpleButton;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/6/2.
 */

public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.sb_skip)
    SimpleButton sbSkip;
    boolean mIsSkip = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        final int count = 5;


        Observable.interval(0,1, TimeUnit.SECONDS)
                .take(count+1)
                .map(new Function<Long, Integer>() {
                    @Override
                    public Integer apply(@NonNull Long aLong) throws Exception {
                        return count - aLong.intValue();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        Log.e("safdsafs",integer+"");
                        sbSkip.setText("跳过"+integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        _doSkip();
                    }

                    @Override
                    public void onComplete() {
                        _doSkip();
                    }
                });



    }

    @Override
    public void onBackPressed() {
        // 不响应后退键
        return;
    }

    private void _doSkip() {

        if (!mIsSkip) {
            mIsSkip = true;
            finish();
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            overridePendingTransition(R.anim.hold, R.anim.zoom_in_exit);
        }
    }

    @OnClick(R.id.sb_skip)
    public void click(View v){
        _doSkip();
    }

}
