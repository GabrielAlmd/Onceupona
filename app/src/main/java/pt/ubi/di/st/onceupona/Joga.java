package pt.ubi.di.st.onceupona;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Joga extends Activity { //boas duas vezes
    DatabaseHelper myDb = new DatabaseHelper(this);

    TextView tJogadores;
    TextView tTexto;
    EditText eTexto;

    Intent iPreviousAct;

    int ID1;
    int ID2;

    String Jog1;
    String Jog2;

    String textoTextV="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joga);

        tJogadores = findViewById(R.id.label2);
        tTexto = findViewById(R.id.mostrapalavras);
        eTexto = findViewById(R.id.insereTex);


        iPreviousAct=getIntent();

        Jog1 = iPreviousAct.getStringExtra("jog1");
        Jog2 = iPreviousAct.getStringExtra("jog2");

        String aux="Jogador 1: "+Jog1+"\nJogador 2: "+Jog2;

        tJogadores.setText(aux);
    }

    public void onClickConfirma(View v){
        String textoEditT=eTexto.getText().toString();

        textoTextV = textoTextV+" "+textoEditT;
        tTexto.setText(textoTextV);
        Log.i("TextoTextView", "onClickConfirma: "+textoTextV);
        eTexto.setText("");//

    }



}

