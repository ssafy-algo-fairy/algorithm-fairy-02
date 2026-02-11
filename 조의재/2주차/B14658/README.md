# [BOJ] 14658 - 하늘에서 별똥별이 빗발친다

### 📌 문제 정보
* **문제 링크**: [백준 14658번](https://www.acmicpc.net/problem/14658)
* **난이도**: Gold 3
* **분류**: 브루트포스 알고리즘

---

### 💡 풀이 전략
1. **입력 처리**: 격자 크기(N, M), 트램펄린 길이(L), 별똥별 수(K)를 입력받고 좌표를 `star` 배열에 저장합니다.
2. **트램펄린 배치**: 이중 `for`문을 통해 두 별똥별을 선택하고, 두 별의 $x, y$ 좌표 중 각각 작은 값을 취해 **트램펄린의 좌하단 시작점**(`minX`, `minY`)으로 설정합니다.
3. **범위 탐색**: `find` 함수를 호출하여 $[minX, minX + L]$ 및 $[minY, minY + L]$ 범위 내에 포함되는 별똥별의 개수를 카운트합니다.
4. **최종 계산**: 전체 개수 $K$에서 최대 카운트 `ans`를 뺀 값을 출력하여 지면에 부딪히는 최소 별똥별 수를 구합니다.

---

### 💻 코드 작성 (Java)
```
import java.io.*;
import java.util.*;

public class Main{
    static StringBuilder sb;
    static int N,M,L,K;
    static int[][] star;
    static int ans;
    public static void main(String args[])throws IOException{
        readInput();
        System.out.println(sb);
    }

    public static void readInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        ans = 0;
        star = new int[K][2];
        for(int k=0; k<K; k++){
            st = new StringTokenizer(br.readLine());

            star[k][0] = Integer.parseInt(st.nextToken()); //x
            star[k][1] = Integer.parseInt(st.nextToken()); //y
        }

        for(int i=1;i<K;i++){
            for(int j=0; j<i; j++){
                // 두 별의 좌표 중 최소값을 기준으로 좌하단 기준점 설정
                int minX = Math.min(star[i][0], star[j][0]);
                int minY = Math.min(star[i][1], star[j][1]);

                int count = find(minX, minY);
                ans = Math.max(ans, count);
            }
        }
        sb.append(K - ans);
    }

    static int find(int x, int y){
        int count = 0;
        for(int i=0; i<K; i++){
            int sx = star[i][0];
            int sy = star[i][1];

            // 기준점(좌하단)으로부터 L 범위 내에 있는지 검사
            if(x<= sx && sx <= x+L && y <= sy && sy <= y+L) count++;
        }

        return count;
    }
}
```
---

### 📝 복기 및 핵심 포인트
* **브루트포스 최적화**: 별똥별의 개수가 최대 100개이므로 $O(K^3)$ 연산으로도 충분히 시간 내 해결이 가능합니다.
* **좌표 기준**: 임의의 두 별을 선택해 형성된 사각형의 가장 왼쪽 아래를 기준으로 트램펄린을 놓음으로써 최적의 해를 탐색합니다.
* **경계 조건**: 별똥별이 트램펄린 모서리에 걸리는 경우를 포함하기 위해 `<=` 연산자를 사용하였습니다.