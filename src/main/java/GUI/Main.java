package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import GUI.Student.StudentGUI;

public class Main extends javax.swing.JFrame {
    private CourseManagers jPanel1 = new CourseManagers();
    private StudentGUI jPanel2 = new StudentGUI();
    private QuanLyGiangVienPanel jPanel3 = new QuanLyGiangVienPanel();
    private JPanel4 jPanel4 = new JPanel4();
    
    public Main() {
        initComponents();
        this.setLocationRelativeTo(null);
        setupPage();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AllButtonJP = new javax.swing.JPanel();
        CourseManageJP = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        StudentManageJP = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        techerManageJP = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        facultyManageJP = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        MomJP = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(java.awt.Color.lightGray);
        setResizable(false);

        AllButtonJP.setBackground(new java.awt.Color(255, 255, 255));
        AllButtonJP.setPreferredSize(new java.awt.Dimension(344, 945));

        CourseManageJP.setBackground(new java.awt.Color(255, 255, 255));
        CourseManageJP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CourseManageJP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clickedCourseManagement(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 32)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon("./CourseManagement/src/main/java/GUI/Icon/pie-chart.png")); // NOI18N
        jLabel1.setText("Quản lý khóa học");

        javax.swing.GroupLayout CourseManageJPLayout = new javax.swing.GroupLayout(CourseManageJP);
        CourseManageJP.setLayout(CourseManageJPLayout);
        CourseManageJPLayout.setHorizontalGroup(
            CourseManageJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CourseManageJPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CourseManageJPLayout.setVerticalGroup(
            CourseManageJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CourseManageJPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        StudentManageJP.setBackground(new java.awt.Color(255, 255, 255));
        StudentManageJP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        StudentManageJP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clickedStudentManagement(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 32)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon("./CourseManagement/src/main/java/GUI/Icon/graduation-hat.png")); // NOI18N
        jLabel2.setText("Quản lý học viên");

        javax.swing.GroupLayout StudentManageJPLayout = new javax.swing.GroupLayout(StudentManageJP);
        StudentManageJP.setLayout(StudentManageJPLayout);
        StudentManageJPLayout.setHorizontalGroup(
            StudentManageJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StudentManageJPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        StudentManageJPLayout.setVerticalGroup(
            StudentManageJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StudentManageJPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        techerManageJP.setBackground(new java.awt.Color(255, 255, 255));
        techerManageJP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        techerManageJP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clickedTeacherManagement(evt);
            }
        });
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 32)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon("./CourseManagement/src/main/java/GUI/Icon/presentaation.png")); // NOI18N
        jLabel3.setText("Quản lý giảng viên");

        javax.swing.GroupLayout techerManageJPLayout = new javax.swing.GroupLayout(techerManageJP);
        techerManageJP.setLayout(techerManageJPLayout);
        techerManageJPLayout.setHorizontalGroup(
            techerManageJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(techerManageJPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        techerManageJPLayout.setVerticalGroup(
            techerManageJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(techerManageJPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        facultyManageJP.setBackground(new java.awt.Color(255, 255, 255));
        facultyManageJP.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        facultyManageJP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clickedFacultyManagement(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 32)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon("\\Icon\\from-a-to-z.png")); // NOI18N
        jLabel4.setText("Quản lý khoa");

        // javax.swing.GroupLayout facultyManageJPLayout = new javax.swing.GroupLayout(facultyManageJP);
        // facultyManageJP.setLayout(facultyManageJPLayout);
        // facultyManageJPLayout.setHorizontalGroup(
        //     facultyManageJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        //     .addGroup(facultyManageJPLayout.createSequentialGroup()
        //         .addContainerGap()
        //         .addComponent(jLabel4)
        //         .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        // );
        // facultyManageJPLayout.setVerticalGroup(
        //     facultyManageJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        //     .addGroup(facultyManageJPLayout.createSequentialGroup()
        //         .addContainerGap()
        //         .addComponent(jLabel4)
        //         .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        // );

        javax.swing.GroupLayout AllButtonJPLayout = new javax.swing.GroupLayout(AllButtonJP);
        AllButtonJP.setLayout(AllButtonJPLayout);
        AllButtonJPLayout.setHorizontalGroup(
            AllButtonJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AllButtonJPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AllButtonJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CourseManageJP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(StudentManageJP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(techerManageJP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(facultyManageJP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        AllButtonJPLayout.setVerticalGroup(
            AllButtonJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AllButtonJPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CourseManageJP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(StudentManageJP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(techerManageJP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(facultyManageJP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(665, Short.MAX_VALUE))
        );

        MomJP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout MomJPLayout = new javax.swing.GroupLayout(MomJP);
        MomJP.setLayout(MomJPLayout);
        MomJPLayout.setHorizontalGroup(
            MomJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1287, Short.MAX_VALUE)
        );
        MomJPLayout.setVerticalGroup(
            MomJPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(AllButtonJP, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MomJP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(AllButtonJP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 40, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MomJP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clickedCourseManagement(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clickedCourseManagement
        this.MomJP.removeAll();
        this.MomJP.revalidate();
        this.MomJP.repaint();
        this.MomJP.setLayout(new GridLayout(1,1));
        this.MomJP.add(this.jPanel1);
        this.jPanel1.setVisible(true);   
    }//GEN-LAST:event_clickedCourseManagement

    private void clickedStudentManagement(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clickedStudentManagement
        this.MomJP.removeAll();
        this.MomJP.revalidate();
        this.MomJP.repaint();
        this.MomJP.setLayout(new GridLayout(1,1));
        this.MomJP.add(this.jPanel2);
        this.jPanel2.setVisible(true);
    }//GEN-LAST:event_clickedStudentManagement

    private void clickedTeacherManagement(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clickedTeacherManagement
        this.MomJP.removeAll();
        this.MomJP.revalidate();
        this.MomJP.repaint();
        this.MomJP.setLayout(new GridLayout(1,1));
        this.MomJP.add(this.jPanel3);
        this.jPanel3.setVisible(true);
    }//GEN-LAST:event_clickedTeacherManagement

    private void clickedFacultyManagement(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clickedFacultyManagement
        this.MomJP.removeAll();
        this.MomJP.revalidate();
        this.MomJP.repaint();
        this.MomJP.setLayout(new GridLayout(1,1));
        this.MomJP.add(this.jPanel4);
        this.jPanel4.setVisible(true);
    }//GEN-LAST:event_clickedFacultyManagement

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AllButtonJP;
    private javax.swing.JPanel CourseManageJP;
    private javax.swing.JPanel MomJP;
    private javax.swing.JPanel StudentManageJP;
    private javax.swing.JPanel facultyManageJP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel techerManageJP;
    // End of variables declaration//GEN-END:variables

    // các phương thức điều chỉnh giao diện 
    public void setupPage() {
        // mặc định chọn trang quản lý khóa học
        this.MomJP.removeAll();
        this.MomJP.revalidate();
        this.MomJP.repaint();
        this.MomJP.setLayout(new GridLayout(1,1));
        this.MomJP.add(this.jPanel1);
        this.jPanel1.setVisible(true);
    }

}
