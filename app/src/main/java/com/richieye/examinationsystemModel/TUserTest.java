package com.richieye.examinationsystemModel;

import android.util.Log;

/**
 * Created by RichieYe on 2016/7/7.
 */

public class TUserTest {

    private int _iD;

    public void setID(int ID)
    {
        this._iD=ID;
    }

    public int getID()
    {
        return _iD;
    }

    private int _tID;

    public void setTID(int iTID)
    {
        this._tID=iTID;
    }

    public int getTID()
    {
        return _tID;
    }

    private int _type;

    public void setType(int iType)
    {
        this._type=iType;
    }

    public int getType()
    {
        return this._type;
    }

    private int _question;

    public void setQuestion(int iQuestion)
    {
        this._question=iQuestion;
    }

    public int getQuestion()
    {
        return this._question;
    }

    private String _uAnswer;

    public void setUAnswer(String strUAnswer)
    {
        this._uAnswer=strUAnswer;
    }

    public String getUAnswer()
    {
        //return "aaaaaaa";
        return "".equals(this._uAnswer.trim())?"未作答":this._uAnswer;
    }

    private int _tAnswer;

    public void setTAnswer(int iTAnswer)
    {
        this._tAnswer=iTAnswer;
    }

    public int getTAnswer()
    {
        return this._tAnswer;
    }
}
