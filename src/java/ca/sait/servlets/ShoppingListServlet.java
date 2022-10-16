
package ca.sait.servlets;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Barsha
 */
public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    String name=(String)request.getSession().getAttribute("name");
    String action=request.getParameter("action");
if(name!=null){
this.getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request,response);
}else{
   this.getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request,response);
}
if(action!=null && action.equals("logout")){
	request.getSession().invalidate();
	response.sendRedirect("register");
}
}
    
    
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    String action=request.getParameter("action");


if(action.equals("register")){

	//creates sesion
	String name=request.getParameter("name");
	if(name!=null){
	request.getSession().setAttribute("name",name);
        ArrayList<String> items=new ArrayList<String>();
	request.getSession().setAttribute("items",items);
	}

}else if(action.equals("add")){
	String item=request.getParameter("item");
	//add item
	ArrayList<String> items=(ArrayList<String>)request.getSession().getAttribute("items");
	items.add(item);
	request.getSession().setAttribute("items",items);

}else if(action.equals("delete")){
        ArrayList<String> items=(ArrayList<String>)request.getSession().getAttribute("items");    
        String itemValue=request.getParameter("item");
        
        //items.remove(itemValue);
        int indexToDelete=-1;
        for(int i=0;i<items.size();i++){
            if(items.get(i).equals(itemValue)){
                indexToDelete=i;
                break;
            }
        }
        if(indexToDelete!=-1){
            items.remove(indexToDelete);
        }
        request.getSession().setAttribute("items",items);
}
this.getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request,response);

    }
}
