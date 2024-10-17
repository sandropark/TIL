public class M_2_17 {
    public static void main(String[] args) {
        String str = "abacabcd";
        boolean[] seen = new boolean[256];
        System.out.print(calculFn(str, str.length()-1, seen));  // dcba
    }
 
    public static String calculFn(String str, int index, boolean[] seen) {  // index=7
        if(index < 0) return "";
        char c = str.charAt(index); // d
        String result = calculFn(str, index-1, seen); // index=6
        if(!seen[c]) {
            seen[c] = true;
            return c + result;
        }
        return result;
    }
}
