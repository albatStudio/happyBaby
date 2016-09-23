package com.superkorsuk.happybaby.db;

/**
 * Created by Luke on 2016-09-22.
 */

public interface Mapper<From, To> {
    To map(From from);
}
