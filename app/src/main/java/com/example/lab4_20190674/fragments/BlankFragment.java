package com.example.lab4_20190674.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab4_20190674.R;
import com.example.lab4_20190674.databinding.FragmentBlankBinding;


public class BlankFragment extends Fragment {

    FragmentBlankBinding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBlankBinding.inflate(inflater,container,false);

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override  //manejar la info cuando ya esta creada
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}