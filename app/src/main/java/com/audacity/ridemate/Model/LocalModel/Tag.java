package com.audacity.ridemate.Model.LocalModel;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by Prince on 6/4/17.
 */
@Table(name = "Tag")
public class Tag extends Model {
    @Column (name = "company")
    private String company;
    @Column(name = "tag")
    private String tag;
    @Column(name = "url")
    private String url;

    public Tag(){
        super();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static List<Tag> getTagByCompany(String company){
        return new Select()
                .from(Tag.class)
                .where("comapany = ?", company)
                .execute();
    }
}
