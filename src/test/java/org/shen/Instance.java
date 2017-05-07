package org.shen;


import com.bea.xml.stream.util.Stack;

/**
 * Created by jackshen on 16/9/7.
 */
public class Instance {

    Stack stack;

    private Instance(){}
    private static Instance instance=new Instance();//没有要求延迟加载这是最简单的线程安全单例
    public static Instance getInstance(){
        return instance;
    }
}
