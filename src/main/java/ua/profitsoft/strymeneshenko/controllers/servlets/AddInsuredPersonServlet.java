package ua.profitsoft.strymeneshenko.controllers.servlets;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import ua.profitsoft.strymeneshenko.data.InsuredPerson;
import ua.profitsoft.strymeneshenko.service.InsuredPersonService;
import ua.profitsoft.strymeneshenko.util.UtilDate;
import ua.profitsoft.strymeneshenko.validate.IValidate;
import ua.profitsoft.strymeneshenko.validate.InsuredPersonValidate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/AddInsuredPersonServlet")
public class AddInsuredPersonServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebApplicationContext webContext = ContextLoader.getCurrentWebApplicationContext();
        InsuredPersonService insuredPersonService = webContext.getBean("insuredPersonService", InsuredPersonService.class);
        InsuredPerson ip = new InsuredPerson();
        ip.setFirstName(request.getParameter("firstName"));
        ip.setLastName(request.getParameter("lastName"));
        String date = request.getParameter("date");
        ip.setDateOfBirth(UtilDate.stringToDate(date, "yyyy-MM-dd"));
        String cost = request.getParameter("cost");
        if(cost.equals("")){
            ip.setCost(0);
        }else{
            ip.setCost(Integer.parseInt(cost));
        }
        String number = request.getParameter("identificationNumber");
        if(number.equals("")){
            ip.setIdentificationNumber(0L);
        }else{
            ip.setIdentificationNumber(Long.parseLong(number));
        }
        IValidate<InsuredPerson> insuredPersonIValidate = new InsuredPersonValidate();
        Map<String,String> errors = insuredPersonIValidate.validate(ip);
        if(errors.size() == 0){
            try {
                insuredPersonService.create(ip);
                response.sendRedirect("ShowContractsServlet");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("addNewInsuredPerson.jsp").forward(request,response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
