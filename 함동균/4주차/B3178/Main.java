import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main  {
	
	static class Trie {
		int size;
		Trie[] child;
		
		public Trie() {
			super();
			size = 0;
			child = new Trie[26];
		}
		
		int input(String str, int idx) {
			int grown = 0;
			if (str.length() == idx)
				return 0;
			int letter = str.charAt(idx) - 'A';
			if (child[letter] == null) {
				grown = 1;
				child[letter] = new Trie();
			}
			grown += child[letter].input(str, idx + 1);
			size += grown;
			return grown;
		}
		
		int rinput(String str, int idx) {
			int grown = 0;
			if (str.length() == idx)
				return 0;
			int letter = str.charAt(str.length() - 1 - idx) - 'A';
			if (child[letter] == null) {
				grown = 1;
				child[letter] = new Trie();
			}
			grown += child[letter].rinput(str, idx + 1);
			size += grown;
			return grown;
		}
		
		int getSize() {
			return size;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		Trie trie = new Trie();
		Trie rtrie = new Trie();
		
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			trie.input(str.substring(0, k), 0);
			rtrie.rinput(str.substring(k), 0);
			str = null;
		}
		System.out.println(trie.getSize() + rtrie.getSize());
	}
}
