/*package com.techelevator;

// Menu is provided to you as a suggested class to handle user input
// Build out a menu class to start

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE };

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public static void main(String[] args) {
		// You will need to create a Menu class to handle display.
		//Menu menu = new Menu();
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}

	public void run() {
		while (true) {
			// Use a method from your Menu class to initialize this value
			String choice = "initialize this here";

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
			}
			else{
				break;
			}
		}
	}
}*/
package com.techelevator;

import com.techelevator.controller.Inventory;
import com.techelevator.controller.Transaction;
import com.techelevator.model.Product;
import com.techelevator.view.Menu;

import java.util.Map;



public class VendingMachineCLI {
	// Create options for main menu
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_OPTION_SALES_REPORT = "Sales Report";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_SALES_REPORT };

	// Create options for purchase menu
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION };

	private Menu menu;
	private Inventory inventory;
	private Transaction transaction;

	public VendingMachineCLI(Menu menu, String inventoryFilePath) {
		this.menu = menu;
		// Load inventory from given file path
		this.inventory = new Inventory(inventoryFilePath);

		// Create a new transaction object
		this.transaction = new Transaction();
	}


	public static void main(String[] args) {
		// Create a new Menu object
		Menu menu = new Menu(System.in, System.out);

		// Create a new VendingMachineCLI object, start the CLI with "main.csv" as the inventory file
		VendingMachineCLI cli = new VendingMachineCLI(menu, "main.csv");
		cli.run();
	}

	public void run() {
		// Main loop for vending machine operation
		while (true) {
			// Display main menu and get user's choice
			String choice = menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				Map<String, Product> products = inventory.getProducts();

				for (String slot : products.keySet()) {
					Product product = products.get(slot);
					// Print each product with formatted price

					System.out.println(slot + ", " + product.toString());
				}
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// enter the purchase menu
				while (true) {

					// Display current money and purchase menu, get user's purchase choice
					System.out.println("Current Money Provided: $" + String.format("%.2f", transaction.getCurrentMoney()));

					String purchaseChoice = menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

					if (purchaseChoice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
						// get money from user and feed into machine
						System.out.println("Enter amount to feed into machine: ");
						String amount = (menu.getUserInput());
						double wholeDollar = Double.parseDouble(amount);

						if (wholeDollar % 1 == 0) {

							transaction.feedMoney(amount);

						}else {
							System.out.println("\nERROR: Please insert a whole dollar amount");
							System.out.println("Example: 1, 10, 5.00\n");
						}

					} else if (purchaseChoice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {

						// get product code from user and attempt to purchase product
						System.out.println("Enter product code: ");
						String productCode = menu.getUserInput().toUpperCase();
						Product productToPurchase = inventory.getProducts().get(productCode);

						if (productToPurchase != null && productToPurchase.getQuantity() > 0) {

							//prints message associated with the type of product
							String message = productToPurchase.getMessage();
							System.out.println(message);

							System.out.println("You selected " + productToPurchase.getName() + ". The cost is $" + String.format("%.2f", productToPurchase.getPrice()));
							//preforms the purchasing process
							transaction.purchaseItem(productToPurchase);


						//if the product exists but has a quantity of 0 it means it is out of stock
						} else if (productToPurchase != null) {

							System.out.println("This item is out of stock!");

						} else {
							System.out.println("Invalid product code. Please try again.");
						}
					} else if (purchaseChoice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {

						// finish the transaction, return change and exit purchase menu
						System.out.println(transaction.returnChange());
						System.out.println("Thank you for your purchase, Have a nice Day!");
						break;
					}
				}
			} else if (choice.equals(MAIN_MENU_OPTION_SALES_REPORT)) {
				transaction.salesReport(inventory.getProducts());



			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				// Exit the program
				System.out.println("Exiting...");
				System.out.println("Good bye! Have a nice day!");
				break;
			} else {
				System.out.println("Invalid option, please choose a valid option!");
			}
		}
	}
}