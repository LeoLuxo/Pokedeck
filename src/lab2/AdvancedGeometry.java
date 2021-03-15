package lab2;
import java.util.Scanner;

     public class AdvancedGeometry {


       public static void main(String[] args) {
//Exercise 10
          Scanner input = new Scanner(System.in);


          System.out.println("Which shape would you like to draw? Please type r for rectangle, c for circle and t for triangle.");
          String shape = input.scanner.nextLine();
          String rectangle ="r";
          String circle = "c";
          String triangle ="t";
          if (shape.equalsIgnoreCase(rectangle)){
              System.out.println("Please input the width of the rectangle");
              Int width = input.scanner.nextInt();
              System.out.println("Please input the height of the rectangle");
              Int height = input.scanner.nextInt();
              Rectangle.draw(int width, int height);

          }
          else if (shape.equalsIgnoreCase(circle)){
            System.out.println("Please input the radius of the circle.");
            Int radius = input.scanner.nextInt();
            Circle.draw(radius);
          }
          else if (shape.equalsIgnoreCase(triangle)){
            System.out.println("Please input the size of the triangle.");
            Int size = input.scanner.nextInt();
            Triangle.draw(size);
          }
          else{
            System.out.println("Sorry, not a valid char, try again.")
            //missing loop to validate.
          }
        }


      }