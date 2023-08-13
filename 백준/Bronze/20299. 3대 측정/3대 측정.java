

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

/*
* BOJ_3대 측정
* - N : 팀의 수
* - K : 팀원 3명의 레이팅 합에 대한 클럽 가입 조건
* - L : 개인 레이팅에 대한 클럽 가입 조건
* - VIP 가입 신청 팀의 팀원들의 레이팅 정보 -> x1, x2, x3
* 가입 조건
* - 팀원 3명의 레이팅 합이 K미만인 팀 가입 할 수 없다. -> 한명의 레이팅이 낮아도 두명의 레잍ㅣㅇ 높으면 가입 가능
*
*
* */
public class Main {

    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;

    // 생성자
    public static class TeamMember{
        // 멤버들의 레이팅 점수
        int x1;
        int x2;
        int x3;

        public TeamMember(int x1, int x2, int x3){
            this.x1 = x1;
            this.x2 = x2;
            this.x3 = x3;
        }

        // 팀의 레이팅 수 -> k보다 작으면 false / 넘으면 true
        public boolean teamRating(int K){
            if ((x1 + x2 + x3) >= K){
                return true;
            }else {
                return false;
            }
        }

        // 팀의 각각의 인원이 L을 넘는지 여부
        public boolean memberRating(int L){
            if (x1 >= L && x2 >= L && x3 >= L) {
                return true;
            }else{
                return false;
            }
        }
    }


    public static void main(String[] args) throws IOException {
        // 입출력 객체 생성
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        // N, K, L 입력
        st = new StringTokenizer(br.readLine().trim());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int VIPTeam = 0; // 몇개의 VIP 팀 출력되는지?

        // N 만큼 팀의 가입 신청
        for (int teamIdx = 0; teamIdx < N ; teamIdx++){
            // 3개의 원소 입력
            st = new StringTokenizer(br.readLine().trim());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int x3 = Integer.parseInt(st.nextToken());

            // 팀 객체 생성
            TeamMember team = new TeamMember(x1, x2, x3);
            // memberRating이 k보다 커야하고
            // 모든 멤버들은 L보다 커야한다.
            if (team.teamRating(K) && team.memberRating(L)) {
                sb.append(x1).append(" ").append(x2).append(" ").append(x3).append(" ");
                VIPTeam++;
            }
        }

        System.out.println(VIPTeam);
        System.out.println(sb);
    }

}
