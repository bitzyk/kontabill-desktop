package main.java.kontabill.mvc.model.forms;

import main.java.kontabill.layout.elements.forms.model.InputType;
import main.java.kontabill.mvc.model.entities.Entity;
import main.java.kontabill.mvc.model.entities.LegalEntity;
import main.java.kontabill.mvc.model.entities.LegalEntityDetailPerson;
import main.java.kontabill.mvc.model.entities.Representative;
import main.java.kontabill.mvc.model.forms.base.BaseAbstractForm;
import main.java.kontabill.mvc.model.forms.base.ValidatorConfig;
import main.java.kontabill.mvc.model.forms.base.ValidatorType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class ClientForm extends BaseAbstractForm {

    private static final String KEY_CLIENT_TYPE = "clientType";
    private static final String KEY_PF_LASTNAME = "pfLastName";
    private static final String KEY_PF_NAME = "pfName";
    private static final String KEY_PF_CNP = "pfCnp";
    private static final String KEY_PF_ID_SERIAL = "pfIdSerial";
    private static final String KEY_PF_ID_NO = "pfIdNo";
    private static final String KEY_PJ_COMPANY = "pjCompany";
    private static final String KEY_PJ_CIF = "pjCif";
    private static final String KEY_PJ_TRADE_REGISTER = "pjTradeRegister";
    private static final String KEY_PJ_BANK = "pjBank";
    private static final String KEY_ADDRESS = "pfAddress";
    private static final String KEY_COUNTY = "pfCounty";
    private static final String KEY_IBAN = "pfIban";


    /**
     * Definition :
     *  - input type
     *  - input label
     *  - input key
     */
    private static final String[][] ELEMENTS_DEFINITION =
    {
            {
                    InputType.DROPDOWN.toString(),
                    "Tip client",
                    KEY_CLIENT_TYPE,
            },
            {
                    InputType.TEXT_FIELD.toString(),
                    "Nume",
                    KEY_PF_NAME
            },
            {
                    InputType.TEXT_FIELD.toString(),
                    "Prenume",
                    KEY_PF_LASTNAME
            },
            {
                    InputType.TEXT_FIELD.toString(),
                    "Serie buletin",
                    KEY_PF_ID_SERIAL
            },
            {
                    InputType.TEXT_FIELD.toString(),
                    "Nr buletin",
                    KEY_PF_ID_NO
            },
            {
                    InputType.TEXT_FIELD.toString(),
                    "Adresa",
                    KEY_ADDRESS
            },
            {
                    InputType.DROPDOWN.toString(),
                    "Judet",
                    KEY_COUNTY
            },
            {
                    InputType.TEXT_FIELD.toString(),
                    "IBAN",
                    KEY_IBAN
            },
            {
                    InputType.TEXT_FIELD.toString(),
                    "Companie",
                    KEY_PJ_COMPANY
            },
            {
                    InputType.TEXT_FIELD.toString(),
                    "CIF",
                    KEY_PJ_CIF
            },
            {
                    InputType.TEXT_FIELD.toString(),
                    "Nr reg comert",
                    KEY_PJ_TRADE_REGISTER
            },
            {
                    InputType.TEXT_FIELD.toString(),
                    "Banca",
                    KEY_PJ_BANK
            },
    };

    // VALIDATORS DEFINITION
    private static final Map<String, List<ValidatorConfig>> ELEMENTS_VALIDATORS = new HashMap<>();

    static {
        // add validators for representative name
        ArrayList<ValidatorConfig> validatorsName = new ArrayList<>();
        ArrayList<ValidatorConfig> validatorsCompany = new ArrayList<>();


        validatorsName.add(new ValidatorConfig(
                ValidatorType.REQUIRED,
                new String[]{}
        ));
        validatorsName.add(
                new ValidatorConfig(
                        ValidatorType.MIN_LENGTH,
                        new String[]{
                                "3", // number of chars
                        }
                )
        );
        validatorsName.add(new ValidatorConfig(
                ValidatorType.MAX_LENGTH,
                new String[]{
                        "50" // number of chars
                }
        ));

        validatorsCompany.add(new ValidatorConfig(
                ValidatorType.REQUIRED,
                new String[]{}
        ));


        // add validator for cnp
        ArrayList<ValidatorConfig> validatorsCnp = new ArrayList<>();
        validatorsCnp.add(new ValidatorConfig(
                ValidatorType.REQUIRED,
                new String[]{}
        ));

        // @todo -> add validator: exact length
        // @todo -> add validator: cnp validator

        // add validators to ELEMENT_VALIDATORS
        ELEMENTS_VALIDATORS.put(
                KEY_PF_NAME,
                validatorsName
        );
        ELEMENTS_VALIDATORS.put(
                KEY_PJ_COMPANY,
                validatorsCompany
        );

        // @todo -> condition validator: if tipPersoana = fizica (event listener)
        ELEMENTS_VALIDATORS.put(
                KEY_PF_CNP,
                validatorsCnp
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
//        if(! (entity instanceof Representative)) {
//            throw new RuntimeException("Hydrate invalid entity.");
//        }
//
//        // hydrate
//        Representative representative = (Representative)entity;
//        representative
//                .setName(
//                        getFormElements().get(KEY_REPRESENTATIVE_NAME).getValue()
//                )
//                .setType(LegalEntity.TYPE_PF)
//                .setIdentifier(
//                        getFormElements().get(KEY_IDENTIFIER).getValue()
//                );
//
//        // if LegalEntity does not already contain LegalEntityDetailPerson set a new object
//        if(! (representative.getLegalEntityDetail() instanceof LegalEntityDetailPerson)) {
//            representative.setLegalEntityDetail(new LegalEntityDetailPerson());
//        }

    }

    public void hydrateForm(Entity entity)
    {
//        Representative representative = (Representative) entity;
//
//        getFormElement(KEY_REPRESENTATIVE_NAME).setValue(
//                representative.getName()
//        );
//        getFormElement(KEY_IDENTIFIER).setValue(
//                representative.getIdentifier()
//        );
    }
}
