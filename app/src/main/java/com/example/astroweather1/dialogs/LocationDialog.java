package com.example.astroweather1.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.astroweather1.R;
import com.example.astroweather1.commondata.Location;
import com.example.astroweather1.commondata.RefreshTime;

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
                        Location.setLocation(Double.parseDouble(latitudeEditText.getText().toString()),Double.parseDouble(longitudeEditText.getText().toString()));
                    }
                });

        latitudeEditText = view.findViewById(R.id.latitudeEditText);
        longitudeEditText = view.findViewById(R.id.longitudeEditText);

        return builder.create();
    }
}
