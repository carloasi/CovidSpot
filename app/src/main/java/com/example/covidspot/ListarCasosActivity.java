package com.example.covidspot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.GridLayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListarCasosActivity extends AppCompatActivity {

    SearchView search;
    private List<Casos> listCasos;
    GridView gridView;
    DAO dao;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_casos);

        gridView = findViewById(R.id.grid_view);
        search = (SearchView) findViewById(R.id.searchCidade);

        dao = new DAO(this);

        listCasos = new ArrayList();
        listCasos = dao.ListarCasosCidade();

        MainAdapter madapter = new MainAdapter(ListarCasosActivity.this, listCasos);
        gridView.setAdapter(madapter);

        //adapter = new ArrayAdapter<String>(this, row)

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String text) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                listCasos = dao.ListarCasosCidade(newText);

                MainAdapter madapter = new MainAdapter(ListarCasosActivity.this, listCasos);
                gridView.setAdapter(madapter);

                return false;
            }
        });



    }

    public void addCaso(View view)
    {
        Intent intent = new Intent(this,AdicionarCasosActivity.class);
        startActivity(intent);
    }

    public void EditarRegistro(View view)
    {
        Intent intent = new Intent(this,ListarRegistrosActivity.class);
        startActivity(intent);
    }


}
