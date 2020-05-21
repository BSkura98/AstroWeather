package com.example.astroweather1.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.astroweather1.R;
import com.example.astroweather1.AstroInformation;

public class LocationDialog extends AppCompatDialogFragment {
    private EditText latitudeEditText;
    private EditText longitudeEditText;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.location_settings, null);

        builder.setView(view)
                .setTitle("Location settings")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(latitudeEditText.getText().toString().length()!=0
                                &&longitudeEditText.getText().toString().length()!=0
                                &&!latitudeEditText.getText().toString().equals(".")
                                &&!longitudeEditText.getText().toString().equals(".")
                                &&!latitudeEditText.getText().toString().equals("-")
                                &&!longitudeEditText.getText().toString().equals("-")
                                &&!(Double.parseDouble(latitudeEditText.getText().toString())>90)
                                &&!(Double.parseDouble(latitudeEditText.getText().toString())<-90)
                                &&!(Double.parseDouble(longitudeEditText.getText().toString())>180)
                                &&!(Double.parseDouble(longitudeEditText.getText().toString())<-180)){
                            AstroInformation.setLocation(Double.parseDouble(latitudeEditText.getText().toString()),Double.parseDouble(longitudeEditText.getText().toString()));
                        }else{
                            Toast.makeText(getContext(),"Error: Incorrect data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        latitudeEditText = view.findViewById(R.id.latitudeEditText);
        longitudeEditText = view.findViewById(R.id.longitudeEditText);

        return builder.create();
    }
}
