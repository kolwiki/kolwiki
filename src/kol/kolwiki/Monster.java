package kol.kolwiki;

public class Monster extends Thing {
	
	private int hp;
	private int attack;
	private int defense;
	
	public int getHp() { return hp; }
	public int getAttack() { return attack; }
	public int getDefense() { return defense; }
	public void setHp(int hp) { this.hp = hp; }
	public void setAttack(int attack) { this.attack = attack; }
	public void setDefense(int defense) { this.defense = defense; }	
		
	public Monster() {
		
	}
	
	public Monster(int id, String name, int hp, int attack, int defense) {
		this.setType(ThingType.MONSTER);
		this.setName(name);
		this.setId(id);
		this.hp = hp;
		this.attack = attack;
		this.defense = defense;
	}
	
	public String sideAText() {
		return "HP: " + hp + "\nAttack: " + attack + "\nDefense: " + defense;
	}

	public String sideBText() {
		return "Side B text goes here";
	}
}
