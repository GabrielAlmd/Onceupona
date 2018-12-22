package pt.ubi.di.st.onceupona;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class PreviousTexts extends Activity
{
    DatabaseHelper databasehelper = new DatabaseHelper(this);
    private ArrayAdapter textsAdapter;
    private ListView textsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previoustexts);
        databasehelper.getReadableDatabase();

        ArrayList<String> texts = databasehelper.getFinalTexts();
        textsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, texts);
        textsListView = findViewById(R.id.textsList);
        textsListView.setAdapter(textsAdapter);
    }

    public void backToMain (View view){
        Intent ibackMain = new Intent(this, ActivityInicial.class);
        startActivity(ibackMain);
    }
}
