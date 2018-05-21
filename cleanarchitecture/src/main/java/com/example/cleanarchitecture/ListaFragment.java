package com.example.cleanarchitecture;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaFragment extends Fragment {

    private ArrayList<Contacto> contactosList;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapterContactos;
    private RecyclerView.LayoutManager layoutManager;
/*
    private ContactosSQLiteHelper contactosSQLiteHelper;
    private SQLiteDatabase dbContactos;
*/

    public ListaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lista, container, false);
/*
        contactosSQLiteHelper = new ContactosSQLiteHelper(getContext(),
                "Agenda",
                null,
                1);
        dbContactos = contactosSQLiteHelper.getWritableDatabase();
        */

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        contactosList = new ArrayList<>();

        adapterContactos = new AdapterContactos(contactosList, R.layout.list_item,
               getActivity());

        recyclerView.setAdapter(adapterContactos);

        class ListarContacto extends AsyncTask<Void, Void, String> {

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
                String res = rh.sendGetRequest(Config.URL_LISTAR);
                return res;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showdata(s);
                //Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
            }
        }
        ListarContacto listar = new ListarContacto();
        listar.execute();
/*
        Cursor cursor = dbContactos.rawQuery("SELECT * FROM contactos",null);
        if(cursor.moveToFirst()){
            do{
                Contacto contactico = new Contacto(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                );
                contactosList.add(contactico);
            }while(cursor.moveToNext());
            adapterContactos.notifyDataSetChanged();

        }

*/
        return view;
    }

    private void showdata(String s) {
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray result = jsonObject.getJSONArray("result");

            for(int position = 0; position < result.length(); position++){
                JSONObject jo = result.getJSONObject(position);

                String id = jo.getString("id");
                String nombre = jo.getString("nombre");
                String telefono = jo.getString("telefono");
                String correo = jo.getString("correo");

                Contacto contacto = new Contacto(nombre,telefono,correo);
                contactosList.add(contacto);


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapterContactos.notifyDataSetChanged();
    }

}
