package com.example.bluegate.object;

import java.sql.Blob;
import java.util.List;

public class Pet {
    byte[] profile_img;
    String name;
    List<byte[]> img;

    public Pet(byte[] profile_img, String name, List<byte[]> img) {
        this.profile_img = profile_img;
        this.name = name;
        this.img = img;
    }

    //get
    public byte[] getProfile_img() {
        return profile_img;
    }

    public String getName() {
        return name;
    }

    public List<byte[]> getImg() {
        return img;
    }

    //set
    public void setProfile_img(byte[] profile_img) {
        this.profile_img = profile_img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(List<byte[]> img) {
        this.img = img;
    }
}
