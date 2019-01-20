package com.example.yeseul.movieapp.view.adapter;

import java.util.List;

/*
 * Google Architecture 에서 추천한 Adapter Interface 구조 */
public interface AdapterContract {

    /* Adapter 가 달린 View 에서 발생하는 이벤트들만 가짐 */
    interface View {

        void setOnItemClickListener(OnItemClickListener itemClickListener);
    }

    /* Adapter 가 가진 Data 를 조작하는 함수들만 가짐 */
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
