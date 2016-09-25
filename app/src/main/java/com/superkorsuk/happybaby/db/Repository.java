package com.superkorsuk.happybaby.db;

/**
 * Created by Luke on 2016-09-22.
 */

public interface Repository<T> {

    int create(T item);

    void update(T item);

    void delete(T item);


}
