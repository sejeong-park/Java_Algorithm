

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
 
public class Main { 
    
    // 15565번 - 귀여운 라이언
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String[] input=br.readLine().split(" ");
        int[] doll=new int[Integer.parseInt(input[0])]; //인형들의 배열
        int k=Integer.parseInt(input[1]);   //라이언 인형 포함 갯수
        ArrayList<Integer> lion=new ArrayList<Integer>();   // 라이언 인형의 위치를 저장할 리스트
        
        input=br.readLine().split(" ");
        for(int i=0;i<doll.length;i++) {
            doll[i]=Integer.parseInt(input[i]);
            if(doll[i]==1) lion.add(i);     //인형이 라이언인경우 위치를 저장
        }
        
        if(lion.size()<k) { //라이언 인형의 개수가 k보다 적은 경우 -1호출 후 종료
            System.out.println(-1);
            return;
        }
        
        /*
            int min=Integer.MAX_VALUE ,end=k-1;
            for(int start=0;(start+end)<lion.size();start++) {
                min=Math.min(min, lion.get(start+end)-lion.get(start)+1);
            }        
         */
        int start=0,end=k-1;    // (슬라이딩)윈도우 사이즈 지정
        int min=Integer.MAX_VALUE, cnt=0;
        while(true) {
            if(end==lion.size()) break;
            cnt=lion.get(end)-lion.get(start)+1;
            min=Math.min(min, cnt);
            
            start++;
            end++;
        }
        
        System.out.println(min);
    }
}