package com.audacity.ridemate.Model.RemoteModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mdhasib on 6/4/17.
 */

public class Tag {
    @SerializedName("tag")
    private String tag;
    @SerializedName("url")
    private String url;

    public String getUrl() {
        return url;
    }

    public String getTag() {
        return tag;
    }

}
