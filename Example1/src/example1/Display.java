/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example1;

import java.util.ArrayList;

/**
 *
 * @author Vo Quoc An - ce190460
 */
public class Display {
    

    private ArrayList<Integer> origin;
    private ArrayList<Integer> afterSort;

    public Display(ArrayList<Integer> origin, ArrayList<Integer> afterSort) {
        this.origin = origin;
        this.afterSort = afterSort;
    }

    public ArrayList<Integer> getOrigin() {
        return origin;
    }

    public void setOrigin(ArrayList<Integer> origin) {
        this.origin = origin;
    }

    public ArrayList<Integer> getAfterSort() {
        return afterSort;
    }

    public void setAfterSort(ArrayList<Integer> afterSort) {
        this.afterSort = afterSort;
    }
    /**
     * Class used to print out Unsorted array and sorted array
     */
    public void printOut() {
        System.out.println("Unsorted array: " + getOrigin());
        System.out.print("Sorted array: " + getAfterSort());
    }
}
