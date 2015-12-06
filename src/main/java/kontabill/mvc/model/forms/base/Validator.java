package main.java.kontabill.mvc.model.forms.base;

import java.util.List;

/**
 *
 */
public interface Validator {

    public boolean validate(String value);

    public List<String> getErrors();
}
