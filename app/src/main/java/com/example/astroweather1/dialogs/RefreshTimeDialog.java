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
import com.example.astroweather1.commondata.RefreshTime;

public class RefreshTimeDialog extends AppCompatDialogFragment {
    private EditText refreshTimeEditText;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.refresh_time_settings, null);

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
                        RefreshTime.setRefreshTime(Integer.parseInt(refreshTimeEditText.getText().toString()));
                    }
                });

        refreshTimeEditText = view.findViewById(R.id.refreshTimeEditText);

        return builder.create();
    }
}
