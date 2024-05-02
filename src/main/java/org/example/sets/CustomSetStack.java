package org.example;

import java.security.SecureRandom;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Stack;

public class CustomSetStack implements ISet<CustomSetStack> {


        private Stack<Integer> _set;

        public CustomSetStack(){
            _set = createEmptyList();
        }
        public Stack<Integer> createEmptyList() {
            _set = new Stack<>();
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
                _set = new Stack<>();
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

        public CustomSetStack clear(){
            _set.clear();
            return this;
        }

        public boolean isSubset(CustomSetStack set) {
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

        public boolean isEqual(CustomSetStack set) {
            return isSubset(set) && set.isSubset(this);
        }

        public CustomSetStack merge(CustomSetStack setB) {
            CustomSetStack C = new CustomSetStack();
            _set.forEach(C::insert);
            setB._set.forEach(C::insert);
            return C;
        }

        public  CustomSetStack intersection( CustomSetStack set) {
            CustomSetStack setC = new  CustomSetStack();
            if (isEmpty() || set.isEmpty()) {
                return setC;
            }
            _set.forEach(x->{if(set.contains(x)) setC.insert(x);});
            return setC;
        }
        public  CustomSetStack difference( CustomSetStack set) {
            CustomSetStack setC = new  CustomSetStack();
            _set.forEach(x->{if(!set.contains(x)) setC.insert(x);});
            return setC;
        }
        public  CustomSetStack symmetricDifference( CustomSetStack set) {
            CustomSetStack c = new  CustomSetStack();
            c = c.merge(difference(set));
            c = c.merge(set.difference(this));
            return c;
        }

    }
