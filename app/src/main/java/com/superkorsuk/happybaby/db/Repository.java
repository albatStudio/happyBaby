package com.superkorsuk.happybaby.db;

/**
 * Created by Luke on 2016-09-22.
 */

public interface Repository<T> {

    long add(T item);

    void add(Iterable<T> items);

    void update(T item);

    void remove(T item);

}
