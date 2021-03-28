package com.hypech.case83_roomstepbystep;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Word {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="english_word")
    private String word;
    @ColumnInfo(name="chinese_meaning")
    private String chinesemeaning;

    public Word(String word, String chinesemeaning){
        this.word = word;
        this.chinesemeaning=chinesemeaning;
    }

    public int getId(){        return id;    }
    public String getWord(){        return word;    }
    public String getChinesemeaning(){        return chinesemeaning;    }

    public void setId(int id){        this.id = id;    }
    public void setWord(String word){        this.word = word;    }
    public void setChinesemeaning(String chinesemeaning){        this.chinesemeaning = chinesemeaning;    }
}
