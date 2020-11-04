import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDAODummyImpl implements InvoiceDao {
	private List<Invoice> invoiceList;

	public InvoiceDAODummyImpl() {
		this.invoiceList = new ArrayList<>();
		invoiceList.add(new Invoice(1, Date.valueOf("2001-03-12"), "Invoice 1", 399, true));
		invoiceList.add(new Invoice(2, Date.valueOf("2012-12-23"), "Invoice 2", 40, false));
		invoiceList.add(new Invoice(3, Date.valueOf("1976-09-14"), "Invoice 3", 1277, true));
		invoiceList.add(new Invoice(4, Date.valueOf("1883-02-02"), "Invoice 4", 2323, false));
		invoiceList.add(new Invoice(5, Date.valueOf("2019-03-11"), "Invoice 5", 63, true));
	}

	@Override
	public List<Invoice> getAllInvoices() {
		return this.invoiceList;
	}

	@Override
	public void insertInvoice(Invoice invoice) {
		int id = this.invoiceList.get(this.invoiceList.size() - 1).getId() + 1;
		invoice.setId(id);
		this.invoiceList.add(invoice);
	}

	@Override
	public void updateInvoice(Invoice invoice) {

		for (Invoice i : this.invoiceList) {
			if (i.getId() == invoice.getId()) {
				i.setDate(invoice.getDate());
				i.setDescription(invoice.getDescription());
				i.setValue(invoice.getValue());
				i.setPaid(invoice.isPaid());
			}
		}
	}

	@Override
	public void deleteInvoice(int id) {
		this.invoiceList.remove(id - 1);
	}
}
