package com.example.mydictionary;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 *
 */
public class WordFragment extends Fragment {

    public interface OnWordSelectedListener{
        public void onWordSelected(int position);
    }
    OnWordSelectedListener onWordSelectedListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_word, container, false);
        ListView listView = view.findViewById(R.id.word_list);
        listView.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,Data.words));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                onWordSelectedListener.onWordSelected(position);
            }
        });
        onWordSelectedListener = (MainActivity)getActivity();
        // Inflate the layout for this fragment
        return view;
    }
}