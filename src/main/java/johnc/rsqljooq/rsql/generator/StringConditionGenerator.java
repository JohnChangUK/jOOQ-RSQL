package johnc.rsqljooq.rsql.generator;

import cz.jirutka.rsql.parser.ast.ComparisonNode;
import johnc.rsqljooq.rsql.evaluator.ConditionEvaluator;
import johnc.rsqljooq.rsql.evaluator.StringConditionEvaluator;
import org.jooq.Condition;
import org.jooq.Field;

public class StringConditionGenerator implements ConditionGenerator<Field<String>> {

    private final ConditionEvaluator<String> stringConditionEvaluator = new StringConditionEvaluator<>();

    @Override
    public Condition generate(Field<String> field, ComparisonNode node) {
        return stringConditionEvaluator.evaluate(field, node.getArguments(), node.getOperator());
    }
}
