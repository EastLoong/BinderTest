// IInfoAidlInterface.aidl
package aidl;

//rebuild后在app build/generated 文件夹下会生成对应的aidl文件
// Declare any non-default types here with import statements
import aidl.IInfoInteractAidlCallback;
interface IInfoAidlInterface {
    void setNumber(int number);
    //每次手动去获取效率太低了，可以加下面的registerCallback回调自动通知客户端
    int getResult();
    String getResultInfo(String info);
    String getBundleResult(in android.os.Bundle bundle);
    //最好是写全路径包名+类名或者上面一样把这个aidl类 import进来import aidl.IInfoInteractAidlCallback，否则可能识别不出来会报错
    void  registerCallback(IInfoInteractAidlCallback callback);
    void  unregisterCallback(IInfoInteractAidlCallback callback);
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */

}