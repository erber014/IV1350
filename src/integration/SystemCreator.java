package integration;

/**
 * Represents the starting up of the accountingsystem, and inventorysystem, of a
 * grocery store.
 * @author Erik
 */
public class SystemCreator {
	private AccountingSystem accountingSystem = new AccountingSystem();
	private InventorySystem inventorySystem = new InventorySystem();
	
        /**
         * Gives access to the accountingsystem
         * @return Returns a reference to the accountingsystem
         */
	public AccountingSystem getAccountingSystem() {
		return this.accountingSystem;
	}
	
        /**
         * Gives access to the accountingsystem
         * @return Returns a reference to the accountingsystem
         */
	public InventorySystem getInventorySystem() {
		return this.inventorySystem;
	}
}