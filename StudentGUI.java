import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentGUI extends JFrame {

    // Form fields
    JTextField txtId, txtName, txtAge, txtCourse;

    // Table
    JTable table;
    DefaultTableModel model;

    // DAO
    StudentDAO dao = new StudentDAO();

    public StudentGUI() {

        setTitle("Student Management System");
        setSize(750, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ================= FORM PANEL =================
        JPanel formPanel = new JPanel(new GridLayout(2, 4, 10, 10));

        txtId = new JTextField();
        txtName = new JTextField();
        txtAge = new JTextField();
        txtCourse = new JTextField();

        formPanel.add(new JLabel("ID"));
        formPanel.add(new JLabel("Name"));
        formPanel.add(new JLabel("Age"));
        formPanel.add(new JLabel("Course"));

        formPanel.add(txtId);
        formPanel.add(txtName);
        formPanel.add(txtAge);
        formPanel.add(txtCourse);

        add(formPanel, BorderLayout.NORTH);

        // ================= TABLE =================
        String[] columns = {"ID", "Name", "Age", "Course"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        JScrollPane sp = new JScrollPane(table);
        add(sp, BorderLayout.CENTER);

        // ================= BUTTON PANEL =================
        JPanel buttonPanel = new JPanel();

        JButton btnAdd = new JButton("Add");
        JButton btnView = new JButton("View");
        JButton btnDelete = new JButton("Delete");
        JButton btnClear = new JButton("Clear");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnView);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        add(buttonPanel, BorderLayout.SOUTH);

        // ================= BUTTON ACTIONS =================

        // ADD STUDENT
        btnAdd.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                String name = txtName.getText();
                int age = Integer.parseInt(txtAge.getText());
                String course = txtCourse.getText();

                Student s = new Student(id, name, age, course);
                dao.addStudent(s);

                JOptionPane.showMessageDialog(this, "Student Added");
                clearFields();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid Input");
            }
        });

        // VIEW STUDENTS
        btnView.addActionListener(e -> {
            model.setRowCount(0); // clear table

            List<Student> students = dao.getAllStudents();

            for (Student s : students) {
                model.addRow(new Object[]{
                        s.id, s.name, s.age, s.course
                });
            }
        });

        // DELETE STUDENT
        btnDelete.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                boolean deleted = dao.deleteStudent(id);

                if (deleted)
                    JOptionPane.showMessageDialog(this, "Student Deleted");
                else
                    JOptionPane.showMessageDialog(this, "Student Not Found");

                clearFields();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Enter valid ID");
            }
        });

        // CLEAR FIELDS
        btnClear.addActionListener(e -> clearFields());

        // TABLE ROW CLICK → AUTO FILL
        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                txtId.setText(model.getValueAt(row, 0).toString());
                txtName.setText(model.getValueAt(row, 1).toString());
                txtAge.setText(model.getValueAt(row, 2).toString());
                txtCourse.setText(model.getValueAt(row, 3).toString());
            }
        });
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtAge.setText("");
        txtCourse.setText("");
    }

    public static void main(String[] args) {
        new StudentGUI().setVisible(true);
    }
}
