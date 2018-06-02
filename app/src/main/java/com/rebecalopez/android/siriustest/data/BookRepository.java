package com.rebecalopez.android.siriustest.data;

import com.rebecalopez.android.siriustest.data.entities.BookInfo;

import java.util.List;

import io.reactivex.Single;

public class BookRepository {

    private BookService bookService;

    public BookRepository() {
    }

    public Single<BookInfo> getBookInfo(String book){
        bookService = new BookService.Factory().create();

        return bookService.getForecast(book/*,"{YOUR_API_KEY}"*/);
    }
}
