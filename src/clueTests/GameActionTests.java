package clueTests;

import java.awt.Color;
import java.util.ArrayList;





import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.BoardCell;
import clueGame.Card;
import clueGame.ComputerPlayer;
import clueGame.Game;
import clueGame.Player;
import clueGame.RoomCell;
import clueGame.RoomCell.DoorDirection;
import clueGame.Suggestion;

public class GameActionTests {

	private static Game ourGame;
	
	@BeforeClass
	public static void setup() {
		ourGame = new Game();
		ourGame.loadConfigFiles("./ourboardfiles/StartCharacters.txt", "./ourboardfiles/Weapons.txt");
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
		BoardCell bc = ourGame.getGameBoard().getCellAt(3, 6);
		System.out.println(((RoomCell) bc).getInitial());
		int loc_2_7Count = 0;
		int loc_4_7Count = 0;
		int loc_3_8Count = 0;
		BoardCell location;
		// loops through multiple times to make sure the room just visited is not picked
		for (int i = 0; i < 95; i++) {
			location = computerTestPlayer.pickLocation(ourGame.getGameBoard().getTargets());
			if (location == ourGame.getGameBoard().getCellAt(2, 7))
				loc_2_7Count++;
			else if (location == ourGame.getGameBoard().getCellAt(4, 7))
				loc_4_7Count++;
			else if (location == ourGame.getGameBoard().getCellAt(3, 8))
				loc_3_8Count++;
			else
				Assert.fail("Cannot go back into room");
		}
		Assert.assertEquals(95, loc_2_7Count + loc_4_7Count + loc_3_8Count);
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
		
		//Test for the four possibilities of suggestions by individual field
		Assert.assertEquals(room, actual.getRoom());// Room should always equal the suggesting player's room
		//the suggested name can be either of these two names
		Assert.assertTrue(actual.getName().equals("Miss Scarlet") || actual.getName().equals("Mrs. Peacock")); 
		//the suggested weapon can equal either of these two weapons
		Assert.assertTrue(actual.getWeapon().equals("Wrench") || actual.getWeapon().equals("Rope"));
		
		
	}
	
}
