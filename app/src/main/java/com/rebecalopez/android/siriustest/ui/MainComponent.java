package com.rebecalopez.android.siriustest.ui;

import com.rebecalopez.android.siriustest.di.BookComponent;
import com.rebecalopez.android.siriustest.di.scopes.PerView;

import dagger.Component;

@PerView
@Component (dependencies = BookComponent.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
