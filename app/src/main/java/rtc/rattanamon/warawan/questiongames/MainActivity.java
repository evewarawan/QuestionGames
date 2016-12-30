package rtc.rattanamon.warawan.questiongames;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.imageView2);

        final MediaPlayer mediaPlayer = MediaPlayer.create(getBaseContext(),R.raw.tone);
        mediaPlayer.start();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                startActivity(new Intent(MainActivity.this, MainHub.class));
                finish();
            }
        });


    }
}
