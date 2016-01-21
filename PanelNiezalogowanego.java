import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PanelNiezalogowanego {
	private Scanner sc;
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private boolean con=true;
	public PanelNiezalogowanego() throws SQLException{
		while(con){
			int temp;
			System.out.println("Co chcesz zrobic");
			System.out.println("1.Obejrzyj zwierzaki");
			System.out.println("2.Wplać darowizne");
			System.out.println("3.wyraz chec adopcji");
			sc=new Scanner(System.in);
			temp=sc.nextInt();
			if(temp==1){
				objerzyjzwierzeta();
			}else if(temp==2){
			wplacdarowizne();
			}else{
			adoptuj();
			}
		}
	}
	private void objerzyjzwierzeta() throws SQLException{

		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schornisko", "root", "dexter16");
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT id, gatunek, rasa, uwagi FROM zwierzeta");
		while (rs.next()) {

			System.out.println(rs.getInt("id") + " " + rs.getString("gatunek") + " " + rs.getString("rasa") + " "
					+ rs.getString("uwagi"));
		}
		conn.close();
	}
	private void wplacdarowizne() throws SQLException{
		System.out.println("podaj Imie");
		sc=new Scanner(System.in);
		String imie=sc.next();
		System.out.println("Podaj nazwisko");
		sc=new Scanner(System.in);
		String nazwisko=sc.nextLine();
		System.out.println("Ile chcesz wplacic?");
		sc=new Scanner(System.in);
		Magazyn.wplacnaKonto(sc.nextFloat());
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schornisko", "root", "dexter16");
		stmt = conn.createStatement();
		stmt.executeUpdate("INSERT INTO darczyncy (imie, nazwisko, suma) VALUES ('"+imie+"', '"+nazwisko+"', '"+sc.nextFloat()+"')");
		conn.close();
	}
private void adoptuj(){
	System.out.println("podaj id zwierzaka ktorego chcesz adpotowac;");
	int id;
	sc=new Scanner(System.in);
	id=sc.nextInt();
	try {
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schornisko", "root", "dexter16");
		stmt = conn.createStatement();
		rs=stmt.executeQuery("Select uwagi from zwierzeta where id="+id);
		rs.next();
		String temp2=rs.getString("uwagi")+" //wyrazono chec adpocji";
		stmt.executeUpdate("UPDATE zwierzeta SET uwagi='" + temp2 + "' WHERE id=" + id);
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
