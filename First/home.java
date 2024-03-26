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

    private static float[] generateWeight(float[] length,float[] width){
        float[] weight=new float[10];
        for(int i=0;i<weight.length;i++){
            weight[i]=(float)(length[i]*width[i]*0.025*9.8);
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
        result+=String.valueOf(alg.bestFit_D(max_w, max_l, length, weight))+",";
        result+=String.valueOf(alg.bestFit_Weight(max_w, max_l, length, weight));
        return result;
    }

    static String runALL2(Float max_w,Float max_l,Float max_wd,float[] length,float[] weight,float[] width){
        String result="";
        Algorithms alg=new Algorithms();
        result+=String.valueOf(alg.first_fit_D_H_2D(max_w, max_l,max_wd, length, weight,width))+",";
        result+=String.valueOf(alg.next_fit_D_H_2D(max_w, max_l,max_wd, length, weight,width))+",";
        result+=String.valueOf(alg.best_fit_D_H_2D(max_w, max_l,max_wd, length, weight,width))+",";
        return result;
    }

    static void printArray(float[] arr){
        String result="";
        for(int i =0;i<arr.length;i++){
            result=result+arr[i]+" ";
        }
        System.out.println(result);
    }

    static float[] reverseFloatArray(float[] array) {
        int length = array.length;
        Arrays.sort(array);
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
        float[] random_width=new float[10];
        float[] random_weight=new float[10];
        float max_length=0;
        float max_weight=0;
        float max_width=0;
        String result="";
        String header="Max_Length,Max_Weight,Input_Length,Input_Weight,Next_Fit,First_Fit,Best_Fit_W_L,Best_Fit_W_W\n";
        String filename="";
        String header1="Max_Length,Max_Width,Max_Weight,Input_Length,Input_Width,Input_Weight,First_Fit_Dec_Height,Next_Fit_Dec_Height,Best_Fit_Dec_Height,\n";

        String[] types_1={"L_Range","LS_Range","LR_Range","LW_Range","LWS_Range","LWR_Range"};
        String[] types_2={"LWDW_Range","LWD_Range"};
        int l_begin=6;
        int w_begin=3;
        
        
        //  For length and Weight
        // for(int i=0;i<types_1.length;i++){
        //     l_begin=6;
        //     for(int j=0;j<4;j++){
        //         filename="./Tests/"+types_1[i]+String.valueOf(j+1)+".csv";
        //         csv.addHeader(header,filename);
        //         // System.out.println(l_begin);
        //         for(int k=0;k<100;k++){
        //             random_length=generateRandomNumbers(l_begin,l_begin+10,10);
        //             max_length=generateSingleValue(16, 46, 10);
        //             max_weight=generateSingleValue(30, 530, 10);
        //             // conditioning based on dependent and independent weight
        //             if(i>=0 && i<=2){
        //                 random_weight=generateRandomNumbers(30,530,10);
        //                 if(types_1[i]=="LS_Range")
        //                     Arrays.sort(random_length);
        //                 else if(types_1[i]=="LR_Range")
        //                     random_length=reverseFloatArray(random_length);
        //             }else{
        //                 random_weight=generateWeight(random_length);
        //                 if(types_1[i]=="LWS_Range")
        //                 Arrays.sort(random_length);
        //                 else if(types_1[i]=="LWR_Range")
        //                 random_length=reverseFloatArray(random_length);
        //             }
        //             result=Float.toString(max_length)+", "+Float.toString(max_weight)+", "+getInputString(random_length)+", "+getInputString(random_weight)+", "+runALL(max_weight, max_length, random_length, random_weight)+"\n";
        //             csv.addRow(result,filename);
        //         }
        //         l_begin+=10;
        //     }
        // }
        
        // 2d
        
        for(int i=0;i<types_2.length;i++){
            l_begin=6;
            w_begin=3;
            for(int j=0;j<4;j++){
                filename="./Tests/"+types_2[i]+String.valueOf(j+1)+".csv";
                csv.addHeader(header1,filename);
                for(int k=0;k<100;k++){
                    random_length=generateRandomNumbers(l_begin,l_begin+10,10);
                    random_width=generateRandomNumbers(w_begin,w_begin+7,10);
                    max_length=generateSingleValue(16, 46, 10);
                    max_weight=generateSingleValue(30, 530, 10);
                    max_width=generateSingleValue(3,31, 10);
                    // algorithm.first_fit_D_H_2D(max_weight, max_length, max_width, random_length, random_weight, random_width);
                        if(types_2[i]=="LWDW_Range"){
                            random_weight=generateWeight(random_length);
                        }
                        else if(types_2[i]=="LWD_Range"){
                            random_weight=generateRandomNumbers(30,530,10);
                        }
                    result=Float.toString(max_length)+", "+Float.toString(max_width)+", "+Float.toString(max_weight)+", "+getInputString(random_length)+", "+getInputString(random_width)+", "+getInputString(random_weight)+", "+runALL2(max_weight, max_length, max_width,random_length, random_weight,random_width)+"\n";
                    csv.addRow(result,filename);
                    }
                    l_begin+=10;
                    w_begin+=7;
                }
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