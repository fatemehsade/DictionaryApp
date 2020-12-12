package com.example.dictionaryapp.Repository;

import android.content.Context;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Room;
import androidx.room.Update;

import com.example.dictionaryapp.DataBase.WordDataBase;
import com.example.dictionaryapp.DataBase.WordDoa;
import com.example.dictionaryapp.Model.Word;

import java.util.List;

public class WordRepository {
    public static WordRepository sInstance;
    Context mContext;
    private WordDoa mDoa;

    public static WordRepository getInstance(Context context) {
        if (sInstance==null)
            sInstance=new WordRepository(context);
        return sInstance;
    }

    public WordRepository(Context context) {
        mContext=context.getApplicationContext();
        WordDataBase dataBase= Room.databaseBuilder(
                mContext,WordDataBase.class,
                "word.db")
                .allowMainThreadQueries().build();
        mDoa=dataBase.getWordDaoDataBase();
    }

    public List<Word> getList(String language){
        return mDoa.getList(language);

    }
    public void add(Word word){
        mDoa.add(word);

    }
    public void delete(Word word){
        mDoa.delete(word);

    }
    public void update(Word word){
        mDoa.update(word);

    }

}
