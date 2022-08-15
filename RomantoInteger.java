// 13
public class RomantoInteger {
    public int romanToInt(String s) {
        int val = 0;
        char last = ' ';
        for (int i = 0; i < s.length(); i++) {
            var c = s.charAt(i);
            if (c == 'I') val += 1;
            if (c == 'V') {
                if (last == 'I') val += 3;
                else val += 5;
            }
            if (c == 'X') {
                if (last == 'I') val += 8;
                else val += 10;
            }
            if (c == 'L') {
                if (last == 'X') val += 30;
                else val += 50;
            }
            if (c == 'C') {
                if (last == 'X') val += 80;
                else val += 100;
            }
            if (c == 'D') {
                if (last == 'C') val += 300;
                else val += 500;
            }
            if (c == 'M') {
                if (last == 'C') val += 800;
                else val += 1000;
            }
            last = c;
        }

        return val;
    }
}
