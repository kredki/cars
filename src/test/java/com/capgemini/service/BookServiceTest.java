package com.capgemini.service;

import com.capgemini.types.AuthorTO;
import com.capgemini.types.AuthorTO.AuthorTOBuilder;
import com.capgemini.types.BookTO;
import com.capgemini.types.BookTO.BookTOBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@PropertySource("application-hsql.properties")
public class BookServiceTest {

	@Autowired
	private BookService bookService;

	@Test
	public void testShouldFindBookById() {

		// given
		String bookTitle = "Herr Tadeusz";
		AuthorTO author = new AuthorTOBuilder().withFirstName("Adam").withLastName("Mickiewicz").build();
		BookTO panTadeuszBook = new BookTOBuilder().withTitle(bookTitle).withAuthor(author).build();
		BookTO savedBook = bookService.saveBook(panTadeuszBook);

		// when
		BookTO selectedBook = bookService.findBookById(savedBook.getId());

		// then
		assertNotNull(selectedBook);
		assertEquals(savedBook.getAuthors(), selectedBook.getAuthors());
		assertEquals(savedBook.getTitle(), selectedBook.getTitle());
	}

	@Test
	public void testShouldFindBooksById() {

		// given
		String bookTitle = "Herr Tadeusz";
		AuthorTO author = new AuthorTOBuilder().withFirstName("Adam").withLastName("Mickiewicz").build();
		BookTO panTadeuszBook = new BookTOBuilder().withTitle(bookTitle).withAuthor(author).build();
		bookService.saveBook(panTadeuszBook);

		// when
		List<BookTO> selectedBooks = bookService.findBooksByTitle(bookTitle);

		// then
		assertNotNull(selectedBooks);
		assertFalse(selectedBooks.isEmpty());
		assertTrue(selectedBooks.stream().anyMatch(b -> b.getTitle().equals(bookTitle)));
	}

	@Test
	public void testShouldFindBooksByAuthor() {

		// given
		String bookTitle = "Herr Tadeusz";
		AuthorTO author = new AuthorTOBuilder().withFirstName("Adam").withLastName("Mickiewicz").build();
		BookTO panTadeuszBook = new BookTOBuilder().withTitle(bookTitle).withAuthor(author).build();
		BookTO savedBook = bookService.saveBook(panTadeuszBook);

		// when
		List<BookTO> selectedBooks = bookService.findBooksByAuthor(savedBook.getAuthors().iterator().next().getId());

		// then
		assertNotNull(selectedBooks);
		assertTrue(selectedBooks.stream().anyMatch(b -> b.getTitle().equals(bookTitle)));
	}
}
