package wz.cifz.zx.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import wz.cifz.zx.R;
import wz.cifz.zx.adapter.MyNewsAdapter;
import wz.cifz.zx.injecct.moudle.AsumeModule;
import wz.cifz.zx.moudle.bean.AumsePic;
import wz.cifz.zx.moudle.bean.NewsList;
import wz.cifz.zx.ui.fragment.IView.IAumseView;
import wz.cifz.zx.ui.fragment.component.DaggerAsumeComponent;
import wz.cifz.zx.ui.fragment.presenter.PreAmuseLiveload;
import wz.cifz.zx.ui.view.PicDetailActivity;
import wz.cifz.zx.utils.GlideImageLoader;
import wz.cifz.zx.utils.ToastUtils;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/23.
 */

public class AmusementLiveFragment extends BaseFragment implements IAumseView, SwipeRefreshLayout.OnRefreshListener {
    @Inject
    PreAmuseLiveload preAmuseLiveload;
    @BindView(R.id.frag_rl_edu)
    RecyclerView fragRlEdu;

    @BindView(R.id.edu_swipe)
    SwipeRefreshLayout EduSwipe;

    private int currentPage = 1;

    MyNewsAdapter adapter;

    Banner banner;
    View headerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_aumse, null);
        ButterKnife.bind(this, view);
        initView();
        load();
        loadPic();
        return view;
    }


    @Override
    public void load() {
        preAmuseLiveload.load(2, 1);
    }

    @Override
    public void initView() {
        DaggerAsumeComponent
                .builder()
                .asumeModule(new AsumeModule(this))
                .build()
                .inject(this);
        headerView = LayoutInflater.from(getContext()).inflate(R.layout.aumse_header, null);
        banner = (Banner) headerView.findViewById(R.id.aumse_head_banner);
    }

    @Override
    public void setData(NewsList list) {

        EduSwipe.setColorSchemeColors(Color.rgb(47, 223, 189));
        EduSwipe.setOnRefreshListener(this);

        fragRlEdu.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new MyNewsAdapter(R.layout.news_item, list.getTngou());
        adapter.addHeaderView(headerView);
        fragRlEdu.setAdapter(adapter);

        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showToast("position+" + position);
            }
        });
        //加载更多
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                currentPage++;
                preAmuseLiveload.loadMoreData(2, currentPage);
            }
        }, fragRlEdu);

        if (EduSwipe.isRefreshing()) {
            EduSwipe.setRefreshing(false);
        }
    }

    @Override
    public void loadPic() {
        preAmuseLiveload.loadPicData(1 + (int) (Math.random() * 7), 5);
    }

    @Override
    public void setPic(final AumsePic aumsePic) {

        List<String> list = new ArrayList<>();
        final List<String> listT = new ArrayList<>();
        for (int i = 0; i < aumsePic.getTngou().size(); i++) {
            list.add("http://tnfs.tngou.net/image" + aumsePic.getTngou().get(i).getImg());
            listT.add(aumsePic.getTngou().get(i).getTitle());
        }

        //初始化banner
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(list);
        banner.setBannerTitles(listT);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), PicDetailActivity.class);
                intent.putExtra("picid", aumsePic.getTngou().get(position).getId());
                getActivity().startActivity(intent);
//                ARouter.getInstance().build("/act/picdetail").withInt("picid",position).navigation();
                ToastUtils.showToast(listT.get(position));
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void LoadMore(NewsList newsList) {
        adapter.addData(newsList.getTngou());
        adapter.loadMoreComplete();
    }

    @Override
    public void onRefresh() {
        currentPage = 1;
        preAmuseLiveload.load(2, currentPage);
        preAmuseLiveload.loadPicData(1, 5);
    }


}
