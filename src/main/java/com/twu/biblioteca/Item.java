package com.twu.biblioteca;

/**
 * Created by aarni on 1/6/15.
 */
public interface Item {
    public String toString();
    public String getStatus();
    public String getTitle();
    public String getType();
    public void checkout();
    public void returned();
}
