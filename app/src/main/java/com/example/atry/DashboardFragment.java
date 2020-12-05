package com.example.atry;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DashboardFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    DBHelper dbO;

    TextView re;
    TextView pu;
    TextView profit_tv;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        dbO = new DBHelper(getActivity());
        View v =  inflater.inflate(R.layout.fragment_dashboard,container,false);

        re=(TextView)v.findViewById(R.id.revenue);
        pu=(TextView)v.findViewById(R.id.purchase);
        profit_tv=(TextView)v.findViewById(R.id.profit);

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

        String s=parent.getItemAtPosition(position).toString();

            String start_date="";
            String end_date="";


        switch (s) {

            case "January":

                start_date="2020-01-01";
                end_date="2020-01-31";

                break;

            case "February":

                start_date="2020-02-01";
                end_date="2020-02-29";

                break;

            case "March":

                start_date="2020-03-01";
                end_date="2020-03-31";

                break;

            case "April":

                start_date="2020-04-01";
                end_date="2020-04-30";

                break;

            case "May":

                start_date="2020-05-01";
                end_date="2020-05-31";

                break;

            case "June":

                start_date="2020-06-01";
                end_date="2020-06-30";

                break;

            case "July":

                start_date="2020-07-01";
                end_date="2020-07-31";

                break;

            case "August":

                start_date="2020-08-01";
                end_date="2020-08-31";

                break;

            case "September":

                start_date="2020-09-01";
                end_date="2020-09-30";

                break;

            case "October":

                start_date="2020-10-01";
                end_date="2020-10-31";

                break;

            case "November":

                start_date="2020-11-01";
                end_date="2020-11-30";

                break;

            case "December":

                start_date="2020-12-01";
                end_date="2020-12-31";
                System.out.println("IN DEc");

                break;

            default:

                System.out.println("Invalid month.");

                break;

        }

        Double d=dbO.getAllSalesFromAMonth(start_date,end_date);
        re.setText(round(d,2)+"");
        Double d2=dbO.getAllPurchaseFromAMonth(start_date,end_date);
        pu.setText(round(d,2)+"");
        Double d3=d-d2;
        profit_tv.setText(round(d3,2)+"");

    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
