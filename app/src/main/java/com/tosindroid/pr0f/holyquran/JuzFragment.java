package com.tosindroid.pr0f.holyquran;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class JuzFragment extends Fragment {
    RecyclerView recyclerView;
    JuzFragmentAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    private int num;
    private ArrayList<JuzPart> parts;
    private List<String> juz;
    private List<String> juzPageNumber;
    private List<String> juzNumber;
    private List<String> juzSurahName;


    View view;
    public final String[] juzNames = {"الم", "سيقول", "تلك الرسل", "لن تنال", "والمحصنات", "لا يحب الله", "وإذا سمعوا", "ولو أننا", "قال الملأ", "واعلموا",
            "يعتذرون", "وما من دٱبة", "ومٱ أبرئ نفسي", "ربما", "سبحان الذي", "قال الم", "اقْتَرَبَ لِلنَّاسِ حِسَابُهُمْ وَهُمْ فِي غَفْلَةٍ مُّعْرِضُونَ", "قد أفلح", "وقال الذين", "أمن خلق السماوات", "أتل مٱ أوحي",
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
        setHasOptionsMenu(true);
        recyclerView =  view.findViewById(R.id.rvJuz);

        juz = new ArrayList<>();
        juzNumber = new ArrayList<>();
        juzPageNumber = new ArrayList<>();
        juzSurahName = new ArrayList<>();





        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new JuzFragmentAdapter(this, getParts());
        recyclerView.setAdapter(adapter);
        return view;
    }

    private ArrayList<JuzPart> getParts(){
        parts = new ArrayList<>();

        for(int i = 0; i< juzNames.length; i++) {
            JuzPart juzPart = new JuzPart();
            juzPart.setJuz(juzNames[i]);
            juzPart.setJuzPageNumber("Page " + juzPages[i]);
            num += 1;
            juzPart.setJuzNumber(Integer.toString(num));
            juzPart.setJuzSurahName(juzSurah[i]);
            parts.add(juzPart);
        }
        return parts;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.juz_fragment_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.juz_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setIconified(false);
        searchView.setQueryHint("Search Juz");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.go_btn_menu:
                openDialogue();
                return true;
            case R.id.about_menu:
                Intent intent = new Intent(getContext(), About.class);
                startActivity(intent);
                return true;
            case R.id.settings_menu:
                Toast.makeText(getActivity(), "This is the settings", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void openDialogue() {
        GoToDialogue goToDialogue = new GoToDialogue();
        goToDialogue.show(getChildFragmentManager(), "Go to page");
    }

}
