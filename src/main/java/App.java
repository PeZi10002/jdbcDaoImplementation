import java.sql.Date;
import java.util.List;

public class App {
	public static void main(String[] args) {
		// Database Test

		InvoiceDao invoiceDao = new InvoiceDaoImpl();

		Invoice i1 = new Invoice(Date.valueOf("2001-03-15"), "DAO Insert", 499, false);

		invoiceDao.insertInvoice(i1);
		invoiceDao.insertInvoice(new Invoice(Date.valueOf("2009-05-15"), "Insert 2", 200, true));
		invoiceDao.insertInvoice(new Invoice(Date.valueOf("2012-09-09"), "Insert 3", 100, false));

		showList(invoiceDao.getAllInvoices());

		invoiceDao.updateInvoice(new Invoice(10, Date.valueOf("1900-04-02"), "Update", 300, true));

		showList(invoiceDao.getAllInvoices());

		invoiceDao.deleteInvoice(8);

		showList(invoiceDao.getAllInvoices());
	}

	// Dummy-Data Test
	/*
	 * InvoiceDAODummyImpl invoices = new InvoiceDAODummyImpl();
	 * 
	 * showList(invoices.getAllInvoices());
	 * 
	 * invoices.insertInvoice(new Invoice(Date.valueOf("1788-02-05"),
	 * "first insert", 344, false));
	 * 
	 * showList(invoices.getAllInvoices());
	 * 
	 * invoices.updateInvoice(new Invoice(3, Date.valueOf("1900-04-02"), "Update",
	 * 300, true));
	 * 
	 * showList(invoices.getAllInvoices());
	 * 
	 * invoices.deleteInvoice(2);
	 * 
	 * showList(invoices.getAllInvoices()); }
	 */

	public static void showList(List<Invoice> list) {
		for (Invoice i : list) {
			System.out.print(i.getId() + " ");
			System.out.print(i.getDate() + " ");
			System.out.print(i.getDescription() + " ");
			System.out.print(i.getValue() + " ");
			System.out.println(i.isPaid());
		}
		System.out.println();
	}
}
