import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ_2470_두용액
 *
 * 두 용액을 혼합하여, 특성값이 0에 가장 가까운 용액
 *
 *
 * */
public class Main {

    static BufferedReader br;
    static StringTokenizer st;

    static int[] solutionList;

    // 결과 저장 클래스
    static class Result{
        int result;
        int solution1;
        int solution2;
        Result(int result, int solution1, int solution2){
            this.result = result;
            this.solution1 = solution1;
            this.solution2 = solution2;
        }
    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        int solutionCnt = Integer.parseInt(br.readLine().trim());
        solutionList = new int[solutionCnt];
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < solutionCnt; idx++){
            solutionList[idx] = Integer.parseInt(st.nextToken());
        }

        // 1. 이분탐색을 위한 정렬
        Arrays.sort(solutionList);

        // 2. 이분탐색 진행 대상 값
        int start = 0;
        int end = solutionList.length -1;
        int mixedSolution;
        Result result = new Result(Integer.MAX_VALUE, start, end); // 초기화

        while(start < end){

            // 두 용액의 합
            mixedSolution = solutionList[start] + solutionList[end];
            // 더 작은 값이 존재한다면
            if (Math.abs(mixedSolution) <= result.result){
                result.result = Math.abs(mixedSolution);
                result.solution1 = start;
                result.solution2 = end;
                if (result.result == 0) break;
            }
            // 섞인 값이 0보다 작으면, start 이동
            if (mixedSolution < 0)
                start += 1;
            else
                end -= 1;
        }

        System.out.println(solutionList[result.solution1] + " " + solutionList[result.solution2]);
    }

}
