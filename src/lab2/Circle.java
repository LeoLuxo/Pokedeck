     package lab2;
     public class Circle {

     public static void drawCircle(int radius){

    float dist;

    // horizontal
    for (int i = 0; i <= 2 * radius; i++) {

    // for vertical
    for (int j = 0; j <= 2 * radius; j++) {
      dist = sqrt((i - radius) * (i - radius) +
                  (j - radius) * (j - radius));

      // distance to center between: radius - 0.5/+0.5)

      if (dist > radius - 0.5 && dist < radius + 0.5)
        cout << "*";
      else
        cout << " ";
    }

    cout << "\n";
  }
}
    }
