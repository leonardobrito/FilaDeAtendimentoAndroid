package com.example.roanderson.listagem;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import Util.Criptografia;

public class ActivityLogin extends AppCompatActivity {
    String cpfCripto;
    private EditText edtCpf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtCpf = (EditText)findViewById(R.id.cpf);
        edtCpf.setText("123");
        cpfCripto = edtCpf.getText().toString();

        Criptografia criptografia = new Criptografia();


        String aidento = criptografia.bin2hex(criptografia.getHash(cpfCripto));
        System.out.println(aidento.toLowerCase());

    }

}
