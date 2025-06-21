/*// Sude Özsoy 22100011074
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
//Sude Özsoy 22100011074
package Odev2;

public class Kurs {
	private int kursId;
	private String kursAd;
	
	public Kurs(int kursId, String kursAd) {
		super();
		this.kursId = kursId;
		this.kursAd = kursAd;
	}

	public int getKursId() {
		return kursId;
	}

	public String getKursAd() {
		return kursAd;
	}
	
}
//Sude Özsoy 22100011074
package Odev2;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Anasayfa {

	public static void main(String[] args) throws IOException {
		String[] dizi;
		String satir;
		char karakter;
		File fkurs = new File("kurs.txt");
		File fkursiyer = new File("kursiyer.txt");
		
		ArrayList<Kursiyer> kursiyer = new ArrayList();
		ArrayList<Kurs> kurs = new ArrayList();
		
		BufferedReader bfkurs = new BufferedReader(new FileReader(fkurs));
		while((satir = bfkurs.readLine()) != null)
		{
			dizi = satir.split("\\+"); //+ yi tek basina kabul etmediğinden bu sekil yazdim
			Kurs yeni = new Kurs(Integer.valueOf(dizi[0]),dizi[1]); //string okunan veriyi hazir fonksiyonla inte cevirdim.
			kurs.add(yeni);
		}
		
		BufferedReader bfkursiyer = new BufferedReader(new FileReader(fkursiyer));
		Kursiyer yeni = null;
		while((satir = bfkursiyer.readLine()) != null)
		{
			if(satir.charAt(0) == '*')  // ilk karakter *sa buraya giriyor.
			{
				satir = satir.substring(1);  // 2. karakterden sona kadarki kismi aliyor yani *ı atiyor.
				dizi = satir.split("\\+");  // +yı tek basina kabul etmedigi icin \\ ekledim.
				yeni = new Kursiyer(Integer.valueOf(dizi[0]),Integer.valueOf(dizi[2]),dizi[1]);
				kursiyer.add(yeni);
				yeni.alinanKurslar = new ArrayList();
			}
			else
			{
				satir = satir.substring(1);
				dizi = satir.split("\\+");
				Kurs k = new Kurs(Integer.valueOf(dizi[0]),dizi[1]);
				yeni.alinanKurslar.add(k);
			}
		}
		bfkursiyer.close();
		bfkurs.close();
		menu(kursiyer,kurs);
	}
	public static void menu(ArrayList<Kursiyer> kursiyer,ArrayList<Kurs> kurs) throws IOException
	{
		Scanner r = new Scanner(System.in);
		int secim;
		System.out.print("1 – Kurs Ekle\r\n"
				+ "2 – Kurs Listele\r\n"
				+ "3 – Kurs Ara\r\n"
				+ "4 – Kurs Sil\r\n"
				+ "5 – Kursiyer Ekle\r\n"
				+ "6 – Kursiyer Ara\r\n"
				+ "7 – Kursiyer Sil\r\n"
				+ "8 – Kursiyerleri Listele\r\n"
				+ "9 – Kursiyerleri Ayrıntılı Listele\r\n"
				+ "10 – Kursiyerin Ödeyeceği Tutarı Hesapla\r\n"
				+ "11 – Çıkış\n"+"\t Secin:");
		secim = r.nextInt();
		switch(secim)
		{
		case 1:
			kursEkle(kurs);
			menu(kursiyer,kurs);
		case 2:
			kursListele(kurs);
			menu(kursiyer,kurs);
		case 3:
			kursAra(kurs);
			menu(kursiyer,kurs);
		case 4:
			kursSil(kursiyer,kurs);
			menu(kursiyer,kurs);
		case 5:
			kursiyerEkle(kursiyer,kurs);
			menu(kursiyer,kurs);
		case 6:
			kursiyerAra(kursiyer);
			menu(kursiyer,kurs);
		case 7:
			kursiyerSil(kursiyer);
			menu(kursiyer,kurs);
		case 8:
			kursiyerListele(kursiyer);
			menu(kursiyer,kurs);
		case 9:
			kursiyerAyrintiliListele(kursiyer);
			menu(kursiyer,kurs);
		case 10:
			int id;
			System.out.print("Ucreti hesaplanacak kursiyerin  id:");
			id = r.nextInt();
			kursiyerOdeyecegiTutarHesapla(kursiyer,id);
			menu(kursiyer,kurs);
		case 11:
			File fkurs = new File("kurs.txt");
			File fkursiyer = new File("Kursiyer.txt");
			
			FileWriter fw= new FileWriter(fkursiyer,false);
			BufferedWriter bw = new BufferedWriter(fw); 
			for(Kursiyer k: kursiyer)
			{
				bw.write("*"+k.getKursiyerId()+"+"+k.getKursiyerAdSoyad()+"+"+k.getKursiyerYas()+"\n");
				for(Kurs d: k.alinanKurslar)
				{
					bw.write("%"+d.getKursId()+"+"+d.getKursAd()+"\n");
				}
			}
			bw.close();
			FileWriter fwk= new FileWriter(fkurs,false);
			BufferedWriter bwk = new BufferedWriter(fwk); 
			for(Kurs k: kurs)
			{
				bwk.write(k.getKursId()+"+"+k.getKursAd()+"\n");
			}
			bwk.close();
			System.exit(0);
		}
	}
	public static void kursEkle(ArrayList<Kurs> kurs)
	{
		Scanner r = new Scanner(System.in);
		String ad;
		int id,kontrol;
		System.out.println("Kurs ekleme ekrani:");
		while(true)
		{
			kontrol=0;
			System.out.print("-Kurs Id:");
			id = r.nextInt();
			for(Kurs k : kurs)
			{
				if(k.getKursId() == id)
				{
					kontrol=1;
					break;
				}
			}
			if(kontrol == 1)
				System.out.println("Ayni id'ye sahip kurs vardir farklı bir id seçin.");
			else
				break;
		}
		System.out.print("-Kurs Adi:");
		ad = r.next();
		
		Kurs yeni = new Kurs(id,ad);
		kurs.add(yeni);
		System.out.println("Ekleme işlemi gerçekleşti.");
	}
	public static void kursListele(ArrayList<Kurs> kurs)
	{
		if(kurs.isEmpty())
			System.out.println("Kurs listesi bos.");
		else
		{
			System.out.println("Kurs Id Kurs Adi");
			for(Kurs k: kurs)
			{
				System.out.println(k.getKursId()+"\t"+k.getKursAd());
			}
		}
	}
	public static void kursAra(ArrayList<Kurs> kurs)
	{
		Scanner r = new Scanner(System.in);
		String ad;
		int kontrol=0;
		System.out.print("Arancak kursun adi:");
		ad = r.next();
		if(kurs.isEmpty())
			System.out.println("Kurs listesi bos.");
		else
		{
			for(Kurs k: kurs)
			{
				if(ad.equals(k.getKursAd()))
				{
					System.out.println("-"+k.getKursId()+"\t"+k.getKursAd());
					kontrol=1;
				}
			}
			if(kontrol == 0)
				System.out.println("-Aranan adda kurs bulunamadi.");
		}
	}
	public static void kursSil(ArrayList<Kursiyer> kursiyer,ArrayList<Kurs> kurs)
	{
		Scanner r = new Scanner(System.in);
		String ad;
		int kontrol=0;
		System.out.print("Silinecek kursun adi:");
		ad = r.next();
		if(kurs.isEmpty())
			System.out.println("Kurs listesi bos.");
		else
		{
			for(Kursiyer a: kursiyer)
			{
				for(Kurs k: a.alinanKurslar)
				{
					if(ad.equals(k.getKursAd()))
					{
						System.out.println("Bu dersi alan kursiyer vardir. Bu yuzden silinmeyecek.");
						return;
					}
				}
			}
			for(Kurs k: kurs)
			{
				if(ad.equals(k.getKursAd()))
				{
					kontrol=1;
					kurs.remove(k);
				}
			}
			if(kontrol == 1)
				System.out.println("Istenilen ders silindi.");
		}
	}
	public static void kursiyerEkle(ArrayList<Kursiyer> kursiyer,ArrayList<Kurs> kurs)
	{
		Scanner r = new Scanner(System.in);
		String ad;
		int id,kontrol,yas,kursid,secim;
		System.out.println("Kursiyer ekleme ekrani:");
		while(true)
		{
			kontrol=0;
			System.out.print("-Kursiyer Id:");
			id = r.nextInt();
			r.nextLine();
			for(Kursiyer k : kursiyer)
			{
				if(k.getKursiyerId() == id)
				{
					kontrol=1;
					break;
				}
			}
			if(kontrol == 1)
				System.out.println("Ayni id'ye sahip kursiyer vardir farklı bir id seçin.");
			else
				break;
		}
		kontrol=0;
		System.out.print("-Ad Soyad:");
		ad = r.nextLine();
		System.out.print("-Yas:");
		yas = r.nextInt();
		
		Kursiyer yeni = new Kursiyer(id,yas,ad);
		kursiyer.add(yeni);
		yeni.alinanKurslar = new ArrayList();
		while(true)
		{
			//kursListele(kurs);  // kurs listesinden hangisini ekleyecegi sorulması icin
			System.out.print("-Kursun id'si:");
			kursid = r.nextInt();
			for(Kurs k:kurs)
			{
				if(kursid == k.getKursId())
				{
					Kurs yenikurs = new Kurs(k.getKursId(),k.getKursAd());
					yeni.alinanKurslar.add(yenikurs);
					kontrol = 1;
					break;
				}
			}
			if(kontrol == 0)
				System.out.println("Girilen id'e kurs yoktur gecerli id girin.");
			else if(kontrol == 1)
			{
				System.out.print("Kurs eklendi,baska kurs eklemek icin 1'e basin:");
				secim = r.nextInt();
				if(secim==1)
					continue;
				else
					System.out.println("-Kursiyer kaydi tamamlandi.");
					break;
			}
		}
		
	}
	public static void kursiyerAra(ArrayList<Kursiyer> kursiyer)
	{
		Scanner r = new Scanner(System.in);
		String ad;
		int kontrol=0;
		System.out.print("Arancak kursun adi:");
		ad = r.next();
		if(kursiyer.isEmpty())
			System.out.println("Kursiyer listesi bos.");
		else
		{
			for(Kursiyer k: kursiyer)
			{
				if(ad.equals(k.getKursiyerAdSoyad()))
				{
					System.out.println("-"+k.getKursiyerId()+"\t"+k.getKursiyerAdSoyad());
					for(Kurs d: k.alinanKurslar)
					{
						System.out.println(k.getKursiyerId()+" "+k.getKursiyerAdSoyad()+" "+k.getKursiyerYas());
						System.out.println("\t"+d.getKursId()+" "+d.getKursAd());
					}
					kontrol=1;
				}
			}
			if(kontrol == 0)
				System.out.println("-Aranan adda kursiyer bulunamadi.");
		}
	}
	public static void kursiyerSil(ArrayList<Kursiyer> kursiyer)
	{
		Scanner r = new Scanner(System.in);
		int id,kontrol=0;
		System.out.print("Silinecek kursiyerin Id'sini girin:");
		id = r.nextInt();
		for(Kursiyer k: kursiyer)
		{
			if(id == k.getKursiyerId())
			{
				kursiyer.remove(k);
				kontrol=1;
				break;
			}
		}
		if(kontrol==1)
			System.out.println("-Silme islemi gerceklesti.");
		else
			System.out.println("-Girilen id'de kayitli kursiyer yoktur.");
	}
	public static void kursiyerListele(ArrayList<Kursiyer> kursiyer)
	{
		if(kursiyer.isEmpty())
		{
			System.out.println("Kursiyer listesi bos.");
			return;
		}
		for(Kursiyer k: kursiyer)
		{
			System.out.println(k.getKursiyerId()+" "+k.getKursiyerAdSoyad()+" "+k.getKursiyerYas());
		}
	}
	public static void kursiyerAyrintiliListele(ArrayList<Kursiyer> kursiyer)
	{
		if(kursiyer.isEmpty())
		{
			System.out.println("Kursiyer listesi bos.");
			return;
		}
		for(Kursiyer k: kursiyer)
		{
			System.out.println(k.getKursiyerId()+" "+k.getKursiyerAdSoyad()+" "+k.getKursiyerYas());
			for(Kurs d: k.alinanKurslar)
			{
				System.out.println("\t"+d.getKursId()+" "+d.getKursAd());
			}
		}
	}
	public static void kursiyerOdeyecegiTutarHesapla(ArrayList<Kursiyer> kursiyer,int kursiyerId)
	{
		int kontrol=0;
		for(Kursiyer k: kursiyer)
		{
			if(k.getKursiyerId() == kursiyerId)
			{
				System.out.println("-> Odenecek tutar:"+k.BorcHesapla());
				kontrol=1;
			}
		}
		if(kontrol == 0)
			System.out.println("Aranan Id'de kursiyer yoktur.");
	}
}
//Sude Özsoy 22100011074
package Odev2;

public interface Hesaplama {
	public abstract double BorcHesapla();
}*/
