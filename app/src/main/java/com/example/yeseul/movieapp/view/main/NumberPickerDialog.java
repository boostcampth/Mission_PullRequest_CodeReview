package com.example.yeseul.movieapp.view.main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.NumberPicker;

import com.example.yeseul.movieapp.R;
import com.example.yeseul.movieapp.databinding.YearDialogBinding;
import com.example.yeseul.movieapp.utils.BindingUtil;

public class NumberPickerDialog extends DialogFragment {
    private MainContract.Presenter listener;
    private YearDialogBinding binding;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(LayoutInflater.from(getContext()),R.layout.year_dialog,null,false);
        binding.setDialog(this);
        LayoutInflater inflater =getActivity().getLayoutInflater();
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("제작년도 설정");
        builder.setView(binding.getRoot())
                .setPositiveButton("OK",((dialog, which) -> listener.onYearButtonClicked(binding.yearFrom.getValue(),binding.yearTo.getValue())))
                .setNegativeButton("Cancel", ((dialog, which) ->listener.onYearButtonClicked(0,0)));
        binding.yearFrom.setMinValue(1990);
        binding.yearFrom.setMaxValue(2019);
        binding.yearFrom.setValue(2019);
        binding.yearTo.setMinValue(1990);
        binding.yearTo.setMaxValue(2019);
        binding.yearTo.setValue(2019);
        binding.yearFrom.setWrapSelectorWheel(false);
        binding.yearTo.setWrapSelectorWheel(false);
        return builder.create();
    }

    public MainContract.Presenter getListener(){
        return listener;
    }
    public void setListener(MainContract.Presenter listener){
        this.listener=listener;
    }
}
