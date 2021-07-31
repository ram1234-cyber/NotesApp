package com.example.android.notesapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.example.android.notesapp.MainActivity;
import com.example.android.notesapp.Model.Notes;
import com.example.android.notesapp.R;
import com.example.android.notesapp.ViewModel.NotesViewModel;
import com.example.android.notesapp.databinding.ActivityInsertNotesBinding;

import java.util.Date;

public class InsertNotes extends AppCompatActivity {

    String title,subtite,notes;
    ActivityInsertNotesBinding binding;
    NotesViewModel notesViewModel;//notes view model se data lega
    String priority="1";
  

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityInsertNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notesViewModel= ViewModelProviders.of(this).get(NotesViewModel.class);//insert aur 2 activity  me show hoga


        binding.greenPrior.setOnClickListener(view -> {
           binding.greenPrior.setImageResource(R.drawable.ic_baseline_done_24);
           binding.redPrior.setImageResource(0);
           binding.yellowPrior.setImageResource(0);


            priority="1";
        });

        binding.yellowPrior.setOnClickListener(view -> {
            binding.greenPrior.setImageResource(0);
            binding.yellowPrior.setImageResource(R.drawable.ic_baseline_done_24);
            binding.redPrior.setImageResource(0);



            priority="2";
        });


        binding.redPrior.setOnClickListener(view -> {
            binding.greenPrior.setImageResource(0);
            binding.yellowPrior.setImageResource(0);
            binding.redPrior.setImageResource(R.drawable.ic_baseline_done_24);


            priority="3";
        });





        binding.donesBtn.setOnClickListener(view -> {

           title=binding.titles.getText().toString();
           subtite=binding.subtitles.getText().toString();
           notes=binding.notesdata.getText().toString();

           CreateNotes(title,subtite,notes);






        });
    }

    private void CreateNotes(String title, String subtite, String notes) {

        Date date=new Date();
        CharSequence sequence= DateFormat.format("MMMM d,yyyy",date.getTime());


        Notes notes1=new Notes();
        notes1.notesTitle=title;
        notes1.notesSubtitle=subtite;
        notes1.notes=notes;
        notes1.notesData=sequence.toString();
        notes1.notesPriority=priority;

//        notesViewModel.insertNote(notes1);
        notesViewModel.insertNote(notes1);

        Toast.makeText(this,"notes created",Toast.LENGTH_SHORT).show();

        finish();



    }
}