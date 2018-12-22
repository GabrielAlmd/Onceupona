package pt.ubi.di.st.onceupona;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;


public class ActivityInicial  extends Activity
{
    DatabaseHelper databasehelper= new DatabaseHelper(this);
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);
        db = databasehelper.getWritableDatabase();
        databasehelper.eraseTablePlayersPlaying(db);
    }

    //Método associado ao Botão Jogo Básico para trocar de atividade
    public void mJogoBasico(View v){
        Intent iJogoBasico = new Intent(this, InsereJogadorBasico.class);

        startActivity(iJogoBasico);
    }


    //Método associado ao Botão Jogo Avançado para trocar de atividade
    public void mJogoAvancado(View v){
        Intent iJogoAvancado = new Intent(this, InsereJogadorAvancado.class);

        startActivity(iJogoAvancado);
    }

    //Método associado ao Botão Rankings para trocar de atividade
    public void mRankings(View v)
    {
        Intent iRankings = new Intent(this, Scores.class);
        startActivity(iRankings);
    }

    //Método associado ao Botão Previous Texts para trocar de atividade
    public void mPreviousTexts(View v)
    {
        Intent iPreviousTexts = new Intent(this, PreviousTexts.class);
        startActivity(iPreviousTexts);
    }
}
