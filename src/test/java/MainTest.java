import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class MainTest {
     String[] args = new String[]{};

    @Test
    @Timeout(value = 22)
    @Disabled
    void currentNoLongerThan22Sec() throws Exception {
        Main.main(args);
    }
}
