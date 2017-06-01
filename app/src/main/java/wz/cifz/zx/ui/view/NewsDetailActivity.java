package wz.cifz.zx.ui.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import wz.cifz.zx.R;
import wz.cifz.zx.injecct.component.DaggerNewsComponent;
import wz.cifz.zx.injecct.moudle.NewsDetailModule;
import wz.cifz.zx.moudle.bean.NewsDetail;
import wz.cifz.zx.presenter.PreNewsDetail;
import wz.cifz.zx.ui.IViewBinder.INewDetail;
import wz.cifz.zx.utils.ToastUtils;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/31.
 */

@Route(path = "/act/newsdetail")
public class NewsDetailActivity extends AppCompatActivity implements INewDetail{

    @BindView(R.id.news_image_view)
    ImageView newsImageView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.news_content_text)
    TextView newsContentText;

    long newsid;

    @Inject
    PreNewsDetail preNewsDetail;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);
        initView();
        load();
    }

    @Override
    public void load() {
        preNewsDetail.loadNews(newsid);
    }

    @Override
    public void initView() {

        Intent intent = getIntent();
        newsid = intent.getLongExtra("newsId",0);

        DaggerNewsComponent
                .builder()
                .newsDetailModule(new NewsDetailModule(this))
                .build()
                .inject(this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public void setData(NewsDetail detail) {
        collapsingToolbar.setTitle(detail.getKeywords());
        Picasso.with(getApplicationContext()).load("http://tnfs.tngou.net/image"+detail.getImg()).config(Bitmap.Config.RGB_565).into(newsImageView);
        newsContentText.setText(Html.fromHtml(detail.getMessage()));
    }

    @Override
    public void loadFailed() {
        ToastUtils.showToast("加载失败");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
