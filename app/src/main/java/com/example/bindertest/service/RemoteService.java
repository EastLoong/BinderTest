package com.example.bindertest.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.bindertest.BinderImpl;
import com.example.bindertest.Contanst;

public class RemoteService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(Contanst.Tag, "RemoteService onCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.v(Contanst.Tag, "RemoteService onBind");
        return new BinderImpl();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(Contanst.Tag, "RemoteService onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.v(Contanst.Tag, "RemoteService onRebind");
        super.onRebind(intent);
    }

    @Override
    public void onDestroy() {
        Log.v(Contanst.Tag, "RemoteService onDestroy");
        super.onDestroy();
    }
}
