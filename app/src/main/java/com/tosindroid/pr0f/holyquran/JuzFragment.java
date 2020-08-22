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

import java.util.ArrayList;
import java.util.List;

public class JuzFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    LinearLayoutManager linearLayoutManager;
    private int num;
    private List<String> juz;
    private List<String> juzPageNumber;
    private List<String> juzNumber;
    private List<String> juzSurahName;

    public JuzFragment() {
    }

    View view;
    public final String[] juzNames = {"الم", "سيقول", "تلك الرسل", "لن تنال", "والمحصنات", "لا يحب الله", "وإذا سمعوا", "ولو أننا", "قال الملأ", "واعلموا",
            "يعتذرون", "وما من دٱبة", "ومٱ أبرئ نفسي", "ربما", "سبحان الذي", "قال الم", "إقترب", "قد أفلح", "وقال الذين", "أمن خلق السماوات", "أتل مٱ أوحي",
            "ومن يقنت", "ومالي", "فمن أظلم", "إليه يرد", "حم", "قال فما خطبكم", "قد سمع الله", "تبارك", "عم"};

    public final String[] juzSurah ={"Al-Fatihah", "Al-Baqarah", "Al-Baqarah", "Al-'Imran", "An-Nisa'", "An-Nisa'", "Al-Ma'idah", "Al-An'am",
            "Al-A'raf", "Al-Anfal", "At-Taubah", "Hud", "Yusuf", "Al-Hijr", "BaniIsra'il", "Al-Kahf", "Al-Anbiya'", "Al-Mu'minun", "Al-Furqan",
            "An-Naml", "Al-'Ankabut", "Al-Ahzab", "YaSin", "Az-Zumar", "HaMim Sajdah", "Al-Ahqaf", "Ad-Dhariyat", "Al-Mujadilah", "Al-Mulk", "An-Naba'"};

    public final String[] juzPages = {"2", "22", "42", "62", "82", "102", "122", "142", "162", "182", "202", "222", "242", "262",
            "282", "302", "322", "342", "362", "382", "402", "422", "442", "462", "482", "502", "522", "542", "562", "582"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.juz_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvJuz);

        juz = new ArrayList<>();
        juzNumber = new ArrayList<>();
        juzPageNumber = new ArrayList<>();
        juzSurahName = new ArrayList<>();


        for(int i = 0; i< juzNames.length; i++) {
            juz.add(juzNames[i]);
            juzPageNumber.add("Page " + juzPages[i]);
            num = i + 1;
            juzNumber.add(""+num);
            juzSurahName.add(juzSurah[i]);
        }


        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new JuzFragmentAdapter(this, juz, juzNumber, juzPageNumber, juzSurahName);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
