package com.example.yeseul.movieapp.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;

import com.example.yeseul.movieapp.utils.POJODiffUtil;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerAdapter<T, H extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements AdapterContract.View, AdapterContract.Model<T> {

    protected List<T> itemList;
    protected OnItemClickListener onItemClickListener;
    protected Context context;

    public BaseRecyclerAdapter(Context context) {
        this.context = context;
    }

    public BaseRecyclerAdapter(Context context, List<T> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (this.itemList == null) {
            return 0;
        }

        return this.itemList.size();
    }

    private void diffUtilUpdate(List<T> oldList, List<T> newList) {
        POJODiffUtil<T> diffUTilCallback = new POJODiffUtil<>(oldList, newList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUTilCallback);

        this.itemList.clear();
        this.itemList.addAll(newList);

        diffResult.dispatchUpdatesTo(this);
    }

    /**
     * 해당 position 의 item 반환
     */
    @Override
    public T getItem(int position) {
        if (this.itemList == null) {
            return null;
        }

        return this.itemList.get(position);
    }

    /**
     * 전체 item list 반환
     */
    @Override
    public List<T> getItemList() {
        if (this.itemList == null) {
            return null;
        }

        return this.itemList;
    }

    /**
     * item list 전체 수정
     */
    public void updateItems(List<T> items) {
        diffUtilUpdate(this.itemList, items);
    }

    /**
     * 해당 position 의 item 수정
     */
    public void updateItem(int position, T item) {
        if (this.itemList == null) {
            return;
        }
        if (position > this.itemList.size()) {
            return;
        }
        List<T> newItemsList = new ArrayList<>(itemList);
        newItemsList.remove(position);
        newItemsList.add(position, item);

        diffUtilUpdate(this.itemList, newItemsList);
    }

    /**
     * 맨 처음 item list 초기화 또는 ,
     * item list 마지막 position 뒤에 추가
     */
    @Override
    public void addItems(List<T> items) {
        if (this.itemList == null) {
            this.itemList = items;
            notifyDataSetChanged();
        } else {
            List<T> newItemList = new ArrayList<>(this.itemList);
            newItemList.addAll(items);
            diffUtilUpdate(this.itemList, newItemList);
        }
    }

    /**
     * position 위치에 items 추가
     */
    public void addItems(int position, List<T> items) {
        if (this.itemList == null) {
            this.itemList = new ArrayList<>();
        }
        if (position > this.itemList.size()) {
            return;
        }
        List<T> newItemList = new ArrayList<>(this.itemList);
        newItemList.addAll(position, items);

        diffUtilUpdate(this.itemList, newItemList);
    }

    /**
     * item list 마지막 position 뒤에 item 추가
     */
    @Override
    public void addItem(T item) {
        if (this.itemList == null) {
            this.itemList = new ArrayList<>();
            itemList.add(item);
            notifyDataSetChanged();
        } else {
            List<T> newItemList = new ArrayList<>(this.itemList);
            newItemList.add(item);

            diffUtilUpdate(this.itemList, newItemList);
        }
    }

    /**
     * position 위치에 item 추가
     */
    public void addItem(int position, T item) {
        if (this.itemList == null) {
            return;
        }
        if (position > this.itemList.size()) {
            return;
        }
        List<T> newItemList = new ArrayList<>(this.itemList);
        newItemList.add(position, item);

        diffUtilUpdate(this.itemList, newItemList);
    }

    /**
     * item list 전체 삭제
     */
    @Override
    public void clearItems() {
        if (itemList != null) {
            itemList.clear();
            notifyDataSetChanged();
        }
    }

    /**
     * position 위치의 item 삭제
     */
    @Override
    public void removeItem(int position) {
        if (this.itemList != null && position < this.itemList.size()) {
            List<T> newItemList = new ArrayList<>(this.itemList);
            newItemList.remove(position);

            diffUtilUpdate(this.itemList, newItemList);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        holder.itemView.setOnClickListener(view -> {

            // item click listener 등록
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }

        });

        onBindView((H) holder, position);
    }

    protected abstract void onBindView(H holder, int position);
}

