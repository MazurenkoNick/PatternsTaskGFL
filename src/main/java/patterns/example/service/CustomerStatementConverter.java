package patterns.example.service;

import patterns.example.model.Customer;

public interface CustomerStatementConverter {

    String getStatement(Customer customer);
}
