package col106.assignment5;

public class ItemNode implements ItemInterface {

	int itemId;
	String itemName;
	int stock;

	// list for storing purchases
	LinkedList<PurchaseNode> purchaseTransactions = new LinkedList<>();
	float valueInPeriod = 0;
	PurchaseNode lastDate = null;

	public ItemNode(int itemId, String itemName, int stock) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.stock = stock;
	}

	public int getItemId() {
		// Enter your code here
		return itemId;
	}

	public String getItemName() {
		// Enter your code here
		return itemName;
	}

	public int getStockLeft() {
		// Enter your code here
		return stock;
	}

	public void addTransaction(PurchaseNode purchaseT) {
		// Enter your code here
		stock = stock - purchaseT.numItemPurchased;
		if (lastDate == null)
			lastDate = purchaseT;
		else {
			if (lastDate.dateobj.compare(purchaseT.dateobj) == -1) {
				lastDate = purchaseT;
			}
		}
		purchaseTransactions.add(purchaseT);

	}

	public Node<PurchaseNode> getPurchaseHead() {
		// Enter your code here
		// Returns the last date of purchase
		return purchaseTransactions.getHead();
	}

	public PurchaseNode getLastDate() {
		return lastDate;
	}

	public void getValueInThePeriod(int day1, int month1, int year1, int day2, int month2, int year2) {
		float noOfPurchased = 0;
		float firstYear = Float.MAX_VALUE;
		float lastYear = -1;
		Node<PurchaseNode> curr = purchaseTransactions.getHead();
		DateNode start = new DateNode(day1, month1, year1);
		DateNode end = new DateNode(day2, month2, year2);
		PurchaseNode temp = null;
		while (curr != null) {
			temp = curr.data;
			if ((temp.dateobj.compare(start) == 1 && temp.dateobj.compare(end) == -1)
					|| (temp.dateobj.compare(start) == 0) || (temp.dateobj.compare(end) == 0)) {
				noOfPurchased += temp.numItemPurchased;
				if (firstYear > temp.dateobj.year) {
					firstYear = temp.dateobj.year;
				} 
				if (lastYear < temp.dateobj.year) {
					lastYear = temp.dateobj.year;
				}
			}
			curr = curr.next;
		}
		valueInPeriod = noOfPurchased / ((lastYear - firstYear) + 1);
	}

	public static <T> void print(T t) {
		if (t == null)
			System.out.println(t);
		else
			System.out.println(t.toString());
	}

	public String toString() {
		return this.itemId + " " + this.itemName;
	}

}
