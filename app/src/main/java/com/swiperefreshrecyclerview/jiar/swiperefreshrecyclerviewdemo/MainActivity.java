package com.swiperefreshrecyclerview.jiar.swiperefreshrecyclerviewdemo;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.swiperefreshrecyclerview.jiar.swiperefreshrecyclerviewdemo.adapter.PersonInfoAdapter;
import com.swiperefreshrecyclerview.jiar.swiperefreshrecyclerviewdemo.model.PersonInfo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private PersonInfoAdapter personInfoAdapter;
    private List<PersonInfo> personInfoList;

    /*********** 分页请求数据 ***********/
    private int pageIndex = 0;
    private final int pageSize = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        personInfoList = new ArrayList<>();
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.theme);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        personInfoAdapter = new PersonInfoAdapter(this, personInfoList);
        personInfoAdapter.setOnClickListener(new PersonInfoAdapter.OnClickListener() {
            @Override
            public void getPersonInfoItem(int position) {
                PersonInfo personInfo = personInfoList.get(position);
                Toast.makeText(MainActivity.this, personInfo.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(personInfoAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageIndex = 0;
                getPersonInfos();
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                if ((MainActivity.this.personInfoList.size() == pageSize*(pageIndex+1)) && (lastVisibleItemPosition == MainActivity.this.personInfoList.size() - 1)) {
                    pageIndex++;
                    getPersonInfos();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void getPersonInfos() {
        if(pageIndex == 0) {
            personInfoList.clear();
        }

        /*********** 模拟网络请求 start ***********/
        swipeRefreshLayout.setRefreshing(true);
        int count = personInfoList.size();
        for (int i = count; i < count+pageSize; i++) {
            PersonInfo personInfo = new PersonInfo();
            personInfo.setFirstName(personInfo.getFirstName()+" "+i);
            personInfoList.add(personInfo);
        }
        swipeRefreshLayout.setRefreshing(false);
        /*********** 模拟网络请求 end ***********/

        personInfoAdapter.notifyDataSetChanged();
    }
}
