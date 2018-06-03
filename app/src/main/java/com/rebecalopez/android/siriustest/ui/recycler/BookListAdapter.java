package com.rebecalopez.android.siriustest.ui.recycler;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
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

public class BookListAdapter extends ListAdapter<Item, BookListAdapter.ViewHolder> {


    public BookListAdapter(@NonNull DiffUtil.ItemCallback<Item> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitle;
        TextView txtAuthor;
        ImageView imgvBook;

        public ViewHolder(View itemView) {
            super(itemView);


            txtTitle = itemView.findViewById(R.id.txt_title);
            txtAuthor = itemView.findViewById(R.id.txt_author);
            imgvBook = itemView.findViewById(R.id.imgv_book);
        }

        public void bind(Item item) {
            if(item.getVolumeInfo() != null) {
                if(item.getVolumeInfo().getTitle() != null)
                    txtTitle.setText(item.getVolumeInfo().getTitle());

                StringBuilder stringBuilder = new StringBuilder(itemView.getContext().getString(R.string.lbl_author));
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
                    Log.d("BookListAdapter", "No image for this item");
                }
            }
        }
    }
}
