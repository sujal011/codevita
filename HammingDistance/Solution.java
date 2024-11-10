package HammingDistance;


import java.util.Scanner;

class Solution{

    public static int hammingDistance(String original,String reString){
        int distance=0;
        for (int i = 0; i < original.length(); i++) {
            if(original.charAt(i)!=reString.charAt(i)) distance++;
        }
        return distance;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String[] input = new String[n];
        // String[] array = new String[n];
        for (int i = 0; i < n; i++) {
            input[i]=sc.nextLine();
            sc.nextLine();
        }
        for (int i = 0; i < n; i++) {
            String bitString = input[i];
            int countZero = 0;
            int countOne = 0;
            boolean invalidFlag=false;
            for (int j = 0; j < bitString.length(); j++) {
                if(bitString.charAt(j)=='0') countZero++;
                else if(bitString.charAt(j)=='1') countOne++;
                else{
                    invalidFlag=true;
                    break ;

                }

            }
            if (invalidFlag) {
                System.out.print("\n"+"INVALID");
                continue;
            }
            StringBuilder reString1 = new StringBuilder();
            StringBuilder reString2 = new StringBuilder();

            // java 11
            // reString1.append("0".repeat(countZero));
            // reString1.append("1".repeat(countOne));
            // reString2.append("1".repeat(countOne));
            // reString2.append("0".repeat(countZero));

            // java 8 
            for (int j = 0; j < countZero; j++) reString1.append('0');
        for (int j = 0; j < countOne; j++) reString1.append('1');
        
        for (int j = 0; j < countOne; j++) reString2.append('1');
        for (int j = 0; j < countZero; j++) reString2.append('0');

            int ham1 = hammingDistance(bitString,reString1.toString());
            int ham2 = hammingDistance(bitString,reString2.toString());

            System.out.print("\n"+Math.min(ham1, ham2));
        }

        sc.close();
    }
}