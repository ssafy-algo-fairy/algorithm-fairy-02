import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] parent;
	static int[] size;
	static int cut;
	static int connect;
	
	static void unite(int a, int b) {
		a = find(a);
		b = find(b);
		if (a == b) {
			cut++;
			return ;
		}
		connect++;
		if (size[a] > size[b])
			parent[b] = a;
		else
			parent[a] = b;
		if (size[a] == size[b]) {
			size[b]++;
		}
	}
	
	static int find(int a) {
		if (parent[a] == a) return a;
		parent[a] = find(parent[a]);
		return parent[a];
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		parent = new int[n + 1];
		size = new int[n + 1];
		cut = 0;
		connect = 0;
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			unite(a, b);
		}
		System.out.println((n - 1) - connect + cut);
	}
}