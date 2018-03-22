package com.dream.xukuan.xk2stu7aidlservice;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author XK
 * @date 2018/3/22.
 */
public class Car implements Parcelable {

    private String name;
    private int price;

    public Car() {
    }

    protected Car(Parcel in) {
        name = in.readString();
        price = in.readInt();
    }

    //反序列的接口
    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel source) {
            return new Car(source);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public Car(String name, int price) {
        this.name = name;
        this.price = price;
    }

    

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(price);
    }
}