package com.joost.filmapplicatie.ApplicationLogic;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joost.filmapplicatie.Domain.Actor;
import com.joost.filmapplicatie.Domain.MovieList;
import com.joost.filmapplicatie.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ActorsAdapter extends RecyclerView.Adapter<ActorsAdapter.ActorViewHolder> {

    private List<Actor> actors;
    private LayoutInflater inflater;
    private Context context;

    public ActorsAdapter(Context context, List<Actor> actors) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.actors = actors;
    }

    @NonNull
    @Override
    public ActorsAdapter.ActorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.movie_detail_activity_actors_recyclerview_item, parent, false);

        return new ActorsAdapter.ActorViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorViewHolder holder, int position) {

        Actor currentActor = actors.get(position);

        holder.actorName.setText(currentActor.getName());
        holder.actorCharacter.setText(currentActor.getCharacter());

        String url = currentActor.getImageURL();
        Picasso.get().load(url).into(holder.actorImage);

    }

    @Override
    public int getItemCount() {
        return actors.size();
    }

    public class ActorViewHolder extends RecyclerView.ViewHolder{

        public TextView actorName;
        public TextView actorCharacter;
        public ImageView actorImage;

        public ActorsAdapter adapter;

        public ActorViewHolder(View itemView, ActorsAdapter adapter) {
            super(itemView);

            actorName = itemView.findViewById(R.id.actor_name);
            actorCharacter = itemView.findViewById(R.id.actor_character);
            actorImage = itemView.findViewById(R.id.actor_image);

            this.adapter = adapter;

        }
    }

}
