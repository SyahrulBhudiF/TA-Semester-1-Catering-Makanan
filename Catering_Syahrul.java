import java.util.Scanner;
import java.util.Random; // merandom angka
import java.time.LocalDate; // tanggal otomatis
import java.time.format.DateTimeFormatter; // formater tanggal

public class Catering_Syahrul {
    static LocalDate tanggal = LocalDate.now();
    static Random random = new Random();
    static Scanner sc = new Scanner(System.in);
    static boolean isContinue;
    static char check, check2; // variabel bantuan
    static int id[] = { 0, 0, 0 };
    static int tempVar;
    static int total[] = new int[3]; // total harga pembayaran
    static int cek, cek4 = 0, cek2 = 0; // variabel bantuan
    static int[][] pilihan = new int[3][3]; // baris 0 orang pertama
    static int[][] porsi = new int[3][3]; // baris 0 orang pertama
    static int totalPorsi[] = new int[3]; // total porsi seluruh paket
    static int[][] jarak = new int[2][3]; // jarak dalam km baris 0 , baris 1 harga ongkir
    static int a = 0, b = 0, c = 0; // variabel pengganti perulangan , digunakan mengisi indeks array
    static int pelunasan[] = new int[3]; // tempat pembayaran
    static int dp[] = new int[3]; // tempat penyimpanan pembayaran dp
    static int checking2[] = new int[3]; // digunakan untuk menghentikan pemilihan
    static String pembeli[] = { "null", "null", "null" };
    static String tempName;
    static String[][] noHpnAlmt = { { " ", " ", " " }, { " ", " ", " " } }; // no hp 0 alamat 1
    static String statusLunas[] = { "Belum bayar", "Belum bayar", "Belum bayar" };
    static String tanggalPesanan[] = { "Tidak ada", "Tidak ada", "Tidak ada" }; // tanggal hari H
    static String[][] tglLnsorDp = { { "Belum membayar", "Tidak Dp atau lunas" },
            { "Belum membayar", "Tidak Dp atau Lunas" },
            { "Belum membayar", "Tidak Dp atau Lunas" } }; // baris 1 orang 1 pertama & kolom 1 lunas & 2 dp
    static String[][] voucher = { { "PAKJARI", "BPKJARI", "JARIYANTO" }, { " ", " ", " " },
            { "Diskon 10%", "Gratis Ongkir", "Potongan Rp 250000" } }; // baris 0 nama voucher, baris 1 input data,
                                                                       // baris 2 efek
    static int totalS = 0;

    public static void main(String[] args) {
        String nama[][] = { { "Paket A", "Paket B", "Paket C" },
                { "Nasi Tumpeng", "Sate Kambing", "Nasi Goreng" },
                { "Gorengan", "Kerupuk Ikan", "Sambal" },
                { "Es Teh", "Es Dawet", "Kopi Hitam" },
                { "Nasi Rawon", "Soto Ayam", "Ayam Bakar" },
                { "Gorengan", "Kerupuk", "Sambal" },
                { "Es Teh", "Es Teler", "Es Dawet" },
                { "Nasi Goreng", "Bakso", "Mie Goreng" },
                { "Gorengan", "Kue", "Kerupuk" },
                { "Es Teh", "Es Buah", "Es cincau" } };
        int harga[] = { 350000, 400000, 300000 };
        int cek3 = 0;
        do { // perulangan menu utama
            isContinue = false;
            menuUtama();
            System.out.print("Pilih menu (1/2/3/4/5/6/7/8) : ");
            int pilih = sc.nextInt();
            switch (pilih) {
                case 1:
                    daftarIsi(nama, harga);
                    System.out.println("Anda juga bisa melihat isi dari setiap paket pada menu pencarian");
                    break;
                case 2:
                    System.out.println("-=-=Kode Voucher=-=-\n1. " + voucher[0][0] + " ( diskon 10% ) ");
                    System.out.printf("2. %s ( gratis ongkir )", voucher[0][1]);
                    System.out.printf("\n3. %s ( potongan Rp 250000 )\n", voucher[0][2]);
                    break;
                case 3:
                    if (cek2 < 3) {
                        menu(nama, harga);
                        cek2++;
                        a = cek2;
                    } else {
                        System.out.println("==Pembeli maksimal 3 orang==");
                    }
                    cek3 = 1;
                    break;
                case 4:
                    if (cek3 == 1) {
                        menuPembayaran(nama, harga);
                    } else {
                        System.out.println("==Silahkan memilih menu pemesanan sebelum melakukan transaksi==");
                    }
                    cek4 = 1;
                    break;
                case 5:
                    status();
                    break;
                case 6:
                    if (cek3 == 1) {
                        pencarian(nama, harga);
                    } else {
                        System.out.println("==Belum ada data yang tersimpan==");
                    }
                    break;
                case 7:
                    if (cek3 == 1) {
                        batal();
                    } else {
                        System.out.println("==Belum ada data yang tersimpan==");
                    }
                    break;
                case 8:
                    laporan(nama, harga);
                    break;
            }
            System.out.print("Kembali ke menu Utama (y/n) ? ");
            check = sc.next().charAt(0);
            if (check == 'y' || check == 'Y') {
                isContinue = true;
            }
        } while (isContinue);
        System.out.println("========Terimakasih========");
        System.out.println("___________________________");
    }

