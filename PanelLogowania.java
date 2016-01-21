import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PanelLogowania {
	private Connection conn = null;
	private ResultSet rs;
	private Statement stmt;
	private String shaslo;
	private int spoziom;

	public PanelLogowania() throws SQLException {
		zaloguj();
	}
	private void zaloguj() throws SQLException{
		while (true) {
			System.out.println("Chcesz sie zalogować?");
			System.out.println("1.Tak");
			System.out.println("2.Nie");
			Scanner sc = new Scanner(System.in);
			int cont = sc.nextInt();
			if (cont == 1) {
				try {
					boolean contrl = true;
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schornisko", "root", "dexter16");
					while (contrl) {
						System.out.println("Podaj login:");
						sc = new Scanner(System.in);
						String login = sc.nextLine();
						System.out.println("Podaj haslo:");
						sc = new Scanner(System.in);
						String haslo = sc.nextLine();

						stmt = conn.createStatement();
						rs = stmt.executeQuery(
								"SELECT haslo, poziomdostepu FROM pracownicy where login='" + login + "'");
						rs.next();
						spoziom = rs.getInt("poziomdostepu");
						shaslo = rs.getString("haslo");
						System.out.println(shaslo);
						if (haslo.equals(shaslo)) {
							switch (spoziom) {
							case 1:
								PanelKierowniczy pk = new PanelKierowniczy();
								contrl = false;
								break;
							case 2:
								PanelPracowniczy pr = new PanelPracowniczy();
								contrl = false;
								break;

							case 3:

								PanelWolontariusza pw = new PanelWolontariusza();
								contrl = false;
								break;

							}

						} else {
							System.out.println("Błędne dane logowania");
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				PanelNiezalogowanego pn=new PanelNiezalogowanego();
			}
		}
	}
}
