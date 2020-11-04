import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.Result;

public class InvoiceDaoImpl implements InvoiceDao {
	public InvoiceDaoImpl() {
	}

	public List<Invoice> getAllInvoices() {
		List<Invoice> invoiceList = new ArrayList<>();

		try (Connection con = connect()) {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Invoice");
			while (rs.next()) {
				Invoice invoice = getInvoiceFromRs(rs); // getInvoiceFromRs returned aktuelle Reihe als Invoice Objekt
				invoiceList.add(invoice);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return invoiceList;
	}

	@Override
	public void insertInvoice(Invoice invoice) {
		int paidYesNo;

		if (invoice.isPaid()) {
			paidYesNo = 1;
		} else {
			paidYesNo = 0;
		}

		try (Connection con = connect()) {
			String stmt = "INSERT INTO Invoice (date, description, value, paid) VALUES(?,?,?,?)";
			PreparedStatement prepStmt = con.prepareStatement(stmt);

			prepStmt.setDate(1, invoice.getDate());
			prepStmt.setString(2, invoice.getDescription());
			prepStmt.setInt(3, invoice.getValue());
			prepStmt.setInt(4, paidYesNo);

			prepStmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void updateInvoice(Invoice invoice) {
		int paidYesNo;

		if (invoice.isPaid()) {
			paidYesNo = 1;
		} else {
			paidYesNo = 0;
		}

		try (Connection con = connect()) {
			String stmt = "UPDATE Invoice SET date = ?, description = ?, value = ?, paid = ? WHERE id = ?";
			PreparedStatement prepStmt = con.prepareStatement(stmt);

			prepStmt.setDate(1, invoice.getDate());
			prepStmt.setString(2, invoice.getDescription());
			prepStmt.setInt(3, invoice.getValue());
			prepStmt.setInt(4, paidYesNo);
			prepStmt.setInt(5, invoice.getId());

			prepStmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void deleteInvoice(int id) {
		try (Connection con = connect()) {
			String stmt = "DELETE FROM Invoice WHERE id= ?";
			PreparedStatement prepStmt = con.prepareStatement(stmt);

			prepStmt.setInt(1, id);

			prepStmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Invoice Objekt aus Resultset zurückgeben Für spätere Verwendung in Schleife
	 * zur Rückgabe von der jeweiligen Reihe im Resultset
	 * 
	 * @param rs
	 * @return Invoice invoice Objekt
	 * @throws SQLException
	 */
	private Invoice getInvoiceFromRs(ResultSet rs) throws SQLException {
		boolean paidYesNo;

		paidYesNo = rs.getInt("paid") == 1; // wenn Column "paid" 1 ist, dann true, also paid

		Invoice invoice = new Invoice(); // Invoiceobjekt erstellen

		invoice.setId(rs.getInt("id"));
		invoice.setDate(rs.getDate("date"));
		invoice.setDescription(rs.getString("description"));
		invoice.setValue(rs.getInt("value"));
		invoice.setPaid(paidYesNo);

		return invoice;
	}

	/**
	 * Eine Java.SQL-Connection als Methode bereitstellen Ist so schön
	 * wiederverwendbar und muss nicht mehr von Hand getippt werden
	 * 
	 * @return Connection to mysql Port 3306, DB Invoice, User: root, kein pw
	 */
	private Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Invoice", "root", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return con;
	}
}
