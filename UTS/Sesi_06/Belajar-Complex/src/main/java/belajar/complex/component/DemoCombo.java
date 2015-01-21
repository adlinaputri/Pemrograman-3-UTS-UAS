package belajar.complex.component;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class DemoCombo {
	private static JComboBox cmbMahasiswa = new JComboBox();
	private static final List<String> daftarMahasiswa = new ArrayList<String>();
	
	public static void isiCombo(){
		DefaultComboBoxModel model = newDefaultComboBoxModel(daftarMahasiswa.toArray());
		
		cmbMahasiswa.setModel(model);
	}
	
	public static void tambahIsiCombo(String data){
		daftarMahasiswa.add(data);
		isiCombo();
	}
	
	public static void main(String[] xx){
		tambahIsiCombo("adlina");
		
		JLabel lbl = new JLabel("Nama Mahasiswa : ");
		final JTextField tfMahasiswa = new JTextField(20);
		JButton btnTambah = new JButton("Tambah");
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(lbl);
		panel.add(tfMahasiswa);
		panel.add(btnTambah);
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		
		panel2.add(cmbMahasiswa);
		
		JPanel panel4 = new JPanel(new FlowLayout());
		panel4.add(new JLabel("Pilihan : "));
		final JTextField tfPilihan = new JTextField(20);
		tfPilihan.setEditable(false);
		panel4.add(tfPilihan);
		
		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(3,1));
		
		panel3.add(panel);
		panel3.add(panel2);
		panel3.add(panel4);
		
		JFrame fr = new JFrame("Belajar Combo");
		
		fr.getContentPane().add(panel3);
		
		fr.setSize(800,200);
		
		fr.setLocationRelativeTo(null);
		
		fr.setVisible(true);
		
		class TambahMahasiswaListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				String data = tfMahasiswa.getText();
				System.out.println("Isi textfield : "+data);
				
				tambahIsiCombo(data);
				
				tfMahasiswa.setText("");
				tfMahasiswa.requestFocus();
			}
		}
		
		TambahMahasiswaListener l = new TambahMahasiswaListener();
		btnTambah.addActionListener(l);
		tfMahasiswa.addActionListener(l);
		
		class PilihanComboListener implements ActionListener {
			public void actionPerformed(ActionEvent e){
				JComboBox cb = (JComboBox) e.getSource();
				String pilihan = (String) cb.getSelectedItem();
				tfPilihan.setText(pilihan);
			}
		}
		
		cmbMahasiswa.addActionListener(new PilihanComboListener());
	}

}

