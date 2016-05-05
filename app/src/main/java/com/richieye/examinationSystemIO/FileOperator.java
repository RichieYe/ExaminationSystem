package com.richieye.examinationSystemIO;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;

/**
 * Created by RichieYe on 2016/4/14.
 */
public class FileOperator
{
    public static Bitmap GetBitmapForFileName(String filePath)
    {
        Bitmap bitmap=null;
        if(isFileExists(filePath))
        {
            bitmap= BitmapFactory.decodeFile(filePath);
        }
        return bitmap;
    }

    public static boolean isFileExists(String filePath)
    {
        File file=new File(filePath);
        return file.exists();
    }
}
