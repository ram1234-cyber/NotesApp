package com.example.android.notesapp.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Update;

import com.example.android.notesapp.Activity.UpdateNotes;
import com.example.android.notesapp.MainActivity;
import com.example.android.notesapp.Model.Notes;
import com.example.android.notesapp.R;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesViewHolder> {


    MainActivity mainActivity;
    List<Notes> notes;
    List<Notes> allnotes;//saare notes ki isme ke kiye search ke liye


    public NotesAdapter(MainActivity mainActivity, List<Notes> notes) {
        this.mainActivity=mainActivity;
        this.notes=notes;
        this.allnotes=new ArrayList<>(notes);
    }

    public void searcNotes(List<Notes> fillteredname){
        this.notes=fillteredname;
        notifyDataSetChanged();
    } //search hoke saare notes aa jaynge



    @NonNull
    @Override
    public notesViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        return new notesViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder( NotesAdapter.notesViewHolder holder, int position) {

        Notes note=notes.get(position);

        if(note.notesPriority.equals("1"))
        {
            holder.notespriority.setBackgroundResource(R.drawable.green);
        }  else if (note.notesPriority.equals("2")){
            holder.notespriority.setBackgroundResource(R.drawable.yellow);
        } else if (note.notesPriority.equals("3")){
            holder.notespriority.setBackgroundResource(R.drawable.redshape);
        }

        holder.title.setText(note.notesTitle);
        holder.subtitle.setText(note.notesSubtitle);
        holder.notesDate.setText(note.notesData);

        //item click hone pe
        holder.itemView.setOnClickListener(view -> {
            Intent intent=new Intent(mainActivity, UpdateNotes.class);
            intent.putExtra("id",note.id);   //data send hoga dusri activiy me
            intent.putExtra("title",note.notesTitle);
            intent.putExtra("subtitle",note.notesSubtitle);
            intent.putExtra("priority",note.notesPriority);
            intent.putExtra("note",note.notes);
            mainActivity.startActivity(intent);



        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    static  class notesViewHolder extends RecyclerView.ViewHolder {

        TextView title,subtitle,notesDate;
       View notespriority;

        public notesViewHolder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.notes_title);
            subtitle=itemView.findViewById(R.id.notes_subtitle);
            notesDate=itemView.findViewById(R.id.notes_date);
            notespriority=itemView.findViewById(R.id.notesprior);


        }
    }


}
