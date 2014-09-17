package kol.kolwiki;

public class Item extends Thing {

	private String name;
	private int id;
	private String description;
	private String itemType;
	private int sellPrice;
	private boolean tradable;
	private boolean discardable;
	private boolean questItem;
	private String location;
	private String requirement;
	private int power;
	private int size;
	private String adventures;
	private String stats;
	private String enchantment;
	private String duration;
	private String quality;

	public String getName() { return name; }
	public int getId() { return id; }
	public String getDescription() { return description; }
	public String getItemType() { return itemType; }
	public int getSellPrice() {	return sellPrice; }
	public boolean isTradable() { return tradable; }
	public boolean isDiscardable() { return discardable; }
	public boolean isQuestItem() { return questItem; }
	public String getLocation() { return location; }
	public String getRequirement() { return requirement; }
	public int getPower() { return power; }
	public int getSize() { return size; }
	public String getAdventures() { return adventures; }
	public String getStats() { return stats; }
	public String getEnchantment() { return enchantment; }
	public String getDuration() { return duration; }
	public String getQuality() { return quality; }
	
	public void setName(String name) { this.name = name; }
	public void setId(int id) {	this.id = id; }
	public void setDescription(String description) { this.description = description; }
	public void setItemType(String itemType) { this.itemType = itemType; }
	public void setSellPrice(int sellPrice) { this.sellPrice = sellPrice; }
	public void setTradable(boolean tradable) { this.tradable = tradable; }
	public void setDiscardable(boolean discardable) { this.discardable = discardable; }
	public void setQuestItem(boolean questItem) { this.questItem = questItem; }
	public void setLocation(String location) { this.location = location; }
	public void setRequirement(String requirement) { this.requirement = requirement; }
	public void setPower(int power) { this.power = power; }
	public void setSize(int size) { this.size = size; }
	public void setAdventures(String adventures) { this.adventures = adventures; }
	public void setStats(String stats) { this.stats = stats; }
	public void setEnchantment(String enchantment) { this.enchantment = enchantment; }
	public void setDuration(String duration) { this.duration = duration; }
	public void setQuality(String quality) { this.quality = quality; }

	@Override
	public String sideAText() {	
		return name + '\n' + itemType;
	}

	@Override
	public String sideBText() {
		return description;
	}
	
}
