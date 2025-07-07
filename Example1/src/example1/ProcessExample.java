/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example1;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Vo Quoc An - ce190460
 */
public class ProcessExample {

    //Create ArrayList with n random element

    /**
     *
     * @throws Exception 
     */
    public void run() throws Exception {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        ArrayList<Integer> listNums = new ArrayList<>();
        System.out.println("Enter a integer n: ");
        int n = sc.nextInt();
        if (n < 0) {
            throw new Exception("Varible n must be potsitive number!");
        }
        if (Character.isAlphabetic(n)) {
            throw new Exception("Varible n must be number");
        }
        for (int i = 0; i < n; i++) {
            int val = random.nextInt(n);
            listNums.add(val);
        }
        SelectionSort ss = new SelectionSort(listNums);
        Display dp = new Display(listNums, ss.Sort()); //
        dp.printOut();
    }
}
