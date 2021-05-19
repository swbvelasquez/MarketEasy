package com.portfolio.marketeasy.infrastructure.threads;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import static com.portfolio.marketeasy.core.global.Constants.*;

public class ProductHandler extends Handler {

    private static final String TAG = "ProductHandler";
    private final AppCompatActivity activity;

    public ProductHandler(@NonNull Looper looper,AppCompatActivity activity) {
        super(looper);
        this.activity=activity;
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        try{
            switch (msg.what) {
                case GETALL:
                    Toast.makeText(activity.getApplicationContext(), "GET ALL " + (int)msg.obj, Toast.LENGTH_SHORT).show();
                    break;
                case GETBYID:
                    Toast.makeText(activity.getApplicationContext(),"GET BY ID " + (int)msg.obj,Toast.LENGTH_SHORT).show();
                    break;
                case INSERT:
                    Toast.makeText(activity.getApplicationContext(),"INSERT " + (int)msg.obj,Toast.LENGTH_SHORT).show();
                    break;
                case UPDATE:
                    Toast.makeText(activity.getApplicationContext(),"UPDATE " + (int)msg.obj,Toast.LENGTH_SHORT).show();
                    break;
                case DELETE:
                    Toast.makeText(activity.getApplicationContext(),"DELETE " + (int)msg.obj,Toast.LENGTH_SHORT).show();
                    break;
                case THREAD_ERROR:
                    Exception ex = (Exception)msg.obj;
                    String error = ex.getMessage()!=null?ex.getMessage():"error not defined";
                    Toast.makeText(activity.getApplicationContext(),"ERROR ON THREAD: " + error,Toast.LENGTH_SHORT).show();
                    break;
            }
        }catch (Exception ex){
            if(ex.getMessage()!=null) {
                Log.d(TAG, "handleMessage: " + ex.getMessage());
            }else{
                ex.printStackTrace();
            }
        }
    }
}
