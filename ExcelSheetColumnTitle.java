/**
 * ExcelSheetColumnTitle
 */
public class ExcelSheetColumnTitle {

    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while (columnNumber > 0) {
            if (columnNumber % 26 == 0) {
                sb.insert(0, 'Z');
                columnNumber /= 26;
                columnNumber--;
            } else {
                sb.insert(0, (char) ('@' + columnNumber % 26));
                columnNumber /= 26;
            }
        }
        return sb.toString();
    }

    // https://leetcode.com/problems/excel-sheet-column-title/solutions/3943190/easy-solution-python-c-c-java-beats-100-0-87-88-with-image/
    public class Solution {
        public String convertToTitle(int columnNumber) {
            StringBuilder out = new StringBuilder();
            while (columnNumber > 0) {
                out.insert(0, (char) ('A' + (columnNumber - 1) % 26));
                columnNumber = (columnNumber - 1) / 26;
            }
            return out.toString();
        }
    }

    public static void main(String[] args) {
        ExcelSheetColumnTitle e = new ExcelSheetColumnTitle();
        System.out.println(e.convertToTitle(701));
    }
}
