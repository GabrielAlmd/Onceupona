package pt.ubi.di.st.onceupona;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GameOver extends Activity
{
    SQLiteDatabase db;
    DatabaseHelper myDB = new DatabaseHelper(this);
    EditText bestplayer;
    public static int gameModeInt;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);
        Intent intentFinalText = getIntent();
        String finalText = intentFinalText.getStringExtra("finaltext");
        TextView finalTextTv = (TextView) findViewById(R.id.finalText);
        finalTextTv.setText(finalText);
        String gameMode = intentFinalText.getStringExtra("GameMode");
        if(gameMode.equals("Básico"))
            gameModeInt = 1;
        else if(gameMode.equals("Round Robin"))
            gameModeInt = 2;
        else if(gameMode.equals("Aleatório"))
            gameModeInt = 3;


        db = myDB.getWritableDatabase();
        myDB.addFinalText(db, finalText);
    }

    public void onClickSubmit(View v)
    {
        bestplayer = findViewById(R.id.bestplayer);
        String bestplayerString = bestplayer.getText().toString();
        int checkPlayerPlaying = myDB.checkPlayer(bestplayerString);
        if(checkPlayerPlaying==1)
        {
            int checkPlayerRanking = myDB.checkPlayerRankings(bestplayerString, gameModeInt);
            if(checkPlayerRanking==1)
            {
                myDB.updatePlayerScore(bestplayerString, gameModeInt);
            }
            else
            {
                myDB.addPlayerRankings(db, bestplayerString, gameModeInt);
            }
            myDB.eraseTablePlayersPlaying(db);
            Intent backMainMenu = new Intent(this, ActivityInicial.class);
            startActivity(backMainMenu);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "O nome não é válido", Toast.LENGTH_SHORT).show();
        }
    }
}
