package Mario;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class Solution{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        List<List<Character>> matrix = new ArrayList<>();

        
        for (int i = 0; i < m; i++) {
            String line = sc.next();
            List<Character> row = new ArrayList<>();
            for(int j=0;j<n;j++){
                row.add(line.charAt(j));
            }
            matrix.add(row);

        }
        int cal=0;
        int coins=0;
        for (int i = 0; i < n; i++) {
            int temp=-1;
            for (int j = 0; j < m; j++) {
                if(matrix.get(j).get(i)=='C'){
                    temp=m-j-i;
                    break;
                }else if(matrix.get(j).get(i)=='H'){
                    if(temp!=0) continue;
                    else temp=m-j;
                    
                }else if (matrix.get(j).get(i)=='0') {
                    temp=0;
                }
            }
            cal+=temp;

        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(matrix.get(i).get(j)=='C') coins++;
            }
        }
        
        
        System.out.println(coins+" "+cal*2);
        sc.close();
    }
}