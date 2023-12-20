Case 1: One Dimension Length/Width/Height with Weight

Test 1: 1k rows uniform
Test 2: 5k rows
Dataset: boxdataset-5k.csv
Input1= length>30.6 on a normal curve with x-axis as length. filename: 1_D_Test2_Input_1
Input 2 = length>32.3, width>23.4 height>17.6
Datasets: 1_D_Test2_input_Length.csv,1_D_Test2_input_Width.csv,1_D_Test2_input_Height.csv

Test 3: Dataset selected on basis of distribution of weight in the boxdataset-20k.csv name:1_D_Test3_onput_WL150
1 datasets having weight<150 

test 4: Dataset selected boxdataset-100k.csv where weight>200
name=1_D_Test4_input_WG200


Approach:

1) get range of values for the length, weight and height

generate length values 500 times

length within range   6.0093178<L< 45.7847091

wdith within range  3.2811031<W<30.9996443

height within range 1.0124164<H<35.9998188


max_length in range 6.0093178 < max_length < 44.3

max_weight in range  30 < max_weight < 530


generate 10 values for each range 500 times

we get 500 values as output or required bins.


Range Length :

6.0093178<=L<=16

16<L<=26

26<L<=36

36<L<=45.7847091