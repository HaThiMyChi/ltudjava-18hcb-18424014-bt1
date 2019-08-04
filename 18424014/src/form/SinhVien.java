/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import helper.FileHandler;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import model.SinhVienObj;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Ha Chi
 */
public class SinhVien extends javax.swing.JInternalFrame {

    private final JFileChooser filestudent;
    private String folder, filename;

    /**
     * Creates new form SinhVien
     */
    public SinhVien() throws IOException {
        initComponents();
        filestudent = new JFileChooser();
        filestudent.setCurrentDirectory(new File("C:\\Users\\Ha Chi\\Desktop"));
        filestudent.setFileFilter(new FileNameExtensionFilter("File CSV", "csv"));
        Loadlistlop();
        LoadSinhVien(filename);
    }

    public void Loadlistlop() {
        try {
            File f = new File("src/resource");
            String[] files = f.list();
            for (String file : files) {
                cbxLop.addItem(filename(file, '/', '.'));
            }           
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    private void LoadSinhVien(String filename) throws IOException {
        List<SinhVienObj> lst = new ArrayList<>();
        List<String> list = FileHandler.readAllLine(filename);
        if (!list.isEmpty()) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                String[] str = list.get(i).split(",");
                SinhVienObj sv = new SinhVienObj(str[0], str[1], str[2], str[3]);
                lst.add(sv);
            }
            DefaultTableModel model = (DefaultTableModel) tblDanhSach.getModel();
            model.setRowCount(0);
            String[] columnsName = {"STT", "MSSV", "Họ tên", "Giới tính", "CMND"};
            model.setColumnIdentifiers(columnsName);
            int i = 1;
            for (SinhVienObj st : lst) {
                Vector row = new Vector();
                String number = Integer.toString(i);
                row.add(number);
                row.add(st.getMaSV());
                row.add(st.getTenSV());
                row.add(st.getGioiTinh());
                row.add(st.getDiaChi());
                model.addRow(row);
                i++;
            }
            tblDanhSach.setModel(model);
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

        pnDanhSachSinhVien = new javax.swing.JPanel();
        spDanhSach = new javax.swing.JScrollPane();
        tblDanhSach = new javax.swing.JTable();
        btnDanhSachLop = new javax.swing.JButton();
        cbxLop = new javax.swing.JComboBox<>();
        btnThemSinhVien = new javax.swing.JButton();

        pnDanhSachSinhVien.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh Sách Sinh Viên"));

        tblDanhSach.setAutoCreateRowSorter(true);
        tblDanhSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblDanhSach.setOpaque(false);
        spDanhSach.setViewportView(tblDanhSach);

        btnDanhSachLop.setText("Import danh sách lớp");
        btnDanhSachLop.setToolTipText("");
        btnDanhSachLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDanhSachLopActionPerformed(evt);
            }
        });

        cbxLop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxLopActionPerformed(evt);
            }
        });

        btnThemSinhVien.setText("Thêm sinh viên");
        btnThemSinhVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSinhVienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnDanhSachSinhVienLayout = new javax.swing.GroupLayout(pnDanhSachSinhVien);
        pnDanhSachSinhVien.setLayout(pnDanhSachSinhVienLayout);
        pnDanhSachSinhVienLayout.setHorizontalGroup(
            pnDanhSachSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDanhSachSinhVienLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnDanhSachSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnDanhSachSinhVienLayout.createSequentialGroup()
                        .addComponent(cbxLop, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDanhSachLop, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThemSinhVien, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(spDanhSach, javax.swing.GroupLayout.PREFERRED_SIZE, 961, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        pnDanhSachSinhVienLayout.setVerticalGroup(
            pnDanhSachSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnDanhSachSinhVienLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(pnDanhSachSinhVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDanhSachLop, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxLop)
                    .addComponent(btnThemSinhVien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(spDanhSach, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnDanhSachSinhVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnDanhSachSinhVien, javax.swing.GroupLayout.PREFERRED_SIZE, 307, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static String filename(String str, char sep, char ext) {
        String fullpath = str;
        int dot1 = fullpath.lastIndexOf(ext);
        int sep1 = fullpath.lastIndexOf(sep);
        return fullpath.substring(sep1 + 1, dot1);
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

    private void cbxLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxLopActionPerformed
        filename = cbxLop.getSelectedItem() + ".csv";
        try {
            LoadSinhVien(filename);
        } catch (IOException ex) {
            Logger.getLogger(SinhVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cbxLopActionPerformed

    private void btnThemSinhVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSinhVienActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        AddSV ad = new AddSV();
        ad.setVisible(true);
    }//GEN-LAST:event_btnThemSinhVienActionPerformed

    private void btnDanhSachLopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDanhSachLopActionPerformed
        // TODO add your handling code here:
        int returnvalue = filestudent.showOpenDialog(this);
        String dir = "src/resource";
        if (returnvalue == JFileChooser.APPROVE_OPTION) {
            File file = filestudent.getSelectedFile();
            try {
                filename = file.toString();
                String files = filename(filename, '\\', '.');
                File f = new File(dir, files + ".csv");
                if (f.exists()) {
                    JOptionPane.showMessageDialog(this, "File này đã có, nếu bạn muốn thêm sinh viên vào lớp thì thêm vào phần bên trái");
                } else {
                    List<String> lstimportdanhsach = FileHandler.readAllLineFileImport(filename);
                    int size = lstimportdanhsach.size();
                    for (int i = 0; i < size; i++) {
                        FileHandler.writeToFile(lstimportdanhsach.get(i), files + ".csv", true);
                    }
                    cbxLop.removeAllItems();
                    Loadlistlop();
                    LoadSinhVien(files + ".csv");
                }
            } catch (Exception ex) {
                ex.getMessage();
            }
        }
    }//GEN-LAST:event_btnDanhSachLopActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDanhSachLop;
    private javax.swing.JButton btnThemSinhVien;
    private javax.swing.JComboBox<String> cbxLop;
    private javax.swing.JPanel pnDanhSachSinhVien;
    private javax.swing.JScrollPane spDanhSach;
    private javax.swing.JTable tblDanhSach;
    // End of variables declaration//GEN-END:variables

    private static class filestudent {

        public filestudent() {
        }
    }
}
