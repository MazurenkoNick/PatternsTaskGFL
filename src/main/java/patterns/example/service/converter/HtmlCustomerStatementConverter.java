package patterns.example.service.converter;

import patterns.example.model.AmountAndRenterPoints;
import patterns.example.model.Customer;
import patterns.example.service.customer.CustomerService;

import java.util.Map;

public class HtmlCustomerStatementConverter implements CustomerStatementConverter {

    public static final HtmlCustomerStatementConverter INSTANCE = new HtmlCustomerStatementConverter();
    private final CustomerService customerService;

    private HtmlCustomerStatementConverter() {
        this.customerService = CustomerService.INSTANCE;
    }

    @Override
    public String getStatement(Customer customer) {
        AmountAndRenterPoints amountsAndRenterPoints = customerService.countAmountsAndRenterPoints(customer);
        StringBuilder sb = new StringBuilder();

        sb.append("<h1>Rental Record for ").append(customer.getName()).append("</h1>\n");
        sb.append("<ul>");
        for (Map.Entry<String, Double> entry : amountsAndRenterPoints.getAmountsAndRenterPoints().entrySet()) {
            sb.append("\n\t<li>")
                    .append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue())
                    .append("</li>");
        }
        sb.append("\n</ul>");

        return sb.toString();
    }
}
