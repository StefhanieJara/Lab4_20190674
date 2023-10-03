package com.example.lab4_20190674.fragments;

import android.content.Context;
import android.hardware.Sensor;
import static android.content.Context.SENSOR_SERVICE;

import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;

import com.example.lab4_20190674.AcelerometroViewModel;
import com.example.lab4_20190674.adapter.lista2Adapter;
import com.example.lab4_20190674.adapter.listaAdapter;
import com.example.lab4_20190674.vistadelVM;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lab4_20190674.R;
import com.example.lab4_20190674.databinding.FragmentAcelerometroBinding;

import java.text.DecimalFormat;


public class AcelerometroFragment extends Fragment implements SensorEventListener {

    FragmentAcelerometroBinding binding;
    private vistadelVM vistadelVMu;

    private lista2Adapter listaAdapter2;
    private AcelerometroViewModel acelerometroViewModel;
    DecimalFormat df = new DecimalFormat("#.##");

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        vistadelVMu = new ViewModelProvider(requireActivity()).get(vistadelVM.class);
        acelerometroViewModel = new ViewModelProvider(requireActivity()).get(AcelerometroViewModel.class);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAcelerometroBinding.inflate(inflater, container, false);
        SensorManager sensorManager = (SensorManager) requireActivity().getSystemService(SENSOR_SERVICE);
        if (sensorManager != null){
            Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if (sensor!=null){
                sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
            }
        }

        NavController navController = NavHostFragment.findNavController(AcelerometroFragment.this);
        acelerometroViewModel.getListaPersonasAcelerometro().observe(this, lista->{
            listaAdapter2 = new lista2Adapter();
            listaAdapter2.setContext(getContext());
            listaAdapter2.setListaAcelerometro(lista);
            listaAdapter2.setPersonasAcelerometroVM(acelerometroViewModel);
            binding.recyclerAcelerometro.setAdapter(listaAdapter2);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            binding.recyclerAcelerometro.setLayoutManager(linearLayoutManager);
        });
        vistadelVMu.getVistaActual().observe(this, vistaActual->{
            Log.i("AcelerometroFragment", "Valor observado: " + vistaActual);
            if (vistaActual.equals("Magnetómetro")){
                navController.navigate(R.id.action_blankFragment_to_acelerometroFragment);
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int type = sensorEvent.sensor.getType();
        if (type==Sensor.TYPE_ACCELEROMETER){
            double aceleracion_total = Math.sqrt(Math.pow(sensorEvent.values[0],2)+
                    Math.pow(sensorEvent.values[1],2)+
                    Math.pow(sensorEvent.values[2],2));
            if (aceleracion_total>15.0){
                Toast.makeText(requireActivity(), String.valueOf(df.format(aceleracion_total))+" m/s^2", Toast.LENGTH_SHORT).show();
                int itemCount = binding.recyclerAcelerometro.getAdapter().getItemCount();
                if (itemCount>0)
                    binding.recyclerAcelerometro.smoothScrollToPosition(itemCount-1);
            }
        }
    }
    @Override
    public void onStop(){
        super.onStop();
        SensorManager sensorManager = (SensorManager) requireActivity().getSystemService(SENSOR_SERVICE);
        sensorManager.unregisterListener(this);
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}