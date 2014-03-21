package clueGame;

public class Suggestion {
	
	private String name;
	private String weapon;
	private String room;
	
	public Suggestion(String name, String weapon, String room){
		this.name = name;
		this.weapon = weapon;
		this.room = room;
	}

	public String getName() {
		return name;
	}

	public String getWeapon() {
		return weapon;
	}

	public String getRoom() {
		return room;
	}
	
	

}
