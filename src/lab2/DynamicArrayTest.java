package lab2;

public class DynamicArrayTest {
  public static void main(String[] args) {
    DynamicArray array = new DynamicArray();
    testAdd(array);
    testRemove(array);
    testGet(array);
    testRealloc(array);
  }

  public static void testAdd(DynamicArray array) {
    System.out.println("Test add method.");
    System.out.println(array.size());
    System.out.println(array.isEmpty());
    array.clear();
    System.out.println(array.size());
    System.out.println(array.isEmpty());
    array.add(4);
    array.add(5);
    array.add(6);
    System.out.println(array);
  }

  public static void testRemove(DynamicArray array) {
    System.out.println("Test remove method.");
    try {
      for(int i = 0; i < 3; ++i) {
        array.remove(1);
        System.out.println(array);
        System.out.println(array.size());
        System.out.println(array.isEmpty());
      }
    }
    catch(ArrayIndexOutOfBoundsException e) {}
    array.remove(0);
    System.out.println(array);
    System.out.println(array.size());
    System.out.println(array.isEmpty());
  }

  public static void testGet(DynamicArray array) {
    System.out.println("Test get method.");
    try {
      array.get(4);
    }
    catch(ArrayIndexOutOfBoundsException e) {
      System.out.println("OK");
    }
  }

  public static void testRealloc(DynamicArray array) {
    System.out.println("Test realloc method.");
    for(int i = 0; i < 100; ++i) {
      array.add(i);
    }
    System.out.println(array);
    while(!array.isEmpty()) {
      array.remove(0);
    }
    System.out.println(array);
    System.out.println(array.size());
    System.out.println(array.isEmpty());
  }
}