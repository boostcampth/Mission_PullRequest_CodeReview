package com.example.yeseul.movieapp.view.adapter;

import java.util.List;

public interface AdapterContract {

    interface View {

        void setOnItemClickListener(OnItemClickListener itemClickListener);

        void setOnItemLongClickListener(OnItemLongClickListener itemLongClickListener);
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
