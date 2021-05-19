package com.portfolio.marketeasy.infrastructure.threads;

import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import static com.portfolio.marketeasy.core.global.Constants.*;

public class ProductThread extends Thread{

    private static final String TAG = "ProductThread";
    private final ProductHandler handler;
    private final int requestCode;

    public ProductThread(ProductHandler handler, int requestCode) {
        this.handler=handler;
        this.requestCode=requestCode;
    }

    @Override
    public synchronized void start() {
        super.start();
        Log.d(TAG, "start: ");
    }

    @Override
    public void run() {
        Log.d(TAG, "run: start");
        if(handler!=null && !isInterrupted()) {
            Log.d(TAG, "run: working");
            Message message;
            try {
                int result = 100;

                switch (requestCode) {
                    case GETALL:
                        result=1;
                        break;
                    case GETBYID:
                        result=2;
                        break;
                    case INSERT:
                        result=3;
                        break;
                    case UPDATE:
                        result=4;
                        break;
                    case DELETE:
                        result=5;
                        break;
                }
                message= handler.obtainMessage(requestCode, result);
                message.sendToTarget();
            } catch (Exception ex) {
                message= handler.obtainMessage(THREAD_ERROR, ex);
                message.sendToTarget();
            }
        }
        Log.d(TAG, "run: end");
    }

    @Override
    public void interrupt() {
        super.interrupt();
        Log.d(TAG, "interrupt: ");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Log.d(TAG, "finalize: ");
    }
}
