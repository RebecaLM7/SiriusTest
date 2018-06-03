package com.rebecalopez.android.siriustest.ui;

import android.arch.lifecycle.ViewModel;

import com.rebecalopez.android.siriustest.data.entities.Item;

import java.util.ArrayList;
import java.util.List;

public class BooksViewModel extends ViewModel{
    private List<Item> itemsList = new ArrayList<>();

    public void setItemsList(List<Item> itemsList) {
        this.itemsList = itemsList;
    }

    public List<Item> getItemsList() {
        return itemsList;
    }
}
