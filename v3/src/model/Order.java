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




	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int ID_Order;

	@Temporal(TemporalType.DATE)
	private Date date_of_settlement;

	@Temporal(TemporalType.DATE)
	private Date date_of_unsettlement;
		
	@OneToOne
	@JoinColumn(name="Room_number")
	private Room room;

	public Order() {
	}

	
	public Date getDate_of_settlement() {
		return this.date_of_settlement;
	}

	public void setDate_of_settlement(Date date_of_settlement) {
		this.date_of_settlement = date_of_settlement;
	}

	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Override
	public String[] getTableHeaders() {
		return new String[]{"Id","Room","Date_of_settlement","Date_of_unsettlement" };
	}

	@Override
	public Object[] getTableRowData() {
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String s1 = myFormat.format(date_of_settlement);
		String s2 = myFormat.format(date_of_unsettlement);
		if(this.room==null)System.out.println("Null room");
		return new Object[]{ID_Order,this.room.getNumber(),s1,s2};

	}

	@Override
	public int getId() {
		return Integer.valueOf(ID_Order);
	}

	@Override
	public void updateWith(Object mask) {
		Order obj = (Order) mask;
		date_of_settlement = obj.getDate_of_settlement();
		date_of_unsettlement = obj.getDate_of_unsettlement();
		room = obj.getRoom();

		
	}

	@Override
	public void setObjectId(int ID) {
		this.ID_Order=ID;
		
	}

	@Override
	public int getObjectId() {
		return ID_Order;
	}

	@Override
	public String getClassName() {
		return "Order";
	}


	public int getID_Order() {
		return ID_Order;
	}


	public Date getDate_of_unsettlement() {
		return date_of_unsettlement;
	}


	public void setID_Order(int iD_Order) {
		ID_Order = iD_Order;
	}


	public void setDate_of_unsettlement(Date date_of_unsettlement) {
		this.date_of_unsettlement = date_of_unsettlement;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID_Order;
		result = prime * result + ((date_of_settlement == null) ? 0 : date_of_settlement.hashCode());
		result = prime * result + ((date_of_unsettlement == null) ? 0 : date_of_unsettlement.hashCode());
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
		if (ID_Order != other.ID_Order)
			return false;
		if (date_of_settlement == null) {
			if (other.date_of_settlement != null)
				return false;
		} else if (!date_of_settlement.equals(other.date_of_settlement))
			return false;
		if (date_of_unsettlement == null) {
			if (other.date_of_unsettlement != null)
				return false;
		} else if (!date_of_unsettlement.equals(other.date_of_unsettlement))
			return false;
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Order [ID_Order=" + ID_Order + ", date_of_settlement=" + date_of_settlement + ", date_of_unsettlement="
				+ date_of_unsettlement + ", room=" + room + "]";
	}


}