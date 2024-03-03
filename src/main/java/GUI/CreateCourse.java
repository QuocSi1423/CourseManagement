package GUI;

import java.awt.Color;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import BUS.CourseBUS;
import BUS.DepartmentBUS;
import BUS.OnlineCourseBUS;
import BUS.OnsiteCourseBUS;
import DAL.CourseDAL;
import DAL.DepartmentDAL;
import DAL.LecturerDAL;
import DAL.OnlineCourseDAL;
import DAL.OnsiteCourseDAL;
import DAL.IDAL.IObjectDAL;
import DTO.CourseDTO;
import DTO.DepartmentDTO;
import DTO.OnlineCourseDTO;
import DTO.OnsiteCourseDTO;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatPropertiesLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.formdev.flatlaf.ui.*;
import com.formdev.flatlaf.ui.FlatInternalFrameUI.FlatInternalFrameBorder;

public class CreateCourse extends javax.swing.JFrame {
    private ArrayList<DepartmentDTO> listDepartments = new ArrayList<>();
    private DepartmentDAL iDepartmentDAL = new DepartmentDAL();
    private DepartmentBUS departmentBUS = new DepartmentBUS(iDepartmentDAL);
    private CourseDAL courseDAL = new CourseDAL();
    private LecturerDAL iLecturerDAL = new LecturerDAL();
    private CourseBUS courseBUS = new CourseBUS(courseDAL, iLecturerDAL);
    //
    private IObjectDAL iObjectDAL = new OnsiteCourseDAL();
    private OnsiteCourseBUS onsiteCourseBUS = new OnsiteCourseBUS(iObjectDAL);
    // 
    private IObjectDAL onlineCourseDAL = new OnlineCourseDAL();
    private OnlineCourseBUS onlineCourseBUS = new OnlineCourseBUS(onlineCourseDAL);
    private boolean studyOnline;
    
