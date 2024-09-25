package org.kohsuke.github;

public enum Enforcement {

	DISABLED("disabled"), ACTIVE("active"), EVALUATE("evaluate");

	private String value;

	Enforcement(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
