package com.portfolio.marketeasy.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.portfolio.marketeasy.R;
import com.portfolio.marketeasy.infrastructure.threads.ProductHandler;
import com.portfolio.marketeasy.infrastructure.threads.ProductThread;
import static com.portfolio.marketeasy.core.global.Constants.*;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        try{
            ProductHandler handler = new ProductHandler(getMainLooper(),this);
            ProductThread thread = new ProductThread(handler,GETALL);
            thread.start();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}