import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int R, C;
	static char[][] arr;
	static boolean[] alphabet; 
	static int answer = 0;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new char[R][C];
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				arr[i][j] = line.charAt(j);
			}
		}
		
		alphabet = new boolean[26]; // 지나온 알파벳인지
		alphabet[arr[0][0] - 'A'] = true; // 좌측 상단 알파벳 방문 먼저 하고
		dfs(0, 0, 1); // 탐색 시작
		System.out.println(answer);
	}

	static void dfs(int x, int y, int depth) {
		answer = Math.max(answer, depth);
		
		for (int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if (0 <= nx && nx < R && 0 <= ny && ny < C) {
				int idx = arr[nx][ny] - 'A';
				if (!alphabet[idx]) {
					alphabet[idx] = true; // 해당 알파벳 선택
					dfs(nx, ny, depth + 1); // 탐색
					alphabet[idx] = false; // 되돌리기(백트래킹)
				}
			}
		}
		
	}


}
