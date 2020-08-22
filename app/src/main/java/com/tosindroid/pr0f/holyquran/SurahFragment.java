package com.tosindroid.pr0f.holyquran;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
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

public class SurahFragment extends Fragment {
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    View view;
    int num;
    SurahFragmentAdapter adapter;
    private List<String> surahs;
    private List<String> pageNumber;
    private List<String> chapterNumber;


    public final String[] surahNames = {"Al-Fatihah", "Al-Baqarah", "Al-'Imran", "An-Nisa'", "Al-Ma'idah", "Al-An'am", "Al-A'raf", "Al-Anfal", "At-Taubah",
            "Yunus", "Hud", "Yusuf", "Ar-Ra'd", "Ibrahim", "Al-Hijr", "An-Nahl", "BaniIsra'il", "Al-Kahf", "Maryam", "TaHa", "Al-Anbiya'", "Al-Hajj", "Al-Mu'minun",
            "An-Nur", "Al-Furqan", "Ash-Shu'ara'", "An-Naml", "Al-Qasas", "Al-'Ankabut", "Ar-Rum", "Luqman", "As-Sajdah", "Al-Ahzab", "Al-Saba'", "Al-Fatir", "YaSin",
            "As-Saffat", "Sad", "Az-Zumar", "Al-Mu'min", "HaMim Sajdah", "Ash-Shura", "Az-Zukhr", "Ad-Dukhan", "Al-Jathiyah", "Al-Ahqaf", "Muhammad", "Al-Fath",
            "Al-Hujurat", "Qaf", "Ad-Dhariyat", "At-Tur", "An-Najm", "Al-Qamar", "Ar-Rahman", "Al-Waqi'ah", "Al-Had", "Al-Mujadilah", "Al-Hashr", "Al-Mumtahanah",
            "As-Saff", "Al-Jumu'ah", "Al-Munafiqun", "At-Taghabun", "At-Talaq", "At-Tahrim", "Al-Mulk", "Al-Qalam", "Al-Haqqah", "Al-Ma'arij", "Nuh", "Al-Jinn",
            "Al-Muzzammil", "Al-Muddaththir", "Al-Qiyamah", "Al-Insan", "Al-Mursalat", "An-Naba'", "An-Nazi'at", "'Abasa", "At-Takwir", "Al-Infitar", "Al-Mutaffifin",
            "Al-Inshiqaq", "Al-Buruj", "At-Tariq", "Al-A'la", "Al-Ghashiyah", "Al-Fajr", "Al-Balad", "Ash-Shams", "Al-Lail", "Ad-Duha", "Al-Inshirah", "At-Tin",
            "Al-'Alaq", "Al-Qadr", "Al-Bayyinah", "Al-Zilzal", "Al-'Adiyat", "Al-Qari'ah", "At-Takathur", "Al-'Asr", "Al-Humazah", "Al-Fil", "Al-Quraish", "Al-Ma'un",
            "Al-Kauthar", "Al-Kafirun", "An-Nasr", "Al-Lahab", "Al-Ikhlas", "Al-Falaq", "An-Nas"};

    public final String[] surahPages = {"1", "2", "50", "77", "106", "128", "150", "177", "186", "207", "221", "235", "248", "255", "261", "267", "282",
            "293", "305", "312", "322", "331", "342", "350", "360", "366", "376", "385", "396", "404", "411", "415", "417", "427", "434", "440", "446",
            "452", "458", "467", "476", "483", "489", "495", "498", "502", "506", "510", "515", "517", "520", "523", "526", "528", "531", "534", "537",
            "542", "545", "549", "551", "553", "554", "556", "558", "560", "562", "564", "566", "568", "570", "571", "573", "575", "577", "578", "580",
            "582", "583", "584", "585", "586", "587", "588", "589", "590", "590", "591", "592", "593", "594", "594", "595", "595", "595", "596", "596",
            "597", "597", "598", "598", "598", "599", "599", "599", "599", "600", "600", "600", "600", "601", "601", "601", "601"};


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.surah_fragment_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.surah_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.surah_fragment, container, false);
        setHasOptionsMenu(true);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvSurah);
        surahs = new ArrayList<>();
        chapterNumber = new ArrayList<>();
        pageNumber = new ArrayList<>();


        for(int i = 0; i< surahNames.length; i++) {
            surahs.add(surahNames[i]);
            num = i + 1;
            chapterNumber.add("Chp "+ num);
            pageNumber.add("Page "+ surahPages[i]);



            /*TextView text = new TextView(view.getContext());
            text.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            text.setText(squares[i]);
            m_ll.addView(text);
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(getActivity(), QuranViewActivity.class);
                    myIntent.putExtra("fatir", 10);
                    startActivity(myIntent);

                }
            });*/

        }


       /* TextView textView =(TextView) view.findViewById(R.id.textView);

        textView.setText("Go to surah");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), QuranViewActivity.class);
                myIntent.putExtra("fatir", R.drawable.holy_quran_simple_arabic_435);
                startActivity(myIntent);

            }
        });*/
        adapter = new SurahFragmentAdapter(this, surahs, chapterNumber, pageNumber);
        gridLayoutManager = new GridLayoutManager(getContext(),2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        return view;




    }
    private void openDialogue() {
        GoToDialogue goToDialogue = new GoToDialogue();
        goToDialogue.show(getChildFragmentManager(), "Go to page");
    }


}


