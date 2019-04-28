package controller;

import integration.AccountingSystem;
import integration.InventorySystem;
import model.CashRegister;
import model.ItemDTO;
import model.Receipt;
import model.Sale;

public class Controller
{
  private Sale sale;
  private InventorySystem inventorySystem;
  private AccountingSystem accountingSystem;
  private CashRegister cashRegister;
  private double totalPrice;
  
  public Controller(InventorySystem inventorySystem, AccountingSystem accountingSystem, CashRegister cashRegister)
  {
    this.inventorySystem = inventorySystem;
    this.accountingSystem = accountingSystem;
    this.cashRegister = cashRegister;
  }
  
  public void startNewSale()
  {
    this.sale = new Sale();
    this.sale.turnOnCashRegister();
  }
  
  public ItemDTO addItem(int itemIdentifier, int itemQuantity)
  {
    ItemDTO foundItem = this.inventorySystem.findItem(itemIdentifier);
    if (foundItem != null)
    {
      this.sale.addItem(foundItem, itemQuantity);
      return foundItem;
    }
    return null;
  }
  
  public double pay(double amount)
  {
    double change = this.sale.pay(amount, this.totalPrice);
    return change;
  }
  
  public double indicateAllItemsRegistered()
  {
    this.totalPrice = this.sale.getRunningTotal();
    return this.sale.getRunningTotal();
  }
  
  public double signalDiscountRequest()
  {
    this.totalPrice = this.sale.calcDiscountedPrice();
    return this.totalPrice;
  }
  
  public Receipt requestReceipt()
  {
    Receipt receipt = new Receipt(this.sale, this.sale.getExistingItems());
    return receipt;
  }
}
