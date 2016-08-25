package com.example.roanderson.listagem.network;


import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.JsonParser;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.Principal;
import java.util.Map;

import Util.URLManager;


/**
 * Created by Roanderson on 15/03/2016.
 */
public class NetworkManager{

    private static final String TAG = Principal.class.getSimpleName();
    public String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private NetworkObserved networkObserved;
    int socketTimeout = 6000;

    public void setNetworkObserved(NetworkObserved networkObserved) {
        this.networkObserved = networkObserved;
    }

    public void post(final Map<String,String> entity,String url){
        String msgSuccess = "Cadastro feito com sucesso.";
        String msgError =   "Erro ao efetuar o cadastro.";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                getResponseListener(msgSuccess),
                getErrorResponse(msgError)
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                return entity;
            }
        };
        ConnectionController.getInstance().addToRequestQueue(postRequest);

    }
    public String postReturnID(final Map<String,String> entity,String url){
        String response = null;
        String msgSuccess = "Cadastro feito com sucesso.";
        String msgError =   "Erro ao efetuar o cadastro.";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                        try {
                            JSONObject json = new JSONObject(response);
                            String t =  json.getString("id").toString();
                            networkObserved.doOnReturnID(t);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                getErrorResponse(msgError)

        ) {
            @Override
            protected Map<String, String> getParams()
            {
                return entity;
            }
        };
        ConnectionController.getInstance().addToRequestQueue(postRequest);
        return "teste";
    }

    public Response.Listener<String> getResponseListener(final String msg){
       return new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                // response
                Log.d("Response", response);
                JsonParser parser = new JsonParser();

                try {
                    JSONObject json = new JSONObject(response);
                    id =  json.get("id").toString();
                    System.out.println(id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                setId(response);

            }
        };
    }

    public Response.ErrorListener getErrorResponse(final String msg){
        return  new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error){

            }
        };
    }

    public void jsonArrayRequest2() {

        JsonArrayRequest requestProds = new JsonArrayRequest(URLManager.URL_GETFILA, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray jsonArray) {
                networkObserved.doOnResponse(jsonArray);
            }

        }, getErrorResponse("Ocorreu um erro no servidor"));

        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        requestProds.setRetryPolicy(policy);
        ConnectionController.getInstance().addToRequestQueue(requestProds);
    }


    public void jsonArrayRequest(String param) {
        JsonArrayRequest requestProds = new JsonArrayRequest(param != null ? "http://192.168.74.89:8080/LoginRestful/produto/produtos" +
                "?str=" + param : URLManager.URL_GETFILA, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray jsonArray) {
                networkObserved.doOnResponse(jsonArray);
            }

        }, getErrorResponse("Ocorreu um erro no servidor"));

        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        requestProds.setRetryPolicy(policy);
        ConnectionController.getInstance().addToRequestQueue(requestProds);
    }

    public void jsonArrayRequestTwoParameters(String paramOne,String paramTwo,String url){

        JsonArrayRequest requestProds = new JsonArrayRequest(paramOne != ""|| paramTwo!= "" +
                "" ? url +
                "filter/?nome="+paramOne+"&"+"categoria="+ paramTwo : url, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray jsonArray) {
                networkObserved.doOnResponse(jsonArray);
            }

        }, getErrorResponse("Ocorreu um erro no servidor"));

        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        requestProds.setRetryPolicy(policy);
        ConnectionController.getInstance().addToRequestQueue(requestProds);
    }

}


