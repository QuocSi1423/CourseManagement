/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import BUS.CourseBUS;
import BUS.StudentBUS;
import BUS.StudentGradeBUS;
import DAL.CourseDAL;
import DAL.IDAL.IStudentGradeDAL;
import DAL.LecturerDAL;
import DAL.StudentDAL;
import DAL.StudentGradeDAL;
import DTO.CourseDTO;
import DTO.DepartmentDTO;
import DTO.StudentDTO;
import DTO.StudentGradeDTO;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Thinkbook 14 G3
 */
public class pointmanagement extends javax.swing.JFrame {

	public int courseid;

	public static List<StudentDTO> listStudent;
	public static List<StudentGradeDTO> listdiem;

	public pointmanagement(int CourseID) {
		initComponents();
		this.courseid = CourseID;
		functionmain();
		this.setLocationRelativeTo(null);

		editpoint.setVisible(false);

	}

	public void functionmain() {
		StudentDAL studendal = new StudentDAL();
		StudentBUS studenBus = new StudentBUS(studendal);
		List<StudentDTO> Listst = studenBus.getAllStudent();
		CourseDAL cou = new CourseDAL();
		listStudent = Listst;

		StudentDAL studentDAL = new StudentDAL();
		CourseDAL courseDAL = new CourseDAL();
		// Tạo đối tượng StudentGradeDAL và gọi phương thức
		StudentGradeDAL studentGr = new StudentGradeDAL(studentDAL, courseDAL);
		StudentGradeBUS studentGrade = new StudentGradeBUS(studentGr, courseDAL);
		List<StudentGradeDTO> diem = studentGrade.getStudentGradesOfCCourse(courseid);
		listdiem = diem;
		showall(listdiem);
		tableCustomizer();
	}

	private void tableCustomizer() {
		// tao mau cho title table
		JTableHeader header = tablemanager.getTableHeader();
		header.setPreferredSize(new Dimension(header.getPreferredSize().width, 48));
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
		tablemanager.setDefaultRenderer(String.class, centerRenderer);
		headerRenderer.setBackground(Color.GRAY);
		headerRenderer.setForeground(Color.WHITE);
		for (int i = 0; i < tablemanager.getModel().getColumnCount(); i++) {

			tablemanager.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
		}
	}

	public void showall(List<StudentGradeDTO> diem) {
		int i = 0;
		// Tạo danh sách các tiêu đề cột
		String[] columnName = { "Stt", "Mã sinh viên", "Họ  ", "Tên", "Điểm", "Tùy chọn" };
		ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/img/icons8-pen-20.png"));
		// Tạo một JLabel với biểu tượng hình ảnh
		JLabel deleteLabel = new JLabel(deleteIcon);
		// Tạo một DefaultTableModel để lưu trữ dữ liệu và tiêu đề cột

		DefaultTableModel model = new DefaultTableModel(columnName, 0);
		model.setRowCount(0);
		// Thêm dữ liệu từ danh sách khóa học vào DefaultTableModel

		for (StudentGradeDTO diemtp : diem) {
			Object[] rowData = {
					i,
					diemtp.getStudent().getID(),
					diemtp.getStudent().getFirstName(),
					diemtp.getStudent().getLastName(),
					diemtp.getGrade(),

					deleteLabel,
			};
			i++;
			model.addRow(rowData);
		}

		// ImageIcon deleteIcon = new ImageIcon("/img/icons8-delete-30.png");

		// Gán DefaultTableModel vào JTable
		tablemanager.setModel(model);

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

		tablemanager.getColumnModel().getColumn(5).setCellRenderer(renderer);

	}

	public void search(List<StudentDTO> listStudent) {
		List<StudentDTO> list = new ArrayList<>();
		String text = Search.getText().toLowerCase();
		if (text.isEmpty()) {
			showall(listdiem);
		} else {
			for (int i = 0; i < listStudent.size(); i++) {
				String title = listStudent.get(i).getLastName().toLowerCase();
				if (title.contains(text)) {
					list.add(listStudent.get(i));
				}
			}
			showall(listdiem);
		}

	}

	public void update(int idstd) {

		int iddiem = 0;
		double diem = Double.parseDouble(point.getText());

		for (int i = 0; i < listdiem.size(); i++) {
			if (idstd == listdiem.get(i).getStudent().getID()) {
				iddiem = listdiem.get(i).getEnrollmentID();
			}

		}

		DepartmentDTO c = new DepartmentDTO();
		StudentDTO a = new StudentDTO(idstd, "", "", LocalDateTime.MIN);
		CourseDTO b = new CourseDTO(courseid, c, "", SOMEBITS);
		StudentGradeDTO newstd = new StudentGradeDTO(iddiem, diem, a, b);

		StudentDAL studentDAL = new StudentDAL();
		CourseDAL courseDAL = new CourseDAL();
		// Tạo đối tượng StudentGradeDAL và gọi phương thức
		StudentGradeDAL studentGr = new StudentGradeDAL(studentDAL, courseDAL);
		StudentGradeBUS studentGrade = new StudentGradeBUS(studentGr, courseDAL);
		studentGrade.updateObject(newstd);

		functionmain();
	}

