package com.example.covidspot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListarRegistrosActivity extends AppCompatActivity {

    private List<Casos> listCasos;
    GridView gridView;
    DAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_registros);

        dao = new DAO(this);

        listCasos = new ArrayList();
        listCasos = dao.ListarCasos();

        gridView = findViewById(R.id.grid_view_regitros);

        MainAdapterRegistros adapter = new MainAdapterRegistros(ListarRegistrosActivity.this, listCasos);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(ListarRegistrosActivity.this, GridItemActivity.class);
                i.putExtra("key", listCasos.get(position).getId());
                startActivity(i);

            }
        });

    }

    public void Editar(View view) {

    }

    public void Apagar(View view, int id) {
        Toast.makeText(getApplicationContext(), id,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume(){
        super.onResume();
        dao = new DAO(this);

        listCasos = new ArrayList();
        listCasos = dao.ListarCasos();

        gridView = findViewById(R.id.grid_view_regitros);

        MainAdapterRegistros adapter = new MainAdapterRegistros(ListarRegistrosActivity.this, listCasos);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(ListarRegistrosActivity.this, GridItemActivity.class);
                i.putExtra("key", listCasos.get(position).getId());
                startActivity(i);

            }
        });
    }

    public void LitRegistro(View view)
    {
        Intent intent = new Intent(this,ListarCasosActivity.class);
        startActivity(intent);
    }

    public void EditarRegistro(View view)
    {
        Intent intent = new Intent(this,ListarRegistrosActivity.class);
        startActivity(intent);
    }
}
