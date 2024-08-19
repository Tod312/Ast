package composite;

import java.util.ArrayList;
import java.util.List;

public class Paragraph implements LibraryComponent{

	private String name;
	
	List<LibraryComponent> pages = new ArrayList<LibraryComponent>();

	public Paragraph(String name) {
		this.name = name;
	}

	
	@Override
	public void display() {
		System.out.println("  Paragraph name : " + name);
		pages.forEach(p -> p.display());
	}


	public List<LibraryComponent> getPages() {
		return pages;
	}
	
	public void add(LibraryComponent page) {
			
		}
	public void add(List<Page> pages) {
		this.pages.addAll(pages);
	}



	public String getName() {
		return name;
	}

}
