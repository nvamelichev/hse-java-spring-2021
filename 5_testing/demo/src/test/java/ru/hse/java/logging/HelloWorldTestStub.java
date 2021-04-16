package ru.hse.java.logging;

import org.junit.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author entropia
 */
public class HelloWorldTestStub {
    // Test Doubles (per Martin Fowler). These are all commonly called "Mocks" (except for Fake):
    // - Dummy: Passed as arguments, but not used
    // - Fake: e.g. In-memory database or GRPC/REST service impl
    // - Stub: Produces pre-programmed answers/reactions when asked
    // - Spy: Stub + Record call arguments, statistics, ...
    // - Mock: Spy + Verification: Fail when expected calls do not happen/happen not the specified number of times etc.

    @Test
    public void mytest() {
        Printer printer = Mockito.mock(Printer.class);
        //Mockito.when(printer.printedCount()).thenReturn(100_500);
        new HelloWorld(printer).run();

        assertThat(printer.printedCount()).isEqualTo(0);
        //assertThat(printer.printedCount()).isEqualTo(100_500);
        //assertThat(printer.printedCount()).isEqualTo(100_500);
    }
}
