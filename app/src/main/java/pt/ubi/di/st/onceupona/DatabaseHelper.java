package pt.ubi.di.st.onceupona;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

//test
public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "Onceupona.db";
    public static final String PLAYERSPLAYING_TABLE = "PlayersPlaying";
    public static final String PLAYERSPLAYING_ID = "ID";
    public static final String PLAYERSPLAYING_NAME = "NAME";
    public static final String FINALTEXTS_TABLE = "FinalTexts";
    public static final String FINALTEXTS_ID = "ID";
    public static final String FINALTEXTS_CONTENT = "CONTENT";
    public static final String RANKINGS_TABLE = "Rankings";
    public static final String RANKINGS_ID = "ID";
    public static final String RANKINGS_PLAYER_NAME = "PLAYER_NAME";
    public static final String RANKINGS_PLAYER_SCORE = "PLAYER_SCORE";
    public static final String RANKINGS_GAME_MODE = "GAME_MODE";
    public static final String GAMEMODES_TABLE= "GameModes";
    public static final String GAMEMODES_ID= "ID";
    public static final String GAMEMODES_NAME = "NAME";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE "+PLAYERSPLAYING_TABLE+ "("+PLAYERSPLAYING_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+PLAYERSPLAYING_NAME+" TEXT);");
        db.execSQL("CREATE TABLE "+FINALTEXTS_TABLE+ "("+FINALTEXTS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+FINALTEXTS_CONTENT+" TEXT);");
        db.execSQL("CREATE TABLE "+RANKINGS_TABLE+ "("+RANKINGS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+RANKINGS_PLAYER_NAME+" TEXT, "+RANKINGS_PLAYER_SCORE+" INTEGER, "+RANKINGS_GAME_MODE+" INTEGER);");
        db.execSQL("CREATE TABLE "+GAMEMODES_TABLE+ "("+GAMEMODES_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+GAMEMODES_NAME+" TEXT);");
        db.execSQL("INSERT INTO "+GAMEMODES_TABLE+" ("+GAMEMODES_NAME+") VALUES ('Básico');");
        db.execSQL("INSERT INTO "+GAMEMODES_TABLE+" ("+GAMEMODES_NAME+") VALUES ('Round Robin');");
        db.execSQL("INSERT INTO "+GAMEMODES_TABLE+" ("+GAMEMODES_NAME+") VALUES ('Aleatório');");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+PLAYERSPLAYING_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+FINALTEXTS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+RANKINGS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+GAMEMODES_TABLE);
        onCreate(db);
    }

    public void addPlayer(SQLiteDatabase db, String name) //adicionar jogador à base de dados
    {
        db.execSQL("INSERT INTO "+PLAYERSPLAYING_TABLE+" ("+PLAYERSPLAYING_NAME+") VALUES ('"+name+"');");
    }

    public ArrayList<String> getRankings()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> rankings = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT R."+RANKINGS_PLAYER_NAME+", R."+RANKINGS_PLAYER_SCORE+", G."+GAMEMODES_NAME+" FROM "+RANKINGS_TABLE+" AS R INNER JOIN "+GAMEMODES_TABLE+" G ON G."+GAMEMODES_ID+" = R."+RANKINGS_GAME_MODE+";", null);
        if(cursor.moveToFirst())
        {
            do {
                rankings.add(cursor.getString(0));
                rankings.add(cursor.getString(1));
                rankings.add(cursor.getString(2));
            }while(cursor.moveToNext());
        }
        return rankings;
    }

    public void addFinalText(SQLiteDatabase db, String text)
    {
        db.execSQL("INSERT INTO "+FINALTEXTS_TABLE+" ("+FINALTEXTS_CONTENT+") VALUES ('"+text+"')");
    }

    public ArrayList<String> getCurrentPlayers()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> players = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT "+PLAYERSPLAYING_NAME+" FROM "+PLAYERSPLAYING_TABLE+";", null);
        if(cursor.moveToFirst())
        {
            do{
                players.add(cursor.getString(0));
            }while(cursor.moveToNext());
        }
        return players;
    }

    public void eraseTablePlayersPlaying(SQLiteDatabase db)
    {
        db.execSQL("DELETE FROM "+PLAYERSPLAYING_TABLE);
    }

    public int checkPlayer(String playerName)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        int check = 0;
        Cursor cursor = db.rawQuery("SELECT EXISTS(SELECT 1 FROM "+PLAYERSPLAYING_TABLE+" WHERE "+PLAYERSPLAYING_NAME+"='"+playerName+"')", null);
        if(cursor.moveToFirst())
            check = cursor.getInt(0);
        return check;
    }

    public void addPlayerRankings(SQLiteDatabase db, String playerName, int gameMode)
    {
        db.execSQL("INSERT INTO "+RANKINGS_TABLE+" ("+RANKINGS_PLAYER_NAME+", "+RANKINGS_PLAYER_SCORE+", "+RANKINGS_GAME_MODE+") VALUES ('"+playerName+"', 1, "+gameMode+");");
    }

    public int checkPlayerRankings(String playerName, int gameMode)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        int check = 0;
        Cursor cursor = db.rawQuery("SELECT EXISTS(SELECT 1 FROM "+RANKINGS_TABLE+" WHERE "+RANKINGS_PLAYER_NAME+"='"+playerName+"' AND "+RANKINGS_GAME_MODE+"="+gameMode+");", null);
        if(cursor.moveToFirst())
            check = cursor.getInt(0);
        return check;
    }

    public void updatePlayerScore(String playerName, int gameMode)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int score = 0;
        Cursor cursor = db.rawQuery("SELECT "+RANKINGS_PLAYER_SCORE+" FROM "+RANKINGS_TABLE+" WHERE "+RANKINGS_PLAYER_NAME+"='"+playerName+"' AND "+RANKINGS_GAME_MODE+"="+gameMode+";", null);
        if(cursor.moveToFirst())
            score = cursor.getInt(0);
        score = score+1;
        db.execSQL("UPDATE "+RANKINGS_TABLE+" SET "+RANKINGS_PLAYER_SCORE+"="+score+" WHERE "+RANKINGS_PLAYER_NAME+"='"+playerName+"' AND "+RANKINGS_GAME_MODE+"="+gameMode+";");
    }

    public ArrayList<String> getFinalTexts()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> texts = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT "+FINALTEXTS_CONTENT+" FROM "+FINALTEXTS_TABLE+";", null);
        if(cursor.moveToFirst())
        {
            do {
                texts.add(cursor.getString(0));
            }while(cursor.moveToNext());
        }
        return texts;
    }
}