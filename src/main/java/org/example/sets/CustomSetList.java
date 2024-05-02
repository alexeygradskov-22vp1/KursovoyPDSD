package org.example.sets;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class CustomSetList implements ISet<CustomSetList> {
    private List<Integer> _set;

    public CustomSetList(){
        _set = createEmptyList();
    }
    public List<Integer> createEmptyList() {
        return new LinkedList<>();
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
            _set = new LinkedList<>();
        }
        boolean isAdded = false;
        if (!contains(data)){
            _set.add(0, data);
            isAdded = true;
        }
        return isAdded;
    }
    public void generate(int length, int min, int max) {
        if (length > 0 && max - min < length) {
            throw new IllegalArgumentException();
        }
        Random random = new SecureRandom();
        int newValue = random.nextInt(max-min+1)+min;
        insert(newValue);
        int randomValue;
        for (int i = 1; i < length; ) {
            randomValue = random.nextInt(max-min+1)+min;
            newValue = randomValue;
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
        _set.forEach(x->{
            String string = x.toString() + delimiter;
            result.append(string);
        });
        result.deleteCharAt(result.length()-1);
        return result.toString();
    }

    public CustomSetList clear(){
        _set.clear();
        return this;
    }

    public boolean isSubset(CustomSetList set) {
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

    public boolean isEqual(CustomSetList set) {
        return isSubset(set) && set.isSubset(this);
    }

    public CustomSetList merge(CustomSetList setB) {
        CustomSetList C = new CustomSetList();
        _set.forEach(C::insert);
        setB._set.forEach(C::insert);
        return C;
    }

    public CustomSetList intersection( CustomSetList set) {
        CustomSetList setC = new CustomSetList();
        if (isEmpty() || set.isEmpty()) {
            return setC;
        }
        _set.forEach(x->{if(set.contains(x)) setC.insert(x);});
        return setC;
    }
    public CustomSetList difference(CustomSetList set) {
        CustomSetList setC = new CustomSetList();
        _set.forEach(x->{if(!set.contains(x)) setC.insert(x);});
        return setC;
    }
    public CustomSetList symmetricDifference(CustomSetList set) {
        CustomSetList c = new CustomSetList();
        c = c.merge(difference(set));
        c = c.merge(set.difference(this));
        return c;
    }

}
