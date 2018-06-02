package com.rebecalopez.android.siriustest;

import android.app.Application;

import com.rebecalopez.android.siriustest.di.BookModule;
import com.rebecalopez.android.siriustest.di.DaggerBookComponent;
import com.rebecalopez.android.siriustest.di.BookComponent;

public class SiriusApp extends Application {
    private BookComponent bookComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        bookComponent = DaggerBookComponent.builder()
                .bookModule(new BookModule())
                .build();
    }

    public BookComponent getBookComponent() {
        return bookComponent;
    }
}
