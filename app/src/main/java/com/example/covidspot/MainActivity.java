package com.example.covidspot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RelativeLayout rellay1, rellay2;
    ImageView logo;
    Handler handler = new Handler();
    private EditText login;
    private EditText senha;
    DAO dao;

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
            logo.getLayoutParams().height = 200;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rellay1 = (RelativeLayout)findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout)findViewById(R.id.rellay2);
        logo = (ImageView) findViewById(R.id.imgView_logo);

        handler.postDelayed(runnable,3000);

        dao = new DAO(this);
    }

    public void acessar(View view)
    {
        Intent intent = new Intent(this,CadastroUsuarioActivity.class);
        startActivity(intent);
    }

    public void logar(View view)
    {
        login = findViewById(R.id.userLogin);
        senha = findViewById(R.id.userSenha);
        String l = login.getText().toString();
        String s = senha.getText().toString();

        if(l.equals("")) {
            Toast.makeText(this, "Infome o login de usuário", Toast.LENGTH_SHORT).show();
        }else
        {
            if(s.equals("")) {
                Toast.makeText(this, "Infome a senha de usuário", Toast.LENGTH_SHORT).show();
            }else{
               if(dao.validarLoginSenha(l, s) == true)
               {
                   Intent intent = new Intent(this,ListarCasosActivity.class);
                   startActivity(intent);
               }
               else
               {
                   Toast.makeText(this, "Login e senha invalido", Toast.LENGTH_SHORT).show();
               }
            }
        }


    }
}
