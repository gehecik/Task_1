package praktikum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static java.lang.Math.pow;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BurgerTest {
    @Test
    public void setBunsTest() {
        Bun bun = new Bun("bun", 3.14f);
        Burger burger = new Burger();

        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "ingredient", 2.7f);
        Burger burger = new Burger();

        burger.addIngredient(ingredient);
        assertEquals(ingredient, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientTest() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "ingredient", 2.7f);
        Burger burger = new Burger();

        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void removeNotExistIngredientTest() {
        Burger burger = new Burger();

        assertThrows(Exception.class, () -> burger.removeIngredient(0));
    }

    @Test
    public void moveIngredientTest() {
        Ingredient ingredientCheese = new Ingredient(IngredientType.FILLING, "cheese", 2.7f);
        Ingredient ingredientHam = new Ingredient(IngredientType.FILLING, "ham", 1.6f);
        Burger burger = new Burger();

        burger.addIngredient(ingredientCheese);
        burger.addIngredient(ingredientHam);
        burger.moveIngredient(0, 1);
        assertEquals(ingredientCheese, burger.ingredients.get(1));
        assertEquals(ingredientHam, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientToSameIndexTest() {
        Ingredient ingredientCheese = new Ingredient(IngredientType.FILLING, "cheese", 2.7f);
        Burger burger = new Burger();

        burger.addIngredient(ingredientCheese);
        burger.moveIngredient(0, 0);
        assertEquals(ingredientCheese, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientToNonExistIndexTest() {
        Ingredient ingredientCheese = new Ingredient(IngredientType.FILLING, "cheese", 2.7f);
        Burger burger = new Burger();

        burger.addIngredient(ingredientCheese);
        assertThrows(Exception.class, () -> burger.moveIngredient(0, 2));
    }

    @Test
    public void moveIngredientFromNonExistIndexTest() {
        Ingredient ingredientCheese = new Ingredient(IngredientType.FILLING, "cheese", 2.7f);
        Burger burger = new Burger();

        burger.addIngredient(ingredientCheese);
        Exception t = assertThrows(Exception.class, () -> burger.moveIngredient(2, 0));
    }

    @Test
    public void getPriceTest() {
        Bun bun = Mockito.mock(Bun.class);
        Ingredient ingredient = Mockito.mock(Ingredient.class);
        Burger burger = new Burger();

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        Mockito.when(bun.getPrice()).thenReturn(3.14f);
        Mockito.when(ingredient.getPrice()).thenReturn(2.7f);

        float price = burger.getPrice();
        assertEquals(3.14f * 2 + 2.7f, price, 1e-6f);

    }

    @Test
    public void getPriceOnlyBunTest() {
        Bun bun = Mockito.mock(Bun.class);
        Burger burger = new Burger();

        burger.setBuns(bun);
        Mockito.when(bun.getPrice()).thenReturn(3.14f);

        float price = burger.getPrice();
        assertEquals(3.14f * 2, price, 1e-6f);
    }
}
