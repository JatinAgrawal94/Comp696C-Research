package First;
// import java.io.BufferedReader;  
import java.io.IOException;
// import java.nio.charset.StandardCharsets;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
// import java.text.DecimalFormat;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Random;
import java.lang.Math;
import java.io.BufferedWriter;
import java.io.FileWriter;
// import First.Algorithms;

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
        result+=String.valueOf(alg.next_fit(max_w, max_l, length, weight))+",";
        return result;
    }

    public static void main(String args[]){
      
        // read data from csv
        Algorithms algorithm=new Algorithms();
        CSV csv=new CSV();
        float[] random_length=new float[10];
        float[] random_width=new float[10];
        float[] random_height=new float[10];
        float[] random_weight=new float[10];
        float max_length;
        float max_weight;
        int result_bins;
        String result="";
        String header="Max_Length,Max_Weight,Input_Length,Input_Weight,Next_Fit_Length,First_Fit_Length,Best_Fit_W_L,Best_Fit_W_W\n";
        csv.addHeader(header,"./first.csv");
        for(int i=0;i<100;i++){
            random_length=generateRandomNumbers(6.0093178,16,10);
            random_weight=generateRandomNumbers(30,530,10);
            max_length=generateSingleValue(6.0093178, 44.3, 10);
            max_weight=generateSingleValue(30, 530, 10);
            result_bins=algorithm.next_fit(max_weight, max_length, random_length, random_weight);
            result=Float.toString(max_length)+", "+Float.toString(max_length)+", "+getInputString(random_length)+", "+getInputString(random_weight)+", "+String.valueOf(result_bins)+",,,"+"\n";
            csv.addRow(result, "./first.csv");
            }
        // generate 10 pairs of values for length and weightwithin given range and th
        
        

        // List<String> sets=new ArrayList<String>();
        // sets.add("boxdataset-1k.csv");
        // sets.add("1_D_Test2_input_Length.csv");
        // sets.add("1_D_Test2_input_Width.csv");
        // sets.add("1_D_Test2_input_Height.csv");
        // sets.add("1_D_Test3_input_WL150.csv");
        // List<String> resultSet=new ArrayList<String>();
        // resultSet.add("1_D_Test1_info_op.csv");
        // resultSet.add("1_D_Test2_info_op_Length.csv");
        // resultSet.add("1_D_Test2_info_op_Width.csv");
        // resultSet.add("1_D_Test2_info_op_Height.csv");
        // resultSet.add("1_D_Test3_op_WL150.csv");

        // try {
        // for(int i=0;i<sets.size();i++){
        //     b.readFromCSV("./Datasets/"+sets.get(i));
        //     ArrayList<List<String>> data= b.getCSV();
        //     Algorithms alg=new Algorithms(data, constraints);
        //         int[][] result=new int[12][constraints.get(0).size()];
        //         result=alg.runAll();
        //         // alg.saveToCSV(result,resultSet.get(i),data.get(0).size());
        //         long endTime = System.currentTimeMillis();
        //         long timeTaken=endTime-startTime;
        //         System.out.println(timeTaken);
        // }
        // } catch (Exception e) {
        //     System.out.println(e);        
        // }
    }
}
/* 
class Box{
    public List<String> boxlength=new ArrayList<String>();
    public List<String> boxwidth=new ArrayList<String>();
    public List<String> boxheight=new ArrayList<String>();
    final DecimalFormat decfor = new DecimalFormat("0.00");  

    ArrayList<List<String>> getCSV(){
        ArrayList<List<String>> result= new ArrayList<List<String>>();
        ArrayList<List<String>> r=this.CalculateWandV();
        result.add(this.boxlength);
        result.add(this.boxwidth);
        result.add(this.boxheight);
        // volume
        result.add(r.get(0));
        // weight
        result.add(r.get(1));
        return result;
    }

    void readFromCSV(String filename){
        Path path=Paths.get(filename);
        this.boxlength.clear();
        this.boxwidth.clear();
        this.boxheight.clear();
        try {
            BufferedReader br = Files.newBufferedReader(path,StandardCharsets.US_ASCII);
            String line=br.readLine();
            while(line!=null){
                String[] attr = line.split(",");
                this.boxlength.add(attr[0]);
                this.boxwidth.add(attr[1]);
                this.boxheight.add(attr[2]);
                line = br.readLine();
            }
            // System.out.println(this.boxlength.get(0));
            String c=this.boxlength.remove(0);
            c=this.boxwidth.remove(0);
            c=this.boxheight.remove(0);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    ArrayList<List<String>> CalculateWandV(){
        List<String> Volume=new ArrayList<String>();
        List<String> Weight=new ArrayList<String>();
        for(int i=0;i<this.boxlength.size();i++){
            Volume.add(String.valueOf((int)(Float.valueOf(this.boxlength.get(i))*Float.valueOf(this.boxwidth.get(i))*Float.valueOf(this.boxheight.get(i)))));
        }
        for(int i=0;i<this.boxlength.size();i++){
            Weight.add(String.valueOf(Math.round(Float.valueOf(Volume.get(i))*0.025*9.8/10)));
        }
        ArrayList<List<String>> r=new ArrayList<List<String>>();
        r.add(Volume);
        r.add(Weight);
        return r;
    }
}
*/

