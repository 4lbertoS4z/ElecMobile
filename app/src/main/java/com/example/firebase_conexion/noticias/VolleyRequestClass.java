package com.example.firebase_conexion.noticias;

import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.firebase_conexion.R;

import org.json.JSONException;

import java.util.Map;

public class VolleyRequestClass {
    //Se conecta a la URL que le pasamos y devuelve el resultado, ademas de usar una progresBar mientras carga los datos
    public VolleyRequestClass(final MainActivity3 ctx, String url, int requestMethod, final Map<String, String> params, final ProgressBar progressBar, final int requestId) {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
        Log.d("ALBERTO"," URL: "+url);
        RequestQueue queue = Volley.newRequestQueue(ctx);
        StringRequest stringRequest = new StringRequest(requestMethod, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            ctx.onVolleyResponse(response, requestId);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if (progressBar != null) {
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;
                Log.d("ALBERTO", "ERROR: "+error);

                if (response != null && response.data != null) {
                    ctx.displayAlertDialog(ctx.getString(R.string.generic_error), response.statusCode + "");
                }

                if (progressBar != null) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };
        stringRequest.setShouldCache(false);
        queue.add(stringRequest);
    }
}
