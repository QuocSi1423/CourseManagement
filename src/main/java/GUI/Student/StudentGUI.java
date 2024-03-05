/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI.Student;

import BUS.StudentBUS;
import DAL.IDAL.IStudentDAL;
import DAL.StudentDAL;
import DTO.StudentDTO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

/**
 * @author luong
 */
public class StudentGUI extends javax.swing.JPanel {

    /**
     * Creates new form StudentGUI
     */
//    private final DefaultTableModel dtm;
    private List<StudentDTO> studentList;
    private final StudentBUS studentBUS;
    private int flag;
    private final IStudentDAL studentDAL;
    private LocalDateTime currentDate;
    private LocalDateTime enrollmentDate;
    private StudentDTO student;
    private JLabel lblDelete;
    public StudentGUI() {
        studentDAL = new StudentDAL();
        studentBUS = new StudentBUS(studentDAL);
        initComponents();
        ImageIcon iconDelete = new ImageIcon(getClass().getResource("/icons/bin.png"));
        lblDelete = new JLabel(iconDelete);
        Input txtGetByName = new Input("Tìm kiếm");
        txtGetByName.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        String hexColorCode = "#98A2FF";
        Color customColor = Color.decode(hexColorCode);
        txtGetByName.setBackground(customColor);
        txtGetByName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtGetByName.getText().equals("") || txtGetByName.getText().equals("Tìm kiếm")) {
                    studentList = studentBUS.getAllStudent();
                } else {
                    studentList = getByName(txtGetByName.getText());
                }
                showTable();
            }
        });
        
        jPanel6.add(txtGetByName);
        
        flag = -1;

//        table
//        dtm = new DefaultTableModel();
//        dtm.addColumn("STT");
//        dtm.addColumn("Họ");
//        dtm.addColumn("Tên");
//        dtm.addColumn("Ngày đăng ký");
//        dtm.addColumn("Xóa");
//        
//        table.getColumnModel().getColumn(0).setPreferredWidth(82);
//        table.getColumnModel().getColumn(1).setPreferredWidth(238);
//        table.getColumnModel().getColumn(2).setPreferredWidth(227);
//        table.getColumnModel().getColumn(3).setPreferredWidth(285);
//        table.getColumnModel().getColumn(4).setPreferredWidth(120);
//        table.setModel(dtm);
        
        studentList = studentBUS.getAllStudent();

        showTable();

        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        Object valueAtFirstColumn = table.getValueAt(selectedRow, 0);
                        int studentId = Integer.parseInt(valueAtFirstColumn.toString());
                        
                        student = (StudentDTO) studentBUS.getAnObjectByID(studentId);
                        flag = studentId;
                        txtFirstName.setText(student.getFirstName());
                        txtLastName.setText(student.getLastName());
                        enrollmentDate = student.getEnrollmentDate();
                    }
                }
            }
        });
//        end table
    }


    public void showTable() {
        DefaultTableCellRenderer renderer =new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof Component) {
                    return (Component) value;
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };

        table.setShowGrid(true);
        table.setGridColor(Color.gray);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setDefaultRenderer(Object.class, new CustomRowHeightRenderer());
        table.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer());
        table.getColumnModel().getColumn(3).setCellRenderer(renderer);
        DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        model.setRowCount(0);
        for (StudentDTO stu : studentList) {
            model.addRow(new Object[]{
                    stu.getID(), stu.getLastName(), stu.getFirstName(),
                    lblDelete
            });
        }
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
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btnCreate = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(159, 159, 159));
        jPanel1.setPreferredSize(new java.awt.Dimension(1306, 945));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(310, 42));
        jPanel3.setRequestFocusEnabled(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("Họ");

        txtLastName.setBackground(new java.awt.Color(233, 233, 233));
        txtLastName.setPreferredSize(new java.awt.Dimension(270, 40));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(310, 42));
        jPanel4.setRequestFocusEnabled(false);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Tên");

        txtFirstName.setBackground(new java.awt.Color(233, 233, 233));
        txtFirstName.setPreferredSize(new java.awt.Dimension(270, 40));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(72, 92));

        btnCreate.setBackground(new java.awt.Color(11, 8, 171));
        btnCreate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCreate.setForeground(new java.awt.Color(255, 255, 255));
        // btnCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("../Icon/add.png"))); // NOI18N
        btnCreate.setText("Thêm");
        btnCreate.setMinimumSize(new java.awt.Dimension(120, 27));
        btnCreate.setPreferredSize(new java.awt.Dimension(100, 40));
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });
        jPanel5.add(btnCreate);

        btnSave.setBackground(new java.awt.Color(11, 8, 171));
        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 255, 255));
        btnSave.setText("Lưu");
        btnSave.setPreferredSize(new java.awt.Dimension(70, 40));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jPanel5.add(btnSave);

        btnCancel.setBackground(new java.awt.Color(250, 7, 7));
        btnCancel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancel.setForeground(new java.awt.Color(255, 255, 255));
        btnCancel.setText("Hủy");
        btnCancel.setPreferredSize(new java.awt.Dimension(70, 40));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        jPanel5.add(btnCancel);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 317, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel6.setPreferredSize(new java.awt.Dimension(346, 40));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel7.setPreferredSize(new java.awt.Dimension(1252, 670));
        jPanel7.setLayout(new java.awt.BorderLayout());

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Họ", "Tên", "Xóa"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(82);
            table.getColumnModel().getColumn(1).setPreferredWidth(238);
            table.getColumnModel().getColumn(2).setPreferredWidth(227);
            table.getColumnModel().getColumn(3).setPreferredWidth(120);
        }

        jPanel7.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(27, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1306, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 945, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        if (txtFirstName.getText().equals("") || txtLastName.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin học viên", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            StudentDTO stu = (StudentDTO) studentBUS.getAnObjectByID(flag);
            stu.setFirstName(txtFirstName.getText());
            stu.setLastName(txtLastName.getText());
            studentBUS.updateObject(stu);
            flag = -1;
        }
        studentList = studentBUS.getAllStudent();
        showTable();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        txtFirstName.setText("");
        txtLastName.setText("");
        flag = -1;
    }//GEN-LAST:event_btnCancelActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
        int column = table.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = table.rowAtPoint(evt.getPoint());
