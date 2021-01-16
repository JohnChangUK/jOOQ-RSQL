package johnc.rsqljooq.context;

import org.jooq.Field;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static java.util.Map.entry;
import static johnc.rsqljooq.jooq.Tables.AUTHOR;
import static johnc.rsqljooq.jooq.Tables.BOOK;

@Component
public class BookTableContext implements TableContext {

    private final Map<String, Field> filters = Map.ofEntries(
            entry("first_name", AUTHOR.FIRST_NAME),
            entry("last_name", AUTHOR.LAST_NAME)
    );

    private final List<Field> allFields = List.of(BOOK.TITLE);

    @Override
    public Map<String, Field> getFilters() {
        return filters;
    }

    @Override
    public List<Field> getAllFields() {
        return allFields;
    }
}
