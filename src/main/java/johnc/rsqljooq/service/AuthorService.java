package johnc.rsqljooq.service;

import johnc.rsqljooq.model.Author;
import johnc.rsqljooq.repository.AuthorRepository;
import org.jooq.Condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.getAllAuthors();
    }

    public List<Author> getAuthorWithRSQLFilter(Condition condition) {
        return authorRepository.getAllAuthorsWithSqlCondition(condition);
    }
}
