package com.dev.apptite.domain.query.strategy;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BetweenOperation implements CriteriaOperationStrategy {
    @Override
    public Predicate buildPredicate(CriteriaBuilder criteriaBuilder, Root<?> root, String field, String value) {
        String[] dates = value.split("/");
        LocalDate startDate = LocalDate.parse(dates[0], DateTimeFormatter.ISO_DATE);
        LocalDate endDate = LocalDate.parse(dates[1], DateTimeFormatter.ISO_DATE);
        return criteriaBuilder.between(root.get(field), startDate, endDate);
    }
}
