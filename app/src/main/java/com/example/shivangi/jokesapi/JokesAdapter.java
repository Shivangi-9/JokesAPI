package com.example.shivangi.jokesapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class JokesAdapter extends RecyclerView.Adapter<JokesAdapter.JokesViewHolder> {

    private Context jContext;
    private ArrayList<Jokes> jokesList;

    public JokesAdapter(Context jContext, ArrayList<Jokes> jokesList) {
        this.jContext = jContext;
        this.jokesList = jokesList;
    }


    @NonNull
    @Override
    public JokesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(jContext).inflate(R.layout.item_layout,parent,false);
        return new JokesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JokesViewHolder holder, int position) {
        Jokes currentJoke = jokesList.get(position);

        String jokesQ = currentJoke.getQuestion();
        String jokesA = currentJoke.getAnswer();

        holder.jQuestion.setText(jokesQ);
        holder.jAnswer.setText(jokesA);
    }

    @Override
    public int getItemCount() {
        return jokesList.size();
    }

    public class JokesViewHolder extends RecyclerView.ViewHolder{

        public TextView jQuestion;
        public TextView jAnswer;

        public JokesViewHolder(@NonNull View itemView) {
            super(itemView);
            jQuestion = itemView.findViewById(R.id.questionTxt);
            jAnswer = itemView.findViewById(R.id.answerTxt);
        }
    }
}
