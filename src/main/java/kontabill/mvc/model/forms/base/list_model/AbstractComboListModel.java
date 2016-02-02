package main.java.kontabill.mvc.model.forms.base.list_model;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by cbitoi on 02/02/16.
 */
abstract public class AbstractComboListModel extends AbstractListModel implements ComboBoxModel{

    private Object selection;

    public static final String INITIAL_PRESELECTED_KEY = "Alege...";

    @Override
    public int getSize() {
        return getListOption().size();
    }

    @Override
    public Object getElementAt(int index) {
        return getListOption().get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selection = anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selection;
    }

    public abstract ArrayList getListOption();

    public abstract void setListOption();

    public abstract void setInitialPreselection();
}
