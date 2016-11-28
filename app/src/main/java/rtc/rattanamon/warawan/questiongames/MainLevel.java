package rtc.rattanamon.warawan.questiongames;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainLevel extends AppCompatActivity implements View.OnClickListener {
    //Explicit
    private int catAnInt, levelAnInt;
    private ImageView easyImageView , difImageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_level);

        easyImageView = (ImageView) findViewById(R.id.imageView5);
        difImageView = (ImageView) findViewById(R.id.imageView6);

        // Get value From Inntent
        catAnInt = getIntent().getIntExtra("Cat", 0);

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

        Intent intent = new Intent(MainLevel.this,PlayGame.class);
        intent.putExtra("Cat", catAnInt);
        intent.putExtra("Level", levelAnInt);
        startActivity(intent);


    } // onclick
} // Main Class
