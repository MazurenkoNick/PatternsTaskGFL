package patterns.example.service.converter;

import patterns.example.model.Customer;
import patterns.example.service.customer.CustomerService;

import java.util.Map;

public class DefaultCustomerStatementConverter implements CustomerStatementConverter {

    public static final DefaultCustomerStatementConverter INSTANCE = new DefaultCustomerStatementConverter();
    private final CustomerService customerService;

    private DefaultCustomerStatementConverter() {
        this.customerService = CustomerService.INSTANCE;
    }

    public String getStatement(Customer customer) {
        Map<String, Double> amountsAndRenterPoints = customerService.countAmountsAndRenterPoints(customer);
        StringBuilder sb = new StringBuilder();

        sb.append("Rental Record for ").append(customer.getName());
        for (Map.Entry<String, Double> entry : amountsAndRenterPoints.entrySet()) {
            sb.append("\n\t")
                    .append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue());
        }

        return sb.toString();
    }
}
