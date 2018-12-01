package pt.ubi.di.st.onceupona;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class ActivityInicial  extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);
    }

    //Método associado ao Botão Jogo Básico para trocar de atividade
    public void mJogoBasico(View v){
        Intent iJogoBasico = new Intent(this, InsereJogadorBasico.class);

        startActivity(iJogoBasico);
    }
    /****************************************************************
    //Método associado ao Botão Jogo Avançado para trocar de atividade
    public void mJogoAvancado(View v){
        Intent iJogoAvancado = new Intent(this, InsereJogadorAvancado.class);

        startActivity(iJogoAvancado);
    }

    //Método associado ao Botão Exit para trocar de atividade
    public void mJogoAvancado(View v){
        Intent iExit = new Intent(this, Exit.class);

        startActivity(iExit);
    }*****************************************************************/

}
