package rtc.rattanamon.warawan.questiongames;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowScore extends AppCompatActivity implements View.OnClickListener {

    //Explicit
    private ImageView catImageView, levelImageView;
    private TextView textView;
    private Button playButton, exitButton;
    private int[] catInts = new int[]{R.drawable.cat00, R.drawable.level11};
    private int[] levInts = new int[]{R.drawable.level0, R.drawable.level1,
            R.drawable.levell10, R.drawable.levell11};
    private int levAnInt, catAnInt, scoreAnInt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_score);

        //Bind Widget
        catImageView = (ImageView) findViewById(R.id.imageView4);
        levelImageView = (ImageView) findViewById(R.id.imageView5);
        textView = (TextView) findViewById(R.id.textView3);
        playButton = (Button) findViewById(R.id.button);
        exitButton = (Button) findViewById(R.id.button2);

        //Show View
        catAnInt = getIntent().getIntExtra("Cat", 0);
        catImageView.setImageResource(catInts[catAnInt]);

        levAnInt = getIntent().getIntExtra("Level", 0);
        if (catAnInt == 1) {

            switch (levAnInt) {
                case 0:
                    levAnInt = 2;
                    break;
                case 1:
                    levAnInt = 3;
                    break;
            }

        }   // if

        levelImageView.setImageResource(levInts[levAnInt]);

        scoreAnInt = getIntent().getIntExtra("Score", 0);
        textView.setText(Integer.toString(scoreAnInt));

        //Button Controller
        playButton.setOnClickListener(this);
        exitButton.setOnClickListener(this);


    }   // Main Method

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.button) {
            startActivity(new Intent(ShowScore.this, MainHub.class));
            finish();
        }

        finish();

    }
}   // Main Class
