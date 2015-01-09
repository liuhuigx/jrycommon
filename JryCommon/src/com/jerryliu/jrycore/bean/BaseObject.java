
package com.jerryliu.jrycore.bean;

public class BaseObject {

    private long id;

    public BaseObject() {
        setId(System.currentTimeMillis());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
