import org.junit.Test;
import source.PalindromicSubstring;

import static org.junit.Assert.assertTrue;

public class UnitTest {

    @Test
    public void stringEquals() {
        String symbols = "QWERTYUIOPASDFGHJKLZXCVBNM";

        for(int a = 0; a < 1000; a++) {
            StringBuilder randString = new StringBuilder();
            int count = 1 + (int) (Math.random() * 20);
            for (int i = 0; i < count; i++)
                randString.append(symbols.charAt((int) (Math.random() * symbols.length())));

            //System.out.println(randString.toString());
            String exp = PalindromicSubstring.longSearch(randString.toString());
            String act = PalindromicSubstring.algorithmicSearch(randString.toString());
            //System.out.println(act);
            //System.out.println(exp);
            assertTrue(act.equals(exp));
        }
    }
}
