package main.java.kontabill.mvc.model.forms.base.list_model;

import java.util.ArrayList;

/**
 * Created by cbitoi on 02/02/16.
 */
public class PfCountyListModel extends AbstractComboListModel {

    ArrayList<String> listOption = new ArrayList<>();

    @Override
    public ArrayList getListOption() {
        return listOption;
    }

    @Override
    public void setListOption() {
        listOption.add("Alege...");
        listOption.add("Iasi");
        listOption.add("Constanta");
        listOption.add("Craiova");
    }

    @Override
    public void setInitialPreselection() {
        setSelectedItem(
                AbstractComboListModel.INITIAL_PRESELECTED_KEY
        );
    }
}
