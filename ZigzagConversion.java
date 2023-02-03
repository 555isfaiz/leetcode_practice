public class ZigzagConversion {
    public String convert(String s, int numRows) {
        if (numRows == 1)
            return s;
        
        StringBuffer sb = new StringBuffer();
        int bigStep = numRows * 2 - 2;
        for (int i = 0; i < numRows; i++) {
            int j = i;
            int step = bigStep - i * 2;
            if (step <= 0) {
                step = bigStep - step;
            }
            while (j < s.length()) {
                sb.append(s.charAt(j));

                j += step;
                
                if (step != bigStep) {
                    step = bigStep - step;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ZigzagConversion z = new ZigzagConversion();
        System.out.print(z.convert("PAYPALISHIRING", 4));
    }
}
