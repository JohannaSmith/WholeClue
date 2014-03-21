package clueGame;

public class Suggestion{
	
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


	//Working equals method
	public boolean equals(Suggestion other) {
		
		if(this == other)
			return true;
		if(other == null)
			return false;
		if (getClass() != other.getClass())
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;
		if (weapon == null) {
			if (other.weapon != null)
				return false;
		} else if (!weapon.equals(other.weapon))
			return false;
		return true;
	}

	
}
