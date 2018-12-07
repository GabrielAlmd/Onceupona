package pt.ubi.di.st.onceupona;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Joga extends Activity { //boas duas vezes
    DatabaseHelper myDb = new DatabaseHelper(this);
    SQLiteDatabase db;
    TextView tJogadores;
    TextView tTexto;
    EditText eTexto;
    Intent iPreviousAct;
    String Jog1;
    String Jog2;
    String textoTextV="";
    String aux = "Vez de: ";
    public static List<String> currentText;
    public static int rounds = 6, flag=0;
    public static String texto="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joga);
        currentText = new ArrayList<>();
        tJogadores = findViewById(R.id.label2);
        iPreviousAct=getIntent();
        int ModoInt = Integer.parseInt(iPreviousAct.getStringExtra("modoB"));
        if(ModoInt==0)
        {
            Jog1 = iPreviousAct.getStringExtra("jog1");
            Jog2 = iPreviousAct.getStringExtra("jog2");
            aux = aux+Jog1;
            tJogadores.setText(aux);
        }


        tTexto = findViewById(R.id.mostrapalavras);
        eTexto = findViewById(R.id.insereTex);

    }

    public void onClickConfirma(View v){
        String textoEditT=eTexto.getText().toString();
        texto = texto + " "+textoEditT;
        currentText = new ArrayList<String>(Arrays.asList(texto.split(" ")));
        System.out.println("TAMANHO AQUI CARALHO: "+currentText.size());
        System.out.println("ARRAYLIST DAS PALAVRAS CARALHO: "+currentText);
        //textoTextV = textoTextV+" "+textoEditT;
        if(currentText.size()>=4)
        {
            textoEditT = "... " + currentText.get(currentText.size()-2) +" "+ currentText.get(currentText.size()-1);
            tTexto.setText(textoEditT);
            //currentText = new ArrayList<String>(Arrays.asList(textoEditT.split(" ")));
        }
        else {

            tTexto.setText(texto);
        }
        eTexto.setText("");//
        rounds--;
        aux = "Vez de: ";
        if(flag == 0)
        {
            aux = aux+Jog2;
            flag = 1;
        }
        else
        {
            aux = aux+Jog1;
            flag = 0;
        }
        tJogadores.setText(aux);
        if(rounds==0)
        {
            db = myDb.getWritableDatabase();
            myDb.addFinalText(db, textoTextV);
            Intent intentGameover = new Intent(this, GameOver.class);
            intentGameover.putExtra("finaltext", textoTextV);
            startActivity(intentGameover);
        }

    }



}

