package com.example.whatsappui.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.whatsappui.Model.Usuario;
import com.example.whatsappui.R;

import java.util.ArrayList;
import java.util.List;

public class LlamadasAdaptador extends RecyclerView.Adapter<LlamadasAdaptador.UsuarioViewHolder> {
    private Context Ctx;
    private List<Usuario> lstUsuarios;

    public LlamadasAdaptador(Context mCtx, ArrayList<Usuario> usuarios) {
        this.lstUsuarios = usuarios;
        Ctx=mCtx;
    }

    @Override
    public LlamadasAdaptador.UsuarioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(Ctx);
        View view = inflater.inflate(R.layout.itemllamada, null);
        return new LlamadasAdaptador.UsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LlamadasAdaptador.UsuarioViewHolder holder, int position) {

        Usuario usuario = lstUsuarios.get(position);

        holder.textViewName.setText(usuario.getNombre());

        Glide.with(Ctx)
                .load(usuario.getUrlavatar())
                .into(holder.imageView);

    }


    @Override
    public int getItemCount() {
        return lstUsuarios.size();
    }


    class UsuarioViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        ImageView imageView,imageLlamada;

        public UsuarioViewHolder(View itemView) {
            super(itemView);

            textViewName= itemView.findViewById(R.id.txtUsuario);
            imageView = itemView.findViewById(R.id.imgAvatar);
            imageLlamada = itemView.findViewById(R.id.imgLlamada);
        }
    }

}
