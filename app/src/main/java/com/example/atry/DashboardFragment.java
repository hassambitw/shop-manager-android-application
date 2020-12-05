package com.example.atry;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DashboardFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_dashboard,container,false);

        Spinner mySpinner = v.findViewById(R.id.monthSpinner);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(v.getContext(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.month_range));

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mySpinner.setAdapter(myAdapter);

        mySpinner.setOnItemSelectedListener(this);

        return v;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
