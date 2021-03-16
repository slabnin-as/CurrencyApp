package com.example.currencyapp.model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonParser {
    public List<CurrencyItem> getAllCurrencies(String uri){
        Map<String, CurrencyItem> currencyItemMap = new HashMap<>();

        try{
            URL url = new URL(uri);
            InputStreamReader reader = new InputStreamReader(url.openStream());

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            JsonObject valute = jsonObject.getAsJsonObject("Valute");
            currencyItemMap = gson.fromJson(valute, new TypeToken<Map<String, CurrencyItem>>(){}.getType());
        } catch (IOException e){
            e.getCause();
        }
        List<CurrencyItem> currencies = new ArrayList<>(currencyItemMap.values());

        return currencies;
    }
}
