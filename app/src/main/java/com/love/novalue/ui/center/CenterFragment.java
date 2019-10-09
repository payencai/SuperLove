package com.love.novalue.ui.center;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.components.ImmersionFragment;
import com.love.novalue.R;
import com.love.novalue.adapter.CardAdapter;
import com.love.novalue.adapter.CardScaleHelper;
import com.love.novalue.adapter.SpeedRecyclerView;
import com.love.novalue.ui.home.HomeFragment;
import com.love.novalue.widget.MyStepView;
import com.ryan.rv_gallery.AnimManager;
import com.ryan.rv_gallery.GalleryRecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CenterFragment extends ImmersionFragment {

    @BindView(R.id.step_view)
    MyStepView mStepView;
    public CenterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_center, container, false);
        ButterKnife.bind(this,view);
        initStepView();
        return view;
    }
    @OnClick({R.id.tv_invate})
    void OnClick(View view){
        switch (view.getId()){
            case R.id.tv_invate:
                showDialog();
                //showPicDialog();
                break;
        }
    }

    private void showPicDialog() {

        final Dialog dialog = new Dialog(getContext(), R.style.CustomDialog);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_share_pic, null);
        GalleryRecyclerView rv_photos=view.findViewById(R.id.rv_list);
        List<Integer> ids=new ArrayList<>();
        ids.add(R.mipmap.sl_photo2);
        ids.add(R.mipmap.sl_photo3);
        ids.add(R.mipmap.sl_photo4);

        CardAdapter  cardAdapter=new CardAdapter(ids) ;
        rv_photos.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rv_photos.setAdapter(cardAdapter);

        rv_photos
                // 设置滑动速度（像素/s）
                .initFlingSpeed(9000)
                // 设置页边距和左右图片的可见宽度，单位dp
                .initPageParams(0, 60)
                // 设置切换动画的参数因子
                .setAnimFactor(0.1f)
                // 设置切换动画类型，目前有AnimManager.ANIM_BOTTOM_TO_TOP和目前有AnimManager.ANIM_TOP_TO_BOTTOM
                .setAnimType(AnimManager.ANIM_BOTTOM_TO_TOP)
                // 设置点击事件
                // 设置自动播放
                .autoPlay(false)
                // 设置自动播放间隔时间 ms
                .intervalTime(2000)
                // 设置初始化的位置
                .initPosition(1)
                // 在设置完成之后，必须调用setUp()方法
                .setUp();

        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);
    }
    private void showDialog() {
        final Dialog dialog = new Dialog(getContext(), R.style.BottomDialog);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_share_center, null);
        view.findViewById(R.id.ll_item1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                showPicDialog();
            }
        });
        dialog.setContentView(view);
        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);
    }
    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this).init();
    }
    private void initStepView() {
        List<String> steps = Arrays.asList(new String[]{"粉丝", "plus", "一星达人","二星达人"});
        mStepView.setSteps(steps);
        mStepView.setCurrentStep(2);
    }

}
