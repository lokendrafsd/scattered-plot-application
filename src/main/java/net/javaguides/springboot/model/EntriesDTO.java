package net.javaguides.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class EntriesDTO {


	private long id;
	
	
	private Double x;

	
	private Double y;

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Double getX() {
		return x;
	}


	public void setX(Double x) {
		this.x = x;
	}


	public Double getY() {
		return y;
	}


	public void setY(Double y) {
		this.y = y;
	}
	
}
