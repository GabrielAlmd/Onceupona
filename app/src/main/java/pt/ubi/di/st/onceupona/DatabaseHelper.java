package pt.ubi.di.st.onceupona;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "Onceupona.db";
    public static final String PLAYERSPLAYING_TABLE = "PlayersPlaying";
    public static final String PLAYERSPLAYING_ID = "ID";
    public static final String PLAYERSPLAYING_NAME = "NAME";
    public static final String FINALTEXTS_TABLE = "FinalTexts";
    public static final String FINALTEXTS_ID = "ID";
    public static final String FINALTEXTS_CONTENT = "CONTENT";
    public static final String PLAYERS_TABLE = "Players";
    public static final String PLAYERS_ID = "ID";
    public static final String PLAYERS_NAME = "NAME";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE "+PLAYERSPLAYING_TABLE+"("+PLAYERSPLAYING_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+PLAYERSPLAYING_NAME+" TEXT);");
        db.execSQL("CREATE TABLE "+FINALTEXTS_TABLE+ "("+FINALTEXTS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+FINALTEXTS_CONTENT+" TEXT)");
        db.execSQL("CREATE TABLE "+PLAYERS_TABLE+ "("+PLAYERS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+PLAYERS_NAME+" TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

}