package ru.hse.java;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;

/**
 * @author entropia
 */
public class HelloWorldTest {
    @Test
    public void mytest() {
        HelloWorld.Printer printer = Mockito.mock(HelloWorld.Printer.class);
        new HelloWorld(printer).run();

        Mockito.verify(printer, times(1)).println("Hello, world!");
    }
}
