package First;
import java.util.ArrayList;
import java.util.Arrays;
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
    
    public static void insertionSortByHeight(ThreeDimension[] arr) {
        for (int i = 1; i < arr.length; i++) {
            ThreeDimension key =arr[i];
            int j = i - 1;
            while (j >= 0 && Float.parseFloat(arr[j].map3.get("height")) < Float.parseFloat(key.map3.get("height"))) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }
    /* 
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

            // if(j==bins && max_wd>=width[i] && max_w>=weight[i] && max_l>=length[i] && occupied_height<=max_l){
            //     rem_wd[bins]=max_wd-width[i];
            //     rem_w[bins]=max_w-weight[i];
            //     rem_l[bins]=max_l-length[i];
            //     occupied_height+=length[i];
                
            //     if(occupied_height<=max_l){
            //         bins++;
            //     }else{
            //         if(j<length.length){
            //             j++;
            //         }else{
            //             break;
            //         }
            //     }
            // }

            if(j==bins && max_wd>=width[i] && max_w>=weight[i] && max_l>=length[i] && occupied_height+length[i]<=max_l){
                // code for stacking the items
                rem_wd[bins]=max_wd-width[i];
                rem_w[bins]=weight[i];
                // rem_w[bins]=max_w-weight[i];
                // rem_l[bins]=max_l-length[i];
                occupied_height+=length[i];
                // if(occupied_height<=max_l){
                //     bins++;
                // }else{
                //     if(j<length.length){
                //         j++;
                //     }else{
                //         break;
                //     }
                // }
            }
            else if(j==bins && max_wd>=width[i] && max_w>=weight[i] && max_l>=length[i] && occupied_height+length[i]>=max_l){
                rem_wd[bins]=max_wd-width[i];
                rem_w[bins]=max_w-weight[i];
                // rem_l[bins]=max_l-length[i];
                occupied_height=length[i];
                bins++;
                if(j>length.length){
                    break;
                }
            }
        }
        return bins;   
    }
*/

    public int first_fit_D_H_2D(float[] lengths, float[] widths, float[] weights, float maxL, float maxW, float maxWt) {
        ArrayList<float[]> bins = new ArrayList<>(); // Store used width and weight for each bin.
        ArrayList<ArrayList<float[]>> binStacks = new ArrayList<>(); // Store vertical stacks in each bin.

        for (int i = 0; i < lengths.length; i++) {
            float itemLength = lengths[i];
            float itemWidth = widths[i];
            float itemWeight = weights[i];
            boolean placed = false;

            for (int j = 0; j < bins.size() && !placed; j++) {
                float[] bin = bins.get(j);
                ArrayList<float[]> stacks = binStacks.get(j);

                // Try to fit item in existing stacks.
                for (float[] stack : stacks) {
                    if (stack[0] + itemWidth <= maxW && stack[1] + itemLength <= maxL && bin[1] + itemWeight <= maxWt) {
                        // Fits in current stack.
                        stack[1] += itemLength; // Increase stack height.
                        bin[1] += itemWeight; // Increase bin weight.
                        placed = true;
                        break;
                    }
                }

                // Try to start a new stack in the current bin if the item doesn't fit in any existing stack.
                if (!placed && bin[0] + itemWidth <= maxW && itemLength <= maxL && bin[1] + itemWeight <= maxWt) {
                    stacks.add(new float[]{itemWidth, itemLength}); // New stack.
                    bin[0] += itemWidth; // Increase bin width.
                    bin[1] += itemWeight; // Increase bin weight.
                    placed = true;
                }
            }

            // If item doesn't fit in any existing bin, start a new bin.
            if (!placed) {
                ArrayList<float[]> newStacks = new ArrayList<>();
                newStacks.add(new float[]{itemWidth, itemLength}); // First stack in new bin.
                bins.add(new float[]{itemWidth, itemWeight}); // Initialize new bin.
                binStacks.add(newStacks);
            }
        }

        return bins.size();
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
            // else if(width[i] <= max_wd && weight[i] <= max_w && occupied_height<=max_l){
            //     bins += 1;
            //     rem_wd = max_wd - width[i];
            //     rem_w = max_w - weight[i];
            //     occupied_height+=length[i];
            // }
            else if(width[i] <= max_wd && weight[i] <= max_w){
                if(length[i]<=max_l-occupied_height){
                    occupied_height+=length[i];
                }else{
                    bins += 1;
                }
                rem_wd = max_wd - width[i];
                rem_w = max_w - weight[i];
            }
        }    
        return bins;
    }
    
    public  int best_fit_D_H_2D(float[] lengths, float[] widths, float[] weights, float maxL, float maxW, float maxWt) {
        Integer[] indices = new Integer[lengths.length];
        for (int i = 0; i < lengths.length; i++) {
            indices[i] = i; // Initialize array of indices.
        }

        // Sort items by width in descending order to prioritize wider items.
        Arrays.sort(indices, (a, b) -> Float.compare(widths[b], widths[a]));

        ArrayList<float[]> bins = new ArrayList<>(); // Store width and weight used.
        ArrayList<ArrayList<float[]>> binStacks = new ArrayList<>(); // Store stacks in bins.
        
        for (int index : indices) {
            float itemLength = lengths[index];
            float itemWidth = widths[index];
            float itemWeight = weights[index];

            float bestFitLengthDiff = Float.MAX_VALUE;
            int bestBinIndex = -1;
            float[] bestStack = null;

            for (int i = 0; i < bins.size(); i++) {
                float[] bin = bins.get(i);
                ArrayList<float[]> stacks = binStacks.get(i);

                for (float[] stack : stacks) {
                    // Check if item fits in the current stack horizontally and within weight limit.
                    if (stack[0] + itemWidth <= maxW && bin[1] + itemWeight <= maxWt) {
                        float lengthDiff = maxL - stack[1];

                        // Check if this stack offers a better fit.
                        if (itemLength <= lengthDiff && lengthDiff < bestFitLengthDiff) {
                            bestFitLengthDiff = lengthDiff;
                            bestBinIndex = i;
                            bestStack = stack;
                        }
                    }
                }

                // If no suitable stack, check if a new stack can be started in the current bin.
                if (itemWidth <= maxW - bin[0] && itemLength <= maxL && bin[1] + itemWeight <= maxWt && bestBinIndex == -1) {
                    bestBinIndex = i;
                    bestStack = new float[]{bin[0], 0}; // Start new stack at the end of the bin.
                }
            }

            // Place item in the best bin.
            if (bestBinIndex != -1 && bestStack != null) {
                bestStack[1] += itemLength; // Increase stack height.
                bins.get(bestBinIndex)[0] = Math.max(bins.get(bestBinIndex)[0], bestStack[0] + itemWidth); // Update bin width.
                bins.get(bestBinIndex)[1] += itemWeight; // Update bin weight.

                // If new stack was started, add it to bin.
                if (bestStack[0] == bins.get(bestBinIndex)[0] - itemWidth) {
                    binStacks.get(bestBinIndex).add(new float[]{bestStack[0], itemLength});
                }
            } else {
                // Create a new bin and place item there.
                ArrayList<float[]> newStacks = new ArrayList<>();
                newStacks.add(new float[]{0, itemLength}); // Place item in new stack.
                bins.add(new float[]{itemWidth, itemWeight});
                binStacks.add(newStacks);
            }
        }

        return bins.size();
    }
    /* 
    int best_fit_D_H_2D(Float max_w,Float max_l,Float max_wd,float[] length,float[] weight,float[] width){
        float[] rem_w=new float[weight.length];
        // float[] rem_l=new float[weight.length];
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
    }*/

    int next_fit_D_H_3D(Float max_w,Float max_l,Float max_h,Float max_wd,float[] length,float[] weight,float[] width,float[] height){
        int bins = 0;
        Float rem_w = max_w;
        // Float rem_l = max_l;
        // Float rem_h=max_h;
        Float rem_wd=max_wd;
        // float[] rem_wd=new float[width.length];
        float occupied_height=0;

        ThreeDimension[] array=new ThreeDimension[10];
        for(int i=0;i<10;i++){
           array[i]=new ThreeDimension();
        }
        for(int i=0;i<10;i++){
            array[i].map1.put("length", Float.toString(length[i]));
            array[i].map2.put("width", Float.toString(width[i]));
            array[i].map3.put("height", Float.toString(height[i]));
            array[i].map4.put("weight", Float.toString(weight[i]));
        }
        insertionSortByHeight(array);
        for(int i=0;i<10;i++){
            length[i]=Float.parseFloat(array[i].map1.get("length"));
            width[i]=Float.parseFloat(array[i].map2.get("width"));
            height[i]=Float.parseFloat(array[i].map3.get("height"));
            weight[i]=Float.parseFloat(array[i].map4.get("weight"));
        }
        // here we have to stack items on top of each other,here we have to take help of maps to do that.
        for(int i=0;i<weight.length;i++){
            if (bins == 0){
                bins += 1;
                // rem_l -= length[i];
                rem_wd-=width[i];
                rem_w -= weight[i];
                occupied_height+=length[i];
            }
            else if(width[i] <= rem_wd && weight[i] <= rem_w && height[i]<=max_h){
                rem_wd -= width[i];
                rem_w -= weight[i];
            }
            else if(width[i] <= max_wd && weight[i] <= max_w && occupied_height+length[i]<=max_l && height[i]<=max_h){
                // bins += 1;
                rem_wd = max_wd - width[i];
                rem_w = max_w - weight[i];
                occupied_height+=length[i];
            }else if(width[i] <= max_wd && weight[i] <= max_w && occupied_height+length[i]>=max_l && height[i]<=max_h){
                bins+=1;
                rem_wd = max_wd - width[i];
                rem_w = max_w - weight[i];
                occupied_height=length[i];
            }
        }    
        return bins;
    }

    public int first_fit_D_H_3D(float[] lengths, float[] widths, float[] heights, float[] weights, float maxL, float maxW, float maxH, float maxWt) {
        Integer[] indices = new Integer[lengths.length];
        for (int i = 0; i < lengths.length; i++) {
            indices[i] = i;
        }

        // Sort items by volume in descending order
        Arrays.sort(indices, (a, b) -> Float.compare(lengths[b] * widths[b] * heights[b], lengths[a] * widths[a] * heights[a]));

        ArrayList<float[]> bins = new ArrayList<>();
        ArrayList<ArrayList<float[]>> binSpaces = new ArrayList<>();
        ArrayList<Float> binWeights = new ArrayList<>();

        for (int index : indices) {
            boolean placed = false;
            
            for (int i = 0; i < bins.size(); i++) {
                if (binWeights.get(i) + weights[index] > maxWt) {
                    continue;
                }
                
                ArrayList<float[]> spaces = binSpaces.get(i);

                for (float[] space : spaces) {
                    // Check if item fits in the current space (considering only stacking on top here)
                    if (space[0] + lengths[index] <= maxL && space[1] + widths[index] <= maxW && space[2] + heights[index] <= maxH) {
                        // Update bin space and weight
                        spaces.add(new float[] {space[0], space[1], space[2] + heights[index]});
                        binWeights.set(i, binWeights.get(i) + weights[index]);
                        placed = true;
                        break;
                    }
                }

                if (placed) {
                    break;
                }
            }

            if (!placed) {
                // Create new bin
                ArrayList<float[]> newSpaces = new ArrayList<>();
                newSpaces.add(new float[] {0, 0, heights[index]});  // Stacking on the base
                bins.add(new float[] {maxL, maxW, maxH});
                binSpaces.add(newSpaces);
                binWeights.add(weights[index]);
            }
        }

        return bins.size();
    }

    public int best_fit_D_H_3D(float[] lengths, float[] widths, float[] heights, float[] weights, float maxL, float maxW, float maxH, float maxWt) {
        Integer[] indices = new Integer[lengths.length];
        for (int i = 0; i < lengths.length; i++) {
            indices[i] = i;
        }

        // Sort items by volume in descending order
        Arrays.sort(indices, (a, b) -> Float.compare(lengths[b] * widths[b] * heights[b], lengths[a] * widths[a] * heights[a]));

        ArrayList<float[]> bins = new ArrayList<>();
        ArrayList<ArrayList<float[]>> binSpaces = new ArrayList<>();
        ArrayList<Float> binWeights = new ArrayList<>();

        for (int index : indices) {
            float bestSpaceVolumeDiff = Float.MAX_VALUE;
            int bestBinIndex = -1;
            float[] bestSpace = null;
            float[] newSpace = null;

            for (int i = 0; i < bins.size(); i++) {
                if (binWeights.get(i) + weights[index] > maxWt) {
                    continue;
                }
                
                for (float[] space : binSpaces.get(i)) {
                    if (space[0] + lengths[index] <= maxL && space[1] + widths[index] <= maxW && space[2] + heights[index] <= maxH) {
                        float spaceVolumeDiff = (maxL - space[0] - lengths[index]) * (maxW - space[1] - widths[index]) * (maxH - space[2] - heights[index]);

                        if (spaceVolumeDiff < bestSpaceVolumeDiff) {
                            bestSpaceVolumeDiff = spaceVolumeDiff;
                            bestBinIndex = i;
                            bestSpace = space;
                            newSpace = new float[] {space[0], space[1], space[2] + heights[index]};
                        }
                    }
                }
            }

            if (bestBinIndex != -1) {
                // Place item in the best bin and update space
                binSpaces.get(bestBinIndex).add(newSpace);
                binWeights.set(bestBinIndex, binWeights.get(bestBinIndex) + weights[index]);
            } else {
                // Create new bin
                ArrayList<float[]> newSpaces = new ArrayList<>();
                newSpaces.add(new float[] {0, 0, heights[index]});  // Stacking on the base
                bins.add(new float[] {maxL, maxW, maxH});
                binSpaces.add(newSpaces);
                binWeights.add(weights[index]);
            }
        }

        return bins.size();
    }

}