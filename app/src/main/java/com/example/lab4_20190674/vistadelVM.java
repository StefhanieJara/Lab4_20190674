package com.example.lab4_20190674;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class vistadelVM extends ViewModel {
    private final MutableLiveData<String> vistaAhora = new MutableLiveData<>();

    public MutableLiveData<String> getVistaActual() {
        return vistaAhora;
    }

}
