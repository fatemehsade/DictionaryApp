package com.example.dictionaryapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.dictionaryapp.Fragment.TranslateFragment;
import com.example.dictionaryapp.Model.Word;
import com.example.dictionaryapp.R;

public class TranslateActivity extends SingleFragmentActivity {

    public static final String EXTRA_LANGUAGE = "language";
    public static final String EXTRA_WORD = "word";

    public static Intent newIntent(Context context, Word word, String language){
        Intent intent=new Intent(context,TranslateActivity.class);
        intent.putExtra(EXTRA_LANGUAGE,language);
        intent.putExtra(EXTRA_WORD,word);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        String language=getIntent().getStringExtra(EXTRA_LANGUAGE);
        Word word= (Word) getIntent().getSerializableExtra(EXTRA_WORD);
        return TranslateFragment.newInstance(word,language);
    }
}