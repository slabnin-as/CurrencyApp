package com.example.currencyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.currencyapp.model.CurrencyAdapter;
import com.example.currencyapp.model.CurrencyItem;
import com.example.currencyapp.model.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String uri = "https://www.cbr-xml-daily.ru/daily_json.js";
    private Thread secThread;
    private Runnable runnable;
    private JsonParser jsonParser;
    private List<CurrencyItem> currencyItems;
    private ListView listView;
    private CurrencyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currencyItems = new ArrayList<>();

        init();

        adapter = new CurrencyAdapter(this, R.layout.currency_item, currencyItems, getLayoutInflater());
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    private void init() {
        runnable = () -> {
            jsonParser = new JsonParser();
            currencyItems = jsonParser.getAllCurrencies(uri);

            runOnUiThread(() -> adapter.update(currencyItems));
        };
        secThread = new Thread(runnable);
        secThread.start();
    }
}