    // fungsi tampilan awal
    static void menuUtama() {
        System.out.println("==========================================");
        System.out.println("||\t     Catering Bapak Jari\t||");
        System.out.println("|| Ds Tanah landean, Kec Balongpanggang ||");
        System.out.println("||\t\t Kab Gresik \t\t||");
        System.out.println("==========================================");
        System.out.println("||\t\t    Menu\t\t||");
        System.out.println("||\t     1. Daftar Paket\t\t||");
        System.out.println("||\t     2. Voucher\t\t\t||");
        System.out.println("||\t     3. Pemesanan\t\t||");
        System.out.println("||\t     4. Transaksi\t\t||");
        System.out.println("||\t     5. Status Pembayaran\t||");
        System.out.println("||\t     6. Pencarian\t\t||");
        System.out.println("||\t     7. Pembatalan\t\t||");
        System.out.println("||\t     8. Laporan \t\t||");
        System.out.println("==========================================");
        System.out.println("-==-==-==-==-==-==-==-==-==-==-==-==-==-==");
    }

    // fungsi daftar paket semua
    static void daftarIsi(String nama[][], int price[]) {
        for (int i = 1; i < nama.length; i++) {
            if (i == 1) {
                System.out.println("\n" + nama[0][0] + " per 5 porsi Rp " + price[0] + "\n===Makanan===");
            } else if (i == 4) {
                System.out.println("\n" + nama[0][1] + " per 5 porsi Rp " + price[1] + "\n===Makanan===");
            } else if (i == 7) {
                System.out.println("\n" + nama[0][2] + " per 5 porsi Rp " + price[2] + "\n===Makanan===");
            } else if (i % 3 == 0) {
                System.out.println("===Minuman===");
            } else if (i == 2 || i == 5 || i == 8) {
                System.out.println("===Pelengkap===");
            }
            for (int j = 0; j < nama[0].length; j++) {
                System.out.printf("%s. %s\n", (j + 1), nama[i][j]);
            }
        }
    }

