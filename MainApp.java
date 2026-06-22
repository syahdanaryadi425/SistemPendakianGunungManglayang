import java.util.*;

class Petugas extends User {
    public Petugas(String u, String p) { super(u, p); }
    @Override
    public boolean login(String u, String p) {
        return this.username.equals(u) && this.password.equals(p);
    }
}

public class MainApp {
    static ArrayList<Jalur> daftarJalur = new ArrayList<>();
    static ArrayList<Pendakian> dataManifes = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        daftarJalur.add(new Jalur("Batu Kuda", 15000, 150000, 50));
        daftarJalur.add(new Jalur("Barubereum", 20000, 200000, 40));

        while(true) {
            System.out.println("\n=== SISTEM PENDAKIAN ===");
            System.out.println("1. Daftar Rombongan\n2. Petugas (Login)\n3. Keluar");
            System.out.print("Pilih: ");
            int menu = sc.nextInt();
            if (menu == 1) menuKetua();
            else if (menu == 2) menuPetugas();
            else break;
        }
    }

    public static void menuKetua() {
        System.out.println("\n--- DAFTAR JALUR TERSEDIA ---");
        System.out.printf("%-3s | %-15s | %-6s | %-12s | %-12s\n", "No", "Nama Jalur", "Kuota", "Harga WNI", "Harga WNA");
        System.out.println("-------------------------------------------------------------");
        for (int i = 0; i < daftarJalur.size(); i++) {
            Jalur j = daftarJalur.get(i);
            System.out.printf("%-3d | %-15s | %-6d | Rp%-10d | Rp%-10d\n", (i + 1), j.namaJalur, j.kuota, j.hargaWNI, j.hargaWNA);
        }

        System.out.print("\nNama Ketua: "); String nk = sc.next();
        System.out.print("Alamat: "); String ak = sc.next();
        System.out.print("Kontak: "); String ck = sc.next();
        System.out.print("Status Ketua (1. WNI / 2. WNA): ");
        boolean wKetua = (sc.nextInt() == 1);

        System.out.print("Pilih nomor jalur: "); Jalur j = daftarJalur.get(sc.nextInt() - 1);
        System.out.print("Jumlah anggota tambahan: "); int tambahan = sc.nextInt();
        int total = 1 + tambahan;

        if (total <= j.kuota) {
            Pendakian p = new Pendakian(nk, j);
            int wni = wKetua ? 1 : 0;
            int wna = wKetua ? 0 : 1;
            p.daftarAnggota.add(new Anggota(nk, ak, ck, wKetua));

            for (int i = 0; i < tambahan; i++) {
                System.out.println("\n--- Data Anggota ke-" + (i + 1) + " ---");
                System.out.print("Nama: "); String n = sc.next();
                System.out.print("Alamat: "); String a = sc.next();
                System.out.print("Kontak: "); String c = sc.next();
                System.out.print("Status (1. WNI / 2. WNA): ");
                boolean w = (sc.nextInt() == 1);
                if (w) wni++; else wna++;
                p.daftarAnggota.add(new Anggota(n, a, c, w));
            }
            p.totalBiaya = (wni * j.hargaWNI) + (wna * j.hargaWNA);
            System.out.println("\nTotal Tagihan: Rp" + p.totalBiaya);
            System.out.print("Bayar: Rp");
            if (sc.nextInt() >= p.totalBiaya) {
                j.kuota -= total;
                dataManifes.add(p);
                System.out.println("Pendaftaran Berhasil!");
            } else System.out.println("Pembayaran gagal!");
        } else System.out.println("Kuota penuh!");
    }

    public static void menuPetugas() {
        Petugas admin = new Petugas("admin", "pendaki123");
        System.out.print("User: "); String u = sc.next();
        System.out.print("Pass: "); String p = sc.next();
        
        if (admin.login(u, p)) {
            boolean loop = true;
            while (loop) {
                System.out.println("\n--- MENU PETUGAS ---");
                System.out.println("1. Cek Kuota (Tabel)\n2. Lihat Manifes\n3. Hapus Data\n4. Logout");
                int pil = sc.nextInt();
                if (pil == 1) {
                    System.out.printf("\n%-15s | %-6s | %-10s\n", "Nama Jalur", "Sisa", "Status");
                    for (Jalur j : daftarJalur) 
                        System.out.printf("%-15s | %-6d | %-10s\n", j.namaJalur, j.kuota, (j.kuota < 10 ? "Kritis" : "Aman"));
                } 
                else if (pil == 2) {
                    if (dataManifes.isEmpty()) {
                        System.out.println("\n[Pemberitahuan]: Tidak ada daftar rombongan yang terdata.");
                    } else {
                        System.out.println("\n--- DAFTAR MANIFES PENDAKI ---");
                        for (Pendakian pnd : dataManifes) {
                            System.out.println("\nKetua: " + pnd.namaKetua + " | Jalur: " + pnd.jalur.namaJalur);
                            for (Anggota a : pnd.daftarAnggota) 
                                System.out.println("- " + a.nama + " (" + (a.isWNI ? "WNI" : "WNA") + ") | " + a.alamat + " | " + a.kontak);
                        }
                    }
                } 
                else if (pil == 3) {
                    hapusData();
                } else {
                    loop = false;
                }
            }
        } else {
            System.out.println("Login Gagal!");
        }
    }

    public static void hapusData() {
        System.out.println("\n--- HAPUS MANIFES PENDAKIAN ---");
        System.out.print("Masukkan nama ketua rombongan yang ingin dihapus: ");
        String cariNama = sc.next();
        
        boolean ditemukan = false;
        for (int i = 0; i < dataManifes.size(); i++) {
            if (dataManifes.get(i).namaKetua.equalsIgnoreCase(cariNama)) {
                Jalur j = dataManifes.get(i).jalur;
                j.kuota += dataManifes.get(i).daftarAnggota.size();
                dataManifes.remove(i);
                System.out.println("Data berhasil dihapus!");
                ditemukan = true;
                break;
            }
        }
        if (!ditemukan) System.out.println("Data tidak ditemukan.");
    }
}