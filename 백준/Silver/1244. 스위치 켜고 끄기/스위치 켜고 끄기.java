
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* 문제
* - 1은 스위치가 켜져있을 때 0은 꺼져있을 때
* - 남학생은 스위치 번호가 자기가 받은 수의 배수이면 스위치의 상태를 바꾼다 (반대로) -> 3이면, 3, 6, 상태 변경
* - 여학생은 자기가 받은 수와 같은 번호가 붙은 스위치 중심으로 좌우가 대칭이면 가장 ㅁ낳은 스위치를 포함하는 구간 -> 스위치 상태 변경
* */
public class Main {

    static int[] Switch; // 스위치 배열
    static int SwitchCnt; // 스위치 배열 크기


    // 스위치의 상태를 변경하는 메서드 -> 인덱스를 기준으로 스위치 변경해주기
    public static void toggleIndex(int index){
        if (Switch[index] == 0) Switch[index] = 1;
        else if (Switch[index] == 1) Switch[index] = 0;
    }

    // 남자일 경우
    public static void manToggle(int number){
        // 남학생인 경우 뽑은 수의 배수 만큼 스위치 상태를 바꾼다.
        for (int idx = 1; idx <= SwitchCnt; idx ++) {
            // 배수 여부
            if (idx % number == 0) {
                toggleIndex(idx); // 스위치 상태 변경
            }
        }
    }

    // 여자일 경우
    public static void womanToggle(int number){
        // 여학생인 경우 뽑은 수를 기준으로 양 옆의 스위치가 좌우가 대칭이면서 가장 많은 스위치를 포함하는 구간에서 -> 이 구간 스위치 상태 모두 변경
        // 이 구간에는 모두 홀수

        // 현재 위치 스위치 상태 변경
        toggleIndex(number);

        // 대칭 스위치
        int depth = 1; // number로부터 양 옆 탐색
        while ((number - depth) >= 1 && (number + depth) <= SwitchCnt){
            // 대칭의 스위치가 일치하다면 양 옆 상태 변경
            if (Switch[number - depth] == Switch[number + depth]){
                toggleIndex(number-depth);
                toggleIndex(number+depth);
                depth ++; // depth 증가
            }
            else{
                // 대칭이 아니라면 종료
                return;
            }

        }
        return;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 스위치 개수
        SwitchCnt = Integer.parseInt(br.readLine().trim());
        // 스위치 배열 받기 - index 그대로 받음
        Switch = new int[SwitchCnt + 1];
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 1; idx <= SwitchCnt ; idx++){
            Switch[idx] = Integer.parseInt(st.nextToken()); // swithch 입력 받기 string to int
        }

        // 학생 인원 수
        int studentNum = Integer.parseInt(br.readLine().trim());
        for (int idx = 0; idx < studentNum; idx++){
            // 한 학생의 성별 / 학생이 받은 수
            st = new StringTokenizer(br.readLine().trim(), " "); // split 기준으로 token
            int gender = Integer.parseInt(st.nextToken()); // 성별
            int number = Integer.parseInt(st.nextToken()); // 받은 수

            if (gender==1){
                manToggle(number);
            }else if (gender == 2){
                womanToggle(number);
            }
        }

        // 출력
        for(int idx = 1; idx <= SwitchCnt; idx ++){
            System.out.print(Switch[idx] + " ");
            // 한 줄에 20개씩 출력
            if ((idx) % 20 == 0){
                System.out.println();
            }
        }

    }
}
