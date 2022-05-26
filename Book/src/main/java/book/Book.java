package book;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "Book")
@Getter
@Setter
public class Book {
	@Id
	private String id;
	private String name;
	private String author;
	private String type;
	private String content;
	private Long price;
}
