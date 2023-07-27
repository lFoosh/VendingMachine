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
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "(1) Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "(2) Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "(3) Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};

	private Menu menu;
	private Inventory inventory;
	private Transaction transaction;

	public VendingMachineCLI(Menu menu, String inventoryFilePath) {
		this.menu = menu;
		this.inventory = new Inventory(inventoryFilePath);
		this.transaction = new Transaction();
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu, "main.csv");  // replace with actual path
		cli.run();
	}

	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				Map<String, Product> products = inventory.getProducts();
				for (String slot : products.keySet()) {
					Product product = products.get(slot);
					System.out.println(slot + ", " + product.getName() + ", " + product.getPrice() + ", " + product.getQuantity() + ", " + product.getClass().getSimpleName());
				}
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// enter the purchase menu
				while (true) {
					System.out.println("Current Money Provided: $" + String.format("%.2f", transaction.getCurrentMoney()));
					String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
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
							System.out.println("You selected " + productToPurchase.getName() + ". The cost is $" + productToPurchase.getPrice());
							transaction.purchaseItem(productToPurchase);
						} else {
							System.out.println("Invalid product code. Please try again.");
						}
					} else if (purchaseChoice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
						// finish the transaction
						transaction.returnChange();
						System.out.println("Thank you, Have a nice Day!");
						return;
					}
				}
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.out.println("Exiting...");
				break;
			} else {
				System.out.println("Invalid option, please choose a valid option!");
			}
		}
	}
}