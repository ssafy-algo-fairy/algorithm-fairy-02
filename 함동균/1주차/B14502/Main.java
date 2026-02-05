import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main  {
    static int[][] map = new int[8][8];
    static int[][] virus = new int[10][];
    static int virus_n = 0;
    static int n;
    static int m;
    static int virus_max;
    static int max = 0;
    static int safe = 0;
    
    static void clear() {
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < m; j++) {
    			if (map[i][j] == 2)
    				map[i][j] = 0;
    		}
    	}
    	for (int i = 0; i < virus_n; i++) {
    		int[] v = virus[i];
    		map[v[0]][v[1]] = 2;
    	}
    }
    
    static int[][] delta = { 
    		{1, 0}, {0, 1}, {-1, 0}, {0, -1}	
    };
    
    static boolean isValid(int i, int j) {
    	return i >= 0 && i < n && j >= 0 && j < m;
    }
    
    static void dfs(int i, int j) {
    	if (safe - virus_max - 3 <= max)
    		return ;
    	for (int[] d : delta) {
    		int di = i + d[0];
    		int dj = j + d[1];
    		if (isValid(di, dj) && map[di][dj] == 0) {
    			map[di][dj] = 2;
    			virus_max++;
    			dfs(di, dj);
    		}
    	}
    }
    
    static int check() {
    	virus_max = 0;
    	for (int i = 0; i < virus_n; i++) {
    		dfs(virus[i][0], virus[i][1]);
    	}
    	
    	return safe - virus_max - 3;
    }
    
    static void wall(int i, int j, int w) {
    	if (w == 3) {
    		int num = check();
    		if (num > max) {
    			max = num;
    		}
    		clear();
    		return ;
    	}
    	for (int k = j; k < m; k++) {
    		if (map[i][k] == 0) {
    			map[i][k] = 1;
    			wall(i, k, w + 1);
    			map[i][k] = 0;
    		}
    	}
    	for (int k = i + 1; k < n; k++) {
    		for (int l = 0; l < m; l++) {
    			if (map[k][l] == 0) {
        			map[k][l] = 1;
        			wall(k, l, w + 1);
        			map[k][l] = 0;
        		}
    		}
    	}
    }
    
    
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					virus[virus_n++] = new int[] {i, j};
				}
				else if (map[i][j] == 0)
					safe++;
			}
		}
		
		wall(0, 0, 0);
		System.out.println(max);
    }

}