    // fungsi pemesanan paket
    static void menu(String name[][], int harga[]) {
        isContinue = false;
        System.out.println("\n==================================================");
        System.out.println("||\t\tCatering Bapak Jari\t\t||");
        System.out.println("__________________________________________________");
        System.out.println("Paket tersedia : ");
        for (int j = 0; j < name[0].length; j++) {
            System.out.printf((j + 1) + ". %s\n", name[0][j]);
        }
        System.out.println("=======Masukkan Identitas========");
        id[a] = random.nextInt(20) + 200;
        System.out.println("Id Pembeli\t: " + id[a]);
        System.out.print("Nama\t\t: ");
        sc.nextLine();
        pembeli[a] = sc.nextLine();
        System.out.print("No Hp\t\t: ");
        noHpnAlmt[0][a] = sc.nextLine();
        System.out.print("Alamat\t\t: ");
        noHpnAlmt[1][a] = sc.nextLine();
        System.out.print("Jarak(km)\t: ");
        jarak[0][a] = sc.nextInt();
        do {
            do {
                do {
                    System.out.print("Pilih paket\t: ");
                    cek = sc.nextInt();
                    pilihan[a][b] = cek;
                    if (cek > 3) {
                        System.out.println("Paket tidak tersedia");
                    }
                } while (cek > 3);
                System.out.println("===============================");
                daftarMenu(name, harga, cek);
                System.out.print("-=-=-=-=-=-\nMasukkan Jumlah porsi: ");
                porsi[a][b] = sc.nextInt();
                if (pilihan[a][b] == 1) {
                    totalPorsi[0] += porsi[a][b];
                } else if (pilihan[a][b] == 2) {
                    totalPorsi[1] += porsi[a][b];
                } else if (pilihan[a][b] == 3) {
                    totalPorsi[2] += porsi[a][b];
                }
                System.out.print("Yakin dengan paket yang dipilih (y/n)? ");
                check = sc.next().charAt(0);
            } while (check == 'n' || check == 'N');
            b += 1;
            if (b > 2) {
                System.out.println("===Terimakasih telah memesan===");
                break;
            }
            System.out.println("---------------------------------------------");
            System.out.print("Memilih paket lain (y/n)? ");
            check = sc.next().charAt(0);
        } while (check == 'y' || check == 'Y');
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("Tanggal pengiriman (dd-mm-yyyy): ");
        sc.nextLine();
        tanggalPesanan[a] = sc.nextLine();
        System.out.print("Pakai Voucher (y/n)? ");
        check2 = sc.next().charAt(0);
        if (check2 == 'y' || check2 == 'Y') {
            System.out.println("Masukkan Voucher : ");
            sc.nextLine();
            voucher[1][a] = sc.nextLine();
        } else {
            cek = 1;
            voucher[1][a] = "-";
        }
        do {
            for (int i = 0; i < voucher[0].length; i++) {
                if (voucher[1][a].equalsIgnoreCase(voucher[0][i])) {
                    System.out.println("Voucher\t: " + voucher[2][i]);
                    cek = 1;
                    break;
                }
            }
            if (cek != 1) {
                System.out.println("-=Voucher Tidak Ada=-");
                voucher[1][a] = "-";
                System.out.print("Masukan Voucher ulang (y/n)? ");
                check = sc.next().charAt(0);
                if (check == 'y' || check == 'Y') {
                    isContinue = true;
                }
            }
        } while (isContinue);
        b = 0;
        a += 1;
        System.out.println("==============================");
    }

    // fungsi pemilihan paket
    static void daftarMenu(String name[][], int harga[], int cek) {
        switch (cek) {
            case 1:
                System.out.printf("%s \n===Makanan===\n", name[0][0]);
                for (int i = 0; i < name[0].length; i++) {
                    System.out.printf("%s. %s\n", (i + 1), name[1][i]);
                }
                System.out.println("\n===Pelengkap===");
                for (int i = 0; i < name[0].length; i++) {
                    System.out.printf("%s. %s\n", (i + 1), name[2][i]);
                }
                System.out.println("\n===Minuman===");
                for (int i = 0; i < name[0].length; i++) {
                    System.out.printf("%s. %s\n", (i + 1), name[3][i]);
                }
                System.out.println("==============\nHarga : " + harga[0] + " Per 5 porsi");
                break;
            case 2:
                System.out.printf("%s \n===Makanan===\n", name[0][1]);
                for (int i = 0; i < name[0].length; i++) {
                    System.out.printf("%s. %s\n", (i + 1), name[4][i]);
                }
                System.out.println("\n===Pelengkap===");
                for (int i = 0; i < name[0].length; i++) {
                    System.out.printf("%s. %s\n", (i + 1), name[5][i]);
                }
                System.out.println("\n===Minuman===");
                for (int i = 0; i < name[0].length; i++) {
                    System.out.printf("%s. %s\n", (i + 1), name[6][i]);
                }
                System.out.println("==============\nHarga : " + harga[1] + " Per 5 porsi");
                break;
            case 3:
                System.out.printf("%s \n===Makanan===\n", name[0][2]);
                for (int i = 0; i < name[0].length; i++) {
                    System.out.printf("%s. %s\n", (i + 1), name[7][i]);
                }
                System.out.println("\n===Pelengkap===");
                for (int i = 0; i < name[0].length; i++) {
                    System.out.printf("%s. %s\n", (i + 1), name[8][i]);
                }
                System.out.println("\n===Minuman===");
                for (int i = 0; i < name[0].length; i++) {
                    System.out.printf("%s. %s\n", (i + 1), name[9][i]);
                }
                System.out.println("==============\nHarga : " + harga[2] + " Per 5 porsi");
                break;
            default:
                System.out.println("Mohon maaf, Paket yang anda cari tidak tersedia");
                break;
        }
    }

