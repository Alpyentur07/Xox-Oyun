
import java.util.Scanner;

 class oyun {

    private yarısmacılar oyuncu1, oyuncu2;
    private oyuntahtası tahta;
    int sayacCizdir=0;

    public static void main(String[] args) {
        oyun t = new oyun();
        t.startGame();


    }
    public void startGame(){
        Scanner s = new Scanner(System.in);

        oyuncu1 = takePlayerInput(1);
        oyuncu2 = takePlayerInput(2);
        while(oyuncu1.getSembol() == oyuncu2.getSembol()){
            System.out.println("Bu Sembol Zaten Alinmış Başka Bir Sembol Seçiniz.");
            char symbol = s.next().charAt(0);
            oyuncu2.setSembol(symbol);
        }
        // Oyun zemini Olusturuldu
        tahta = new oyuntahtası(oyuncu1.getSembol(), oyuncu2.getSembol());
        // Oyunu Yonetme Kısmı
        boolean oyuncu1sıra = true;
        int status = oyuntahtası.TAMAMLANMAMIŞ;
        while(status == oyuntahtası.TAMAMLANMAMIŞ || status == oyuntahtası.GECERSİZ){
            if(oyuncu1sıra){
                System.out.println("Oyuncu 1 - " + oyuncu1.getisim() + "'in sirasi");
                int x=0,y=0;
                System.out.println("Hangi kutuya deger gireceksiniz");

                if(sayacCizdir==0)
                {
                    cizdir();
                    sayacCizdir++;
                }


                int kutusayi=s.nextInt();
                switch(kutusayi)
                {
                    case 1:
                        x=0;
                        y=0;
                        break;
                    case 2:
                        x=0;
                        y=1;
                        break;
                    case 3:
                        x=0;
                        y=2;
                        break;
                    case 4:
                        x=1;
                        y=0;
                        break;
                    case 5:
                        x=1;
                        y=1;
                        break;
                    case 6:
                        x=1;
                        y=2;
                        break;
                    case 7:
                        x=2;
                        y=0;
                        break;
                    case 8:
                        x=2;
                        y=1;
                        break;
                    case 9:
                        x=2;
                        y=2;
                        break;
                }
                status =  tahta.move(oyuncu1.getSembol(), x, y);
                if(status != oyuntahtası.GECERSİZ){
                    oyuncu1sıra = false;
                    tahta.print();
                }else{
                    System.out.println("Hatalı Giriş Tekrar Deneyiniz.");
                }
            }else{
                System.out.println("Oyuncu 2 - " + oyuncu2.getisim() + "'nin sirasi");
                int x=0,y=0;
                System.out.println("Hangi kutuya değer gireceksiniz");

                int kutusayi=s.nextInt();

                switch(kutusayi)
                {
                    case 1:
                        x=0;
                        y=0;
                        break;
                    case 2:
                        x=0;
                        y=1;
                        break;
                    case 3:
                        x=0;
                        y=2;
                        break;
                    case 4:
                        x=1;
                        y=0;
                        break;
                    case 5:
                        x=1;
                        y=1;
                        break;
                    case 6:
                        x=1;
                        y=2;
                        break;
                    case 7:
                        x=2;
                        y=0;
                        break;
                    case 8:
                        x=2;
                        y=1;
                        break;
                    case 9:
                        x=2;
                        y=2;
                        break;
                }


                status =  tahta.move(oyuncu2.getSembol(), x, y);
                if(status != oyuntahtası.GECERSİZ){
                    oyuncu1sıra = true;
                    tahta.print();
                }else{
                    System.out.println("Hatalı Giriş Lütfen Tekrar Deneyiniz.");
                }
            }
        }

        if(status == oyuntahtası.Oyuncu_1_Kazandı){
            System.out.println(" 1 - " + oyuncu1.getisim() +" Kazandi!");
        }else if(status == oyuntahtası.Oyuncu_2_Kazandı){
            System.out.println("Oyuncu 2 - " + oyuncu2.getisim() +" Kazandi!");
        }else{
            System.out.println("Berabere!");
        }
    }

    public void cizdir()
    {
        int sayac=0;
        System.out.println("---------------");
        for(int i =0; i < 3; i++)
        {
            if(i==0)
                sayac=0;
            else if(i==1)
                sayac=3;
            else if(i==2)
                sayac=6;
            for(int j =0; j < 3; j++)
            {
                sayac++;
                System.out.print("| " +sayac+  " |");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("---------------");
    }



    private yarısmacılar takePlayerInput(int num){
        Scanner s = new Scanner(System.in);
        System.out.println("Oyuncuyu Gir " + num + " ad: ");
        String isim = s.nextLine();
        System.out.println("Oyuncuyu Gir " + num + " sembol: ");
        char sembol = s.next().charAt(0);
        yarısmacılar p = new yarısmacılar(isim, sembol);
        return p;
    }
}
class oyuntahtası extends oyun {
    private char tahta[][];
    private int boardbuyukluk = 3;
    private char o1Sembol, o2Sembol;
    private int saymak;
    public final static int Oyuncu_1_Kazandı = 1;
    public final static int Oyuncu_2_Kazandı = 2;
    public final static int BERABERE = 3;
    public final static int TAMAMLANMAMIŞ = 4;
    public final static int GECERSİZ = 5;
    public oyuntahtası(char o1Sembol, char o2Sembol){
        tahta = new char[boardbuyukluk][boardbuyukluk];
        for(int i =0; i < boardbuyukluk; i++){
            for(int j =0; j < boardbuyukluk; j++){
                tahta[i][j] = ' ';
            }
        }
        this.o1Sembol = o1Sembol;
        this.o2Sembol = o2Sembol;
    }
    public int move(char sembol, int x, int y) {
        if(x < 0 || x >= boardbuyukluk || y < 0 || y >= boardbuyukluk || tahta[x][y] != ' '){
            return GECERSİZ;
        }
        tahta[x][y] = sembol;
        saymak++;

        if(tahta[x][0] == tahta[x][1] && tahta[x][0] == tahta[x][2]){
            return sembol == o1Sembol ? Oyuncu_1_Kazandı : Oyuncu_2_Kazandı;
        }

        if(tahta[0][y] == tahta[1][y] && tahta[0][y] == tahta[2][y]){
            return sembol == o1Sembol ? Oyuncu_1_Kazandı : Oyuncu_2_Kazandı;
        }

        if(tahta[0][0] != ' ' && tahta[0][0] == tahta[1][1] && tahta[0][0] == tahta[2][2]){
            return sembol == o1Sembol ? Oyuncu_1_Kazandı : Oyuncu_2_Kazandı;
        }

        if(tahta[0][2] != ' ' && tahta[0][2] == tahta[1][1] && tahta[0][2] == tahta[2][0]){
            return sembol == o1Sembol ? Oyuncu_1_Kazandı : Oyuncu_2_Kazandı;
        }
        if(saymak == boardbuyukluk * boardbuyukluk){
            return BERABERE;
        }
        return  TAMAMLANMAMIŞ;
    }
    public void print() {
        System.out.println("---------------");
        for(int i =0; i < boardbuyukluk; i++){
            for(int j =0; j < boardbuyukluk; j++){
                System.out.print("| " + tahta[i][j] + " |");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("---------------");
    }
}
class yarısmacılar {
    private String isim;
    private char sembol;

    public yarısmacılar(String isim, char sembol){
        setisim(isim);
        setSembol(sembol);
    }

    public void setisim(String isim) {

        if(!isim.isEmpty()) {
            this.isim = isim;
        }
    }

    public String getisim() {
        return this.isim;
    }

    public void setSembol(char sembol) {
        if(sembol != '\0') {
            this.sembol = sembol;
        }
    }

    public char getSembol() {
        return this.sembol;
    }
}