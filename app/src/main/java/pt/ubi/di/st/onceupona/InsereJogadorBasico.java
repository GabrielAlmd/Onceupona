package pt.ubi.di.st.onceupona;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class InsereJogadorBasico extends Activity
{
    SQLiteDatabase db;
    DatabaseHelper databasehelper = new DatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insere_jogador_basico);
    }

    public void vamosJogar (View view) //onclik metodo do botão seguinte
    {
        EditText editText1 = (EditText) findViewById(R.id.et1);
        EditText editText2 = (EditText) findViewById(R.id.et2);
        String jogador1 = editText1.getText().toString().trim();
        String jogador2 = editText2.getText().toString().trim();

        db = databasehelper.getWritableDatabase();
        databasehelper.addPlayer(db, jogador1);
        databasehelper.addPlayer(db, jogador2);

        Intent iSeguinte = new Intent(this,Joga.class);
        iSeguinte.putExtra("jog1",jogador1);
        iSeguinte.putExtra("jog2",jogador2);

        startActivity(iSeguinte);

    }
}
