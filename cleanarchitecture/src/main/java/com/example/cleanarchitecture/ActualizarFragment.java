package com.example.cleanarchitecture;


import android.app.ProgressDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActualizarFragment extends Fragment {

    private EditText eNombre, eTelefono, eCorreo;
    private Button bActualizar;

    /*
    private ContactosSQLiteHelper contactosSQLiteHelper;
    private SQLiteDatabase dbContactos;
    */

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
        bActualizar = view.findViewById(R.id.bActualizar);
        bActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                class ActualizarContacto extends AsyncTask<Void, Void, String> {
                    ProgressDialog loading;

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        loading = ProgressDialog.show(
                                getActivity(),
                                "Crear contacto", "Creando...",
                                false,false);
                    }

                    @Override
                    protected String doInBackground(Void... voids) {
                        HashMap<String,String> params = new HashMap<>();
                        params.put("nombre",eNombre.getText().toString());
                        params.put("telefono",eTelefono.getText().toString());
                        params.put("correo",eCorreo.getText().toString());

                        RequestHandler requestHandler = new RequestHandler();
                        String result = requestHandler.sendPostRequest(Config.URL_ACTUALIZAR,params);
                        return result;
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);

                        loading.dismiss();
                        Log.d("crear contacto",s);
                    }



                }
                ActualizarContacto actualizarContacto = new ActualizarContacto();
                actualizarContacto.execute();
            }
        });
/*
        contactosSQLiteHelper = new ContactosSQLiteHelper(
                getContext(),
                "Agenda",
                null,
                1);
        dbContactos = contactosSQLiteHelper.getWritableDatabase();
        bActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues dataDB = new ContentValues(); //para almacenar info que se va a bases de datos

                dataDB.put("telefono",eTelefono.getText().toString());
                dataDB.put("correo",eCorreo.getText().toString());

                dbContactos.update("contactos",
                        dataDB,
                        "nombre='"+eNombre.getText().toString()+"'",
                        null);
                Toast.makeText(getContext(),"Dato Actualizado",Toast.LENGTH_SHORT).show();

            }
        });
 */

        return view;
    }

    public void actualizarClicked (View view){

    }

}
