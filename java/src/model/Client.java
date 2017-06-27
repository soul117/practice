package model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="clients")
@NamedQuery(name="Client.findAll", query="SELECT c FROM Client c")
public class Client implements Serializable, IModel, IdbModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private int ID_client;
	
	@Lob
	private String fio;
	
	@Lob
	private String phone;
	
	@OneToOne
	@JoinColumn(name="Order_ID")
	private Order Order_ID;

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
		return new Object[]{ID_client,fio,phone,Order_ID};
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
		Order_ID = obj.getOrder_ID();
		
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

	public Order getOrder_ID() {
		return Order_ID;
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

	public void setOrder_ID(Order order_ID) {
		Order_ID = order_ID;
	}
	
	
}
