package com.todzhang.todo.web;

import com.todzhang.todo.model.ToDoItem;
import com.todzhang.todo.repository.InMemoryToDoRepository;
import com.todzhang.todo.repository.ToDoRepository;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by todzhang on 2017/1/3.
 */
//public class ToDoServlet {
public class ToDoServlet extends HttpServlet {
    public static final String FIND_ALL_SERVLET_PATH="/all";
    public static final String INDEX_PAGE="/jsp/todo-list.jsp";

    private ToDoRepository toDoRepository=new InMemoryToDoRepository();

    /**
     * Receives standard HTTP requests from the public
     * <code>service</code> method and dispatches
     * them to the <code>do</code><i>XXX</i> methods defined in
     * this class. This method is an HTTP-specific version of the
     * {@link Servlet#service} method. There's no
     * need to override this method.
     *
     * @param req  the {@link HttpServletRequest} object that
     *             contains the request the client made of
     *             the servlet
     * @param resp the {@link HttpServletResponse} object that
     *             contains the response the servlet returns
     *             to the client
     * @throws IOException      if an input or output error occurs
     *                          while the servlet is handling the
     *                          HTTP request
     * @throws ServletException if the HTTP request
     *                          cannot be handled
     * @see Servlet#service
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath=req.getServletPath();
        String view=processRequest(servletPath,req);
        RequestDispatcher dispatcher=req.getRequestDispatcher(view);
        dispatcher.forward(req,resp);
    }

    private String processRequest(String requestPath,HttpServletRequest request){

        if(requestPath.equals(FIND_ALL_SERVLET_PATH)){
            List<ToDoItem> toDoItems=toDoRepository.findAll();
            request.setAttribute("toDoItems",toDoItems);
            request.setAttribute("stats",determineStatus(toDoItems));
            request.setAttribute("filter","all");
            return INDEX_PAGE;
        }
        return FIND_ALL_SERVLET_PATH;
    }


    private ToDoListStats determineStatus(List<ToDoItem> items){
        ToDoListStats status=new ToDoListStats();
        for (ToDoItem item:
             items) {
            if(item.isCompleted())
                status.addCompleted();
            else
                status.addActive();
        }
        return status;
    }


public class ToDoListStats{
    private int active;
    private int completed;
    private void addActive(){
        ++active;
    }

    private void addCompleted(){
        ++completed;
    }

    private int getActive(){
        return active;
    }

    private int getCompleted(){
        return completed;
    }

    private int getAll(){
        return active+completed;
    }
}

}