package johnc.rsqljooq.rsql.evaluator;

import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import johnc.rsqljooq.rsql.RSQLSearch;
import org.jooq.Condition;
import org.jooq.Field;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class StringConditionEvaluator<T> implements ConditionEvaluator<T> {

    public Condition evaluate(Field<T> field, List<T> arguments, ComparisonOperator operator) {
        T firstArgument = arguments.get(0);
        switch (RSQLSearch.getOperator(operator)) {
            case EQUAL:
                return field.eq(firstArgument);
            case NOT_EQUAL:
                return field.notEqual(firstArgument);
            case IN:
                return field.in(arguments);
            case NOT_IN:
                return field.notIn(arguments);
            default:
                throw new ResponseStatusException(
                        HttpStatus.UNPROCESSABLE_ENTITY,
                        "RSQL Operator: " + operator.toString() + " not allowed for strings");
        }
    }
}
