package GUI;

import BUS.CourseBUS;
import BUS.InstructorBUS;
import BUS.LecturerBUS;
import DAL.CourseDAL;
import DAL.IDAL.ICourseDAL;
import DAL.IDAL.IInstructorDAL;
import DAL.IDAL.ILecturerDAL;
import DAL.InstructorDAL;
import DAL.LecturerDAL;
import DTO.CourseDTO;
import DTO.InstructorDTO;
import DTO.LecturerDTO;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import raven.cell.TableActionCellEditor;
import raven.cell.TableActionEvent;
import raven.cell.TableActioncellRender;

public class AssignmentManagement extends javax.swing.JFrame {
    // các đối tượng tạo tạm thời để code
    private CourseDTO courseDTO;
    // các đối tượng chính hoạt động trong frame
    private ICourseDAL courseDAL = new CourseDAL();
    private ILecturerDAL lecturerDAL = new LecturerDAL();
    private CourseBUS courseBUS = new CourseBUS(courseDAL, lecturerDAL);
    private LecturerBUS lecturerBUS = new LecturerBUS(lecturerDAL);
    private InstructorDAL instructorDAL = new InstructorDAL();
    private InstructorBUS instructorBUS = new InstructorBUS(instructorDAL);
    private ArrayList<LecturerDTO> listAssignedtLectures = new ArrayList<LecturerDTO>();
    private ArrayList<LecturerDTO> listUnassignedLectures = new ArrayList<LecturerDTO>();
    // các đối tượng liên quan đến bảng
    private DefaultTableModel assignedTableModel;
    private int selectedRowAssignedTable;
    private DefaultTableModel unassignedTableModel;
    private int selectedRowUnassignedTable;
    
