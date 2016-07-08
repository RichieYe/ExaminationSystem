package com.richieye.examinationsystemModel;

import android.util.Log;

/**
 * Created by RichieYe on 2016/7/8.
 */

public class TUserTest1 {
    private int ID;

    public void setID(int ID)
    {
        this.ID=ID;
    }

    public int getID()
    {
        return ID;
    }

    private int TID;

    public void setTID(int iTID)
    {
        this.TID=iTID;
    }

    public int getTID()
    {
        return TID;
    }

    private int Type;

    public void setType(int iType)
    {
        this.Type=iType;
    }

    public int getType()
    {
        return this.Type;
    }

    private int Question;

    public void setQuestion(int iQuestion)
    {
        this.Question=iQuestion;
    }

    public int getQuestion()
    {
        return this.Question;
    }

    private String UAnswer;

    public void setUAnswer(String strUAnswer)
    {
        this.UAnswer=strUAnswer;
    }

    public String getUAnswer()
    {
        //return "aaaaaaa";
        return "".equals(this.UAnswer.trim())?"未作答":this.UAnswer;
    }

    private int TAnswer;

    public void setTAnswer(int iTAnswer)
    {
        this.TAnswer=iTAnswer;
    }

    public int getTAnswer()
    {
        return this.TAnswer;
    }
}
