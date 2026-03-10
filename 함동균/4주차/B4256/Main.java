import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main  {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[] pre;
	static int[] in;
	
	static void post(int pre_s, int pre_e, int in_s, int in_e) throws Exception {
		if (pre_s > pre_e) return;
		int root = pre[pre_s];
		int root_idx = 0;
		
		for (int i = in_s; i <= in_e; i++) {
			if (in[i] == root) {
				root_idx = i;
				break;
			}
		}
		
		int size_left = root_idx - in_s;
		int size_right = in_e - root_idx;
		post(pre_s + 1, pre_s + size_left, in_s, in_s + size_left - 1);
		post(pre_e - size_right + 1, pre_e, in_e - size_right + 1, in_e);
		
		bw.append(root + " ");
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < test; t++) {
			int n = Integer.parseInt(br.readLine());
			pre = new int[n];
			in = new int[n];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				pre[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				in[i] = Integer.parseInt(st.nextToken());
			}
			post(0, n - 1, 0, n - 1);
			bw.append("\n");
		}
		bw.flush();
		bw.close();
	}
}