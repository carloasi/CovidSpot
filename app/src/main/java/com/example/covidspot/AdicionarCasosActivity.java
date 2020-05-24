package com.example.covidspot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdicionarCasosActivity extends AppCompatActivity {

    private String nome;
    private Spinner caso;
    private Spinner sexo;
    private String cidade;
    private DAO dao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_casos);


        Spinner spinner = (Spinner) findViewById(R.id.spinnercaso);
        Spinner spinnersEXO = (Spinner) findViewById(R.id.spinnerSexo);

        List<String> listcasos = new ArrayList<>(Arrays.asList("CASO","SUSPEITO","CONFIRMADO","OBITO"));
        List<String> listsexo = new ArrayList<>(Arrays.asList("SEXO","MASCULINO","FEMININO","OUTROS"));

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listcasos );
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listsexo );
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnersEXO.setAdapter(dataAdapter2);

        dao = new DAO(this);

    }


    public void AddCaso(View view){

        EditText nome_ = findViewById(R.id.edit_nome);
        EditText cidade_ = findViewById(R.id.edit_cidade);
        nome = nome_.getText().toString();
        cidade = cidade_.getText().toString();
        caso = (Spinner)findViewById(R.id.spinnercaso);
        sexo = (Spinner)findViewById(R.id.spinnerSexo);

        if(nome.toString() == "" || nome.equals("nome"))
        {
            Toast.makeText(this, "Infome o nome", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if(cidade.toString() == "" || cidade.equals("CIDADE"))
            {
                Toast.makeText(this, "Infome a cidade", Toast.LENGTH_SHORT).show();
            }
            else
            {
                if(sexo.getSelectedItem().toString() == "" || sexo.getSelectedItem().toString().equals("SEXO"))
                {
                    Toast.makeText(this, "Infome o sexo", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(caso.getSelectedItem().toString() == "" || caso.getSelectedItem().toString().equals("CASO"))
                    {
                        Toast.makeText(this, "Infome o caso", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Casos casos = new Casos();
                        casos.setNome(nome.toString());
                        casos.setCidade(cidade.toString());
                        casos.setSexo(sexo.getSelectedItem().toString());
                        casos.setCaso(caso.getSelectedItem().toString());


                        long id = dao.inserirCasos(casos);

                        Toast.makeText(this, "Novo caso Adiciondo id:"+ id, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }



    }

    public void verCaso(View view)
    {
        Intent intent = new Intent(this,ListarCasosActivity.class);
        startActivity(intent);
    }

    public void editarCaso(View view)
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
