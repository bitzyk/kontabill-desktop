package main.java.kontabill.mvc.model.forms.base.list_model;


import org.apache.commons.lang3.StringUtils;
import java.lang.reflect.Constructor;

/**
 * Created by cbitoi on 02/02/16.
 */
abstract public class ComboListModelFactory {


    public static AbstractComboListModel createListModel(String key)
    {
        try {
            Class<?> listModelClass = Class.forName(
                    "main.java.kontabill.mvc.model.forms.base.list_model."
                            + StringUtils.capitalize(key) +
                            "ListModel"
            );
            Constructor<?> constructor = listModelClass.getConstructor();

            AbstractComboListModel model = (AbstractComboListModel) constructor.newInstance();
            model.setListOption();
            model.setInitialPreselection();

            return model;
        }
        catch (Exception e) {
            throw new RuntimeException("List model for key: `" + key + "` is not defined.");
        }
    }

}
