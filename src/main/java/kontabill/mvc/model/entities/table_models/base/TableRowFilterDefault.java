package main.java.kontabill.mvc.model.entities.table_models.base;

import main.java.kontabill.mvc.model.entities.Entity;
import main.java.kontabill.mvc.model.entities.base.FilterableEntity;

import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class TableRowFilterDefault<M, Object> extends RowFilter {

    private String filterTerm;

    private boolean compareCaseSensitive = false;

    @Override
    public boolean include(Entry entry) {

        // if filter term was not been completed include every row by returning true
        if(filterTerm == null || filterTerm.trim().length() == 0) {
            return true;
        }

        // if filter term is completed by default all rows are not included
        // whitelist strategy
        boolean include = false;

        // get entity at current row - current rowIndex it's represented by entry.getIdentifier
        Entity entity = ((BaseAbstract) entry.getModel()).getEntityAtRow(
                (int) entry.getIdentifier()
        );

        if(entity instanceof FilterableEntity) {
            String filterableValue = ((FilterableEntity) entity).getFiletrableEntityValue();

            String patternTerm = "(^|\\s)" + Pattern.quote(filterTerm);

            if(compareCaseSensitive == false) {
                patternTerm = "(?i)" + patternTerm;
            }

            Pattern pattern = Pattern.compile(patternTerm);
            Matcher matcher = pattern.matcher(filterableValue);

            include = matcher.find();
        }

        return include;
    }

    public void setFilterTerm(String filterTerm) {
        this.filterTerm = filterTerm;
    }
}
