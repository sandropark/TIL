public class M_2_14 {
    public static void main(String[] args) {
        String str1 = "Programming";    
        String str2 = "Programming";
        // 위 2가지 방식은 String constant pool에 있는 하나의 객체를 참조한다. 동일한 객체
        String str3 = new String("Programming"); // 힙 메모리에 별도의 객체가 생성된다.

        System.out.println(str1 == str2); // true
        System.out.println(str1 == str3); // false
        System.out.println(str1.equals(str3)); // true
    }
}