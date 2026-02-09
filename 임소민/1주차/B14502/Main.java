import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N, M;
    static int[][] origin;
    static ArrayList<int[]> emptyPlaces; // 벽 세울 수 있는 빈칸들
    static int[][] temp;
    static int answer;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        origin = new int[N][M];
        emptyPlaces = new ArrayList<>();
        for (int i = 0; i < N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j = 0; j < M; j++) {
        		origin[i][j] = Integer.parseInt(st.nextToken());
        		if (origin[i][j] == 0) emptyPlaces.add(new int[] {i, j});
        	}
        }
        
        buildWalls(0, 0);
        
        System.out.println(answer);
    }
    
    // 1. 벽 세우기: 재귀 + 백트래킹
    static void buildWalls(int cnt, int start) { // start-> 순열 X 조합 O (start 이후 칸 탐색)
    	if (cnt == 3) { // 벽 3개 다 세우면
    		temp = new int[N][M]; // 임시 맵 복사
    		for (int i = 0; i < N; i++) {
    			temp[i] = origin[i].clone(); // 깊은 복사
    		}
    		spreadVirus();
    		int safeCnt = countSafeZone();
    		answer = Math.max(answer, safeCnt);
    		return;
    	}
    	
    	for (int i = start; i < emptyPlaces.size(); i++) {
    		int[] place = emptyPlaces.get(i);
    		int x = place[0];
    		int y = place[1];
    		
    		origin[x][y] = 1;
    		buildWalls(cnt + 1, i + 1);
    		origin[x][y] = 0; // 백트래킹
    	}
    }
    
 
    // 2. 바이러스 퍼뜨리기: BFS
    static void spreadVirus() {
    	boolean[][] visited = new boolean[N][M];
    	Queue<int[]> queue = new ArrayDeque<>();
    	
    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < M; j++) {
    			if (temp[i][j] == 2 && !visited[i][j]) {
    				queue.add(new int[] {i, j});
    				visited[i][j] = true;
    				
    				while (!queue.isEmpty()) {
    					int[] now = queue.poll();
    					
    					for (int d = 0; d < 4; d++) {
    						int nx = now[0] + dx[d];
    						int ny = now[1] + dy[d];
    						
    						if (0 <= nx && nx < N && 0 <= ny && ny < M) {
        						if (temp[nx][ny] == 0) {
        							temp[nx][ny] = 2;
        							queue.add(new int[] {nx, ny});
        							visited[nx][ny] = true;
        						}
        					}
    					}
    				}

    			}
    		}
    	}
    }
    
    // 3. 안전 영역 크기 구하기: BFS
    static int countSafeZone() {
    	int cnt = 0;
    	
    	boolean[][] visited = new boolean[N][M];
    	Queue<int[]> queue = new ArrayDeque<>();

    	for (int i = 0; i < N; i++) {
    		for (int j = 0; j < M; j++) {
    			if (temp[i][j] == 0 && !visited[i][j]) {
    				queue.add(new int[] {i, j});
    		    	visited[i][j] = true;
    		    	cnt++;
    		    	
    		    	while (!queue.isEmpty()) {
    		    		int[] now = queue.poll();
    		    		
    		    		for (int d = 0; d < 4; d++) {
    		    			int nx = now[0] + dx[d];
    		    			int ny = now[1] + dy[d];
    		    			
    		    			if (0 <= nx && nx < N && 0 <= ny && ny < M) {
    		    				if (temp[nx][ny] == 0 && !visited[nx][ny]) {
    		    					queue.add(new int[] {nx, ny});
    		    					visited[nx][ny] = true;
    		    					cnt++;
    		    				}
    		    			}
    		    		}
    		    	}
    			}
    		}
    	}
    	
    	return cnt;
    }
  
}
