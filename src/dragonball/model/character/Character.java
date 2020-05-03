package dragonball.model.character;

import java.io.Serializable;

abstract public class Character implements Serializable  {
private String name;

public Character(String name){
	this.name=name;
}
public String getName(){
	return this.name;
}
}