    // fungsi pemilihan nama khusus untuk menu transaksi
    static void menuNama() {
        printVarName();
        c -= 1;
        if ((checking2[c] == 1 || checking2[c] == 2)) { // ketika salah pencet tapi sudah
            // di bayar atau di dp
            System.out.println("==Pembayaran Telah Lunas atau Sudah di DP==");
            cek = 0;
            c = 0;
        }
    }

    // fungsi pemilihan yang bisa digunakan berkali kali
    static void printVarName() {
        int index = 0;
        while (index < pembeli.length) {
            if (pembeli[index].equalsIgnoreCase("null")) {
                System.out.println();
            } else {
                System.out.println((index + 1) + ". " + pembeli[index]);
            }
            index++;
        }
        do {
            System.out.print("Pilih nomor : ");
            c = sc.nextInt();
            if (c > 3) { // memilih diluar jangkauan
                System.out.println("Mohon pilih nomor yang benar");
            }
        } while (c > index);
    }

    // fungsi menu pembayaran
    static void menuPembayaran(String asma[][], int rego[]) {
        c = 0;
        System.out.println("Metode Pembayaran :\n1. Lunas\n2. Dp(50%)\n3. Kembali\n");
        System.out.print("Pilih menu : ");
        cek = sc.nextInt();
        if (cek != 3 && cek < 3) {
            menuNama();
        }
        switch (cek) {
            case 1:
                hargaT(asma, rego, c);
                System.out.println("Jumlah yang harus dibayar : " + total[c]);
                System.out.print("Masukkan jumlah yang dibayar : ");
                pelunasan[c] = sc.nextInt();
                System.out.println("Tanggal Pelunasan : " + tanggal.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                tglLnsorDp[c][0] = tanggal.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                statusLunas[c] = "Lunas";
                checking2[c] = 1;
                totalS += pelunasan[c];
                System.out.println("===Terimakasih telah memesan===");
                break;
            case 2:
                hargaT(asma, rego, c);
                System.out.println("Jumlah yang harus dibayar : " + total[c]);
                System.out.println("Dp 50% : " + (total[c] - ((double) total[c] / 2)));
                System.out.print("Masukkan jumlah yang dibayar : ");
                pelunasan[c] = sc.nextInt();
                totalS += pelunasan[c];
                System.out.println("Tanggal DP\t : " + tanggal.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                tglLnsorDp[c][1] = tanggal.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                dp[c] = total[c] - pelunasan[c];
                checking2[c] = 2;
                statusLunas[c] = "Dp";
                System.out.println(
                        "===Terimakasih telah memesan dan silahkan melunasi pada menu status pembayaran===");
                break;
        }
    }

    // fungsi penghitungan harga total dan penampilan harga
    static void hargaT(String nam[][], int price[], int c) {
        int totall = 0;
        total[c] = 0; // bernilai 0 supaya ketika terjadi perulangan nilai tidak bertambah
        System.out.println("============================\nId Pembeli\t: " + id[c]);
        System.out.println("Nama\t\t: " + pembeli[c]);
        System.out.println("Alamat\t\t: " + noHpnAlmt[0][c]);
        System.out.println("No Hp\t\t: " + noHpnAlmt[1][c]);

        for (int i = 0; i < pembeli.length; i++) {
            if (pilihan[c][i] == 1) {
                System.out.println("Nama paket\t: " + nam[0][0]);
                System.out.println("Total porsi\t: " + porsi[c][i]);
                totall = price[0] * (porsi[c][i] / 5);
                System.out.println("Total\t\t: Rp " + totall);
                total[c] += totall;
            } else if (pilihan[c][i] == 2) {
                System.out.println("Nama paket\t: " + nam[0][1]);
                System.out.println("Total porsi\t: " + porsi[c][i]);
                totall = price[1] * (porsi[c][i] / 5);
                System.out.println("Total\t\t: Rp " + totall);
                total[c] += totall;
            } else if (pilihan[c][i] == 3) {
                System.out.println("Nama paket\t: " + nam[0][2]);
                System.out.println("Total porsi\t: " + porsi[c][i]);
                totall = price[2] * (porsi[c][i] / 5);
                System.out.println("Total\t\t: Rp " + totall);
                total[c] += totall;
            }
        }
        ongkirNvoucher(c);
        System.out.println("Ongkir " + jarak[0][c] + " Km\t: " + jarak[1][c]);
        System.out.println("=-=-=-===============-=-=-=");
        System.out.println("Total semua\t: Rp " + total[c]);
        System.out.println("Tanggal kirim\t: " + tanggalPesanan[c]);
    }

    // fungsi ongkir
    static int ongkirNvoucher(int c) {
        jarak[1][c] = jarak[0][c] * 3000;
        System.out.println("Kode Voucher\t: " + voucher[1][c]);
        if (voucher[1][c].equalsIgnoreCase(voucher[0][0])) {
            total[c] = total[c] - (total[c] / 10);
            System.out.println("Voucher\t\t: " + voucher[2][0]);
        } else if (voucher[1][c].equalsIgnoreCase(voucher[0][1])) {
            jarak[1][c] = 0;
            System.out.println("Voucher\t\t: " + voucher[2][1]);
        } else if (voucher[1][c].equalsIgnoreCase(voucher[0][2])) {
            total[c] -= 250000;
            System.out.println("Voucher\t\t: " + voucher[2][2]);
        }
        total[c] += jarak[1][c];
        return total[c];
    }

    // fungsi status pembayaran
    static void status() {
        if (cek4 != 1) {
            System.out.println("==Silahkan menuju menu Transaksi sebelum mengecek status pembayaran==");
        } else {
            System.out.println("=====Nama=====");
            printVarName();
            cek = c;
            cek -= 1;
            if (statusLunas[cek] == "Lunas") {
                System.out.println("Pembayaran sudah LUNAS");
            } else if (statusLunas[cek] == "Dp") {
                System.out.println("Pembayaran anda belum Lunas, silahkan melanjutkan pembayaran\n===================");
                System.out.println("Total harga : Rp " + total[cek]);
                System.out.println("Dp 50% yang sudah di bayar : Rp " + pelunasan[cek]);
                System.out.println("Sisa yang harus dibayar : Rp " + (total[cek] - pelunasan[cek]));
                System.out.print("Masukkan jumlah yang dibayar : Rp ");
                pelunasan[cek] = sc.nextInt();
                statusLunas[cek] = "Lunas";
                tglLnsorDp[cek][0] = tanggal.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                System.out.println("Tanggal Pelunasan : " + tglLnsorDp[cek][0]);
                totalS += pelunasan[cek];
                System.out.println("===Terimakasih telah memakai jasa kami, LUNAS===");
            } else {
                System.out.println("Belum membayar");
            }
        }
    }

    // fungsi tambahan menu pencarian
    static void finalPencarian(String nem[][], int hrg[], int c) {
        c -= 1;
        hargaT(nem, hrg, c);
        System.out.println("_______________________________\nTotal yang sudah dibayar : " + pelunasan[c]);
        System.out.println("Status pembayaran\t : " + statusLunas[c]);
        System.out
                .println("-=Tanggal Transaksi-=\nDp\t: " + tglLnsorDp[c][1] + "\nLunas\t: " + tglLnsorDp[c][0]);
    }

    // fungsi menu pencarian
    static void pencarian(String jeneng[][], int hrg[]) {
        do {
            System.out.print(
                    "_____________________\nMenu pencarian :\n1. Id\n2. Nama\n3. Tanggal\n4. Total Paket\n5. Isi Paket\n6. Kembali\n==============\nPilih : ");
            cek = sc.nextInt();
            cek -= 1;
            switch (cek) {
                case 0:
                    System.out.println("-=-=-=-=-=-=-=-\nMasukkan ID : ");
                    tempVar = sc.nextInt();
                    for (int i = 0; i < id.length; i++) {
                        if (tempVar == id[i]) {
                            cek = 1;
                            c = i + 1;
                            finalPencarian(jeneng, hrg, c);
                            break;
                        }
                    }
                    if (cek != 1) {
                        System.out.println("-=ID Tidak ditemukan=-");
                    }
                    cek = 0;
                    break;
                case 1:
                    System.out.println("-=-=-=-=-=-=-=-\nMasukkan Nama : ");
                    sc.nextLine();
                    tempName = sc.nextLine();
                    for (int i = 0; i < pembeli.length; i++) {
                        if (tempName.equalsIgnoreCase(pembeli[i])) {
                            cek = 1;
                            c = i + 1;
                            finalPencarian(jeneng, hrg, c);
                            break;
                        }
                    }
                    if (cek != 1) {
                        System.out.println("-=Nama Tidak ditemukan=-");
                    }
                    cek = 0;
                    break;
                case 2:
                    cek = 0;
                    System.out.println("-=-=-=-=-=-=-=-\nMasukkan Tanggal : ");
                    sc.nextLine();
                    tempName = sc.nextLine();
                    for (int i = 0; i < id.length; i++) {
                        if (tempName.equalsIgnoreCase(tanggalPesanan[i])) {
                            cek = 1;
                            c = i + 1;
                            finalPencarian(jeneng, hrg, c);
                            break;
                        }
                    }
                    if (cek != 1) {
                        System.out.println("-=Tanggal Tidak ditemukan=-");
                    }
                    cek = 0;
                    break;
                case 3:
                    do {
                        System.out.println("-=-Total Paket-=-");
                        for (int j = 0; j < jeneng[0].length; j++) {
                            System.out.printf((j + 1) + ". %s\n", jeneng[0][j]);
                        }
                        System.out.print("Pilih nomor : ");
                        c = sc.nextInt();
                        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
                        for (int i = 0; i < pilihan.length; i++) {
                            for (int j = 0; j < pilihan[0].length; j++) {
                                if (pilihan[i][j] == c) {
                                    System.out.println(jeneng[0][c - 1]);
                                    System.out.println("Nama\t: " + pembeli[i]);
                                    System.out.println("Porsi\t: " + porsi[i][j]);
                                }
                            }
                        }
                        System.out.println("Total Porsi : " + totalPorsi[c - 1]);
                        System.out.print("Lihat yang lain (y/n) ? ");
                        check = sc.next().charAt(0);
                    } while (check == 'y' || check == 'Y');
                    break;
                case 4:
                    do {
                        System.out.println("-=-Isi Paket-=-");
                        for (int j = 0; j < jeneng[0].length; j++) {
                            System.out.printf((j + 1) + ". %s\n", jeneng[0][j]);
                        }
                        System.out.print("Pilih nomor : ");
                        c = sc.nextInt();
                        daftarMenu(jeneng, hrg, c);
                        System.out.print("Lihat yang lain (y/n) ? ");
                        check = sc.next().charAt(0);
                    } while (check == 'y' || check == 'Y');
                    break;
                case 5:
                    break;
                default:
                    System.out.println("=-=Input Salah=-=");
                    break;
            }
            System.out.println();
            System.out.print("Cari lagi (y/n) ? ");
            check = sc.next().charAt(0);
            System.out.println("==============================");
        } while (check == 'y' || check == 'Y');
    }

    // fungsi laporan
    static void laporan(String nama[][], int price[]) {
        System.out
                .println("-=-=--==-=-=-=-=-=-=-=-==-=-\n||=========LAPORAN========||\n||________________________||");
        for (int i = 0; i < pembeli.length; i++) {
            if (pembeli[i].equalsIgnoreCase("null")) {
                System.out.println();
            } else {
                c = 0;
                c = i + 1;
                finalPencarian(nama, price, c);
                System.out.println();
                System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            }
        }
        System.out.println("==========================\nTotal Penjualan\t\t: Rp " + totalS);
        for (int i = 0; i < totalPorsi.length; i++) {
            System.out.println("Total porsi " + nama[0][i] + "\t: " + totalPorsi[i]);
        }
    }

    // fungsi batalkan pesanan
    static void batal() {
        printVarName();
        c -= 1;
        pembeli[c] = "null";
        tanggalPesanan[c] = null;
        statusLunas[c] = null;
        voucher[1][c] = null;
        tglLnsorDp[c][0] = null;
        tglLnsorDp[c][1] = null;
        noHpnAlmt[0][c] = null;
        noHpnAlmt[1][c] = null;
        total[c] = 0;
        dp[c] = 0;
        id[c] = 0;
        pelunasan[c] = 0;
        jarak[0][c] = 0;
        jarak[1][c] = 0;
        for (int i = 0; i < pilihan.length; i++) {
            if (pilihan[c][i] == 1) {
                totalPorsi[0] -= porsi[c][i];
            } else if (pilihan[c][i] == 2) {
                totalPorsi[1] -= porsi[c][i];
            } else if (pilihan[c][i] == 3) {
                totalPorsi[2] -= porsi[c][i];
            }
            pilihan[c][i] = 0;
            porsi[c][i] = 0;
        }
        a = c;
        cek2--;
        System.out.println("=Pesanan Telah Dibatalkan=");
    }
}
