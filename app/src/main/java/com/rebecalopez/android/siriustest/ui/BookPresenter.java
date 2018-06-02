package com.rebecalopez.android.siriustest.ui;

import android.util.Log;

import com.rebecalopez.android.siriustest.data.BookRepository;
import com.rebecalopez.android.siriustest.data.entities.BookInfo;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BookPresenter implements BookContract.Presenter {

    private BookRepository bookRepository;
    private BookContract.View view;


    public BookPresenter(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public void loadData(String bookTitle) {
        Log.d("BookPresenter","loadData");

        final Disposable disposable = bookRepository.getBookInfo(bookTitle)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onError);
    }

    private void onError(Throwable throwable) {
        view.showError(throwable.toString());
    }

    private void onSuccess(BookInfo bookInfo) {
        view.showResults(bookInfo.getItems());
    }

    @Override
    public void attachView(BookContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
