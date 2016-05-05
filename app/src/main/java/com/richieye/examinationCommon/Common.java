package com.richieye.examinationCommon;

import java.util.Random;

/**
 * Created by RichieYe on 2016/4/14.
 */
public class Common
{

    public static String getRandomCode(int length)
    {
        if(length<3)
        {
            return "Error:长度不能少于3";
        }

        String[] strCode={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","p","q","r","s","t","u","v","w","x","y","z",
                "A","B","C","D","E","F","G","H","I","J","K","L","M","N","P","Q","R","S","T","U","V","X","Y","Z","0","1","2","3","4","5","6","7","8","9"};

        StringBuffer buffer=new StringBuffer();
        for(int i=0;i<length;i++)
        {
            Random rd=new Random();

            buffer.append(strCode[rd.nextInt(strCode.length)]);
        }

        return  buffer.toString();
    }
}
