import java.util.Random;
import java.util.Scanner;


public class mayinTarlasi {

    int satirSayisi,sutunSayisi,boyut;//boş oluşturuyoruz bunları da dizileri de constructor da alırız

    int[][] harita; //temiz tablo oluşturuldu dizi şeklinde değer atamadan.

    int[][] board; //kullanıcının oynadığı harita

    boolean oyun = true;//oyun durumu

    Random rand = new Random();//kullanıcının random sayı üretmesini sağlayan sınıf.

    Scanner input = new Scanner(System.in);

    //constructoru oluşturalım...sadece satır ve sütun sayısını alacaz dışardan şimdilik
    mayinTarlasi(int satirSayisi,int sutunSayisi){

        this.satirSayisi=satirSayisi;

        this.sutunSayisi=sutunSayisi;

        this.harita=new int[satirSayisi][sutunSayisi];//dizinin içi boştu atamasını burda yaptık
        //daha önce demiştik başka sınıfta değişken kısmında ne varsa onu const kısmında thisleriz diye
        //tanımladığımız dizilerde değişken kısmında ondan burda thisledik

        this.board=new int[satirSayisi][sutunSayisi];

        this.boyut=satirSayisi*sutunSayisi;//tablonun boyutunu bunda tutacağız

    }

    //şimdi artık geri dönüşsüz bir metot lazım sonuçta değer döndürmeyecek oyunun çalıştığı yer
    //dışardan veri almayacaksak metotlarda hatırla yukardaki değişkenleri kullanırdık
    public void calis(){

        int satir,sutun,basari=0;

        oyunaHazirlik();

        // artık kullanıcıdan değerler alacaz bakacaz orda mayın varmı yoksa etrafındakileri versin
        System.out.println("Oyun Başladı !");

        while(oyun==true) {

            ekranaBas(board);
            System.out.print("satır : ");
            satir=input.nextInt();
            System.out.print("sütun : ");
            sutun=input.nextInt();
            if(satir <=0 || satir>=satirSayisi ){
                System.out.println("geçersiz koordinat");
                continue;
            }
            if(sutun <=0 || sutun>=sutunSayisi ){
                System.out.println("geçersiz koordinat");
                continue;
            }
            if(harita[satir][sutun]!=-1){//girilen değerlerde mayın var mı yok mu bakılacak
                mayinKontrol(satir,sutun);
                basari++;
                if(basari==(boyut-(boyut/4))){
                    System.out.println("başardınız mayına basmadınız");
                    break;
                }
            }
            else{
                oyun=false;
                System.out.println("oyun bitti");
            }
        }
    }


    //OYUNDA TABLO VS MAYIN VS RASTGELE ŞEYLERİN OLDUĞU METOT
    //ARRAYİN 1/4 Ü KADAR MAYIN OLSUN
    //-1 mayın var anlamına geliyor oyunda.
    //-2 etrafında da orda da mayın yok anlamını taşıyor.
    //aşağıdaki metotta mayınları ekliyoruz ratgele.

    public void oyunaHazirlik(){
        int rastgeleSatir,rastgeleSutun,sayac=0;//rastgele oluşturacağım
            while(sayac != (boyut/4)){
                rastgeleSatir=rand.nextInt(satirSayisi);//satir sayım kadar rastgele bir sayi oluştur satır sayım kadar çünkü geçmesin sonra bunu rastgele satir sayısına ata
                rastgeleSutun=rand.nextInt(sutunSayisi);
                if(harita[rastgeleSatir][rastgeleSutun] != -1){
                    harita[rastgeleSatir][rastgeleSutun]=-1;
                    sayac++;
                }
            }
    }

    //şimdi bide bütün bunları ekrana yazdıran bir metot yazalım
    //dışardan 2 boyutlu bir dizi alacak
    //sonrasında bu diziyi 2 boyutlu dizileri ekrana basar gibi aynı mantıkta basacaz

    public void ekranaBas(int[][] dizi){
        for(int i=0;i<dizi.length;i++) {
            for(int j=0;j<dizi[0].length;j++){
                if(dizi[i][j]>=0)
                    System.out.print(" ");
                System.out.print(dizi[i][j]+" ");
            }
            System.out.println();
        }
    }

    public void mayinKontrol(int sat,int sut){
        //koordinat bakacaz nerelerde mayın var [3][5] ya sağı için sağdaki +1 mesela o da [sat],[sut+1]
        if(harita[sat][sut]==0){
            if((sut<sutunSayisi-1) && harita[sat][sut+1]==-1){
                board[sat][sut]++;
            }
            if((sat<satirSayisi-1) && harita[sat+1][sut]==-1){
                board[sat][sut]++;
            }
            if((sat>0) && harita[sat-1][sut]==-1){
                board[sat][sut]++;
            }
            if((sut >0) && harita[sat][sut-1]==-1){
                board[sat][sut]++;
            }
            if(board[sat][sut]==0){
                board[sat][sut]=-2;
            }
        }


    }


}
