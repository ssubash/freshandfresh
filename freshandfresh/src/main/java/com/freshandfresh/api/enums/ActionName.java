package com.freshandfresh.api.enums;

public enum ActionName {
	
	PENDING("Pending"), 
	CANCEL("Cancel"), 
	ACCEPT("Accept"),
	REJECT("Reject"),
	ASSIGN("Assign"),
	DISPATCH("Dispatch"),
	CLOSE("Close");
	
	
	private String name;
	
	ActionName(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
