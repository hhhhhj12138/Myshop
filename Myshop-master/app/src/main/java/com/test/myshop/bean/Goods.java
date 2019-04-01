package com.test.myshop.bean;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Goods implements Serializable {
    private String name;
    private Bitmap[] bitmaps;
    private int spinner;
    private float price;
    private String phonum;
    private String notes;

    public Goods(String name, Bitmap[] bitmaps, int spinner, float price, String phonum, String notes) {
        this.name = name;
        for (int i = 0; i < 3; i++) {
            this.bitmaps[i] = bitmaps[i];
        }
        this.spinner = spinner;
        this.price = price;
        this.phonum = phonum;
        this.notes = notes;
    }


    public String getName() {
        return name;
    }

    public Bitmap[] getBitmaps() {
        return bitmaps;
    }

    public int getSpinner() {
        return spinner;
    }

    public float getPrice() {
        return price;
    }

    public String getNotes() {
        return notes;
    }

    public String getPhonum() {
        return phonum;
    }
}
