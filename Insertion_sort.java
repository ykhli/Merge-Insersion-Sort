package jfreechart;

import java.util.*;
import java.io.*;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ChartUtilities;

public class Insertion_sort{
  public static int comparison = 0;
  public static void main(String[] agrs){
//    int [] test = {45,29,6,64,12,16};
//    int [] result = new int[test.length];
//    for(int i = 0; i< test.length; i++){
//      result[i] = test[i];
//    }
//    result = insertionSort(result,result.length);
//    printArray(result);
//    System.out.println("comparison = "+ comparison);
//    
    /*******************Generate graph for different data size***************************/
    XYSeries series = new XYSeries("Insertion Sort Graph");

    
    for (int dataSize = 1000; dataSize <= 1000000; dataSize = dataSize+100000) {
      comparison = 0;
      int [] sort_data = new int[dataSize];
      sort_data = generateData(dataSize);
      int [] result = new int[sort_data.length];
      
      long startTime = System.nanoTime();
      result = insertionSort(sort_data,dataSize);
      long endTime = System.nanoTime();
      series.add(dataSize,endTime-startTime);
    }
    XYSeriesCollection dataset = new XYSeriesCollection();
    dataset.addSeries(series);
    JFreeChart chart = ChartFactory.createXYLineChart(
                "Insertion Sort Chart",
                "Data size",
                "Time used",
                dataset,
                PlotOrientation.VERTICAL,  // Plot Orientation
                true,                      // Show Legend
                true,                      // Use tooltips
                false                      // Configure chart to generate URLs?
                );
    try {
        ChartUtilities.saveChartAsJPEG(new File("InsertionSort-td2.jpg"), chart, 1000, 1000);
    } catch (IOException e) {
        System.err.println("Problem occurred creating chart.");
}
    /***********************************************************/
    
  }
  
  public static void printArray(int[] B){
    for(int i = 0;i<B.length; i++){
      System.out.println(B[i]);
    }
  }
  
  public static int []generateData(int dataSize){
     Random randomNum = new Random();
     int [] data = new int[dataSize];
     for(int i = 0; i< dataSize; i++){
       data[i] = randomNum.nextInt(1000000);
     }
    
     return data;
  }

  public static int []insertionSort(int[] data, int data_length){
    // int [] result = new int[data_length];
    // for(int n = 0;n<data_length;n++){
    //   result[n] = data[n];
    // }
    
    for(int i=0;i<data_length;i++){
      for(int j=i;j>0;j--){
        comparison++;
        if(data[j] < data[j-1]){
          int temp = data[j];
          data[j] = data[j-1];
          data[j-1] = temp;
        }else{
          break;
        }
      }   
    }
    return data;
  }

}