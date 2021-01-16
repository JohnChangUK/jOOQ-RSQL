package johnc.rsqljooq.rsql.generator;

import org.jooq.Field;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ConditionGeneratorContext {

    private final ConditionGenerator<Field<String>> stringGeneratorContext = new StringConditionGenerator();
    private final ConditionGenerator<Field<Integer>> integerGeneratorContext = new IntegerConditionGenerator();

    private final Map<Class<?>, ConditionGenerator> conditionGenerators = Map.ofEntries(
            Map.entry(String.class, stringGeneratorContext),
            Map.entry(Integer.class, integerGeneratorContext));

    @SuppressWarnings({"unchecked"})
    public <T> ConditionGenerator<Field<T>> getConditionGenerator(Field<T> field) {
        return conditionGenerators.get(field.getDataType().getType());
    }
}
