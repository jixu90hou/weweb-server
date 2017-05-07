package org.weweb.interceptor;

/**
 * Created by jackshen on 2016/12/31.
 */

public enum LogType{
    LOGIN(0),REGISTER(1),LOGOUT(2),QUERYUSER(3);
    private int type;
    private LogType(int type){
        this.type=type;
    };

    public int getType() {
        return type;
    }
}
