// 393
public class UTF8Validation {
    public boolean validUtf8(int[] data) {
        long len = 0;
        for (int i = 0; i < data.length; i++) {
            if (len == 0) {
                if ((data[i] & 0xf0) == 0xf0 && (data[i] & 0x8) == 0) len = 3;
                else if ((data[i] & 0xe0) == 0xe0 && (data[i] & 0x10) == 0) len = 2;
                else if ((data[i] & 0xc0) == 0xc0 && (data[i] & 0x20) == 0) len = 1;
                else if ((data[i] & 0x80) != 0)
                    return false;
            } else {
                if ((data[i] & 0x80) != 0x80)
                    return false;
                len--;
            }
        }
        return len == 0;
    }

    public static void main(String[] args) {
        UTF8Validation u = new UTF8Validation();
        System.out.println(u.validUtf8(new int[] {
                240,162,138,147,17
        }));
    }
}
