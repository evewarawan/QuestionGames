package rtc.rattanamon.warawan.questiongames;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
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
    private int catAnInt, LevelAnInt, fullTimes, times = 0,
            myTime = 0, scoreAnInt = 0, myChoose = 0;
    private String[] questionStrings, choice1Strings, choice2Strings,
            choice3Strings, answerStrings, imageStrings;
    private TextView questionTextView, timeTextView, scoreTextView;
    private RadioGroup radioGroup;
    private RadioButton choice1RadioButton, choice2RadioButton, choice3RadioButton;
    private ImageView imageView, answerImageView;
    private boolean aBoolean = true;
    private int[] soundInts  = new int[]{R.raw.ok1, R.raw.tone};
    private MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        mediaPlayer = MediaPlayer.create(getBaseContext(),R.raw.tone);
        mediaPlayer.start();

        //Bind Widget
        bindWidget();

        // Get Value From Intent
        catAnInt = getIntent().getIntExtra("Cat", 0);
        LevelAnInt = getIntent().getIntExtra("Level", 0);
        Log.d("30decV1", "(Cat, Level) ==> (" + catAnInt + "," + LevelAnInt + ")");

        scoreTextView.setText(getResources().getString(R.string.score) + "0");

        createView();

        checkTimes();

        //Radio Controller
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i) {
                    case R.id.radioButton:
                        myChoose = 1;
                        break;
                    case R.id.radioButton2:
                        myChoose = 2;
                        break;
                    case R.id.radioButton3:
                        myChoose = 3;
                        break;
                }

            }
        });

        //Answer Controller
        answerImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Check Score
                if (myChoose == Integer.parseInt(answerStrings[times])) {
                    scoreAnInt += 1;
                    scoreTextView.setText("คะแนน = " + Integer.toString(scoreAnInt));




                } else {

                }

                Log.d("30decV1", "fullTime ==> " + fullTimes);
                if (times >= (fullTimes - 1)) {
                    mediaPlayer.stop();
                    Toast.makeText(PlayGame.this, "หมดข้อแล้ว", Toast.LENGTH_SHORT).show();
                    aBoolean = false;
                    Intent intent = new Intent(PlayGame.this, ShowScore.class);
                    intent.putExtra("Score", scoreAnInt);
                    intent.putExtra("Cat", catAnInt);
                    intent.putExtra("Level", LevelAnInt);
                    startActivity(intent);
                    finish();

                } else {



                    times += 1;
                    showView(times);
                }




                radioGroup.clearCheck();

            }   // onClick
        });


    } // Main Method

    private void checkTimes() {

        //To Do

        if (myTime == 120) {
            mediaPlayer.stop();
            Intent intent = new Intent(PlayGame.this, ShowScore.class);
            intent.putExtra("Score", scoreAnInt);
            intent.putExtra("Cat", catAnInt);
            intent.putExtra("Level", LevelAnInt);
            startActivity(intent);
            finish();

        } else {

            timeTextView.setText(Integer.toString(120 - myTime) + " วินาที");
            myTime += 1;

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    if (aBoolean) {
                        checkTimes();
                    }

                }
            }, 1000);


        }


    }   // checkTimes

    private void bindWidget() {
        questionTextView = (TextView) findViewById(R.id.textView5);
        radioGroup = (RadioGroup) findViewById(R.id.ragchoice);
        choice1RadioButton = (RadioButton) findViewById(R.id.radioButton);
        choice2RadioButton = (RadioButton) findViewById(R.id.radioButton2);
        choice3RadioButton = (RadioButton) findViewById(R.id.radioButton3);
        imageView = (ImageView) findViewById(R.id.imageView10);
        answerImageView = (ImageView) findViewById(R.id.imageView9);
        radioGroup = (RadioGroup) findViewById(R.id.ragchoice);
        timeTextView = (TextView) findViewById(R.id.textView);
        scoreTextView = (TextView) findViewById(R.id.textView2);

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

        try {

            questionTextView.setText(Integer.toString(index + 1) + ". " + questionStrings[index]);
            choice1RadioButton.setText(choice1Strings[index]);
            choice2RadioButton.setText(choice2Strings[index]);
            choice3RadioButton.setText(choice3Strings[index]);

            Picasso.with(PlayGame.this).load(imageStrings[index]).into(imageView);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
} //Main Class
