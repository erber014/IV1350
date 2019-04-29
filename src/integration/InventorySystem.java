package integration;

import model.ItemDTO;
import model.Sale;
/**
 * Represents the inventorysystem of a grocery store.
 * @author Erik
 */
public class InventorySystem {
	ItemDTO inventory[] = new ItemDTO[3];
	
        /**
         * Creates an instance of the inventorysystem, with items in it.
         */
	public InventorySystem() {
		inventory[0] = new ItemDTO(10.0, 0, "Banan", 12.5, 0);
		inventory[1] = new ItemDTO(15.0, 1, "Äpple", 12.5, 0);
		inventory[2] = new ItemDTO(8.5, 2, "Apelsin", 12.5, 0);
	}
	/**
         * Finds an item in the inventorysystem with the same itemidentifier
         * as the one sent in. If no such item exists it returns null
         * @param itemIdentifier The unique number that represents an item in
         * the inventorysystem
         * @return returns an item from the inventorysystem
         */
	public ItemDTO findItem(int itemIdentifier) {
		for(int i = 0; i < inventory.length; i++) {
			if(inventory[i].getItemIdentifier() == itemIdentifier) {
				return inventory[i];
			}
		}
		return null;
	}
	
        /**
         * Saves information about the sale in the inventorysystem.
         * @param sale Contains the information about the sale to be saved.
         */
	public void saveSaleInformation(Sale sale) {
		
	}
}