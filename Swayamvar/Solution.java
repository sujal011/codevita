
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Groom{
    char groom;
    int brideRejected;
    Groom(char groom,int brideRejected){
        this.groom=groom;
        this.brideRejected=brideRejected;
    }
    // public char getGroom(){
    //     return groom;
    // }
}

class Solution{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String brides=sc.nextLine();
        // sc.nextLine();
        String grooms=sc.nextLine();

        Queue<Groom> groomQueue = new LinkedList<Groom>();
        for (int i = 0; i < n; i++) {
            groomQueue.add(new Groom(grooms.charAt(i),-1));
        }
        Queue<Character> brideQueue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            brideQueue.add(brides.charAt(i));
        }

        int count=0;
        int brideCount=0;
        // char rejected = 'a';
        while (!brideQueue.isEmpty() && !groomQueue.isEmpty()) {
            if(groomQueue.peek().brideRejected==brideCount){
                break;
            }
            if(groomQueue.peek().groom==brideQueue.peek()){
                groomQueue.poll();
                brideQueue.poll();
                count++;
                brideCount++;
            }
            else{
                char groom = groomQueue.poll().groom;
                groomQueue.add(new Groom(groom, brideCount));
                // if(rejected==groomQueue.peek()){
                //     break;
                // }else{
                //     rejected=groomQueue.peek();
                // }
            }
        }

        System.out.print(n-count);
        sc.close();
    }
}