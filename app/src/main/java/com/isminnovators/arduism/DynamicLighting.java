package com.isminnovators.arduism;

import android.app.Fragment;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

/**
 * Created by SUBHAM on 05-Mar-16.
 */
public class DynamicLighting extends Fragment {
    Switch dlonoff;
    BtSendData btSendData = new BtSendData();
Dynamiclightingsenddata dynamiclightingsenddata;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dynamiclighting,container,false);
        getActivity().setTitle("Dynamic Lighting");
        dlonoff=(Switch)view.findViewById(R.id.dlonoff);
        dynamiclightingsenddata=(Dynamiclightingsenddata)getActivity();
        return view;

    }
    @Override
    public void onResume() {
        super.onResume();



        dlonoff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dynamiclightingsenddata.dynamiclightinhsenddata1("@2:1:1#");
                } else {
                   dynamiclightingsenddata.dynamiclightinhsenddata1("@2:1:0#");
                }
            }
        });
    }

    public interface Dynamiclightingsenddata{
        public abstract void dynamiclightinhsenddata1(String message);
    }
}