	public void onclickpen(int column, int row) {

		if (column == 5) { // Xác định chỉ số cột thứ 7 (chỉ số bắt đầu từ 0)
			// Lấy giá trị của cột thứ 1 của dòng được nhấp chuột và in ra
			Object value = tablemanager.getValueAt(row, 2);
			ten.setText((String) value);
			Object masv = tablemanager.getValueAt(row, 1);
			String masvString = masv.toString();
			idsv.setText(masvString);
			editpoint.setVisible(true);

		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jPanel2 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		Search = new javax.swing.JTextField();
		jScrollPane1 = new javax.swing.JScrollPane();
		tablemanager = new javax.swing.JTable();
		editpoint = new javax.swing.JPanel();
		point = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		ten = new javax.swing.JLabel();
		jButton1 = new javax.swing.JButton();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		idsv = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		jPanel1.setBackground(new java.awt.Color(255, 255, 255));

		jPanel2.setBackground(new java.awt.Color(255, 255, 255));
		jPanel2.setForeground(new java.awt.Color(255, 255, 255));

		jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		jLabel1.setForeground(new java.awt.Color(11, 8, 171));
		jLabel1.setText("Quản Lý điểm");

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(
				jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel2Layout.createSequentialGroup()
								.addGap(250, 250, 250)
								.addComponent(jLabel1)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel2Layout.setVerticalGroup(
				jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel2Layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jLabel1)
								.addContainerGap(10, Short.MAX_VALUE)));

		Search.setBackground(new java.awt.Color(255, 255, 255));
		Search.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
		Search.setForeground(new java.awt.Color(0, 51, 51));
		Search.setText("Tìm kiếm");
		Search.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				SearchMouseClicked(evt);
			}
		});
		Search.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				SearchKeyPressed(evt);
			}
		});

		tablemanager.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] {
						{ null, null, null, null, null },
						{ null, null, null, null, null },
						{ null, null, null, null, null },
						{ null, null, null, null, null },
						{ null, null, null, null, null }
				},
				new String[] {
						"Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
				}));
		tablemanager.setRowHeight(40);
		tablemanager.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tablemanagerMouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(tablemanager);

		editpoint.setBackground(new java.awt.Color(11, 8, 171));
		editpoint.setForeground(new java.awt.Color(102, 102, 102));

		point.setBackground(new java.awt.Color(255, 255, 255));
		point.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		point.setForeground(new java.awt.Color(0, 0, 153));

		jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		jLabel2.setForeground(new java.awt.Color(255, 255, 255));
		jLabel2.setText("Mã :");

		ten.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		ten.setForeground(new java.awt.Color(255, 255, 255));
		ten.setText("Tên");

		jButton1.setBackground(new java.awt.Color(255, 255, 255));
		jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		jButton1.setForeground(new java.awt.Color(11, 8, 171));
		jButton1.setText("Xác nhận");
		jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jButton1MouseClicked(evt);
			}
		});
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		tablemanager.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()) {
			@Override
			public boolean stopCellEditing() {
				boolean stopped = super.stopCellEditing();
				if (stopped) {
					System.out.println(tablemanager.getValueAt(tablemanager.getSelectedRow(), 4));

					int studentID = (int) tablemanager.getValueAt(tablemanager.getSelectedRow(), 1);
					int enrollmentID = 0;

					for (int i = 0; i < listdiem.size(); i++) {
						if (studentID == listdiem.get(i).getStudent().getID()) {
							enrollmentID = listdiem.get(i).getEnrollmentID();
						}
					}

					System.out.println(enrollmentID);

					StudentGradeDTO studentGrade = new StudentGradeDTO(enrollmentID,
							Double.parseDouble((String) tablemanager.getValueAt(tablemanager.getSelectedRow(), 4)), new StudentDTO(),
							new CourseDTO());

					StudentDAL studentDAL = new StudentDAL();
					CourseDAL courseDAL = new CourseDAL();
					StudentGradeDAL studentGr = new StudentGradeDAL(studentDAL, courseDAL);
					StudentGradeBUS studentGradeBUS = new StudentGradeBUS(studentGr, courseDAL);
					studentGradeBUS.updateObject(studentGrade);

					int row = tablemanager.getSelectedRow();
					int column = tablemanager.getSelectedColumn();
					if (row < tablemanager.getRowCount() - 1) {
						tablemanager.changeSelection(row + 1, column, false, false);
						tablemanager.editCellAt(row + 1, column);
						JTextField textField = (JTextField) tablemanager.getEditorComponent();
						if (textField != null) {
							textField.requestFocusInWindow();
						}
					}
				}
				return stopped;
			}
		});

		jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		jLabel4.setForeground(new java.awt.Color(255, 255, 255));
		jLabel4.setText("Điểm :");

		jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
		jLabel5.setForeground(new java.awt.Color(11, 8, 171));
		jLabel5.setText("Chỉnh sửa điểm");

		idsv.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		idsv.setForeground(new java.awt.Color(255, 255, 255));
		idsv.setText("mã sinh viên");

		jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
		jLabel3.setForeground(new java.awt.Color(255, 255, 255));
		jLabel3.setText("Tên :");

		jLabel6.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
		jLabel6.setForeground(new java.awt.Color(255, 0, 0));
		jLabel6.setText("X");
		jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jLabel6MouseClicked(evt);
			}
		});

		javax.swing.GroupLayout editpointLayout = new javax.swing.GroupLayout(editpoint);
		editpoint.setLayout(editpointLayout);
		editpointLayout.setHorizontalGroup(
				editpointLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(editpointLayout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(editpointLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(editpointLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
												.addGroup(editpointLayout.createSequentialGroup()
														.addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE,
																54, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(point, javax.swing.GroupLayout.PREFERRED_SIZE,
																105, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(editpointLayout.createSequentialGroup()
														.addGroup(editpointLayout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(jLabel2,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 45,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(jLabel3,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 45,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addGroup(editpointLayout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(idsv)
																.addComponent(ten,
																		javax.swing.GroupLayout.PREFERRED_SIZE, 99,
																		javax.swing.GroupLayout.PREFERRED_SIZE))))
										.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 172,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(editpointLayout.createSequentialGroup()
								.addGap(244, 244, 244)
								.addComponent(jLabel5)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 18,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));
		editpointLayout.setVerticalGroup(
				editpointLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editpointLayout.createSequentialGroup()
								.addGroup(editpointLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jLabel5)
										.addGroup(editpointLayout.createSequentialGroup()
												.addContainerGap()
												.addComponent(jLabel6)))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(
										editpointLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(ten)
												.addComponent(jLabel3))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										editpointLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(idsv)
												.addComponent(jLabel2))
								.addGap(14, 14, 14)
								.addGroup(
										editpointLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel4)
												.addComponent(point, javax.swing.GroupLayout.PREFERRED_SIZE, 19,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(23, 23, 23)
								.addComponent(jButton1)
								.addGap(23, 23, 23)));

		jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-search-15 (2).png"))); // NOI18N

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
								.addContainerGap(51, Short.MAX_VALUE)
								.addGroup(jPanel1Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 617,
												Short.MAX_VALUE)
										.addGroup(jPanel1Layout.createSequentialGroup()
												.addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 136,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addComponent(editpoint, javax.swing.GroupLayout.Alignment.TRAILING,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGap(48, 48, 48)));
		jPanel1Layout.setVerticalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup()
								.addGap(33, 33, 33)
								.addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, 0)
								.addGroup(jPanel1Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(Search, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
										.addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(editpoint, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, 0)
								.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(0, 6, Short.MAX_VALUE)));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void SearchMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_SearchMouseClicked
		// TODO add your handling code here:

	}// GEN-LAST:event_SearchMouseClicked

	private void SearchKeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_SearchKeyPressed
		// TODO add your handling code here:

		search(listStudent);
	}// GEN-LAST:event_SearchKeyPressed

	private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jButton1MouseClicked
		// TODO add your handling code here:
		int idstd = Integer.parseInt(idsv.getText());
		String poi = point.getText();
		System.out.println(poi);
		if (poi.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Không để trống điểm", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		} else
			update(idstd);

	}// GEN-LAST:event_jButton1MouseClicked

	private void tablemanagerMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tablemanagerMouseClicked
		// TODO add your handling code here:
		int column = tablemanager.getColumnModel().getColumnIndexAtX(evt.getX()); // Lấy chỉ số cột từ vị trí x của
																																							// chuột
		int row = evt.getY() / tablemanager.getRowHeight(); // Lấy chỉ số dòng từ vị trí y của chuột
		onclickpen(column, row);
	}// GEN-LAST:event_tablemanagerMouseClicked

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jButton1ActionPerformed

	private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jLabel6MouseClicked
		// TODO add your handling code here:
		editpoint.setVisible(false);
	}// GEN-LAST:event_jLabel6MouseClicked

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel.
		 * For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(pointmanagement.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(pointmanagement.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(pointmanagement.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(pointmanagement.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		}
		// </editor-fold>
		// </editor-fold>
		// </editor-fold>
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new pointmanagement(4022).setVisible(true);

			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JTextField Search;
	private javax.swing.JPanel editpoint;
	private javax.swing.JLabel idsv;
	private javax.swing.JButton jButton1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextField point;
	private javax.swing.JTable tablemanager;
	private javax.swing.JLabel ten;
	// End of variables declaration//GEN-END:variables
}
