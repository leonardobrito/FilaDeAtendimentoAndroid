package com.example.roanderson.listagem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import Util.Criptografia;
import br.com.jansenfelipe.androidmask.MaskEditTextChangedListener;

public class ActivityLogin extends AppCompatActivity {
    String cpfCripto;
    private EditText edtCpf;
    private MaskEditTextChangedListener maskCPF;
    private Button botao;
    private String cpfAux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edtCpf = (EditText)findViewById(R.id.cpf);
        maskCPF  = new MaskEditTextChangedListener("###.###.###-##", edtCpf);
        cpfCripto = edtCpf.getText().toString();
        botao = (Button) findViewById(R.id.signin_button);


        edtCpf.addTextChangedListener(maskCPF);
        Criptografia criptografia = new Criptografia();

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cpfAux = (edtCpf.getText().toString());
                cpfAux = cpfAux.replace(".", "");
                cpfAux = cpfAux.replace("-", "");
                System.out.println("CPF " + cpfAux);
            }
        });


        String aidento = criptografia.bin2hex(criptografia.getHash(cpfCripto));
        System.out.println(aidento.toLowerCase());

    }

}
