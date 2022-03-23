package com.example.bindertest;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

import aidl.IInfoAidlInterface;
import aidl.IInfoInteractAidlCallback;

public class BinderImpl extends IInfoAidlInterface.Stub {
    private int i = 8;
    private IInfoInteractAidlCallback iInfoInteractAidlCallback;

    @Override
    public void setNumber(int number) throws RemoteException {
        i = number;
        Log.v(Contanst.Tag, "BinderImpl setNumber");
        if (iInfoInteractAidlCallback != null) {
            Log.v(Contanst.Tag, "BinderImpl setNumber iInfoInteractAidlCallback");
            iInfoInteractAidlCallback.callback("callback_ResultInfo:" + i);
        }

    }

    @Override
    public int getResult() throws RemoteException {
        Log.v(Contanst.Tag, "BinderImpl getResult");
        return i;
    }

    @Override
    public String getResultInfo(String info) throws RemoteException {
        Log.v(Contanst.Tag, "BinderImpl getResultInfo");

        return info + "_ResultInfo:" + i;
    }

    @Override
    public String getBundleResult(Bundle bundle) throws RemoteException {
        return null;
    }

    @Override
    public void registerCallback(IInfoInteractAidlCallback callback) throws RemoteException {
        iInfoInteractAidlCallback = callback;
        Log.v(Contanst.Tag, "BinderImpl registerCallback");

    }

    @Override
    public void unregisterCallback(IInfoInteractAidlCallback callback) throws RemoteException {
        iInfoInteractAidlCallback = null;
        Log.v(Contanst.Tag, "BinderImpl unregisterCallback");

    }

}
