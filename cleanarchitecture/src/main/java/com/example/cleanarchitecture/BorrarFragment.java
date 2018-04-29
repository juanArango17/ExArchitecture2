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
public class BorrarFragment extends Fragment {

    private EditText eNombre;

    public BorrarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_borrar, container, false);

        eNombre = view.findViewById(R.id.eNombre);

        return view;
    }

    public void borrarClicked(View view){

    }

}
