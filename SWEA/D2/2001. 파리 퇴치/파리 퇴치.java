import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader br;
	static StringTokenizer st;

	// test_case 별로 입력 받을 배열
	static int[][] prefixSum;

	public static int prefixResult(int row, int col) {
		// [row][col] 인덱스를 기준으로 2*2만큼씩 탐색한다.
		// prefixSum 컨셉에 맞게 현재 자기보다 1만큼 작은 row / 1만큼 작은 col을 합해주고 row-1 col-1을 빼준다 (두번
		// 더해졌으므로 )

		// 누적합 배열에서 찾기
		return prefixSum[row - 1][col] + prefixSum[row][col - 1] - prefixSum[row - 1][col - 1];
	}

	// overLoading
	public static int prefixResult(int startRow, int startCol, int endRow, int endCol) {

		// 전체 면적 - (row 기준 면적) - (col 기준 면적) + (중복된 영역)
		return prefixSum[endRow][endCol] - prefixSum[startRow - 1][endCol] - prefixSum[endRow][startCol - 1]
				+ prefixSum[startRow - 1][startCol - 1];
	}

	public static void main(String args[]) throws IOException {

		// 파일 입출력 초기화
		br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// test_case 입력
		int T = Integer.parseInt(br.readLine().trim());

		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine().trim());
			// N과 M 입력
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			// 배열 입력 초기화
			prefixSum = new int[N + 1][N + 1]; // 누적합 계산하는 이차우너 배열

			// 배열 입력 받기
			for (int row = 1; row <= N; row++) {

				// row 기준 입력
				st = new StringTokenizer(br.readLine().trim());
				for (int col = 1; col <= N; col++) {
					// 누적합 배열 완성 시키기
					prefixSum[row][col] = prefixResult(row, col) + Integer.parseInt(st.nextToken());
				}
			}

			// 누적합을 기준으로 잡을 수 있는 파리 찾기
			// 최대로 많이 잡을 수 있는 파리
			int maxValue = Integer.MIN_VALUE; // 초기화
			int DISTANCE = M - 1; // 나의 현재 위치를 기준으로 M-1 만큼 거리를 더한것 까지!
			int result = 0; // 파리 면적
			for (int row = 1; row <= N; row++) {
				for (int col = 1; col <= N; col++) {
					// 배열 범위에서 벗어나지 않는 조건
					int endRow = row + DISTANCE; // 1 + M-1 = M
					int endCol = col + DISTANCE; // 1 + M-1 = M
					if (1 <= endRow && endRow <= N && 1 <= endCol && endCol <= N) {
						result = prefixResult(row, col, endRow, endCol);
						// 만약 면적의 합이 최대값에 저장한다.
						if (result > maxValue) {
							maxValue = result;
						}
					}

				}
			} // end for 2차원 배열

			sb.append("#").append(test_case).append(" ").append(maxValue).append("\n");

		} // end for test_case
		System.out.println(sb);

	}

}
