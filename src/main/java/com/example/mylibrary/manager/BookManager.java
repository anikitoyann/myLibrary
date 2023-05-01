package com.example.mylibrary.manager;
import com.example.mylibrary.db.DBConnectionProvider;
import com.example.mylibrary.model.Book;
import com.example.mylibrary.model.User;
import com.example.mylibrary.model.UserType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BookManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    AuthorManager authorManager=new AuthorManager();
    public void save(Book book) {
            String sql = "INSERT INTO book(title,description,price,pic_name,author_id) VALUES(?,?,?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, book.getTitle());
                ps.setString(2, book.getDescription());
                ps.setInt(3,book.getPrice());
                ps.setString(4, book.getPicName());
                ps.setInt(5,book.getAuthor().getId());
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


    public List<Book> search(String keyword) {
        List<Book> books = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM book WHERE title like ?")) {
            keyword="%"+keyword+"%";
            statement.setString(1, keyword);
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
        book.setPicName(resultSet.getString("pic_name"));
        int authorId =resultSet.getInt("author_id");
        book.setAuthor(authorManager.getById(authorId));
        return book;
    }
    public List<Book> getBooksByUser(User user) {
        List<Book> userBooks = new ArrayList<>();
        String sql = "SELECT * FROM book WHERE added_by_user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = getBookFromResultSet(resultSet);
                userBooks.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userBooks;
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
