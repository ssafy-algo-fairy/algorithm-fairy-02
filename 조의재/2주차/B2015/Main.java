import java.io.*;
import java.util.*;

public class Main{
    //static StringBuilder sb;
    static Long N,K;

    public static void main(String args[])throws IOException{
        readInput();
    }

    public static void readInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //sb = new StringBuilder();
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        K = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        HashMap<Long, Long> map = new HashMap<>();
        long total = 0;
        long ans = 0;
        map.put(0L, 1L);
        for(int i=1; i<=N; i++){
            total += Long.parseLong(st.nextToken());

            ans += map.getOrDefault(total - K, 0L);

            map.put(total, map.getOrDefault(total, 0L) +1);
        }

        System.out.println(ans);
    }
}
