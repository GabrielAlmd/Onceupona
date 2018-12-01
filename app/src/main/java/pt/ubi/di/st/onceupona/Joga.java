package pt.ubi.di.st.onceupona;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Joga extends Activity { //boas duas vezes

    TextView tPlayers = findViewById(R.id.lable2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joga);
    }



}

