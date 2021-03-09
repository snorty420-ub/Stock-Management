package col106.assignment5;

import java.util.Comparator;
/*
Implementation of MergeSort Algorithm :
    1. you get linked list of size <=1 you just return the list as it is already sorted
    2. Find mid node using findSplit method(Don't forget to add mid element to globalList before returning it)
    3. Create two LinkedList lt (with head = head and tail = mid) and rt (with head = mid.next and tail = tail)
    4. Now recursively MergSort lt and rt Linked lists(Maintain this order)
    5. Now merge these two lists that we got from recursive calls using given crieteria for ordering
    6. Return merged Linked list
*/

public class LLMergeSort<T> {

  LinkedList<T> globalList = new LinkedList<T>();

  // CALL THIS METHOD AFTER EVERY CALL OF findSplit and DO NOT MODIFY THIS METHOD
  public void adjustGlobalPointer(T node) {
    globalList.add(node);
  }

  // Utility function to get the middle of the linked list
  public Node<T> findSplit(LinkedList<T> lst) {
    // find middle node of LL :
    Node<T> middle = lst.getHead();
    // Enter your code here

    // !!!!!*****DO NOT REMOVE THIS METHOD CALL (change the argument
    // apprpriately)*****!!!!!
    adjustGlobalPointer(middle.getData()); // Add object of ItemNode after finding mid in each call
    return middle;
  }

  public LinkedList<T> MergeSort(LinkedList<T> lst, Comparator<T> c) {
    // Recursively Apply MergeSort, by calling function findSplit(..) to find middle
    // node to split
    // Enter your code here
    int size = lst.getSize();
    if (size <= 1) {
      return lst;
    } else {
      int middle = size % 2 == 0 ? (size / 2) : (size / 2) + 1;
      Node<T> curr = lst.getHead();
      lst.setSize(middle);
      LinkedList<T> l2 = new LinkedList<>();
      l2.setSize(size - middle);
      middle--;
      while (middle-- > 0) {
        curr = curr.next;
      }
      l2.setNewHead(curr.next);
      curr.next = null;

      globalList.add(curr.data);
      // print(lst);
      // print(l2);
      // print("..........");
      lst = MergeSort(lst, c);
      l2 = MergeSort(l2, c);
      LinkedList<T> merged = merge(lst, l2, c);
      return merged;
    }
  }

  public LinkedList<T> merge(LinkedList<T> l1, LinkedList<T> l2, Comparator<T> c) {
    Node<T> a = l1.getHead();
    Node<T> b = l2.getHead();
    LinkedList<T> l = new LinkedList<>();
    while (a != null && b != null) {
      if (c.compare(a.data, b.data) == 1) { // a > b ==> 1
        l.add(b.data);
        b = b.next;
      } else {
        l.add(a.data);
        a = a.next;
      }
    }

    while (a != null) {
      l.add(a.data);
      a = a.next;
    }
    while (b != null) {
      l.add(b.data);
      b = b.next;
    }

    // print("merged " + l);
    return l;
  }

  // DO NOT CALL OR MODIFY THESE METHODS IN YOUR CALL THIS IS FOR USE IN DRIVER
  // CODE
  public LinkedList<T> getGlobalList() {
    return this.globalList;
  }

  public void clearGlobalList() {
    globalList = new LinkedList<>();
  }

  public static <T> void print(T t) {
    System.out.println(t.toString());
  }

}
