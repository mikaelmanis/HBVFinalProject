package is.hi.hbv202g.assignment8;

import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Unit tests for the Library System.
 */
public class MainTest {

    @Test
    public void shouldBePossibleToInstantiateLibrarySystem() {
        LibrarySystem librarySystem = new LibrarySystem();
        assertNotNull(librarySystem);
    }

    @Test
    public void shouldBePossibleToInstantiateAuthor() {
        Author author = new Author("John Doe");
        assertEquals("John Doe", author.getName());
    }

    @Test
    public void shouldAddStudentUserToLibrarySystem() {
        LibrarySystem librarySystem = new LibrarySystem();
        librarySystem.addStudentUser("Alice", true);
        assertEquals(1, librarySystem.getUsers().size());
    }

    @Test
    public void shouldAddFacultyMemberToLibrarySystem() {
        LibrarySystem librarySystem = new LibrarySystem();
        librarySystem.addFacultyMemberUser("Dr. Smith", "Computer Science");
        assertEquals(1, librarySystem.getUsers().size());
    }

    @Test
    public void shouldAddBookWithSingleAuthor() throws EmptyAuthorListException {
        LibrarySystem librarySystem = new LibrarySystem();
        Integer oldSize = librarySystem.getBooks().size();
        librarySystem.addBookWithTitleAndNameOfSingleAuthor("Book Title", "John Doe");
        assertEquals(Optional.of(oldSize), librarySystem.getBooks().size());
    }

    @Test
    public void shouldAddBookWithMultipleAuthors() throws EmptyAuthorListException, IOException {
        LibrarySystem librarySystem = new LibrarySystem();
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("Author One"));
        authors.add(new Author("Author Two"));
        librarySystem.addBookWithTitleAndAuthorList("Multi-Author Book", authors);
        assertEquals(1, librarySystem.getBooks().size());
    }

    @Test
    public void shouldFindBookByTitle() throws EmptyAuthorListException, UserOrBookDoesNotExistException {
        LibrarySystem librarySystem = new LibrarySystem();
        librarySystem.addBookWithTitleAndNameOfSingleAuthor("Book Title", "John Doe");
        Book book = librarySystem.findBookByTitle("Book Title");
        assertNotNull(book);
    }

    @Test
    public void shouldFindUserByName() throws UserOrBookDoesNotExistException {
        LibrarySystem librarySystem = new LibrarySystem();
        librarySystem.addStudentUser("Alice", true);
        User user = librarySystem.findUserByName("Alice");
        assertNotNull(user);
    }

    @Test
    public void shouldBorrowBook() throws EmptyAuthorListException, UserOrBookDoesNotExistException {
        LibrarySystem librarySystem = new LibrarySystem();
        librarySystem.addStudentUser("Alice", true);
        librarySystem.addBookWithTitleAndNameOfSingleAuthor("Book Title", "John Doe");
        User user = librarySystem.findUserByName("Alice");
        Book book = librarySystem.findBookByTitle("Book Title");
        librarySystem.borrowBook(user, book);
        assertEquals(1, librarySystem.getLendings().size());
    }

    @Test
    public void shouldReturnBook() throws EmptyAuthorListException, UserOrBookDoesNotExistException {
        LibrarySystem librarySystem = new LibrarySystem();
        librarySystem.addStudentUser("Alice", true);
        librarySystem.addBookWithTitleAndNameOfSingleAuthor("Book Title", "John Doe");
        User user = librarySystem.findUserByName("Alice");
        Book book = librarySystem.findBookByTitle("Book Title");
        librarySystem.borrowBook(user, book);
        librarySystem.returnBook(user, book);
        assertEquals(0, librarySystem.getLendings().size());
    }

    @Test
    public void shouldExtendLendingForFacultyMember() throws EmptyAuthorListException, UserOrBookDoesNotExistException {
        LibrarySystem librarySystem = new LibrarySystem();
        librarySystem.addFacultyMemberUser("Dr. Smith", "Computer Science");
        librarySystem.addBookWithTitleAndNameOfSingleAuthor("Book Title", "John Doe");
        FacultyMember faculty = (FacultyMember) librarySystem.findUserByName("Dr. Smith");
        Book book = librarySystem.findBookByTitle("Book Title");
        librarySystem.borrowBook(faculty, book);
        LocalDate newDueDate = LocalDate.now().plusDays(30);
        librarySystem.extendLending(faculty, book, newDueDate);
        assertEquals(newDueDate, librarySystem.getLendings().get(0).getDueDate());
    }
}
