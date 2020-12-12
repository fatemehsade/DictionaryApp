package com.example.dictionaryapp.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dictionaryapp.Model.Word;

import java.util.List;
@Dao
public interface WordDoa {
    @Query("SELECT * FROM wordTable WHERE language = :language")
    List<Word> getList(String language);
    @Insert()
    void add(Word word);
    @Delete
    void delete(Word word);
    @Update
    void update(Word word);
}
