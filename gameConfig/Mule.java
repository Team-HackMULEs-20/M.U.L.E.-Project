package gameConfig;

public class Mule {
	
	private Type type;
	private Player owner;
	private int cost;
	
	public Mule(Type t) {
		type = t;
		cost = 100;
		
		if (type == Type.FOOD) {
			cost += 25;
		} else if (type == Type.ENERGY) {
			cost += 50;
		} else if (type == Type.SMITHORE) {
			cost += 75;
		} else if (type == Type.CRYSTITE) {
			cost += 100;
		}
		
	}

	public void setOwner(Player p) {
		owner = p;
	}
	
	public Player getOwner() {
		return owner;
	}
	
	public Type getType() {
		return type;
	}
	
	private enum Type {
		FOOD, ENERGY, SMITHORE, CRYSTITE
	}
	
}
