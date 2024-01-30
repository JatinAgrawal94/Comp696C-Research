package First;
import java.util.*;

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
            if(j==bins){
                rem_l[bins]=max_l-length[i];
                rem_w[bins]=max_w-weight[i];
                bins++;
            }
        }
        return bins;   
    }

   int bestFit_Weight(Float max_w,Float max_l,float[] length,float[] weight)
    {   
        
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
    int bestFit_D(Float max_w,Float max_l,float[] length,float[] weight)
    {   
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

    int first_fit_D_H_2D(Float max_w,Float max_l,Float max_wd,float[] length,float[] weight,float[] width){
        // here we have to arrange eleements by length and arrange on the width side.
        int bins=0;
        float[] rem_w=new float[weight.length];
        float[] rem_l=new float[length.length];
        float[] rem_wd=new float[width.length];
        float occupied_height=0;
        Arrays.sort(length);

        // arrange rem_l according to height
        int j=0;
        for(int i=0;i<weight.length;i++){
            j=0;
            while(occupied_height<=max_l){
            while(j<bins){
                if(rem_w[j] >= weight[i] && rem_wd[j] >= width[i]){
                    rem_w[j]=rem_w[j]-weight[i];
                    rem_wd[j]=rem_wd[j]-width[i];
                    break;
                }
                j++;
            }
            if(j==bins){
                rem_wd[bins]=max_wd-width[i];
                rem_w[bins]=max_w-weight[i];
                rem_l[bins]=max_l-length[i];
                occupied_height+=length[i];
                bins++;
            }
        }
        }
        return bins;   
    }

}
