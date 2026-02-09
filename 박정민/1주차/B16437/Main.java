package B16437;

import java.util.*;
import java.io.*;

public class Main {

	static char[] type;
	static int[] num;
	static LinkedList<Integer>[] children;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		type = new char[n + 1];
		num = new int[n + 1];
		children = new LinkedList[n + 1];
		for (int i = 1; i <= n; i++) {
			children[i] = new LinkedList<>();
		}

		for (int i = 2; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			type[i] = st.nextToken().charAt(0);
			num[i] = Integer.parseInt(st.nextToken());
			children[Integer.parseInt(st.nextToken())].add(i);
		}

		System.out.println(dfs(1));
	}

	public static long dfs(int i) {
		long sheep = 0;

		for (int child : children[i]) {
			sheep += dfs(child);
		}
		
		if (type[i] == 'S') {
			sheep += num[i];
		} else if (type[i] == 'W') {
			sheep -= num[i];
		}

		return (sheep > 0 ? sheep : 0);
	}

}
