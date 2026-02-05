import java.util.*;
import java.lang.*;
import java.io.*;

public class boj14502 {
	static int N;
	static int M;

	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};

	static String[][] map;

	static int max = 0;

	static List<int[]> empty = new ArrayList<>();
	static List<int[]> virus = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new String[N][M];
		for(int i=0; i<N; i++){
			map[i] = br.readLine().split(" ");
		}

		for(int i=0;i<N;i++){
			for(int j=0;j<M;j++){
				if(map[i][j].equals("0")) empty.add(new int[]{i,j});
				if(map[i][j].equals("2")) virus.add(new int[]{i,j});
			}
		}

		comb(0, 0, new int[3][2]);

		System.out.println(max);
	}

	// 벽 세우는 조합
	static void comb(int start, int count, int[][] selected){
		if(count == 3){
			simulate(selected);
			return;
		}

		for(int i=start;i<empty.size();i++){
			selected[count] = empty.get(i);
			comb(i+1, count+1, selected);
		}
	}


	// 벽 설치 후 바이러스 전파 시뮬
	static void simulate(int[][] walls){
		String[][] tmp = new String[N][M];
		for(int i=0;i<N;i++){
			tmp[i] = map[i].clone();
		}

		// 벽 설치
		for(int[] w : walls){
			tmp[w[0]][w[1]] = "1";
		}

		// 바이러스 확산
		for(int[] v : virus){
			spread(v[0], v[1], tmp);
		}

		getSafeArea(tmp);
	}

	// 바이러스 전파
	static void spread(int x, int y, String[][] tmp){
		for(int d=0; d<4; d++){
			int nx = x + dx[d];
			int ny = y + dy[d];

			if(nx<0 || ny<0 || nx>=N || ny>=M) continue;
			if(tmp[nx][ny].equals("0")){
				tmp[nx][ny] = "2";
				spread(nx, ny, tmp);
			}
		}
	}

	// safe Area 구하기
	static void getSafeArea(String[][] map){
		int count = 0;
		for(int i=0;i<N;i++){
			for(int j=0;j<M;j++){
				if(map[i][j].equals("0")) count++;
			}
		}

		max = Math.max(count, max);
	}
}