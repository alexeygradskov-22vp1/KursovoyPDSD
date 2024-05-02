
package org.example.sets;

import java.security.SecureRandom;
import java.util.Random;

public class CustomSet implements ISet<CustomSet> {
    public Node head;

    public CustomSet() {
        head = createEmptyList();
    }


    public Node createEmptyList() {
        return null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public boolean contains(int data) {
        boolean isContain = false;
        if (!isEmpty()) {
            Node current = head;
            while (current != null) {
                if (current._data == data) {
                    isContain = true;
                    return isContain;
                }
                current = current._next;
            }
        }
        return isContain;
    }

    public boolean insert(int data) {
        if (contains(data)) {
            return false;
        }
        Node node = new Node();
        node._data = data;
        node._next = head;
        head = node;
        return true;
    }

    public void generate(int length, int min, int max) {
        if (length <= 0 || max - min < length) {
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
        Node curr = head;
        int count = 1;
        while (curr._next != null) {
            curr = curr._next;
            count++;
        }
        return count;
    }

    public String print(char delimiter) {
        if (isEmpty()) {
            return "Пустое множество";
        }
        Node curr = head;
        StringBuilder result = new StringBuilder();
        result.append(curr._data);
        curr = curr._next;
        while (curr != null) {
            result.append(delimiter);
            result.append(curr._data);
            curr = curr._next;
        }
        return result.toString();
    }

    public CustomSet clear(){
        head = null;
        return this;
    }

    public boolean isSubset(CustomSet set) {
        boolean isSubset = true;
        if (isEmpty()
                || set.isEmpty()) {
            return isSubset;
        } if (isEmpty()&&set.isEmpty()) throw new IllegalArgumentException("Оба множества пустых");
        Node currA = set.head;
        while (currA != null) {
            if (!contains(currA._data)) {
                isSubset = false;
                return isSubset;
            }
            currA = currA._next;
        }
        return isSubset;
    }

    public boolean isEqual(CustomSet set) {
        return isSubset(set) && set.isSubset(this);
    }

    public CustomSet merge(CustomSet setB) {
        CustomSet C = new CustomSet();
        Node curr = setB.head;
        while (curr != null) {
            C.insert( curr._data);
            curr = curr._next;
        }
        curr = head;
        while (curr != null) {
            C.insert(curr._data);
            curr = curr._next;
        }
        return C;
    }

    public CustomSet intersection( CustomSet set) {
        CustomSet setC = new CustomSet();
        if (isEmpty() || set.isEmpty()) {
            return setC;
        }
        Node curr = head;
        while (curr != null) {
            if (set.contains(curr._data)) {
                setC.insert(curr._data);
            }
            curr = curr._next;
        }
        return setC;
    }

    public CustomSet difference(CustomSet set) {
        CustomSet setC = new CustomSet();
        Node curr = head;
        while (curr != null) {
            if (!set.contains(curr._data)) {
                setC.insert(curr._data);
            }
            curr = curr._next;
        }

        return setC;
    }

    public CustomSet symmetricDifference(CustomSet set) {
        CustomSet c = new CustomSet();
        c = c.merge(difference(set));
        c = c.merge(set.difference(this));
        return c;
    }
}
