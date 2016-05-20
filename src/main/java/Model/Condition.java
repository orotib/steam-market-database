package Model;

public enum Condition {
	BATTLE("Battle-Scarred"),
	WELL("Well-Worn"),
	FIELD("Field-Tested"),
	MINIMAL("Minimal Wear"),
	FACTORY("Factory New");
	private String value;
	
	private Condition(String value) {
		this.setValue(value);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
