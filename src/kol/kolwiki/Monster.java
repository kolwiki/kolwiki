package kol.kolwiki;

public class Monster extends Thing {
	
	private String name;
	private int id;
	private int hp;
	private int attack;
	private int defense;
	private int safeMoxie;
	private int initiative;
	private String element;
	private String resistance;
	private float meat;
	private String phylum;
	private String description;
	private String items;
	private String location;
	private String url;
	
	public int getHp() { return hp; }
	public int getAttack() { return attack; }
	public int getDefense() { return defense; }
	public String getName() { return name; }
	public int getId() { return id; }
	public int getSafeMoxie() { return safeMoxie; }
	public int getInit() { return initiative; }
	public String getElement() { return element; }
	public String getResistance() { return resistance; }
	public float getMeat() { return meat; }
	public String getPhylum() { return phylum; }
	public String getDescription() { return description; }
	public String getItems() { return items; }	
	public String getLocation() { return location; }
	public String getUrl() { return url; }
	
	public void setHp(int hp) { this.hp = hp; }
	public void setAttack(int attack) { this.attack = attack; }
	public void setDefense(int defense) { this.defense = defense; }
	public void setName(String name) { this.name = name; }
	public void setId(int id) { this.id = id; }
	public void setSafeMoxie(int safeMoxie) { this.safeMoxie = safeMoxie; }
	public void setInit(int initiative) { this.initiative = initiative; }
	public void setElement(String element) { this.element = element; }
	public void setResistance(String resistance) { this.resistance = resistance; }
	public void setMeat(float meat) { this.meat = meat; }
	public void setPhylum(String phylum) { this.phylum = phylum; }
	public void setDescription(String description) { this.description = description; }
	public void setItems(String items) { this.items = items; }	
	public void setLocation(String location) { this.location = location; }	
	public void setUrl(String url) { this.url = url; }
			
	public Monster() {
		
	}
	
	public Monster(int id, String name, int hp, int attack, int defense, int safeMoxie, int initiative, String element, String resistance,
			float meat, String phylum, String description, String items, String location, String url) {
		this.setType(ThingType.MONSTER);
		this.setName(name);
		this.setId(id);
		this.hp = hp;
		this.attack = attack;
		this.defense = defense;
		this.safeMoxie = safeMoxie;
		this.initiative = initiative;
		this.element = element;
		this.resistance = resistance;
		this.meat = meat;
		this.phylum = phylum;
		this.description = description;
		this.items = items;
		this.location = location;
		this.url = url;
	}
	
	public String sideAText() {
		return description + "\n\nFound: " + location + "\n\nDrops: " + items;
	}

	public String sideBText() {
		return "HP: " + hp + "\nAttack: " + this.attack + "\nDefense: " + defense + "\nSafe moxie: " + safeMoxie +
				"\nInitiative: " + initiative + "\nMeat: " + meat +"\n\nElement: " + element + "\nResistances: " + resistance + 
				"\nPhylum: " + phylum;
	}
	
	public String getProperty(String key) {
		switch(key) {
		case "Name": return name;
		case "Id": return Integer.toString(id);
		case "Attack": return Integer.toString(attack);
		case "Defense": return Integer.toString(defense);
		case "Safe moxie": return Integer.toString(safeMoxie);
		case "Initiative": return Integer.toString(initiative);
		case "Element":	return element;
		case "Resistance": return resistance;
		case "Meat": return Float.toString(meat);
		case "Phylum": return phylum;
		case "Description": return description;
		case "Items": return items;
		case "Location": return location;
		case "URL": return url;
		default: return null;
		}
	}
}
