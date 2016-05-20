package Model;

public class Skin implements Comparable<Skin> {
	private String collection;
	private Type type;
	private String weapon;
	private String skin;
	private Condition condition;
	private double avgPrice;

	public Skin(String collection, Type type, String weapon, String skin, Condition condition, double avgPrice) {
		super();
		this.collection = collection;
		this.type = type;
		this.weapon = weapon;
		this.skin = skin;
		this.condition = condition;
		this.avgPrice = avgPrice;
	}

	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getWeapon() {
		return weapon;
	}

	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}

	public String getSkin() {
		return skin;
	}

	public void setSkin(String skin) {
		this.skin = skin;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public double getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(double avgPrice) {
		this.avgPrice = avgPrice;
	}

	@Override
	public String toString() {
		return new StringBuilder().append(collection).append(" ")
								  .append(type.getValue()).append(" ")
								  .append(weapon).append(" ")
								  .append(skin).append(" ")
								  .append(condition.getValue()).append(" ")
								  .append(avgPrice).toString();
	}

	public int compareTo(Skin skin) {
		return skin.avgPrice > avgPrice ? 1 : skin.avgPrice < avgPrice ? -1 : 0;
	}

}
