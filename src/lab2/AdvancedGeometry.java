package lab2;
import lab2.*;

     public class AdvancedGeometry {


       public static void main(String[] args) {

          Scanner input = new Scanner(System.in);


          System.out.println("Which shape would you like to draw? Please type r for rectangle, c for circle and t for triangle.");
          String shape = input.nextLine();
          String rectangle ="r";
          String circle = "c";
          String triangle ="t";
          if (shape.equalsIgnoreCase(rectangle)){
              System.out.println("Please input the width of the rectangle");
              int width = input.nextInt();
              System.out.println("Please input the height of the rectangle");
              int height = input.nextInt();
              Rectangle.draw(width, height);

          }
          else if (shape.equalsIgnoreCase(circle)){
            System.out.println("Please input the radius of the circle.");
            int radius = input.nextInt();
            Circle.draw(radius);
          }
          else if (shape.equalsIgnoreCase(triangle)){
            System.out.println("Please input the size of the triangle.");
            int size = input.nextInt();
            Triangle.draw(size);
          }
          else{
            System.out.println("Sorry, not a valid char, try again.");
            //missing loop to validate.
          }
        }


      }