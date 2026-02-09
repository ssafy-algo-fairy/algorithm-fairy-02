import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main  {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int[] dish = new int[n];
		for (int i = 0; i < n; i++) {
			dish[i] = Integer.parseInt(br.readLine());
		}
		
		int[] selected = new int[d + 1];
		int num_sel = 0;
		int max_sel = 0;
		int start = 0;
		int end = k - 1;
		
		for (int i = start; i <= end; i++) {
			if (selected[dish[i]] == 0) {
				num_sel++;
			}
			selected[dish[i]]++;
		}
		max_sel = Math.max(max_sel, selected[c] == 0 ? num_sel + 1 : num_sel);
		if (n == k) {
			System.out.println(selected[c] == 0 ? max_sel + 1 : max_sel);
			return ;
		}
		
		for (int i = 1; i < n; i++) {
			selected[dish[start]]--;
			if (selected[dish[start]] == 0) {
				num_sel--;
			}
			start = (start + 1) % n;
			end = (end + 1) % n;
			if (selected[dish[end]] == 0) {
				num_sel++;
			}
			selected[dish[end]]++;
			max_sel = Math.max(max_sel, selected[c] == 0 ? num_sel + 1 : num_sel);
		}
		System.out.println(max_sel);
		
    }
}