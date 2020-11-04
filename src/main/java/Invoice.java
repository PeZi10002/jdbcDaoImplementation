import java.sql.Date;

public class Invoice {
	private int id;
	private Date date;
	private String description;
	private int value;
	private boolean paid;

	public Invoice() {
	}

	public Invoice(Date date, String description, int value, boolean paid) {
		this.date = date;
		this.description = description;
		this.value = value;
		this.paid = paid;
	}

	public Invoice(int id, Date date, String description, int value, boolean paid) {
		this.id = id;
		this.date = date;
		this.description = description;
		this.value = value;
		this.paid = paid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}
}
