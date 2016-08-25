package com.example.roanderson.listagem.models;

/**
 * Created by Roanderson on 24/08/2016.
 */
public class ListEspera {
    String nome;
    int status;
    String hora;

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
