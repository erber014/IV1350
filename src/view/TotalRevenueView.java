/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import java.util.ArrayList;
import model.Sale;
import model.SaleObserver;

/**
 * 
 * @author Erik
 */
public class TotalRevenueView implements SaleObserver{
    private ArrayList<Double> Sales = new ArrayList<Double>();
    
    /**
     *Creates an instance of the TotalRevenueView with the values set to zero. 
     */
    public TotalRevenueView() {
        for (int i = 0; i < Sales.size(); i++) {
            Sales.set(i, 0.0);
        }
    }
   
    
    @Override
    public void newSale(Sale sale) {
        addNewSale(sale);
        printCurrentState();
    }
    
    private void addNewSale(Sale sale) {
        Sales.add(sale.getAmountPaid());
    }
    
    private void printCurrentState() {
        System.out.println("*******THE TOTAL REVENUE IS********");
        double totalRev = 0.0;
        for(int i=0; i < Sales.size(); i++) {
            totalRev += Sales.get(i);
            System.out.println("Revenue from Sale: " + Sales.get(i));
        }
        System.out.println("Total revenue: " + totalRev);
        System.out.println("***********************************");
    }
}
