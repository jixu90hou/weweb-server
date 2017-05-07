package org.weweb.datastructure;

/**
 * Created by jackshen on 2016/12/27.
 */
public class QuickSortExample {
    public static void main(String[] args) {
        int[] a={99,11,222,33,9,100,5,1,44,10};
        int[] b=quickSort(a);
        for (int i=0;i<b.length;i++)
            System.out.println(b[i]);
    }
    public static int[] quickSort(int[] a){
        for (int i=1;i<a.length;i++){
            if (a[0]>a[i]){
                a[0]=a[i];
                quickSort(a);
            }
        }
        return a;
    }
}
