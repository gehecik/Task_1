package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTest {

    String name = "Bun";
    float price = 3.14f;

    @Test
    public void getNameTest() {
        Bun bun = new Bun(name, price);

        assertEquals(name, bun.getName());
    }

    @Test
    public void getPriceTest() {
        Bun bun = new Bun(name, price);

        assertEquals(price, bun.getPrice());
    }

}
