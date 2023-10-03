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
import com.example.lab4_20190674.MagnetometroViewModel;
import com.example.lab4_20190674.R;
import com.example.lab4_20190674.dto.Results;
import com.example.lab4_20190674.service.UserService;

import java.util.ArrayList;
import java.util.List;

public class listaAdapter extends RecyclerView.Adapter<listaAdapter.MagnetometroViewHolder>  {
    private List<Results> listaMagnetrometro;
    private Context context;
    private MagnetometroViewModel personasMagnetometroVM;

    @NonNull
    @Override
    public listaAdapter.MagnetometroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv, parent, false);
        return new listaAdapter.MagnetometroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MagnetometroViewHolder holder, int position) {
        Results results = listaMagnetrometro.get(position);
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
            ArrayList<Results> listaActual = personasMagnetometroVM.getListaPersonasMagnetometro().getValue();
            listaActual.remove(results);
            personasMagnetometroVM.getListaPersonasMagnetometro().postValue(listaActual);
            notifyDataSetChanged();

        });







    }


    @Override
    public int getItemCount() {
        return listaMagnetrometro.size();
    }
    public class MagnetometroViewHolder extends RecyclerView.ViewHolder{
        Results results;
        public MagnetometroViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }

    public List<Results> getListaMagnet() {
        return listaMagnetrometro;
    }

    public void setListaMagnet(List<Results> listaMagnet) {
        this.listaMagnetrometro = listaMagnet;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public MagnetometroViewModel getPersonasMagnetometroVM() {
        return personasMagnetometroVM;
    }

    public void setPersonasMagnetometroVM(MagnetometroViewModel personasMagnetometroVM) {
        this.personasMagnetometroVM = personasMagnetometroVM;
    }












}
