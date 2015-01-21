package com.adlinaputri.resep_makanan;

public class Resep {
	private String nama;
	private String resep;

	public String getNama(){
		return nama;
	}
	public void setNama(String nama){
		this.nama = nama;
	}
	public String getResep(){
		return resep;
	}
	public void setResep(String resep){
		this.resep = resep;
	}
	
	@Override
	public String toString(){
	return this.nama;	
	}
}
