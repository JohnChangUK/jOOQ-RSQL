package johnc.rsqljooq.controller;

import cz.jirutka.rsql.parser.RSQLParserException;
import johnc.rsqljooq.context.TableContext;
import johnc.rsqljooq.model.Author;
import johnc.rsqljooq.service.AuthorService;
import johnc.rsqljooq.service.RSQLService;
import org.jooq.Condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class AuthorController {

    private final AuthorService authorService;
    private final TableContext authorTableContext;
    private final RSQLService rsqlService;

    @Autowired
    public AuthorController(
            AuthorService authorService,
            TableContext authorTableContext,
            RSQLService rsqlService) {
        this.authorService = authorService;
        this.authorTableContext = authorTableContext;
        this.rsqlService = rsqlService;
    }

    @GetMapping(path = "/authors", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> allAuthors = authorService.getAllAuthors();
        return ResponseEntity.ok(allAuthors);
    }

    @GetMapping(path = "/authors/search", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Author>> getAllAuthors(@RequestParam(name = "filter") String filter) {
        try {
            Condition sqlCondition = rsqlService.getSqlCondition(filter, authorTableContext);
            List<Author> allAuthors = authorService.getAuthorWithRSQLFilter(sqlCondition);
            return ResponseEntity.ok(allAuthors);
        } catch (RSQLParserException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }
}
