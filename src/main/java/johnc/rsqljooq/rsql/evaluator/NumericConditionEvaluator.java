package johnc.rsqljooq.rsql.evaluator;

import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import johnc.rsqljooq.rsql.RSQLSearch;
import org.jooq.Condition;
import org.jooq.Field;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class NumericConditionEvaluator<T> implements ConditionEvaluator<T> {

    public Condition evaluate(Field<T> field, List<T> arguments, ComparisonOperator operator) {
        T firstArgument = arguments.get(0);
        switch (RSQLSearch.getOperator(operator)) {
            case EQUAL:
                return field.eq(firstArgument);
            case NOT_EQUAL:
            case GREATER_THAN:
                return field.greaterThan(firstArgument);
            case GREATER_THAN_OR_EQUAL:
                return field.greaterOrEqual(firstArgument);
            case LESS_THAN:
                return field.lessThan(firstArgument);
            case LESS_THAN_OR_EQUAL:
                return field.lessOrEqual(firstArgument);
            case IN:
                return field.in(arguments);
            case NOT_IN:
                return field.notIn(arguments);
            case BETWEEN:
                if (arguments.size() > 1) {
                    return field.between(firstArgument, arguments.get(1));
                }
            default:
                throw new ResponseStatusException(
                        HttpStatus.UNPROCESSABLE_ENTITY,
                        "RSQL Operator: " + operator + " not allowed for numbers");
        }
    }
}
