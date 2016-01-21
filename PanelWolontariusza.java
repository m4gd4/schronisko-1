import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PanelWolontariusza {
	private Connection conn = null;
	private Statement stmt;
	private Scanner sc;
	private ResultSet rs;
	private boolean contr = true;

	public PanelWolontariusza() throws SQLException {
		while (contr) {
			System.out.println("Co chcesz zrobic?");
			System.out.println("1.Sprawdz karme");
			System.out.println("2.Sprawdz stan klatek");
			System.out.println("3. Dodaj karme do klatki");
			System.out.println("4. Posprzataj klatke");
			System.out.println("5.Wyjdź");
			sc = new Scanner(System.in);
			int test = sc.nextInt();
			switch (test) {
			case 1:
				sprawdzkarme();
				break;
			case 2:
				sprawdzstanklatek();
				break;
			case 3:
				dodajkarme();
				break;
			case 4:
				posprzatajklatke();
			case 5:
				contr = false;
			}
		}
	}

	private void sprawdzkarme() {

		System.out.println(Magazyn.sprawdzKarme() + " kg");
	}

	private void sprawdzstanklatek() throws SQLException {

		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schornisko", "root", "dexter16");
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT id, statusczystosci, statuskarmy FROM klatka");
		while (rs.next()) {
			System.out
					.println(rs.getInt("id") + " " + rs.getString("statusczystosci") + " " + rs.getInt("statuskarmy"));
		}
		conn.close();
	}

	private void dodajkarme() throws SQLException {

		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schornisko", "root", "dexter16");
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT id, statusczystosci, statuskarmy FROM klatka");
		while (rs.next()) {
			System.out
					.println(rs.getInt("id") + " " + rs.getString("statusczystosci") + " " + rs.getInt("statuskarmy"));
		}
		conn.close();
	}

	private void posprzatajklatke() throws SQLException {
		int id;
		System.out.println("Którą klatke posprzatales?");
		sc = new Scanner(System.in);
		id = sc.nextInt();
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schornisko", "root", "dexter16");
		stmt = conn.createStatement();
		stmt.executeUpdate("UPDATE klatka SET statusczystosci='posprzatana' WHERE id='" + id + "'");
	}
}
