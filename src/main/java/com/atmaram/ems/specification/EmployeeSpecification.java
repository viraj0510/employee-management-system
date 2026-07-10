package com.atmaram.ems.specification;

import com.atmaram.ems.entity.Employee;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecification {

    public static Specification<Employee> hasFirstName(String firstName) {
        return (root, query, criteriaBuilder) ->
                firstName == null || firstName.isBlank()
                        ? null
                        : criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("firstName")),
                                "%" + firstName.toLowerCase() + "%");
    }

    public static Specification<Employee> hasLastName(String lastName) {
        return (root, query, criteriaBuilder) ->
                lastName == null || lastName.isBlank()
                        ? null
                        : criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("lastName")),
                                "%" + lastName.toLowerCase() + "%");
    }

    public static Specification<Employee> hasDepartment(String department) {
        return (root, query, criteriaBuilder) ->
                department == null || department.isBlank()
                        ? null
                        : criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("department")),
                                "%" + department.toLowerCase() + "%");
    }

    public static Specification<Employee> hasDesignation(String designation) {
        return (root, query, criteriaBuilder) ->
                designation == null || designation.isBlank()
                        ? null
                        : criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("designation")),
                                "%" + designation.toLowerCase() + "%");
    }

    public static Specification<Employee> hasStatus(String status) {
        return (root, query, criteriaBuilder) ->
                status == null || status.isBlank()
                        ? null
                        : criteriaBuilder.equal(
                                criteriaBuilder.lower(root.get("status")),
                                status.toLowerCase());
    }
}