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
import clueGame.ComputerPlayer;
import clueGame.Game;
import clueGame.HumanPlayer;
import clueGame.Player;
import clueGame.RoomCell;
import clueGame.RoomCell.DoorDirection;
import clueGame.Suggestion;

public class GameActionTests {

	private static Game ourGame;
	private static Card mustardCard;
	private static Card knifeCard;
	private static Card wrenchCard;
	private static Card scarletCard;

	@BeforeClass
	public static void setup() {
		ourGame = new Game();
		ourGame.loadConfigFiles("./ourboardfiles/StartCharacters.txt", "./ourboardfiles/Weapons.txt");
		
		//write a few new cards NOT added to the deck, but added to a player's cards in order to test disproving suggestions
		mustardCard = new Card("Colonel Mustard", CardType.PERSON);
		knifeCard = new Card("Knife", CardType.WEAPON);
		scarletCard = new Card ("Miss Scarlet", CardType.PERSON);
		wrenchCard = new Card("Wrench", CardType.WEAPON);
		
		// mustardCard and knifeCard are static variables, because @BeforeClass
		// is static.  This allows me to set up the cards one time.
		
	}
	
	@Test //Tests that the computer can make a valid suggestion based on the cards that it has 'seen' and the current room
	public void computerSuggestion(){
		//creates the player to be tested
		RoomCell cPlayerLoc = new RoomCell('C', DoorDirection.RIGHT);
		ComputerPlayer cPlayer = new ComputerPlayer("Miss Scarlet", cPlayerLoc, Color.RED);
		String room = ourGame.getGameBoard().getRoom(cPlayerLoc.getInitial());
		ArrayList<Card> deck = ourGame.getDeck();

		//Creating pseudo seen deck for Computer Player
		for(int i = 1; i< 6; i++){
			cPlayer.updateSeen(deck.get(i)); //Adds all weapon cards to seen except 'Wrench'
		}
		for(int i = 7; i < 12; i++){
			cPlayer.updateSeen(deck.get(i)); //Adds all person cards to seen except 'Miss Scarlet'
		}

		//Only Possible Suggestion for Computer
		Suggestion expected = new Suggestion("Miss Scarlet", "Wrench", room);
		Suggestion actual = cPlayer.createSuggestion(room, ourGame.getDeck());
		Assert.assertTrue(expected.equals(actual));



		//Four Different Possible Solutions with 1 Room
		cPlayerLoc = new RoomCell('D', DoorDirection.UP);
		cPlayer = new ComputerPlayer("Mrs. Peacock", cPlayerLoc, Color.BLUE);
		room = ourGame.getGameBoard().getRoom(cPlayerLoc.getInitial());

		//Creates new pseudo deck for new computer player
		for(int i = 2; i< 6; i++){
			cPlayer.updateSeen(deck.get(i)); //Adds all weapon cards to seen except 'Wrench' and 'Rope'
		}
		for(int i = 8; i < 12; i++){
			cPlayer.updateSeen(deck.get(i)); //Adds all person cards to seen except 'Miss Scarlet' and 'Mrs. Peacock'
		}

		actual = cPlayer.createSuggestion(room, deck);

		//Test for the four possibilities of suggestions by individual field
		Assert.assertEquals(room, actual.getRoom());// Room should always equal the suggesting player's room
		//the suggested name can be either of these two names
		Assert.assertTrue(actual.getName().equals("Miss Scarlet") || actual.getName().equals("Mrs. Peacock")); 
		//the suggested weapon can equal either of these two weapons
		Assert.assertTrue(actual.getWeapon().equals("Wrench") || actual.getWeapon().equals("Rope"));
	}
	
	//DISPROVE SUGGESTIONS tests
	//our own "deal" is necessary before these tests so that cards can be tested and are not randomly alloted
	//use a handleSuggestion method for all this?
	@Test
	public void cardShown() { //normal situation, card successfully returned
		Card expected = mustardCard;
		ourGame.getPlayers().get(0).getMyCards().add(mustardCard);
		Card actual = ourGame.handleSuggestion("Col. Mustard", "Candlestick", ourGame.getPlayers().get(1)); 
		Assert.assertEquals(expected, actual);
	}
	@Test
	public void multipleOptionsOnePlayer() { //if one player has multiple cards that could disprove, one is selected randomly
		Card expected0 = mustardCard;
		Card expected1 = knifeCard;
		Card notInSoln = new Card("notInSoln", CardType.WEAPON); //make sure it only returns one of the cards from the player that successfully disproves
		ourGame.getPlayers().get(0).getMyCards().add(knifeCard);
		ourGame.getPlayers().get(0).getMyCards().add(notInSoln);
		Card actual = ourGame.handleSuggestion("Col. Mustard", "Knife", ourGame.getPlayers().get(2)); 
		Assert.assertTrue(actual.equals(expected0) || actual.equals(expected1));
	}
	@Test
	public void multiplePlayersCouldDisprove() { //if multiple players could disprove, still only one card is returned (from first player that can disprove)
		ourGame.getPlayers().get(0).getMyCards().remove(knifeCard);
		ourGame.getPlayers().get(4).getMyCards().add(knifeCard);
		Card expected = mustardCard;
		Card actual = ourGame.handleSuggestion("Col. Mustard", "Knife", ourGame.getPlayers().get(2)); 
		Assert.assertEquals(actual, expected);
	}
	@Test
	public void allPlayersQueried() { //both first and last players are queried
		ourGame.getPlayers().get(4).getMyCards().remove(knifeCard);
		ourGame.getPlayers().get(0).getMyCards().remove(mustardCard);
		ourGame.getPlayers().get(5).getMyCards().add(mustardCard);
		Card expected = mustardCard;
		Card actual = ourGame.handleSuggestion("Col. Mustard", "Knife", ourGame.getPlayers().get(2)); 
		Assert.assertEquals(actual, expected);
		expected = knifeCard;
		ourGame.getPlayers().get(0).getMyCards().add(mustardCard);
		actual = ourGame.handleSuggestion("Col. Mustard", "Knife", ourGame.getPlayers().get(2));
	}
	@Test
	public void suggestorHasOnlySolution() { //testing that the player who makes the suggestion is not queried (should return null)
		Card expected = null;
		ourGame.getPlayers().get(3).getMyCards().add(scarletCard);
		Card actual = ourGame.handleSuggestion("Miss Scarlet", "Wrench", ourGame.getPlayers().get(3));;
		Assert.assertEquals(expected, actual);
	}
	@Test
	public void nullReturn() { //no player has a card to disprove
		ourGame.getPlayers().get(3).getMyCards().remove(scarletCard);
		Card expected = null;
		Card actual = ourGame.handleSuggestion("Miss Scarlet", "Wrench", ourGame.getPlayers().get(3));
		Assert.assertEquals(expected, actual);
		
	}

}
