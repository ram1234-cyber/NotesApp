package com.example.android.notesapp.Dao;


import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android.notesapp.Model.Notes;

import java.util.List;

@androidx.room.Dao //1 ISKOBTA NA HOGA KI DAO HA
public interface NotesDao {


    @Query("SELECT * FROM Notes_Database")
    LiveData<List<Notes>> getallNotes(); //

    @Query("SELECT * FROM Notes_Database ORDER BY notes_priority DESC")
    LiveData<List<Notes>> hightolow(); //

    @Query("SELECT * FROM Notes_Database ORDER BY notes_priority ASC")
    LiveData<List<Notes>> lowtohigh(); //






    @Insert
    void insertNotes(Notes... notes); //automatic insert ho jayyga

    @Query("DELETE FROM Notes_Database WHERE id=:id")
    void delteNotes(int id);

    @Update
    void updatesNotes(Notes notes);


}
