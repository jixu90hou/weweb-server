package org.weweb.jvm;

/**
 * Created by jackshen on 2017/1/2.
 */
public class TestStack2 {
    private static int count=0;
    public void recursion(long a,long b,long c){
        long d=0,e=0,f=0;
        count++;
        recursion(a,b,c);
    }

    public static void main(String[] args) {
        try{
            TestStack2 testStack2=new TestStack2();
            testStack2.recursion(1L,2L,3L);
        }catch (Throwable e){
            System.out.println("deep of stack is "+count);
            e.printStackTrace();
        }

    }
}
