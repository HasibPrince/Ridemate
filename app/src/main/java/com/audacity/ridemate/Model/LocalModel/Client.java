package com.audacity.ridemate.Model.LocalModel;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.audacity.ridemate.Model.RemoteModel.Tag;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Prince on 6/4/17.
 */
@Table(name = "client")
public class Client extends Model {

    @Column(name = "name")
    public String name;
    @Column(name = "logo")
    public String logo;
    @Column(name = "company")
    public String company;
    @Column(name = "country")
    public String country;

    public Client(){
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public static List<Client> getClients(){
        return new Select()
                .from(Client.class)
                .execute();
    }

    public static void clear(){
        new Delete().from(Client.class).execute();
    }
}
