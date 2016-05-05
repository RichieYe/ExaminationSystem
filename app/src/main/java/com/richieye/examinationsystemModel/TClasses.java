package com.richieye.examinationsystemModel;

/**
 * Created by RichieYe on 2016/4/14.
 */
public class TClasses
{
    private int _id;

    public void setID(int ID)
    {
        this._id=ID;
    }

    public int getID()
    {
        return this._id;
    }
    private String _classname;

    public void setClassName(String ClassName)
    {
        this._classname=ClassName;
    }

    public String getClassName()
    {
        return this._classname;
    }

    public TClasses(int Id,String ClassName)
    {
        this._id=Id;
        this._classname=ClassName;
    }

    public TClasses()
    {

    }
}
