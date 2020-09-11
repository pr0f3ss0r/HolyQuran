package com.tosindroid.pr0f.holyquran;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FavoriteFragmentAdapter extends RecyclerView.Adapter<FavoriteFragmentAdapter.ViewHolder> {

    private FavoriteFragment ctx;
    private List<Bookmarks> bookmarksList;
    //private FavDB favDB;

    public FavoriteFragmentAdapter(FavoriteFragment context, List<Bookmarks> bookmarksList){
        this.ctx = context;
        this.bookmarksList = bookmarksList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteFragmentAdapter.ViewHolder holder, int position) {
        holder.surahNameTextView.setText(bookmarksList.get(position).getSurah());
        holder.pageNumberTextView.setText(bookmarksList.get(position).getPageNumber());
        holder.pageNumber2TextView.setText(bookmarksList.get(position).getPageNumber());
        holder.juzNumberTextView.setText(bookmarksList.get(position).getJuzNumber());

    }

    @Override
    public int getItemCount() {
        return bookmarksList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView surahNameTextView;
        TextView pageNumberTextView;
        TextView pageNumber2TextView;
        TextView juzNumberTextView;
        LinearLayout favLinearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            surahNameTextView = itemView.findViewById(R.id.surahNameTextView);
            pageNumberTextView = itemView.findViewById(R.id.pageNumberTextView);
            pageNumber2TextView = itemView.findViewById(R.id.pageNumber2TextView);
            juzNumberTextView = itemView.findViewById(R.id.juzNumberTextView);
            favLinearLayout = itemView.findViewById(R.id.favLinearLayout);
        }
    }
}
