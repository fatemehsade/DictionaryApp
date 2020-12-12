package com.example.dictionaryapp.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.dictionaryapp.Activity.TranslateActivity;
import com.example.dictionaryapp.Model.Word;
import com.example.dictionaryapp.R;
import com.example.dictionaryapp.Repository.WordRepository;

import java.util.ArrayList;
import java.util.List;


public class DictionaryFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private WordRepository mRepository;
    private String language;
    private WordAdaptor mAdaptor;
    private EditText mEditTextSearch;


    public DictionaryFragment() {
        // Required empty public constructor
    }


    public static DictionaryFragment newInstance() {
        DictionaryFragment fragment = new DictionaryFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository = WordRepository.getInstance(getActivity());
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dictionary, container, false);
        findViews(view);
        initViews();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUi();
    }



    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mEditTextSearch = view.findViewById(R.id.search_app_bar);
    }

    private void initViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUi();
    }

    private void updateUi() {
        List<Word> words = mRepository.getList(language);
        if (mAdaptor == null) {
            mAdaptor = new WordAdaptor(words);
            mRecyclerView.setAdapter(mAdaptor);
        } else {
            mAdaptor.setWords(words);
            mAdaptor.notifyDataSetChanged();
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.lanquege, menu);
        //inflater.inflate(R.menu.menu_actionbar,menu);
        MenuItem searchItem=menu.findItem(R.id.search);
        SearchView searchView= (SearchView)
                searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdaptor.getFilter().filter(newText);
                return false;
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.persion_item:
                language = "PERSION";
                return true;
            case R.id.Ebglish_item:
                language = "ENGLISH";
                return true;

            case R.id.add_item:
                if (language == null) {
                    language = "PERSION";
                }
                Word word = new Word();
                word.setLanguage(language);
                mRepository.add(word);
                Intent intent = TranslateActivity.newIntent(getActivity(), word, language);
                startActivity(intent);
                return true;

        }
        return false;
    }





    public class WordAdaptor extends RecyclerView.Adapter<WordViewHolder> implements Filterable {
        List<Word> mWords;
        private List<Word> mSearchResult;


        public List<Word> getWords() {
            return mWords;
        }

        public void setWords(List<Word> words) {
            mWords = words;
        }

        public WordAdaptor(List<Word> words) {

            mWords = words;
            mSearchResult=new ArrayList<>(mWords);


        }


        @NonNull
        @Override
        public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.row_item, parent, false);
            return new WordViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
            Word word = mWords.get(position);
            holder.bindWord(word);

        }

        @Override
        public int getItemCount() {
            return mWords.size();
        }

        private Filter mFilter=new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Word> resultList = new ArrayList<>();
                if (constraint == null || constraint.length() == 0)
                    resultList.addAll(mSearchResult);
                else {

                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (Word word : mSearchResult) {
                        if (word.getWord().contains(filterPattern))
                            resultList.add(word);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = resultList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mWords.clear();
                mWords.addAll((List) results.values);
                notifyDataSetChanged();
            }
        };

        @Override
        public Filter getFilter() {
            return mFilter;
        }
    }


    public class WordViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewWord;
        private Word mWord;

        public WordViewHolder(@NonNull View itemView) {

            super(itemView);
            mTextViewWord = itemView.findViewById(R.id.word_row);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = TranslateActivity.newIntent(getActivity(), mWord, language);
                    startActivity(intent);
                }
            });
        }

        public void bindWord(Word word) {
            mWord = word;
            mTextViewWord.setText(word.getWord());

        }
    }
}
