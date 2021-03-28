package com.hypech.case83_roomstepbystep.async;

import android.os.AsyncTask;

import com.hypech.case83_roomstepbystep.WordDao;

public class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
    private WordDao wordDao;
    public DeleteAllAsyncTask(WordDao wordDao) {
        this.wordDao=wordDao;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        wordDao.deleteAllWords();
        return null;
    }
}
