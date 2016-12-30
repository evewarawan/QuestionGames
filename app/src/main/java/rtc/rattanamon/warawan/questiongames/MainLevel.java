package rtc.rattanamon.warawan.questiongames;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainLevel extends AppCompatActivity implements View.OnClickListener {
    //Explicit
    private int catAnInt, levelAnInt;
    private ImageView easyImageView, difImageView, imageView;
    private int[] imageInts = new int[]{R.drawable.cat00, R.drawable.level11};
    private int[] levInts = new int[]{R.drawable.level0, R.drawable.level1,
            R.drawable.levell10, R.drawable.levell11};
    private int levAnInt;
    private MediaPlayer mediaPlayer;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_level);

        easyImageView = (ImageView) findViewById(R.id.imageView5);
        difImageView = (ImageView) findViewById(R.id.imageView6);
        imageView = (ImageView) findViewById(R.id.imageView7);
        mediaPlayer = MediaPlayer.create(getBaseContext(),R.raw.tone);
        mediaPlayer.start();

        // Get value From Intent
        catAnInt = getIntent().getIntExtra("Cat", 0);
        imageView.setImageResource(imageInts[catAnInt]);

        switch (catAnInt) {

            case 0:

                easyImageView.setImageResource(levInts[0]);
                difImageView.setImageResource(levInts[1]);

                break;
            case 1:

                easyImageView.setImageResource(levInts[2]);
                difImageView.setImageResource(levInts[3]);

                break;

        }   // switch




        //Image Controller
        easyImageView.setOnClickListener(this);
        difImageView.setOnClickListener(this);
    }// Main Method

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView5:
                levelAnInt = 0;
                break;
            case R.id.imageView6:
                levelAnInt = 1;
                break;
        }
        mediaPlayer.stop();
        Intent intent = new Intent(MainLevel.this, PlayGame.class);
        intent.putExtra("Cat", catAnInt);
        intent.putExtra("Level", levelAnInt);
        startActivity(intent);
        finish();


    } // onclick
} // Main Class
