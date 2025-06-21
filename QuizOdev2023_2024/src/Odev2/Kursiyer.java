// Sude Özsoy 22100011074
package Odev2;

import java.util.ArrayList;

public class Kursiyer implements Hesaplama{
	private int kursiyerId,kursiyerYas;
	private String kursiyerAdSoyad;
	ArrayList<Kurs> alinanKurslar;
	
	public Kursiyer(int kursiyerId, int kursiyerYas, String kursiyerAdSoyad) {
		super();
		this.kursiyerId = kursiyerId;
		this.kursiyerYas = kursiyerYas;
		this.kursiyerAdSoyad = kursiyerAdSoyad;
	}
	
	public int getKursiyerId() {
		return kursiyerId;
	}
	public int getKursiyerYas() {
		return kursiyerYas;
	}
	public String getKursiyerAdSoyad() {
		return kursiyerAdSoyad;
	}

	@Override
	public double BorcHesapla() {
		double aylik=2000,topkurs=0,ucret=0;
		
		for(Kurs k: alinanKurslar)
			topkurs++;
		
		if(topkurs>3)
		{
			ucret = aylik*topkurs*0.9;
			System.out.println("-Kampanya 3'den yararlanildi.");
		}
		else if(topkurs==3)
		{
			ucret = aylik*2 + aylik*0.75;
			System.out.println("-Kampanya 2'den yararlanildi.");
		}
		else if(topkurs == 2)
		{
			ucret = aylik+aylik*0.8;
			System.out.println("-Kampanya 1'den yararlanildi.");
		}
		else
		{
			ucret = aylik;
			System.out.println("-Tek kursu olanlara kampanya uygulanmamaktadir.");
		}
		return ucret;
	}
	
}
