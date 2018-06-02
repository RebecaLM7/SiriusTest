package com.rebecalopez.android.siriustest.ui;

import com.rebecalopez.android.siriustest.data.entities.Item;

import java.util.List;

public interface MainContract {
    interface View {
        void showResults(List<Item> bookItem);

        void showError(String error);
    }

    interface Presenter {
        void loadData(String bookTitle);

        void attachView(View view);

        void detachView();
    }
}
