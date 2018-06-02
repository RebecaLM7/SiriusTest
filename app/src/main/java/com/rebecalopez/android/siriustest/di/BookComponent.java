package com.rebecalopez.android.siriustest.di;

import com.rebecalopez.android.siriustest.data.BookRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = BookModule.class)
public interface BookComponent {
    BookRepository bookRepository();
}
