package com.example.postcreator.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private MutableLiveData<Integer> count = new MutableLiveData<>();


    public void setCount(int size) {
        this.count.setValue(size);
    }

    public MutableLiveData<Integer> getCount() {
        return count;
    }
}
