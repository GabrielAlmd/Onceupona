package pt.ubi.di.st.onceupona;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameOver extends Activity
{
    SQLiteDatabase db;
    DatabaseHelper myDB = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);
        Intent intentFinalText = getIntent();
        String finalText = intentFinalText.getStringExtra("finaltext");
        TextView finalTextTv = (TextView) findViewById(R.id.finalText);
        finalTextTv.setText(finalText);

        db = myDB.getWritableDatabase();
        myDB.eraseTablePlayersPlaying(db);
    }

    public void jogarNovo (View v){
        Intent iDeNovo = new Intent(this, ActivityInicial.class);

        startActivity(iDeNovo);
    }
}
