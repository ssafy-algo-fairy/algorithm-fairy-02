import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main  {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int n = Integer.parseInt(st.nextToken());
    	int k = Integer.parseInt(st.nextToken());
    	
    	int[] dp = new int[k + 1];
    	st = new StringTokenizer(br.readLine());
		int w = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());
    	for (int i = w; i <= k; i++) {
    		dp[i] = v;
    	}
    	for (int i = 1; i < n; i++) {
    		st = new StringTokenizer(br.readLine());
    		w = Integer.parseInt(st.nextToken());
    		v = Integer.parseInt(st.nextToken());
    		for (int j = k; j >= w; j--) {
    			dp[j] = Math.max(dp[j], dp[j - w] + v);
    		}
    	}
    	System.out.println(dp[k]);
    }

}