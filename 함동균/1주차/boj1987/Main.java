import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main  {
    
	static int[][] delta = {
			{0, 1}, {1, 0}, {0, -1}, {-1, 0}	
	};
	static int[][] map;
	
	static boolean isValid(int i, int j) {
		return i >= 0 && i < map.length && j >= 0 && j < map[0].length;
	}
	
	static int dfs(int[] visit, int i, int j) {
		int max = 0;
		visit[map[i][j] - 'A'] = 1;
		for (int[] d : delta) {
			int di = i + d[0];
			int dj = j + d[1];
			if (isValid(di, dj) && visit[map[di][dj] - 'A'] == 0) {
				int len = dfs(visit, di, dj);
				if (len > max)
					max = len;
			}
		}
		visit[map[i][j] - 'A'] = 0;
		return max + 1;
	}
	
	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	int r = Integer.parseInt(st.nextToken());
    	int c = Integer.parseInt(st.nextToken());
    	map = new int[r][c];
    	for (int i = 0; i < r; i++) {
    		String row = br.readLine();
    		for (int j = 0; j < c; j++) {
    			map[i][j] = row.charAt(j);
    		}
    	}
    	int[] visit = new int[26];
    	System.out.println(dfs(visit, 0, 0));
    }

}