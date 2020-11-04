package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import exceptions.InvalidNumberException;
import exceptions.NegativeNumberException;

class BoardTest {
	
	private Board game;
	public void setupScenary() throws InvalidNumberException, NegativeNumberException {
		game = new Board(3, 4, 8);
	}
	@Test
	public void test() {
		try {
			setupScenary();
			assertNotNull(game.getFirstGrid());
			assertNull(game.getFirstGrid().getUp());
			assertNull(game.getFirstGrid().getLeft());
			assertNotNull(game.getFirstGrid().getDown());
			assertNull(game.getFirstGrid().getDown().getLeft());
			assertNotNull(game.getFirstGrid().getDown().getDown());
			assertNull(game.getFirstGrid().getDown().getDown().getLeft());
			assertNotNull(game.getFirstGrid().getDown().getDown().getDown());
			assertNull(game.getFirstGrid().getDown().getDown().getDown().getLeft());
			assertNull(game.getFirstGrid().getDown().getDown().getDown().getDown());
			
			Grid first2 = game.getFirstGrid().getRight();
			assertNotNull(first2);
			assertNull(first2.getUp());
			assertNotNull(first2.getRight());
			assertNotNull(first2.getLeft());
			assertNotNull(first2.getDown());
			assertNotNull(first2.getDown().getLeft());
			assertNotNull(first2.getDown().getRight());
			assertNotNull(first2.getDown().getDown());
			assertNotNull(first2.getDown().getDown().getRight());
			assertNotNull(first2.getDown().getDown().getLeft());
			assertNotNull(first2.getDown().getDown().getDown());
			assertNotNull(first2.getDown().getDown().getDown().getLeft());
			assertNotNull(first2.getDown().getDown().getDown().getRight());
			assertNull(first2.getDown().getDown().getDown().getDown());
			
			Grid first3 = first2.getRight();
			assertNotNull(first3);
			assertNull(first3.getUp());
			assertNull(first3.getRight());
			assertNotNull(first3.getLeft());
			assertNull(first3.getRight());
			assertNotNull(first3.getDown());
			assertNotNull(first3.getDown().getLeft());
			assertNull(first3.getDown().getRight());
			assertNotNull(first3.getDown().getDown());
			assertNotNull(first3.getDown().getDown().getLeft());
			assertNull(first3.getDown().getDown().getRight());
			assertNotNull(first3.getDown().getDown().getDown());
			assertNotNull(first3.getDown().getDown().getDown().getLeft());
			assertNull(first3.getDown().getDown().getDown().getRight());
			assertNull(first3.getDown().getDown().getDown().getDown());
			
		}catch(InvalidNumberException in) {
			fail("Todos los numeros son validos");
		}catch(NegativeNumberException ne) {
			fail("El numero no es negativo");
		}
	}

}
