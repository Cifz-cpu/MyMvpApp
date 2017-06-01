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
import wz.cifz.zx.R;
import wz.cifz.zx.adapter.MyNewsAdapter;
import wz.cifz.zx.injecct.moudle.SportModule;
import wz.cifz.zx.moudle.bean.NewsList;
import wz.cifz.zx.ui.fragment.IView.IFragmentView;
import wz.cifz.zx.ui.fragment.component.DaggerSportComponent;
import wz.cifz.zx.ui.fragment.presenter.PreSport;
import wz.cifz.zx.utils.ToastUtils;

/**
 * desc
 * author cifz
 * e_mail wangzhen1798@gmail.com
 * Created 2017/5/23.
 */

public class SportLiveFragment extends BaseFragment implements IFragmentView, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    PreSport preSport;
    @BindView(R.id.sport_rl)
    RecyclerView sportRl;
    @BindView(R.id.sport_swipe)
    SwipeRefreshLayout sportSwipe;

    private int currentPage = 1;

    MyNewsAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_sport, null);
        initView();
        load();
        ButterKnife.bind(this, view);
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
        preSport.loadNewsList(4, currentPage);
    }

    @Override
    public void initView() {
        DaggerSportComponent
                .builder()
                .sportModule(new SportModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void setData(NewsList list) {
        sportSwipe.setColorSchemeColors(Color.rgb(47, 223, 189));
        sportSwipe.setOnRefreshListener(this);

        sportRl.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MyNewsAdapter(R.layout.news_item,list.getTngou());
        sportRl.setAdapter(adapter);
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
                preSport.loadMoreData(4,currentPage);
            }
        },sportRl);

        if(sportSwipe.isRefreshing()){
            sportSwipe.setRefreshing(false);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onRefresh() {
        currentPage = 1;
        preSport.loadNewsList(4,currentPage);
    }
}
