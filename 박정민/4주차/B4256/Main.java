package week4.B4256;

import java.util.*;
import java.io.*;

public class Main {
	
	static int n, index;
	static int[] preorder, inorder;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());
			
			preorder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			inorder = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			Node root = new Node(preorder[0]);
			index = 1;
			int rootNum = 0;
			
			for (int i = 0; i < n; i++) {
				if (inorder[i] == preorder[0]) {
					rootNum = i;
					break;
				}
			}
			
			makeTree(root, 0, rootNum, n);
			postorder(root);
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static class Node {
		int value;
		Node left;
		Node right;
		
		public Node(int value) {
			this.value = value;
		}
		
		public void setLeft(Node n) {
			this.left = n;
		}
		
		public void setRight(Node n) {
			this.right = n;
		}
		
		public Node getLeft() {
			return left;
		}
		
		public Node getRight() {
			return right;
		}
	}
	
	public static void makeTree(Node root, int start, int rootNum, int end) {
		
		// left
		for (int i = start; i < rootNum; i++) {
			if (inorder[i] == preorder[index]) {
				Node left = new Node(inorder[i]);
				root.setLeft(left);
				index++;
				makeTree(left, start, i, rootNum);
				break;
			}
		}
		
		// right
		for (int i = rootNum+1; i < end; i++) {
			if (inorder[i] == preorder[index]) {
				Node right = new Node(inorder[i]);
				root.setRight(right);
				index++;
				makeTree(right, rootNum+1, i, end);
				break;
			}
		}
	}
	
	public static void postorder(Node root) {
		if (root.left != null) postorder(root.getLeft());
		if (root.right != null) postorder(root.getRight());
		sb.append(root.value + " ");
		
	}

}
