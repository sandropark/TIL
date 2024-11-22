public class M_3_20 {
    public static void main(String[] args) {
        int a = 0;
        for (int i = 1; i < 999; i++) { // 1~998
            if (i % 3 == 0 && i % 2 != 0) // 3의 배수면서 2의 배수가 아닌 것
                a = i; // 변수 a에 덮어쓰기 하기 때문에 위 조건에 맞는 가장 큰 수를 찾으면 된다.
        }
        System.out.print(a); // 993
    }
}
