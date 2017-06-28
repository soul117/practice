package controller;

import java.util.List;


import javax.persistence.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


import model.*;

public class JpaController implements IController {
	
	EntityManagerFactory emf =
			Persistence.createEntityManagerFactory("laba_4_JPA");


	public List getObjectList(Class clazz) {
		EntityManager em = emf.createEntityManager();
		// Формуємо ім'я іменованого запиту для заданого класу
		String queryName = clazz.getSimpleName() + "." + "findAll";
		// Отримуємо перелік записів таблиці заданого класу
		List list = em.createNamedQuery(queryName).getResultList();
		em.close();
		if (list.size()==0) 
			System.out.println("No objects");
		return list;
	}

	
	public TableModel getModel(String className) {
		try {
			Class clazz = Class.forName("model." + className);
			// Отримуємо заголовок таблиці
			IModel obj = (IModel) clazz.newInstance();
			String[] header = obj.getTableHeaders();
			// Отримуємо список об'єктів
			List list = getObjectList(clazz);
			if (list == null || list.size() == 0)
				return new DefaultTableModel(null, header);
			// Створюємо масив потрібного розміру
			Object[][] array = new Object[list.size()][header.length];
			// Наповнюємо масив
			int i = 0;
			for (Object s : list)
				array[i++] = ((IModel) s).getTableRowData();
			// Повертаємо модель
			return new DefaultTableModel(array, header);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error model");
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public boolean exist(IModel obj) {
		Class clazz = obj.getClass();
		// Отримуємо перелік записів таблиці заданого класу
		List list = getObjectList(clazz);
		if (list != null && list.size() != 0)
			for (Object current : list)
				if (current.equals(obj))
					return true;	
		return false;
	}

	
	@Override
	public void add(Object obj) {
		//Class clazz = obj.getClass();
		if (exist((IModel) obj)){
			System.out.println("exist");
			return;}
		EntityManagerFactory emf = 
					Persistence.createEntityManagerFactory("laba_4_JPA");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(obj);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
}


	@SuppressWarnings("unchecked")
	@Override
	public void edit(int id, Object obj) {
		Class clazz = obj.getClass();
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			Object updObj = em.find(clazz, id);
			((IModel) updObj).updateWith(obj);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}


	@Override
	public void delete(int id, String className) {
		EntityManager em = emf.createEntityManager();
		try {
			Class clazz = Class.forName("model." + className);
			Object delObj = em.find(clazz, id);
			em.getTransaction().begin();
			em.remove(delObj);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}

		
	}

	@Override
	public TableModel getTableModelForFreeRooms() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("laba_4_JPA");
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("select r from Room r where r.inhabited = ?1");
		q.setParameter(1, "no");
		List<Room> list = q.getResultList();
		String[][] arr = new String[list.size()][6];
		int i = 0;
		for (Room r : list) {
			arr[i][0] = Integer.toString(r.getID_Room());
			arr[i][1] = Integer.toString(r.getNumber());
			arr[i][2] = r.getType();
			arr[i][3] = r.getCount();
			arr[i][4] = r.getReserved();
			arr[i++][5] = r.getInhabited();
		}
		DefaultTableModel model = new DefaultTableModel(arr,
	 new String[] {"Number(id)","Type","Size","Reserved","Inhabited"});
		return model;

	}

	@Override
	public TableModel getTableModelForLuxRooms() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("laba_4_JPA");
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("select r from Room r where r.type = ?1");
		q.setParameter(1, "Lux");
		List<Room> list = q.getResultList();
		String[][] arr = new String[list.size()][6];
		int i = 0;
		for (Room r : list) {
			arr[i][0] = Integer.toString(r.getID_Room());
			arr[i][1] = Integer.toString(r.getNumber());
			arr[i][2] = r.getType();
			arr[i][3] = r.getCount();
			arr[i][4] = r.getReserved();
			arr[i++][5] = r.getInhabited();
		}
		DefaultTableModel model = new DefaultTableModel(arr,
	 new String[] {"Number(id)","Type","Size","Reserved","Inhabited"});
		return model;
	}


	public void operateObject(IdbModel obj, int operation) {
		if(operation==1){ add( (Object)obj); }
		
		String className = obj.getClassName();
		int id = obj.getObjectId();
		
		if(operation==2)
		{
			edit(id,(Object)obj);
		}
		
		if (operation==4)
		{
			delete(id,className);
		}
	}

}
