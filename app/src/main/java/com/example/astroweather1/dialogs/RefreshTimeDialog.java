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

public class RefreshTimeDialog extends AppCompatDialogFragment {
    private EditText refreshTimeEditText;
    //private String refreshTime;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.refresh_time_settings, null);

        if(savedInstanceState != null){
            refreshTimeEditText.setText(savedInstanceState.getString("refreshTime"));
            //String numberOnScreen = savedInstanceState.getString("numberOnScreen");
        }

        builder.setView(view)
                .setTitle("Refresh time settings")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(refreshTimeEditText.getText().toString().length()!=0){
                            AstroInformation.setRefreshTime(Integer.parseInt(refreshTimeEditText.getText().toString()));
                        }else{
                            Toast.makeText(getContext(),"Error: Incorrect data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        refreshTimeEditText = view.findViewById(R.id.refreshTimeEditText);

        return builder.create();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("refreshTime", refreshTimeEditText.getText().toString());
    }
}
