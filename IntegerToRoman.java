// 12
public class IntegerToRoman {
    void append(StringBuilder sb, char c, char c4, char c9, int num) {
        if (num == 9) {
            sb.append(c).append(c9);
        } else if (num == 4) {
            sb.append(c).append(c4);
        } else {
            if (num >= 5)
                sb.append(c4);
            num = num % 5;
            for (int i = 0; i < num; i++) {
                sb.append(c);
            }
        }
    }

    public String intToRoman(int num) {
        int thousands = num / 1000;
        int hundreds = num % 1000 / 100;
        int tens = num % 100 / 10;
        int ones = num % 10;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < thousands; i++) {
            sb.append('M');
        }
        append(sb, 'C', 'D', 'M', hundreds);
        append(sb, 'X', 'L', 'C', tens);
        append(sb, 'I', 'V', 'X', ones);
        return sb.toString();
    }

    public static void main(String[] args) {
        IntegerToRoman i = new IntegerToRoman();
        System.out.println(i.intToRoman(1994));
    }
}
