package com.example.cleanarchitecture;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActualizarFragment extends Fragment {

    private EditText eNombre, eTelefono, eCorreo;

    public ActualizarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_actualizar, container, false);

        eNombre = view.findViewById(R.id.eNombre);
        eTelefono = view.findViewById(R.id.eTelefono);
        eCorreo = view.findViewById(R.id.eCorreo);

        return view;
    }

    public void actualizarClicked (View view){

    }

}
