package First;
import java.util.*;
import java.io.IOException;
import java.lang.Math;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class home{
    public static double getRandomNumber(double min, double max){
        return  ((Math.random() * (max - min)) + min);
    }
    private static float[] generateRandomNumbers(double minValue, double maxValue, int count) {
        float[] randomnumbers=new float[count];
        for(int i=0;i<count;i++){
            randomnumbers[i]=(float)(Math.random() * (maxValue - minValue + 1) + minValue);
        }
        return randomnumbers;
    }
    private static float generateSingleValue(double minValue, double maxValue, int count) {
        float randomnumbers;
        randomnumbers=(float)((Math.random() * (maxValue - minValue + 1) + minValue));
        return randomnumbers;
    }
    private static float[] generateWeight(float[] length){
        float[] weight=new float[10];
        for(int i=0;i<weight.length;i++){
            weight[i]=(float)(length[i]*0.025*9.8);
        }
        return weight;
    }

    private static String getInputString(float[] strig){
        String input="[";
        for(int i=0;i<strig.length;i++){
            if(i==strig.length-1){
                input+=Float.toString(strig[i]);    
            }else
            input+=Float.toString(strig[i])+"|";
        }
        input+="]";
        return input;
    }

    static String runALL(Float max_w,Float max_l,float[] length,float[] weight){
        String result="";
        Algorithms alg=new Algorithms();
        result+=String.valueOf(alg.next_fit(max_w, max_l, length, weight))+",";
        result+=String.valueOf(alg.first_fit(max_w, max_l, length, weight))+",";
        result+=String.valueOf(alg.bestFit_Weight(max_w, max_l, length, weight))+",";
        result+=String.valueOf(alg.bestFit_D(max_w, max_l, length, weight));
        return result;
    }

    static float[] reverseFloatArray(float[] array) {
        int length = array.length;
        for (int i = 0; i < length / 2; i++) {
            float temp = array[i];
            array[i] = array[length - i - 1];
            array[length - i - 1] = temp;
        }
        return array;
    }

    public static void main(String args[]){
      
        // read data from csv
        Algorithms algorithm=new Algorithms();
        CSV csv=new CSV();
        float[] random_length=new float[10];
        
        float[] random_weight=new float[10];
        float max_length=0;
        float max_weight=0;
        int result_bins;
        String result="";
        String header="Max_Length,Max_Weight,Input_Length,Input_Weight,Next_Fit,First_Fit,Best_Fit_W_L,Best_Fit_W_W\n";
        String filename="./New_Results/LWR_Range";
        
        csv.addHeader(header,filename+"1.csv");
        for(int i=0;i<1;i++){
            random_length=generateRandomNumbers(6.0093178,16,10);
            // Arrays.sort(random_length);
            random_length=reverseFloatArray(random_length);
            random_weight=generateWeight(random_length);
            // random_weight=generateRandomNumbers(30,530,10);
            max_length=generateSingleValue(6.0093178, 44.3, 10);
            max_weight=generateSingleValue(30, 530, 10);
            // result_bins=algorithm.next_fit(max_weight, max_length, random_length, random_weight);
            result=Float.toString(max_length)+", "+Float.toString(max_weight)+", "+getInputString(random_length)+", "+getInputString(random_weight)+", "+runALL(max_weight, max_length, random_length, random_weight)+"\n";
            csv.addRow(result, filename+"1.csv");
        }

        csv.addHeader(header,filename+"2.csv");
        for(int i=0;i<100;i++){
            random_length=generateRandomNumbers(16,26,10);
            // Arrays.sort(random_length);
            random_length=reverseFloatArray(random_length);
            random_weight=generateWeight(random_length);
            // random_weight=generateRandomNumbers(30,530,10);
            max_length=generateSingleValue(6.0093178, 44.3, 10);
            max_weight=generateSingleValue(30, 530, 10);
            // result_bins=algorithm.next_fit(max_weight, max_length, random_length, random_weight);
            result=Float.toString(max_length)+", "+Float.toString(max_weight)+", "+getInputString(random_length)+", "+getInputString(random_weight)+", "+runALL(max_length,max_weight, random_length, random_weight)+"\n";
            csv.addRow(result, filename+"2.csv");
        }

        csv.addHeader(header,filename+"3.csv");
        for(int i=0;i<100;i++){
            random_length=generateRandomNumbers(26,36,10);
            // Arrays.sort(random_length);
            random_length=reverseFloatArray(random_length);
            random_weight=generateWeight(random_length);
            // random_weight=generateRandomNumbers(30,530,10);
            max_length=generateSingleValue(6.0093178, 44.3, 10);
            max_weight=generateSingleValue(30, 530, 10);
            // result_bins=algorithm.next_fit(max_weight, max_length, random_length, random_weight);
            result=Float.toString(max_length)+", "+Float.toString(max_weight)+", "+getInputString(random_length)+", "+getInputString(random_weight)+", "+runALL(max_length, max_weight, random_length, random_weight)+"\n";
            csv.addRow(result, filename+"3.csv");
        }

        csv.addHeader(header,filename+"4.csv");
        for(int i=0;i<100;i++){
            random_length=generateRandomNumbers(36,45.7847091,10);
            // Arrays.sort(random_length);
            random_length=reverseFloatArray(random_length);
            random_weight=generateWeight(random_length);
            // random_weight=generateRandomNumbers(30,530,10);
            max_length=generateSingleValue(6.0093178, 44.3, 10);
            max_weight=generateSingleValue(30, 530, 10);
            // result_bins=algorithm.next_fit(max_weight, max_length, random_length, random_weight);
            result=Float.toString(max_length)+", "+Float.toString(max_weight)+", "+getInputString(random_length)+", "+getInputString(random_weight)+", "+runALL(max_length, max_weight, random_length, random_weight)+"\n";
            csv.addRow(result, filename+"4.csv");
        }


    }
}

