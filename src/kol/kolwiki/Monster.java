package kol.kolwiki;

public class Monster {
	private int id;
	private String name;
	private int hp;
	private int attack;
	private int defense;
	
	public int getId() { return id; }
	public String getName() { return name; }
	public int getHp() { return hp; }
	public int getAttack() { return attack; }
	public int getDefense() { return defense; }
	public void setId(int id) { this.id = id; }
	public void setName(String name) { this.name = name; }
	public void setHp(int hp) { this.hp = hp; }
	public void setAttack(int attack) { this.attack = attack; }
	public void setDefense(int defense) { this.defense = defense; }	
		
	public Monster() {
		
	}
	
	public Monster(int id, String name, int hp, int attack, int defense) {
		this.id = id;
		this.name = name;
		this.hp = hp;
		this.attack = attack;
		this.defense = defense;
	}
}