class CSV{
    public float[][] data;
    public float[][] constraints;

    // public CSV(ArrayList<List<String>> data,ArrayList<List<String>> constraints){
    //     float[][] dt=new float[data.size()][data.get(0).size()];
    //     float[][] ct=new float[constraints.size()][constraints.get(0).size()];
    //     for(int i=0;i<data.size();i++){
    //         for(int j=0;j<data.get(0).size();j++){
    //             dt[i][j]=Float.valueOf(data.get(i).get(j));
    //         }
    //     }
    //     for(int i=0;i<constraints.size();i++){
    //         for(int j=0;j<constraints.get(0).size();j++){
    //             ct[i][j]=Float.valueOf(constraints.get(i).get(j));
    //         }
    //     }
    //     this.data=dt;
    //     this.constraints=ct;
    // }
        
    int [][] runAll(){
        int temp=0;
        // String temps="[";
        int[][] result=new int[12][constraints[0].length];    
        // String[][] results=new String[12][constraints[0].length];  
        // addHeader("Max_Length, Max_Weight,Next_Fit_Length,Next_Fit_Width,Next_Fit_Height,First_Fit_Length,First_Fit_Width,First_Fit_Height,Best_Fit_W_L,Best_Fit_W_W,Best_Fit_W_H,Best_Fit_D_L,Best_Fit_D_W,Best_Fit_D_H\n","1_D_Test3_op_WL150.csv");  
        
        for(int i=0;i<constraints[0].length;i++){
            System.out.println(i);
            // ----------------------NEXT FIT ALGO STARTS---------------------------
                // nextfit for length
                temp=0;
                // temps="[";
                for(int j=0;j<100;j++){
                    temp+=(this.next_fit(Float.valueOf(constraints[1][i]), Float.valueOf(constraints[0][i]),0));
                    // temps+=String.valueOf(this.next_fit(Float.valueOf(constraints[1][i]), Float.valueOf(constraints[0][i]),0))+'|';
                }
                // temps+="]";
                // results[0][i]=temps;
                result[0][i]=(Integer.valueOf(temp/100));

                // nextfit for width
                temp=0;
                // temps="[";
                for(int j=0;j<100;j++){ 
                    temp+=(this.next_fit(Float.valueOf(constraints[1][i]), Float.valueOf(constraints[0][i]),1));
                    // temps+=String.valueOf(this.next_fit(Float.valueOf(constraints[1][i]), Float.valueOf(constraints[0][i]),1))+"|";
                }
                // temps+="]";
                // results[1][i]=temps;
                result[1][i]=(Integer.valueOf(temp/100));
                
                // nextfit for height
                temp=0;
                // temps="[";
                for(int j=0;j<100;j++){
                    temp+=(this.next_fit(Float.valueOf(constraints[1][i]), Float.valueOf(constraints[0][i]),2));
                    // temps+=String.valueOf(this.next_fit(Float.valueOf(constraints[1][i]), Float.valueOf(constraints[0][i]),2))+"|";
                }
                // temps+="]";
                // results[2][i]=temps;
                result[2][i]=(Integer.valueOf(temp/100));

                // ---------------------------NEXTFIT ALGO ENDS-------------------------------
                // --------------------------FIRSTFIT ALGO STARTS-------------------------------

                // firstfit for length
                temp=0;
                // temps="[";
                for(int j=0;j<100;j++){
                    // temps+=String.valueOf(this.first_fit(Float.valueOf(constraints[1][i]), Float.valueOf(constraints[0][i]),0))+"|";
                    temp+=(this.first_fit(Float.valueOf(constraints[1][i]), Float.valueOf(constraints[0][i]),0));
                }
                // temps+="]";
                // results[3][i]=temps;
                result[3][i]=(Integer.valueOf(temp/100));
 
                // firstfit for width
                temp=0;
                // temps="[";
                for(int j=0;j<100;j++){
                    // temps+=String.valueOf(this.first_fit(Float.valueOf(constraints[1][i]), Float.valueOf(constraints[0][i]),1))+"|";
                    temp+=(this.first_fit(Float.valueOf(constraints[1][i]), Float.valueOf(constraints[0][i]),1));
                }
                // temps+="]";
                // results[4][i]=temps;
                result[4][i]=(Integer.valueOf(temp/100));

                // firstfit for height
                temp=0;
                // temps="[";
                for(int j=0;j<100;j++){
                    // temps+=String.valueOf(this.first_fit(Float.valueOf(constraints[1][i]), Float.valueOf(constraints[0][i]),2))+"|";
                    temp+=(this.first_fit(Float.valueOf(constraints[1][i]), Float.valueOf(constraints[0][i]),2));
                }
                // temps+="]";
                // results[5][i]=temps;
                result[5][i]=(Integer.valueOf(temp/100));

                // ------------------------------BESTFIT PREF WEIGHT ALGO STARTS HERE-----------------------------------
                
                // bestfit_weight for length
                temp=0;
                // temps="[";
                for(int j=0;j<100;j++){
                    // temps+=String.valueOf(this.bestFit_Weight(Float.valueOf(constraints[1][i]), Float.valueOf(constraints[0][i]),0))+"|";
                    temp+=(this.bestFit_Weight(Float.valueOf(constraints[1][i]), Float.valueOf(constraints[0][i]),0));
                }
                // temps+="]";
                // results[6][i]=temps;
                result[6][i]=(Integer.valueOf(temp/100));

                // bestfit_weight for width
                temp=0;
                // temps="[";
                for(int j=0;j<100;j++){
                    // temps+=String.valueOf(this.bestFit_Weight(Float.valueOf(constraints[1][i]), Float.valueOf(constraints[0][i]),1))+"|";
                    temp+=(this.bestFit_Weight(Float.valueOf(constraints[1][i]), Float.valueOf(constraints[0][i]),1));
                }
                // temps+="]";
                // results[7][i]=temps;
                result[7][i]=(Integer.valueOf(temp/100));

                // bestfit_weight for height
                temp=0;
                // temps="[";
                for(int j=0;j<100;j++){
                    // temps+=String.valueOf(this.bestFit_Weight(Float.valueOf(constraints[1][i]), Float.valueOf(constraints[0][i]),2))+"|";
                    temp+=(this.bestFit_Weight(Float.valueOf(constraints[1][i]), Float.valueOf(constraints[0][i]),2));
                }
                // temps+="]";
                // results[8][i]=temps;
                result[8][i]=(Integer.valueOf(temp/100));

                //---------------------------------BESTFIT PREF WEGIHT ALGO ENDS HERE-------------------------------------------
                //---------------------------------BESTFIT PREF DIMENSIO ALGO STARTS HERE----------------------------------------

                //bestfit pref length
                temp=0;
                // temps="[";
                for(int j=0;j<100;j++){
                    // temps+=String.valueOf(this.bestFit_D(Float.valueOf(constraints[1][i]), Float.valueOf(constraints[0][i]),0))+"|";
                    temp+=(this.bestFit_D(Float.valueOf(constraints[1][i]), Float.valueOf(constraints[0][i]),0));
                }
                result[9][i]=(Integer.valueOf(temp/100));
                // temps+="]";
                // results[9][i]=temps;

                //bestfit pref width
                temp=0;
                // temps="[";
                for(int j=0;j<100;j++){
                    // temps+=String.valueOf(this.bestFit_D(Float.valueOf(constraints[1][i]), Float.valueOf(constraints[0][i]),1))+"|";
                    temp+=(this.bestFit_D(Float.valueOf(constraints[1][i]), Float.valueOf(constraints[0][i]),1));
                }
                result[10][i]=(Integer.valueOf(temp/100));
                // temps+="]";
                // results[10][i]=temps;

                //bestfit pref height
                temp=0;
                // temps="[";
                for(int j=0;j<100;j++){
                    // temps+=String.valueOf(this.bestFit_D(Float.valueOf(constraints[1][i]), Float.valueOf(constraints[0][i]),2))+"|";
                    temp+=(this.bestFit_D(Float.valueOf(constraints[1][i]), Float.valueOf(constraints[0][i]),2));
                }
                result[11][i]=(Integer.valueOf(temp/100));
                // temps+="]";
                // results[11][i]=temps;
                //-------------------------------BESTFIT PREF DIMENSION ALGO ENDS HERE---------------------------------
                String str=String.valueOf(constraints[0][i])+","+String.valueOf(constraints[1][i])+",";
                String str1="";
                for(int j=0;j<12;j++){
                    if(j==11){
                        str1+=result[j][i]+"\n";
                        break;    
                    }
                    str1+=result[j][i]+", ";
                }
                str=str+str1;
                addRow(str, "1_D_Test3_op_WL150.csv");
        }
        return result;
    }

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
            System.out.println("Row has been added to " + csvFile);
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

