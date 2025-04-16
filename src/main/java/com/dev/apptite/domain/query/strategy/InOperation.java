package com.dev.apptite.domain.query.strategy;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.Arrays;

public class InOperation implements CriteriaOperationStrategy {
    @Override
    public Predicate buildPredicate(CriteriaBuilder criteriaBuilder, Root<?> root, String field, String value) {
        String[] values = value.split("/");
        return root.get(field).in(Arrays.asList(values));
    }
}
