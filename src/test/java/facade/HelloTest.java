package facade;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
    @Test
    public void testHello(){
        System.out.println("Method to say hello");
        Hello hello = new Hello();
        String actual = hello.sayHello("Helge");
        String expected = "Hello Helge";
        assertEquals(expected, actual);
    }
}