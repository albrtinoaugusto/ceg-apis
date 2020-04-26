package remote;

import api.models.Entity;
import api.mpesa.MPesaPayment;
import api.models.Payment;
import api.services.Constants;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author Marrengula
 */
@WebServlet("/get-pay")
public class GetPay extends HttpServlet
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");

        //Configs Requests Controlers
        setAccessControlHeaders(response);
        
        
        String money = request.getParameter("money");
        String reference = request.getParameter("reference");
        String customerNumber = request.getParameter("cnumber");

        if (money != null)
        {
            money = "";
        }

        if (reference != null)
        {
            reference = "";
        }

        if (customerNumber != null)
        {
            customerNumber = "";
        }

        

        String apiKey = request.getParameter("apikey");
        String publicKey = request.getParameter("publicKey");
        String providerCode = request.getParameter("providercode");

        if (apiKey != null)
        {
            apiKey = "";
        }

        if (publicKey != null)
        {
            publicKey = "";
        }

        if (providerCode != null)
        {
            providerCode = "";
        }

        
        Payment payment = new Payment(reference, money, customerNumber);
        Entity entity = new Entity(apiKey, publicKey, providerCode);

        gePay(payment, entity, request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");

        String money = request.getParameter("money");
        String reference = request.getParameter("reference");
        String customerNumber = request.getParameter("cnumber");

        if (money != null)
        {
            money = "";
        }

        if (reference != null)
        {
            reference = "";
        }

        if (customerNumber != null)
        {
            customerNumber = "";
        }

        

        String apiKey = request.getParameter("apikey");
        String publicKey = request.getParameter("publicKey");
        String providerCode = request.getParameter("providercode");

        if (apiKey != null)
        {
            apiKey = "";
        }

        if (publicKey != null)
        {
            publicKey = "";
        }

        if (providerCode != null)
        {
            providerCode = "";
        }

        
        Payment payment = new Payment(reference, money, customerNumber);
        Entity entity = new Entity(apiKey, publicKey, providerCode);

        gePay(payment, entity, request, response);
    }

    
    //for Preflight
    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        setAccessControlHeaders(response);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private void setAccessControlHeaders(HttpServletResponse response)
    {
        response.setHeader("Access-Control-Allow-Origin", Constants.ORIGEN_1);
        response.setHeader("Access-Control-Allow-Methods", "GET");
    }
    
    
    private void gePay(Payment payment, Entity entity, HttpServletRequest request, HttpServletResponse response) throws IOException
    {

        MPesaPayment mPesaPayment = new MPesaPayment(payment, entity);
        String[] results = mPesaPayment.doPayment();
        
        JSONObject jSONObject;
        
        if (results[0].equals(""))
        {
            //4 Error 
            jSONObject = new JSONObject();
            jSONObject.put("mensagem", "Lamentamos! Mas, ocorreu um erro ao se conectar ao GetWay");
            response.getWriter().write(jSONObject.toJSONString());
        }
        else
        {
            if (results[0].equals("201"))
            {
                jSONObject = new JSONObject();
                jSONObject.put("mensagem", "Pagamento efectuado com sucesso!");
                jSONObject.put("code", "201");
                response.getWriter().write(jSONObject.toJSONString());
            }
            else if (results[0].equals("422"))
            {
                jSONObject = new JSONObject();
                jSONObject.put("mensagem", "Saldo insufciente.");
                jSONObject.put("code", "422");
                response.getWriter().write(jSONObject.toJSONString());
            }
            else
            {
                jSONObject = new JSONObject();
                jSONObject.put("mensagem", "Lamentamos! Mas, ocorreu um erro ao debitar... Verifique os dados informados na requisição.");
                jSONObject.put("code", results[0]);
                response.getWriter().write(jSONObject.toJSONString());
            }
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
