package com.util.volley;

/**
 * Created by risen on 16/1/8.
 */
public interface UIDataListener<T> {
    public void onDataChanged(T data);
    public void onErrorHappened(String errorCode, String errorMessage);
}
