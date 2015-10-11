package com.parse.starter;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseUser;


public class PerfilFragment extends Fragment {
    TextView usuario,nombre,apellido,dni;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        usuario = (TextView)view.findViewById(R.id.show_usuario);
        usuario.setText(ParseUser.getCurrentUser().getUsername());
        nombre = (TextView)view.findViewById(R.id.show_nombre);
        nombre.setText(ParseUser.getCurrentUser().getString("Nombre"));
        apellido = (TextView)view.findViewById(R.id.show_apellido);
        apellido.setText(ParseUser.getCurrentUser().getString("Apellido"));
        dni = (TextView)view.findViewById(R.id.show_dni);
        dni.setText(ParseUser.getCurrentUser().getString("DNI"));
        return view;
    }}
