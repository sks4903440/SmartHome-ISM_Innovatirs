package com.isminnovators.arduism;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

/**
 * Created by SUBHAM on 05-Mar-16.
 */
public class SmartSolutions extends Fragment {
    BtSendData btSendData = new BtSendData();
    ToggleButton light1tb, light2tb, airconditionertb, tvtb, refrigeratortb, fantb;
    SmartSolutionssenddata smartSolutionssenddata;
    SharedPreferences sharedPreferences;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.smartsolutions, container, false);
        getActivity().setTitle("Smart Solutions");

        smartSolutionssenddata = (SmartSolutionssenddata) getActivity();

        light1tb = (ToggleButton) view.findViewById(R.id.light1tb);
        light2tb = (ToggleButton) view.findViewById(R.id.light2tb);
        airconditionertb = (ToggleButton) view.findViewById(R.id.airconditionertb);
        tvtb = (ToggleButton) view.findViewById(R.id.tvtb);
        refrigeratortb = (ToggleButton) view.findViewById(R.id.refrigeratortb);
        fantb = (ToggleButton) view.findViewById(R.id.fantb);


        return view;
    }


    @Override
    public void onResume() {
        super.onResume();


        light1tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    smartSolutionssenddata.smartsolutionssenddata1("@4:1:1#");
                } else {
                    smartSolutionssenddata.smartsolutionssenddata1("@4:1:0#");

                }
            }
        });
        light2tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    smartSolutionssenddata.smartsolutionssenddata1("@4:2:1#");

                } else {
                    smartSolutionssenddata.smartsolutionssenddata1("@4:2:0#");
                }


            }
        });
        airconditionertb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    smartSolutionssenddata.smartsolutionssenddata1("@4:3:1#");

                } else {
                    smartSolutionssenddata.smartsolutionssenddata1("@4:3:0#");
                }
                sharedPreferences = getActivity().getPreferences(0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("fanstate", 1);
                editor.commit();

            }
        });
        tvtb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    smartSolutionssenddata.smartsolutionssenddata1("@4:4:1#");

                } else {
                    smartSolutionssenddata.smartsolutionssenddata1("@4:4:0#");

                }
            }
        });
        refrigeratortb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    smartSolutionssenddata.smartsolutionssenddata1("@4:5:1#");

                } else {
                    smartSolutionssenddata.smartsolutionssenddata1("@4:5:0#");

                }

            }
        });
        fantb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    smartSolutionssenddata.smartsolutionssenddata1("@4:6:1#");

                } else {
                    smartSolutionssenddata.smartsolutionssenddata1("@4:6:0#");

                }
            }
        });
    }

    public interface SmartSolutionssenddata {
        public abstract void smartsolutionssenddata1(String message);
    }


}
