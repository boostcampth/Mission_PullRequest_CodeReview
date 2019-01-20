package com.example.yeseul.movieapp.view.main;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yeseul.movieapp.R;
import com.example.yeseul.movieapp.databinding.HolderMovieItemBinding;
import com.example.yeseul.movieapp.pojo.Movie;
import com.example.yeseul.movieapp.view.adapter.BaseRecyclerAdapter;

public class MovieListAdapter extends BaseRecyclerAdapter<Movie, MovieListAdapter.ViewHolder> {

    public MovieListAdapter(Context context) {
        super(context);
    }

    @Override
    protected void onBindView(ViewHolder holder, int position) {
        // ViewHolder  에서 사용할 item 을 binding 해준다.
        holder.binding.setItem(itemList.get(position));
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        // ViewHolder 바인딩
        View view = LayoutInflater.from(context).inflate(R.layout.holder_movie_item, parent, false);
        return new ViewHolder(view);
    }

    // static 의미 : nested class 로 상위 클래스의 메소드, 변수를 사용하지 않겠다고 명시
    // non-static 인 경우엔 상위 클래스의 reference 를 가지고 있음 -> Adapter 가 죽어도 ViewHolder 에서 Adapter 를 가지고 있는 경우 memory leak 발생
    // adapter 가 한 곳에서만 사용된다면 non-static 도 상관없다.
    public static class ViewHolder extends RecyclerView.ViewHolder{

        // binding 만 하고 onBindView 할 때 item 을 설정해주면
        // RXJava 를 이용해서 자동적으로 ViewHolder 의 값이 변경되게 함
        HolderMovieItemBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
