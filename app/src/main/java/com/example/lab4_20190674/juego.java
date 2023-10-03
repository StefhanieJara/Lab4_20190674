package com.example.lab4_20190674;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import com.example.lab4_20190674.databinding.ActivityJuegoBinding;
import com.example.lab4_20190674.databinding.ActivityMainBinding;
import com.example.lab4_20190674.dto.Profile;
import com.example.lab4_20190674.dto.Results;
import com.example.lab4_20190674.service.UserService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class juego extends AppCompatActivity {

    ActivityJuegoBinding binding;

    String textoMagnetometro = "Ir al Magnetómetro";
    String textoAcelerometro = "Ir al Acelerómetro";
    UserService userService = new Retrofit.Builder()
            .baseUrl("https://randomuser.me")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserService.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJuegoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        vistadelVM vistaVM = new ViewModelProvider(juego.this).get(vistadelVM.class);
        AcelerometroViewModel personasAcelerometroVM = new ViewModelProvider(juego.this).get(AcelerometroViewModel.class);
        MagnetometroViewModel personasMagnetometroVM = new ViewModelProvider(juego.this).get(MagnetometroViewModel.class);

        personasMagnetometroVM.getListaPersonasMagnetometro().postValue(new ArrayList<>());
        personasAcelerometroVM.getListaPersonasAcelerometro().postValue(new ArrayList<>());
        vistaVM.getVistaActual().postValue("Inicio");

        binding.cambio.setOnClickListener(view -> {
            if(binding.cambio.getText().toString().equals(textoAcelerometro)){
                vistaVM.getVistaActual().postValue("Acelerómetro");
                binding.cambio.setText(textoMagnetometro);
            }else{
                vistaVM.getVistaActual().postValue("Magnetómetro");
                binding.cambio.setText(textoAcelerometro);
            }
        });

        binding.ojito.setOnClickListener(view -> {
            Log.d("msg-test-nombre"," "+binding.cambio.getText().toString());
            if (binding.cambio.getText().toString().equals(""+textoMagnetometro)){
                // Estoy en el Acelerómetro
                mostrarAlertaAcelerometro();
            }else{
                // Estoy en el Magnetómetro
                mostrarAlertaMagnetometro();
            }
        });

        binding.agregar.setOnClickListener(view -> {
            binding.agregar.setEnabled(false);
            binding.cambio.setEnabled(false);
            if (binding.cambio.getText().toString().equals(textoMagnetometro)){
                // Estoy en el Acelerómetro
                userService.random().enqueue(new Callback<Profile>() {
                    @Override
                    public void onResponse(Call<Profile> call, Response<Profile> response) {
                        if (response.isSuccessful()){
                            ArrayList<Results> listaUsuariosAcelerómetro = personasAcelerometroVM.getListaPersonasAcelerometro().getValue();
                            Results results = response.body().getResults().get(0);
                            listaUsuariosAcelerómetro.add(results);
                            personasAcelerometroVM.getListaPersonasAcelerometro().postValue(listaUsuariosAcelerómetro);
                            binding.agregar.setEnabled(true);
                            binding.cambio.setEnabled(true);
                        }
                    }
                    @Override
                    public void onFailure(Call<Profile> call, Throwable t) {
                    }
                });

            }else{
                // Estoy en el magnetómetro
                userService.random().enqueue(new Callback<Profile>() {
                    @Override
                    public void onResponse(Call<Profile> call, Response<Profile> response) {
                        if (response.isSuccessful()){
                            ArrayList<Results> listaUsuariosMagnetómetro = personasMagnetometroVM.getListaPersonasMagnetometro().getValue();
                            Results results = response.body().getResults().get(0);
                            listaUsuariosMagnetómetro.add(results);
                            personasMagnetometroVM.getListaPersonasMagnetometro().postValue(listaUsuariosMagnetómetro);
                            binding.agregar.setEnabled(true);
                            binding.cambio.setEnabled(true);
                        }
                    }
                    @Override
                    public void onFailure(Call<Profile> call, Throwable t) {
                    }
                });


            }
        });
    }

    public void mostrarAlertaMagnetometro(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Detalles - Magnetómetro");
        alertDialog.setMessage("Haga CLICK en 'Añadir' para agregar contactos a su lista."+
                " Esta aplicación está utilizando el MAGNETÓMETRO de su dispositivo.\n\n"+
                "De esta forma, la lista se mostrará el 100% cuando se apunte al NORTE. "+
                "Caso contrario, se desvanecerá...");
        alertDialog.setPositiveButton("Aceptar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("msgAlerta", "Positivo");
                    }
                });
        alertDialog.show();
    }
    public void mostrarAlertaAcelerometro(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Detalles - Acelerómetro");
        alertDialog.setMessage("Haga CLICK en 'Añadir' para agregar contactos a su lista."+
                " Esta aplicación está utilizando el ACELERÓMETRO de su dispositivo.\n\n"+
                "De esta forma, la lista hará scroll hacia abajo, cuando agite su dispositivo.");
        alertDialog.setPositiveButton("Aceptar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("msgAlerta", "Positivo");
                    }
                });
        alertDialog.show();
    }
}