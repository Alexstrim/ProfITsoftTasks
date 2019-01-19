package ua.profitsoft.strymeneshenko.controllers;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import ua.profitsoft.strymeneshenko.data.Contract;
import ua.profitsoft.strymeneshenko.service.ContractService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/EditContractServlet")
public class EditContractServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebApplicationContext webContext = ContextLoader.getCurrentWebApplicationContext();
        ContractService contractService = webContext.getBean("contractService", ContractService.class);
        HttpSession session = request.getSession();
        Contract contract = (Contract) session.getAttribute("editContract");
        try {
            contractService.update(contract);
            session.removeAttribute("editContract");
            response.sendRedirect("ShowContractsServlet");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebApplicationContext webContext = ContextLoader.getCurrentWebApplicationContext();
        ContractService contractService = webContext.getBean("contractService", ContractService.class);
        HttpSession session = request.getSession();
        try {
            Contract contract = contractService.read(Long.parseLong(request.getParameter("contractId")));
            session.setAttribute("editContract", contract);
            response.sendRedirect("editContract.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
