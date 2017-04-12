package com.edgarxie.bindingtest.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.edgarxie.bindingtest.BR;

/**
 * Created by edgar on 17-4-5.
 */

public class Employee extends BaseObservable{
    private String firstName;
    private String lastName;
    private boolean fired;
    private String avatar;

    public Employee(String firstName, String lastName,boolean fired) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fired=fired;
    }

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    @Bindable
    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(BR.lastName);
    }

    public void setFired(boolean fired) {
        this.fired = fired;
        notifyChange();
    }

    public boolean isFired() {
        return fired;
    }

    @Bindable
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
