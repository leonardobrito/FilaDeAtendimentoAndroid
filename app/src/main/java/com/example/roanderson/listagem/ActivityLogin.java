package com.example.roanderson.listagem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import Util.Criptografia;
import br.com.jansenfelipe.androidmask.MaskEditTextChangedListener;

public class ActivityLogin extends AppCompatActivity {
    String cpfCripto;
    private EditText edtCpf;
    private MaskEditTextChangedListener maskCPF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edtCpf = (EditText)findViewById(R.id.cpf);
        maskCPF  = new MaskEditTextChangedListener("###.###.###-##", edtCpf);
        edtCpf.setText("123");
        cpfCripto = edtCpf.getText().toString();

        edtCpf.addTextChangedListener(maskCPF);
        Criptografia criptografia = new Criptografia();


        String aidento = criptografia.bin2hex(criptografia.getHash(cpfCripto));
        System.out.println(aidento.toLowerCase());

    }

}
