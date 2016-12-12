package com.talem;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RunThread extends Thread{
    @Override
    public void run(){
    	Lock lock = new ReentrantLock();
    	lock.lock();
    	IMSMain objMain = new IMSMain();
    	
    	Item item1 = new Item("Book1", 3);
        Item item2 = new Item("Book2", 5);
        Item item3 = new Item("Book3", 0);
        
        objMain.addItem(item1);
        objMain.addItem(item2);
        objMain.addItem(item3);
        objMain.addItem(item1);
        objMain.addItem(item2);
    	
    	int choice = 0;
    	String productName;
    	int pickUpQuantity, restockQuantity;
    	boolean isAvailable = false;
    	boolean done = false;
    	while(!done){
    		System.out.println("------------------------");
    		System.out.println("Enter 1: Picking an Item");
        	System.out.println("Enter 2: Restock an Item");
        	System.out.println("Enter 3: View an Item");
        	System.out.println("Enter Any Other number to exit..");
        	Scanner sc = new Scanner(System.in);
        	choice = sc.nextInt();
        	try{
        		switch(choice){
            	case 1:
            		System.out.println("Enter the name of Item you want to Pick-up: ");
            		productName = sc.next();
            		System.out.println("Enter the quantity of "+ productName+ " you want to pick-up..");
            		pickUpQuantity = sc.nextInt();
            		isAvailable = objMain.checkQuantity(productName, pickUpQuantity, true);
            		if(isAvailable){
            			PickingResult pickedItem = objMain.pickProduct(productName, pickUpQuantity);
        				System.out.println("Success ! Picked "+pickUpQuantity+" of "+pickedItem.getItem().getName()
        						+". Quantity left is: "+pickedItem.getItem().getQuantity());
        				System.out.println();
            		}
            		break;
            	case 2:
            		System.out.println("Enter the name of Item you want to Restock: ");
            		productName = sc.next();
            		System.out.println("Enter the quantity to be restocked: ");
            		restockQuantity = sc.nextInt();
            		isAvailable = objMain.checkQuantity(productName, restockQuantity, false);
            		if(isAvailable){
            			RestockingResult restockedItem = objMain.restockProduct(productName,restockQuantity);
            			System.out.println("Success ! Re-stocked "+restockQuantity+" of "+restockedItem.getItem().getName()
        						+". Available quantity is: "+restockedItem.getItem().getQuantity());
            		}
            		else {
            			System.out.println("Adding item "+productName+" to the inventory...");
            			Item item = new Item(productName, restockQuantity);
            			objMain.addItem(item);
            			System.out.println("Success ! Added item "+productName+" to the inventory...");
            		}
            		break;
            	case 3:
            		System.out.println("Enter the name of item you want to view: ");
            		productName = sc.next();
            		objMain.view_items(productName);
            		break;
            	default:
            		sc.close();
            		lock.unlock();
            		System.out.println("Closed Inventory Management System");
            		done = true;
            		break;
            	}
        	}
        	catch(InputMismatchException ex){
        		System.out.println("Please enter valid Integer value as suggested...");
        	}
    	}
    }

}

