
/*
* SWEA 1218 괄호짝짓기
*
* (), [], {}, <>로 이루어진 문자열 존재
* 괄호의 유효여부 확인하기
*
* - Stack 이용
* - 문제 조건 : Stack 라이브러리 사용하지 말기 :<
*
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    // Stack 구현하기
    public static class Stack<E> {
        int top; // 마지막 인덱스 구분
        int size; // 최대 크기
        E[] stack; // stack List

        // 생성자
        public Stack(int size){
            // 객체 생성
            this.size = size;
            this.stack = (E[]) new Object[size]; // E type으로 객체 생성
            this.top = -1;
        }

        // push 연산자 : 데이터 삽입
        void push(E item){
            stack[++top] = item;
        }

        // generic 타입 반환 메서드
        // stack 자료구조 빼기
        E pop(){
            // top 값 꺼내고, top index --
            return stack[top--];
        }

        // stack의 top 반환
        E peek(){
            return stack[top];
        }

        // stack의 크기 반환
        int size(){
            //top은 index 값이므로 +1
            return top+1;
        }

        // stack 비어있는 지 확인
        boolean empty(){
            if (size() == 0) return true;
            else return false;
        }

    }
    // left 괄호랑 right 괄호의 Pair 여부 판단
    public static boolean isPair(char left, char right){
        if (left == '(' && right == ')') return true;
        else if (left == '[' && right == ']') return true;
        else if (left == '{' && right == '}') return true;
        else if (left == '<' && right == '>') return true;
        else return false;
    }

    public static void main(String args[]) throws IOException {
        // 입출력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스는 10개로 주어진다.
        for (int test_case = 1; test_case <= 10; test_case ++ ){

            // 입력 받을 괄호의 수
            int N = Integer.parseInt(br.readLine().trim());
            // 정답 초기화
            int result = 1;

            // stack 객체 생성
            Stack<Character> stack = new Stack<>(N);

            // 테스트 케이스 입력 받기
            String str = br.readLine().trim();
            // char array로 변환
            char [] charArray = str.toCharArray();

            // for
            for(char ch : charArray){
                // 왼쪽 괄호를 만나면, stack에 넣는다.
                if (ch == '(' || ch == '[' || ch == '{' || ch == '<'){
                    stack.push(ch);
                }else{
                    // 만약 오른쪽 괄호가 들어왔는데, stack이 비어있다면? -> 오류
                    if (stack.empty()){
                        result = 0;
                        break;
                    }
                    // 만약 짝이 있다면?
                    // Pair 여부를 검사하므로, stack에 들어있는 것이 왼쪽 괄호
                    if (isPair(stack.peek(), ch)){
                        stack.pop(); // pair라면 빼주기
                    }else{
                        // 짝이 아니라면
                        result = 0; // 유효하지 않음
                        break;
                    }
                }
            }
            // 모든 array를 탐색해서 문제가 없다면?
            sb.append('#').append(test_case).append(' ').append(result).append('\n');
        } // end for test_case
        System.out.println(sb);
    }
}
