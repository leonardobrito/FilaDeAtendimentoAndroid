package com.example.roanderson.listagem.network;

import org.json.JSONArray;


public interface NetworkObserved {
    public void doOnResponse(JSONArray jsonArray);
    public void doOnReturnID(String string);
}
