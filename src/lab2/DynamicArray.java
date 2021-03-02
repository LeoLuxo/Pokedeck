package lab2;

public class DynamicArray {
  private static int INITIAL_CAPACITY = 5;
  private int[] data;
  private int size;
  public DynamicArray() {
    data = new int[INITIAL_CAPACITY];
    size = 0;
  }

  // Returns `true` if the array is empty.
  public boolean isEmpty() {
    return true;
  }

  // Returns the size of the array.
  public int size() {
    return 0;
  }

  // Remove all elements from data.
  public void clear() {
  }

  // Create a `String` with the elements of the array separated by comma, without a new line character at the end.
  // For instance: 4, 5, 6
  public String toString() {
    return "";
  }

  // Returns `true` if the array `data` is full: no more element can be added to `data`.
  // Returns `false` otherwise.
  private boolean isFull() {
    return true;
  }

  // If the array `data` is full:
  //  1. Create a new array `data2` doubling the size of data.
  //  2. Copy the elements of `data` into `data2`.
  //  3. Assign `data2` to `data`.
  private void realloc() {
  }

  // The element `x` is added to `data`, and `size` is incremented by one.
  // `data` is automatically resized if it is too small.
  public void add(int x) {
  }

  private void checkIndex(int idx) {
    if(idx >= size) {
      throw new ArrayIndexOutOfBoundsException();
    }
  }

  // Set the ith element of `data` to `x`.
  public void set(int idx, int x) {
  }

  // Return the element at the index `idx` of `data`.
  public int get(int idx) {
    return 0;
  }

  // Remove the element at index `idx`.
  // Shift all the elements after `idx` of one position to the left.
  public void remove(int idx) {
  }
}
