import java.util.ArrayList;
import java.util.List;

public class DesignBrowserHistory {
    class BrowserHistory {

        List<String> urls = new ArrayList<>();
        int ptr = 0;
        int len = 0;

        public BrowserHistory(String homepage) {
            urls.add(homepage);
            len = 1;
        }
        
        public void visit(String url) {
            ptr++;
            urls.add(ptr, url);
            len = ptr + 1;
        }
        
        public String back(int steps) {
            ptr = Math.max(0, ptr - steps);
            return urls.get(ptr);
        }
        
        public String forward(int steps) {
            ptr = Math.min(len - 1, ptr + steps);
            return urls.get(ptr);
        }
    }

    public static void main(String[] args) {
        DesignBrowserHistory d = new DesignBrowserHistory();
        var h = d.new BrowserHistory("leetcode.com");
        h.visit("google.com");
        h.visit("facebook.com");
        h.visit("youtube.com");
        h.back(1);
        h.back(1);
        h.forward(1);
        h.visit("linkedin.com");
        h.forward(2);
        h.back(2);
        h.back(7);
    }
}
