package com.example.nepal_app.Datalayer;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import com.example.nepal_app.Logic.ChildObj;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class CacheSaving {
    private static final CacheSaving ourInstance = new CacheSaving();
    private ArrayList<ChildObj> childArr = new ArrayList<>();

    private CacheSaving(){

    }

    public static CacheSaving getInstance() {
        return ourInstance;
    }

    public void saveChild(Context context, ArrayList<ChildObj> arr){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Children", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(arr);
        editor.putString("ChildArr",json);
        editor.apply();
    }

    public void deleteImage(String name, Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("Image", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(name).apply();
    }

    public void saveImageNewName(Context context, String oldName, String newName, Bitmap bitmap){

        bitmap = loadImage(context, oldName);

        deleteImage(oldName, context);

        saveImage(context,newName, bitmap);
    }

    public void saveImage(Context context, String name, Bitmap bitmap){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap = Bitmap.createScaledBitmap(bitmap,60,60,true);
        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
        byte[] byteArr = byteArrayOutputStream.toByteArray();
        String encodeImage = Base64.encodeToString(byteArr,Base64.DEFAULT);


        SharedPreferences sharedPreferences = context.getSharedPreferences("Image", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(name, encodeImage);
        editor.apply();
    }

    public Bitmap loadImage(Context context, String name){
        Bitmap bitmap = null;
        SharedPreferences settings = context.getSharedPreferences("Image", Context.MODE_PRIVATE);
        String loadedImage = settings.getString(name, null);

        if (loadedImage != null){
            byte[] b = Base64.decode(loadedImage, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(b,0,b.length);
        }
        return bitmap;
    }


    public ArrayList<ChildObj> loadChild(Context context) {
        SharedPreferences sharedPreferences =  context.getSharedPreferences("Children", Context.MODE_PRIVATE);;
        Gson gson = new Gson();
        String json = sharedPreferences.getString("ChildArr", null);
        Type type = new TypeToken<ArrayList<ChildObj>>() {
        }.getType();
        childArr = gson.fromJson(json, type);
        if (childArr == null){
            childArr = new ArrayList<>();
        }
        return childArr;
    }



}
