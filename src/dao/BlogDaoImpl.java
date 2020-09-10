package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Blog;
import utility.ConnectionManager;

public class BlogDaoImpl implements BlogDaoInterface {

	@Override
	public void insertBlog(Blog blog) throws SQLException {
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String sql = "INSERT INTO BLOG(BLOG_ID, BLOG_TITLE, BLOG_DESCRIPTION, CURRENT_DATE) VALUES(?,?,?,?)";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, blog.getBlogId());
		preparedStatement.setString(2, blog.getBlogTitle());
		preparedStatement.setString(3, blog.getBlogDescription());
		preparedStatement.setDate(4, java.sql.Date.valueOf(blog.getPostedOn()));
		preparedStatement.executeUpdate();
		System.out.println("Inserted Successfully!!");
	}

	@Override
	public Blog selectBlog(int blogid) {
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String sql = "SELECT * FROM BLOG WHERE BLOG_ID = " + blogid;
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		ResultSet resultSet = null;
		try {
			resultSet = statement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Blog blog = null;
		try {
			while(resultSet.next()) {
			int id = resultSet.getInt(1);
			String title = resultSet.getString(2);
			String desc = resultSet.getString(3);
			LocalDate date = resultSet.getDate(4).toLocalDate();
			blog = new Blog(id, title, desc, date);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return blog;
	}

	@Override
	public List<Blog> selectAllBlogs() {
		List<Blog> list = new ArrayList<Blog>();
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String sql = "SELECT * FROM BLOG";
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		ResultSet resultSet = null;
		try {
			resultSet = statement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Blog blog = null;
		try {

			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String title = resultSet.getString(2);
				String desc = resultSet.getString(3);
				LocalDate date = resultSet.getDate(4).toLocalDate();
				blog = new Blog(id, title, desc, date);
				list.add(blog);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean deleteBlog(int id) throws SQLException {
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String sql = "DELETE FROM BLOG WHERE BLOG_ID = " + id;
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		boolean resultSet = false;
		try {
			resultSet = statement.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultSet;
	}

	@Override
	public boolean updateBlog(Blog blog) throws SQLException, Exception {
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String sql = "UPDATE BLOG SET BLOG_TITLE = ?, BLOG_DESCRIPTION = ?, CURRENT_DATE=? WHERE BLOG_ID = "
				+ blog.getBlogId();

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, blog.getBlogTitle());
		preparedStatement.setString(2, blog.getBlogDescription());
		preparedStatement.setDate(3, java.sql.Date.valueOf(blog.getPostedOn()));
		int row = preparedStatement.executeUpdate();

		if (row != 0) {
			System.out.println("Updated Successfully!!");
			return true;
		} else {
			return false;
		}

	}

}
