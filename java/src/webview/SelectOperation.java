package webview;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SelectOperation
 */
@WebServlet("/SelectOperation")
public class SelectOperation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//public static int operation;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public SelectOperation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
    	String operation = request.getParameter("operation");
		HttpSession session = request.getSession();
		session.setAttribute("operation", operation);
		String formName;
		if (operation.equals("delete")) 
			formName ="getId.html";
		else
			formName = operation + (String) session.getAttribute("className") + ".html";	
		request.getRequestDispatcher(formName).forward(request, response);
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
