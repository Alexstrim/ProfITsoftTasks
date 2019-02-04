package ua.profitsoft.strymeneshenko.controllers.servlets;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import ua.profitsoft.strymeneshenko.data.Client;
import ua.profitsoft.strymeneshenko.data.Individual;
import ua.profitsoft.strymeneshenko.data.LegalEntity;
import ua.profitsoft.strymeneshenko.service.ClientService;
import ua.profitsoft.strymeneshenko.validate.ClientValidate;
import ua.profitsoft.strymeneshenko.validate.IValidate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/AddClientServlet")
public class AddClientServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebApplicationContext webContext = ContextLoader.getCurrentWebApplicationContext();
        ClientService clientService = webContext.getBean("clientService", ClientService.class);
        HttpSession session = request.getSession();
        IValidate<Client> clientIValidate = new ClientValidate();
        try {
            Client c = (Client)session.getAttribute("indiv");
            if(c != null){
                session.removeAttribute("indiv");
                Individual indiv = (Individual) c;
                indiv.setFirstName(request.getParameter("firstName"));
                indiv.setLastName(request.getParameter("lastName"));
                indiv.setAdress(request.getParameter("address"));
                c = indiv;
            }else{
                LegalEntity legalEntity = (LegalEntity)session.getAttribute("legal");
                session.removeAttribute("legal");
                legalEntity.setNameOrganization(request.getParameter("nameOrganization"));
                legalEntity.setAdress(request.getParameter("address"));
                c = legalEntity;
            }
            Map<String,String> errors = clientIValidate.validate(c);
            if(errors.size() == 0){
                clientService.create(c);
                session.removeAttribute("Send");
                session.removeAttribute("clientType");
                response.sendRedirect("ShowContractsServlet");
            }else {
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("addNewClient.jsp").forward(request,response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
