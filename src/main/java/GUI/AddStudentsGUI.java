/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BUS.CourseBUS;
import BUS.StudentBUS;
import DAL.LecturerDAL;
import BUS.StudentGradeBUS;
import DAL.CourseDAL;
import DAL.StudentDAL;
import DAL.StudentGradeDAL;
import DTO.StudentDTO;
import DTO.CourseDTO;
import DTO.StudentGradeDTO;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author nqk09
 */
public class AddStudentsGUI extends javax.swing.JFrame {

    StudentDTO studentDTO = new StudentDTO();
    StudentDAL studentDAL = new StudentDAL();
    CourseDAL courseDAL = new CourseDAL();
    LecturerDAL lecturerDAL = new LecturerDAL();
    CourseBUS courseBUS = new CourseBUS(courseDAL, lecturerDAL);
    StudentGradeDAL studentGradeDAL = new StudentGradeDAL(studentDAL, courseDAL);
    StudentBUS studentBUS = new StudentBUS(studentDAL);
    StudentGradeBUS studenGradeBUS = new StudentGradeBUS(studentGradeDAL, courseDAL);
    List<StudentDTO> listStudent = new ArrayList<>();
    List<StudentDTO> list = new ArrayList<>();
    int courceID;

    /**
     * Creates new form AddStudentsGUI
     */
    public AddStudentsGUI(int courseID) {
        list = new ArrayList<>();
        this.courceID = courseID;
        initComponents();
        loadDataToAddedTable();
        loadDataToListTable();
        tableAddCustomizer();
        tableListCustomizer();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_AddedStudents = new javax.swing.JTable();
        jTextField_Search = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_ListOfStudent = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(666, 627));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(11, 8, 171));
        jLabel1.setText("Thêm học viên");

        jTable_AddedStudents.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTable_AddedStudents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable_AddedStudents.setRowHeight(48);
        jTable_AddedStudents.setShowHorizontalLines(true);
        jTable_AddedStudents.setShowVerticalLines(true);
        jScrollPane1.setViewportView(jTable_AddedStudents);

        jTextField_Search.setBackground(new java.awt.Color(255, 255, 255));
        jTextField_Search.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTextField_Search.setForeground(new java.awt.Color(204, 204, 204));
        jTextField_Search.setText("  Tìm kiếm");
        jTextField_Search.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTextField_Search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_SearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_SearchFocusLost(evt);
            }
        });
        jTextField_Search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_SearchKeyReleased(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-plus-18.png"))); // NOI18N
        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable_ListOfStudent.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTable_ListOfStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable_ListOfStudent.setRowHeight(48);
        jTable_ListOfStudent.setShowHorizontalLines(true);
        jTable_ListOfStudent.setShowVerticalLines(true);
        jScrollPane2.setViewportView(jTable_ListOfStudent);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(264, 264, 264)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jTextField_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_Search, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thêm những sinh viên đã chọn?", "Thêm sinh viên", JOptionPane.YES_NO_OPTION);
        if (result == 0) {
            TableModel model = jTable_ListOfStudent.getModel();
            for (int row = 0; row < model.getRowCount(); row++) {
                if (model.getValueAt(row, 4).toString().contains("/Icon/icons8-checkbox-24.png")) {
                    int studentID = Integer.parseInt(model.getValueAt(row, 0).toString());
                    StudentDTO student = (StudentDTO) studentBUS.getAnObjectByID(studentID);
                    CourseDTO cource = (CourseDTO) courseBUS.getACourseByID(courceID);
                    StudentGradeDTO studentGradeDTO = new StudentGradeDTO(0, 0.0, new StudentDTO(studentID), cource);
                    studenGradeBUS.insertObject(studentGradeDTO);
                }
            }
            loadDataToAddedTable();
            loadDataToListTable();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField_SearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_SearchKeyReleased
        DefaultTableModel model = (DefaultTableModel) jTable_ListOfStudent.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(model);
        jTable_ListOfStudent.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter("(?i)" + jTextField_Search.getText()));
    }//GEN-LAST:event_jTextField_SearchKeyReleased

    private void jTextField_SearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_SearchFocusGained
        if (jTextField_Search.getText().equals("  Tìm kiếm")) {
            jTextField_Search.setText("");
            jTextField_Search.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_jTextField_SearchFocusGained

    private void jTextField_SearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_SearchFocusLost
        jTextField_Search.setText("  Tìm kiếm");
        jTextField_Search.setForeground(new Color(204, 204, 204));
    }//GEN-LAST:event_jTextField_SearchFocusLost

    /**
     * @param args the command line arguments
     */
    

    private boolean isExist(int id) {
        TableModel model = jTable_AddedStudents.getModel();
        for (int row = 0; row < model.getRowCount(); row++) {
            int existingID = Integer.parseInt(model.getValueAt(row, 0).toString());
            if (id == existingID) {
                return true;
            }
        }
        return false;
    }

    private void deleteObj() {
        int result = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa sinh viên đã chọn?", "Xóa sinh viên", JOptionPane.YES_NO_OPTION);
        if (result == 0) {
            int i = jTable_AddedStudents.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) jTable_AddedStudents.getModel();
            int id = Integer.parseInt(model.getValueAt(i, 0).toString());
            List<StudentGradeDTO> studentGradeDTOs = studenGradeBUS.getStudentGradesOfCCourse(courceID);
            for (StudentGradeDTO studentGradeDTO : studentGradeDTOs) {
                if (studentGradeDTO.getStudent().getID() == id) {
                    int delResult = studenGradeBUS.removeObject(studentGradeDTO.getEnrollmentID());
                    if (delResult > 0) {
                        JOptionPane.showMessageDialog(this, "Xóa thành công!");
                        loadDataToAddedTable();
                        loadDataToListTable();
                    }else{
                        JOptionPane.showMessageDialog(this, "Không thành công!");

                    }
                }
            }
        }
    }

    private void tableAddCustomizer() {
        JTableHeader header = jTable_AddedStudents.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 48));
        header.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setBorder(BorderFactory.createLineBorder(Color.black));
                setHorizontalAlignment(JLabel.CENTER);
                return this;
            }
        };
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        jTable_AddedStudents.setDefaultRenderer(String.class, centerRenderer);
        headerRenderer.setBackground(new Color(68, 68, 68));
        headerRenderer.setForeground(Color.WHITE);
        for (int i = 0; i < jTable_AddedStudents.getModel().getColumnCount(); i++) {
            jTable_AddedStudents.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
    }

    private void loadDataToAddedTable() {
        list = studentBUS.getAllStudentsOfACourse(courceID);
        Collections.sort(list, Comparator.comparingInt(StudentDTO::getID));
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 4:
                        return Icon.class;
                    default:
                        return Object.class;
                }
            }
        };
        model.addColumn("ID");
        model.addColumn("Họ");
        model.addColumn("Tên");
        model.addColumn("Ngày");
        model.addColumn("Xóa");
        jTable_AddedStudents.setModel(model);

        for (StudentDTO studentDTO : list) {
            LocalDateTime localDateTime = studentDTO.getEnrollmentDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String formattedDate = localDateTime.format(formatter);
            ImageIcon icon = new ImageIcon(getClass().getResource("/Icon/icons8-delete-24.png"));
            model.addRow(new Object[]{studentDTO.getID(), studentDTO.getFirstName(), studentDTO.getLastName(), formattedDate, icon});
        }
        
        jTable_AddedStudents.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = jTable_AddedStudents.rowAtPoint(e.getPoint());
                int col = jTable_AddedStudents.columnAtPoint(e.getPoint());
                if (col == 4) {
                    deleteObj();
                }
            }
        });
    }

    private void tableListCustomizer() {
        JTableHeader header = jTable_ListOfStudent.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 48));
        header.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setBorder(BorderFactory.createLineBorder(Color.black));
                setHorizontalAlignment(JLabel.CENTER);
                return this;
            }
        };
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        jTable_ListOfStudent.setDefaultRenderer(String.class, centerRenderer);
        headerRenderer.setBackground(new Color(68, 68, 68));
        headerRenderer.setForeground(Color.WHITE);
        for (int i = 0; i < jTable_ListOfStudent.getModel().getColumnCount(); i++) {
            jTable_ListOfStudent.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
    }

    private void loadDataToListTable() {
        jTable_ListOfStudent.setRowSorter(null);
        jTable_ListOfStudent.setRowSelectionAllowed(true);
        jTable_ListOfStudent.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        listStudent = (ArrayList<StudentDTO>) studentBUS.getAllStudent();
        Collections.sort(listStudent, Comparator.comparingInt(StudentDTO::getID));
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 4:
                        return Icon.class;
                    default:
                        return Object.class;
                }
            }
        };
        model.addColumn("ID");
        model.addColumn("Họ");
        model.addColumn("Tên");
        model.addColumn("Ngày");
        model.addColumn("Thêm");
        jTable_ListOfStudent.setModel(model);
        for (StudentDTO studentDTO : listStudent) {
            if (!isExist(studentDTO.getID())) {
                LocalDateTime localDateTime = studentDTO.getEnrollmentDate();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                if (localDateTime != null) {
                    String formattedDate = localDateTime.format(formatter);
                    ImageIcon icon = new ImageIcon(getClass().getResource("/Icon/icons8-unchecked-checkbox-24.png"));
                    model.addRow(new Object[]{studentDTO.getID(), studentDTO.getFirstName(), studentDTO.getLastName(), formattedDate, icon});
                }
            }
        }
        jTable_ListOfStudent.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = jTable_ListOfStudent.rowAtPoint(e.getPoint());
                int col = jTable_ListOfStudent.columnAtPoint(e.getPoint());
                if (col == 4) {
                    if (model.getValueAt(row, col).toString().contains("/Icon/icons8-unchecked-checkbox-24.png")) {
                        ImageIcon newIcon = new ImageIcon(getClass().getResource("/Icon/icons8-checkbox-24.png"));
                        model.setValueAt(newIcon, row, col);
                    } else {
                        ImageIcon newIcon = new ImageIcon(getClass().getResource("/Icon/icons8-unchecked-checkbox-24.png"));
                        model.setValueAt(newIcon, row, col);
                    }
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable_AddedStudents;
    private javax.swing.JTable jTable_ListOfStudent;
    private javax.swing.JTextField jTextField_Search;
    // End of variables declaration//GEN-END:variables
}
