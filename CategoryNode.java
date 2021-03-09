package col106.assignment5;

public class CategoryNode implements CategoryInterface {

	int categoryId;
	String categoryName;
	LinkedList<ItemNode> itemList;

	// DO NOT REMOVE THIS CONSTRUCTOR
	public CategoryNode(int categoryId, String categoryName) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.itemList = new LinkedList<ItemNode>();
	}

	public int getCategoryId() {
		return this.categoryId;
	}

	public LinkedList<ItemNode> getLinkedListOfCategory() {
		return itemList;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public ItemNode getItem(int itemId) {
		Node<ItemNode> curr = itemList.getHead();
		try {
			while (true) {
				if (itemId == curr.data.itemId)
					return curr.data;
				curr = curr.next;
			}
		} catch (Exception e) {
			System.out.println("Invalid item Id");
			return null;
		}

	}

}
