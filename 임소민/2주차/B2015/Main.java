import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        long K = Long.parseLong(st.nextToken());
        
        long answer = 0;
        
        long[] prefix = new long[N + 1];
        
        Map<Long, Integer> map = new HashMap<>();
        map.put(0L, 1); // prefix[0] 처리
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
        	long num = Long.parseLong(st.nextToken());
        	prefix[i] = prefix[i - 1] + num; 
        	
        	// [i, j] 구간합 = prefix[j] - prefix[i - 1]
        	// -> prefix[now] - prefix[past] = K
        	// -> prefix[past] = prefix[now] - K
        	// i 이전에 prefix[now] - K 랑 같은 값 몇 개 있는지
        	answer += map.getOrDefault(prefix[i]- K , 0);
        	
        	map.put(prefix[i], map.getOrDefault(prefix[i], 0) + 1);
        }
        
        
        System.out.println(answer);
    }
    
}
