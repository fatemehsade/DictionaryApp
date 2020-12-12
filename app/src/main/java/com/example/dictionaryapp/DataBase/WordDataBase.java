package com.example.dictionaryapp.DataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.dictionaryapp.Converters;
import com.example.dictionaryapp.Model.Word;

@Database(entities = {Word.class},version = 1)
@TypeConverters({Converters.class})
public abstract class WordDataBase extends RoomDatabase {
    public abstract WordDoa getWordDaoDataBase();
}
