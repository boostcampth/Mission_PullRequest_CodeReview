package com.example.yeseul.movieapp.view.main;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.yeseul.movieapp.BR;
import com.example.yeseul.movieapp.R;
import com.example.yeseul.movieapp.RecentSearchWordVM;
import com.example.yeseul.movieapp.data.model.RecentSearchWord;
import com.example.yeseul.movieapp.databinding.SearchWordListItemBinding;

import java.util.ArrayList;

public class RecentSearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<RecentSearchWord> recentSearchWords = new ArrayList<>();
    private Context mContext = null;
    private EditText etSearch = null;

    public RecentSearchAdapter(ArrayList<RecentSearchWord> recentSearchWords, Context mContext, EditText etSearch) {
        this.recentSearchWords = recentSearchWords;
        this.mContext = mContext;
        this.etSearch = etSearch;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        SearchWordListItemBinding searchWordListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.search_word_list_item, viewGroup, false);
        return new RecentSearchWordHolder(searchWordListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        RecentSearchWordHolder recentSearchWordHolder = (RecentSearchWordHolder)viewHolder;
        recentSearchWordHolder.searchWordListItemBinding.setVariable(BR.vm_recent_search_word, new RecentSearchWordVM(recentSearchWords.get(position)));
        recentSearchWordHolder.searchWordListItemBinding.itemLL.setOnClickListener(view -> {
            Log.d("position",String.valueOf(viewHolder.getAdapterPosition()));
            etSearch.setText("");
            etSearch.setText(recentSearchWords.get(viewHolder.getAdapterPosition()).getMovieName());
            RecentSearchWord recentSearchWord = recentSearchWords.get(viewHolder.getAdapterPosition());
            RecentSearchWord tempSearchWord = recentSearchWords.get(0);
            recentSearchWords.set(0, recentSearchWord);
            recentSearchWords.set(viewHolder.getAdapterPosition(), tempSearchWord);
            notifyItemChanged(0);
            notifyItemChanged(viewHolder.getAdapterPosition());
        });
    }

    @Override
    public int getItemCount() {
        if(recentSearchWords.size()>5){
            return 5;
        }
        return recentSearchWords.size();
    }

    class RecentSearchWordHolder extends RecyclerView.ViewHolder{
        private SearchWordListItemBinding searchWordListItemBinding = null;

        RecentSearchWordHolder(SearchWordListItemBinding searchWordListItemBinding) {
            super(searchWordListItemBinding.getRoot().getRootView());
            this.searchWordListItemBinding = searchWordListItemBinding;
        }

        SearchWordListItemBinding getSearchWordListItemBinding() {
            return searchWordListItemBinding;
        }
    }
}
