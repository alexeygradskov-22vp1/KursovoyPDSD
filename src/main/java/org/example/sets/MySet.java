package org.example;

import java.security.SecureRandom;
import java.util.*;

public class MySet implements ISet<MySet>{
    private SortedSet<Integer> _set;

    public MySet(){
        _set = createEmptyList();
    }
    public SortedSet<Integer> createEmptyList() {
        return _set = new TreeSet<>();
    }

    public boolean isEmpty(){
        if (!(_set==null)) {
            return _set.isEmpty();
        }return true;
    }

    public boolean contains(int data){
        boolean contain = false;
        if (isEmpty()){
            return contain;
        }
        else {
            return _set.contains(data);
        }

    }

    public boolean insert(int data){
        if (_set==null){
            _set = new TreeSet<>();
        }
        return _set.add(data);
    }
    public void generate(int length, int min, int max, int del) {
        if (length > 0 && max - min < length) {
            throw new IllegalArgumentException();
        }
        Random random = new SecureRandom();
        int newValue = random.nextInt(max-min+1)+min;
        insert(del == 5 ? (newValue * 5) : newValue * 4 + 1);
        int randomValue;
        for (int i = 1; i < length; ) {
            randomValue = random.nextInt(max-min+1)+min;
            newValue = del == 5 ? (randomValue * 5) : randomValue * 4 + 1;
            if (insert(newValue)) {
                i++;
            }
        }
    }
    public int length() {
        if (isEmpty()) {
            return 0;
        }
        return _set.size();
    }

    public String print(char delimiter) {
        if (isEmpty()) {
            return "Пустое множество";
        }
        StringBuilder result = new StringBuilder();
        _set.stream().forEach(x->{
            String string = x.toString() + delimiter;
            result.append(string);
        });
        result.deleteCharAt(result.length()-1);
        return result.toString();
    }

    public MySet clear(){
        _set.clear();
        return this;
    }

    public boolean isSubset(MySet set) {
        boolean isSubset = true;
        if (isEmpty()
                || set.isEmpty()) {
            return isSubset;
        }
        for (Integer integer : _set) {
            if (!set.contains(integer)) {
                isSubset = false;
            }
        }
        return isSubset;
    }

    public boolean isEqual(MySet set) {
        return isSubset(set) && set.isSubset(this);
    }

    public MySet merge(MySet setB) {
        MySet C = new MySet();
        _set.forEach(C::insert);
        setB._set.forEach(C::insert);
        return C;
    }

    public MySet intersection( MySet set) {
        MySet setC = new MySet();
        if (isEmpty() || set.isEmpty()) {
            return setC;
        }
        _set.forEach(x->{
            if (set.contains(x)) setC.insert(x);
        });
        set._set.forEach(x->{
            if (_set.contains(x)) setC.insert(x);
        });
        return setC;
    }
    public MySet difference(MySet set) {
        MySet setC = new MySet();
        _set.forEach(x->{
            if (!set.contains(x)){
                setC.insert(x);
            }
        });
        return setC;
    }
    public MySet symmetricDifference(MySet set) {
        MySet c = new MySet();
        c = c.merge(difference(set));
        c = c.merge(set.difference(this));
        return c;
    }


}
