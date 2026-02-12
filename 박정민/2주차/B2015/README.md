# 📌 백준 2015번 – 수들의 합 4

## 📝 문제 설명

길이가 `N`인 정수 배열이 주어진다.  

이 배열에서 **연속된 부분 수열의 합이 정확히 K가 되는 경우의 수**를 구하라.

---

## 📥 입력

- 첫 줄에 정수 `N`, `K`
- 둘째 줄에 `N`개의 정수

### 제한
- 1 ≤ N ≤ 200,000
- |Ai| ≤ 1,000
- |K| ≤ 1,000,000,000

---

## 📤 출력

합이 K가 되는 연속 부분 수열의 개수 출력

---

# 💡 해결 아이디어

## ❌ 브루트포스 접근

모든 시작점 i에 대해 끝점 j를 탐색하면서 부분합을 구하면  
시간복잡도는 O(N²)이 된다.

N이 최대 200,000이므로 시간 초과가 발생한다.

---

## ✅ Prefix Sum + HashMap 활용 (O(N))

### 🔹 핵심 원리

현재까지의 누적합을 sum이라 하자.

어떤 구간의 합이 K가 되려면:

sum[j] - sum[i] = K

이를 변형하면:

sum[i] = sum[j] - K

즉,  
현재 누적합이 sum일 때  
이전에 (sum - K)가 몇 번 등장했는지를 세어주면 된다.

---

## 🔹 알고리즘 흐름

1. 누적합 변수 sum 선언
2. 경우의 수를 저장할 count 선언
3. HashMap에 (누적합, 등장 횟수) 저장
4. 처음에 map.put(0L, 1) 넣어줌  
   → 처음부터 K가 되는 경우 처리
5. 배열을 순회하면서:
   - 누적합 계산
   - sum - K가 map에 몇 개 있는지 확인
   - 그 개수만큼 count 증가
   - 현재 sum을 map에 추가

---

# 🧠 예시

입력
5 5
1 2 3 2 5

누적합 진행:

index 0 → sum = 1  
index 1 → sum = 3  
index 2 → sum = 6 → (6 - 5 = 1 존재) → 1개 증가  
index 3 → sum = 8 → (8 - 5 = 3 존재) → 1개 증가  
index 4 → sum = 13 → (13 - 5 = 8 존재) → 1개 증가  

총 3개

---

# ⏱ 시간복잡도

- 배열 1회 순회 → O(N)
- HashMap 연산 → 평균 O(1)

✅ 전체 시간복잡도: O(N)

---

# ⚠️ 주의사항

- 누적합은 int 범위를 초과할 수 있으므로 long 사용
- 경우의 수 또한 매우 커질 수 있으므로 long 사용
- map.put(0L, 1)을 반드시 먼저 넣어줘야 함

---

# 💻 코드

```java
long sum = 0;
long count = 0;
HashMap<Long, Integer> map = new HashMap<>();
map.put(0L, 1);

for (int i = 0; i < n; i++) {
    sum += arr[i];
    long toFind = sum - k;
    count += map.getOrDefault(toFind, 0);
    map.put(sum, map.getOrDefault(sum, 0) + 1);
}
