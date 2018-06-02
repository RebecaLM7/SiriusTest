package com.rebecalopez.android.siriustest.data;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rebecalopez.android.siriustest.R;
import com.rebecalopez.android.siriustest.data.entities.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder>{

    private List<Item> booksList;

    public BookAdapter(List<Item> booksList) {
        this.booksList = booksList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        holder.bind(booksList.get(position));
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitle;
        TextView txtAuthor;
        ImageView imgvBook;

        ViewHolder(View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txt_title);
            txtAuthor = itemView.findViewById(R.id.txt_author);
            imgvBook = itemView.findViewById(R.id.imgv_book);
        }

        void bind(Item item) {
            if(item.getVolumeInfo() != null) {
                txtTitle.setText("Title: ");
                if(item.getVolumeInfo().getTitle() != null)
                    txtTitle.append(item.getVolumeInfo().getTitle());

                StringBuilder stringBuilder = new StringBuilder("Author: ");
                if (item.getVolumeInfo().getAuthors() != null) {
                    for (String author : item.getVolumeInfo().getAuthors()) {
                        stringBuilder.append(author + " ,");
                    }
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                txtAuthor.setText(stringBuilder.toString());

                try {
                    Picasso.get().load(item.getVolumeInfo().getImageLinks().getSmallThumbnail()).into(imgvBook);
                } catch (Exception e) {
                    Log.d("BookAdapter", "No image for this item");
                }
            }
        }
    }
}
