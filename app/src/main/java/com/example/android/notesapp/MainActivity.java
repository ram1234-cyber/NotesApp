 package com.example.android.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.android.notesapp.Activity.InsertNotes;
import com.example.android.notesapp.Adapter.NotesAdapter;
import com.example.android.notesapp.Model.Notes;
import com.example.android.notesapp.ViewModel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

 public class MainActivity extends AppCompatActivity {

    FloatingActionButton newnotebtn;
    NotesViewModel notesViewModelModel;
    RecyclerView notesrecycler;
    NotesAdapter notesAdapter;
    List<Notes> filternotesalllist;//saara data isme hoga
     TextView nofilter,lowtohigh,hightolow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newnotebtn=findViewById(R.id.newNotesBtn);
        notesrecycler=findViewById(R.id.notesRecycler);

        nofilter=findViewById(R.id.no_filter);
        lowtohigh=findViewById(R.id.low_to_high);
        hightolow=findViewById(R.id.high_to_low);

        nofilter.setBackgroundResource(R.drawable.filter_selected);

        nofilter.setOnClickListener(view -> {
            loaddata(0);
            hightolow.setBackgroundResource(R.drawable.filter_unshape);
            lowtohigh.setBackgroundResource(R.drawable.filter_unshape);
            nofilter.setBackgroundResource(R.drawable.filter_selected);
        });
        lowtohigh.setOnClickListener(view -> {
            loaddata(1);
            hightolow.setBackgroundResource(R.drawable.filter_unshape);
            lowtohigh.setBackgroundResource(R.drawable.filter_selected);
            nofilter.setBackgroundResource(R.drawable.filter_unshape);

        });
        hightolow.setOnClickListener(view -> {
            loaddata(2);
            hightolow.setBackgroundResource(R.drawable.filter_selected);
            lowtohigh.setBackgroundResource(R.drawable.filter_unshape);
            nofilter.setBackgroundResource(R.drawable.filter_unshape);
        });







        notesViewModelModel= ViewModelProviders.of(this).get(NotesViewModel.class);//notes ko get krne ke liye

        newnotebtn.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, InsertNotes.class));
        });

//        notesViewModelModel.getAllNotes.observe(this,notes -> {
//            notesrecycler.setLayoutManager(new GridLayoutManager(this,2));
//            notesAdapter=new NotesAdapter(MainActivity.this,notes);
//            notesrecycler.setAdapter(notesAdapter);
//            filternotesalllist=notes;
//        });

        notesViewModelModel.getAllNotes.observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {
                setAdapter(notes);
            }
        });

    }

     private void loaddata(int i) {

        if (i==0){
            notesViewModelModel.getAllNotes.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filternotesalllist=notes;
                }
            });

        } else if (i==1){

            notesViewModelModel.lowtohigh.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filternotesalllist=notes;
                }
            });
        } else if (i==2){
            notesViewModelModel.hightolow.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                    filternotesalllist=notes;
                }
            });
        }


     }


     public void setAdapter(List<Notes> notes){
         notesrecycler.setLayoutManager(new GridLayoutManager(this,2));
         notesAdapter=new NotesAdapter(MainActivity.this,notes);
         notesrecycler.setAdapter(notesAdapter);



     }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);

        MenuItem menuItem=menu.findItem(R.id.app_bar_search);

        SearchView searchView= (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Please search here ");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newtext) {
                Notesfilter(newtext);//jaise tyoe krenge wsise text nilega
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    private void Notesfilter(String newtext) {
        ArrayList<Notes> filterednames=new ArrayList<>();

        for (Notes notes : this.filternotesalllist){

            if (notes.notesTitle.contains(newtext) || notes.notesSubtitle.contains(newtext)){
                filterednames.add(notes);
            }
        }
        this.notesAdapter.searcNotes(filterednames) ;

    }
}