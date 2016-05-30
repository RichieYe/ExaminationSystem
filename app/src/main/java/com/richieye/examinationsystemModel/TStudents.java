package com.richieye.examinationsystemModel;

import java.io.Serializable;

/**
 * Created by RichieYe on 2016/5/30.
 */
public class TStudents implements Serializable {
    public int getID() {
        return _id;
    }

    public void setID(int id)
    {
        this._id=id;
    }

    public String getNo() {
        return _No;
    }

    public void setNo(String No)
    {
        this._No=No;
    }

    public String getUserName() {
        return _UserName;
    }

    public void setUserName(String UserName)
    {
        this._UserName=UserName;
    }

    public int getCID() {
        return _CID;
    }

    public void setCID(int CID)
    {
        this._CID=CID;
    }

    public String getPassword() {
        return _Password;
    }

    public void setPassword(String Password)
    {
        this._Password=Password;
    }

    public String getGender() {
        return _Gender;
    }

    public void setGender(String Gender)
    {
        this._Gender=Gender;
    }

    public String getPhone() {
        return _Phone;
    }

    public void setPhone(String Phone)
    {
        this._Phone=Phone;
    }

    public String getAddress() {
        return _Address;
    }

    public void setAddress(String Address)
    {
        this._Address=Address;
    }

    public String getLocalPath() {
        return _LocalPath;
    }

    public void setLocalPath(String LocalPath)
    {
        this._LocalPath=LocalPath;
    }

    public String getServicePath() {
        return _ServicePath;
    }

    public void setServicePath(String ServicePath)
    {
        this._ServicePath=ServicePath;
    }

    private int _id;

    private String _No;

    private String _UserName;

    private int _CID;

    private String _Password;

    private String _Gender;

    private String _Phone;

    private String _Address;

    private String _LocalPath;

    private String _ServicePath;

}
