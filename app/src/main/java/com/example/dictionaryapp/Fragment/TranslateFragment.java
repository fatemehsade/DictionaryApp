package com.example.dictionaryapp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.dictionaryapp.Model.Word;
import com.example.dictionaryapp.R;
import com.example.dictionaryapp.Repository.WordRepository;


public class TranslateFragment extends Fragment {
    public static final String ARGS_WORD = "com.example.dictionaryapp.Fragment.word";
    public static final String ARGS_LANGUAGE = "com.example.dictionaryapp.Fragment.language";
    private EditText mEditTextWord, mEditTextTranslate;
    private Button mButtonEdit, mButtonDelete;
    private Word mWord;
    private WordRepository mRepository;
    private ImageButton mImageView_share;
    private Word inputWord;


    public TranslateFragment() {
        // Required empty public constructor
    }


    public static TranslateFragment newInstance(Word word, String language) {
        TranslateFragment fragment = new TranslateFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARGS_WORD, word);
        args.putString(ARGS_LANGUAGE, language);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWord = (Word) getArguments().getSerializable(ARGS_WORD);
        mRepository = WordRepository.getInstance(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_translate, container, false);
        findViews(view);
        initViews();
        setListener();
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        UpdateUi();
    }

    private void findViews(View view) {
        mEditTextWord = view.findViewById(R.id.edit_txt_word);
        mEditTextTranslate = view.findViewById(R.id.edit_txt_translate);
        mButtonEdit = view.findViewById(R.id.edit_btn);
        mButtonDelete = view.findViewById(R.id.delete_btn);
        mImageView_share = view.findViewById(R.id.share_btn);
    }

    private void initViews() {
        mEditTextWord.setText(mWord.getWord());
        mEditTextTranslate.setText(mWord.getTranslate());
        UpdateUi();
    }

    private void setListener() {
        mEditTextWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                mWord.setWord(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mEditTextTranslate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mWord.setTranslate(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRepository.delete(mWord);
                getActivity().finish();

            }
        });
        mButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRepository.update(mWord);
                getActivity().finish();

            }
        });
        mImageView_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_STREAM, getDescriptionTask());
                Toast.makeText(getActivity(), getDescriptionTask(), Toast.LENGTH_SHORT).show();
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });


    }

    private void UpdateUi() {
        mRepository.update(mWord);

    }

    private String getDescriptionTask() {
        String TaskDescription;
        if (inputWord != null) {
            TaskDescription = getString(
                    R.string.description_word,
                    inputWord.getWord(),
                    inputWord.getTranslate()
            );
        } else {
            TaskDescription = getString(
                    R.string.description_word,
                    mWord.getWord(),
                    mWord.getTranslate()
            );

        }
        return TaskDescription;


    }
}