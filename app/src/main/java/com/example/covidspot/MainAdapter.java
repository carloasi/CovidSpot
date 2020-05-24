package com.example.covidspot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MainAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<Casos> listCasos;

    public MainAdapter(Context c,List<Casos> caso){
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
            convertView = inflater.inflate(R.layout.row_item,null);
        }

        Casos caso = new Casos();
        caso = listCasos.get(position);

        TextView textView = convertView.findViewById(R.id.txt_cidade);
        textView.setText(caso.getCidade());

        TextView textView2 = convertView.findViewById(R.id.txt_suspeitos);
        textView2.setText(String.valueOf(caso.getSuspeitos()));

        TextView textView3 = convertView.findViewById(R.id.txt_confirmados);
        textView3.setText(String.valueOf(caso.getConfirmados()));

        TextView textView4 = convertView.findViewById(R.id.txt_obitos);
        textView4.setText(String.valueOf(caso.getMortos()));


        return convertView;
    }
}
