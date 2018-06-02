package com.rebecalopez.android.siriustest.di;

import com.rebecalopez.android.siriustest.data.BookService;

import dagger.Module;
import dagger.Provides;

@Module
public class BookModule {

    @Provides
    public BookService provideBookService(){return new BookService.Factory().create();}
}
