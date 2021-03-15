
     package lab2;
     public class ComputeArray {

       public int[] data;

       public ComputeArray() {
        data = new int[10];
         int i;
         int idx = 10;
         int arr[] = {1,2,3,4}
         int x = 6

         arr = addX(idx, arr, x);


       }


       public void set(int idx, int x, int arr[]) {
        int i;
        int data[] = new int[idx + 1];
        for (i = 0; i <n; i ++)
          data[i] = arr[i],

        data[idx] = x;
        return data;
       }


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