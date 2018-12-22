package pt.ubi.di.st.onceupona;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Joga extends Activity {
    DatabaseHelper myDb = new DatabaseHelper(this);
    SQLiteDatabase db;
    TextView tJogadores;
    TextView tTexto;
    EditText eTexto;
    Intent iPreviousAct;
    String Jog1;
    String Jog2;
    String Jog;
    String aux = "Vez de: ";
    public static List<String> currentText;
    public static List<String>  playerNames;
    public static int rounds = 6, flag=0, iterator=0, caracteres;
    public static String texto="";
    public int ModoInt;
    public static String GameMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joga);
        currentText = new ArrayList<>();
        playerNames = new ArrayList<>();
        tJogadores = findViewById(R.id.label2);
        iPreviousAct=getIntent();
        ModoInt = Integer.parseInt(iPreviousAct.getStringExtra("modo"));

        tTexto = findViewById(R.id.mostrapalavras);
        eTexto = findViewById(R.id.insereTex);
        if(ModoInt==0)
        {
            GameMode = "Básico";
            Jog1 = iPreviousAct.getStringExtra("jog1");
            Jog2 = iPreviousAct.getStringExtra("jog2");
            aux = aux+Jog1;
            tJogadores.setText(aux);
        }
        else if(ModoInt==1)
        {
            playerNames = myDb.getCurrentPlayers();
            rounds = Integer.parseInt(iPreviousAct.getStringExtra("RONDAS"));
            caracteres = Integer.parseInt(iPreviousAct.getStringExtra("CARACTERES"));
            eTexto.setFilters(new InputFilter[] {new InputFilter.LengthFilter(caracteres)});
            System.out.println(caracteres);
            GameMode = iPreviousAct.getStringExtra("GameMode");
            if(GameMode.equals("Aleatório"))
            {
                Collections.shuffle(playerNames);
            }
            Jog = playerNames.get(iterator);
            aux = aux+Jog;
            tJogadores.setText(aux);
        }
    }

    public void onClickConfirma(View v)
    {
        String textoEditT=eTexto.getText().toString();
        texto = texto + " "+textoEditT;
        currentText = new ArrayList<String>(Arrays.asList(texto.split(" ")));
        if(currentText.size()>=4)
        {
            textoEditT = "... " + currentText.get(currentText.size()-2) +" "+ currentText.get(currentText.size()-1);
            tTexto.setText(textoEditT);
        }
        else {

            tTexto.setText(texto);
        }
        eTexto.setText("");//
        aux = "Vez de: ";

        if(ModoInt==0)
        {
            rounds--;
            if(flag == 0)
            {
                aux = aux + Jog2;
                flag = 1;
            }
            else {
                aux = aux + Jog1;
                flag = 0;
            }
        }
        else if(ModoInt==1)
        {
            if(iterator==playerNames.size()-1)
            {
                if(GameMode.equals("Aleatório"))
                {
                    Collections.shuffle(playerNames);
                }
                iterator = 0;
                rounds--;
                Jog = playerNames.get(iterator);
            }
            else {
                iterator++;
                Jog = playerNames.get(iterator);
            }
            aux = aux + Jog;
        }

        tJogadores.setText(aux);
        if(rounds==0)
        {
            Intent intentGameover = new Intent(this, GameOver.class);
            intentGameover.putExtra("finaltext", texto);
            intentGameover.putExtra("GameMode", GameMode);
            texto="";
            rounds = 6;
            startActivity(intentGameover);
        }

    }

}

