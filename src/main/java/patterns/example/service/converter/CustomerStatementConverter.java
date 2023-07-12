package patterns.example.service.converter;

import patterns.example.model.AmountAndRenterPoints;
import patterns.example.model.Customer;

public interface CustomerStatementConverter {

    String getStatement(Customer customer);

    default String getStatement(AmountAndRenterPoints amountAndRenterPoints) {
        StringBuilder sb = new StringBuilder();
        sb.append(buildHeader(amountAndRenterPoints));
        sb.append(buildCoreInfo(amountAndRenterPoints));
        sb.append(buildFooter(amountAndRenterPoints));
        return sb.toString();
    }

    String buildHeader(AmountAndRenterPoints amountAndRenterPoints);
    String buildCoreInfo(AmountAndRenterPoints amountAndRenterPoints);
    default String buildFooter(AmountAndRenterPoints amountAndRenterPoints) { // it is possible to skip this step
        return "";
    }
}
