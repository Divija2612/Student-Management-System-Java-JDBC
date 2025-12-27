import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
public class StudentDAO {

    public void addStudent(Student s) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO students VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, s.id);
            ps.setString(2, s.name);
            ps.setInt(3, s.age);
            ps.setString(4, s.course);

            ps.executeUpdate();
            System.out.println("Student added successfully!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void viewStudents() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM students");

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " | " +
                    rs.getString("name") + " | " +
                    rs.getInt("age") + " | " +
                    rs.getString("course")
                );
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
	public void searchStudentById(int id) {
    try {
        Connection con = DBConnection.getConnection();
        String sql = "SELECT * FROM students WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println(
                rs.getInt("id") + " | " +
                rs.getString("name") + " | " +
                rs.getInt("age") + " | " +
                rs.getString("course")
            );
        } else {
            System.out.println("Student not found.");
        }

    } catch (Exception e) {
        System.out.println(e);
    }
}
public boolean deleteStudent(int id) {
    String sql = "DELETE FROM students WHERE id = ?";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, id);

        int rows = ps.executeUpdate();
        return rows > 0; // true if deleted

    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}
public List<Student> getAllStudents() {
    List<Student> list = new ArrayList<>();
    try {
        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM students");

        while (rs.next()) {
            list.add(new Student(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("course")
            ));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}



}
