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
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by SUBHAM on 04-Mar-16.
 */
public class SensibleCharging extends Fragment {
    Switch sconoff;
    RadioButton scradiobtn1, scradiobtn2, scradiobtn3, scradiobtn4;
    TextView scontext;
    TextView scofftext;
    Button sconbtn, scoffbtn;
    EditText sconedittext, scoffedittext;
    BtSendData btSendData = new BtSendData();
Sensiblesenddata sensiblesenddata;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sensiblecharging, container, false);
        getActivity().setTitle("Sensible Charging");

        sensiblesenddata=(Sensiblesenddata)getActivity();

        sconoff = (Switch) view.findViewById(R.id.switch1);
        scradiobtn1 = (RadioButton) view.findViewById(R.id.scradiobtn1);
        scradiobtn2 = (RadioButton) view.findViewById(R.id.scradiobtn2);
        scradiobtn3 = (RadioButton) view.findViewById(R.id.scradiobtn3);
        scradiobtn4 = (RadioButton) view.findViewById(R.id.scradiobtn4);
        scontext = (TextView) view.findViewById(R.id.scontext);
        scofftext = (TextView) view.findViewById(R.id.scofftext);
        sconbtn = (Button) view.findViewById(R.id.sconbtn);
        scoffbtn = (Button) view.findViewById(R.id.scoffbtn);
        sconedittext = (EditText) view.findViewById(R.id.sconedittext);
        scoffedittext = (EditText) view.findViewById(R.id.scoffedittext);


        return view;
    }


    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();



        scradiobtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sconedittext.setVisibility(View.INVISIBLE);
                sconbtn.setVisibility(View.INVISIBLE);
               sensiblesenddata.sensiblesenddata1("@1-1-100#");
            }
        });
        scradiobtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sconedittext.setVisibility(View.VISIBLE);
                sconbtn.setVisibility(View.VISIBLE);

            }
        });
        scradiobtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoffedittext.setVisibility(View.INVISIBLE);
                scoffbtn.setVisibility(View.INVISIBLE);
            }
        });
        scradiobtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoffedittext.setVisibility(View.VISIBLE);
                scoffbtn.setVisibility(View.VISIBLE);
                sensiblesenddata.sensiblesenddata1("@1:1:5#");

            }
        });

        sconoff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    scradiobtn1.setVisibility(View.VISIBLE);
                    scradiobtn2.setVisibility(View.VISIBLE);
                    scradiobtn3.setVisibility(View.VISIBLE);
                    scradiobtn4.setVisibility(View.VISIBLE);
                    scontext.setVisibility(View.VISIBLE);
                    scofftext.setVisibility(View.VISIBLE);
                    sconedittext.setVisibility(View.INVISIBLE);
                    scoffedittext.setVisibility(View.INVISIBLE);
                    sconbtn.setVisibility(View.INVISIBLE);
                    scoffbtn.setVisibility(View.INVISIBLE);
                } else {
                    scradiobtn1.setVisibility(View.INVISIBLE);
                    scradiobtn2.setVisibility(View.INVISIBLE);
                    scradiobtn3.setVisibility(View.INVISIBLE);
                    scradiobtn4.setVisibility(View.INVISIBLE);
                    scontext.setVisibility(View.INVISIBLE);
                    scofftext.setVisibility(View.INVISIBLE);
                    sconedittext.setVisibility(View.INVISIBLE);
                    scoffedittext.setVisibility(View.INVISIBLE);
                    sconbtn.setVisibility(View.INVISIBLE);
                    scoffbtn.setVisibility(View.INVISIBLE);
                    sensiblesenddata.sensiblesenddata1("@1:1:0#");
                    sensiblesenddata.sensiblesenddata1("@1:2:0#");
                }
            }

        });
        sconbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sconuserdefined = sconedittext.getText().toString();
                sconedittext.clearFocus();
                int sconuserdefinedint = Integer.parseInt(sconuserdefined);
                if (sconuserdefinedint < 0 || sconuserdefinedint > 100) {
                    Toast.makeText(getActivity(), "Enter a value between 0 t0 100", Toast.LENGTH_SHORT).show();
                } else {
                    sensiblesenddata.sensiblesenddata1("@1:1:" + sconuserdefined + "#");
                }

            }
        });
        scoffbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String scoffuserdefined = scoffedittext.getText().toString();
                sconedittext.clearFocus();
                int scoffuserdefinedint = Integer.parseInt(scoffuserdefined);
                if (scoffuserdefinedint < 0 || scoffuserdefinedint > 100) {
                    Toast.makeText(getActivity(), "Enter a value between 0 t0 100", Toast.LENGTH_SHORT).show();
                } else {
                    sensiblesenddata.sensiblesenddata1("@1:2:" + scoffuserdefined + "#");
                }
            }
        });

    }
    public interface Sensiblesenddata {
        public abstract void sensiblesenddata1(String message);
    }


}

