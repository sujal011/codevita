package PrimeTimeAgain;

import java.util.Scanner;

class Solution{
    public static boolean isprime(int n)
    {
        if(n==1) return false;
        for(int i=2;i<=(int) Math.sqrt(n) ; i++){
          if(n%i==0)
            return false;
        }
        return true;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int totalHours = sc.nextInt();
        int P = sc.nextInt();
        int p = totalHours/P;
        int t=1;
        int count=0;
        int time[][]=new int [p][P];
       for (int i = 0; i < P; i++) {
        for (int j = 0; j < p; j++) {
            time[j][i]=t++;
        }
       }
       for (int i = 0; i < p; i++) {
        boolean flag = true;
        for (int j = 0; j < P; j++) {
            if (!isprime(time[i][j])) {
                flag=false; break;
            }
        }
        if(flag) count++;
        
       }
       System.out.println(count);
        
        sc.close();
    }
}
