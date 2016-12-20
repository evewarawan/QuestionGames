package rtc.rattanamon.warawan.questiongames;

import android.content.Context;
import android.os.AsyncTask;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by Admin on 28/11/2559.
 */

public class SynQuestion extends AsyncTask<String,Void, String>{
    //Explicit
    private static final String urlJSON = "http://swiftcodingthai.com/eve/get_question_where.php";
    private Context context;
    private String catString, levString;

    public SynQuestion(Context context, String catString, String levString) {
        this.context = context;
        this.catString = catString;
        this.levString = levString;
    }

    @Override
    protected String doInBackground(String... params) {

        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add("Category", catString)
                    .add("Level", levString)
                    .build();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(urlJSON).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }



    }
} // Main Class
