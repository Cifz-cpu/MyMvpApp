package wz.cifz.zx.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import wz.cifz.zx.MyApplication;
import wz.cifz.zx.R;
import wz.cifz.zx.moudle.bean.NewsList;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/27.
 */

public class MyNewsAdapter extends BaseQuickAdapter<NewsList.TngouBean,BaseViewHolder> {

    public MyNewsAdapter(@LayoutRes int layoutResId, @Nullable List<NewsList.TngouBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, NewsList.TngouBean item) {
        helper.setText(R.id.news_name,item.getKeywords());

        Picasso.with(MyApplication.getApplication())
                .load("http://tnfs.tngou.net/image"+item.getImg()+"_80x80")
                .config(Bitmap.Config.RGB_565)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        helper.setImageBitmap(R.id.news_img,bitmap);
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });

//        Glide.with(MyApplication.getApplication())
//                .load("http://tnfs.tngou.net/image"+item.getImg()+"_180x180")
//                .into(new SimpleTarget<Drawable>() {
//                    @Override
//                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
//
//                    }
//                });

    }
}
