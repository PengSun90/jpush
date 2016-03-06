package com.xunmeng.jpush.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.xunmeng.jpush.R;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * <p>
 * Title: jazzViewPagerAdapter.java
 * </p>
 * <p>
 * E-Mail: 176291935@qq.com
 * </p>
 * <p>
 * QQ: 176291935
 * </p>
 * <p>
 * Http: iaiai.iteye.com
 * </p>
 * <p>
 * Create time: 2011-6-26
 * </p>
 * 
 * @author ����
 * @version 0.0.1
 */
public class jazzViewPagerAdapter extends PagerAdapter {

	private ArrayList<View> mPageViewList;
	private List<String> mImageList;
	private Context mContext;
	private LayoutInflater mInflater;
	// private ImageDownloader mImageDownloader;

	public jazzViewPagerAdapter(Context context, List<String> imageList) {
		mContext = context;
		mImageList = imageList;
		mPageViewList = new ArrayList<View>();
		if (mInflater == null) {
			mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
		// TODO: 2016/3/6  记得更新数组
		for (int i = 0; i < /*暂时先写死为4*/4; i++) {
			View view = mInflater.inflate(R.layout.news_jazz_view_item, null);
			mPageViewList.add(view);
		}
//		imageLoader = new AsyncImageLoader(mContext);
	}

	@Override
	public int getCount() {
		return mPageViewList != null ? mPageViewList.size() : 0;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public int getItemPosition(Object object) {
		return super.getItemPosition(object);
	}

	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		((ViewPager) arg0).removeView(mPageViewList.get(arg1));
	}

	@Override
	public Object instantiateItem(View arg0, final int arg1) {
		((ViewPager) arg0).addView(mPageViewList.get(arg1));
		final ImageView ImageV = (ImageView) mPageViewList.get(arg1).findViewById(R.id.image);

//		final String imgUrl = mImageList.get(arg1);
//		ImageV.setTag(imgUrl);
//		if (!TextUtils.isEmpty(imgUrl)) {
//			Bitmap bitmap = imageLoader.loadImage(ImageV, imgUrl);
//			if (bitmap != null) {
				ImageV.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.duoduo));
//			}
//		}
//		ImageV.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Toast.makeText(mContext, "���ͼƬ", Toast.LENGTH_LONG).show();
//			}
//		});
		return mPageViewList.get(arg1);
	}

	@Override
	public void restoreState(Parcelable arg0, ClassLoader arg1) {
		//
	}

	@Override
	public Parcelable saveState() {
		//
		return null;
	}

	@Override
	public void startUpdate(View arg0) {
		//
	}

	@Override
	public void finishUpdate(View arg0) {
		//
	}

}
