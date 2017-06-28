package model;

import java.io.Serializable;
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
	@Column(name="ID_room")
	private int ID_Room;
	
	
	@Column(name="Number")
	private int roomNumber;
	
	@Lob
	private String type;
	
	@Lob
	@Column(name="Size")
	private String count;

	@Lob
	private String reserved;
	
	@Lob
	private String inhabited;
	
	@OneToOne(mappedBy = "room")
	private Order order;
	
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

	public int getNumber() {
		return this.roomNumber;
	}

	public void setNumber(int number) {
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
	
	@Override
	public String[] getTableHeaders() {
		return new String[]{"ID","Number","Type","Size","Reserved","Inhabited"};
	}

	@Override
	public Object[] getTableRowData() {
		return new Object[]{ID_Room,roomNumber,type,count,reserved,inhabited};
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
				+ ", type=" + type + ", roomNumber=" + roomNumber + "]";
	}

	public int getID_Room() {
		return ID_Room;
	}

	public void setID_Room(int iD_Room) {
		ID_Room = iD_Room;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	
	

}