    public AssignmentManagement(int courceID) {
        initComponents();
        // lấy tạm thời một khóa học để test
        this.courseDTO = courseBUS.getACourseByID(courceID);
        //
        tableInitialization();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        addButton = new javax.swing.JButton();
        NameOfLecturerJTF = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        UnassignedJT = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        AssignedJT = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deselection(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("QUẢN LÝ PHÂN CÔNG");

        addButton.setBackground(new java.awt.Color(0, 0, 255));
        addButton.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        addButton.setForeground(new java.awt.Color(255, 255, 255));
        addButton.setText("+ Thêm");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButton(evt);
            }
        });

        NameOfLecturerJTF.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        NameOfLecturerJTF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                findLecturerByName(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel2.setText("Tìm kiếm:");

        UnassignedJT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã giảng viên", "Họ tên"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        UnassignedJT.setRowHeight(40);
        jScrollPane2.setViewportView(UnassignedJT);

        AssignedJT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã giảng viên", "Họ tên", "Xóa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        AssignedJT.setRowHeight(40);
        jScrollPane1.setViewportView(AssignedJT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NameOfLecturerJTF, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addButton))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(225, 225, 225)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(NameOfLecturerJTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void findLecturerByName(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_findLecturerByName
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if(this.NameOfLecturerJTF.getText().isEmpty()) {
                loadDataToTable(this.listAssignedtLectures, this.listUnassignedLectures);
            } else {
                getLecturerByName(this.NameOfLecturerJTF.getText());
            }
        }
    }//GEN-LAST:event_findLecturerByName

    private void addButton(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButton
        assignToLecturer();
    }//GEN-LAST:event_addButton

    private void deselection(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deselection
        this.AssignedJT.clearSelection();
        this.UnassignedJT.clearSelection();
    }//GEN-LAST:event_deselection

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable AssignedJT;
    private javax.swing.JTextField NameOfLecturerJTF;
    private javax.swing.JTable UnassignedJT;
    private javax.swing.JButton addButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

    // các phương thức điều chỉnh giao diện 
    public void tableInitialization() {
        // lấy dữ liệu vào hai danh sách
        this.listAssignedtLectures = (ArrayList<LecturerDTO>) this.courseBUS.getLecturersOfCourse(this.courseDTO.getCourseID());
        this.listUnassignedLectures = (ArrayList<LecturerDTO>) this.courseBUS.getLecturerNotAssignForACourse(this.courseDTO.getCourseID());
        // khởi tạo đối tượng cho bảng
        this.assignedTableModel = (DefaultTableModel) this.AssignedJT.getModel();
        this.selectedRowAssignedTable = -1;
        this.unassignedTableModel = (DefaultTableModel) this.UnassignedJT.getModel();
        this.selectedRowUnassignedTable = -1;
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onDelete(int row) {
                if(AssignedJT.isEditing()) {
                    AssignedJT.getCellEditor().stopCellEditing();
                }
                cancelAssignmentToLecturer(row);
                System.out.println(row);
            }
        };
        this.AssignedJT.getColumnModel().getColumn(3).setCellRenderer(new TableActioncellRender());
        this.AssignedJT.getColumnModel().getColumn(3).setCellEditor(new TableActionCellEditor(event));
        // đẩy dữ liệu lên bảng
        loadDataToTable(this.listAssignedtLectures, listUnassignedLectures);
    }
    
    public Object loadDataToRow(LecturerDTO lecturerDTO, DefaultTableModel tableModel) {
        Object[] row = null;
        int id = lecturerDTO.getID();
        String lastName = lecturerDTO.getLastName();
        String firstName = lecturerDTO.getFirstName();
        row = new Object[] {
            tableModel.getRowCount() + 1,
            id,
            lastName + " " + firstName
        };
        return row;
    }
    
    public void loadDataToTable(ArrayList<LecturerDTO> listAssignedtLectures, 
            ArrayList<LecturerDTO> listUnassignedLectures) {
        this.assignedTableModel.setRowCount(0);
        this.unassignedTableModel.setRowCount(0);
        Object[] row = null;
        // đẩy dữ liệu lên bảng đã phân công
        for(LecturerDTO lecturerDTO : listAssignedtLectures) {
            row = (Object[]) loadDataToRow(lecturerDTO, this.assignedTableModel);
            this.assignedTableModel.addRow(row);
        }
        // đẩy dữ liệu lên bảng chưa phân công
        for(LecturerDTO lecturerDTO : listUnassignedLectures) {
            row = (Object[]) loadDataToRow(lecturerDTO, this.unassignedTableModel);
            this.unassignedTableModel.addRow(row);
        }
    }
    
    // các phương thức xử lý khác
    // xử lý các sự kiện tìm kiếm
    public void getLecturerByName(String nameOfLecturer) {
        ArrayList<LecturerDTO> listNewUnassigned = new ArrayList<LecturerDTO>();
        for(LecturerDTO lecturerDTO : this.listUnassignedLectures) {
            if(lecturerDTO.getFirstName().equalsIgnoreCase(nameOfLecturer)) {
                listNewUnassigned.add(lecturerDTO);
            }
        }
        loadDataToTable(this.listAssignedtLectures, listNewUnassigned);
    }
    
    // xử lý các sự kiện nút nhấn thêm
    public void assignToLecturer() {
        int[] selectedRows = this.UnassignedJT.getSelectedRows();
        for(int i=0; i<selectedRows.length; i++) {
            int rowIndex = selectedRows[i];
            int id = (int) this.unassignedTableModel.getValueAt(rowIndex, 1);
            LecturerDTO lecturerDTO = getALecturerByIdOnListUnassign(id);
            this.instructorBUS.createAInstructor(new InstructorDTO(this.courseDTO, lecturerDTO));
            this.listAssignedtLectures = (ArrayList<LecturerDTO>) this.courseBUS.getLecturersOfCourse(this.courseDTO.getCourseID());
            this.listUnassignedLectures = (ArrayList<LecturerDTO>) this.courseBUS.getLecturerNotAssignForACourse(this.courseDTO.getCourseID());
            loadDataToTable(this.listAssignedtLectures, this.listUnassignedLectures);
        }
    }
    
    public LecturerDTO getALecturerByIdOnListUnassign(int idLecturer) {
        for(LecturerDTO lecturerDTO : this.listUnassignedLectures) {
            if(lecturerDTO.getID() == idLecturer) {
                return lecturerDTO;
            }
        }
        return null;
    }
    
    // xử lý sự kiện nút xóa phân công
    public void cancelAssignmentToLecturer(int rowIndex) {
        int id = (int) this.assignedTableModel.getValueAt(rowIndex, 1);
        LecturerDTO lecturerDTO = getALecturerByIdOnListAssign(id);
        int result = this.instructorBUS.removeAInstructor(new InstructorDTO(this.courseDTO, lecturerDTO));
        this.listAssignedtLectures = (ArrayList<LecturerDTO>) this.courseBUS.getLecturersOfCourse(this.courseDTO.getCourseID());
        this.listUnassignedLectures = (ArrayList<LecturerDTO>) this.courseBUS.getLecturerNotAssignForACourse(this.courseDTO.getCourseID());
        loadDataToTable(this.listAssignedtLectures, this.listUnassignedLectures);
        System.out.println(result);
    }
    
    public LecturerDTO getALecturerByIdOnListAssign(int idLecturer) {
        for(LecturerDTO lecturerDTO : this.listAssignedtLectures) {
            if(lecturerDTO.getID() == idLecturer) {
                return lecturerDTO;
            }
        }
        return null;
    }
    
}
