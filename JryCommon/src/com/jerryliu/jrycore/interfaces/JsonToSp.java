
package com.jerryliu.jrycore.interfaces;

import com.jerryliu.jrycore.bean.BaseObject;

public interface JsonToSp {
    public String toJsonString();

    public BaseObject fromJsonString(String s);
}
