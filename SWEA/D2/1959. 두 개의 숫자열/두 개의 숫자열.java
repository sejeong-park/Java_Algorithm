

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;

    // a와 b 배열 입력 받기
    static int minSize, maxSize;
    static int [] minArray;
    static int [] maxArray;

    // 최대 최소 판단해주기
    public static void MinMaxSize(int [] aArray, int[] bArray){
        // 만약 aSize가 더 큰 경우
        if (aArray.length > bArray.length){
            minSize = bArray.length;
            minArray = bArray;
            maxSize = aArray.length;
            maxArray = aArray;
        }else {
            // bSize가 더 큰 경우
            minSize = aArray.length;
            minArray = aArray;
            maxSize = bArray.length;
            maxArray = bArray;
        }
    }

    public static int findMaxMulti(){
        int result = Integer.MIN_VALUE; // 최종 출력할 결과값
        // 곱해줄 수 있는 인덱스까지 출력! -> start index
        for (int idx = 0; idx < maxSize-minSize+1; idx++){
            int caseResult = 0; // maxArray와 minArray의 곱
            int maxIdx = idx; // 초기 max Idx -> base 가 되는 Array의 index를 찾아준다.
            for (int minIdx = 0; minIdx < minSize; minIdx++){
                //System.out.println(minArray[minIdx] + " "+ maxArray[maxIdx++]);
                caseResult += minArray[minIdx] * maxArray[maxIdx++];
            }
            result = Math.max(result, caseResult); // 최대값 갱신
        }
        return result;
    }

    public static void main(String[] args) throws IOException {

        // 기본 입출력 선언하기
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // test case 입력 받기
        int T = Integer.parseInt(br.readLine().trim());
        for (int test_case = 1; test_case <= T; test_case++){
            // A와 B 크기 입력 받기
            st = new StringTokenizer(br.readLine().trim());
            int aSize = Integer.parseInt(st.nextToken());
            int bSize = Integer.parseInt(st.nextToken());

            // 초기 배열 생성
            int [] aArray = new int[aSize];
            int[] bArray = new int[bSize];

            // a 배열 입력
            st = new StringTokenizer(br.readLine().trim());
            for (int idx = 0; idx < aSize; idx++){
                aArray[idx] = Integer.parseInt(st.nextToken());
            }
            // b 입력
            st = new StringTokenizer(br.readLine().trim());
            for (int idx = 0; idx < bSize; idx++){
                bArray[idx] = Integer.parseInt(st.nextToken());
            }


            // 더 큰 배열 여부를 판단하기 위해
            MinMaxSize(aArray, bArray);

            // 가장 큰 수 갱신하며 출력
            sb.append("#").append(test_case).append(" ").append(findMaxMulti()).append("\n");
        } //  end for test_case
        System.out.println(sb);

    }

}
