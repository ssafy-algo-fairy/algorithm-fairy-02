package com.ssafy.test;

import java.util.Scanner;

public class fairySolution {
	static int max_len=0;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	static void backtracking(int x, int y, int[] cnt, int len, int R, int C, int[][] Map) {
		if (len>max_len) max_len=len;
		for (int i=0; i<4; i++) {
			int nx = x+dx[i], ny = y+dy[i];
			if (nx<0 || nx>R-1 || ny<0 || ny>C-1 || cnt[Map[nx][ny]]==1) continue;
			cnt[Map[nx][ny]]++;
			backtracking(nx,ny,cnt,len+1,R,C,Map);
			cnt[Map[nx][ny]]--;
		}
	}
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int R = sc.nextInt();
        int C = sc.nextInt();
        int[][] Map = new int[R][C];
        for (int i=0; i<R; i++) {
        	String[] s = sc.next().split("");
        	for (int j=0; j<C; j++) {
        		Map[i][j] = s[j].charAt(0) - 'A'; // Map에 0~25로 저장
        	}
        }
        int[] cnt = new int[26];
        cnt[Map[0][0]]++;
        
        backtracking(0,0,cnt,1,R,C,Map); // 백트래킹
        System.out.println(max_len);
    }
}