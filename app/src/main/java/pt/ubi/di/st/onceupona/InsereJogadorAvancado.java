package pt.ubi.di.st.onceupona;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class InsereJogadorAvancado extends Activity {
//
    SQLiteDatabase db;
    DatabaseHelper myDB = new DatabaseHelper(this);

    //EditText
    EditText eNome,eCaracter, eRondas;

    int iCatacter=0,iRondas;

    //Spinner
    String[] modo={"Round Robin","Aleatório"};
    Spinner spModo;

    String sNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insere_jogador_avancado);

        spModo = findViewById(R.id.sp3);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, modo);
        spModo.setAdapter(adapter);
    }

    public void addJogador(View v){
        db=myDB.getWritableDatabase();

        eNome = findViewById(R.id.et1);
        sNome = eNome.getText().toString().trim();

        if(sNome.equals("")){
            Toast.makeText(getApplicationContext(),"Introduza o seu Nome!",Toast.LENGTH_LONG).show();
        }
        myDB.addPlayer(db,sNome);
    }

    public void bSeguinte (View view){
        Intent iJogoAvancado = new Intent(this, Joga.class);

        //Numero de Caracteres
        eCaracter = findViewById(R.id.eCarateres);
        iCatacter = Integer.parseInt(eCaracter.getText().toString());
        iJogoAvancado.putExtra("CARACTERES",iCatacter);

        //Modo de Jogo
        String sAux = spModo.getSelectedItem().toString();
        iJogoAvancado.putExtra("MODO", sAux);

        //Numero de Rondas
        eRondas = findViewById(R.id.eRonda);
        iRondas = Integer.parseInt(eRondas.getText().toString());
        iJogoAvancado.putExtra("RONDAS",iRondas);

        startActivity(iJogoAvancado);
    }

}
