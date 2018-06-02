package com.rebecalopez.android.siriustest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.rebecalopez.android.siriustest.data.BookAdapter;
import com.rebecalopez.android.siriustest.data.BookRepository;
import com.rebecalopez.android.siriustest.data.entities.Item;
import com.rebecalopez.android.siriustest.ui.BookContract;
import com.rebecalopez.android.siriustest.ui.BookPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BookContract.View{
    private static final String TAG = MainActivity.class.getName();

    BookPresenter bookPresenter;
    BookRepository bookRepository;
    EditText edtSearch;
    ImageButton btnSearch;
    RecyclerView rvBooks;

    List<Item> booksList = new ArrayList<>();
    BookAdapter bookAdapter = new BookAdapter(booksList);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtSearch = findViewById(R.id.edt_search);
        btnSearch = findViewById(R.id.btn_search);
        rvBooks   = findViewById(R.id.rv_books);

        rvBooks.setLayoutManager(new LinearLayoutManager(this));
        rvBooks.setHasFixedSize(true);
        rvBooks.setAdapter(bookAdapter);

        bookRepository = new BookRepository();
        bookPresenter = new BookPresenter(bookRepository);

        bookPresenter.attachView(this);

        btnSearch.setOnClickListener(v -> bookPresenter.loadData(/*"Harry"*/edtSearch.getText().toString()));

    }

    @Override
    public void showResults(List<Item> bookItem) {
        Log.d(TAG, "showResults;"+bookItem);
        booksList.clear();

        if(bookItem != null && bookItem.size()>0)
            booksList.addAll(bookItem);
        else
            booksList = new ArrayList<>();

        bookAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {
        booksList.clear();
        bookAdapter.notifyDataSetChanged();

        Toast.makeText(this,"No information, try later or with a different search",Toast.LENGTH_SHORT).show();
        Log.d(TAG,"Error:"+error);
    }
}
