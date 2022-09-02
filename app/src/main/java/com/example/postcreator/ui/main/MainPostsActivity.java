package com.example.postcreator.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.example.postcreator.databinding.ActivityMainPostsBinding;
import com.google.android.material.tabs.TabLayout;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;


import com.example.postcreator.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainPostsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainPostsBinding binding = ActivityMainPostsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = binding.viewPager;
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);




        binding.fab.setOnClickListener(
                view -> {
                 List<Long> longs = parseMessageReferences("#90");
                    Toast.makeText(this, "size: "+longs.get(0), Toast.LENGTH_SHORT).show();
                });
    }

    public Pattern patternMessageReference = Pattern.compile("#([0-9]+)");
    public List<Long> parseMessageReferences(String text){
        List<Long> list = new ArrayList<>();
        Matcher matcher = patternMessageReference.matcher(text);
        while(matcher.find()){
            try {
                list.add(Long.parseLong(matcher.group(1)));
            }catch(NumberFormatException e){}
        }
        return list;
    }
}