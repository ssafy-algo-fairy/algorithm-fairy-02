import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main  {
	static class Node {
		String animal;
		int num;
		List<Integer> child;
	}
	
	static Node[] tree;
	
	static long solve(int idx) {
		long max = 0;
		
		if (idx != 1 && tree[idx].animal.equals("W"))
			max = -tree[idx].num;
		else
			max = tree[idx].num;
		for (Integer i : tree[idx].child) {
			max += solve(i);
		}
		return max > 0 ? max : 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		tree = new Node[n + 1];
		for (int i = 1; i <= n; i++) {
			tree[i] = new Node();
			tree[i].child = new LinkedList<>();
		}
		for (int i = 2; i <= n; i++) {
			StringTokenizer st  = new StringTokenizer(br.readLine());
			tree[i].animal = st.nextToken();
			tree[i].num = Integer.parseInt(st.nextToken());
			
			int parent = Integer.parseInt(st.nextToken());
			tree[parent].child.add(i);
		}
		System.out.println(solve(1));

    }
}