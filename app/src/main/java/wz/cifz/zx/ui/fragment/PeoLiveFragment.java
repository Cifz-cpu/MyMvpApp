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

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import wz.cifz.zx.R;
import wz.cifz.zx.adapter.MyNewsAdapter;
import wz.cifz.zx.injecct.moudle.PeoModule;
import wz.cifz.zx.moudle.bean.NewsList;
import wz.cifz.zx.ui.fragment.IView.IFragmentView;
import wz.cifz.zx.ui.fragment.component.DaggerPeoComponent;
import wz.cifz.zx.ui.fragment.presenter.PrePeoNewsList;
import wz.cifz.zx.ui.view.NewsDetailActivity;
import wz.cifz.zx.ui.view.PicDetailActivity;
import wz.cifz.zx.utils.ToastUtils;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/23.
 */

public class PeoLiveFragment extends BaseFragment implements IFragmentView, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    PrePeoNewsList preLoadNewsList;
    @BindView(R.id.frag_rl)
    RecyclerView amuseFragmeng;
    @BindView(R.id.amuse_swipe)
    SwipeRefreshLayout amuseSwipe;
    private int currentPage = 1;
    MyNewsAdapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_peo, null);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        load();
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void load() {
        preLoadNewsList.loadNewsList(1, currentPage);
    }

    @Override
    public void initView() {
        DaggerPeoComponent
                .builder()
                .peoModule(new PeoModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setData(final NewsList list) {

        amuseSwipe.setColorSchemeColors(Color.rgb(47, 223, 189));
        amuseSwipe.setOnRefreshListener(this);

        amuseFragmeng.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MyNewsAdapter(R.layout.news_item, list.getTngou());
        amuseFragmeng.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                ARouter.getInstance().build("/act/newsdetail").withLong("newsId",list.getTngou().get(position).getId()).navigation();
                Intent intent = new Intent();
                intent.setClass(getActivity(), NewsDetailActivity.class);
                intent.putExtra("newsId", list.getTngou().get(position).getId());
                getActivity().startActivity(intent);
                ToastUtils.showToast("" + position);
            }
        });
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                currentPage++;
                preLoadNewsList.loadMoreData(1, currentPage);
            }
        }, amuseFragmeng);
        amuseSwipe.setRefreshing(false);
    }


    @Override
    public void LoadMore(NewsList newsList) {
        adapter.addData(newsList.getTngou());
        adapter.loadMoreComplete();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onRefresh() {
        currentPage = 1;
        preLoadNewsList.loadNewsList(1, currentPage);
    }
}
