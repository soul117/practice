package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the orders database table.
 * 
 */
@Entity
@Table(name="orders")
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o ORDER by o.date_of_settlement")
public class Order implements Serializable, IModel, IdbModel {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date_of_settlement == null) ? 0 : date_of_settlement.hashCode());
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		result = prime * result + ((room == null) ? 0 : room.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (date_of_settlement == null) {
			if (other.date_of_settlement != null)
				return false;
		} else if (!date_of_settlement.equals(other.date_of_settlement))
			return false;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;
		return true;
	}

	private static final long serialVersionUID = 1L;

	@Id
	private int orderID;

	@Temporal(TemporalType.DATE)
	private Date date_of_settlement;

	@Lob
	private String person;
	
	@ManyToOne
	@JoinColumn(name="ROOM")
	private Room room;

	public Order() {
	}

	public int getOrderID() {
		return this.orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public Date getDate_of_settlement() {
		return this.date_of_settlement;
	}

	public void setDate_of_settlement(Date date_of_settlement) {
		this.date_of_settlement = date_of_settlement;
	}

	public String getPerson() {
		return this.person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Override
	public String[] getTableHeaders() {
		return new String[]{"Id","Room","FIO","Date_of_settlement" };
	}

	@Override
	public Object[] getTableRowData() {
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		String s = myFormat.format(date_of_settlement);
		return new Object[]{orderID,this.room,person,s};

	}

	@Override
	public int getId() {
		return Integer.valueOf(orderID);
	}

	@Override
	public void updateWith(Object mask) {
		Order obj = (Order) mask;
		person = obj.getPerson();
		date_of_settlement = obj.getDate_of_settlement();
		room = obj.getRoom();

		
	}

	@Override
	public void setObjectId(int ID) {
		this.orderID=ID;
		
	}

	@Override
	public int getObjectId() {
		return orderID;
	}

	@Override
	public String getClassName() {
		return "Order";
	}

}