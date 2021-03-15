
package lab2;
import java.util.Scanner;

     public class Soon {


       public static void main(String[] args) {
        //Exercise 1.8
        ComputeArray array = new ComputeArray();
        //Exercise 1.2
        //Read 2 integers and print them.
        Scanner input = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        int a = input.nextInt();
        System.out.print("Enter a second integer: ");
        int b = input.nextInt();
        System.out.println("You entered: " + a + " and " + b);


        //Exercise 1.3
        // Print the maximum and minimum of the integers (I added the condition for when the integers are equal)

        if (a > b){
          System.out.println("The maximum of these integers is " + a + " and the minimum of these integers is " + b);
        }
        else if (a == b){
          System.out.println("They are equal to each other, they are both the maximum and the minimum.");
        }
        else {
          System.out.println("The maximum of these integers is " + b + " and the minimum of theses integers is " + a);
        }

        // Use functions to find maximumg and minimum:
        System.out.println("The maximum of " + a + " and " + b + " is " + max(a, b));
        System.out.println("The minimum of " + a + " and " + b + " is " + min(a, b));

        //Exercise 1.5
         //Ask user how many numbers s.he wants to input:
        System.out.print("How many numbers do you want to input?");
        int numbers = input.nextInt();
        //Ask the user to input the the numbers
        System.out.println("Enter the " +numbers+ " numbers");
        //Read the first number from the user input
        int tmp = input.nextInt();
        // Initialize input
        int max = tmp;
        int min = tmp;
         // loop over the numbers
         for (int i = 1; i < numbers; i++){
          //read input
          int integer = input.nextInt();
          // Find minimum and maximum value
          if (integer < min) {
            min = integer;
          }
          if(integer > max) {
            max = integer;
          }
        }
        // Print min and max
         System.out.println("The minimum is: " + min);
         System.out.println("The maximum is: " + max);


         //Exercise 1.6
        //Ask user how big the array is:

         System.out.print("How many numbers would you like to input?");
         int min2, max2;
         int arraysize = input.nextInt();
         int[] nums = new int[arraysize];
         //get array
         System.out.print("Enter the array of length " + arraysize);
         for (int i = 0; i < arraysize; i++){

          nums[i] = input.nextInt();

         }
          //assume first elements as smallest and largest values respectively
          min2 = nums[0];
          max2 = nums[0];
          for (int i=0; i < arraysize; i++){
            if(min2 > nums[i]){
              min2 = nums[i];
            }
            if (max2 < nums[i]){
              max2 = nums[i];
            }

          }System.out.println("The min value is "+ min2 +" and your max value is " + max2);


          //Exercise 10

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
            Circle.drawCircle(radius);
          }
          else if (shape.equalsIgnoreCase(triangle)){
            Triangle.draw()
          }
          else{
            System.out.println("Sorry, not a valid char, try again.") //add loop to vaildate input
          }
        }




        //Exercise 1.4
      // The functions to find maximum and minimum
      public static int max(int a, int b){
        return a ^ ((a ^ b) & -((a < b) ? 1 : 0));
      }


      public static int min(int a, int b){
        return b ^ ((a ^ b) & -((a < b) ? 1 : 0));
      }



}







