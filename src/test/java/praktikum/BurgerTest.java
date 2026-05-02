package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BurgerTest {
    private Burger burger;

    @BeforeEach
    void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsTest() {
        Bun bun = new Bun("bun", 3.14f);

        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "ingredient", 2.7f);

        burger.addIngredient(ingredient);
        assertEquals(ingredient, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientTest() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "ingredient", 2.7f);

        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void removeNotExistIngredientTest() {
        assertThrows(IndexOutOfBoundsException.class, () -> burger.removeIngredient(0));
    }

    @Test
    public void moveIngredientTest() {
        Ingredient ingredientCheese = new Ingredient(IngredientType.FILLING, "cheese", 2.7f);
        Ingredient ingredientHam = new Ingredient(IngredientType.FILLING, "ham", 1.6f);

        burger.addIngredient(ingredientCheese);
        burger.addIngredient(ingredientHam);
        burger.moveIngredient(0, 1);
        assertEquals(ingredientCheese, burger.ingredients.get(1));
        assertEquals(ingredientHam, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientToSameIndexTest() {
        Ingredient ingredientCheese = new Ingredient(IngredientType.FILLING, "cheese", 2.7f);

        burger.addIngredient(ingredientCheese);
        burger.moveIngredient(0, 0);
        assertEquals(ingredientCheese, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientToNonExistIndexTest() {
        Ingredient ingredientCheese = new Ingredient(IngredientType.FILLING, "cheese", 2.7f);

        burger.addIngredient(ingredientCheese);
        assertThrows(IndexOutOfBoundsException.class, () -> burger.moveIngredient(0, 2));
    }

    @Test
    public void moveIngredientFromNonExistIndexTest() {
        Ingredient ingredientCheese = new Ingredient(IngredientType.FILLING, "cheese", 2.7f);

        burger.addIngredient(ingredientCheese);
        assertThrows(IndexOutOfBoundsException.class, () -> burger.moveIngredient(2, 0));
    }

    @Test
    public void getPriceTest() {
        Bun bun = Mockito.mock(Bun.class);
        Ingredient ingredient = Mockito.mock(Ingredient.class);

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

        burger.setBuns(bun);
        Mockito.when(bun.getPrice()).thenReturn(3.14f);

        float price = burger.getPrice();
        assertEquals(3.14f * 2, price, 1e-6f);
    }

    @Test
    public void getReceiptTest() {
        Bun bun = Mockito.mock(Bun.class);
        Ingredient ingredient = Mockito.mock(Ingredient.class);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        Mockito.when(bun.getName()).thenReturn("Bun");
        Mockito.when(bun.getPrice()).thenReturn(3.14f);
        Mockito.when(ingredient.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(ingredient.getName()).thenReturn("cheese");
        Mockito.when(ingredient.getPrice()).thenReturn(2.7f);

        assertEquals(
                String.format("(==== %s ====)%n= %s %s =%n(==== %s ====)%n%nPrice: %f%n",
                        "Bun", "filling", "cheese", "Bun", 3.14f * 2 + 2.7f),
                burger.getReceipt()
        );
    }

    @Test
    public void getReceiptOnlyBunTest() {
        Bun bun = Mockito.mock(Bun.class);

        burger.setBuns(bun);
        Mockito.when(bun.getName()).thenReturn("Bun");
        Mockito.when(bun.getPrice()).thenReturn(3.14f);

        assertEquals(
                String.format("(==== %s ====)%n(==== %s ====)%n%nPrice: %f%n",
                        "Bun","Bun", 3.14f * 2),
                burger.getReceipt()
        );
    }

    @Test
    public void getReceiptWithoutBunTest() {
        assertThrows(NullPointerException.class, () -> burger.getReceipt());
    }
}
