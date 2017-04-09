package com.example.oromil.kilogrammtestproject.realm;

import io.realm.RealmObject;

/**
 * Created by Oromil on 05.04.2017.
 */

public class Song extends RealmObject{

    private String label;
    private String author;
    private Integer version;
    private Integer id;

    public Song(Integer id, String label, String author, Integer version){
        this.id = id;
        this.version = version;
        this.label = label;
        this.author = author;
    }

    public Song(){}

    public Integer getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    public String getAuthor() {
        return author;
    }

    public String getLabel() {
        return label;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
