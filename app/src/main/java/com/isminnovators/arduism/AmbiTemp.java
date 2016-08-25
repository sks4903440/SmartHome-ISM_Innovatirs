package com.isminnovators.arduism;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by SUBHAM on 05-Mar-16.
 */
public class AmbiTemp extends Fragment {
    Button atbtn;
    EditText atedittext;
    Switch atonoff;
    TextView attext;
    BtSendData btSendData = new BtSendData();
    AmbiTempsenddata ambiTempsenddata;
    SharedPreferences sharedPreferences;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ambitemp, container, false);
        getActivity().setTitle("Ambi-Temp");
        atbtn = (Button) view.findViewById(R.id.atbtn);
        atedittext = (EditText) view.findViewById(R.id.atedittext);
        atonoff = (Switch) view.findViewById(R.id.atonoff);
        attext = (TextView) view.findViewById(R.id.attext);
        ambiTempsenddata = (AmbiTempsenddata) getActivity();
        sharedPreferences = getActivity().getPreferences(0);
        if (sharedPreferences.getInt("fanstate", -1) == 1) {
            atonoff.setChecked(false);
            atedittext.setVisibility(View.INVISIBLE);
            attext.setVisibility(View.INVISIBLE);
            atbtn.setVisibility(View.INVISIBLE);
        }

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();


        atonoff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    atedittext.setVisibility(View.VISIBLE);
                    attext.setVisibility(View.VISIBLE);
                    atbtn.setVisibility(View.VISIBLE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("fanstate", -1);
                    editor.commit();
                } else {
                    atedittext.setVisibility(View.INVISIBLE);
                    attext.setVisibility(View.INVISIBLE);
                    atbtn.setVisibility(View.INVISIBLE);
                    ambiTempsenddata.ambitempsenddata1("@3:0:0#");
                }
            }
        });

        atbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String attemp = atedittext.getText().toString();
                ambiTempsenddata.ambitempsenddata1("@3:1:" + attemp + "#");
                atedittext.clearFocus();
            }
        });
    }

    public void ambitempswitchoff() {
        atonoff.setChecked(false);
    }

    public interface AmbiTempsenddata {
        public abstract void ambitempsenddata1(String message);
    }
}
