package com.ssafy.test;

import java.util.*;
import java.io.*;

public class fairySolution {

	static int N, M, Q;
	
	static ArrayList<String> folderList = new ArrayList<>();
	static ArrayList<String> fileList = new ArrayList<>();
	
	static HashMap<String, Integer> folderIdx = new HashMap<>();
    static HashMap<String, Integer> fileIdx = new HashMap<>();
    
	static int[] folderIn;
	static int[][] fileCnt;
	static ArrayList<String>[] goTo;
	
	static String[] queryList;

	public static void main(String args[]) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		folderIn = new int[N + 1]; // main 포함 폴더
		fileCnt = new int[N + 1][M]; // 파일 종류별 카운트
		goTo = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) goTo[i] = new ArrayList<>(); // 동적 할당
		
		folderList.add("main");
        folderIdx.put("main", 0);
		
		for (int i = 0; i < N + M; i++) {
			st = new StringTokenizer(br.readLine());
			String P = st.nextToken(); // 상위 폴더 이름
			String F = st.nextToken(); // 폴더 또는 파일의 이름
			int C = Integer.parseInt(st.nextToken()); // 0 파일 1 폴더
			
			int pId = getFolderId(P); // 부모 폴더가 아직 안 나온 경우 고려
			
			if (C == 0)
				getFileId(F);
			else
				getFolderId(F);
			goTo[pId].add(F);
		}

		searchFile(0);

		Q = Integer.parseInt(br.readLine());
		for (int i = 0; i < Q; i++) {
			queryList = br.readLine().split("/");
			String folderName = queryList[queryList.length - 1];
			int folderNum = (folderName.equals("main")) ? 0 : folderIdx.get(folderName);
			
			int cnt1 = 0, cnt2 = 0; // 파일 종류 개수, 파일 총 개수
			for (int j = 0; j < M; j++) {
				if (fileCnt[folderNum][j] != 0)
					cnt1++;
				cnt2 += fileCnt[folderNum][j];
			}
			System.out.println(cnt1 + " " + cnt2);
		}

	}
	
	static int getFolderId(String name) {
        Integer id = folderIdx.get(name);
        if (id != null) return id;

        int newId = folderList.size();

        folderList.add(name);
        folderIdx.put(name, newId);
        return newId;
    }

    static int getFileId(String name) {
        Integer id = fileIdx.get(name);
        if (id != null) return id;

        int newId = fileList.size();

        fileList.add(name);
        fileIdx.put(name, newId);
        return newId;
    }
    
	static void searchFile(int cur) {
		folderIn[cur] = 1;
		
		for (String s : goTo[cur]) {
			if (folderList.contains(s)) { // 폴더인 경우
				searchFile(folderList.indexOf(s));
			} else { // 파일인 경우
				for (int i = 0; i < N + 1; i++) {
					if (folderIn[i] == 1)
						fileCnt[i][fileList.indexOf(s)]++;
				}
			}
		}
		
		folderIn[cur] = 0;
	}

}