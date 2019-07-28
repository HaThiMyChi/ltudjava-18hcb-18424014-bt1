package model;

import java.util.List;

public class SinhVienObj {
	public String MaSV;
	public String TenSV;
	public String GioiTinh;
	public String DiaChi;

	public String getGioiTinh() {
		return GioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		GioiTinh = gioiTinh;
	}

	public String getDiaChi() {
		return DiaChi;
	}

	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}

	public SinhVienObj(String maSV, String tenSV, String gioitinh, String diachi) {
		MaSV = maSV;
		TenSV = tenSV;
		DiaChi = diachi;
		GioiTinh = gioitinh;
	}

	public String getMaSV() {
		return MaSV;
	}

	public void setMaSV(String maSV) {
		MaSV = maSV;
	}

	public String getTenSV() {
		return TenSV;
	}

	public void setTenSV(String tenSV) {
		TenSV = tenSV;
	}

	public boolean CheckMaSV(String maSV, List<SinhVienObj> list) {
		if (list == null || maSV.isEmpty())
			return false;
		for (SinhVienObj sv : list) {
			if (sv.MaSV.equals(maSV)) {
				return true;
			}
		}
		return false;
	}

	public String convertObjectToString(SinhVienObj sinhvien) {
		String str = "";
		str = sinhvien.MaSV + "," + sinhvien.TenSV + "," + sinhvien.GioiTinh + "," + sinhvien.DiaChi;
		return str;
	}
}
