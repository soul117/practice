package model;

public interface IModel {
	public String[] getTableHeaders();
	public Object[] getTableRowData();
	
	int getId();
	void updateWith(Object mask);

}
