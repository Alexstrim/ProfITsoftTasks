package ua.profitsoft.strymeneshenko.controllers.servlets;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import ua.profitsoft.strymeneshenko.data.Client;
import ua.profitsoft.strymeneshenko.data.Contract;
import ua.profitsoft.strymeneshenko.data.InsuredPerson;
import ua.profitsoft.strymeneshenko.service.ClientService;
import ua.profitsoft.strymeneshenko.service.ContractService;
import ua.profitsoft.strymeneshenko.service.InsuredPersonService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/AddContractServlet")
public class AddContractServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebApplicationContext webContext = ContextLoader.getCurrentWebApplicationContext();
        ClientService clientService = webContext.getBean("clientService", ClientService.class);
        InsuredPersonService insuredPersonService = webContext.getBean("insuredPersonService", InsuredPersonService.class);
        ContractService contractService = webContext.getBean("contractService", ContractService.class);
        HttpSession session = request.getSession();
        if(request.getParameter("command").equals("Add to contract")){
            try {
                Client c = clientService.read(Long.parseLong(request.getParameter("client")));
                Contract contract = (Contract) session.getAttribute("contr");
                contract.setClient(c);
                session.setAttribute("contr", contract);
                response.sendRedirect("addNewContract3Step.jsp");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if(request.getParameter("command").equals("Add selected persons to contract")){
            try {
                InsuredPerson ip = insuredPersonService.read(Long.parseLong(request.getParameter("personId")));
                Contract contract = (Contract) session.getAttribute("contr");
                contract.addInsuredPerson(ip);
                session.setAttribute("contr", contract);
                response.sendRedirect("addNewContract3Step.jsp");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(request.getParameter("command").equals("Add contract")){
            Contract contract = (Contract) session.getAttribute("contr");
            try {
                contractService.create(contract);
                session.removeAttribute("contr");
                response.sendRedirect("ShowContractsServlet");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebApplicationContext webContext = ContextLoader.getCurrentWebApplicationContext();
        ClientService clientService = webContext.getBean("clientService", ClientService.class);
        InsuredPersonService insuredPersonService = webContext.getBean("insuredPersonService", InsuredPersonService.class);
        HttpSession session = request.getSession();
        try {
            List<Client> clients = clientService.getAllList();
            System.out.println(clients);
            List<InsuredPerson> persons = insuredPersonService.getAllList();
            session.setAttribute("clients", clients);
            session.setAttribute("persons", persons);
            response.sendRedirect("addNewContract1Step.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
