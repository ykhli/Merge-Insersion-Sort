package jfreechart;


import java.util.*;
import java.io.*;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ChartUtilities;




public class Merge_sort{
	public static int comparison = 0;
	public static void main(String[] args) {
		System.out.println("aaaaaaa");
    
    try {
         
          
          // read and reformat data from txt
          BufferedReader reader = new BufferedReader(new FileReader("data_to_sort.txt"));
          String line = null;
          StringBuilder builder = new StringBuilder();
          while((line =reader.readLine())!= null){
            builder.append(line);
          }
          reader.close();
          String bString = builder.toString();
          String[] dataList = bString.split(" ");
          List<Integer> data = new ArrayList<Integer>();
          
          for(int i = 0; i<dataList.length; i++){
            data.add(Integer.valueOf(dataList[i]));
          }
          int[] finalData = new int[data.size()];
          for(int i=0, len = data.size(); i < len; i++)
             finalData[i] = data.get(i);
          // end of getting data from txt
          
          
          // 
          // int [] sort_data = new int[finalData.length];
          // for(int i = 0;i<finalData.length;i++){
          //   sort_data[i] = finalData[i];
          // }
          
          
          // calculate time/#of comparisons for different data size
          
          
          
          // int dataSize = 100000;
//           int [] sort_data = new int[dataSize];
//           sort_data = generateData(dataSize);
//           
//           int [] result = new int[sort_data.length];
//           
//           long startTime = System.nanoTime();
//           result = mergesort(sort_data);
//           long endTime = System.nanoTime();
// 
//           
//           System.out.println("Data size is "+ sort_data.length);
//           System.out.println("No.of Comparisons is "+counter);
//           System.out.println("Time elapsed is "+((endTime - startTime) / 1000000)+ " ms");
//                    
    			} catch(IOException ioe ) {}
       
    XYSeries series = new XYSeries("Mergesort Graph");

    
    for (int dataSize = 1000; dataSize <= 1000000; dataSize = dataSize+10000) {
      comparison = 0; //set no. of comparisons to 0 every time we have a new dataset
      int [] sort_data = new int[dataSize];
      sort_data = generateData(dataSize);
      int [] result = new int[sort_data.length];
    
      long startTime = System.nanoTime();
      result = mergesort(sort_data);
      long endTime = System.nanoTime();
      series.add(comparison,endTime-startTime);
    }
    XYSeriesCollection dataset = new XYSeriesCollection();
    dataset.addSeries(series);
    JFreeChart chart = ChartFactory.createXYLineChart(
                "Mergesort Chart",
                "No.of Comparisons",
                "Time used",
                dataset,
                PlotOrientation.VERTICAL,  // Plot Orientation
                true,                      // Show Legend
                true,                      // Use tooltips
                false                      // Configure chart to generate URLs?
                );
    try {
                ChartUtilities.saveChartAsJPEG(new File("MergeSort_comparison_time2.jpg"), chart, 1000, 1000);
            } catch (IOException e) {
                System.err.println("Problem occurred creating chart.");
    }

       
       // int [] list = {3,2,4,5,9,1,67,22,30,12,38,6};
       // int [] result = new int[list.length];
       // result = mergesort(list);

       // printArray(result);
    


	}
  
  public static int []generateData(int dataSize){
     Random randomNum = new Random();
     int [] data = new int[dataSize];
     for(int i = 0; i< dataSize; i++){
       data[i] = randomNum.nextInt(1000000);
     }
    
     return data;
  
  }
  
  public static void printArray(int[] B){
    for(int i = 0;i<B.length; i++){
      System.out.println(B[i]);
    }
  }


	public static int []mergesort(int[] B){
    int mid = B.length/2;
    int [] left = new int[mid];
    int [] right;
    int [] result = new int[B.length];
    
    if(B.length<=1){
      return B;
    }
    
		if(B.length % 2 ==0){
		  right = new int[mid];
		}else{
		  right = new int[mid+1];
		}
    
    for(int i = 0;i<mid; i++){ 
      // mid # of slots
      left[i] = B[i]; 
    }
    
    int x = 0;
    for(int j = mid; j< B.length; j++){
      // since the length is end
      right[x] = B[j];
      x++;
    }


    left = mergesort(left); 
    right = mergesort(right); 
    result = merge(left, right);
    
    
    // System.out.println("result mergesort");
    // printArray(result);
    // System.out.println("==========");

    return result;
	}
  
	
  
  
  public static int []merge(int[] left, int[] right){
    
    int []result = new int[left.length + right.length];
    int indexL = 0;
    int indexR = 0;
    int indexResult = 0;
    
    while(indexL < left.length && indexR < right.length){
      comparison++;
      if (left[indexL] <= right[indexR]){
        
        result[indexResult] = left[indexL];
        indexL++;
        indexResult++;
      }else{
        
        result[indexResult] = right[indexR];
        indexR++;
        indexResult++;
      }
    }
    while (indexL < left.length){

      result[indexResult] = left[indexL];
      indexResult++;
      indexL++;
    }
    while (indexR < right.length){
      result[indexResult] = right[indexR];
      indexResult++;
      indexR++;
    }
    // printArray(result);
    // System.out.println("==========");
    
    return result;
    
  }
  
  
  
  
  
  
  
}