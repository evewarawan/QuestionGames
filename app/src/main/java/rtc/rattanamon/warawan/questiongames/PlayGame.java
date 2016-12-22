package rtc.rattanamon.warawan.questiongames;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

public class PlayGame extends AppCompatActivity {

    // Explicit
    private int catAnInt, LevelAnInt, fullTimes, times = 0;
    private String[] questionStrings, choice1Strings, choice2Strings,
            choice3Strings, answerStrings, imageStrings;
    private TextView questionTextView;
    private RadioGroup radioGroup;
    private RadioButton choice1RadioButton, choice2RadioButton, choice3RadioButton;
    private ImageView imageView, answerImageView;

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

        answerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("22decV1", "fullTime ==> " + fullTimes);
                if (times >= fullTimes) {
                    Toast.makeText(PlayGame.this, "หมดข้อแล้ว", Toast.LENGTH_SHORT).show();
                } else {
                    times += 1;
                    showView(times);
                }

            }   // onClick
        });


    } // Main Method

    private void bindWidget() {
        questionTextView = (TextView) findViewById(R.id.textView5);
        radioGroup = (RadioGroup) findViewById(R.id.ragchoice);
        choice1RadioButton = (RadioButton) findViewById(R.id.radioButton);
        choice2RadioButton = (RadioButton) findViewById(R.id.radioButton2);
        choice3RadioButton = (RadioButton) findViewById(R.id.radioButton3);
        imageView = (ImageView) findViewById(R.id.imageView10);
        answerImageView = (ImageView) findViewById(R.id.imageView9);
    }

    private void createView() {

        try {

            SynQuestion synQuestion = new SynQuestion(PlayGame.this,
                    Integer.toString(catAnInt), Integer.toString(LevelAnInt));
            synQuestion.execute();
            String strJSoN = synQuestion.get();
            Log.d("28novV1", "JSON ==> " + strJSoN);

            JSONArray jsonArray = new JSONArray(strJSoN);
            fullTimes = jsonArray.length();
            questionStrings = new String[jsonArray.length()];
            choice1Strings = new String[jsonArray.length()];
            choice2Strings = new String[jsonArray.length()];
            choice3Strings = new String[jsonArray.length()];
            answerStrings = new String[jsonArray.length()];
            imageStrings = new String[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                questionStrings[i] = jsonObject.getString("Question");
                choice1Strings[i] = jsonObject.getString("Choice1");
                choice2Strings[i] = jsonObject.getString("Choice2");
                choice3Strings[i] = jsonObject.getString("Choice3");
                answerStrings[i] = jsonObject.getString("Answer");
                imageStrings[i] = jsonObject.getString("Image");
            }

            showView(0);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void showView(int index) {

        questionTextView.setText(Integer.toString(index + 1) + ". " + questionStrings[index]);
        choice1RadioButton.setText(choice1Strings[index]);
        choice2RadioButton.setText(choice2Strings[index]);
        choice3RadioButton.setText(choice3Strings[index]);

        try {

            Picasso.with(PlayGame.this).load(imageStrings[index]).into(imageView);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
} //Main Class
