package testanalyzer.examples;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ExceptionRuleJunit4Test {

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void test1() {
        thrown.expect(NullPointerException.class);
    }

}
