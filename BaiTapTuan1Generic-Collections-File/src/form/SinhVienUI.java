package form;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import helper.FileHandler;
import model.SinhVienObj;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SinhVienUI extends JFrame {
	/**
	 * 
	 */
	private String fileName = "src/resource/SinhVien.txt";
	private JTextField txt_masv;
	private JTextField txt_name;
	private JComboBox<Object> cbbox_gioitinh;
	private JTextArea txt_diachi;
	public boolean update_add = false;
	public String maSvUpdate = "";
	private static final long serialVersionUID = 1L;

	public SinhVienUI() {
		Inital();
	}

	public SinhVienUI(boolean update, String maSvUp, List<SinhVienObj> lstSv) {
		Inital();
		update_add = update;
		maSvUpdate = maSvUp;
		for (SinhVienObj sv : lstSv) {
			if (sv.getMaSV().equalsIgnoreCase(maSvUpdate)) {
				txt_masv.setText(sv.MaSV);
				txt_masv.setEnabled(false);
				txt_name.setText(sv.TenSV);
				txt_diachi.setText(sv.DiaChi);
				if (sv.GioiTinh.equals("Nam")) {
					cbbox_gioitinh.setSelectedIndex(0);
				} else {
					cbbox_gioitinh.setSelectedIndex(1);
				}
			}
		}
	}

	public void Inital() {

		this.setTitle("THÊM SINH VIÊN");
		this.setVisible(false);
		this.setBounds(100, 100, 499, 299);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		JPanel panel = new JPanel();
		panel.setForeground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 464, 188);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Mã Sinh Viên");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(36, 29, 88, 14);
		panel.add(lblNewLabel);

		JLabel lblTnSinhVin = new JLabel("Tên Sinh Viên");
		lblTnSinhVin.setHorizontalAlignment(SwingConstants.LEFT);
		lblTnSinhVin.setBounds(36, 67, 88, 14);
		panel.add(lblTnSinhVin);

		txt_masv = new JTextField();
		txt_masv.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		txt_masv.setBounds(156, 26, 219, 20);
		panel.add(txt_masv);
		txt_masv.setColumns(10);

		txt_name = new JTextField();
		txt_name.setColumns(10);
		txt_name.setBounds(156, 64, 219, 20);
		panel.add(txt_name);

		JLabel lblGioiws = new JLabel("Giới Tính");
		lblGioiws.setHorizontalAlignment(SwingConstants.LEFT);
		lblGioiws.setBounds(36, 98, 88, 14);
		panel.add(lblGioiws);

		cbbox_gioitinh = new JComboBox<Object>(new String[] { "Nam", "Nữ" });
		cbbox_gioitinh.setSelectedIndex(0);
		cbbox_gioitinh.setBounds(156, 95, 101, 20);
		panel.add(cbbox_gioitinh);

		JLabel lblaCh = new JLabel("Địa Chỉ");
		lblaCh.setBounds(36, 134, 46, 14);
		panel.add(lblaCh);

		txt_diachi = new JTextArea();
		txt_diachi.setBounds(156, 126, 209, 51);
		panel.add(txt_diachi);

		JButton btnSave = new JButton("Lưu");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txt_masv.getText().isEmpty() || txt_name.getText().isEmpty()) {
					JOptionPane.showMessageDialog(new JFrame(), "Xin vui lòng nhập đầy đủ thông tin", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					String ma = txt_masv.getText();
					String name = txt_name.getText();
					String gioitinh = cbbox_gioitinh.getItemAt(cbbox_gioitinh.getSelectedIndex()).toString();
					String diachi = txt_diachi.getText();
					SinhVienObj sinhvien = new SinhVienObj(ma, name, gioitinh, diachi);
					if (update_add) {
						update(sinhvien);
					} else {
						AddSV(sinhvien);
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(new JFrame(), "Lỗi Hệ Thống", "Lỗi", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		btnSave.setBounds(93, 199, 89, 23);
		getContentPane().add(btnSave);

		JButton btnReload = new JButton("Reload");
		btnReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!update_add) {
					txt_masv.setText("");
				}
				cbbox_gioitinh.setSelectedIndex(0);
				txt_name.setText("");
				txt_diachi.setText("");
			}
		});
		btnReload.setBounds(229, 199, 89, 23);
		getContentPane().add(btnReload);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				// super.windowClosing(we);
				//JOptionPane.showMessageDialog(new JFrame(), "Đóng form", "Lỗi", JOptionPane.ERROR_MESSAGE);
				Main window = new Main();
				window.frame.setVisible(true);
				setVisible(false);
			}

			@Override
			public void windowClosed(WindowEvent e) {
				//JOptionPane.showMessageDialog(new JFrame(), "đĐóng form", "Lỗi", JOptionPane.ERROR_MESSAGE);
				setVisible(false);
				// super.windowClosed(e);
			}

		});
	}

	@SuppressWarnings("null")
	private void AddSV(SinhVienObj sinhvien) throws Exception {
		if (sinhvien == null) {
			JOptionPane.showMessageDialog(new JFrame(), "Lỗi Hệ Thống", "Thông Báo", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Main window = new Main();
		List<SinhVienObj> list = window.loadFile();
		try {
			if (list != null && sinhvien.CheckMaSV(sinhvien.MaSV, list)) {
				JOptionPane.showMessageDialog(new JFrame(), "Mã sinh viên này đã tồn tại", "Thông Báo",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			FileHandler.writeToFile(sinhvien.convertObjectToString(sinhvien), fileName, true);
			JOptionPane.showMessageDialog(new JFrame(), "Lưu thành công!");
			setVisible(false);
			window.frame.setVisible(true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void update(SinhVienObj sinhvien) {
		if (sinhvien == null) {
			JOptionPane.showMessageDialog(new JFrame(), "Lỗi Hệ Thống", "Thông Báo", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Main window = new Main();
		List<SinhVienObj> list = window.loadFile();
		try {
			for (SinhVienObj sv : list) {
				if (sv.getMaSV().equals(txt_masv.getText())) {
					sv.setGioiTinh(sinhvien.GioiTinh);
					sv.setDiaChi(sinhvien.DiaChi);
					sv.setTenSV(sinhvien.TenSV);
					JOptionPane.showMessageDialog(null, "Cập nhật dữ liệu thành công");
					FileHandler.writeToFile(convertObjectToString(list), fileName, false);
					setVisible(false);
					window.frame.setVisible(true);
					break;

				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public String convertObjectToString(List<SinhVienObj> lstnew) {
		String str = "";
		for (SinhVienObj sv : lstnew) {
			if (sv != null) {
				str += sv.convertObjectToString(sv) + "\n";
			}
		}
		return str;
	}
}
