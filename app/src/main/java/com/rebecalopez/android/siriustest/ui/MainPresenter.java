package com.rebecalopez.android.siriustest.ui;

import com.rebecalopez.android.siriustest.data.BookRepository;
import com.rebecalopez.android.siriustest.data.entities.BookInfo;
import com.rebecalopez.android.siriustest.di.scopes.ActivityScope;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@ActivityScope
public class MainPresenter implements MainContract.Presenter {

    private BookRepository bookRepository;
    private MainContract.View view;


    @Inject
    public MainPresenter(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public void loadData(String bookTitle) {
      //  Log.d("MainPresenter","loadData");

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
    public void attachView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
