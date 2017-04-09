package com.example.oromil.kilogrammtestproject;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.oromil.kilogrammtestproject.adapter.MyAdapter;
import com.example.oromil.kilogrammtestproject.realm.Song;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainPresenter presenter;
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private SwipeRefreshLayout swipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipe = (SwipeRefreshLayout) findViewById(R.id.refreshLayout);
        presenter = new MainPresenter(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2){
            @Override
            public boolean supportsPredictiveItemAnimations() {
                return true;
            }
        };

        recyclerView.setLayoutManager(mLayoutManager);

        presenter.updateData();

        swipe.setOnRefreshListener(() -> {

            swipe.setRefreshing(true);
            presenter.updateData();
        });

    }

    public void updateAdapter(List<Song> list){
        if (recyclerView.getAdapter()==null){
            Log.d("Adapter: ", "Adapter is null");
            adapter = new MyAdapter(list, this);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setSongs(list);
            adapter.notifyDataSetChanged();
            Log.d("Adapter: ", "Adapter updated");
        }
    }

    public void updateAdapter(Object[] list){
        ArrayList<Song>songList = new ArrayList<>();
        for (Object song:list) {
            Song s = (Song) song;
            songList.add(s);
        }
        Log.d("SongInRealm", songList.size()+"");
        if (recyclerView.getAdapter()==null){
            adapter = new MyAdapter(songList, this);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setSongs(songList);
            adapter.notifyDataSetChanged();
            Log.d("Adapter: ", "Adapter updated");
        }
    }

    public void showLoading(boolean show){
        swipe.setRefreshing(show);
    }

}
