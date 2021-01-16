package johnc.rsqljooq.rsql;

import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import cz.jirutka.rsql.parser.ast.RSQLOperators;
import johnc.rsqljooq.config.RSQLJooqConfig;

public enum RSQLSearch {
    EQUAL(RSQLOperators.EQUAL),
    NOT_EQUAL(RSQLOperators.NOT_EQUAL),
    GREATER_THAN(RSQLOperators.GREATER_THAN),
    GREATER_THAN_OR_EQUAL(RSQLOperators.GREATER_THAN_OR_EQUAL),
    LESS_THAN(RSQLOperators.LESS_THAN),
    LESS_THAN_OR_EQUAL(RSQLOperators.LESS_THAN_OR_EQUAL),
    IN(RSQLOperators.IN),
    NOT_IN(RSQLOperators.NOT_IN),
    BETWEEN(RSQLJooqConfig.BETWEEN);

    private ComparisonOperator operator;

    RSQLSearch(ComparisonOperator operator) {
        this.operator = operator;
    }

    public static RSQLSearch getOperator(ComparisonOperator operator) {
        for (RSQLSearch rsql : values()) {
            if (rsql.getOperator() == operator) {
                return rsql;
            }
        }
        return null;
    }

    public ComparisonOperator getOperator() {
        return operator;
    }
}
