/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.CourseBUS;
import BUS.OnlineCourseBUS;
import BUS.OnsiteCourseBUS;
import DAL.CourseDAL;
import DAL.DatabaseManager;
import DAL.LecturerDAL;
import DAL.OnlineCourseDAL;
import DAL.OnsiteCourseDAL;
import DTO.CourseDTO;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Thinkbook 14 G3
 */
public class CourseManagers extends javax.swing.JPanel {

    /**
     * Creates new form CourseManagers
     */
    public CourseManagers() {
        initComponents();
          functionmain();
    }
      public List<CourseDTO> ListCourse;

    private void tableCustomizer() {
        JTableHeader header = TableCourse.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 40));
        header.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setBorder(BorderFactory.createLineBorder(Color.black));
                setHorizontalAlignment(JLabel.CENTER);
                return this;
            }
        };
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        TableCourse.setDefaultRenderer(String.class, centerRenderer);
        headerRenderer.setBackground(Color.BLUE);
        headerRenderer.setForeground(Color.WHITE);
        for (int i = 0; i < TableCourse.getModel().getColumnCount(); i++) {

            TableCourse.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
    }
     public void functionmain() {
        CourseDAL caudal = new CourseDAL();
        LecturerDAL ildal = new LecturerDAL();
        CourseBUS courseService = new CourseBUS(caudal, ildal);
        List<CourseDTO> allCourses = caudal.getAllCourses();
        ListCourse = allCourses;
        showCourseList(allCourses);
        tableCustomizer();
    }

    public void Delete(int column, int row) {
        if (column == 7) { 
            Object value = TableCourse.getValueAt(row, 1);
            System.out.println("Value of column 1: " + value);
            int choice = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa?", "Xác nhận xóa",
                    JOptionPane.OK_CANCEL_OPTION);

            if (choice == JOptionPane.OK_OPTION) {
                
                DatabaseManager db = new DatabaseManager();
                CourseDAL caudal = new CourseDAL(db);
                try {
                    db.getConnection().setAutoCommit(false);
                    if (TableCourse.getValueAt(row, 4) == "online") {
                        OnlineCourseDAL onCourseDAL = new OnlineCourseDAL(db);
                        OnlineCourseBUS onCourseBUS = new OnlineCourseBUS(onCourseDAL);
                        onCourseBUS.removeAnOnlineCourse((int) value);
                    }else{
                        OnsiteCourseDAL onCourseDAL = new OnsiteCourseDAL(db);
                        OnsiteCourseBUS onCurseBUS = new OnsiteCourseBUS(onCourseDAL);
                        onCurseBUS.removeAnOnsiteCourse((int)value);
                    }

                    LecturerDAL ildal = new LecturerDAL();
                    CourseBUS courseService = new CourseBUS(caudal, ildal);

                    int nhangt = courseService.removeCourse((int) value);

                    if (nhangt == -1) {
                        db.getConnection().rollback();
                        JOptionPane.showMessageDialog(null, "Không thể thực hiện xóa đối tượng.", "Thông báo",
                        JOptionPane.WARNING_MESSAGE);
                    }else{
                        db.getConnection().commit();
                    }

                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                functionmain();
            }
        } else if (column == 6) {
            
            int courseID = (int)TableCourse.getValueAt(row, 1);
            String type = (String)TableCourse.getValueAt(row, 4);
            new menumanager(courseID, type).setVisible(true);

        }
    }

    public void clicksearch() {
        String text = Search.getText().toLowerCase();

        if (text == "") {
            showCourseList(ListCourse);
        } else
            search(ListCourse);
    }

    public void search(List<CourseDTO> allCourses) {
        String text = Search.getText().toLowerCase(); // Chuyển đổi chuỗi cần tìm kiếm về chữ thường
        List<CourseDTO> list = new ArrayList<>();
        for (int i = 0; i < allCourses.size(); i++) {
            String title = allCourses.get(i).getTitle().toLowerCase(); // Chuyển đổi chuỗi trong đối tượng CourseDTO về
                                                                       // chữ thường
            if (title.contains(text)) {
                list.add(allCourses.get(i));
            }
        }

        showCourseList(list);
    }

    public void addall() {
        showCourseList(ListCourse);
    }

    public void addonline() {
        List<CourseDTO> temp = new ArrayList<>();
        for (int i = 0; i < ListCourse.size(); i++) {
            if (ListCourse.get(i).getType() == 1) {
                temp.add(ListCourse.get(i));
            }
        }
        showCourseList(temp);
    }

    public void addonsite() {
        List<CourseDTO> temp = new ArrayList<>();
        for (int i = 0; i < ListCourse.size(); i++) {
            if (ListCourse.get(i).getType() == 0) {
                temp.add(ListCourse.get(i));
            }
        }
        showCourseList(temp);
    }

    public void showCourseList(List<CourseDTO> courseList) {
        int i = 0;
        // Tạo danh sách các tiêu đề cột
        String[] columnNames = { "Stt", "Mã Khóa học ", "Tên khóa học", "Khoa", "Loại", "Tín chỉ", "Quản lý", "Xóa" };

        // Tạo một DefaultTableModel để lưu trữ dữ liệu và tiêu đề cột
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Thêm dữ liệu từ danh sách khóa học vào DefaultTableModel
        for (CourseDTO course : courseList) {

            // Tạo một JButton với văn bản "Xem" và màu nền màu cam
            JButton viewButton = new JButton("Xem");
            viewButton.setBackground(Color.ORANGE);
            viewButton.setPreferredSize(new Dimension(50, 40)); // Đặt kích thước cho nút

            int temp = course.getType();
            String type = "";
            if (temp == 1) {
                type = "online";
            } else
                type = "onsite";

            // ImageIcon deleteIcon = new ImageIcon("/img/icons8-delete-30.png");

            ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/img/icons8-delete-30.png"));
            // Tạo một JLabel với biểu tượng hình ảnh
            JLabel deleteLabel = new JLabel(deleteIcon);

            Object[] rowData = {
                    i,
                    course.getCourseID(),
                    course.getTitle(),
                    course.getDepartment().getName(),
                    type,
                    course.getCredits(),
                    viewButton, // Thay thế "Xem" bằng JButton
                    deleteLabel, // Thay thế "Xóa" bằng JLabel chứa biểu tượng hình ảnh
                    // Thêm các thông tin khác của khóa học vào đây
            };
            i++;
            model.addRow(rowData);
        }

        // Gán DefaultTableModel vào JTable
        TableCourse.setModel(model);

        // Tạo một TableCellRenderer tùy chỉnh để hiển thị thành phần trong ô
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                if (value instanceof Component) {
                    return (Component) value;
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };

        // Đặt TableCellRenderer cho cột "Xem" (cột thứ 6)
        TableCourse.getColumnModel().getColumn(6).setCellRenderer(renderer);
        // Đặt TableCellRenderer cho cột "Xóa" (cột thứ 7)
        TableCourse.getColumnModel().getColumn(7).setCellRenderer(renderer);
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
        ButtonAddCourse = new javax.swing.JButton();
        Search = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        PanelALL = new javax.swing.JLabel();
        PanelOline = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        PanelOnsite = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableCourse = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(159, 159, 159));

        ButtonAddCourse.setBackground(new java.awt.Color(11, 8, 171));
        ButtonAddCourse.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        ButtonAddCourse.setForeground(new java.awt.Color(255, 255, 255));
        ButtonAddCourse.setText("+  Tạo khóa học");

        ButtonAddCourse.addActionListener(e->{
            new CreateCourse().setVisible(true);
        });

        Search.setBackground(new java.awt.Color(152, 162, 255));
        Search.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Search.setForeground(new java.awt.Color(0, 0, 0));
        Search.setText("Tìm kiếm");
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });
        Search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SearchKeyPressed(evt);
            }
        });

        jPanel14.setPreferredSize(new java.awt.Dimension(190, 34));

        PanelALL.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        PanelALL.setForeground(new java.awt.Color(0, 0, 0));
        PanelALL.setText("Tất cả");
        PanelALL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PanelALLMouseClicked(evt);
            }
        });

        PanelOline.setBackground(new java.awt.Color(255, 255, 255));
        PanelOline.setForeground(new java.awt.Color(255, 255, 255));
        PanelOline.setPreferredSize(new java.awt.Dimension(64, 34));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Online");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PanelOlineLayout = new javax.swing.GroupLayout(PanelOline);
        PanelOline.setLayout(PanelOlineLayout);
        PanelOlineLayout.setHorizontalGroup(
            PanelOlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelOlineLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel12)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        PanelOlineLayout.setVerticalGroup(
            PanelOlineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelOlineLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelOnsite.setBackground(new java.awt.Color(255, 255, 255));
        PanelOnsite.setPreferredSize(new java.awt.Dimension(64, 30));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Onsite");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout PanelOnsiteLayout = new javax.swing.GroupLayout(PanelOnsite);
        PanelOnsite.setLayout(PanelOnsiteLayout);
        PanelOnsiteLayout.setHorizontalGroup(
            PanelOnsiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelOnsiteLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel13)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        PanelOnsiteLayout.setVerticalGroup(
            PanelOnsiteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelOnsiteLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel13)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelALL, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelOline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(PanelOnsite, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(PanelALL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelOnsite, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(PanelOline, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TableCourse.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "", "", "", "", "", "", "", "Title 8"
            }
        ));
        TableCourse.setRowHeight(56);
        TableCourse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableCourseMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TableCourse);

        jPanel2.setBackground(new java.awt.Color(152, 162, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-search-15 (2).png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(275, 275, 275)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 216, Short.MAX_VALUE)
                        .addComponent(ButtonAddCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ButtonAddCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Search, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchActionPerformed

    private void SearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchKeyPressed
        // TODO add your handling code here:
          clicksearch();

    }//GEN-LAST:event_SearchKeyPressed

    private void PanelALLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelALLMouseClicked
        // TODO add your handling code here:
        
         addall();
        PanelALL.setBackground(Color.GRAY);
        PanelOline.setBackground(Color.WHITE);
        PanelOnsite.setBackground(Color.WHITE);
    }//GEN-LAST:event_PanelALLMouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // TODO add your handling code here:
        addonline();
        PanelALL.setBackground(Color.WHITE);
        PanelOline.setBackground(Color.GRAY);
        PanelOnsite.setBackground(Color.WHITE);
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        // TODO add your handling code here:
        addonsite();
        PanelALL.setBackground(Color.WHITE);
        PanelOline.setBackground(Color.WHITE);
        PanelOnsite.setBackground(Color.gray);
    }//GEN-LAST:event_jLabel13MouseClicked

    private void TableCourseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableCourseMouseClicked
        // TODO add your handling code here:
            int column = TableCourse.getColumnModel().getColumnIndexAtX(evt.getX()); // Lấy chỉ số cột từ vị trí x của chuột
        int row = evt.getY() / TableCourse.getRowHeight(); // Lấy chỉ số dòng từ vị trí y của chuột

        Delete(column, row);
    }//GEN-LAST:event_TableCourseMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonAddCourse;
    private javax.swing.JLabel PanelALL;
    private javax.swing.JPanel PanelOline;
    private javax.swing.JPanel PanelOnsite;
    private javax.swing.JTextField Search;
    private javax.swing.JTable TableCourse;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
