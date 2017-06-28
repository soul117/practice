package webview;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.table.TableModel;

import controller.JpaController;
import model.*;

/**
 * Servlet implementation class OperateTable
 */
@WebServlet("/OperateTable")
public class OperateTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OperateTable() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */



    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	
    	HttpSession session = request.getSession();
    	String tableName = (String) session.getAttribute("className");
    	JpaController controller = (JpaController) session.getAttribute("controller");
    	String className = "model." + tableName;
    	String operation = (String) session.getAttribute("operation");
    	
    	if (operation.equals("delete")) {
    		int id = Integer.parseInt(request.getParameter("Id"));
    		controller.delete(id, tableName);
    	} 
    	
    	else {
    		IdbModel obj = null;
    		try {
    			obj = (IdbModel) Class.forName(className).newInstance();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		if (obj instanceof Room) {
    			
				String num = request.getParameter("roomNumber");
				((Room) obj).setNumber(Integer.parseInt(num));
				String type = request.getParameter("roomType");
				((Room) obj).setType(type);
				String size = request.getParameter("roomSize");
				((Room) obj).setCount(size);
				String reserved = request.getParameter("roomReserved");
				((Room) obj).setReserved(reserved);
				String inhabited = request.getParameter("roomInhabited");
				((Room) obj).setInhabited(inhabited);
				
				if(operation.equals("edit")){
					int roomId = Integer.parseInt(request.getParameter("roomID"));
					obj.setObjectId(roomId);
					controller.edit(roomId, obj);
				}
			
		} else if (obj instanceof Client) {
		
				String name = request.getParameter("clientName");
				((Client) obj).setFio(name);
				String phone = request.getParameter("clientPhone");
				((Client) obj).setPhone(phone);
				int Order_ID = Integer.parseInt(request.getParameter("client_order_id"));
				Order order_obj = (Order) findObject(Order_ID, controller,"Order");
				((Client) obj).setOrder(order_obj);
				
				if(operation.equals("edit")){
					int id = Integer.parseInt(request.getParameter("clientId"));
					obj.setObjectId(id);
					controller.edit(id, obj);
				}
		
		} else if (obj instanceof Order) {
			//Для створення нового запису потрібнa kimnata
			int roomId = Integer.parseInt(request.getParameter("orderRoom"));
			Room room = (Room) findObject(roomId, controller,"Room");
			((Order) obj).setRoom(room);
			SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
			String s1 = request.getParameter("orderDateofSettlement");
			String s2 = request.getParameter("orderDateofUnsettlement");
			Date dateSettlement = Date.valueOf(myFormat.format(s1));
			((Order) obj).setDate_of_settlement(dateSettlement);
			Date dateUnsettlement = Date.valueOf(s2);
			((Order) obj).setDate_of_unsettlement(dateUnsettlement);
			if (operation.equals("edit")) {
				//Для оновлення потрібен Id
				int id = Integer.parseInt(request.getParameter("orderId"));
				obj.setObjectId(id);
				controller.edit(id, obj);
			} 

		}
    		
    		if (operation.equals("add")) {
    			System.out.println("add object");
    			controller.add(obj);
    		}
    	}
    	
    	TableModel tableModel = controller.getModel(tableName);
    	session.setAttribute("tableModel", tableModel);
    	request.getRequestDispatcher("showTable.jsp").forward(request, response);
    }

    
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		doGet(request, response);
	}
	
	private IModel findObject(int id, JpaController controller, String className) {
		try {
			IModel obj = null;
			Class clazz = Class.forName("model."+className);
			for (Object x : controller.getObjectList(clazz)) {
				obj = (IModel) x;
				if (obj.getId() == id)
					return obj;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;

	}

}
