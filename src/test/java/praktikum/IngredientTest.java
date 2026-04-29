package praktikum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTest {
    public String name = "ingredient";
    public float price = 2.7f;

    @Test
    public void getPriceTest() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, name, price);

        assertEquals(price, ingredient.getPrice());
    }

    @Test
    public void getNameTest() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, name, price);

        assertEquals(name, ingredient.getName());
    }

    @ParameterizedTest
    @EnumSource(IngredientType.class)
    public void getTypeTest(IngredientType type) {
        Ingredient ingredient = new Ingredient(type, name, price);

        assertEquals(type, ingredient.getType());
    }
}
