package org.example;

public interface ISet<T> {

    boolean isEmpty();

    boolean contains(int data);

    boolean insert(int data);

    void generate(int length, int min, int max, int del);

    int length();

    String print(char delimiter);

    T clear();

    boolean isSubset(T set);

    boolean isEqual(T set);

    T merge(T setB);

    T intersection(T set);

    T difference(T set);

    T symmetricDifference(T set);
}
