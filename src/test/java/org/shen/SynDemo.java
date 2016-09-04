package org.shen;

/**
 * Created by jackshen on 16/8/21.
 */
public class SynDemo extends  Thread{
    private int age=21;
    private String name="zhangsan";
    public synchronized void setValue(String name,int age){
        this.name=name;
        try {
            Thread.sleep(2000);
            this.age=age;
            System.out.println("name:"+name+"\t age:"+age);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public synchronized void getValue(){
        System.out.println("name:"+name+"\t age:"+age);
    }
    public synchronized void setValueAgain(){
        this.age=99;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //zhangsan,99 | zhangsan,21 |wangba,99
        this.name="wangba";
        System.out.println("name:"+name+"\t age:"+age);

    }

    public static void main(String[] args) {
        System.out.println("===========开始执行========");
       final SynDemo synDemo=new SynDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synDemo.setValue("wangwu",45);
     // synDemo.setValueAgain();
            }
        }).start();

        synDemo.getValue();
        synDemo.age=0;
        synDemo.name="";

    }
}
