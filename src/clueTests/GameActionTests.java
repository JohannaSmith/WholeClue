package clueTests;

import java.awt.Color;
import java.util.ArrayList;

import junit.framework.Assert;

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
	private static Card ballroomCard;

	@BeforeClass
	public static void setup() {
		ourGame = new Game();
		ourGame.loadConfigFiles("./ourboardfiles/StartCharacters.txt", "./ourboardfiles/Weapons.txt");

		//write a few new cards NOT added to the deck, but added to a player's cards in order to test disproving suggestions
		mustardCard = new Card("Col. Mustard", CardType.PERSON);
		knifeCard = new Card("Knife", CardType.WEAPON);
		scarletCard = new Card ("Miss Scarlet", CardType.PERSON);
		wrenchCard = new Card("Wrench", CardType.WEAPON);
		ballroomCard = new Card("Ballroom", CardType.ROOM);

		// mustardCard and knifeCard are static variables, because @BeforeClass
		// is static.  This allows me to set up the cards one time.
		ourGame.deal();
	}

	@Test
	public void testSelectTarget() {
		//testing the destination will be a room
		ComputerPlayer computerTestPlayer = (ComputerPlayer) ourGame.getPlayers().get(1); // possible hard coding
		ourGame.getGameBoard().calcAdjacencies();
		ourGame.getGameBoard().calcTargets(5, 0, 11);
		int expected = 10;
		int roomTestCount = 0;
		BoardCell location = computerTestPlayer.pickLocation(ourGame.getGameBoard().getTargets());
		// testing multiple times to make sure it wasn't a fluke
		for (int i = 0; i < 10; i++) {
			if (location.isRoom())
				roomTestCount++;
		}
		int actual = roomTestCount;
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testNoReturnToRoom() {
		// testing the computer selects a random walkway when they are coming from a room
		ComputerPlayer computerTestPlayer = new ComputerPlayer("Name", ourGame.getGameBoard().getCellAt(ourGame.getGameBoard().calcIndex(3,6)), ourGame.convertColor("red"));
		ourGame.getGameBoard().calcTargets(3, 6, 2);
		int loc_2_7Count = 0;
		int loc_4_7Count = 0;
		int loc_3_8Count = 0;
		int loc_5_6Count = 0;
		int loc_4_5Count = 0;
		BoardCell location;
		// loops through multiple times to make sure the room just visited is not picked
		for (int i = 0; i < 100; i++) {
			location = computerTestPlayer.pickLocation(ourGame.getGameBoard().getTargets());
			if (location == ourGame.getGameBoard().getCellAt(2, 7))
				loc_2_7Count++;
			else if (location == ourGame.getGameBoard().getCellAt(4, 7))
				loc_4_7Count++;
			else if (location == ourGame.getGameBoard().getCellAt(3, 8))
				loc_3_8Count++;
			else if (location == ourGame.getGameBoard().getCellAt(5, 6))
				loc_5_6Count++;
			else if (location == ourGame.getGameBoard().getCellAt(4, 5))
				loc_4_5Count++;
			else{
				System.out.println(loc_2_7Count + loc_4_7Count + loc_3_8Count + loc_5_6Count + loc_4_5Count);
				Assert.fail("Cannot go back into room");
			}
		}
		Assert.assertEquals(100, loc_2_7Count + loc_4_7Count + loc_3_8Count + loc_5_6Count + loc_4_5Count);
	}

	@Test
	public void testTargetRandomSelection() {
		// testing the computer randomly selects a walkway when no room is a target
		ComputerPlayer computerTestPlayer = (ComputerPlayer) ourGame.getPlayers().get(1);
		ourGame.getGameBoard().calcTargets(5, 0, 2);
		int loc_4_1Count = 0;
		int loc_5_2Count = 0;
		// Run the test 100 times to check for accuracy
		for (int i=0; i<100; i++) {
			BoardCell location = computerTestPlayer.pickLocation(ourGame.getGameBoard().getTargets());
			if (location == ourGame.getGameBoard().getCellAt(4, 1))
				loc_4_1Count++;
			else if (location == ourGame.getGameBoard().getCellAt(5, 2))
				loc_5_2Count++;
			else
				Assert.fail("Invalid target selected");
		}
		// Ensures we have 100 total selections (fail should also ensure this)
		Assert.assertEquals(100, loc_4_1Count + loc_5_2Count);
		// Ensures each target was selected more than once
		Assert.assertTrue(loc_4_1Count > 10);
		Assert.assertTrue(loc_5_2Count > 10);

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
	
	
	//Card shown is just what disproveSuggestion tests do
	/*@Test
	public void cardShown() { //normal situation, card successfully returned
		Card expected = mustardCard;
		ourGame.getPlayers().get(0).getMyCards().add(mustardCard);
		Card actual = ourGame.handleSuggestion("Col. Mustard", "Ballroom", "Candlestick", ourGame.getPlayers().get(1)); 
		Assert.assertTrue(actual.equals(expected));
	}*/
	
	
	@Test
	public void onePlayerOneCorrectMatch(){
		
		Card expected;
		Card actual;
		
		//Sets up test player
		Player player = new ComputerPlayer();
		player.myCards.add(new Card("Col. Mustard", CardType.PERSON));
		player.myCards.add(new Card("Prof Plum", CardType.PERSON));
		player.myCards.add(new Card("Candlestick", CardType.WEAPON));
		player.myCards.add(new Card("Wrench", CardType.WEAPON));
		player.myCards.add(new Card("Conservatory", CardType.ROOM));
		player.myCards.add(new Card("Ballroom", CardType.ROOM));
		
		
		//The right PERSON is disproved
		expected = mustardCard;
		actual = player.disproveSuggestion("Col. Mustard", "Kitchen", "Rope");
		Assert.assertTrue(actual.equals(expected));
		
		//The right ROOM is disproved
		expected = ballroomCard;
		actual = player.disproveSuggestion("Miss Scarlet", "Ballroom", "Rope");
		Assert.assertTrue(actual.equals(expected));
		
		//The right Weapon is disproved
		expected = wrenchCard;
		actual = player.disproveSuggestion("Miss Scarlet", "Kitchen", "Wrench");
		Assert.assertTrue(actual.equals(expected));
		
		//Null is returned
		expected = null;
		actual = player.disproveSuggestion("Miss Scarlet", "Kitchen", "Rope");
		Assert.assertTrue(actual == null);
		
	}
	@Test
	public void multipleOptionsOnePlayer() { //if one player has multiple cards that could disprove, one is selected randomly
		Card expected0 = mustardCard;
		Card expected1 = knifeCard;
		Card notInSoln = new Card("notInSoln", CardType.WEAPON); //make sure it only returns one of the cards from the player that successfully disproves
		ourGame.getPlayers().get(0).getMyCards().add(knifeCard);
		ourGame.getPlayers().get(0).getMyCards().add(notInSoln);
		Card actual = ourGame.handleSuggestion("Col. Mustard", "Ballroom", "Knife", ourGame.getPlayers().get(2)); 
		Assert.assertTrue(actual.equals(expected0) || actual.equals(expected1));
	}
	@Test
	public void allPlayersQueried() { //if multiple players could disprove, still only one card is returned (from first player that can disprove)
		
		//Setting up Player Elements
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new HumanPlayer());
		for(int i = 0; i < 5; i++){
			players.add(new ComputerPlayer());
		}
		
		//Human Player
		players.get(0).myCards.add(new Card("Prof Plum", CardType.PERSON ));
		players.get(0).myCards.add(new Card("Candlestick", CardType.WEAPON));
		
		//Computer Player 1
		players.get(1).myCards.add(new Card("Wrench", CardType.WEAPON));
		players.get(1).myCards.add(new Card("Library", CardType.ROOM));
		
		//Computer Player 2
		players.get(2).myCards.add(new Card("Mrs. White", CardType.PERSON));
		players.get(2).myCards.add(new Card("Lead Pipe", CardType.WEAPON));
		
		//Computer Player 3
		players.get(3).myCards.add(new Card("Rope", CardType.WEAPON));
		players.get(3).myCards.add(new Card("Observatory", CardType.ROOM));
		players.get(3).myCards.add(new Card("Col. Mustard", CardType.PERSON));
		
		//Computer Player 4
		players.get(4).myCards.add(new Card("Mrs. Peacock", CardType.PERSON));
		players.get(4).myCards.add(new Card("Kitchen", CardType.ROOM));
		
		//Computer Player 5
		players.get(5).myCards.add(new Card("Revolver", CardType.WEAPON));
		players.get(5).myCards.add(new Card("Mr. Green", CardType.PERSON));
		
		ourGame.setPlayers(players);		
		
		//No Players can disprove
		Card expected = null;
		Card actual = ourGame.handleSuggestion("Miss Scarlet", "Conservatory", "Knife", ourGame.getPlayers().get(2));
		Assert.assertTrue(actual == null);
		
		//Only Human Can disprove
		expected = players.get(0).myCards.get(0); // Sets expected to Human player's first card (Prof Plum)
		actual = ourGame.handleSuggestion("Prof Plum", "Conservatory", "Knife", ourGame.getPlayers().get(2)); 
		Assert.assertTrue(actual.equals(expected));
		
		//Only suggester can disprove the suggestion (Computer)
		expected = null;
		actual = ourGame.handleSuggestion("Mrs. White", "Ballroom", "Knife", ourGame.getPlayers().get(2));
		Assert.assertTrue(actual == null);
		
		//Only suggester can disprove the suggestion (Human)
		expected = null;
		actual = ourGame.handleSuggestion("Prof Plum", "Conservatory", "Knife", ourGame.getPlayers().get(0));
		Assert.assertTrue(actual == null);
		
		//Two Players can disprove
		
		//Player 5 and 6 can disprove (5 with Mrs. Peacock, 6 with Revolver)
		expected = new Card("Mrs. Peacock", CardType.PERSON);
		actual = ourGame.handleSuggestion("Mrs. Peacock", "Conservatory", "Revolver", ourGame.getPlayers().get(0)); //Player 5 and 6 can disprove
		Assert.assertTrue(actual.equals(expected));
		
		//Only Player 6 can disprove (Last Player)
		expected = new Card("Revolver", CardType.WEAPON);
		actual = ourGame.handleSuggestion("Miss Scarlet", "Conservatory", "Revolver", ourGame.getPlayers().get(0));
		Assert.assertTrue(actual.equals(expected));
		
		
	}
	/*@Test
	public void allPlayersQueried() { //both first and last players are queried
		ourGame.getPlayers().get(4).getMyCards().remove(knifeCard);
		ourGame.getPlayers().get(0).getMyCards().remove(mustardCard);
		ourGame.getPlayers().get(5).getMyCards().add(mustardCard);
		Card expected = mustardCard;
		Card actual = ourGame.handleSuggestion("Col. Mustard", "Ballroom", "Knife", ourGame.getPlayers().get(2)); 
		Assert.assertEquals(expected, actual);
		expected = knifeCard;
		ourGame.getPlayers().get(0).getMyCards().add(mustardCard);
		actual = ourGame.handleSuggestion("Col. Mustard", "Ballroom", "Knife", ourGame.getPlayers().get(2));
	}*/
	/*@Test
	public void suggestorHasOnlySolution() { //testing that the player who makes the suggestion is not queried (should return null)
		Card expected = null;
		ourGame.getPlayers().get(3).getMyCards().add(scarletCard);
		Card actual = ourGame.handleSuggestion("Miss Scarlet", "Ballroom", "Wrench", ourGame.getPlayers().get(3));;
		Assert.assertEquals(expected, actual);
	}
	@Test
	public void nullReturn() { //no player has a card to disprove
		ourGame.getPlayers().get(3).getMyCards().remove(scarletCard);
		Card expected = null;
		Card actual = ourGame.handleSuggestion("Miss Scarlet", "Ballroom", "Wrench", ourGame.getPlayers().get(3));
		Assert.assertEquals(expected, actual);

	}*/


}

