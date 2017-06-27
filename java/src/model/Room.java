package model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the rooms database table.
 * 
 */
@Entity
@Table(name="rooms")
@NamedQuery(name="Room.findAll", query="SELECT r FROM Room r")
public class Room implements Serializable, IModel, IdbModel {
	private static final long serialVersionUID = 1L;

	@Id
	private int ID_Room;
	
	@Lob
	private String count;

	@Lob
	private String inhabited;

	@Lob
	private String reserved;

	@Lob
	private String type;

	@Lob
	private String roomNumber;
	
	@OneToMany(mappedBy="room")
	private List<Order> orders;

	
	

	public Room() {
	}

	public String getCount() {
		return this.count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getInhabited() {
		return this.inhabited;
	}

	public void setInhabited(String inhabited) {
		this.inhabited = inhabited;
	}

	public String getNumber() {
		return this.roomNumber;
	}

	public void setNumber(String number) {
		this.roomNumber = number;
	}

	public String getReserved() {
		return this.reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setRoom(this);
		return order;
	}
	private List<Order> getOrders() {
		return this.orders;
	}
	
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}


	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setRoom(null);
		return order;
	}


	@Override
	public String[] getTableHeaders() {
		return new String[]{"ID","Number","Type","Size","Reserved","Inhabited"};
	}

	@Override
	public Object[] getTableRowData() {
		return new Object[]{ID_Room,roomNumber,type,
				count,reserved,inhabited};
	}

	@Override
	public int getId() {
		return ID_Room;
	}

	@Override
	public void updateWith(Object mask) {
			Room obj = (Room) mask;
			roomNumber = obj.getNumber();
			type = obj.getType();
			count = obj.getCount();
			reserved = obj.getReserved();
			inhabited = obj.getInhabited();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((count == null) ? 0 : count.hashCode());
		result = prime * result + ID_Room;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}



	@Override
	public void setObjectId(int parseInt) {
		this.ID_Room = parseInt;
		
	}

	@Override
	public int getObjectId() {
		return ID_Room;
	}

	@Override
	public String getClassName() {
		return "Room";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (count == null) {
			if (other.count != null)
				return false;
		} else if (!count.equals(other.count))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Room [ID_Room=" + ID_Room + ", count=" + count + ", inhabited=" + inhabited + ", reserved=" + reserved
				+ ", type=" + type + ", roomNumber=" + roomNumber + ", orders=" + orders + "]";
	}

	public int getID_Room() {
		return ID_Room;
	}

	public void setID_Room(int iD_Room) {
		ID_Room = iD_Room;
	}
	
	

}