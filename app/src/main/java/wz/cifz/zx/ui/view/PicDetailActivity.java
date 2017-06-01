package wz.cifz.zx.ui.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import wz.cifz.zx.R;
import wz.cifz.zx.injecct.component.DaggerPicComponent;
import wz.cifz.zx.injecct.moudle.PicModule;
import wz.cifz.zx.moudle.bean.PicDetail;
import wz.cifz.zx.presenter.PrePicDetail;
import wz.cifz.zx.ui.IViewBinder.IPicAct;
import wz.cifz.zx.utils.ToastUtils;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/30.
 */

@Route(path = "/act/picdetail")
public class PicDetailActivity extends AppCompatActivity implements IPicAct, ViewPager.OnPageChangeListener {

    @Inject
    PrePicDetail prePicDetail;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.img_tv)
    TextView imgTv;
    @BindView(R.id.img_vp)
    ViewPager imgVp;
    @BindView(R.id.current)
    TextView current;
    @BindView(R.id.total)
    TextView total;

    long id;

    private List<ImageView> imglist;

    ArrayList<String> listUrl;
    ImageView img;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);
        ButterKnife.bind(this);
        initView();
        load();
    }

    public void load() {
        prePicDetail.loadPicDetail(id);
    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        id = intent.getLongExtra("picid",0);

        DaggerPicComponent
                .builder()
                .picModule(new PicModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setData(PicDetail list) {
        total.setText(list.getList().size()+"");
        imgTv.setText(list.getTitle());
        listUrl = new ArrayList<>();
        imglist = new ArrayList<>();
        if(list.getList()!=null){
            total.setText(list.getList().size()+"");
            for(int i = 0;i < list.getList().size();i ++){
                listUrl.add(list.getList().get(i).getSrc());
                img = new ImageView(getApplicationContext());
                imglist.add(img);
            }
        }
        imgVp.setAdapter(new MyPagerAdapter());
        imgVp.addOnPageChangeListener(this);
    }


    @Override
    public void loadFailed() {
        ToastUtils.showToast("图片加载失败");
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        current.setText(position+1+"");
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return listUrl.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = imglist.get(position);
            Picasso.with(getApplicationContext()).load("http://tnfs.tngou.net/image"+listUrl.get(position)).config(Bitmap.Config.RGB_565).into(imglist.get(position));
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imglist.get(position));
        }
    }

}
