package model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="clients")
@NamedQuery(name="Client.findAll", query="SELECT c FROM Client c ORDER by c.fio")
public class Client implements Serializable, IModel, IdbModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_client")
	private int ID_client;
	
	@Lob
	@Column(name="FIO")
	private String fio;
	
	@Lob
	@Column(name="Phone_number")
	private String phone;
	
	@OneToOne
	@JoinColumn(name="Order_ID")
	private Order order;

	@Override
	public void setObjectId(int ID) {
		ID_client = ID;
		
	}

	@Override
	public int getObjectId() {
		return ID_client;
	}

	@Override
	public String getClassName() {
		return "Client";
	}

	@Override
	public String[] getTableHeaders() {
		return new String[]{"Id","FIO","Phone_number","Order_ID" };
	}

	@Override
	public Object[] getTableRowData() {
		return new Object[]{ID_client,fio,phone,this.order};
	}

	@Override
	public int getId() {
		return ID_client;
	}

	@Override
	public void updateWith(Object mask) {
		Client obj = (Client) mask;
		fio = obj.getFio();
		phone = obj.getPhone();
		order = obj.getOrder();
		
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getID_client() {
		return ID_client;
	}

	public String getFio() {
		return fio;
	}

	public String getPhone() {
		return phone;
	}

	public void setID_client(int iD_client) {
		ID_client = iD_client;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
}
