package wz.cifz.zx.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.youth.banner.loader.ImageLoader;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/30.
 */

public class GlideImageLoader extends ImageLoader{

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        /**
         注意：
         1.图片加载器由自己选择，这里不限制，只是提供几种使用方法
         2.返回的图片路径为Object类型，由于不能确定你到底使用的那种图片加载器，
         传输的到的是什么格式，那么这种就使用Object接收和返回，你只需要强转成你传输的类型就行，
         切记不要胡乱强转！
         */
        String nowPath = (String) path;
        if (nowPath != null && nowPath.length() > 0) {
//            Glide.with(context).load(nowPath).into(imageView);
            Picasso.with(context).load(nowPath).config(Bitmap.Config.RGB_565).into(imageView);
        }
    }
}
