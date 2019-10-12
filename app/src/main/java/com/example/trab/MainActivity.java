package com.example.trab;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private TextView id;
    private TextView email;
    private TextView First_name;
    private TextView Last_name;
    private ImageView avatar;
    private ProgressDialog load;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadUsuario download = new DownloadUsuario();

        id = (TextView) findViewById(R.id.textView13);
        email = (TextView) findViewById(R.id.textView14);
        First_name = (TextView) findViewById(R.id.textView15);
        Last_name = (TextView) findViewById(R.id.textView16);
        avatar = (ImageView) findViewById(R.id.imageView);

        //Chama Async Task
        download.execute();
    }

    private class DownloadUsuario extends AsyncTask<Void, Void, Usuario> {

        @Override
        protected void onPreExecute() {
            //inicia o dialog
            load = ProgressDialog.show(MainActivity.this,
                    "Aguarde ...", "Obtendo Informações...");
        }

        @Override
        protected Usuario doInBackground(Void... params) {
            Conversor util = new Conversor();
            return util.getInformacao("reqres.in/API/user/1");
        }
    }


    protected void onPostExecute(Usuario usuario) {
        id.setText(usuario.getId());
        email.setText(usuario.getEmail());
        First_name.setText(usuario.getFirst_name());
        Last_name.setText(usuario.getLast_name());
        avatar.setImageBitmap(usuario.getAvatar());
        load.dismiss(); //deleta o dialog
    }
}
