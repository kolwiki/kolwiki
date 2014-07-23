package kol.kolwiki;

/*
 * Superclass for objects that are displayable in a list format
 * eg. monsters, items
 */

public abstract class Thing {
	public enum ThingType { MONSTER, ITEM };
	
	private String name;
	private String extra; // extra information sometimes displayed in Thing Viewer eg. ML or something // TODO maybe make equivalent of sideAText() to give string	
	private int id;
	private ThingType type;
	
	public ThingType getType() { return type; }
	public String getName() { return name; }
	public String getExtra() { return extra; }
	public int getId() { return id; }
	public void setType(ThingType type) { this.type = type; } 
	public void setName(String name) { this.name = name; }
	public void setExtra(String extra) { this.extra = extra; }
	public void setId(int id) { this.id = id; }
	
	public Thing() {
		
	}
	
	public Thing(ThingType type, String name, String extra) {
		this.type = type;
		this.name = name;
		this.name = extra;
	}
	
	public abstract String sideAText();
	public abstract String sideBText();	
}
