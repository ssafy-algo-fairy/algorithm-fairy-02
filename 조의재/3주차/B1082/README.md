# 🔢 [백준 1082] 방 번호 - Java 풀이 분석

## 1. 문제 정보
* **문제 번호**: 1082
* **알고리즘**: 그리디 (Greedy)
* **핵심**: 주어진 예산 내에서 가장 자릿수가 길고, 높은 숫자가 앞에 오는 번호 만들기

---
## 2. 핵심 로직 분석

### 💡 그리디 전략 (Greedy Strategy)
가장 큰 숫자를 만들기 위해 다음의 단계별 최적화를 수행합니다.

1.  **자릿수 최대화**: 숫자의 크기는 무조건 자릿수가 많은 것이 유리합니다. 따라서 가장 가격이 낮은 숫자를 골라 최대한 긴 숫자를 만듭니다.
2.  **첫 자릿수 예외 처리**: 숫자는 `0`으로 시작할 수 없습니다. 가장 싼 숫자가 `0`이라면, `0`이 아닌 숫자 중 가장 저렴한 것을 첫 번째 자리에 배치합니다. 만약 다른 숫자를 살 예산이 없다면 답은 `0`이 됩니다.
3.  **상위 자릿수 교체**: 확보된 자릿수 내에서, 앞쪽 인덱스(높은 자릿수)부터 차례대로 남은 예산을 더해 살 수 있는 가장 큰 숫자(`N-1`부터 확인)로 교체합니다.

### 🚀 시간 및 공간 복잡도
* **시간 복잡도**: $O(N + L \times N)$
    * $N$은 숫자의 종류(최대 50), $L$은 완성된 번호의 자릿수(최대 50)입니다.
    * 자릿수마다 전체 숫자를 역순으로 한 번씩 확인하므로 약 $50 \times 50 = 2,500$번의 연산으로 매우 빠르게 동작합니다.
* **공간 복잡도**: $O(N + L)$
    * 가격 정보를 저장하는 배열 $O(N)$과 결과 숫자를 담는 `StringBuilder` $O(L)$ 공간을 사용하므로 효율적입니다.


## 3. 전체 소스 코드

```java
    public static void solution(){
        int minIdx = 0;
        int totalMoney = M;

        // 예외 처리: 숫자가 하나뿐인 경우
        if(N == 1) {
            sb.append("0");
            return;
        }
        
        // 전체 중 가장 저렴한 숫자 찾기
        for(int i=1; i<N; i++){
            if(price[minIdx] >= price[i]) minIdx = i;
        }
        
        // 가장 저렴한 숫자가 0인 경우 처리
        if(minIdx == 0){
            // 0을 제외하고 가장 저렴한 숫자 찾기
            int secondMinIdx = 1;
            for(int i=2; i<N; i++){
                if(price[secondMinIdx] >= price[i]) secondMinIdx = i;
            }

            // 가장 싼 숫자가 0인데, 0이 아닌 다른 숫자를 살 돈이 없는 경우
            if(totalMoney < price[secondMinIdx]){
                sb.append("0");
                return;
            }
            
            // 첫 번째 자릿수로 0이 아닌 가장 싼 숫자 배치
            sb.append(secondMinIdx);
            totalMoney -= price[secondMinIdx];
        }

        // 남은 돈으로 가장 저렴한 숫자를 최대한 붙여 자릿수 확보
        int length = totalMoney / price[minIdx];
        for(int i=0; i<length; i++){
            sb.append(minIdx);
            totalMoney -= price[minIdx];
        }

        // 그리디 수정: 앞에서부터 최대한 큰 숫자로 교체 시도
        int sbLen = sb.length();
        for(int i=0; i < sbLen; i++){
            for(int j=N-1; j>=0; j--){
                int currentPrice = price[sb.charAt(i)-'0'];
                
                // 현재 숫자보다 큰 숫자로 바꿀 수 있는지 체크
                if(totalMoney + currentPrice >= price[j]){
                    sb.setCharAt(i, (char)(j+'0'));
                    totalMoney += currentPrice - price[j];
                    break;
                }
            }
        }
    }
```
