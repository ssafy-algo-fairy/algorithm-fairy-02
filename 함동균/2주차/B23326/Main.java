import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main  {
	static boolean[] myungso;
	static TreeSet<Integer> tree = new TreeSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		myungso = new boolean[n];
		
		st = new StringTokenizer(br.readLine());
		int now = 0;
		for (int i = 0; i < n; i++) {
			if (Integer.parseInt(st.nextToken()) == 1) {
				tree.add(i);
                myungso[i] = true;
            }
		}
		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			if (cmd.equals("1")) {
				int loc = Integer.parseInt(st.nextToken()) - 1;
				if (myungso[loc]) {
					tree.remove(loc);
				}
				else
					tree.add(loc);
                myungso[loc] = !myungso[loc];
			}
			else if (cmd.equals("2")) {
				int len = Integer.parseInt(st.nextToken());
				now = (now + len) % n;
			}
			else {
				if (tree.size() == 0)
					bw.append("-1").append("\n");
				else {
					Integer next = tree.ceiling(now);
					if (next == null) {
						bw.append(String.valueOf(tree.first() + (n - now))).append("\n");
					}
					else {
						bw.append(String.valueOf(next - now)).append("\n");
					}
				}
			}
		}
		bw.flush();
		bw.close();
    }
}