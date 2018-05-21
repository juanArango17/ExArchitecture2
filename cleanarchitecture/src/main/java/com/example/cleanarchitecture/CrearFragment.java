package com.example.cleanarchitecture;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class CrearFragment extends Fragment {

    private EditText eNombre, eTelefono, eCorreo;
    private Button bCrear;
    /*
    private ContactosSQLiteHelper contactosSQLiteHelper;
    private SQLiteDatabase dbContactos;
    */

    public CrearFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_crear, container, false);

        eNombre = view.findViewById(R.id.eNombre);
        eTelefono = view.findViewById(R.id.eTelefono);
        eCorreo = view.findViewById(R.id.eCorreo);

        bCrear  = view.findViewById(R.id.bCrear);

        bCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AndroidNetworking.post(Config.URL_CREAR)
                        .addBodyParameter("nombre", eNombre.getText().toString())
                        .addBodyParameter("telefono", eTelefono.getText().toString())
                        .addBodyParameter("correo", eCorreo.getText().toString())
                        .setTag("test")
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // do anything with response
                                Toast.makeText(getActivity(),response.toString(),Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onError(ANError error) {
                                Log.d("Response",error.getMessage());
                                // handle error
                            }
                        });
            }
        });

/*
        bCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue queue = Volley.newRequestQueue(getActivity());

                StringRequest postRequest = new StringRequest(Request.Method.POST, Config.URL_CREAR,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                // response
                                Log.d("Response", response);
                                Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Log.d("Error.Response", error.getMessage());
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String>  params = new HashMap<String, String>();
                        params.put("nombre", eNombre.getText().toString());
                        params.put("telefono", eTelefono.getText().toString());
                        params.put("correo",eCorreo.getText().toString());

                        return params;
                    }
                };
                queue.add(postRequest);
            }
        });
        */

        return view;
        /*
        bCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                class CrearContacto extends AsyncTask<Void, Void, String>{
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
                        String result = requestHandler.sendPostRequest(Config.URL_CREAR,params);
                        return result;
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);

                        loading.dismiss();
                        Log.d("crear contacto",s);
                    }



                }
                CrearContacto crearContacto = new CrearContacto();
                crearContacto.execute();
            }
        });
        */

        /*
        contactosSQLiteHelper = new ContactosSQLiteHelper(
                getContext(),
                "Agenda",
                null,
                1);
        dbContactos = contactosSQLiteHelper.getWritableDatabase();

        bCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues dataDB = new ContentValues();
                dataDB.put("nombre",eNombre.getText().toString());
                dataDB.put("telefono",eTelefono.getText().toString());
                dataDB.put("correo",eCorreo.getText().toString());

                dbContactos.insert("contactos",
                        null,
                        dataDB
                );

                dbContactos.execSQL("INSERT INTO contactos VALUES(" +
                        "null, " +
                        "'"+eNombre.getText().toString()+"'," +
                        "'"+eTelefono.getText().toString()+"'," +
                        "'"+eCorreo.getText().toString()+"')"
                );

            }
        });
        */
    }
}
