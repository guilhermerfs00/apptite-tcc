package com.dev.apptite.domain.query.strategy;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class LessThanOrEqualOperation implements CriteriaOperationStrategy {
    @Override
    public Predicate buildPredicate(CriteriaBuilder criteriaBuilder, Root<?> root, String field, String value) {
        return criteriaBuilder.lessThanOrEqualTo(root.get(field), value);
    }
}
