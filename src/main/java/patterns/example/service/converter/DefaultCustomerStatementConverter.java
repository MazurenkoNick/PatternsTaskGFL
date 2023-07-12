package patterns.example.service.converter;

import patterns.example.model.AmountAndRenterPoints;
import patterns.example.model.Customer;
import patterns.example.service.customer.CustomerService;

import java.util.Map;

public class DefaultCustomerStatementConverter implements CustomerStatementConverter {

    public static final DefaultCustomerStatementConverter INSTANCE = new DefaultCustomerStatementConverter();
    private final CustomerService customerService;

    private DefaultCustomerStatementConverter() {
        this.customerService = CustomerService.INSTANCE;
    }

    @Override
    public String getStatement(Customer customer) {
        AmountAndRenterPoints amountsAndRenterPoints = customerService.countAmountsAndRenterPoints(customer);
        return getStatement(amountsAndRenterPoints);
    }

    @Override
    public String getStatement(AmountAndRenterPoints amountsAndRenterPoints) {
        StringBuilder sb = new StringBuilder();

        sb.append("Rental Record for ").append(amountsAndRenterPoints.getCustomerName());
        for (Map.Entry<String, Double> entry : amountsAndRenterPoints.getAmountsAndRenterPoints().entrySet()) {
            sb.append("\n\t")
                    .append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue());
        }
        return sb.toString();
    }
}
