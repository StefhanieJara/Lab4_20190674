package com.example.lab4_20190674.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lab4_20190674.AcelerometroViewModel;
import com.example.lab4_20190674.MagnetometroViewModel;
import com.example.lab4_20190674.R;
import com.example.lab4_20190674.dto.Results;

import java.util.ArrayList;
import java.util.List;

public class lista2Adapter extends RecyclerView.Adapter<lista2Adapter.AcelerometroViewHolder> {

    private List<Results> listaAcelerometro;
    private Context context;
    private AcelerometroViewModel personasAcelerometro;

    @NonNull
    @Override
    public lista2Adapter.AcelerometroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv, parent, false);
        return new lista2Adapter.AcelerometroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull lista2Adapter.AcelerometroViewHolder holder, int position) {
        Results results = listaAcelerometro.get(position);
        holder.results = results;

        ImageView foto = holder.itemView.findViewById(R.id.Foto);
        TextView genero = holder.itemView.findViewById(R.id.Genero);
        TextView telefono = holder.itemView.findViewById(R.id.Telefono);
        TextView email = holder.itemView.findViewById(R.id.Email);
        TextView nombre = holder.itemView.findViewById(R.id.Nombre);
        TextView ciudad = holder.itemView.findViewById(R.id.Ciudad);
        TextView pais = holder.itemView.findViewById(R.id.Pais);
        ImageView cerrar = holder.itemView.findViewById(R.id.cerrar);
        Glide.with(context).load(results.getPicture().getLarge()).into(foto);
        genero.setText("Genero: "+results.getGender());
        telefono.setText("Phone: "+results.getPhone());
        email.setText("Email: "+results.getEmail());
        nombre.setText("Nombre: "+results.getName());
        ciudad.setText("Ciudad: "+results.getLocation().getCity());
        pais.setText("País: "+results.getLocation().getCountry());

        nombre.setText(results.getName().getTitle()+" "+results.getName().getFirst()+" "+results.getName().getLast());
        cerrar.setOnClickListener(view -> {
            ArrayList<Results> listaActual = personasAcelerometro.getListaPersonasAcelerometro().getValue();
            listaActual.remove(results);
            personasAcelerometro.getListaPersonasAcelerometro().postValue(listaActual);
            notifyDataSetChanged();

        });




    }


    @Override
    public int getItemCount() {
        return listaAcelerometro.size();
    }
    public class AcelerometroViewHolder extends RecyclerView.ViewHolder{
        Results results;
        public AcelerometroViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }

    public List<Results> getListaMagnet() {
        return listaAcelerometro;
    }

    public void setListaMagnet(List<Results> listaMagnet) {
        this.listaAcelerometro = listaMagnet;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public AcelerometroViewModel getPersonasAcelerometro() {
        return personasAcelerometro;
    }

    public void setPersonasMagnetometroVM(AcelerometroViewModel personasAcelerometroVM) {
        this.personasAcelerometro = personasAcelerometroVM;
    }

}
