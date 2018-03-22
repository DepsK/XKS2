// IMyAidlInterface.aidl
package com.dream.xukuan.xk2stu7aidlservice;

// Declare any non-default types here with import statements
import com.dream.xukuan.xk2stu7aidlservice.Car;
interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void setData(String msg);
    String getMsg();
    void sendObject(in Car car);
}
