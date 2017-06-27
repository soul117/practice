package webview;

import java.io.IOException;
import java.util.regex.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetRoomId
 */
@WebServlet("/GetRoomId")
public class GetRoomId extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public String roomId;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetRoomId() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String room = request.getParameter("RoomId");
		if(room!=null){
			Pattern pat=Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
			Matcher matcher=pat.matcher(room);
			while (matcher.find()) {
			    roomId=matcher.group();
			};
		request.setAttribute("Room",roomId );
		request.getRequestDispatcher("getRoomId.jsp").forward(request, response);}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
