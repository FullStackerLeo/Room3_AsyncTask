package com.hypech.case83_roomstepbystep.async;

import android.os.AsyncTask;

import com.hypech.case83_roomstepbystep.Word;
import com.hypech.case83_roomstepbystep.WordDao;

public class DeleteAsynTask extends AsyncTask<Word, Void, Void> {
    private WordDao wordDao;

    public DeleteAsynTask(WordDao wordDao){
        this.wordDao = wordDao;
    }

    @Override
    protected Void doInBackground(Word... words) {
        wordDao.deleteWords(words);
        return null;
    }
}
