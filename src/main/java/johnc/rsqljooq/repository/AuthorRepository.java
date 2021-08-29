package johnc.rsqljooq.repository;

import johnc.rsqljooq.context.TableContext;
import johnc.rsqljooq.model.Author;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static johnc.rsqljooq.jooq.Tables.AUTHOR;

@Repository
public class AuthorRepository {

    private final DSLContext dsl;
    private final TableContext authorTableContext;

    @Autowired
    public AuthorRepository(DSLContext dsl, TableContext authorTableContext) {
        this.dsl = dsl;
        this.authorTableContext = authorTableContext;
    }

    public List<Author> getAllAuthors() {
        return dsl.select(authorTableContext.getAllFields())
                .from(AUTHOR)
                .fetch()
                .stream()
                .map(this::authorEntityToDto)
                .collect(Collectors.toList());
    }

    public List<Author> getAllAuthorsWithSqlCondition(Condition condition) {
        return dsl
                .select(authorTableContext.getAllFields())
                .from(AUTHOR)
                .where(condition)
                .fetch()
                .stream()
                .map(this::authorEntityToDto)
                .collect(Collectors.toList());
    }

    private Author authorEntityToDto(Record record) {
        return new Author.Builder()
                .firstName(record.get(AUTHOR.FIRST_NAME))
                .lastName(record.get(AUTHOR.LAST_NAME))
                .build();
    }
}
