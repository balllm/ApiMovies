package com.example.apimovies;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class Detail_activity extends AppCompatActivity {
    TextView title;
    TextView year;
    ImageView poster;
    TextView genre;
    TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        title = findViewById(R.id.titleView);
        year = findViewById(R.id.yearView);
        poster = findViewById(R.id.imageView);
        genre = findViewById(R.id.genreView);
        description = findViewById(R.id.descirptionView);

        String url = "https://www.omdbapi.com/?apikey=c47dffa7&i=tt2975590";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String Title = response.getString("Title");
                            String Poster = response.getString("Poster");
                            String Released = response.getString("Released");
                            String Genre = response.getString("Genre");
                            String Plot = response.getString("Plot");

                            title.setText("Title: " + Title);
                            year.setText("Released: " + Released);
                            genre.setText("Genre: " + Genre);
                            description.setText("Plot: " + Plot);


                            Picasso picasso = new Picasso.Builder(getApplicationContext())
                                    .listener(new Picasso.Listener() {
                                        @Override
                                        public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {

                                        }
                                    })
                                    .build();

                            picasso.load(Poster)
                                    .into(poster);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }
}