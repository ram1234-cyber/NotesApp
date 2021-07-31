package com.example.android.notesapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.notesapp.Model.Notes;
import com.example.android.notesapp.R;
import com.example.android.notesapp.ViewModel.NotesViewModel;
import com.example.android.notesapp.databinding.ActivityUpdateNotesBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Date;

public class UpdateNotes extends AppCompatActivity {

    ActivityUpdateNotesBinding binding;
    NotesViewModel notesViewModel;
    String priority = "1";
    String stitle, ssubtitle, snotes, spriority;
    int sid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_notes);
        binding = ActivityUpdateNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        sid = getIntent().getIntExtra("id", 0);//adaprer se data get hoga
        stitle = getIntent().getStringExtra("title");
        ssubtitle = getIntent().getStringExtra("subtitle");
        spriority = getIntent().getStringExtra("priority");
        snotes = getIntent().getStringExtra("note");

        binding.uptitle.setText(stitle);
        binding.upsubtitle.setText(ssubtitle);
        binding.upnote.setText(snotes);

        notesViewModel= ViewModelProviders.of(this).get(NotesViewModel.class);

        if (spriority.equals("1")){
            binding.greenPrior.setImageResource(R.drawable.ic_baseline_done_24);

        }else  if (spriority.equals("2")){
            binding.yellowPrior.setImageResource(R.drawable.ic_baseline_done_24);

        }else if (spriority.equals("3")){
            binding.redPrior.setImageResource(R.drawable.ic_baseline_done_24);
        }



        binding.greenPrior.setOnClickListener(view -> {
            binding.greenPrior.setImageResource(R.drawable.ic_baseline_done_24);
            binding.redPrior.setImageResource(0);
            binding.yellowPrior.setImageResource(0);


            priority = "1";
        });

        binding.yellowPrior.setOnClickListener(view -> {
            binding.greenPrior.setImageResource(0);
            binding.yellowPrior.setImageResource(R.drawable.ic_baseline_done_24);
            binding.redPrior.setImageResource(0);


            priority = "2";
        });


        binding.redPrior.setOnClickListener(view -> {
            binding.greenPrior.setImageResource(0);
            binding.yellowPrior.setImageResource(0);
            binding.redPrior.setImageResource(R.drawable.ic_baseline_done_24);


            priority = "3";
        });

        binding.updateBtn.setOnClickListener(view -> {

            String title = binding.uptitle.getText().toString();
            String subtite = binding.upsubtitle.getText().toString();
            String notes = binding.upnote.getText().toString();

            UpdatesNotes(title, subtite, notes);


        });


    }

    private void UpdatesNotes(String title, String subtite, String notes) {

        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d,yyyy", date.getTime());

        Notes updatenotes = new Notes();

        updatenotes.notesTitle = title;
        updatenotes.notesSubtitle = subtite;
        updatenotes.id = sid;
        updatenotes.notes = notes;
        updatenotes.notesData = sequence.toString();
        updatenotes.notesPriority = priority;

        notesViewModel.updateNote(updatenotes);

        Toast.makeText(this, "notes updated", Toast.LENGTH_SHORT).show();

        finish();


    }@Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.delete_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.ic_delete){
            BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(UpdateNotes.this,R.style.BottomSheetStyle);

            View view= LayoutInflater.from(UpdateNotes.this).inflate(R.layout.delete_bottomsheet,
                    (LinearLayout)findViewById(R.id.bottom_sheet));
            bottomSheetDialog.setContentView(view);


            TextView yes,no;

            yes=view.findViewById(R.id.delete_yes);
            no=view.findViewById(R.id.delete_no);

            yes.setOnClickListener(view1 -> {
                notesViewModel.deleteNotes(sid);
                finish();
            });

            no.setOnClickListener(view1 -> {
                 bottomSheetDialog.dismiss();

            });










            bottomSheetDialog.show();

        }
        return true;
    }
}