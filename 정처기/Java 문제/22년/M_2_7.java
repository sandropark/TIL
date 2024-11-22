public class M_2_7 {
    public static void main(String args[]) {
        int i = 3, k = 1;

        switch (i) {
            case 1:
                k += 1;
            case 2:
                k++;
            case 3:
                k = 0; // k=0
            case 4:
                k += 3; // k=3
            case 5:
                k -= 10; // k=-7
            default:
                k--; // k=-8
        }

        System.out.print(k); // -8
    }
}
