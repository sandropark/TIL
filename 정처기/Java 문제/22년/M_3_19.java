public class M_3_19 {
    static int[] makeArray() {
        int[] tempArr = new int[4];

        for (int i = 0; i < tempArr.length; i++) { // 0~3
            tempArr[i] = i;
        }

        return tempArr;
    }

    public static void main(String[] args) {
        int[] intArr = makeArray(); // [0,1,2,3]

        for (int i = 0; i < intArr.length; i++)
            System.out.print(intArr[i]); // 0123
    }
}
