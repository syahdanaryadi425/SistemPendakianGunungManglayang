import java.util.ArrayList;

public class Pendakian implements Pembayaran {
    String namaKetua;
    Jalur jalur;
    int totalBiaya;
    ArrayList<Anggota> daftarAnggota = new ArrayList<>();

    public Pendakian(String nk, Jalur j) {
        this.namaKetua = nk;
        this.jalur = j;
    }

    @Override
    public int hitungTotalBiaya() {
        return this.totalBiaya;
    }
}