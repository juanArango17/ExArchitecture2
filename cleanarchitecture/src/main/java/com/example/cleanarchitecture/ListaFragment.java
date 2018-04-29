package com.example.cleanarchitecture;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaFragment extends Fragment {

    private ArrayList<Contacto> contactosList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapterContactos;
    private RecyclerView.LayoutManager layoutManager;

    public ListaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        contactosList = new ArrayList<>();

        adapterContactos = new AdapterContactos(contactosList, R.layout.list_item,
               getActivity());

        recyclerView.setAdapter(adapterContactos);

        return view;
    }

}
