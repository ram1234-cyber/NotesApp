package com.example.android.notesapp.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.android.notesapp.Dao.NotesDao;
import com.example.android.notesapp.Database.NotesDatabase;
import com.example.android.notesapp.Model.Notes;

import java.util.List;

public class NotesRepository {

    public NotesDao notesDao;

    public LiveData<List<Notes>> getallNotes;
    public LiveData<List<Notes>> hightolow;
    public LiveData<List<Notes>> lowtohigh;

    public NotesRepository(Application application) {

        NotesDatabase notesDatabase = NotesDatabase.getDatabaseInstance(application);
        notesDao = notesDatabase.notesDao();//databse ke andar se jo notes dao ha
        getallNotes = notesDao.getallNotes();
        hightolow=notesDao.hightolow();
        lowtohigh=notesDao.lowtohigh();

    }


    public void insertNotes(Notes notes) {
        notesDao.insertNotes(notes);
    }

    public void deleteNotes(int id) {
        notesDao.delteNotes(id);
    }

    public void updateNotes(Notes notes) {
        notesDao.updatesNotes(notes);
    }


}
