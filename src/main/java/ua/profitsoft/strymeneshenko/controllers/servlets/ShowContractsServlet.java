package ua.profitsoft.strymeneshenko.controllers.servlets;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import ua.profitsoft.strymeneshenko.data.Contract;
import ua.profitsoft.strymeneshenko.db.dao.hibernate.ContractHibernateDAO;
import ua.profitsoft.strymeneshenko.service.ContractService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/ShowContractsServlet")
public class ShowContractsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebApplicationContext webContext = ContextLoader.getCurrentWebApplicationContext();
        ContractService contractService = webContext.getBean("contractService", ContractService.class);
        try {
            List<Contract> contracts = contractService.getAllList();
            HttpSession session = request.getSession();
            session.setAttribute("contracts", contracts);
            response.sendRedirect("showAllContracts.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
