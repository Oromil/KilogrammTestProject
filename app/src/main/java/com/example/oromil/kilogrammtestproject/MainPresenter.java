package com.example.oromil.kilogrammtestproject;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.oromil.kilogrammtestproject.network.ServerManager;
import com.example.oromil.kilogrammtestproject.network.model.MyResponse;
import com.example.oromil.kilogrammtestproject.realm.Song;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.realm.Realm;
import io.realm.RealmCollection;
import io.realm.RealmConfiguration;
import rx.Subscriber;

/**
 * Created by Oromil on 05.04.2017.
 */

public class MainPresenter {

    private ServerManager serverManager;
    private MainActivity activity;
    private Realm realm;
    private ArrayList<Song> songs;

    MainPresenter(Context context){
        serverManager = new ServerManager();
        activity = (MainActivity) context;
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(context)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfig);
        realm = Realm.getInstance(realmConfig);
        songs = new ArrayList<>();
    }

    public void updateData(){

        serverManager.getData(new Subscriber<List<MyResponse>>() {
            @Override
            public void onCompleted() {
                Log.d("Request", "Complete");
                activity.showLoading(false);
                Toast.makeText(activity, "Data successfully updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Request", "Error");
                Log.d("onError: ", e+"");
                getFromRealm();
                    activity.updateAdapter(songs);

                activity.showLoading(false);
                Toast.makeText(activity, "Could not update data\n" +
                        "Check network connection", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNext(List<MyResponse> myResponses) {
                Log.d("Request", "Start");
                Log.d("RX:", myResponses.get(0).getLabel());
                addSongsToList(myResponses);
                activity.updateAdapter(songs);
                saveInRealm(myResponses);
            }
        });
    }

    private void saveInRealm(List<MyResponse>myResponses){

        addSongsToList(myResponses);
        realm.executeTransaction(realm1 -> {
            realm.where(Song.class).findAll().deleteAllFromRealm();
            realm1.insertOrUpdate(songs);
            Log.d("Transaction", "sucsess");
        });
    }

    private void getFromRealm(){
        Object[] list = realm.where(Song.class).findAll().toArray();
        songs.clear();
        for (Object song:list) {
            Song s = (Song) song;
            songs.add(s);
        }
    }

    public void addSongsToList(List<MyResponse>myResponses){
        songs.clear();

        for (MyResponse response:myResponses) {
            songs.add(new Song(response.getId(),response.getLabel(),
                    response.getAuthor(),response.getVersion()));
        }
    }
}
