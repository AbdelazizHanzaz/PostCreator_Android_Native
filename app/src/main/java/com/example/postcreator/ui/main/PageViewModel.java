package com.example.postcreator.ui.main;

import android.app.Application;
import android.content.Context;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.postcreator.pojo.Post;
import com.example.postcreator.repository.PostsRepository;

import java.util.ArrayList;
import java.util.List;

public class PageViewModel extends ViewModel {

    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private MutableLiveData<Integer> count = new MutableLiveData<>();
    private static List<Integer> list = new ArrayList<>();

    private LiveData<String> mText = Transformations.map(mIndex, new Function<Integer, String>() {
        @Override
        public String apply(Integer input) {
            return "Hello world from section: " + input;
        }
    });

    public void setIndex(int index) {
        mIndex.setValue(index);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setCount(int item){
        list.add(item);
        count.setValue(getListCount().size());
    }

    public MutableLiveData<Integer> getCount() {
        return count;
    }

    public static List<Integer> getListCount(){
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        return list;
    }
}