package rtc.rattanamon.warawan.questiongames;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainHub extends AppCompatActivity implements View.OnClickListener {


    private ImageView cat0ImageView, cat1ImageView; // 0==>คิดเลข , 1 ==> อะไรเอ่ย
    private int anInt;
    private MediaPlayer mediaPlayer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hub);


        cat0ImageView = (ImageView) findViewById(R.id.imageView);
        cat1ImageView = (ImageView) findViewById(R.id.imageView3);
        mediaPlayer = MediaPlayer.create(getBaseContext(),R.raw.tone);
        mediaPlayer.start();

        //Image controller
        cat0ImageView.setOnClickListener(this);
        cat1ImageView.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.imageView:
                anInt = 0;
                break;
            case R.id.imageView3:
                anInt = 1;
                break;
        }
        mediaPlayer.stop();
        Intent intent = new Intent(MainHub.this, MainLevel.class);
        intent.putExtra("Cat", anInt);
        startActivity(intent);
        finish();

    } //onclick
}
