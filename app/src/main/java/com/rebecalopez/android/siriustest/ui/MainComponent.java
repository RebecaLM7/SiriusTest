package com.rebecalopez.android.siriustest.ui;

import com.rebecalopez.android.siriustest.di.BookComponent;
import com.rebecalopez.android.siriustest.di.scopes.ActivityScope;

import dagger.Component;

@ActivityScope
@Component (dependencies = BookComponent.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
