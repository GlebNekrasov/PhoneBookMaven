package ru.academits.nekrasovgleb.converter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.academits.nekrasovgleb.service.DeleteValidation;

public class DeleteValidationConverter {
    private Gson gson = new GsonBuilder().create();

    public String convertToJson(DeleteValidation deleteValidation) {
        return gson.toJson(deleteValidation);
    }
}
