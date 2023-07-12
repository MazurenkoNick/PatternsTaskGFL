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
        return getStatement(amountsAndRenterPoints);
    }

    @Override
    public String buildHeader(AmountAndRenterPoints amountAndRenterPoints) {
        return "<h1>Rental Record for " + amountAndRenterPoints.getCustomerName() + "</h1>\n" +
                "<ul>";
    }

    @Override
    public String buildCoreInfo(AmountAndRenterPoints amountAndRenterPoints) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Double> entry : amountAndRenterPoints.getAmountsAndRenterPoints().entrySet()) {
            sb.append("\n\t<li>")
                    .append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue())
                    .append("</li>");
        }
        return sb.toString();
    }

    @Override
    public String buildFooter(AmountAndRenterPoints amountAndRenterPoints) {
        return "\n</ul>";
    }
}
