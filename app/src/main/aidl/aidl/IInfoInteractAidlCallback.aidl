// IInfoInteractAidlCallback.aidl
package aidl;

// Declare any non-default types here with import statements

interface IInfoInteractAidlCallback {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
     void callback(String result);
     void callbackBundle(in android.os.Bundle bundle);

}