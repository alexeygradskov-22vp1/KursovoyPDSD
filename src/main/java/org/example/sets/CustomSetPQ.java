package org.example;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class CustomSetPQ implements ISet<CustomSetPQ>{

        private PriorityQueue<Integer> _set;

        public CustomSetPQ(){
            _set = createEmptyList();
        }
        public PriorityQueue<Integer> createEmptyList() {
            _set = new PriorityQueue<>();
            return _set;
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
                _set = new PriorityQueue<>();
            }
            boolean isAdded = false;
            if (!contains(data)){
                _set.add(data);
                isAdded = true;
            }
            return isAdded;
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
            _set.forEach(x->{
                String string = x.toString() + delimiter;
                result.append(string);
            });
            result.deleteCharAt(result.length()-1);
            return result.toString();
        }

        public CustomSetPQ clear(){
            _set.clear();
            return this;
        }

        public boolean isSubset(CustomSetPQ set) {
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

        public boolean isEqual(CustomSetPQ set) {
            return isSubset(set) && set.isSubset(this);
        }

        public CustomSetPQ merge(CustomSetPQ setB) {
            CustomSetPQ C = new CustomSetPQ();
            _set.forEach(C::insert);
            setB._set.forEach(C::insert);
            return C;
        }

        public CustomSetPQ intersection(CustomSetPQ set) {
            CustomSetPQ setC = new CustomSetPQ();
            if (isEmpty() || set.isEmpty()) {
                return setC;
            }
            _set.forEach(x->{if(set.contains(x)) setC.insert(x);});
            return setC;
        }
        public CustomSetPQ difference(CustomSetPQ set) {
            CustomSetPQ setC = new CustomSetPQ();
            _set.forEach(x->{if(!set.contains(x)) setC.insert(x);});
            return setC;
        }
        public CustomSetPQ symmetricDifference(CustomSetPQ set) {
            CustomSetPQ c = new CustomSetPQ();
            c = c.merge(difference(set));
            c = c.merge(set.difference(this));
            return c;
        }

    }


