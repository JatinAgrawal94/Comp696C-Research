package First;
// import java.util.*;
import java.util.HashMap;
import java.util.Map;


public class Algorithms {
   public int next_fit(Float max_w,Float max_l,float[] length,float[] weight){
        int bins = 0;
        Float rem_w = max_w;
        Float rem_l = max_l;

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

    int first_fit(Float max_w,Float max_l,float[] length,float[] weight){
        int bins=0;
        float[] rem_w=new float[weight.length];
        float[] rem_l=new float[length.length];
       
        int j=0;
        for(int i=0;i<weight.length;i++){
            j=0;
            while(j<bins){
                if(rem_w[j] >= weight[i] && rem_l[j] >= length[i]){
                    rem_w[j]=rem_w[j]-weight[i];
                    rem_l[j]=rem_l[j]-length[i];
                    break;
                }
                j++;
            }
            if(j==bins && max_l>=length[i] && max_w>=weight[i]){
                rem_l[bins]=max_l-length[i];
                rem_w[bins]=max_w-weight[i];
                bins++;
            }
        }
        return bins;   
    }

   int bestFit_Weight(Float max_w,Float max_l,float[] length,float[] weight){   
        float[] rem_w=new float[weight.length];
        float[] rem_l=new float[weight.length];
        int res = 0;
        int min,bi;
        for (int i = 0; i < weight.length; i++) 
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
    
            if (min == (int)(max_w + 1) && max_w>=weight[i] && max_l>=length[i])
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

    int bestFit_D(Float max_w,Float max_l,float[] length,float[] weight){   
        float[] rem_w=new float[weight.length];
        float[] rem_l=new float[weight.length];
        int res = 0;
        int min,bi;
        for (int i = 0; i < weight.length; i++) 
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
            if (min == (int)(max_w + 1) && max_w>=weight[i] && max_l>=length[i])
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

    public static void insertionSort(TripleMap[] arr) {
        for (int i = 1; i < arr.length; i++) {
            TripleMap key =arr[i];
            int j = i - 1;
            while (j >= 0 && Float.parseFloat(arr[j].map1.get("length")) < Float.parseFloat(key.map1.get("length"))) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }
    
    int first_fit_D_H_2D(Float max_w,Float max_l,Float max_wd,float[] length,float[] weight,float[] width){
        // here we have to arrange eleements by length and arrange on the width side.
        int bins=0;
        float[] rem_w=new float[weight.length];
        float[] rem_l=new float[length.length];
        float[] rem_wd=new float[width.length];
        float occupied_height=0;
        // jatin home.java home.class Algorithm.java bash Researchjava I feel lazu a
        TripleMap[] array=new TripleMap[10];
        for(int i=0;i<10;i++){
           array[i]=new TripleMap();
        }
        for(int i=0;i<10;i++){
            array[i].map1.put("length", Float.toString(length[i]));
            array[i].map2.put("width", Float.toString(width[i]));
            array[i].map3.put("weight", Float.toString(weight[i]));
        }
        insertionSort(array);
        for(int i=0;i<10;i++){
            length[i]=Float.parseFloat(array[i].map1.get("length"));
            width[i]=Float.parseFloat(array[i].map2.get("width"));
            weight[i]=Float.parseFloat(array[i].map3.get("weight"));
        }
        // arrange rem_l according to height
        int j=0;
        for(int i=0;i<weight.length;i++){
            j=0;
            while(j<bins){
                if(rem_w[j] >= weight[i] && rem_wd[j] >= width[i]){
                    rem_w[j]=rem_w[j]-weight[i];
                    rem_wd[j]=rem_wd[j]-width[i];
                    break;
                }
                j++;
            }
            if(j==bins && max_wd>=width[i] && max_w>=weight[i] && max_l>=length[i] && occupied_height<=max_l){
                rem_wd[bins]=max_wd-width[i];
                rem_w[bins]=max_w-weight[i];
                rem_l[bins]=max_l-length[i];
                occupied_height+=length[i];
                if(occupied_height<=max_l){
                    bins++;
                }else{
                    if(j<length.length){
                        j++;
                    }else{
                        break;
                    }
                }
            }
        }
        return bins;   
    }    
    int next_fit_D_H_2D(Float max_w,Float max_l,Float max_wd,float[] length,float[] weight,float[] width){
        int bins = 0;
        Float rem_w = max_w;
        Float rem_l = max_l;
        Float rem_wd=max_wd;
        // float[] rem_wd=new float[width.length];
        float occupied_height=0;

        TripleMap[] array=new TripleMap[10];
        for(int i=0;i<10;i++){
           array[i]=new TripleMap();
        }
        for(int i=0;i<10;i++){
            array[i].map1.put("length", Float.toString(length[i]));
            array[i].map2.put("width", Float.toString(width[i]));
            array[i].map3.put("weight", Float.toString(weight[i]));
        }
        insertionSort(array);
        for(int i=0;i<10;i++){
            length[i]=Float.parseFloat(array[i].map1.get("length"));
            width[i]=Float.parseFloat(array[i].map2.get("width"));
            weight[i]=Float.parseFloat(array[i].map3.get("weight"));
        }

        for(int i=0;i<weight.length;i++){
            if (bins == 0){
                bins += 1;
                // rem_l -= length[i];
                rem_wd-=width[i];
                rem_w -= weight[i];
                occupied_height+=length[i];
            }
            else if(width[i] <= rem_wd && weight[i] <= rem_w){
                rem_wd -= width[i];
                rem_w -= weight[i];
            }
            else if(width[i] <= max_wd && weight[i] <= max_w && occupied_height<=max_l){
                bins += 1;
                rem_wd = max_wd - width[i];
                rem_w = max_w - weight[i];
                occupied_height+=length[i];
            }
        }    
        return bins;
    }
    int best_fit_D_H_2D(Float max_w,Float max_l,Float max_wd,float[] length,float[] weight,float[] width){
        float[] rem_w=new float[weight.length];
        float[] rem_l=new float[weight.length];
        float[] rem_wd=new float[width.length];
        int res = 0;
        int min,bi;
        for (int i = 0; i < weight.length; i++) 
        {
             min = (int)(max_w+1);
             bi = 0;
    
            for (int j = 0; j < res; j++) 
            {
                if (rem_w[j] >= weight[i] && rem_wd[j] >= width[i] && rem_w[j] - weight[i] < min)
                {
                    bi = j;
                    min = (int)(rem_w[j] - weight[i]);
                }
            }
    
            if (min == (int)(max_w + 1) && max_w>=weight[i] && max_wd>=width[i])
            {
                rem_w[res] = max_w - weight[i];
                rem_wd[res] = max_wd - width[i];
                res=res+ 1;
            }
            else // Assign the item to best bin
                rem_w[bi] -= weight[i];
                rem_wd[bi] -= width[i];
        }        
        return res;
    }
}




