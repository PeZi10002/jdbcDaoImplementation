import java.util.List;

public interface InvoiceDao {
	List<Invoice> getAllInvoices();

	void insertInvoice(Invoice invoice);

	void updateInvoice(Invoice invoice);

	void deleteInvoice(int id);
}
