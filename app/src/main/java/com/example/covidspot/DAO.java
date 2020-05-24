package com.example.covidspot;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DAO {
    private Conexao conexao;
    private SQLiteDatabase banco;
    private  List<Casos>  ListCidade;

    public DAO(Context context) {
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }


    //usuarios

    public long inserir(Usuarios aluno) {
        ContentValues values = new ContentValues();
        values.put("nome", aluno.getNome());
        values.put("login", aluno.getLogin());
        values.put("senha", aluno.getSenha());
        return banco.insert("usuario",null,values);
    }

    public Boolean UsuarioExist(String login){

        Boolean result = false;
        String Log;

        Cursor cursor = banco.rawQuery("SELECT login FROM usuario WHERE  login = '"+login+"'", null);

        while (cursor.moveToNext()){
            Log = cursor.getString(0);

            if(Log.equals(login))
            {
                result = true;
                break;
            }
        }

        return result;
    }

    public Boolean validarLoginSenha(String login, String senha){

        Boolean result = false;
        String Log;
        String Sen;

        Cursor cursor = banco.rawQuery("SELECT login,senha FROM usuario WHERE  login = '"+login+"'", null);
        while (cursor.moveToNext()){
            Log = cursor.getString(0);
            Sen = cursor.getString(1);

            if(Log.equals(login) && Sen.equals(senha))
            {
                result = true;
                break;
            }
        }

        return result;
    }

    //casos

    public long inserirCasos(Casos caso) {

        ContentValues values = new ContentValues();
        values.put("nome", caso.getNome());
        values.put("cidade", caso.getCidade());
        values.put("caso", caso.getCaso());
        values.put("sexo", caso.getSexo());
        return banco.insert("casos",null,values);
    }

    public List<Casos> ListarCasos(){
        List<Casos> casos = new ArrayList<>();
        Cursor cursor = banco.query("casos",new String[]{"id","nome","cidade","caso","sexo"},null,null,null,null,null);

        while (cursor.moveToNext()){
            Casos c = new Casos();
            c.setId(cursor.getInt(0));
            c.setNome(cursor.getString(1));
            c.setCidade(cursor.getString(2));
            c.setCaso(cursor.getString(3));
            c.setSexo(cursor.getString(4));
            casos.add(c);
        }

        return casos;
    }

    public Casos ListarCasos(String id){

        Cursor cursor = banco.rawQuery("SELECT id,nome,cidade,caso,sexo FROM casos WHERE  id = '"+id+"'", null);
        Casos c = new Casos();

        while (cursor.moveToNext()){

            c.setId(cursor.getInt(0));
            c.setNome(cursor.getString(1));
            c.setCidade(cursor.getString(2));
            c.setCaso(cursor.getString(3));
            c.setSexo(cursor.getString(4));
        }

        return c;
    }

    public List<Casos> ListarCasosCidade(String cidade){
        List<Casos> casos = new ArrayList<>();

        String b = cidade;
        Cursor cursor = banco.rawQuery("SELECT id,nome,cidade,caso,sexo FROM casos where cidade like '%"+cidade+"%'", null);

        while (cursor.moveToNext()){
            Casos c = new Casos();
            c.setId(cursor.getInt(0));
            c.setNome(cursor.getString(1));
            c.setCidade(cursor.getString(2));
            c.setCaso(cursor.getString(3));
            c.setSexo(cursor.getString(4));
            casos.add(c);
        }

        return casos;
    }

    public void RemoverCaso(String id)
    {
        banco.delete("casos", "id = ?", new String[]{id});
    }

    public void AtualizarCaso(Casos caso)
    {
        ContentValues values = new ContentValues();
        values.put("nome", caso.getNome());
        values.put("cidade", caso.getCidade());
        values.put("caso", caso.getCaso());
        values.put("sexo", caso.getSexo());
        banco.update("casos",values, "id = ?", new String[]{ Integer.toString(caso.getId())});

    }

    public List<Casos> ListarCasosCidade(){
        List<Casos> casos = new ArrayList<>();
        Cursor cursor = banco.query("casos",new String[]{"id","nome","cidade","caso","sexo"},null,null,null,null,null);

        while (cursor.moveToNext()){
            Casos c = new Casos();
            c.setId(cursor.getInt(0));
            c.setNome(cursor.getString(1));
            c.setCidade(cursor.getString(2));
            c.setCaso(cursor.getString(3));
            c.setSexo(cursor.getString(4));
            casos.add(c);
        }

        //Agrupa os dados.

        ListCidade = new ArrayList<>();

        //Filtra as cidades distintas
        for (int i = 0; i < casos.size(); ++i) {
            Casos obj = (Casos)casos.get(i);
            addCidade(obj.getCidade());
        }

        //Conta os casos por cidade
        for (int i = 0; i < ListCidade.size(); ++i) {
            for (int j = 0; j < casos.size(); ++j) {
                //Casos obj = (Casos)casos.get(i);

                if(ListCidade.get(i).getCidade().equals(casos.get(j).getCidade()))
                {
                    int value = 0;

                    switch (casos.get(j).getCaso())
                    {
                        case "SUSPEITO":
                        {
                            //myList.get(3).setEmail("new email");

                            value = casos.get(j).getSuspeitos();
                            value ++;
                            ListCidade.get(i).setSuspeitos(value);

                            break;
                        }

                        case "CONFIRMADO":
                        {
                            value = casos.get(j).getConfirmados();
                            value ++;
                            ListCidade.get(i).setConfirmados(value);

                            break;
                        }

                        case "OBITO":
                        {
                            value = casos.get(j).getMortos();
                            value ++;
                            ListCidade.get(i).setMortos(value);

                            break;
                        }
                    }
                }
            }
        }


        return ListCidade;
    }

     public void addCidade(String cidade)
     {
         boolean exist = false;
         for (int i = 0; i < ListCidade.size(); ++i) {

             if(ListCidade.get(i).getCidade().equals(cidade)) {
                exist = true;

                break;
             }
         }

         if(exist == false) {
            //Adiciona a nova cidade na lista

             Casos caso = new Casos();
             caso.setCidade(cidade);
             caso.setConfirmados(0);
             caso.setMortos(0);
             caso.setSuspeitos(0);

             ListCidade.add(caso);
         }


     }


}
