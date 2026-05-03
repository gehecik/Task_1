package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {

    Database database;

    @BeforeEach
    void setUp() {
        database = new Database();
    }

    @Test
    public void availableBunsSizeTest() {
        List<Bun> buns = database.availableBuns();

        assertEquals(3, buns.size());
    }

    @Test
    public void availableBunsSameListOfBunsTest() {
        List<Bun> bunsFirstList = database.availableBuns();
        List<Bun> bunsSecondList = database.availableBuns();

        assertEquals(bunsFirstList, bunsSecondList);
    }

    @Test
    public void availableBunsFirstBunTest() {
        List<Bun> buns = database.availableBuns();

        assertEquals("black bun", buns.get(0).getName());
        assertEquals(100, buns.get(0).getPrice());
    }

    @Test
    public void availableIngredientsSizeTest() {
        List<Ingredient> ingredients = database.availableIngredients();

        assertEquals(6, ingredients.size());
    }

    @Test
    public void availableIngredientsSameListTest() {
        List<Ingredient> ingredientsFirstList = database.availableIngredients();
        List<Ingredient> ingredientsSecondList = database.availableIngredients();

        assertEquals(ingredientsFirstList, ingredientsSecondList);
    }

    @Test
    public void availableIngredientsFirstTest() {
        List<Ingredient> ingredients = database.availableIngredients();

        assertEquals(IngredientType.SAUCE, ingredients.get(0).getType());
        assertEquals("hot sauce", ingredients.get(0).getName());
        assertEquals(100, ingredients.get(0).getPrice());
    }

    @Test
    public void availableIngredientsContainSauceAndFillingTypesTest() {
        List<Ingredient> ingredients = database.availableIngredients();
        int cntSauce = 0;
        int cntFilling = 0;

        for (Ingredient ingredient : ingredients) {
            if (ingredient.getType().equals(IngredientType.SAUCE)) {
                cntSauce++;
            } else if (ingredient.getType().equals(IngredientType.FILLING)) {
                cntFilling++;
            }
        }

        assertEquals(3, cntSauce);
        assertEquals(3, cntFilling);
    }
}
