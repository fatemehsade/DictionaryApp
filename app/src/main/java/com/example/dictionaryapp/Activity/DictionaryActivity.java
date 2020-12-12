package com.example.dictionaryapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.dictionaryapp.Fragment.DictionaryFragment;
import com.example.dictionaryapp.R;

public class DictionaryActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context){
        Intent intent=new Intent(context,DictionaryActivity.class);
        return intent;
    }


    @Override
    public Fragment createFragment() {
        return DictionaryFragment.newInstance();
    }
}