class CSV{
    public float[][] data;
    public float[][] constraints;

    void saveToCSV(int[][] result,String path,int size){
        String csvFile = "./Results/"+path; // Your CSV file name
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            // Writing data to the CSV file
            writer.write("Max_Length, Max_Weight,Input_Length,Input_Weight,Next_Fit_Length,Next_Fit_Width,Next_Fit_Height,First_Fit_Length,First_Fit_Width,First_Fit_Height,Best_Fit_W_L,Best_Fit_W_W,Best_Fit_W_H,Best_Fit_D_L,Best_Fit_D_W,Best_Fit_D_H\n"); // Header row
            String str="";
            for(int i=0;i<result[0].length;i++){
                str=this.constraints[0][i]+ ", "+ this.constraints[1][i] +", ";
                for(int j=0;j<result.length;j++){
                    if(j==result.length-1){
                        str+=result[j][i]+"\n";
                    }else{
                        str+=result[j][i]+", ";
                    }
                }
                writer.write(str);
            }
            writer.write("\nDataSet Size: "+size+",,,,,,,,,,,");
            System.out.println("Data has been written to " + csvFile);
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    void addHeader(String header,String path){
        // 
        String csvFile = path; // Your CSV file name
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            // Writing data to the CSV file
            // writer.write("Max_Length,Max_Weight,Input_Length,Input_Weight,Next_Fit_Length,Next_Fit_Width,Next_Fit_Height,First_Fit_Length,First_Fit_Width,First_Fit_Height,Best_Fit_W_L,Best_Fit_W_W,Best_Fit_W_H,Best_Fit_D_L,Best_Fit_D_W,Best_Fit_D_H\n"); // Header row
            writer.write(header);
            System.out.println("Header Added to" + csvFile);
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    void addRow(String result,String path){
        String csvFile = path; // Your CSV file name
        try (FileWriter writer = new FileWriter(csvFile,true)) {
            // Writing data to the CSV file
            writer.write(result);
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
    
    void saveToCSVSTR(String[][] result,String path,int size){
        String csvFile = "./Results/"+path; // Your CSV file name
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            // Writing data to the CSV file
            writer.write("Max_Length, Max_Weight,Next_Fit_Length,Next_Fit_Width,Next_Fit_Height,First_Fit_Length,First_Fit_Width,First_Fit_Height,Best_Fit_W_L,Best_Fit_W_W,Best_Fit_W_H,Best_Fit_D_L,Best_Fit_D_W,Best_Fit_D_H\n"); // Header row
            String str="";
            
            for(int i=0;i<result[0].length;i++){
                str=this.constraints[0][i]+ ", "+ this.constraints[1][i] +", ";
                for(int j=0;j<result.length;j++){
                    if(j==result.length-1){
                        str+=result[j][i]+"\n";
                    }else{
                        str+=result[j][i]+", ";
                    }
                }
                writer.write(str);
            }
            writer.write("\nDataSet Size: "+size+",,,,,,,,,,,");
            System.out.println("Data has been written to " + csvFile);
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

}