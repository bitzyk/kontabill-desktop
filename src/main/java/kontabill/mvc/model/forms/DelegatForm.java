package main.java.kontabill.mvc.model.forms;

import main.java.kontabill.layout.elements.forms.model.InputType;
import main.java.kontabill.mvc.model.entities.*;
import main.java.kontabill.mvc.model.forms.base.BaseAbstractForm;
import main.java.kontabill.mvc.model.forms.base.ValidatorConfig;
import main.java.kontabill.mvc.model.forms.base.ValidatorType;
import main.java.kontabill.mvc.model.forms.base.validators.DecimalValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class DelegatForm extends BaseAbstractForm {

    private static final String KEY_DELEGAT_NAME = "delegatName";
    private static final String KEY_ID_SERIAL = "idSerial";
    private static final String KEY_ID_NO = "idNo";


    /**
     * Definition :
     *  - input type
     *  - input label
     *  - input key
     *  - group key
     */
    private static final String[][] ELEMENTS_DEFINITION =
    {
            {
                    InputType.TEXT_FIELD.toString(),
                    "Nume delegat",
                    KEY_DELEGAT_NAME,
                    "",
            },
            {
                    InputType.TEXT_FIELD.toString(),
                    "Serie buletin",
                    KEY_ID_SERIAL,
                    "",
            },
            {
                    InputType.TEXT_FIELD.toString(),
                    "Numar buletin",
                    KEY_ID_NO,
                    "",
            }
    };

    // VALIDATORS DEFINITION
    private static final Map<String, List<ValidatorConfig>> ELEMENTS_VALIDATORS = new HashMap<>();

    static {
        // add validators for delegat name
        ArrayList<ValidatorConfig> validatorsConfigDelegatName = new ArrayList<>();


        validatorsConfigDelegatName.add(new ValidatorConfig(
                ValidatorType.REQUIRED,
                new String[]{}
        ));
        validatorsConfigDelegatName.add(
                new ValidatorConfig(
                        ValidatorType.MIN_LENGTH,
                        new String[]{
                                "3", // number of chars
                        }
                )
        );
        validatorsConfigDelegatName.add(new ValidatorConfig(
                ValidatorType.MAX_LENGTH,
                new String[]{
                        "50" // numberOfChard
                }
        ));



        // add validator for id serial
        ArrayList<ValidatorConfig> validatorsIdSerial = new ArrayList<>();
        validatorsIdSerial.add(new ValidatorConfig(
                ValidatorType.REQUIRED,
                new String[]{}
        ));
        validatorsIdSerial.add(
                new ValidatorConfig(
                        ValidatorType.MAX_LENGTH,
                        new String[]{
                                "20", // number of chars
                        }
                )
        );
        validatorsIdSerial.add(
                new ValidatorConfig(
                        ValidatorType.ALPHA_NUMERIC,
                        new String[]{}
                )
        );

        // add validators for id no
        ArrayList<ValidatorConfig> validatorsIdNo = new ArrayList<>();
        validatorsIdNo.add(new ValidatorConfig(
                ValidatorType.REQUIRED,
                new String[]{}
        ));
        validatorsIdNo.add(
                new ValidatorConfig(
                        ValidatorType.MAX_LENGTH,
                        new String[]{
                                "20", // number of chars
                        }
                )
        );
        validatorsIdNo.add(
                new ValidatorConfig(
                        ValidatorType.DECIMAL,
                        new String[]{}
                )
        );

        // add validators to ELEMENT_VALIDATORS
        ELEMENTS_VALIDATORS.put(
                KEY_DELEGAT_NAME,
                validatorsConfigDelegatName
        );
        ELEMENTS_VALIDATORS.put(
                KEY_ID_SERIAL,
                validatorsIdSerial
        );
        ELEMENTS_VALIDATORS.put(
                KEY_ID_NO,
                validatorsIdNo
        );
    }
    // END VALIDATORS DEFINITION

    @Override
    public String[][] getElementsDefinition() {
        return ELEMENTS_DEFINITION;
    }

    @Override
    protected Map<String, List<ValidatorConfig>> getElementsValidatorsConfig() {
        return ELEMENTS_VALIDATORS;
    }

    @Override
    public void hydrateEntity(Entity entity) {
        // validate entity to hydrate
        if(! (entity instanceof Delegat)) {
            throw new RuntimeException("Hydrate invalid entity.");
        }

        // hydrate
        Delegat delegat = (Delegat)entity;
        delegat
                .setName(getFormElements().get(KEY_DELEGAT_NAME).getValue())
                .setType(LegalEntity.TYPE_PF);

        // if LegalEntity does not already contain LegalEntityDetailPerson set a new object
        if(! (delegat.getLegalEntityDetail() instanceof LegalEntityDetailPerson)) {
            delegat.setLegalEntityDetail(new LegalEntityDetailPerson());
        }

        LegalEntityDetailPerson legalEntityDetailPerson = delegat.getLegalEntityDetail();

        legalEntityDetailPerson
                .setIdNo(getFormElements().get(KEY_ID_NO).getValue())
                .setIdSerial(getFormElements().get(KEY_ID_SERIAL).getValue());

    }

    public void hydrateForm(Entity entity)
    {
        Delegat delegat = (Delegat) entity;

        getFormElement(KEY_DELEGAT_NAME).setValue(delegat.getName());
        getFormElement(KEY_ID_SERIAL).setValue(delegat.getLegalEntityDetail().getIdSerial());
        getFormElement(KEY_ID_NO).setValue(delegat.getLegalEntityDetail().getIdNo());
    }
}
