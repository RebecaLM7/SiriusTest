package com.rebecalopez.android.siriustest.data;

import com.rebecalopez.android.siriustest.data.entities.BookInfo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class BookRepository {

    private BookService bookService;

    @Inject
    public BookRepository(BookService bookService){this.bookService = bookService;}

    public Single<BookInfo> getBookInfo(String book){return bookService.getForecast(book,40);
    }
}
