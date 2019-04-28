package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Receipt
{
  private double amountPaid;
  private double change;
  private ItemDTO[] itemList;
  private double total;
  private LocalDateTime dateTime;
  private DateTimeFormatter time;
  private String formattedDate;
  private String storeName;
  private String adress;
  private int items;
  
  public Receipt(Sale sale, int items)
  {
    this.storeName = "Store";
    this.adress = "Streetadress 1";
    this.amountPaid = sale.getAmountPaid();
    this.change = sale.getChange();
    this.itemList = sale.getItemList();
    this.total = sale.getRunningTotal();
    this.dateTime = LocalDateTime.now();
    this.time = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    this.formattedDate = this.dateTime.format(this.time);
    this.items = items;
  }
  
  public double getAmountPaid()
  {
    return this.amountPaid;
  }
  
  public double getChange()
  {
    return this.change;
  }
  
  public ItemDTO[] getItemList()
  {
    return this.itemList;
  }
  
  public int getItems()
  {
    return this.items;
  }
  
  public double getTotal()
  {
    return this.total;
  }
  
  public String getTime()
  {
    return this.formattedDate;
  }
  
  public String getName()
  {
    return this.storeName;
  }
  
  public String getAdress()
  {
    return this.adress;
  }
}
