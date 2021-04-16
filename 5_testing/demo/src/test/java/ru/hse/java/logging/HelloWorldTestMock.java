package ru.hse.java.logging;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;

/**
 * @author entropia
 */
public class HelloWorldTestMock {
    @Test
    public void mytest() {
        Printer printer = Mockito.mock(Printer.class);
        new HelloWorld(printer).run().run();

        Mockito.verify(printer, times(2)).println("Hello, world!");
    }
}
