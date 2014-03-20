package clueTests;

import java.awt.Color;
import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.BoardCell;
import clueGame.Card;
import clueGame.CardType;
import clueGame.Game;
import clueGame.Player;
import clueGame.Solution;

public class GameSetupTests {
	private static Game ourGame;
	
	@BeforeClass
	public static void setup() {
		ourGame = new Game();
		ourGame.loadConfigFiles("./ourboardfiles/StartCharacters.txt", "./ourboardfiles/Weapons.txt");
		ourGame.deal();
	}
	
	//test the people (part of config file testing)
	@Test
	public void numPlayers() { //correct number of total players
		int expected = 6;
		int actual = ourGame.getPlayers().size();
		Assert.assertEquals(expected, actual);
	}
	@Test
	public void testHuman() {
		/*check the HumanPlayer
		name
		color
		starting location
		*/
		
		Player human = ourGame.getPlayers().get(0);
		String nameExpected = "Miss Scarlet";
		Color colorExpected = Color.red;
		BoardCell cellExpected = ourGame.getGameBoard().getCellAt(0,7);
		
		Assert.assertEquals(nameExpected, human.getMyName());
		Assert.assertEquals(colorExpected, human.getMyColor());
		Assert.assertEquals(cellExpected, human.getLocation());
	}	
	@Test
	public void testComputer() {
		
		//First Computer Player
		Player computer = ourGame.getPlayers().get(1);
		String cNameExpected = "Mrs. Peacock";
		Color cColorExpected = Color.blue;
		BoardCell cCellExpected = ourGame.getGameBoard().getCellAt(5,0);
		
		Assert.assertEquals(cNameExpected, computer.getMyName()); //check name
		Assert.assertEquals(cColorExpected, computer.getMyColor()); //check color
		Assert.assertEquals(cCellExpected, computer.getLocation()); //check location
		
		//Second Computer Player
		computer = ourGame.getPlayers().get(5);
		cNameExpected = "Col. Mustard";
		cColorExpected = Color.yellow;
		cCellExpected = ourGame.getGameBoard().getCellAt(0, 17); 
		
		Assert.assertEquals(cNameExpected, computer.getMyName()); //check name
		Assert.assertEquals(cColorExpected, computer.getMyColor()); //check color
		Assert.assertEquals(cCellExpected, computer.getLocation()); //check location
	}
	
	//testing the deck (parts directly influenced by loadConfigFiles)
	@Test
	public void totalCards() { //correct number of cards in deck
		//6 people, 6 weapons, 9 rooms
		int expected = (6+6+9);
		int actual = ourGame.getDeck().size();
		Assert.assertEquals(expected, actual);
	}
	@Test
	public void containsCards() {
		//check deck contains specific weapon
		Card c = new Card("Candlestick", CardType.WEAPON);
		boolean expected = true;
		boolean actual = c.equals(ourGame.getDeck().get(3));
		Assert.assertEquals(expected, actual);
		
		//check deck contains specific person
		c.setMyType(CardType.PERSON);
		c.setName("Mrs. White");
		actual = c.equals(ourGame.getDeck().get(8));
		Assert.assertEquals(expected, actual);
		
		//check deck contains specific room
		c.setMyType(CardType.ROOM);
		c.setName("Kitchen");
		actual = c.equals(ourGame.getDeck().get(20));
		Assert.assertEquals(expected, actual);
		
	}
	@Test
	public void cardTypeNums() { //testing number of each type of card in the deck
		//6 people, 6 weapons, 9 rooms 
		int expected = 6;
		int personCards = 0;
		int weaponCards = 0;
		int roomCards = 0;
		for (Card c: ourGame.getDeck()) {
			if (c.getMyType().equals(CardType.PERSON))
				personCards++;
			if (c.getMyType().equals(CardType.WEAPON))
				weaponCards++;
			if (c.getMyType().equals(CardType.ROOM))
				roomCards++;	
		}
		Assert.assertEquals(expected, personCards);
		Assert.assertEquals(expected, weaponCards);
		expected = 9;
		Assert.assertEquals(expected, roomCards);
	}
	@Test
	public void allDelt() { // make sure all the cards got dealt
		int expected = 0;
		int actual = ourGame.getDeckClone().size();
		Assert.assertEquals(expected, actual);
	}
	@Test
	public void evenSplit() { //check that each player has approximately the same number of cards
		int expected = 3;
		for (Player p: ourGame.getPlayers()){
			int actual = p.getMyCards().size();
			Assert.assertEquals(expected, actual);
		}
	}
	@Test
	public void uniqueCards() { //check that no card is given out more than once
		ArrayList<Player> players = ourGame.getPlayers();
		ArrayList<Card> allCards = new ArrayList<Card>();
		
		for(Player p: players){
			ArrayList<Card> playerCards = new ArrayList<Card>();
			playerCards = p.getMyCards();
			allCards.addAll(playerCards);
		}
		
		for(int i = 0; i < allCards.size() - 1; i++){
			for(int j = i+1; j < allCards.size(); j++){
				Card current = allCards.get(i);
				Card compare = allCards.get(j);				
				Assert.assertFalse(current.equals(compare));
			}
		}
	}
	@Test
	public void makeAccusation() { //check the validity of an accusation
		String room, weapon, person;
		Player accusingPlayer;
		Solution solution = ourGame.getSolution();
		
		//correct accusation
		room = "Dining Room";
		weapon = "Wrench";
		person = "Miss Scarlet";
		Assert.assertTrue(ourGame.checkAccusation(solution, room, weapon, person));

		//incorrect room
		Assert.assertFalse(ourGame.checkAccusation(solution, "Kitchen", weapon, person));
		//incorrect person
		Assert.assertFalse(ourGame.checkAccusation(solution, room, weapon, "Col. Mustard"));
		//incorrect weapon
		Assert.assertFalse(ourGame.checkAccusation(solution, room, "Lead Pipe" , person));
		
	}
}
