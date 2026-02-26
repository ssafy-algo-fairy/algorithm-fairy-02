# [BJ 문제번호] 문제 이름 - Java

### 📋 문제 개요
- **알고리즘 유형**: 완전 탐색 (순열), 그리디 알고리즘 (Greedy)
- **문제 핵심**: 1, 2, 3으로 이루어진 배열을 특정 순서대로 그룹화하기 위해 필요한 **최소 교환(Swap) 횟수**를 구하는 문제입니다. 완성될 배치의 순서가 고정되어 있지 않으므로, 가능한 모든 그룹 배치 순서를 확인하여 최적의 해를 찾습니다.

---

### 💡 풀이 로직

1. **데이터 구조 및 초기화**: 
   - `arr[]`: 초기 배열의 상태를 저장합니다.
   - `numCnt[]`: 입력 배열에서 1, 2, 3이 각각 몇 개씩 있는지 카운트합니다.
   - `selected[]`: 현재 탐색 중인 그룹의 배치 순서(순열)를 저장합니다.
2. **완전 탐색 (순열)**: 
   - 1, 2, 3이 배치될 수 있는 모든 경우의 수($3! = 6$가지)를 `permutation` 메서드를 통해 탐색합니다.
3. **구역 설정 및 오배치 카운팅**: 
   - `solve()` 내부에서 `numCnt`를 이용해 전체 배열을 3개의 타겟 구역으로 나눕니다.
   - 각 구역(`i`)을 순회하며, 해당 구역에 실제로는 어떤 숫자(`arr[j]`)가 위치해 있는지 `rangeNum[구역][숫자]`에 카운트합니다.
4. **그리디 기반 교환 횟수 계산**: 
   - **직접 교환 (2-way Swap)**: 1구역에 2가 있고, 2구역에 1이 있는 상황처럼 서로 교환하면 바로 제자리를 찾는 경우입니다. `Math.min`을 통해 짝이 맞는 최대 개수를 구해 `totalChange`에 더합니다.
   - **순환 교환 (3-way Swap)**: 1구역에 2, 2구역에 3, 3구역에 1이 있는 것처럼 세 숫자가 맞물린 경우입니다. 잘못 배치된 전체 원소(`cnt`)에서 2-way Swap 처리된 개수를 빼고, 3-way Swap은 한 사이클당 2번의 교환이 필요하므로 `(남은 개수 / 3) * 2`로 계산합니다.

---

### 💻 코드 주요 부분 설명
```java
static int solve(){
    int rangeNum[][] = new int[4][4];

    // 1. 구역별 오배치 숫자 카운팅
    int idx = 1;
    for(int i=1; i<=3; i++){
        int targetNum = selected[i];
        // 현재 타겟 숫자가 차지해야 할 구역 내에 실제 들어있는 숫자들을 카운트
        for(int j=idx; j<idx+numCnt[targetNum]; j++){
            rangeNum[i][arr[j]]++;
        }
        idx += numCnt[targetNum]; // 다음 구역 시작 인덱스 갱신
    }

    // 2. 2-way Swap (1번의 교환으로 2개가 제자리를 찾는 경우)
    int change12 = Math.min(rangeNum[1][selected[2]], rangeNum[2][selected[1]]);
    int change13 = Math.min(rangeNum[1][selected[3]], rangeNum[3][selected[1]]);
    int change23 = Math.min(rangeNum[2][selected[3]], rangeNum[3][selected[2]]);
    
    int totalChange = (change12 + change13 + change23);

    // 3. 제자리에 있지 않은 전체 원소 개수 카운팅
    int cnt = 0;
    for(int i=1; i<=3; i++){
        for(int j=1; j<=3; j++){
            if(selected[i] == j) continue; // 올바른 자리에 있는 경우 패스
            cnt += rangeNum[i][j];
        }
    }

    // 4. 3-way Swap (2번의 교환으로 3개가 제자리를 찾는 경우)
    cnt = cnt - (totalChange) * 2; // 2-way 직접 교환으로 해결된 원소들 제외
    cnt = (cnt / 3) * 2;           // 남은 원소들은 3-way 순환 구조이므로 / 3 * 2
    
    return totalChange + cnt;      // 총 교환 횟수 반환
}
```
