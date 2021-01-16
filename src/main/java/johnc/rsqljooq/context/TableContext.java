package johnc.rsqljooq.context;

import org.jooq.Field;

import java.util.List;
import java.util.Map;

public interface TableContext {

    Map<String, Field> getFilters();

    List<Field> getAllFields();
}
