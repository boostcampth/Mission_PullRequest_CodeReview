package com.example.yeseul.movieapp.view.adapter;

import android.widget.AdapterView;

import java.util.List;

public interface AdapterContract {

    interface View {
        void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener);
        void setOnItemClickListener(OnItemClickListener itemClickListener);
    }

    interface Model<T> {

        T getItem(int position);

        List<T> getItemList();

        int getItemCount();

        void addItems(List<T> items);

        void addItem(T item);

        void removeItem(int position);

        void updateItems(List<T> items);

        void clearItems();
    }
}
