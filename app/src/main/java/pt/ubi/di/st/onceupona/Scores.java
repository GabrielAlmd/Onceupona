package pt.ubi.di.st.onceupona;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Scores extends Activity
{
    DatabaseHelper databasehelper = new DatabaseHelper(this);
    private TableLayout scoresTable;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        databasehelper.getWritableDatabase();

        ArrayList<String> rankings = databasehelper.getRankings();
        System.out.println("RANKINGS AQUI CARALHO: "+rankings);

        ArrayList<String> players = new ArrayList<>();
        ArrayList<String> scores = new ArrayList<>();
        ArrayList<String> gameModes = new ArrayList<>();
        for (int i=0; i<rankings.size(); i+=3)
            players.add(rankings.get(i));
        for (int i=1; i<rankings.size(); i+=3)
            scores.add(rankings.get(i));
        for (int i=2; i<rankings.size(); i+=3)
            gameModes.add(rankings.get(i));

        System.out.println("PLAYERS AQUI CARALHO: "+players);
        System.out.println("SCORES AQUI CARALHO: "+scores);
        System.out.println("GAMEMODES AQUI CARALHO: "+gameModes);

        scoresTable = (TableLayout) findViewById(R.id.rankingsTable);
        for(int i=0; i<players.size(); i++)
        {
            TableRow row = new TableRow(this);
            String player = players.get(i);
            String score = scores.get(i);
            String gameMode = gameModes.get(i);
            TextView t1 = new TextView(this);
            TextView t2 = new TextView(this);
            TextView t3 = new TextView(this);
            t1.setText(player);
            t2.setText(" "+score);
            t3.setText("      "+gameMode);
            row.addView(t1);
            row.addView(t2);
            row.addView(t3);
            scoresTable.addView(row);
        }

        /*int i = players.size();
        String rows[] = new String[i+1];
        rows[0] = "NOME SCORE GAMEMODE";
        for(int j=0; j<i; j++)
            rows[j+1]= players.get(j) +" " +scores.get(j) +" " + gameModes.get(j);

        ArrayAdapter<String> rankingsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, players);
        ListView rankingsListView = findViewById(R.id.rankingsTable);
        rankingsListView.setAdapter(rankingsAdapter);*/


    }
}
