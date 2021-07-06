package com.example.firebase_conexion.noticias;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.bumptech.glide.Glide;
import com.example.firebase_conexion.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import fr.arnaudguyon.xmltojsonlib.XmlToJson;

public class MainActivity3 extends AppCompatActivity {

    private TextView tvTitle;
    private ImageView imageView;
    private RecyclerView recyclerView;
    private ArrayList<FeedPost> feedPost;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tvTitle = findViewById(R.id.feed_title);
        imageView = findViewById(R.id.logo);

        recyclerView = findViewById(R.id.feed_recyclerview);
        progressBar = findViewById(R.id.progress_bar);


        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        feedPost = new ArrayList<FeedPost>();  //inicializa el arrayList de los post para luego cargarlos en el adaptador de recycleView
        Map<String, String> params = new HashMap<String, String>();
        Log.d("ALBERTO", "Params: " + params);

        new VolleyRequestClass(MainActivity3.this, AppData.FEED_URL, Request.Method.GET, params, progressBar, AppData.FEED_REQUEST);
    }

    public void onVolleyResponse(String response, int id) throws JSONException { //llamamos a la clase Volleyclass

        if (id == AppData.FEED_REQUEST) {
            ////////////////
            //Con esto convertimos el xml en Json para poder leerlo, ya que si leemos directamente del xml da errores
            XmlToJson xmlToJson = new XmlToJson.Builder(response).build();
            Log.d("ALBERTO", "RESPONSE IS: " + xmlToJson);
            JSONObject feed = xmlToJson.toJson().getJSONObject("rss").getJSONObject("channel");
            ////////////////
            tvTitle.setText(feed.getJSONObject("image").getString("title"));  //Cogemos titulo del texto
            Glide.with(this).load(feed.getJSONObject("image").getString("url")).into(imageView); //url de la web para nuestro foto_Noticia
            /////////////////
//          En el for ponemos en el array de objetos todos los datos que queremos del xml
            for (int i = 0; i < feed.getJSONArray("item").length(); i++) {
                JSONObject obj = feed.getJSONArray("item").getJSONObject(i);
                String title = obj.getString("title");
                String content = obj.getString("description");
                String link = obj.getString("link");
                String fecha = obj.getString("pubDate");

                /////////////////
                feedPost.add(new FeedPost(title, content, link, fecha));//con estos tenemos los datos de la clase FeedPost en el array
            }
            //////
            //creamos el adaptador se lo ponemos al recycler view
            FeedAdapter feedAdapter = new FeedAdapter(feedPost, this);
            recyclerView.setAdapter(feedAdapter);
            ///////
            // Con esto añadimos separacion a las celdas del recyclerView
           DividerItemDecoration divider = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
           divider.setDrawable(ContextCompat.getDrawable(getBaseContext(), R.drawable.my_custom_divider));
           recyclerView.addItemDecoration(divider);
            ///////

        }

    }

    public void displayAlertDialog(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                // The dialog is automatically dismissed when a dialog button is clicked.
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


}
