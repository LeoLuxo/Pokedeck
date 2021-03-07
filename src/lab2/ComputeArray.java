
     package lab2;
     public class ComputeArray {

       public int[] data;

       public ComputeArray() {
         data = new int[10];
       }


       public void set(int idx, int x) { ... }

    public int maximum(int[] data){
    int maxValue = data[0];
    for(int i=1;i < data.length;i++){
      if(data[i] > maxValue){
         maxValue = data[i];
      }
    }
    return maxValue;
  }


  public int minimum(int[] data){
    int minValue = data[0];
    for(int i=1;i<data.length;i++){
      if(data[i] < minValue){
        minValue = data[i];
      }
    }
    return minValue;
  }
}