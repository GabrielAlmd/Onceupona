package pt.ubi.di.st.onceupona;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class InsereJogadorAvancado extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insere_jogador_avancado);


    }

    public void bSeguinte (View view){
        Intent iJogoAvancado = new Intent(this, InsereJogadorBasico.class);
        startActivity(iJogoAvancado);
        //dsuiafhasdkuhfiaosuhf
    }

}
