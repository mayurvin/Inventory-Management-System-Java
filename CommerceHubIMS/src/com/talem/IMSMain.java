package com.talem;

import java.util.concurrent.ConcurrentHashMap;

public class IMSMain implements InventoryManagementSystem {
	
	private ConcurrentHashMap<String, Item> items;
    
	public IMSMain(){
        this.items = new ConcurrentHashMap<String, Item>();
    }
    
    public static void main(String args[]) {
    	
        RunThread th = new RunThread();
        Thread thread = new Thread(th);
        thread.start();
    }
    
    public void addItem(Item item){
        items.put(item.getName(), item);
    }
    
    public Item getItem(String itemName){
        return this.items.get(itemName);
    }
    
    public ConcurrentHashMap<String, Item> getAllItems(){
        return this.items;
    }

   
    public void view_items(String item_name){
        Item item = this.items.get(item_name);
        if(item!=null){
        	System.out.println(item.getName() + " has " + item.getQuantity() + " items left...");
        }
        else {
        	System.out.println("Specified item does not exist in the inventory...");
        }
        
    }

    public PickingResult pickProduct(String productId, int amountToPick){
        Item item = this.items.get(productId);
        item.removeAmount(amountToPick);

        PickingResult pickingResult = new PickingResult(item);
        return pickingResult;
    }
    public RestockingResult restockProduct(String productId, int amountToRestock){
        Item item = this.items.get(productId);
        item.addAmount(amountToRestock);

        RestockingResult restockingResult = new RestockingResult(item);
        return restockingResult;
    }

	boolean checkQuantity(String productName, int pickUpQuantity, boolean pickUp) {
		// TODO Auto-generated method stub
		if(pickUpQuantity <= 0){
			System.out.println("Nice try ! Please enter a quantity greater than 0...");
			return false;
		}
		if (this.getAllItems().containsKey(productName)) {
			if (!pickUp) {
				return true;
			}

			if (this.getItem(productName).getQuantity() >= pickUpQuantity ) {
				return true;
			} else {
				System.out.println("Sorry! Item " + productName + " has only " + this.getItem(productName).getQuantity()
						+ " items left..");
				return false;
			}
		} else {
			System.out.println("Sorry! We do not have Item: " + productName);
			return false;
		}
	}
}
