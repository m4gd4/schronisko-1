
public class Magazyn {
 private static float stanKonta=1000;
 private static float stanKarmy=500;
 private static float cena=2.5f;
 public static void kupkarme(float ile){
	 if(stanKonta>=ile*cena){
	 stanKonta=stanKonta-(ile*cena);
	 stanKarmy=stanKarmy+ile;
	 }
	 else{
		 System.out.println("brak funduszy na koncie");
	 }
 }
 public static float sprawdzKonto(){
	 return stanKonta;
 }
 public static void wplacnaKonto(float ile){
	 if(ile>0)
	 stanKonta=stanKonta+ile;
	 else{
		 System.out.println("wplata nie moze wynosić liczby mniejszej od 0");
	 }
 }
 public static float sprawdzKarme(){
	 return stanKarmy;
 }
}
