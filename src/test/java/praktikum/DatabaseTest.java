package praktikum;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseTest {
    @Test
    public void availableBunsTest() {
        Database database = new Database();
        List<Bun> buns = database.availableBuns();

        assertEquals(3, buns.size());
    }

    @Test
    public void availableIngredientsTest() {
        Database database = new Database();
        List<Ingredient> ingredients = database.availableIngredients();

        assertEquals(6, ingredients.size());
    }
}
