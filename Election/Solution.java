package Election;
import java.util.Scanner;
import java.util.Stack;
class Solution{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        // int n=sc.nextInt();
        // char[] input=new char[n];
        String s=sc.nextLine();
        int n = s.length();
        // System.out.println(input);
        Stack<Character> st=new Stack<>();
        // int count=0;
        int numsOfA=0;
        int numsOfB=0;
        for (int i = 0; i < n; i++){
        
            if(s.charAt(i)=='B'){
                numsOfB++;
                int count=0;
                i++;
                while (i<n && s.charAt(i)=='-' ) {
                    count++;
                    i++;
                }
                if(i>=n){
                    numsOfB=numsOfB+count;
                    break;
                }
                
                if(s.charAt(i)=='A'){
                    numsOfA++;
                    numsOfA+=(count/2);
                    numsOfB+=count/2;
                    count=0;
                    // i++;
                }else if(s.charAt(i)=='B'){
                        numsOfB++;
                        numsOfB+=count;
                        count=0;
                        // i++;
                    
                }
                
            }else if (s.charAt(i)=='-') {
                st.push('-');
            }else{
                numsOfA++;
                while(!st.isEmpty()){
                    if (st.peek()=='-') {
                        st.pop();
                        numsOfA++;
                    }
                }
                
            }
        }
        System.out.println("A: "+numsOfA);
        System.out.println("B: "+numsOfB);
        if(numsOfA>numsOfB){
            System.out.println("A");
        }else if(numsOfB>numsOfA){
            System.out.println("B");
        }else{
            System.out.println("BOTH");
        }
        sc.close();
    }
}

