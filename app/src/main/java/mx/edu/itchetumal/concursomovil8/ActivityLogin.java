package mx.edu.itchetumal.concursomovil8;

/**
 * Created by centroinnovacion3 on 13/11/15.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;


public class ActivityLogin extends Activity implements OnClickListener {

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //obteniendo una instancia
        Button iniciar = (Button)findViewById(R.id.btnEntrar);
        //Registrando el escuchador
        iniciar.setOnClickListener(this);

    }

    @Override

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEntrar:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            case R.id.btnSalir:
                Intent salir = new Intent(this,ActivitySplash.class);
                finish();
        }
    }

}