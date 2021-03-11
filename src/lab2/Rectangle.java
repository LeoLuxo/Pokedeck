     package lab2;
     public class Rectangle {

     public static void draw(int length, int width){
      int i = 1;
      int j = 1;

    for(i = 1; i <= length; i++)
    {
      for(j = 1; j <= width; j++)
      {
        System.out.print("* ");
      }
      System.out.print("\n");
    }

    return 0;

      }     }
