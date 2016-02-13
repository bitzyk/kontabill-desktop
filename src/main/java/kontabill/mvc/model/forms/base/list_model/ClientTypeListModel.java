package main.java.kontabill.mvc.model.forms.base.list_model;

import java.util.ArrayList;

/**
 * Created by cbitoi on 02/02/16.
 */
public class ClientTypeListModel extends AbstractComboListModel {

    ArrayList<String> listOption = new ArrayList<>();

    @Override
    public ArrayList getListOption() {
        return listOption;
    }

    public static final String PF_OPTION_VALUE  = "Persoana fizica";

    public static final String PJ_OPTION_VALUE  = "Persoana juridica";

    @Override
    public void setListOption() {
        listOption.add("Alege...");
        listOption.add(PF_OPTION_VALUE);
        listOption.add(PJ_OPTION_VALUE);
    }

    @Override
    public void setInitialPreselection() {
        setSelectedItem(
                AbstractComboListModel.INITIAL_PRESELECTED_KEY
        );
    }
}
