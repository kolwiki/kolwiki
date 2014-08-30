package kol.kolwiki;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;

public class Filter {
	private static final String TAG = "Filter";

	private Thing.ThingType type;
	//private Map<String, String> restrictions; // eg. 'Location' => 'Sleazy back alley'
	private List<Restriction> restrictions;
	private List<Thing> thingList;
	private List<Thing> originalThingList;

	public List<Thing> getList() { return thingList; }
	public List<Restriction> getRestrictions() { return restrictions; }
	public void setRestrictions(List<Restriction> restrictions) { this.restrictions = restrictions; }
	
	public enum RestrictionType { EQUAL, NOTEQUAL, GREATERTHAN, LESSTHAN, GREATEREQUALTHAN, LESSEQUALTHAN };

	public Filter(List<Thing> thingList) {
		this.thingList = thingList;
		if (thingList.size() > 0) {
			this.type = thingList.get(0).getType();
		}		
		this.restrictions = new ArrayList<Restriction>();
		this.originalThingList = new ArrayList<Thing>(thingList);
	}

	public void addRestriction(String key, String value, RestrictionType type) {
		Restriction restriction = new Restriction(key, value, type);
		restrictions.add(restriction);
		if (this.type == Thing.ThingType.MONSTER) {
			applyMonsterRestriction(restriction);
		}
	}
		
	public void removeRestriction(String key, String value, RestrictionType type) {		
		restrictions.remove(new Restriction(key, value, type));
		// reset thingList and reapply other permissions		
		thingList = new ArrayList<Thing>(originalThingList);
		for (Restriction aRestriction : restrictions) {
			applyMonsterRestriction(aRestriction);
		}
	}

	private void applyMonsterRestriction(Restriction restriction) {
		List<Thing> newThingList = new ArrayList<Thing>();

		for (Thing thing : thingList) {
			Monster monster = (Monster) thing;
			String monsterValue = monster.getProperty(restriction.key);
			
			Log.d(TAG, monsterValue + ", " + restriction.value);
			
			switch(restriction.type) {
			case EQUAL:
				if (monsterValue.equals(restriction.value)) {
					newThingList.add(monster);
				}
				break;
			case NOTEQUAL:
				if (!monsterValue.equals(restriction.value)) {
					newThingList.add(monster);
				}
				break;
			case GREATERTHAN:
				if (Integer.parseInt(monsterValue) > Integer.parseInt(restriction.value)) {
					newThingList.add(monster);
				}
				break;
			case LESSTHAN:
				if (Integer.parseInt(monsterValue) < Integer.parseInt(restriction.value)) {
					newThingList.add(monster);
				}
				break;
			case GREATEREQUALTHAN:
				if (Integer.parseInt(monsterValue) >= Integer.parseInt(restriction.value)) {
					newThingList.add(monster);
				}
				break;
			case LESSEQUALTHAN:
				if (Integer.parseInt(monsterValue) <= Integer.parseInt(restriction.value)) {
					newThingList.add(monster);
				}
				break;			
			}
		}
		thingList = newThingList;
	}
		
	protected class Restriction {
		protected String key;
		protected String value;
		protected RestrictionType type;
		
		public Restriction(String key, String value, RestrictionType type) {
			this.key = key;
			this.value = value;
			this.type = type;
		}
	}
}
