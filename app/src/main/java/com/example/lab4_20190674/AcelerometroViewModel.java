package com.example.lab4_20190674;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.example.lab4_20190674.dto.Results;

import javax.xml.transform.Result;

public class AcelerometroViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<Results>> listaPersonasAcelerometro = new MutableLiveData<ArrayList<Results>>();

    public MutableLiveData<ArrayList<Results>> getListaPersonasAcelerometro() {
        return listaPersonasAcelerometro;
    }

}
