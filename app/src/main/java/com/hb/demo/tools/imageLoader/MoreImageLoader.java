package com.hb.demo.tools.imageLoader;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hb.demo.R;
import com.hb.demo.utils.glide.GlideRoundTransformUtil;

/**
 * Created by tech60 on 2017/8/21.
 */

public class MoreImageLoader {
    public MoreImageLoader() {
    }

    /**
     * 加载图片
     * 
     * @param context
     * @param imageView
     * @param url 目标图片
     * @param placeHolder 等待时的图片
     * @param error 加载失败后显示的图片
     */
    public void display(Context context, ImageView imageView, String url, int placeHolder,
                        int error) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument exception:imageView is null");
        }
        //crossFade 淡入淡出动画
        Glide.with(context).load(url)
                .placeholder(placeHolder)
                .error(error)
                .crossFade()
                .into(imageView);
    }

    public void display(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument exception:imageView is null");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.mipmap.ic_image_loading)
                .error(R.mipmap.ic_error_picture)
                .crossFade()
                .into(imageView);
    }

    public void displaySmallPhono(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_image_loading)
                .error(R.mipmap.ic_error_picture)
                .thumbnail(0.5f)
                .into(imageView);
    }

    public void displayBigPhoto(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .asBitmap()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_image_loading)
                .error(R.mipmap.ic_error_picture)
                .into(imageView);
    }

    public void display(Context context, ImageView imageView, int url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.mipmap.ic_image_loading)
                .error(R.mipmap.ic_error_picture)
                .crossFade()
                .into(imageView);
    }

    public void displayRound(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        // diskCacheStrategy 设置缓存策略
        // DiskCacheStrategy.SOURCE：缓存原始数据
        // DiskCacheStrategy.RESULT：缓存变换(如缩放、裁剪等)后的资源数据
        // DiskCacheStrategy.NONE：什么都不缓存
        // DiskCacheStrategy.ALL：缓存SOURCE和RESULT。
        // 默认采用DiskCacheStrategy.RESULT策略，对于download only操作要使用DiskCacheStrategy.SOURCE。
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.mipmap.ic_image_head)
                .centerCrop()
                .transform(new GlideRoundTransformUtil(context))
                .into(imageView);
    }

    public void displayRound(Context context, ImageView imageView, int resId) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(resId)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.mipmap.ic_image_head)
                .centerCrop()
                .transform(new GlideRoundTransformUtil(context))
                .into(imageView);
    }

}
