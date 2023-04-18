package com.example.mydictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements WordFragment.OnWordSelectedListener{
    FrameLayout container;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(findViewById(R.id.fragment_container)!= null){
            if(savedInstanceState != null) {
                return ;
            }
            WordFragment wordsFragment = new WordFragment();
            container = findViewById(R.id.fragment_container);
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(R.id.fragment_container,wordsFragment).commit();
        }
    }
    public void onWordSelected(int position){
        if(findViewById(R.id.fragment_container) == null) // landscape mode
        {
            definitionFragment definitionFragment = (definitionFragment) getSupportFragmentManager().findFragmentById(R.id.definition_fragment);
            definitionFragment.updateDefinitionView(position);

        }
        else //portrait
        {
            definitionFragment newFragment = new definitionFragment();
            Bundle args = new Bundle();
            args.putInt(definitionFragment.ARG_POSITION,position);
            newFragment.setArguments(args);

            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.fragment_container,newFragment);
            ft.addToBackStack(null);
            ft.commit();
        }
    }
}