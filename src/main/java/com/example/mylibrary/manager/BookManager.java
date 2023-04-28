package com.example.mylibrary.manager;
import com.example.mylibrary.db.DBConnectionProvider;
import com.example.mylibrary.model.Author;
import com.example.mylibrary.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BookManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    AuthorManager authorManager=new AuthorManager();
    public void save(Book book) {
            String sql = "INSERT INTO book(title,description,price,author_id) VALUES(?,?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, book.getTitle());
                ps.setString(2, book.getDescription());
                ps.setInt(3,book.getPrice());
                ps.setInt(4,book.getAuthor().getId());
                ps.executeUpdate();
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    book.setId(generatedKeys.getInt(1));
                }
                System.out.println("Book inserted into DB");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * from book");
            while (resultSet.next()) {
                books.add(getBookFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }


    public Book getById(int id) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * from book where id=" + id);
            if (resultSet.next()) {
                return getBookFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Book> getAllByBookName(String title) {
        List<Book> books = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM book WHERE title = ?")) {
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                books.add(getBookFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public void removeById(int bookId) {
        String sql = "DELETE FROM book where id=" + bookId;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Book getBookFromResultSet(ResultSet resultSet) throws SQLException, SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt("id"));
        book.setTitle(resultSet.getString("title"));
        book.setDescription(resultSet.getNString("description"));
        book.setPrice(resultSet.getInt("price"));
        int authorId =resultSet.getInt("author_id");
        book.setAuthor(authorManager.getById(authorId));
        return book;
    }


        public void update(Book book) {
        String sql = "UPDATE book SET title=?,description=?,price=?,author_id=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,book.getTitle());
            statement.setString(2, book.getDescription());
            statement.setInt(3,book.getPrice());
            statement.setInt(4,book.getAuthor().getId());
            statement.setInt(5,book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
