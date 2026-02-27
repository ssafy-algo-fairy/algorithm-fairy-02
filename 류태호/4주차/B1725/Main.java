package B1725;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[] arr;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        System.out.println(solve(0, N - 1));
    }

    // 분할정복으로
    static int solve(int left, int right) {
        if (left == right) {
            return arr[left];
        }

        int mid = (left + right) / 2;
        int area = Math.max(solve(left, mid), solve(mid + 1, right));

        // 겹친 부분도 계산해야함
        int leftIdx = mid;
        int rightIdx = mid + 1;
        // 둘 중 낮은 쪽
        int height = Math.min(arr[leftIdx], arr[rightIdx]);
        // 겹친 부분의 너비가 더 클 수도 있음
        area = Math.max(area, height * 2);

        // 넓혀가며 너비 구하기
        while (leftIdx > left || rightIdx < right) {
            if (rightIdx < right) {
                if (leftIdx == left) {
                    // 왼쪽 끝까지 갔으면 오른쪽으로 진행
                    rightIdx++;
                    height = Math.min(height, arr[rightIdx]);
                } else if (arr[leftIdx - 1] < arr[rightIdx + 1]) {
                    // 높은 쪽으로 진행
                    rightIdx++;
                    height = Math.min(height, arr[rightIdx]);
                } else {
                    // 둘 다 아니면 왼쪽으로 진행
                    leftIdx--;
                    height = Math.min(height, arr[leftIdx]);
                }
            } else {
                // 오른쪽 끝까지 갔으면 왼쪽으로 진행
                leftIdx--;
                height = Math.min(height, arr[leftIdx]);
            }
            // 비교
            area = Math.max(area, height * (rightIdx - leftIdx + 1));
        }
        return area;
    }
}
