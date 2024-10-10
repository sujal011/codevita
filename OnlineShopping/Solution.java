package OnlineShopping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

class Item{
    String name;
    int cost;

    public Item(String name,int cost){
        this.name=name;
        this.cost=cost;
    }
    @Override
    public String toString() {
        
        return "{"+this.name+": "+this.cost+"}";
    }
}

public class Solution{

    public static int findKsum(int[] tokens,int[] claimed,int n,int k){
        int max_sum = 0;
        for (int i = 0; i + k <= n; i++) {
            int temp = 0;
            for (int j = i; j < i + k; j++) {
                if(claimed[j]==0){
                    temp += tokens[j];
                }
            }
            if (temp > max_sum)
                max_sum = temp;
        }

        return max_sum;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] tokens=new int[n];
        int collected = 0;
        for (int i = 0; i < n; i++) {
            tokens[i]=sc.nextInt();
        }
        int countOnes = 0;
        int[] claimed=new int[n];
        for (int i = 0; i < n; i++) {
            claimed[i]=sc.nextInt();
            if (claimed[i]==1) {
                countOnes++;
                collected+=tokens[i];
            }
        }
        
        int countZeros = n-countOnes;
        int k=sc.nextInt();
        sc.nextLine();
        String itemsString =sc.nextLine();
        List<Item> items = new ArrayList<Item>();
        
        StringTokenizer tokenizer = new StringTokenizer(itemsString, ": ");
        while (tokenizer.hasMoreTokens()) {
            String itemName = tokenizer.nextToken();
            int itemCost = Integer.parseInt(tokenizer.nextToken());
            items.add(new Item(itemName, itemCost));
        }
        System.out.println(collected);
        collected+=findKsum(tokens, claimed, n, k);
        // Map<String,Integer> itemsMap = new HashMap<>();

        // for (Item item : items) {
        //     if(item.cost<=collected){
        //         itemsMap.put(item.name, item.cost);
        //     }
        // }
        List<String> result = new ArrayList<>();
        int min = collected;
        for (Item item : items) {
            if (item.cost<=collected) {
                int wastage = collected - (item.cost);
                if(wastage<min){
                    min = wastage;
                    result.clear();
                    result.add(item.name);

                }else if(wastage == min){
                    result.add(item.name);
                }
            }
        }
        // System.out.println(items);
        // System.out.println(countOnes);
        // System.out.println(countZeros);
        // System.out.println(collected);
        for (String name : result) {
            System.out.print(name);
        }
        sc.close();
    }
}