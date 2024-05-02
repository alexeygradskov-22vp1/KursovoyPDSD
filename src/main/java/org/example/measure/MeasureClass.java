package org.example;

import java.time.Clock;

public class MeasureClass<K> implements IMeasure< K>{
    Clock clock;
    public MeasureClass(){
        clock = Clock.systemDefaultZone();
    }
    @Override
    public long measureGenerate(int N, ISet<K> set) {
        long start = 0, end=0;
        start = clock.millis();
        set.generate(N, 0 , 1000, 1);
        end = clock.millis() - start;
        return end;
    }

    @Override
    public long measureIntersection(ISet<K> setA, K setB) {
        long start = 0, end=0;
        start = clock.millis();
        setA.intersection(setB);
        end = clock.millis() - start;
        return end;
    }

    @Override
    public long measureSubset(ISet<K> setA, K setB) {
        long start = 0, end=0;
        start = clock.millis();
        setA.isSubset(setB);
        end = clock.millis() - start;
        return end;
    }

    @Override
    public long measureEqual(ISet<K> setA, K setB) {
        long start = 0, end=0;
        start = clock.millis();
        setA.isEqual(setB);
        end = clock.millis() - start;
        return end;
    }

    @Override
    public long measureMerge(ISet<K> setA, K setB) {
        long start = 0, end=0;
        start = clock.millis();
        setA.merge(setB);
        end = clock.millis() - start;
        return end;
    }

    @Override
    public long measureDiff(ISet<K> setA, K setB) {
        long start = 0, end=0;
        start = clock.millis();
        setA.difference(setB);
        end = clock.millis() - start;
        return end;
    }

    @Override
    public long measureSymDiff(ISet<K> setA, K setB) {
        long start = 0, end=0;
        start = clock.millis();
        setA.symmetricDifference(setB);
        end = clock.millis() - start;
        return end;
    }

    @Override
    public long measureLength(ISet<K> set) {
        long start = 0, end=0;
        start = clock.millis();
        set.length();
        end = clock.millis() - start;
        return end;
    }
}