    int next_fit(Float max_w,Float max_l,int d){
        int bins = 0;
        Float rem_w = max_w;
        Float rem_l = max_l;
        float[] weight=this.data[4];
        float[] length=this.data[d];

        for(int i=0;i<weight.length;i++){
            if (bins == 0){
                bins += 1;
                rem_l -= length[i];
                rem_w -= weight[i];
            }
            else if(length[i] <= rem_l && weight[i] <= rem_w){
                rem_l -= length[i];
                rem_w -= weight[i];
            }
            else if(length[i] <= max_l && weight[i] <= max_w){
                bins += 1;
                rem_l = max_l - length[i];
                rem_w = max_w - weight[i];
            }
        }
    return bins;
    }

    int first_fit(Float max_w,Float max_l,int d){
        int bins=0;
        float[] rem_w=new float[this.data[d].length];
        float[] rem_l=new float[this.data[d].length];
       
        int j=0;
        for(int i=0;i<this.data[4].length;i++){
            j=0;
            while(j<bins){
                if(rem_w[j] >= Float.valueOf(this.data[4][j]) && rem_l[j] >= Float.valueOf(this.data[d][j])){
                    rem_w[j]=rem_w[j]-Float.valueOf(this.data[4][i]);
                    rem_l[j]=rem_l[j]-Float.valueOf(this.data[d][i]);
                    break;
                }
                j++;
            }
            if(j==bins){
                rem_l[bins]=max_l-Float.valueOf(this.data[d][i]);
                rem_w[bins]=max_w-Float.valueOf(this.data[4][i]);
                bins++;
            }
        }
        return bins;   
    }

