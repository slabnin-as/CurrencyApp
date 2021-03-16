package com.example.currencyapp.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.currencyapp.R;

import java.util.List;

public class CurrencyAdapter extends ArrayAdapter<CurrencyItem> {
    private LayoutInflater inflater;
    private List<CurrencyItem> currencyItems;

    public CurrencyAdapter(@NonNull Context context, int resource, List<CurrencyItem> listItem, LayoutInflater inflater) {
        super(context, resource, listItem);
        this.inflater = inflater;
        this.currencyItems = listItem;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        CurrencyItem currencyItem = getItem(position);

        if (convertView == null) {
            viewHolder = new ViewHolder();

            convertView = inflater.inflate(R.layout.currency_item, null, true);

            viewHolder.charCode = convertView.findViewById(R.id.textViewCharCode);
            viewHolder.name = convertView.findViewById(R.id.textViewName);
            viewHolder.test = convertView.findViewById(R.id.textViewTest);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.charCode.setText(currencyItem.getCharCode());
        viewHolder.name.setText(currencyItem.getName());

        return convertView;
    }

    public void update(List<CurrencyItem> currencyItems) {
        this.currencyItems.clear();
        this.currencyItems.addAll(currencyItems);
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        TextView charCode;
        TextView name;
        TextView test;
    }
}
