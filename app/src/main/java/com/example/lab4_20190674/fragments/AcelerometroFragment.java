package com.example.lab4_20190674.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab4_20190674.R;
import com.example.lab4_20190674.databinding.FragmentAcelerometroBinding;


public class AcelerometroFragment extends Fragment {

    FragmentAcelerometroBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAcelerometroBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }
}