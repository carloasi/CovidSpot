package com.example.covidspot;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GridItemActivity extends AppCompatActivity {
    String id;
    DAO dao;
    private String nome;
    private Spinner caso;
    private Spinner sexo;
    private String cidade;
    List<String> listcasos;
    List<String> listsexo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_item);
        Intent intent = getIntent();

        dao = new DAO(this);
        id = intent.getSerializableExtra("key").toString();

        listcasos = new ArrayList<>(Arrays.asList("CASO","SUSPEITO","CONFIRMADO","OBITO"));
        listsexo = new ArrayList<>(Arrays.asList("SEXO","MASCULINO","FEMININO","OUTROS"));

        Spinner spinner = (Spinner) findViewById(R.id.spinnercaso);
        Spinner spinnersEXO = (Spinner) findViewById(R.id.spinnerSexo);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listcasos );
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listsexo );
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnersEXO.setAdapter(dataAdapter2);

        setValues();
    }

    public  void setValues()
    {
        Casos caso = new Casos();
        caso = dao.ListarCasos(id);

        int spinnerCaso = 0;
        int spinnerSexo = 0;

        switch (caso.getCaso())
        {
            case "SUSPEITO":
            {
                spinnerCaso = 1;
                break;
            }
            case "CONFIRMADO":
            {
                spinnerCaso = 2;
                break;
            }
            case "OBITO":
            {
                spinnerCaso = 3;
                break;
            }
        }

        switch (caso.getSexo())
        {
            case "MASCULINO":
            {
                spinnerSexo = 1;
                break;
            }
            case "FEMININO":
            {
                spinnerSexo = 2;
                break;
            }
            case "OUTROS":
            {
                spinnerSexo = 3;
                break;
            }
        }


        TextView textView1 = findViewById(R.id.editar_nome);
        textView1.setText(caso.getNome());

        TextView textView2 = findViewById(R.id.editar_cidade);
        textView2.setText(caso.getCidade());

        Spinner spinner = (Spinner) findViewById(R.id.spinnercaso);
        spinner.setSelection(spinnerCaso);

        Spinner spinnersEXO = (Spinner) findViewById(R.id.spinnerSexo);
        spinnersEXO.setSelection(spinnerSexo);

    }

    public void VerCasos(View view)
    {
        Intent intent = new Intent(this,ListarRegistrosActivity.class);
        startActivity(intent);
    }

    public void Alterar(View view)
    {
        EditText nome_ = findViewById(R.id.editar_nome);
        EditText cidade_ = findViewById(R.id.editar_cidade);
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
                        casos.setId(Integer.parseInt(id));
                        dao.AtualizarCaso(casos);

                        Toast.makeText(this, "caso alterado", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }


    }

    public void Remover(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("REMOVER CASO");
        builder.setMessage("Tem certeza que deseja remover esse caso?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                dao.RemoverCaso(id);
                Toast.makeText(getApplicationContext(), "Caso removido", Toast.LENGTH_SHORT).show();
                finish();
            }

        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    @Override
    public void onResume(){
        super.onResume();

    }
}
