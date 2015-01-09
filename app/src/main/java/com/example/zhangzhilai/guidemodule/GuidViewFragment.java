package com.example.zhangzhilai.guidemodule;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by zhangzhilai on 1/8/15.
 */
public class GuidViewFragment extends Fragment{

    public static final String TAG = "GuidViewFragment";

    public static final String GUIDEVIEW_RESOURCE_BACKGRONDS = "GUIDEVIEW_RESOURCE_BACKGRONDS";
    public static final String GUIDEVIEW_PAGE_POSITION = "GUIDEVIEW_PAGE_POSITION";
    public static final String GUIDEVIEW_PAGE_COUNT = "GUIDEVIEW_PAGE_COUNT";

    private int mBackgroundResourceId;
    private int mPageCount;
    private int mPagePosition;

    private ImageView mBackGroundImageView;
    private FrameLayout mAddviewFrame;
    private Bitmap mBackGroundBitmap;

    public static GuidViewFragment newInstance(int backGroundRes,int backGoundCount, int position){
        GuidViewFragment guidViewFragment = new GuidViewFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(GUIDEVIEW_RESOURCE_BACKGRONDS, backGroundRes);
        bundle.putInt(GUIDEVIEW_PAGE_COUNT, backGoundCount);
        bundle.putInt(GUIDEVIEW_PAGE_POSITION, position);
        guidViewFragment.setArguments(bundle);
        return guidViewFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mBackgroundResourceId = getArguments().getInt(GUIDEVIEW_RESOURCE_BACKGRONDS);
        mPageCount = getArguments().getInt(GUIDEVIEW_PAGE_COUNT);
        mPagePosition = getArguments().getInt(GUIDEVIEW_PAGE_POSITION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.guide_viewpager_item, null);
        mAddviewFrame = (FrameLayout)view.findViewById(R.id.frame_addview);
        mBackGroundImageView = (ImageView)view.findViewById(R.id.img_background);
        mBackGroundImageView.setScaleType(ImageView.ScaleType.CENTER);
        mBackGroundBitmap = loadBitmapResource(mBackgroundResourceId, 1);
        if(mBackGroundBitmap != null){
            mBackGroundImageView.setBackgroundResource(mBackgroundResourceId);
        } else {
            mBackGroundImageView.setImageBitmap(mBackGroundBitmap);
        }
        if(mPagePosition == mPageCount - 1){
            addViews(mAddviewFrame);
        }
        return view;
    }

    private void addViews(FrameLayout view) {
        Button button = new Button(getActivity());
        button.setWidth(30);
        button.setHeight(30);
        button.setText("hello world");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), JumpActivity.class);
                getActivity().finish();
                startActivity(intent);
            }
        });
        view.addView(button);
    }

    public Bitmap loadBitmapResource(int imageId, int sampleSize) {
        try {
            InputStream is = this.getResources().openRawResource(imageId);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            options.inSampleSize = sampleSize;
            Bitmap btp = BitmapFactory.decodeStream(is, null, options);
            return btp;
        } catch (Exception e) {
            e.printStackTrace();
        } catch (OutOfMemoryError e) {
            return loadBitmapResource(imageId, sampleSize++);
        }
        return null;
    }
}
