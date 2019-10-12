package com.example.trab;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Conversor {

    public Usuario getInformacao(String end){
        String json = ConexAPI.getJsonFromApi(end);
        Usuario retorno = parseJson(json);
        return retorno;
    }
    private Usuario parseJson(String json) {
        try {
            Usuario usuario = new Usuario();

            JSONObject jsonObj = new JSONObject(json);
            JSONArray array = jsonObj.getJSONArray("results");

            JSONObject objArray = array.getJSONObject(0);

            JSONObject obj = objArray.getJSONObject("Usuario");
            usuario.setId(obj.getString("Id"));
            usuario.setEmail(obj.getString("Email"));
            usuario.setFirst_name(obj.getString("First Name"));
            usuario.setLast_name(obj.getString("Last Name"));
            JSONObject foto = obj.getJSONObject("picture");
            usuario.setAvatar(baixarImagem(foto.getString("large")));

            return usuario;
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }
    private Bitmap baixarImagem(String url) {
        //converte a imagem para o formato Bitmap
        try {
            URL endereco = new URL(url);
            InputStream inputStream = endereco.openStream();
            Bitmap imagem = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            return imagem;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}



