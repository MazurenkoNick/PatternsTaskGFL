package patterns.example.service.converter;

import patterns.example.model.AmountAndRenterPoints;
import patterns.example.model.Customer;

public interface CustomerStatementConverter {

    String getStatement(Customer customer);
    String getStatement(AmountAndRenterPoints amountAndRenterPoints);
}
