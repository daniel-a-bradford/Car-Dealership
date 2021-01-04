package com.cardealer.entity;

// Class for use in storing the selection "checked" of HTML check boxes boxes and radio and the associated style.
public class StyleAndChecked {
	
	private String style = "";
	private boolean checked = false;
	
	public StyleAndChecked(String style, boolean checked) {
		super();
		this.style = style;
		this.checked = checked;
	}
	
	public String getStyle() {
		return this.style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getChecked() {
		if (this.checked)
			return "checked";
		return "";
	}
	public void setChecked(boolean checked) {
		if (checked)
			this.checked = checked;
	}
	
	

}
