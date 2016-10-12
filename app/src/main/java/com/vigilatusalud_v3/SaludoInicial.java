package com.vigilatusalud_v3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.Window;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;

import clases.MainActivity;

public class SaludoInicial extends Activity {


    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

    Context contexto = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_saludo_inicial);

        copiar_dbV3();


        // Clase en la que está el código a ejecutar
        TimerTask timerTask = new TimerTask() {
            public void run() {
                // Aquí el código que queremos ejecutar.
                Log.e("APLication", "Lanzar Aplication: ");
                Lanzar_Inicio();
                Log.e("APLication", "LANZADO ");
            }
        };

        // Aquí se pone en marcha el timer cada segundo.
        Timer timer = new Timer();
        // Dentro de 0 milisegundos avísame cada 1000 milisegundos
        timer.schedule(timerTask, 3700);


    }

    public void Lanzar_Inicio() {
//		Log.e("APLication","Lanzar Inicio ");
//		Intent i=new Intent(this, PantallaPrincipal.class);
        Intent i = new Intent(this, MainActivity.class);
//		i.putExtra("P_SESION", p_sesion);	
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.saludo_inicial, menu);
        return true;
    }

    public void copiar_dbV3() {
        copiar_fichero("saludvigia");
    }


    private void copiar_fichero(String archivo) {
        try {
            InputStream IS = getApplicationContext().getAssets().open(archivo);
            OutputStream OS = new FileOutputStream(getBaseContext().getFilesDir()+"/"+archivo);
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = IS.read(buffer)) > 0) {
                OS.write(buffer, 0, length);
            }
            OS.flush();
            OS.close();
            IS.close();
            Log.e("LISTO", "Base de datos creada");
        } catch (FileNotFoundException e) {
            Log.e("ERROR", "Archivo no encontrado, " + e.toString());
        } catch (IOException e) {
            Log.e("ERROR", "Error al copiar la Base de Datos, " + e.toString());
        }

    }


}
