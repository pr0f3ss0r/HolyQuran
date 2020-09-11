package com.tosindroid.pr0f.holyquran;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class GoToDialogue extends AppCompatDialogFragment {

    private EditText gotoEditText;



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogue_layout, null);



        builder.setView(view).setTitle("Go To Page")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String toGo = gotoEditText.getText().toString();
                int pageToGo  = Integer.parseInt(toGo);

                Intent myIntent = new Intent(getContext(), QuranViewActivity.class);

                if(pageToGo <= 607){
                    myIntent.putExtra("fatir", pageToGo);
                    getContext().startActivity(myIntent);
                }else{
                    Toast.makeText(getContext(),"Page number does not exist", Toast.LENGTH_LONG).show();

                }

            }
        });

        gotoEditText = view.findViewById(R.id.goToEditText);
        return builder.create();
    }
}
