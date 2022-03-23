package com.example.bindertest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bindertest.service.RemoteService;

import aidl.IInfoAidlInterface;
import aidl.IInfoInteractAidlCallback;

public class MainActivity extends AppCompatActivity {
    private int number = 1;
    TextView clickResultTv, callbackResultTv;
    private IInfoAidlInterface iInfoAidlInterface;

    //注意这里是 stub
    private IInfoInteractAidlCallback iInfoInteractAidlCallback = new IInfoInteractAidlCallback.Stub() {
        @Override
        public void callback(String result) throws RemoteException {
            callbackResultTv.setText(result);
        }

        @Override
        public void callbackBundle(Bundle bundle) throws RemoteException {

        }
    };
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iInfoAidlInterface = IInfoAidlInterface.Stub.asInterface(service);
            Log.v(Contanst.Tag, "onServiceConnected start");

            try {
                iInfoAidlInterface.registerCallback(iInfoInteractAidlCallback);
            } catch (RemoteException e) {
                Log.v(Contanst.Tag, e.toString());
                e.printStackTrace();
            }
            Log.v(Contanst.Tag, "onServiceConnected end");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.v(Contanst.Tag, "onServiceDisconnected");

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clickResultTv= MainActivity.this.findViewById(R.id.clickresult);
        callbackResultTv= MainActivity.this.findViewById(R.id.callbackresult);
        findViewById(R.id.bind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RemoteService.class);
                bindService(intent, conn, Context.BIND_AUTO_CREATE);

            }
        });

        findViewById(R.id.unbind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(conn);
                if(iInfoAidlInterface!=null){
                    try {
                        iInfoAidlInterface.unregisterCallback(iInfoInteractAidlCallback);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        findViewById(R.id.set).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iInfoAidlInterface == null){
                    showTip();
                    return;
                }
                try {
                    iInfoAidlInterface.setNumber(number);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                number++;
            }
        });

        findViewById(R.id.get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iInfoAidlInterface == null){
                    showTip();
                    return;
                }
                try {
                    clickResultTv.setText(iInfoAidlInterface.getResultInfo("info"));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void showTip(){
        Toast.makeText(MainActivity.this, "请先bind", Toast.LENGTH_SHORT).show();
    }
}