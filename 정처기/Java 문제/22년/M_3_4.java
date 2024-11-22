public class M_3_4 {
    public static void main(String[] args) {
        int[] result = new int[5];
        int[] arr = new int[] { 77, 32, 10, 99, 50 };

        for (int i = 0; i < 5; i++) { // 0~4
            result[i] = 1;
            for (int j = 0; j < 5; j++) { // 0~4
                if (arr[i] < arr[j])
                    result[i]++;
            }
        }

        for (int k = 0; k < 5; k++) {
            System.out.print(result[k]); // 24513
        }
    }
}