   int bestFit_Weight(Float max_w,Float max_l,int d)
    {   
        int n=this.data[d].length;
        float[] weight=this.data[4];
        float[] length=this.data[d];
        float[] rem_w=new float[n];
        float[] rem_l=new float[n];
        int res = 0;
        int min,bi;
        for (int i = 0; i < this.data[0].length; i++) 
        {
             min = (int)(max_w+1);
             bi = 0;
    
            for (int j = 0; j < res; j++) 
            {
                if (rem_w[j] >= weight[i] && rem_l[j] >= length[i] && rem_w[j] - weight[i] < min)
                {
                    bi = j;
                    min = (int)(rem_w[j] - weight[i]);
                }
            }
    
            if (min == (int)(max_w + 1))
            {
                rem_w[res] = max_w - weight[i];
                rem_l[res] = max_l - length[i];
                res=res+ 1;
            }
            else // Assign the item to best bin
                rem_w[bi] -= weight[i];
                rem_l[bi] -= length[i];
        }        
        return res;
    }
    int bestFit_D(Float max_w,Float max_l,int d)
    {   
        int n=this.data[d].length;
        float[] weight=this.data[4];
        float[] length=this.data[d];
        float[] rem_w=new float[n];
        float[] rem_l=new float[n];
        int res = 0;
        int min,bi;
        for (int i = 0; i < this.data[0].length; i++) 
        {
            min = (int)(max_w+1);
            bi = 0;
    
            for (int j = 0; j < res; j++) 
            {
                if (rem_w[j] >= weight[i] && rem_l[j] >= length[i] && rem_l[j] - length[i] < min){
                    bi = j;
                    min = (int)(rem_w[j] - weight[i]);
                }
            }
            if (min == (int)(max_w + 1))
            {
                rem_w[res] = max_w - weight[i];
                rem_l[res] = max_l - length[i];
                res += 1;
            }
            else // Assign the item to best bin
                rem_w[bi] -= weight[i];
                rem_l[bi] -= length[i];
        }
        return res;
    }
}
/* 
class Dimension{
    public List<String> maxlength=new ArrayList<String>();
    public List<String> maxweight=new ArrayList<String>();
    
     ArrayList<List<String>> getCSV(){
        ArrayList<List<String>> result= new ArrayList<List<String>>();
        result.add(this.maxlength);
        result.add(this.maxweight);
        return result;
    }

    void readFromCSV(String filename){
        Path p=Paths.get(filename);
        try {
            BufferedReader br = Files.newBufferedReader(p,StandardCharsets.US_ASCII);
            String line=br.readLine();
            while(line!=null){
                String[] attr = line.split(",");
                this.maxlength.add(attr[0]);
                this.maxweight.add(attr[1]);
                line = br.readLine();
            }
            String c=this.maxlength.remove(0);
            c=this.maxweight.remove(0);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

*/