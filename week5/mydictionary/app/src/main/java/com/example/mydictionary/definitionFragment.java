package com.example.mydictionary;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class definitionFragment extends Fragment {

    public static final String ARG_POSITION = "position";

    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if(args != null) {
            int pos = args.getInt("position");
            updateDefinitionView(pos);
        }
    }
    public void updateDefinitionView(int position) {
        TextView def = (TextView) getView().findViewById(R.id.definition);
        def.setText(Data.definitions[position]);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_definition, container, false);
    }
}