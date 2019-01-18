package com.example.yeseul.movieapp.view.main;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.time.LocalDate;

public class NumberPickerFragment extends DialogFragment {

    private MainPresenter mPresenter;
    private int type;

    public static NumberPickerFragment newInstance(MainPresenter presenter,int type) {
        
        Bundle args = new Bundle();
        
        NumberPickerFragment fragment = new NumberPickerFragment();
        args.putInt("type",type);
        fragment.setPresenter(presenter);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments() != null ? getArguments().getInt("type") : 0;
    }


    @TargetApi(Build.VERSION_CODES.O)
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final NumberPicker numberPicker = new NumberPicker(getActivity());

        if(type==1) {
            numberPicker.setMinValue(1896);
        }else if(type ==2){
            numberPicker.setMinValue(mPresenter.getYearFrom());
        }
        numberPicker.setMaxValue(LocalDate.now().getYear());


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("년도를 선택하세요");

        builder.setPositiveButton("확인", (dialog, which) -> valueChangeListener.onValueChange(numberPicker,
                numberPicker.getValue(), numberPicker.getValue()));

        builder.setNegativeButton("취소",null);

        builder.setView(numberPicker);
        return builder.create();
    }

    private NumberPicker.OnValueChangeListener valueChangeListener = (numberPicker, oldValue, newValue) -> {
        if(type==1) {
            mPresenter.onYearFromSelected(numberPicker.getValue());
        } else if(type==2){
            mPresenter.onYearToSelected(numberPicker.getValue());
        }
        Toast.makeText(getContext(), "value: " + numberPicker.getValue(), Toast.LENGTH_SHORT).show();

    };

    public void setPresenter(MainPresenter presenter) {
        this.mPresenter = presenter;
    }
}
