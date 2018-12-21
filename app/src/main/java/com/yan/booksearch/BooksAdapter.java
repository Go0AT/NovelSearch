package com.yan.booksearch;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolder> {
    private List<Books> mBookList;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from( viewGroup.getContext()).inflate(R.layout.books_item, viewGroup,false);
            ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Books books = mBookList.get(i);
        viewHolder.booksName.setText(books.getName());
    }

    @Override
    public int getItemCount() {
        return mBookList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView booksName;

        public ViewHolder(View view){
            super(view);
            booksName = (TextView) view.findViewById(R.id.books_title);
        }
    }

    public BooksAdapter(List<Books> booksList){
        mBookList = booksList;
    }
}
