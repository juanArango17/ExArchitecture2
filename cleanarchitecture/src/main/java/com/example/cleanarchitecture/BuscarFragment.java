package com.example.cleanarchitecture;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BuscarFragment extends Fragment {

    EditText eNombre;
    TextView tNombre, tTelefono, tCorreo;

    public BuscarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_buscar, container, false);

        eNombre = view.findViewById(R.id.eNombre);
        tNombre = view.findViewById(R.id.tNombre);
        tTelefono = view.findViewById(R.id.tTelefono);
        tCorreo = view.findViewById(R.id.tCorreo);


        return view;
    }

    public void buscarClicked(View view){

    }

}
