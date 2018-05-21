package com.example.cleanarchitecture;


import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class BuscarFragment extends Fragment {

    EditText eNombre;
    TextView tNombre, tTelefono, tCorreo;
    Button bBuscar;

    /*
    private ContactosSQLiteHelper contactosSQLiteHelper;
    private SQLiteDatabase dbContactos;
    */

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
        bBuscar = view.findViewById(R.id.bBuscar);

        bBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*SQlite
                ContentValues dataBD = new ContentValues();

                dataBD.put("nombre", eNombre.getText().toString());                          //mismo nombre de la base de datos
                dataBD.put("telefono", eTelefono.getText().toString());
                dataBD.put("correo", eCorreo.getText().toString());

                dbContactos.insert("contactos", null, dataBD);

                */

               /* dbContactos.execSQL("INSERT INTO contactos VALUES(" +            **
                        "null, '" + eNombre.getText().toString() + "'," +          **
                        "'" + eTelefono.getText().toString() + "'," +              otra forma
                        "'" + eCorreo.getText().toString() + "')");*/       //     **

                //ejemplo sentencia SQL:  "INSERT INTO contactos VALUES (null, 'Edwin', '45655', 'edwin@gmail.com')"


                //con XAMPP
                class BuscarContacto extends AsyncTask<Void, Void, String> {

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
                        String res = rh.sendGetRequestParam(Config.URL_BUSCAR, eNombre.getText().toString());
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
                BuscarContacto buscar = new BuscarContacto();
                buscar.execute();

            }
        });

        /*
        contactosSQLiteHelper = new ContactosSQLiteHelper(getContext(),
                "Agenda",
                null,
                1);
        dbContactos = contactosSQLiteHelper.getWritableDatabase();

        bBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = dbContactos.rawQuery("SELECT * FROM contactos WHERE " +
                        "nombre = '"+eNombre.getText().toString()+"'",null);
                if(cursor.moveToFirst()){
                    tNombre.setText(cursor.getString(1));
                    tTelefono.setText(cursor.getString(2));
                    tCorreo.setText(cursor.getString(3));
                }
            }
        });

        */

        return view;

    }

    private void showdata(String s){
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray result = jsonObject.getJSONArray("result");
            JSONObject c = result.getJSONObject(0);
            tNombre.setText(c.getString("nombre"));
            tTelefono.setText(c.getString("telefono"));
            tCorreo.setText(c.getString("correo"));
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
