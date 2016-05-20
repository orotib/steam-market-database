package Model;

public enum Type {
	NORMAL("Normal"),
	STATTRAK("StatTrak"),
	SOUVENIR("Souvenir");
	private String value;
	
	private Type(String value) {
		this.setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