//        int row = evt.getY() / table.getRowHeight();
        System.out.println(column + " " + row);
        delete(column, row);
    }//GEN-LAST:event_tableMouseClicked

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        // TODO add your handling code here:
        if (txtFirstName.getText().equals("") || txtLastName.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin học viên", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } else {
            currentDate = LocalDateTime.now();
            StudentDTO stu = new StudentDTO();
            stu.setFirstName(txtFirstName.getText());
            stu.setLastName(txtLastName.getText());
            stu.setEnrollmentDate(currentDate);
            System.out.println(stu.getFirstName() + "," + stu.getLastName() + "," + stu.getEnrollmentDate().toString());
            studentBUS.insertObject(stu);
        }
        studentList = studentBUS.getAllStudent();
        showTable();
    }//GEN-LAST:event_btnCreateActionPerformed

    public void delete(int column, int row) {
        System.out.println("aaaaa");
        if (column == 4) {
            Object value =  table.getValueAt(row, 0);
            System.out.println("Value of column 1: " + value);
            int choice = JOptionPane.showConfirmDialog(this, "Bạn có thực sự muốn xóa?", "Thông báo", JOptionPane.OK_CANCEL_OPTION);

            if (choice == JOptionPane.OK_OPTION) {
                int studentDelete = studentBUS.removeObject((int) value);
//                studentBUS.removeObject(studentDelete);
                
                if (studentDelete == -1) {
                    JOptionPane.showMessageDialog(null, "Không thể thực hiện xóa đối tượng", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
            }
            studentList = studentBUS.getAllStudent();
            showTable();
        }
    }

    public List<StudentDTO> getByName(String studentName) {
        List<StudentDTO> list = new ArrayList<>();
        for (StudentDTO student : studentList) {
            if (student.getFirstName().equalsIgnoreCase(studentName) || student.getLastName().equalsIgnoreCase(studentName)) {
                list.add(student);
            }
        }
        return list;
    }


    private class CustomRowHeightRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            int desiredRowHeight = 40;            
            table.setRowHeight(row, desiredRowHeight);

            return component;
        }
    }

    private class CustomHeaderRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            String hexColorCode = "#0B08AB";
            Color customColor = Color.decode(hexColorCode);
            component.setBackground(customColor);
            component.setForeground(Color.WHITE);
            component.setFont(new Font("Segoe UI", Font.BOLD, 16));
            component.setPreferredSize(new Dimension(row, 40));

            JTableHeader header = table.getTableHeader();
            TableColumnModel colModel = header.getColumnModel();
            TableColumn col = colModel.getColumn(column);

            return component;
        }
        public CustomHeaderRenderer() {
            setHorizontalAlignment(SwingConstants.CENTER);
//            setForeground(Color.WHITE);
//            setBackground(Color.decode("#0B08AB"));
//            setFont(new Font("Segoe UI", Font.BOLD, 16));
        }
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    // End of variables declaration//GEN-END:variables
}
