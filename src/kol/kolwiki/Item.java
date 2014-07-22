package kol.kolwiki;

public class Item {
	private String name;
	private String type;
	private String damage;
	
	public String getName() { return name; }
	public String getType() { return type; }
	public String getDamage() { return damage; }
	public void setName(String name) { this.name = name; }
	public void setType(String type) { this.name = type; }
	public void setDamage(String damage) { this.name = damage; }
		
	public Item() {
		
	}
	
	public Item(String name, String type, String damage) {
		this.name = name;
		this.type = type;
		this.damage = damage;
	}
}