    public CreateCourse() {
        initComponents();
        this.setLocationRelativeTo(null);
        loadDepartmentToPage();
        studyOnline = false;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        TitleJTF = new javax.swing.JTextField();
        LocationJTF = new javax.swing.JTextField();
        DaysJTF = new javax.swing.JTextField();
        CreditsJTF = new javax.swing.JTextField();
        UrlJTF = new javax.swing.JTextField();
        DepartmentJCB = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        OnlineLearningJRB = new javax.swing.JRadioButton();
        TimeFF = new javax.swing.JFormattedTextField();
        SaveJBT = new javax.swing.JButton();
        CancelJBT = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("TẠO KHÓA HỌC");
        jLabel1.setToolTipText("");

        jPanel1.setBackground(new java.awt.Color(240, 240, 240));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));
        jPanel1.setToolTipText("");
        jPanel1.setMaximumSize(new java.awt.Dimension(811, 330));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Thông tin chung");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Số ngày học");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setText("Thông tin chi tiết");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Tên khóa học");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("Địa điểm");

        TitleJTF.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        LocationJTF.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        DaysJTF.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        CreditsJTF.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        CreditsJTF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                checkCreditsInput(evt);
            }
        });

        UrlJTF.setBackground(new java.awt.Color(178, 174, 174));
        UrlJTF.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        UrlJTF.setEnabled(false);

        DepartmentJCB.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setText("Số tín chỉ");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setText("Khoa");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setText("Thời gian");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("URL");

        OnlineLearningJRB.setBackground(new java.awt.Color(204, 204, 204));
        OnlineLearningJRB.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        OnlineLearningJRB.setText("Online");
        OnlineLearningJRB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        OnlineLearningJRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OnlineLearningJRBActionPerformed(evt);
            }
        });

        TimeFF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.MEDIUM))));
        TimeFF.setToolTipText("");
        TimeFF.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        TimeFF.setName(""); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(OnlineLearningJRB))
                            .addComponent(jLabel2))
                        .addContainerGap(561, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(DaysJTF, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(LocationJTF, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                                .addComponent(TitleJTF, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(CreditsJTF, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(DepartmentJCB, 0, 200, Short.MAX_VALUE)
                            .addComponent(UrlJTF, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(TimeFF))
                        .addGap(30, 30, 30))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(TitleJTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(DepartmentJCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(CreditsJTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(OnlineLearningJRB))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(LocationJTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(TimeFF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(DaysJTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UrlJTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(27, 27, 27))
        );

        SaveJBT.setBackground(new java.awt.Color(0, 0, 204));
        SaveJBT.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        SaveJBT.setForeground(new java.awt.Color(255, 255, 255));
        SaveJBT.setText("Lưu");
        SaveJBT.setAutoscrolls(true);
        SaveJBT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SaveJBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAll(evt);
            }
        });

        CancelJBT.setBackground(new java.awt.Color(255, 51, 51));
        CancelJBT.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        CancelJBT.setForeground(new java.awt.Color(255, 255, 255));
        CancelJBT.setText("Hủy");
        CancelJBT.setAutoscrolls(true);
        CancelJBT.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CancelJBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelAll(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(335, 335, 335)
                        .addComponent(SaveJBT)
                        .addGap(32, 32, 32)
                        .addComponent(CancelJBT)))
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(334, 334, 334))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SaveJBT)
                    .addComponent(CancelJBT))
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void OnlineLearningJRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OnlineLearningJRBActionPerformed
        if(this.OnlineLearningJRB.isSelected()) {
            onlinePage();
            this.studyOnline = true;
        } else {
            onsitePage();
            this.studyOnline = false;
        }
    }//GEN-LAST:event_OnlineLearningJRBActionPerformed

    private void cancelAll(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelAll
        resetPage();
    }//GEN-LAST:event_cancelAll

    private void saveAll(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAll
        if(!studyOnline) {
            getCourseOnsite();
        } else {
            getCourseOnline();
        }
    }//GEN-LAST:event_saveAll

    private void checkCreditsInput(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_checkCreditsInput
        if(!isNumbers(this.CreditsJTF.getText())) {
            JOptionPane.showMessageDialog(null, "Số tín chỉ phải là số nguyên lớn hơn 0!", "", JOptionPane.WARNING_MESSAGE);
            this.CreditsJTF.setText("");
        }
    }//GEN-LAST:event_checkCreditsInput

    public static void main(String args[]) {
        FlatMacLightLaf.setup();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateCourse().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelJBT;
    private javax.swing.JTextField CreditsJTF;
    private javax.swing.JTextField DaysJTF;
    private javax.swing.JComboBox<String> DepartmentJCB;
    private javax.swing.JTextField LocationJTF;
    private javax.swing.JRadioButton OnlineLearningJRB;
    private javax.swing.JButton SaveJBT;
    private javax.swing.JFormattedTextField TimeFF;
    private javax.swing.JTextField TitleJTF;
    private javax.swing.JTextField UrlJTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    // các phương thức điều chỉnh giao diện 
    public void loadDepartmentToPage() {
        this.listDepartments = (ArrayList<DepartmentDTO>) this.departmentBUS.getAllDepartment();
        for(DepartmentDTO department : listDepartments) {
            this.DepartmentJCB.addItem(department.getName());
        }
    }
    
    public void onsitePage() {
        this.LocationJTF.enable(true);
        this.LocationJTF.setBackground(new Color(255,255,255));
        this.DaysJTF.enable(true);
        this.DaysJTF.setBackground(new Color(255,255,255));
        this.TimeFF.enable(true);
        this.TimeFF.setBackground(new Color(255,255,255));
        this.UrlJTF.enable(false);
        this.UrlJTF.setBackground(new Color(178, 174, 174));
        this.UrlJTF.setText("");
    }
    
    public void onlinePage() {
        this.LocationJTF.enable(false);
        this.LocationJTF.setBackground(new Color(178, 174, 174));
        this.LocationJTF.setText("");
        this.DaysJTF.enable(false);
        this.DaysJTF.setBackground(new Color(178, 174, 174));
        this.DaysJTF.setText("");
        this.TimeFF.enable(false);
        this.TimeFF.setBackground(new Color(178, 174, 174));
        this.TimeFF.setText("");
        this.UrlJTF.enable(true);
        this.UrlJTF.setBackground(new Color(255,255,255));
    }
    
    public void resetPage() {
        this.TitleJTF.setText("");
        this.CreditsJTF.setText("");
        this.DepartmentJCB.setSelectedIndex(0);
        this.LocationJTF.setText("");
        this.TimeFF.setText("");
        this.DaysJTF.setText("");
        this.UrlJTF.setText("");
        this.OnlineLearningJRB.setSelected(false);
    }
    
    // các phương thức xử lý nút nhấn
    public void getCourseOnsite() {
        if(this.TitleJTF.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên khóa học!", "", JOptionPane.WARNING_MESSAGE);
            return;
        } 
        if(this.CreditsJTF.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số tín chỉ khóa học!", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(this.LocationJTF.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập địa điểm khóa học!", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(this.TimeFF.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập thời gian khóa học!", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(this.DaysJTF.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số ngày học của khóa học!", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // khởi tạo đối tượng courseDTO để lưu vào database
        DepartmentDTO departmentDTO = getSelectedDepartment();
        String title = this.TitleJTF.getText();
        int credits = Integer.parseInt(this.CreditsJTF.getText());
        CourseDTO courseDTO = new CourseDTO(departmentDTO, title, credits);
        int courseId = this.courseBUS.createCourse(courseDTO);
        // khởi tạo đối tượng onsiteCourseDTO để lưu vào database
        String location = this.LocationJTF.getText();
        String days = this.DaysJTF.getText();
        LocalTime time = LocalTime.parse(this.TimeFF.getText());
        OnsiteCourseDTO onsiteCourseDTO = new OnsiteCourseDTO( courseId, location, days, time);
        this.onsiteCourseBUS.createAnOnsiteCourse(onsiteCourseDTO);
        this.dispose();
    }
    
    public void getCourseOnline() {
        if(this.TitleJTF.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên khóa học!", "", JOptionPane.WARNING_MESSAGE);
            return;
        } 
        if(this.CreditsJTF.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số tín chỉ khóa học!", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(this.UrlJTF.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập URL khóa học!", "", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // khởi tạo đối tượng courseDTO để lưu vào database
        DepartmentDTO departmentDTO = getSelectedDepartment();
        String title = this.TitleJTF.getText();
        int credits = Integer.parseInt(this.CreditsJTF.getText());
        CourseDTO courseDTO = new CourseDTO(departmentDTO, title, credits);
        int courseId = this.courseBUS.createCourse(courseDTO);
        // khởi tạo đối tượng onlineCourse đển lưu vào database
        String url = this.UrlJTF.getText();
        OnlineCourseDTO  onlineCourseDTO = new OnlineCourseDTO(courseId, url);
        this.onlineCourseBUS.createAnOnlineCourse(onlineCourseDTO);
        this.dispose();
    }
    
    // các phương thức xử lý dữ liệu 
    public boolean isNumbers(String input) {
        boolean isValid = false;
        try {
            int number = Integer.parseInt(input);
            if(number > 0) {
                isValid = true;
            } else {
                isValid = false;
            }
        } catch (NumberFormatException e) {
            isValid = false;
        }
        return isValid;
    }
    
    public DepartmentDTO getSelectedDepartment() {
        DepartmentDTO departmentDTO = null;
        String department = (String) this.DepartmentJCB.getSelectedItem();
        for(DepartmentDTO departmentDTOItem : this.listDepartments) {
            if(department.equals(departmentDTOItem.getName())) {
                departmentDTO = departmentDTOItem;
            }
        }
        return departmentDTO;
    }
}
