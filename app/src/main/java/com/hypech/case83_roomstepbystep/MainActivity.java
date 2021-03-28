package com.hypech.case83_roomstepbystep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hypech.case83_roomstepbystep.async.DeleteAllAsyncTask;
import com.hypech.case83_roomstepbystep.async.DeleteAsynTask;
import com.hypech.case83_roomstepbystep.async.InsertAsyncTask;
import com.hypech.case83_roomstepbystep.async.UpdateAsyncTask;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    WordDatabase wordDatabase;
    WordDao wordDao;
    LiveData<List<Word>> allWordsLive;

    Button buttonInsert, buttonUpdate, buttonDelete, buttonClear;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        wordDatabase = Room.databaseBuilder(this, WordDatabase.class, "word_database")
//                .allowMainThreadQueries()       //in first step, we allow Main Thread update
//                .build();

        wordDatabase = WordDatabase.getDatabase(this);
        wordDao = wordDatabase.getWordDao();
        allWordsLive = wordDao.getAllWordsLive();

        buttonInsert = findViewById(R.id.buttonInsert);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonClear  = findViewById(R.id.buttonClear);

        textView     = findViewById(R.id.textViewHW);
        allWordsLive.observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                StringBuilder text = new StringBuilder();
                for (int i = 0; i < words.size(); i++){
                    Word word = words.get(i);
                    text.append(word.getId()).append("! ").append(word.getWord()).append("= ").append(word.getChinesemeaning()).append("\n");
                }
                textView.setText(text.toString());
            }
        });

        buttonInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Word word1 = new Word("Hello","你好");
                Word word2 = new Word("World","世界");
                //wordDao.insertWords(word1, word2);
                new InsertAsyncTask(wordDao).execute(word1, word2);     //updateView();
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Word word = new Word("Hi","你好啊");
                word.setId(2);
//                wordDao.updateWords(word);
                new UpdateAsyncTask(wordDao).execute(word);
//                updateView();
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DeleteAllAsyncTask(wordDao).execute();
                //wordDao.deleteAllWords();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word word = new Word("Hi2","你好啊2");
                word.setId(2);
                //wordDao.deleteWords(word);
                new DeleteAsynTask(wordDao).execute(word);
            }
        });

    }
/*
    void updateView(){
        List<Word> list = wordDao.getAllWords();
        Log.e("text:  ", String.valueOf(list.size()));
        String text = "";
        for(int i = 0; i < list.size(); i++){
            Word word = list.get(i);
            text += word.getId() +":"+word.getWord()+"="+word.getChinesemeaning()+"\n";
            Log.e("text:  ", text);
        }
        Log.e("text2:  ", text);
        textView.setText(text);
    }*/
}