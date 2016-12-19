package rtc.rattanamon.warawan.questiongames;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class PlayGame extends AppCompatActivity {

    // Explicit
    private int catAnInt, LevelAnInt;
    private String[] questionStrings, choice1Strings, choice2Strings,
            choice3Strings, answerStrings;
    private TextView questionTextView;
    private RadioGroup radioGroup;
    private RadioButton choice1RadioButton, choice2RadioButton, choice3RadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        //Bind Widget
        bindWidget();

        // Get Value From Intent
        catAnInt = getIntent().getIntExtra("Cat", 0);
        LevelAnInt = getIntent().getIntExtra("Level", 0);
        Log.d("28novV1", "(Cat, Level) ==> (" + catAnInt + "," + LevelAnInt + ")");

        createView();


    } // Main Method

    private void bindWidget() {
        questionTextView = (TextView) findViewById(R.id.textView5);
        radioGroup = (RadioGroup) findViewById(R.id.ragchoice);
        choice1RadioButton = (RadioButton) findViewById(R.id.radioButton);
        choice2RadioButton = (RadioButton) findViewById(R.id.radioButton2);
        choice3RadioButton = (RadioButton) findViewById(R.id.radioButton3);
    }

    private void createView() {

        try {

            SynQuestion synQuestion = new SynQuestion(PlayGame.this,
                    Integer.toString(catAnInt), Integer.toString(LevelAnInt));
            synQuestion.execute();
            String strJSoN = synQuestion.get();
            Log.d("28novV1", "JSON ==> " + strJSoN);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
} //Main Class
