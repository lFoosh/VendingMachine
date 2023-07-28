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

import java.util.Map;



public class VendingMachineCLI {
	// Create options for main menu
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

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
			String choice = (String)menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

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
					String purchaseChoice = (String)menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

					if (purchaseChoice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
						// get money from user and feed into machine
						System.out.println("Enter amount to feed into machine: ");
						double amount = Double.parseDouble(menu.getUserInput());
						transaction.feedMoney(amount);

					} else if (purchaseChoice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
						// get product code from user and attempt to purchase product
						System.out.println("Enter product code: ");
						String productCode = menu.getUserInput();
						Product productToPurchase = inventory.getProducts().get(productCode);

						if (productToPurchase != null) {

							System.out.println("You selected " + productToPurchase.getName() + ". The cost is $" + String.format("%.2f", productToPurchase.getPrice()));
							transaction.purchaseItem(productToPurchase);

							//Prints message depending on class of purchase
							String message = productToPurchase.getMessage();
							System.out.println(message);

						} else {
							System.out.println("Invalid product code. Please try again.");
						}
					} else if (purchaseChoice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {

						// finish the transaction, return change and exit purchase menu
						transaction.returnChange();
						System.out.println("Thank you for your purchase, Have a nice Day!");
						break;
					}
				}
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				// Exit the program
				System.out.println("Exiting...");
				break;
			} else {
				System.out.println("Invalid option, please choose a valid option!");
			}
		}
	}
}