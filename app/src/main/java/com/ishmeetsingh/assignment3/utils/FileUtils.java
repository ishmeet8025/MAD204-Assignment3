package com.ishmeetsingh.assignment3.utils;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FileUtils {

    public static String saveNotesToExternal(Context ctx, String filename, String content) {
        try {
            File dir = ctx.getExternalFilesDir(null);
            if (dir == null) return "";
            File file = new File(dir, filename);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(content.getBytes());
            fos.close();
            return file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String readNotesFromExternal(Context ctx, String filename) {
        try {
            File dir = ctx.getExternalFilesDir(null);
            if (dir == null) return "";
            File file = new File(dir, filename);
            if (!file.exists()) return "";
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            br.close();
            fis.close();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
