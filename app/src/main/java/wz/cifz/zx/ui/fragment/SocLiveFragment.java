package wz.cifz.zx.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wz.cifz.zx.R;
import wz.cifz.zx.adapter.MyNewsAdapter;
import wz.cifz.zx.injecct.moudle.SocModule;
import wz.cifz.zx.moudle.bean.NewsList;
import wz.cifz.zx.ui.fragment.IView.IFragmentView;
import wz.cifz.zx.ui.fragment.component.DaggerSocComponent;
import wz.cifz.zx.ui.fragment.presenter.PreSoc;
import wz.cifz.zx.utils.ToastUtils;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/23.
 */

public class SocLiveFragment extends BaseFragment implements IFragmentView, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    PreSoc preSoc;
    @BindView(R.id.soc_rl)
    RecyclerView socRl;
    @BindView(R.id.soc_swipe)
    SwipeRefreshLayout socSwipe;
    private int currentPage = 1;
    MyNewsAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_soc, null);
        ButterKnife.bind(this, view);
        initView();
        load();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void LoadMore(NewsList newsList) {
        adapter.addData(newsList.getTngou());
        adapter.loadMoreComplete();
    }

    @Override
    public void load() {
        preSoc.loadNewsList(6, currentPage);
    }

    @Override
    public void initView() {
        DaggerSocComponent
                .builder()
                .socModule(new SocModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setData(NewsList list) {
        socSwipe.setColorSchemeColors(Color.rgb(47, 223, 189));
        socSwipe.setOnRefreshListener(this);

        socRl.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MyNewsAdapter(R.layout.news_item,list.getTngou());
        socRl.setAdapter(adapter);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showToast("position+"+position);
            }
        });

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                currentPage ++;
                preSoc.loadMoreData(6,currentPage);
            }
        },socRl);

        if(socSwipe.isRefreshing()){
            socSwipe.setRefreshing(false);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onRefresh() {
        currentPage = 1;
        preSoc.loadNewsList(6,currentPage);
    }
}
