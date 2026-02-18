import java.io.*;
import java.util.*;

public class Main{
    static StringBuilder sb;

    static Map<String, TreeMap<Integer, Integer>> part;
    static int n, b;
    public static void main(String args[])throws IOException{
        readInput();
        System.out.println(sb);
    }

    public static void readInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while(T --> 0){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            int maxQ = 0;
            int minQ = Integer.MAX_VALUE;

            part = new HashMap<>();

            for(int i=0; i<n; i++){
                st = new StringTokenizer(br.readLine());

                String type = st.nextToken();
                st.nextToken();
                int price = Integer.parseInt(st.nextToken());
                int quality = Integer.parseInt(st.nextToken());

                TreeMap<Integer, Integer> tm = part.computeIfAbsent(type, k -> new TreeMap<>());

                Map.Entry<Integer, Integer> higherEntry = tm.ceilingEntry(quality);
                if(higherEntry != null && higherEntry.getValue() < price ) continue; // 더 좋은 거 있을 때
            
                tm.put(quality, price);

                // 더 안 좋은 거 있을 때
                while(true){
                    Map.Entry<Integer, Integer> lowerEntry = tm.lowerEntry(quality);
                    if(lowerEntry != null && lowerEntry.getValue() >= price){
                        tm.remove(lowerEntry.getKey());
                    }else{
                        break;
                    }
                }

                maxQ = Math.max(maxQ, quality);
                minQ = Math.min(minQ, quality);
            }

            sb.append(find(minQ, maxQ)+"\n");
        }
    }

    static int find(int left, int right){
        int ans = 0;
        while(left <= right){
            int mid = left + (right - left)/2;
            if(check(mid)){
                ans = mid;
                left = mid+1;
            }
            else{
                right = mid-1;
            }
        }
        return ans;
    }

    static boolean check(int targetQual){
        int totalPrice = 0;

        for(TreeMap<Integer, Integer> tm : part.values()){
            Map.Entry<Integer, Integer> entry = tm.ceilingEntry(targetQual);
            if(entry == null) return false;

            totalPrice += entry.getValue();
            if(totalPrice > b) return false;
        }
        return true;
    }
}
