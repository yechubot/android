package com.example.customlistview;

public class ItemData {
    private int imgId;
    private String title;
    private String sub;

    public ItemData(int imgId, String title, String sub) {
        this.imgId = imgId;
        this.title = title;
        this.sub = sub;
    }

    public int getImgId() {
        return imgId;
    }

    public String getTitle() {
        return title;
    }

    public String getSub() {
        return sub;
    }
}
