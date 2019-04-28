package model;
/**
 * Represents a sale in a grocery store.
 */
public class Sale
{
  private int vAT;
  private ItemDTO[] itemList = new ItemDTO[4];
  private double runningTotal = 0.0D;
  private int itemsExisting = 0;
  private double change;
  private double payment;
  CashRegister cashRegister;
  boolean itemFlag = false;
  boolean discountFlag = false;
  
  
  /**
   * Adds an item to a list which represents the items that are to be bought/sold
   * @param item Represents the item to be added to the sale
   * @param quantity Represents the quantity of the item that is added to the list
   */
  public void addItem(ItemDTO item, int quantity)
  {
    if (this.itemList[0] == null) {
      this.itemList[0] = item;
    }
    for (int i = 0; i < this.itemsExisting + 1; i++) {
      if ((item.getItemIdentifier() == this.itemList[i].getItemIdentifier()) && (!this.itemFlag))
      {
        this.itemList[i].updateQuantity(quantity);
        this.itemFlag = true;
        i = this.itemsExisting + 2;
      }
    }
    if (!this.itemFlag)
    {
      this.itemList[(this.itemsExisting + 1)] = item;
      item.updateQuantity(quantity);
      this.itemsExisting += 1;
    }
    this.itemFlag = false;
    
    calculateRunningTotal(item, quantity);
  }
  
 /**
  * Creates an instance of CashRegiser, to represent the cashier
  * turning the register on
  */
  public void turnOnCashRegister()
  {
    this.cashRegister = new CashRegister();
  }
  // Why is newTotalPrice a thing here??
  
  /**
   * Represents the customer paying for the items in the sale
   * @param payment Is the amount of money the customer pays
   * @param newTotalPrice
   * @return 
   */
  public double pay(double payment, double newTotalPrice)
  {
    this.change = this.cashRegister.calculateChange(payment, newTotalPrice);
    this.payment = payment;
    this.cashRegister.addPayment(payment - this.change);
    return this.change;
  }
  
  /**
   * Calculates the runningtotal of the sale using an item from the inventory system
   * and the quantity of that item.
   * @param foundItem Represents an item found in the inventory system
   * @param itemQuantity the quantity of the items found.
   */
  public void calculateRunningTotal(ItemDTO foundItem, int itemQuantity)
  {
    this.runningTotal += foundItem.getPrice() * itemQuantity;
  }
  /**
   * 
   * @return the running total.
   */
  public double getRunningTotal()
  {
    if (!this.discountFlag) {
      return this.runningTotal;
    }
    return this.runningTotal * 0.8;
  }
  
  /**
   * 
   * @return the list of items
   */
  public ItemDTO[] getItemList()
  {
    return this.itemList;
  }
  
  /**
   * 
   * @return the number of items in the list
   */
  public int getExistingItems()
  {
    return this.itemsExisting + 1;
  }
  
  /**
   * 
   * @return the change the customer receives.
   */
  public double getChange()
  {
    return this.change;
  }
  
  /**
   * 
   * @return the amount the customer pays
   */
  public double getAmountPaid()
  {
    return this.payment;
  }
  
  /**
   * 
   * @return returns the discounted price
   */
  public double calcDiscountedPrice()
  {
    this.discountFlag = true;
    return this.runningTotal * 0.8;
  }
}
