package form;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import helper.FileHandler;
import model.SinhVienObj;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main {

    private String fileName = "src/resource/SinhVien.txt";
    public JFrame frame;
    private JTextField txt_ma;
    private JTextField txt_name;
    public List<SinhVienObj> lstSv;
    private JTable table;
    private JScrollPane scrollPane;
    private JButton btn_search;
    private JButton btn_add;
    private JButton btn_delete;
    private JPanel panel;
    private JButton btn_load;
    private JButton btn_update;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main window = new Main();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Main() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        lstSv = new ArrayList<>();
        lstSv = loadFile();
        frame = new JFrame("QUẢN LÝ SINH VIÊN");
        frame.setBounds(100, 100, 499, 440);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        panel = new JPanel();
        panel.setBounds(0, 0, 483, 85);
        frame.getContentPane().add(panel);

        txt_ma = new JTextField();
        txt_ma.setBounds(139, 11, 203, 20);

        JLabel lb_masv = new JLabel("Mã Sinh Viên");
        lb_masv.setBounds(29, 14, 87, 14);

        JLabel lb_name = new JLabel("Tên Sinh Viên");
        lb_name.setBounds(29, 48, 87, 14);

        txt_name = new JTextField();
        txt_name.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        });
        txt_name.setBounds(139, 42, 203, 20);
        txt_name.setColumns(10);

        btn_search = new JButton("Tìm");
        btn_search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                search(e);
            }
        });
        btn_search.setBounds(373, 44, 64, 23);
        panel.setLayout(null);
        panel.add(lb_masv);
        panel.add(txt_ma);
        panel.add(lb_name);
        panel.add(txt_name);
        panel.add(btn_search);

        btn_add = new JButton("Thêm");
        btn_add.setBackground(Color.GREEN);
        btn_add.setBounds(20, 367, 89, 23);
        btn_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                SinhVienUI sv = new SinhVienUI();
                sv.setVisible(true);
                frame.setVisible(false);

                
            }
        });
        frame.getContentPane().add(btn_add);

        btn_delete = new JButton("Xoá");
        btn_delete.setForeground(Color.WHITE);
        btn_delete.setBackground(Color.RED);
        btn_delete.setBounds(246, 367, 89, 23);
        btn_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent agr0) {
                remove(agr0);
            }
        });
        frame.getContentPane().add(btn_delete);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][]{{null, null, null, null}, {null, null, null, null}, {null, null, null, null},
                {null, null, null, null}},
                new String[]{"Mã Sinh Viên", "Tên Sinh Viên", "Giới Tính", "Địa Chỉ"}
        ));
        LoadFrame();
        table.setBounds(10, 96, 463, 164);
        frame.getContentPane().add(table);
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 96, 463, 260);
        frame.getContentPane().add(scrollPane);

        btn_load = new JButton("Tải Lại");
        btn_load.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                LoadFrame();
            }
        });
        btn_load.setBounds(372, 367, 89, 23);
        frame.getContentPane().add(btn_load);

        btn_update = new JButton("Cập Nhật");
        btn_update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                update(arg0);
            }
        });
        btn_update.setBounds(122, 367, 89, 23);
        frame.getContentPane().add(btn_update);
    }

    public List<SinhVienObj> loadFile() {
        try {
            List<SinhVienObj> lst = new ArrayList<>();
            List<String> list = FileHandler.readAllLine(fileName);
            lst = ConvertStringToObject(list);
            return lst;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }

    private List<SinhVienObj> ConvertStringToObject(List<String> list) throws Exception {
        List<SinhVienObj> lst = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String str[] = list.get(i).split(",");
            try {
                SinhVienObj sinhVien;
                sinhVien = new SinhVienObj(str[0].toString(), str[1].toString(), str[2].toString(), str[3].toString());
                lst.add(sinhVien);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lst;
    }

    public void LoadFrame() {
        lstSv = new ArrayList<>();
        lstSv = loadFile();
        if (lstSv != null && lstSv.size() > 0) {
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            tableModel.setRowCount(0);
            lstSv.stream().forEach(a -> {
                tableModel.addRow(new Object[]{a.getMaSV(), a.getTenSV(), a.getGioiTinh(), a.getDiaChi()});
            });
            table.setModel(tableModel);
        }
    }

    public void LoadFrame(List<SinhVienObj> sv) {
        if (sv != null && sv.size() > 0) {
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            tableModel.setRowCount(0);
            sv.stream().forEach(a -> {
                tableModel.addRow(new Object[]{a.getMaSV(), a.getTenSV(), a.getGioiTinh(), a.getDiaChi()});
            });
            table.setModel(tableModel);
        }
    }

    private void remove(ActionEvent e) {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Xin chọn dòng sinh viên cần xoá");
            return;
        } else {
            String maSV = (String) table.getValueAt(row, 0);
            for (SinhVienObj sv : lstSv) {
                if (sv.getMaSV().equalsIgnoreCase(maSV)) {
                    JOptionPane.showMessageDialog(null, "Xóa thành công");
                    lstSv.remove(sv);
                    LoadFrame();
                    break;
                }
            }
            try {
                FileHandler.writeToFile(convertObjectToString(lstSv), fileName, false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        // JOptionPane.showMessageDialog(null, "Đã có lỗi xảy ra, thử lại sau");
    }

    private void search(ActionEvent e) {
        String masv = txt_ma.getText();
        String tenSv = txt_name.getText();
        if (masv.isEmpty() && tenSv.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Mời bạn nhập vào ô tìm kiếm!");
            return;
        }
        List<SinhVienObj> lst = new ArrayList<>();
        for (SinhVienObj sv : lstSv) {
            if (masv.isEmpty() && !tenSv.isEmpty() && sv.TenSV.equals(tenSv)) {
                lst.add(sv);
            } else {
                if (!masv.isEmpty() && tenSv.isEmpty() && sv.MaSV.equals(masv)) {
                    lst.add(sv);
                } else {
                    if (sv.getTenSV().equals(tenSv) && sv.getTenSV().equals(tenSv)) {
                        lst.add(sv);
                    }
                }
            }

        }
        LoadFrame(lst);
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

    private void update(ActionEvent e) {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Xin chọn dòng sinh viên cần update");
            return;
        } else {
            String maSV = (String) table.getValueAt(row, 0);
            for (SinhVienObj sv : lstSv) {
                if (sv.getMaSV().equalsIgnoreCase(maSV)) {
                    SinhVienUI svUi = new SinhVienUI(true, maSV, lstSv);
                    svUi.setTitle("CẬP NHẬT THÔNG TIN MASV:" + maSV);
                    svUi.setVisible(true);
                    frame.setVisible(false);
                    break;
                }
            }
        }
    }
}
