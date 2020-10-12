package com.example.addresswithdata;

import io.realm.RealmObject;

public class testDBData extends RealmObject {
    private String mName = null;
    private String mPhone = null;
    private String mEmail = null;

    public void setName(String name)
    {
        mName = name;
    }

    public void setPhone(String phone)
    {
        mPhone = phone;
    }

    public void setEMail(String email)
    {
        mEmail = email;
    }

    public String getName()
    {
        return mName;
    }

    public String getPhone()
    {
        return mPhone;
    }

    public String getEMail()
    {
        return mEmail;
    }

}
