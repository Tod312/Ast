package composite;

import java.util.ArrayList;
import java.util.List;

public class Book implements LibraryComponent{

	private String name;

	private List<LibraryComponent> paragraphs = new ArrayList<LibraryComponent>();
	
	public Book(String name){
		this.name = name;
	}
	
	public void add(LibraryComponent component) {
		paragraphs.add(component);
	}
	
	@Override
	public void display() {
		System.out.println("Book name : " + name);
		paragraphs.forEach(p -> p.display());
		
	}

	public List<LibraryComponent> getParagraphs() {
		return paragraphs;
	}

	public void setParagraphs(List<LibraryComponent> paragraphs) {
		this.paragraphs = paragraphs;
	}

	public String getName() {
		return name;
	}
	
	
}
