package rtc.rattanamon.warawan.questiongames;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by Admin on 28/11/2559.
 */

public class SynQuestion extends AsyncTask<String,Void, String>{
    //Explicit
    private static final String urlJSON = "http://swiftcodingthai.com/eve/get_question_where.php";
    private Context context;

    public SynQuestion(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {




        return null;
    }
} // Main Class
