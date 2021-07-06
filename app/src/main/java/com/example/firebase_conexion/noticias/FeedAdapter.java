package com.example.firebase_conexion.noticias;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase_conexion.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
// Adaptador del Recyclerview


public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {
    private final List<FeedPost> feedPost;
    private final Context mainActivity3;

    public FeedAdapter(ArrayList<FeedPost> feedPost, MainActivity3 mainActivity3) {
        this.feedPost = feedPost;
        this.mainActivity3 = mainActivity3;

    }

    @NonNull
    @Override
    public FeedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View v;

        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_posts, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedAdapter.ViewHolder holder, int position ) {
        //Se cargan los datos gracias al onBindViewHolder




        final FeedPost fP = feedPost.get(position);
        holder.postTitle.setText(fP.getTitle());

        String regex = "http(s?)://([\\w-]+\\.)+[\\w-]+(/[\\w- ./]*)+\\.(?:[gG][iI][fF]|[jJ][pP][gG]|[jJ][pP][eE][gG]|[pP][nN][gG]|[bB][mM][pP])";

        Matcher m = Pattern.compile(regex).matcher(fP.getContent());

        if (m.find())
        {
            Picasso.get().load(m.group(0)).into(holder.foto_noticia);
        }

        String data = fP.getFecha();
        Object json = null;
        try {//con el try comprobamos que si lo que estamos cargando es un Array json o no separando las etiquetas content con ", "
            json = new JSONTokener(data).nextValue();
            if (json instanceof JSONArray) {
                String categories = "";
                for (int i = 0; i < ((JSONArray) json).length(); i++) {
                    categories += ((JSONArray) json).getJSONObject(i).getString("content") + ", ";

                }
                holder.postCategory.setText(categories.substring(0, categories.length() - 2));//quitamos la coma y el espacio en el ultimo titulo

            }
            else
            {
                holder.postCategory.setText(fP.getFecha());

            }



        } catch (JSONException e) {
            e.printStackTrace();
            holder.postCategory.setText(fP.getFecha());
        }

    }

    @Override
    public int getItemCount() {
        return feedPost.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        /////
        // Clase ViewHolder que tiene el father para ponerle el  clicklistener para hacer un intent y poder
        // Acceder a la noticia cuando hacemos click en ella
        ///
        final TextView postTitle;
        final TextView postCategory;
        final ImageView foto_noticia;
        final LinearLayout father;

        ViewHolder(View v) {
            super(v);
            postTitle = v.findViewById(R.id.post_title);
            postCategory = v.findViewById(R.id.post_category);
            foto_noticia=v.findViewById(R.id.foto_Noticia);
            father = v.findViewById(R.id.father);

            father.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(feedPost.get(pos).getLink()));
            mainActivity3.startActivity(i);
        }
    }
}
