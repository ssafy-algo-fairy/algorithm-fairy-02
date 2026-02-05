# [BJ 15961] 회전 초밥 - Java

### 📋 문제 개요
- **알고리즘 유형**: 슬라이딩 윈도우 (Sliding Window), 두 포인터
- **문제 핵심**: 회전하는 벨트 위에서 연속된 $k$개의 접시를 선택했을 때, 쿠폰 서비스(`c`)를 포함하여 먹을 수 있는 **최대 초밥 가짓수**를 구하는 문제입니다.

---

### 💡 풀이 로직
1. **데이터 구조**: 
   - `sushi[]`: 벨트 위의 초밥 번호를 저장합니다.
   - `type[]`: 현재 선택한 범위(윈도우) 내에 있는 초밥 종류별 개수를 저장하는 카운팅 배열입니다.
2. **슬라이딩 윈도우**: 
   - 초기에 $0$번 인덱스부터 $k$개의 초밥을 조사하여 `typeCnt`를 설정합니다.
   - 이후 한 칸씩 이동하며 `제거되는 초밥(Poll)`의 카운트를 감소시키고, `새로 들어오는 초밥(Add)`의 카운트를 증가시킵니다.
3. **원형 배열 처리**: 벨트의 끝과 처음이 연결되어 있으므로 인덱스 접근 시 `i % N` 연산을 사용하여 범위를 유지합니다.
4. **쿠폰 처리**: 매 단계마다 `type[c] == 0`인지를 확인하여, 현재 선택한 초밥 중에 쿠폰 초밥이 없다면 가짓수에 $+1$을 더해 최댓값을 갱신합니다.



---

### 💻 코드 주요 부분 설명
```java
public static void findAnswer() {
    Queue<Integer> que = new LinkedList<>();
    // 1. 첫 번째 윈도우 설정 (0 ~ k-1)
    for(int i=0; i<k; i++){
        if(type[sushi[i]]++ == 0) typeCnt++;
        que.add(sushi[i]);
    }

    // 초기 상태에서 최대 가짓수 계산 (쿠폰 보너스 포함)
    ans = Math.max(ans, typeCnt + (type[c] == 0 ? 1 : 0));

    // 2. 윈도우 한 칸씩 이동
    for (int i = k; i < N + k - 1; i++) {
        int sushiIdx = i % N; // 원형 배열 인덱스
        
        // 새로운 초밥 추가
        if(0 == type[sushi[sushiIdx]]++) typeCnt++;
        que.add(sushi[sushiIdx]);

        // 가장 오래된 앞쪽 초밥 제거
        int removeNum = que.poll();
        if(0 == --type[removeNum]) typeCnt--;

        // 쿠폰 번호(c)가 현재 윈도우에 없으면 +1 하여 최댓값 비교
        ans = Math.max(ans, typeCnt + (type[c] == 0 ? 1 : 0));
    }
}