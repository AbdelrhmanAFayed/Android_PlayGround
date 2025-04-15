package com.example.productviewer;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("thumbnail")
    public String imgURL ;

    public String title ;
    public Double price ;

    @SerializedName("description")
    public String desc ;
    @SerializedName("rating")
    public Double Rating ;

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Double getRating() {
        return Rating;
    }

    public void setRating(Double rating) {
        Rating = rating;
    }
}
