package ru.semenovmy.learning.currenciesconverter.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import ru.semenovmy.learning.currenciesconverter.data.model.Currency;

public class CurrencyAdapter extends BaseAdapter {

    private final List<Currency> mCurrencies;

    public CurrencyAdapter(@NonNull List<Currency> currencies) {
        mCurrencies = new ArrayList<>(currencies);
    }

    @Override
    public int getCount() {
        return mCurrencies.size();
    }

    @Override
    public Currency getItem(int position) {

        return mCurrencies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
            Holder holder = new Holder(convertView);
            convertView.setTag(holder);
        }
        Currency currency = getItem(position);
        Holder holder = (Holder) convertView.getTag();
        if (currency != null) {
            String text = currency.getName();
            holder.mText.setText(text);
        }
        return convertView;
    }

    private static class Holder {
        private TextView mText;

        private Holder(View view) {
            mText = view.findViewById(android.R.id.text1);
        }
    }
}
