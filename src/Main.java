import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input =new Scanner(System.in);//sonuçta burda da veri alacaz örneğin kaça kaç ?
        //yani kullanıcıya soracaz burda kaç satır kaç sütunluk tablo istiyorsun diye
        System.out.println("Mayın Tarlasına Hoşgeldiniz !");
        System.out.println("lütfen oynamak istediğiniz boyutları giriniz ... ");

        System.out.print("satır sayısı : ");
        int satir=input.nextInt();
        System.out.print("sütun sayısı : ");
        int sutun=input.nextInt();


        //artık değerleri aldık kullanıcıdan şimdi sınıfı çağırabiliriz
        mayinTarlasi mayin = new mayinTarlasi(satir,sutun);
        mayin.calis();
    }
}