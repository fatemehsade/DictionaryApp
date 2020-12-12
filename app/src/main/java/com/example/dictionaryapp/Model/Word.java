package com.example.dictionaryapp.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.UUID;
@Entity(tableName = "wordTable")
public class Word implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Long mId;

    @ColumnInfo(name = "word")
    private String mWord;

    @ColumnInfo(name = "translate")
    private String mTranslate;

    @ColumnInfo(name = "wordId")
    private UUID mWordId;

    @ColumnInfo(name = "language")
    private String Language;

    public Word() {
        mWordId=UUID.randomUUID();
    }

    public String getLanguage() {
        return Language;
    }


    public void setLanguage(String language) {
        Language = language;
    }

    public UUID getWordId() {
        return mWordId;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getWord() {
        return mWord;
    }

    public void setWord(String word) {
        mWord = word;
    }

    public String getTranslate() {
        return mTranslate;
    }

    public void setTranslate(String translate) {
        mTranslate = translate;
    }

    public void setWordId(UUID wordId) {
        mWordId = wordId;
    }
}
