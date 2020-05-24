package com.example.covidspot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MainAdapterRegistros extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<Casos> listCasos;

    public MainAdapterRegistros(Context c,List<Casos> caso){
        context = c;
        this.listCasos = caso;
    }

    @Override
    public int getCount() {
        return listCasos.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null){
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView == null){
            convertView = inflater.inflate(R.layout.row_item_registro,null);
        }

        Casos caso = new Casos();
        caso = listCasos.get(position);

        TextView textView = convertView.findViewById(R.id.txt_reg_cidade);
        textView.setText(caso.getCidade());

        TextView textView1 = convertView.findViewById(R.id.txt_reg_nome);
        textView1.setText(caso.getNome());

        TextView textView2 = convertView.findViewById(R.id.txt_reg_caso);
        textView2.setText(caso.getCaso());


        return convertView;
    }
}
