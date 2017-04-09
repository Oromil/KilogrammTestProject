package com.example.oromil.kilogrammtestproject.network.model;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oromil on 05.04.2017.
 */

public class MyResponse {

    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("version")
    @Expose
    private Integer version;
    @SerializedName("id")
    @Expose
    private Integer id;

    public String getLabel(){return label;}
    public String getAuthor(){return author;}
    public Integer getVersion(){return version;}
    public Integer getId(){return id;}

}
