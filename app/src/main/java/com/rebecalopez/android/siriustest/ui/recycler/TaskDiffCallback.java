package com.rebecalopez.android.siriustest.ui.recycler;

import android.support.v7.util.DiffUtil;

import com.rebecalopez.android.siriustest.data.entities.Item;

public class TaskDiffCallback extends DiffUtil.ItemCallback<Item> {

    @Override
    public boolean areItemsTheSame(Item oldItem, Item newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(Item oldItem, Item newItem) {
        return oldItem == newItem;
    }
}
