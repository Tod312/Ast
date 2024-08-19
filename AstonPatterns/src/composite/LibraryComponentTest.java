package composite;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LibraryComponentTest {

	public static void main(String[] args) {
		
		Book book = new Book("Earth");
		Paragraph paragraph1 = new Paragraph("Earth in the sky");
		Paragraph paragraph2 = new Paragraph("Earth under water");
	
		paragraph1.add(Stream.iterate(1, seed -> seed + 1).limit(5).map(number -> new Page(number)).toList());// добавление страниц в параграф
		paragraph2.add(IntStream.of(6,7,8,9,10).mapToObj(integer -> new Page(integer)).toList());// добавление страниц в параграф
		
		book.add(paragraph1);
		book.add(paragraph2);
		
		book.display();
	}
}
