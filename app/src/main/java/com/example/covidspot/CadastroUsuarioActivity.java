package com.example.covidspot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private EditText nome;
    private EditText login;
    private EditText senha;
    private Button Btn_acessar;
    private Button Btn_logar;
    private DAO dao;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Btn_logar.setVisibility(View.GONE);
            Btn_acessar.setVisibility(View.VISIBLE);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        nome = findViewById(R.id.edit_nome);
        login = findViewById(R.id.edit_login);
        senha = findViewById(R.id.edit_senha);
        Btn_acessar = findViewById(R.id.btn_acessar);
        Btn_logar = findViewById(R.id.btn_cadastrar);

        dao = new DAO(this);
    }

    public void salvar(View view){


        if(nome.getText().toString() == "") {
            Toast.makeText(this, "Infome o nome de usuário", Toast.LENGTH_SHORT).show();
        }else{
            if(login.getText().toString() == "") {
                Toast.makeText(this, "Infome o login de usuário", Toast.LENGTH_SHORT).show();
            }else{
                if(senha.getText().toString() == "") {
                    Toast.makeText(this, "Infome a senha de usuário", Toast.LENGTH_SHORT).show();
                }else{

                    boolean value = dao.UsuarioExist(login.getText().toString());

                    if(value == true) {
                        Toast.makeText(this, "Usuario já cadastrado", Toast.LENGTH_SHORT).show();
                    }else {
                        Usuarios u = new Usuarios();
                        u.setNome(nome.getText().toString());
                        u.setLogin(login.getText().toString());
                        u.setSenha(senha.getText().toString());

                        handler.postDelayed(runnable,1000);

                        long id = dao.inserir(u);
                        Toast.makeText(this, "Usuario inserido com id: " + id, Toast.LENGTH_SHORT).show();

                        //rellay1.setVisibility(View.VISIBLE);
                    }
                }
            }
        }




    }

    public void acessar(View view)
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void voltar(View view)
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


}
