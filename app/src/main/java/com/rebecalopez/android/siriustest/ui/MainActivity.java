package com.rebecalopez.android.siriustest.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.rebecalopez.android.siriustest.R;
import com.rebecalopez.android.siriustest.SiriusApp;
import com.rebecalopez.android.siriustest.ui.recycler.BookListAdapter;
import com.rebecalopez.android.siriustest.ui.recycler.TaskDiffCallback;
import com.rebecalopez.android.siriustest.data.entities.Item;
import com.rebecalopez.android.siriustest.di.BookComponent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainContract.View{

    private static final String TAG = MainActivity.class.getName();

    @Inject
     MainPresenter mainPresenter;

    private EditText edtSearch;

    private List<Item> booksList = new ArrayList<>();
    private BookListAdapter bookAdapter = new BookListAdapter(new TaskDiffCallback());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtSearch = findViewById(R.id.edt_search);
        ImageButton btnSearch = findViewById(R.id.btn_search);
        RecyclerView rvBooks = findViewById(R.id.rv_books);

        rvBooks.setLayoutManager(new LinearLayoutManager(this));
        rvBooks.setHasFixedSize(true);
        rvBooks.setAdapter(bookAdapter);

        injectDependencies();

        mainPresenter.attachView(this);
        btnSearch.setOnClickListener(v -> mainPresenter.loadData(edtSearch.getText().toString()));

    }

    private void injectDependencies() {

        BookComponent bookComponent = ((SiriusApp) getApplication()).getBookComponent();

        DaggerMainComponent.builder()
                .bookComponent(bookComponent)
                .build()
                .inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.detachView();
    }

    @Override
    public void showResults(List<Item> bookItem) {
       // Log.d(TAG, "showResults;"+bookItem);

        bookAdapter.submitList(bookItem);
    }

    @Override
    public void showError(String error) {
        booksList.clear();
        bookAdapter.submitList(booksList);

        Toast.makeText(this,this.getString(R.string.lbl_toast_error),Toast.LENGTH_SHORT).show();
        Log.d(TAG,"Error:"+error);
    }
}
