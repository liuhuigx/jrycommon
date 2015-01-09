
package com.jerryliu.jrycore.bean;

import com.jerryliu.jrycore.interfaces.JsonToSp;

public class BaseJsonObject extends BaseObject implements JsonToSp {

    public static BaseJsonObject createfromJsonString(String s) {
        return new BaseJsonObject().fromJsonString(s);
    }

    public BaseJsonObject() {

    }

    @Override
    public String toJsonString() {
        return null;
    }

    @Override
    public BaseJsonObject fromJsonString(String s) {
        return null;
    }

}
