package com.example.roanderson.listagem;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.roanderson.listagem.adapters.ListEsperaAdapter;
import com.example.roanderson.listagem.models.ListEspera;
import com.example.roanderson.listagem.network.NetworkManager;
import com.example.roanderson.listagem.network.NetworkObserved;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import Util.URLManager;

public class MainActivity extends AppCompatActivity implements NetworkObserved{
    RecyclerView recyclerView;
    //ArrayList<ListEspera> Items;
    private static ArrayList<ListEspera> Items = new ArrayList<ListEspera>();
    private ListEsperaAdapter listEsperaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executarRequestFila();
                //atualizaLista();
            }
        });


        /*Items = new ArrayList<>();
        listEspera1 = new ListEspera();
        listEspera2 = new ListEspera();
        listEspera3 = new ListEspera();
        listEspera4 = new ListEspera();
        listEspera5 = new ListEspera();
        listEspera7 = new ListEspera();
        listEspera6 = new ListEspera();
        listEspera1.setNome("Afonso");
        listEspera1.setStatus("Atendido");
        Items.add(listEspera1);


        listEspera2.setNome("Beruwilson");
        listEspera2.setStatus("Atendido");
        Items.add(listEspera2);
        listEspera3.setNome("Celso");
        listEspera3.setStatus("Em Atendimento");
        Items.add(listEspera3);

        listEspera4.setNome("Rafael");
        listEspera4.setStatus("Em espera");
        Items.add(listEspera4);

        listEspera5.setNome("Ricardo");
        listEspera5.setStatus("Em espera");
        Items.add(listEspera5);

        listEspera6.setNome("Roanderson Felipe de Souza Pinheiro");
        listEspera6.setStatus("Em espera");
        Items.add(listEspera6);
        listEspera7.setNome("Maria da Saúde");
        listEspera7.setStatus("Em espera");
        Items.add(listEspera7);*/
        executarRequestFila();
        atualizaLista();
        for (int i=0;i<Items.size();i++){

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void doOnResponse(JSONArray jsonArray) {
        converteListaFila(jsonArray);
    }

    @Override
    public void doOnReturnID(String string) {

    }
    private void executarRequestFila() {
        Items.clear();
        //param = encodeUTF8(param);
        NetworkManager networkManager = new NetworkManager();
        networkManager.setNetworkObserved(this);
        networkManager.jsonArrayRequest(null);
    }

    private String encodeUTF8(String param) {
        if (param != null) {
            try {
                param = URLEncoder.encode(param, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return param;
    }

    public void atualizaLista(){
       /* produtoAdapter = new ProdutoListAdapter(this,produtos);
        produtoAdapter.setRecyclerViewOnClickListenerHack(this);
        recyclerView = (RecyclerView) findViewById(R.id.rv_list);
        recyclerView.setAdapter(produtoAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));*/

        listEsperaAdapter =new ListEsperaAdapter(this,Items);
        recyclerView= (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new VerticalSpace(50));
        recyclerView.setAdapter(listEsperaAdapter);
    }

    //Converte a lista de filas
    public void converteListaFila(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                ListEspera lista = new ListEspera();
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                lista = converteLista(jsonObj);
                Items.add(lista);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        getSetListFila();
        listEsperaAdapter.notifyDataSetChanged();
    }

    //converte só um Fila;
    public ListEspera converteLista(JSONObject jsonObj) throws JSONException {
        ListEspera listEspera = new ListEspera();
        listEspera.setNome(jsonObj.getString("nome"));
        listEspera.setStatus(Integer.parseInt(jsonObj.getString("status")));
        listEspera.setHora(jsonObj.getString("hora"));
        return listEspera;
    }

    public List<ListEspera> getSetListFila() {
        List<ListEspera> mListAux = Items;
        return mListAux;
    }


}
