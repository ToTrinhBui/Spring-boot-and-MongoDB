package book.data;

import org.springframework.data.mongodb.repository.MongoRepository;

import book.Book;

public interface BookRepository extends MongoRepository <Book, String>{

}
