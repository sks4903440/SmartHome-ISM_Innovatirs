package com.isminnovators.arduism;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by SUBHAM on 06-Mar-16.
 */
public class Feedback extends Fragment {
    Button feedbacksend;
    EditText feedbackedit;




    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.feedback,container,false);
        getActivity().setTitle("Feedback");
        feedbackedit=(EditText)view.findViewById(R.id.feedbackedit);
        feedbacksend=(Button)view.findViewById(R.id.feedbacksend);

        feedbacksend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feedback=feedbackedit.getText().toString();
                feedbackedit.clearFocus();
                if(feedback==" "){
                    Toast.makeText(getActivity(),"Enter the feedback first",Toast.LENGTH_SHORT).show();
                }
                else{
                    final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                    intent.setType("text/html");
                    intent.putExtra(android.content.Intent.EXTRA_EMAIL, "sks4903440@gmail.com");
                    intent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Arduism related Issues  & Suggestions" );
                    intent.putExtra(android.content.Intent.EXTRA_TEXT, feedback);
                    startActivity(Intent.createChooser(intent, "Feedback"));
                    Toast.makeText(getActivity(),"Feedback Sent",Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }
}
