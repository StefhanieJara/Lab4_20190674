package com.example.lab4_20190674;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lab4_20190674.dto.Results;

import java.util.ArrayList;

public class MagnetometroViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<Results>> listaPersonasMagnetometro = new MutableLiveData<ArrayList<Results>>();

    public MutableLiveData<ArrayList<Results>> getListaPersonasMagnetometro() {
        return listaPersonasMagnetometro;
    }
}
