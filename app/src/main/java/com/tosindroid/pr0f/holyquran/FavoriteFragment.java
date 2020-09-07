package com.tosindroid.pr0f.holyquran;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {
    RecyclerView recyclerView;
    FavoriteFragmentAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    private ArrayList<Bookmarks> bookmarks;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.favorite_fragment, container, false);
        recyclerView = view.findViewById(R.id.rvFavorite);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new FavoriteFragmentAdapter(this, addfav());
        recyclerView.setAdapter(adapter);
        return view;


    }


    private ArrayList<Bookmarks> addfav(){
        bookmarks = new ArrayList<>();

            Bookmarks book = new Bookmarks();
            book.setJuzNumber("50");
            book.setPageNumber("10");
            book.setSurah("اقْتَرَبَ لِلنَّاسِ");
            bookmarks.add(book);

        return bookmarks;
    }
}
