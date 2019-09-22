package com.cagataykolus.orderapp.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cagataykolus.orderapp.R;
import com.cagataykolus.orderapp.model.Order;
import com.cagataykolus.orderapp.ui.main.adapter.ExpandableRecyclerViewAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView.View {

    MainPresenter mainPresenter;
    Toolbar toolbar;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.page_title_orders));
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        setSupportActionBar(toolbar);

        mainPresenter = new MainPresenter();
        mainPresenter.attachView(this);
        mainPresenter.getOrders();

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.detachView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.toolbar_menu_exit:
                mainPresenter.doExit(100);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void ordersResponseReady(List<Order> orders) {
        initRecyclerView(orders);
    }

    private void initRecyclerView(List<Order> orders) {
        ExpandableRecyclerViewAdapter expandableCategoryRecyclerViewAdapter =
                new ExpandableRecyclerViewAdapter(getApplicationContext(), orders);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        recyclerView.setAdapter(expandableCategoryRecyclerViewAdapter);
    }
}
