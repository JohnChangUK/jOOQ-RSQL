package johnc.rsqljooq.rsql.evaluator;

import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import org.jooq.Condition;
import org.jooq.Field;

import java.util.List;

public interface ConditionEvaluator<T> {

    Condition evaluate(Field<T> field, List<T> arguments, ComparisonOperator operator);
}
