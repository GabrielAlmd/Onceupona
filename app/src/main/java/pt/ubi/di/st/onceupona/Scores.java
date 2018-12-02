package pt.ubi.di.st.onceupona;

import android.app.Activity;
import android.os.Bundle;

public class Scores extends Activity
{
    DatabaseHelper databasehelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        databasehelper.getWritableDatabase();
    }
}
