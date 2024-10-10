package ZeroCount;

import java.util.Scanner;

class Solution{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int L= sc.nextInt();
        int K= sc.nextInt();
        if(L==K){
            System.out.print(0);
            return;
        }
        if(K==0){
            System.out.print(L);
            return;
        }
        if(K>0){
            System.out.print(1);
            return;
        }
    }
}