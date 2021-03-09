package col106.assignment5;

import java.util.Comparator;

public class StockMgmt implements StockMgmtInterface {
	// !!!!!!!*********DON'T CREATE YOUR OWN OBJECTS OF LLMergeSort*********!!!!!!!
	// use these only this object to sort
	LLMergeSort mergeSortobj;

	LinkedList<CategoryNode> categories;

	// DO NOT MNODIFY THIS CONSTRUCTOR
	public StockMgmt() {

		mergeSortobj = new LLMergeSort();
		categories = new LinkedList<CategoryNode>();

		categories.add(new CategoryNode(1, "mobile"));
		categories.add(new CategoryNode(2, "utensils"));
		categories.add(new CategoryNode(3, "sanitary"));
		categories.add(new CategoryNode(4, "medicalEquipment"));
		categories.add(new CategoryNode(5, "clothes"));

	}

	public void addItem(int categoryId, int itemId, String itemName, int stock) {
		// Your code goes here
		CategoryNode cat = getCategory(categoryId);
		cat.itemList.add(new ItemNode(itemId, itemName, stock));
	}

	public void addItemTransaction(int categoryId, int itemId, String itemName, int numItemPurchased, int day,
			int month, int year) {
		// Your code goes here
		CategoryNode cat = getCategory(categoryId);
		cat.getItem(itemId).addTransaction(new PurchaseNode(numItemPurchased, day, month, year));

	}

	// Query 1
	public LinkedList<ItemNode> sortByLastDate() {
		// Your code goes here
		LinkedList<ItemNode> l = new LinkedList<>();
		Node<CategoryNode> curr = categories.getHead();

		LinkedList<ItemNode> items = null;
		for (int i = 0; i < 5; i++) {
			items = curr.data.itemList;
			Node<ItemNode> n = items.getHead();
			while (n != null) {
				l.add(n.data);
				if (n.data.purchaseTransactions.getSize() == 0) {
					n.data.addTransaction(new PurchaseNode(0, 1, 8, 1970));
				}
				n = n.next;
			}
			curr = curr.next;
		}

		Comparator<ItemNode> c = new Comparator<ItemNode>() {
			@Override
			public int compare(ItemNode a, ItemNode b) {
				int comp = a.getLastDate().dateobj.compare(b.getLastDate().dateobj);
				if (comp == 0) { // decreasing lexographic order
					int nameCompare = b.itemName.compareTo(a.itemName);
					return nameCompare > 0 ? 1 : nameCompare < 0 ? -1 : 0;
				}
				return comp;
			}
		};

		LinkedList<ItemNode> sorted = mergeSortobj.MergeSort(l, c);
		// Testing OOP concept
		// curr = categories.getHead();
		// curr.data.itemList.getHead().next.data.itemName = "Mudit Garg";
		// print(curr.data.itemList.getHead().next.data);

		return sorted;
	}

	// Query 2
	public LinkedList<ItemNode> sortByPurchasePeriod(int day1, int month1, int year1, int day2, int month2, int year2) {
		// Your code goes here

		LinkedList<ItemNode> l = new LinkedList<>();
		Node<CategoryNode> curr = categories.getHead();

		LinkedList<ItemNode> items = null;
		for (int i = 0; i < 5; i++) {
			items = curr.data.itemList;
			Node<ItemNode> n = items.getHead();
			while (n != null) {
				l.add(n.data);
				n.data.getValueInThePeriod(day1, month1, year1, day2, month2, year2);
				n = n.next;
			}
			curr = curr.next;
		}

		Comparator<ItemNode> c = new Comparator<ItemNode>() {
			@Override
			public int compare(ItemNode a, ItemNode b) {
				if (a.valueInPeriod == b.valueInPeriod) {
					int nameCompare = b.itemName.compareTo(a.itemName);
					return nameCompare > 0 ? 1 : nameCompare < 0 ? -1 : 0;
				}
				return Float.compare(a.valueInPeriod, b.valueInPeriod);
			}

		};

		LinkedList<ItemNode> sorted = mergeSortobj.MergeSort(l, c);

		return sorted;
	}

	// Query 3
	public LinkedList<ItemNode> sortByStockForCategory(int category) {
		// Your code goes here
		CategoryNode cat = getCategory(category);
		Node<ItemNode> n = cat.itemList.getHead();
		LinkedList<ItemNode> l = new LinkedList<>();
		while (n != null) {
			l.add(n.data);
			n = n.next;
		}
		Comparator<ItemNode> c = new Comparator<ItemNode>() {
			@Override
			public int compare(ItemNode a, ItemNode b) {
				int comp = Integer.compare(b.stock, a.stock);
				if (comp == 0) {
					int nameCompare = b.itemName.compareTo(a.itemName);
					return nameCompare > 0 ? 1 : nameCompare < 0 ? -1 : 0;
				}
				return comp;
			}
		};

		l = mergeSortobj.MergeSort(l, c);

		return l;
	}

	// Query 4
	public LinkedList<ItemNode> filterByCategorySortByDate(int category) {
		// Your code goes here
		CategoryNode cat = getCategory(category);
		Node<ItemNode> n = cat.itemList.getHead();
		LinkedList<ItemNode> l = new LinkedList<>();
		while (n != null) {
			l.add(n.data);
			if (n.data.purchaseTransactions.getSize() == 0)
				n.data.addTransaction(new PurchaseNode(0, 1, 8, 1970));
			n = n.next;
		}
		Comparator<ItemNode> c = new Comparator<ItemNode>() {
			@Override
			public int compare(ItemNode a, ItemNode b) {
				int comp = -a.getLastDate().dateobj.compare(b.getLastDate().dateobj);
				if (comp == 0) {
					int nameCompare = b.itemName.compareTo(a.itemName);
					return nameCompare > 0 ? 1 : nameCompare < 0 ? -1 : 0;
				}
				return comp;
			}
		};

		l = mergeSortobj.MergeSort(l, c);
		return l;
	}

	public CategoryNode getCategory(int categoryId) {
		int t = categoryId - 1;
		Node<CategoryNode> curr = categories.getHead();
		while (t-- > 0) {
			curr = curr.next;
		}
		return curr.data;
	}

	public <T> void print(T a) {
		System.out.println(a.toString());
	}

	// !!!!!*****DO NOT MODIFY THIS METHOD*****!!!!!//
	public LinkedList<ItemNode> checkMergeSort() {
		LinkedList<ItemNode> retLst = mergeSortobj.getGlobalList();
		mergeSortobj.clearGlobalList();
		return retLst;
	}
}
