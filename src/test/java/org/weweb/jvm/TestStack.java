package org.weweb.jvm;

/**
 * Created by jackshen on 2017/1/2.
 */
public class TestStack {
    private static int count=0;
    public void recursion(){
        count++;
        recursion();
    }

    public static void main(String[] args) {
        try{
            TestStack testStack=new TestStack();
            testStack.recursion();
        }catch (Throwable e){
            System.out.println("deep of stack is "+count);
            e.printStackTrace();
        }


    }
}
