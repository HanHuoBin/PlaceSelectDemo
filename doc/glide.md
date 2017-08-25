# Glide实例

## 1.tools下添加一个简单的 单例服务工厂 SingleFactory

通过STManager 获取单例对象
```
public static <T> T getSingle(Class<T> serviceName) {
    return SingleFactory.getInstance().getService(serviceName);
}
```

MoreManager 用于管理各个获取单例的实例，比如Glide：
```
public static MoreImageLoader getImageLoader() {
    return STManager.getSingle(MoreImageLoader.class);
}
```

对的，就是这么简单

## 2.MoreImageLoader Glide常用方法的封装
先看个实例代码
```
Glide.with(context).load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .placeholder(R.mipmap.ic_image_loading)
            .error(R.mipmap.ic_error_picture)
            .crossFade()
            .into(imageView);

Glide.with(context).load(url)
            .placeholder(placeHolder)
            .error(error)
            .animate( android.R.anim.slide_in_left ) // or R.anim.zoom_in
            .into(imageView);

```

方法说明:

    1.placeholder 等待加载时显示的图片
    2.error 加载失败后显示的图片
    3.crossFade 淡入淡出动画
     3.1补充：如果对于当前动画不满足，可以自定义动画，使用Glide的animate方法
    4.CenterCrop()是一个裁剪技术，即缩放图像让它填充到 ImageView 界限内并且裁剪额外的部分。ImageView 会被完全填充，但图像可能不会完整显示。
     4.1fitCenter()是一个裁剪技术，即缩放图像让图像都测量出来等于或小于 ImageView 的边界范围。该图像将会完全显示，但可能不会填满整个 ImageView。
    5.diskCacheStrategy 设置缓存策略
     5.1 DiskCacheStrategy.SOURCE：缓存原始数据
     5.2 DiskCacheStrategy.RESULT：缓存变换(如缩放、裁剪等)后的资源数据
     5.3 DiskCacheStrategy.NONE：什么都不缓存
     5.4 DiskCacheStrategy.ALL：缓存SOURCE和RESULT。
     5.5 默认采用DiskCacheStrategy.RESULT策略，对于download only操作要使用DiskCacheStrategy.SOURCE。