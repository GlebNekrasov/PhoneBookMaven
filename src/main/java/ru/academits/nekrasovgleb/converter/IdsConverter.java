package ru.academits.nekrasovgleb.converter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class IdsConverter {
    private Gson gson = new GsonBuilder().create();

    public int[] convertFromJson(String idsJson) {
        return gson.fromJson(idsJson, int[].class);
    }
}
