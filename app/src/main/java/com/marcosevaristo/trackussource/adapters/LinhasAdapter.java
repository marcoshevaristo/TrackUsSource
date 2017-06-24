package com.marcosevaristo.trackussource.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.marcosevaristo.trackussource.R;
import com.marcosevaristo.trackussource.model.Linha;
import com.marcosevaristo.trackussource.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class LinhasAdapter extends ArrayAdapter<Linha> {
    private List<Linha> lLinhas = new ArrayList<Linha>();
    private int layoutResId;
    private Context ctx;

    public LinhasAdapter(Context ctx, int layoutResId, List<Linha> lLinhas) {
        super(ctx, layoutResId, lLinhas);
        this.layoutResId = layoutResId;
        this.ctx = ctx;
        this.lLinhas = lLinhas;
    }

    @Override
    public int getCount() {
        return lLinhas.size();
    }

    @Override
    public Linha getItem(int pos) {
        return lLinhas.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        LinhaHolder linhaHolder = null;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layoutResId, parent, false);;
            linhaHolder = new LinhaHolder();
            TextView mainTextView = (TextView)view.findViewById(R.id.linhaBuscadaText);
            TextView subTextView = (TextView)view.findViewById(R.id.linhaBuscadaSubText);
            linhaHolder.texto = mainTextView;
            linhaHolder.subTexto = subTextView;

            view.setTag(linhaHolder);
        } else {
            linhaHolder = (LinhaHolder) view.getTag();
        }

        Linha linha = lLinhas.get(position);
        if(linha != null) {
            linhaHolder.texto.setText(linha.toStringMainTextOnly());
            if(StringUtils.isNotBlank(linha.getSubtitulo())) {
                linhaHolder.subTexto.setText(linha.getSubtitulo());
            }
        }

        return view;
    }

    private static class LinhaHolder {
        TextView texto;
        TextView subTexto;
    }
}