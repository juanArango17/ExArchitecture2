package com.example.cleanarchitecture;

import android.app.ProgressDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class BorrarFragment extends Fragment {

    private EditText eNombre;
    private Button bBorrar;
/*
    private ContactosSQLiteHelper contactosSQLiteHelper;
    private SQLiteDatabase dbContactos;
    */

    public BorrarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_borrar, container, false);

        eNombre = view.findViewById(R.id.eNombre);
        bBorrar = view.findViewById(R.id.bBorrar);

        bBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //con XAMPP
                class BorrarContacto extends AsyncTask<Void, Void, String> {

                    ProgressDialog loading;

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        loading = ProgressDialog.show(getActivity(),
                                "Buscar contacto", "Buscando...", false, false);
                    }

                    @Override
                    protected String doInBackground(Void... voids) {

                        RequestHandler rh = new RequestHandler();
                            String res = rh.sendGetRequestParam(Config.URL_BORRAR, eNombre.getText().toString());
                        return res;
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        loading.dismiss();
                        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
                    }
                }
                BorrarContacto borrar = new BorrarContacto();
                borrar.execute();
            }
        });
/*
        contactosSQLiteHelper = new ContactosSQLiteHelper(getContext(),
                "Agenda",
                null,
                1);
        dbContactos = contactosSQLiteHelper.getWritableDatabase();

        bBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbContactos.delete("contactos",
                        "nombre='"+eNombre.getText().toString()+"'",
                        null);
                Toast.makeText(getContext(),"Campo Borrado",Toast.LENGTH_SHORT).show();
            }
        });
*/


        return view;
    }


}
