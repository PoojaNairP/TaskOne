package com.taskone.aspire.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskone.aspire.model.Employee;
import com.taskone.aspire.model.EmployeeManager;
import com.taskone.aspire.model.Manager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private static final Logger logger = LoggerFactory.getLogger(PostService.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void processAndWriteToFile(String inputJson, String outputFilePath) throws IOException {
        if (inputJson == null || inputJson.trim().isEmpty()) {
            throw new IllegalArgumentException("Input JSON cannot be null or empty.");
        }
        if (outputFilePath == null || outputFilePath.trim().isEmpty()) {
            throw new IllegalArgumentException("Output file path cannot be null or empty.");
        }

        List<Employee> employees;
        try {
            employees = objectMapper.readValue(inputJson, objectMapper.getTypeFactory().constructCollectionType(List.class, Employee.class));
        } catch (IOException e) {
            logger.error("Failed to parse input JSON", e);
            throw new IOException("Failed to parse input JSON", e);
        }

        List<EmployeeManager> outputList = processEmployees(employees);

        try (FileWriter fileWriter = new FileWriter(outputFilePath)) {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(fileWriter, outputList);
        } catch (IOException e) {
            logger.error("Failed to write output to file", e);
            throw new IOException("Failed to write output to file", e);
        }
    }

    private List<EmployeeManager> processEmployees(List<Employee> employees) {
        if (employees == null || employees.isEmpty()) {
            throw new IllegalArgumentException("Employee list cannot be null or empty.");
        }

        // Group employees by manager
        Map<Integer, List<Employee>> employeesByManager = employees.stream()
                .collect(Collectors.groupingBy(Employee::getManagerId));

        // Create the output structure
        return employeesByManager.entrySet().stream()
                .map(entry -> {
                    int managerId = entry.getKey();
                    List<Employee> empList = entry.getValue();

                    if (empList.isEmpty()) {
                        throw new IllegalStateException("Employee list for manager cannot be empty.");
                    }

                    // Create Manager object
                    Manager manager = new Manager();
                    manager.setId(managerId);
                    manager.setName(Objects.requireNonNull(empList.get(0).getManagerName(), "Manager name cannot be null."));

                    // Sort employees by name
                    List<Employee> sortedEmployees = empList.stream()
                            .sorted((e1, e2) -> e1.getName().compareTo(e2.getName()))
                            .collect(Collectors.toList());

                    // Create EmployeeManager object
                    EmployeeManager managerWithEmployees = new EmployeeManager();
                    managerWithEmployees.setManager(manager);
                    managerWithEmployees.setEmployeeList(sortedEmployees);

                    return managerWithEmployees;
                })
                .sorted((m1, m2) -> m1.getManager().getName().compareTo(m2.getManager().getName())) // Sort managers by name
                .collect(Collectors.toList());
    }
}













