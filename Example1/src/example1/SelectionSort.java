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
public class SelectionSort {

    private ArrayList<Integer> listNums;

    /**
     *
     * @param listNums
     */
    public SelectionSort(ArrayList<Integer> listNums) {
        this.listNums = listNums;
    }

    /**
     *
     * @return
     */
    public ArrayList<Integer> getListNums() {
        return listNums;
    }

    /**
     *
     * @param listNums
     */
    public void setListNums(ArrayList<Integer> listNums) {
        this.listNums = listNums;
    }

    /**
     *
     * @return
     */
    public ArrayList<Integer> Sort() {
        ArrayList<Integer> afterSort = new ArrayList<>(this.listNums);
        for (int i = 0; i < afterSort.size() - 1; i++) {
            int minLocation = i;
            int firstVal = afterSort.get(i);
            for (int j = i; j < afterSort.size(); j++) {
                if (afterSort.get(minLocation) > afterSort.get(j)) {
                    minLocation = j;
                }
            }
            afterSort.set(i, afterSort.get(minLocation));
            afterSort.set(minLocation, firstVal);
        }
        return afterSort;

    }

}
