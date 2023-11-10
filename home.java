
import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;  
import java.text.DecimalFormat;  
import java.lang.Math;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class home{
    public static void main(String args[]){
        long startTime = System.currentTimeMillis();
        Box b=new Box();
        Dimension d=new Dimension();
        // read data from csv

        b.readFromCSV("./Datasets/1_D_Test2_input_Height.csv");
        ArrayList<List<String>> data= b.getCSV();

        d.readFromCSV("./Datasets/length-weight.csv");
        ArrayList<List<String>> constraints= d.getCSV();

        Algorithms alg=new Algorithms(data, constraints);
        try {
            int[][] result=new int[12][constraints.get(0).size()];
            result=alg.runAll();
            alg.saveToCSV(result);
        } catch (Exception e) {
            System.out.println(e);        
        }
        long endTime = System.currentTimeMillis();
        long timeTaken=endTime-startTime;
        System.out.println(timeTaken);
    }
}

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

class Algorithms{
    public float[][] data;
    public float[][] constraints;

    public Algorithms(ArrayList<List<String>> data,ArrayList<List<String>> constraints){
        float[][] dt=new float[data.size()][data.get(0).size()];
        float[][] ct=new float[constraints.size()][constraints.get(0).size()];
        for(int i=0;i<data.size();i++){
            for(int j=0;j<data.get(0).size();j++){
                dt[i][j]=Float.valueOf(data.get(i).get(j));
            }
        }
        for(int i=0;i<constraints.size();i++){
            for(int j=0;j<constraints.get(0).size();j++){
                ct[i][j]=Float.valueOf(constraints.get(i).get(j));
            }
        }
        this.data=dt;
        this.constraints=ct;
    }
    int[][] runAll(){
        int temp=0;
        int[][] result=new int[12][constraints[0].length];    
        for(int i=0;i<constraints[0].length;i++){
            // ----------------------NEXT FIT ALGO STARTS---------------------------
                // nextfit for length
                temp=0;
                for(int j=0;j<100;j++){
                    temp+=(this.next_fit(Float.valueOf(constraints[1][i]), Float.valueOf(constraints[0][i]),0));
                }
                result[0][i]=(Integer.valueOf(temp/100));
                // nextfit for width
                temp=0;
                for(int j=0;j<100;j++){
                    temp+=(this.next_fit(Float.valueOf(constraints[1][i]), Float.valueOf(constraints[0][i]),1));
                }
                result[1][i]=(Integer.valueOf(temp/100));
                
                // nextfit for height
                temp=0;
                for(int j=0;j<100;j++){
                    temp+=(this.next_fit(Float.valueOf(constraints[1][i]), Float.valueOf(constraints[0][i]),2));
                }
                result[2][i]=(Integer.valueOf(temp/100));

                // ---------------------------NEXTFIT ALGO ENDS-------------------------------
                // --------------------------FIRSTFIT ALGO STARTS-------------------------------
                // firstfit for length
                temp=0;
                for(int j=0;j<100;j++){
                    temp+=(this.first_fit(Float.valueOf(constraints[1][i]), Float.valueOf(constraints[0][i]),0));
                }
                result[3][i]=(Integer.valueOf(temp/100));
 
                // firstfit for width
                temp=0;
                for(int j=0;j<100;j++){
                    temp+=(this.first_fit(Float.valueOf(constraints[1][i]), Float.valueOf(constraints[0][i]),1));
                }
                result[4][i]=(Integer.valueOf(temp/100));

                // firstfit for height
                temp=0;
                for(int j=0;j<100;j++){
                    temp+=(this.first_fit(Float.valueOf(constraints[1][i]), Float.valueOf(constraints[0][i]),2));
                }
                result[5][i]=(Integer.valueOf(temp/100));
                // ------------------------------BESTFIT PREF WEIGHT ALGO STARTS HERE-----------------------------------
                
                // bestfit_weight for length
                temp=0;
                for(int j=0;j<100;j++){
                    temp+=(this.bestFit_Weight(Float.valueOf(constraints[1][i]), Float.valueOf(constraints[0][i]),0));
                }
                result[6][i]=(Integer.valueOf(temp/100));

                // bestfit_weight for width
                temp=0;
                for(int j=0;j<100;j++){
                    temp+=(this.bestFit_Weight(Float.valueOf(constraints[1][i]), Float.valueOf(constraints[0][i]),1));
                }
                result[7][i]=(Integer.valueOf(temp/100));

                // bestfit_weight for height
                temp=0;
                for(int j=0;j<100;j++){
                    temp+=(this.bestFit_Weight(Float.valueOf(constraints[1][i]), Float.valueOf(constraints[0][i]),2));
                }
                result[8][i]=(Integer.valueOf(temp/100));
                //---------------------------------BESTFIT PREF WEGIHT ALGO ENDS HERE-------------------------------------------
                //---------------------------------BESTFIT PREF DIMENSIO ALGO STARTS HERE----------------------------------------
                //bestfit pref length
                temp=0;
                for(int j=0;j<100;j++){
                    temp+=(this.bestFit_D(Float.valueOf(constraints[1][i]), Float.valueOf(constraints[0][i]),0));
                }
                result[9][i]=(Integer.valueOf(temp/100));
                
                //bestfit pref width
                temp=0;
                for(int j=0;j<100;j++){
                    temp+=(this.bestFit_D(Float.valueOf(constraints[1][i]), Float.valueOf(constraints[0][i]),1));
                }
                result[10][i]=(Integer.valueOf(temp/100));
                
                //bestfit pref height
                temp=0;
                for(int j=0;j<100;j++){
                    temp+=(this.bestFit_D(Float.valueOf(constraints[1][i]), Float.valueOf(constraints[0][i]),2));
                }
                result[11][i]=(Integer.valueOf(temp/100));
                //-------------------------------BESTFIT PREF DIMENSION ALGO ENDS HERE---------------------------------
        }
        
        return result;
    }

    void saveToCSV(int[][] result){
        String csvFile = "./Results/1_D_Test_2_Height.csv"; // Your CSV file name
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
            // System.out.println(this.maxlength);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}