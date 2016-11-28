package rtc.rattanamon.warawan.questiongames;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class PlayGame extends AppCompatActivity {

    // Explicit
    private  int catAnInt, LevelAnInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        // Get Value From Intent
        catAnInt = getIntent().getIntExtra("Cat", 0);
        LevelAnInt = getIntent().getIntExtra("Level", 0);
        Log.d("28novV1", "(Cat, Level) ==> (" + catAnInt + "," + LevelAnInt + ")");


    } // Main Method
} //Main Class
