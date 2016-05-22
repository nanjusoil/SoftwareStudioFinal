package main.java;

public enum Type{
	CONTROL,
	TOOL,    //click->can be used, used in the right way->disappear
	MESSAGE,  //click->show message image, open safe deposit box->disappear
	FURNITURE //things that cannot be moved, such as table, picture or statues
				//but maybe player can interactive with them
}
