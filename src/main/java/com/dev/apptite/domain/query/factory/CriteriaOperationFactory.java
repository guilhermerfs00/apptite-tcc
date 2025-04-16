package com.dev.apptite.domain.query.factory;

import com.dev.apptite.domain.enums.CriteriaTypeEnum;
import com.dev.apptite.domain.query.strategy.*;

import java.util.HashMap;
import java.util.Map;

import static com.dev.apptite.domain.enums.CriteriaTypeEnum.*;

public class CriteriaOperationFactory {
    private static final Map<CriteriaTypeEnum, CriteriaOperationStrategy> strategies = new HashMap<>();

    static {
        strategies.put(LIKE, new LikeOperation());
        strategies.put(EQUAL, new EqualOperation());
        strategies.put(BTW, new BetweenOperation());
        strategies.put(GT, new GreaterThanOperation());
        strategies.put(LT, new LessThanOperation());
        strategies.put(GTE, new GreaterThanOrEqualOperation());
        strategies.put(LTE, new LessThanOrEqualOperation());
        strategies.put(IN, new InOperation());
    }

    public static CriteriaOperationStrategy getStrategy(CriteriaTypeEnum type) {
        return strategies.get(type);
    }
}
