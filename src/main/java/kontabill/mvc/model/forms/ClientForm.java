package main.java.kontabill.mvc.model.forms;

import main.java.kontabill.layout.elements.forms.model.InputType;
import main.java.kontabill.mvc.model.entities.Entity;
import main.java.kontabill.mvc.model.entities.LegalEntity;
import main.java.kontabill.mvc.model.entities.LegalEntityDetailPerson;
import main.java.kontabill.mvc.model.entities.Representative;
import main.java.kontabill.mvc.model.forms.base.*;
import main.java.kontabill.mvc.model.forms.base.list_model.AbstractComboListModel;
import main.java.kontabill.mvc.model.forms.base.list_model.ClientTypeListModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class ClientForm extends BaseAbstractForm implements DynamicChangeableForm {

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
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_COUNTY = "county";
    private static final String KEY_IBAN = "iban";


    private static final String GROUP_PF = "groupPf";
    private static final String GROUP_PJ = "groupPJ";

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
                    InputType.DROPDOWN.toString(),
                    "Tip client",
                    KEY_CLIENT_TYPE,
                    GROUP_PF,
            },
            {
                    InputType.TEXT_FIELD.toString(),
                    "Nume",
                    KEY_PF_NAME,
                    GROUP_PF,
            },
            {
                    InputType.TEXT_FIELD.toString(),
                    "Prenume",
                    KEY_PF_LASTNAME,
                    GROUP_PF,
            },
            {
                    InputType.TEXT_FIELD.toString(),
                    "Cnp",
                    KEY_PF_CNP,
                    GROUP_PF,
            },
            {
                    InputType.TEXT_FIELD.toString(),
                    "Serie buletin",
                    KEY_PF_ID_SERIAL,
                    GROUP_PF,
            },
            {
                    InputType.TEXT_FIELD.toString(),
                    "Nr buletin",
                    KEY_PF_ID_NO,
                    GROUP_PF,
            },
            {
                    InputType.TEXT_FIELD.toString(),
                    "Companie",
                    KEY_PJ_COMPANY,
                    GROUP_PJ,
            },
            {
                    InputType.TEXT_FIELD.toString(),
                    "CIF",
                    KEY_PJ_CIF,
                    GROUP_PJ,
            },
            {
                    InputType.TEXT_FIELD.toString(),
                    "Nr reg comert",
                    KEY_PJ_TRADE_REGISTER,
                    GROUP_PJ,
            },
            {
                    InputType.TEXT_FIELD.toString(),
                    "Banca",
                    KEY_PJ_BANK,
                    GROUP_PJ,
            },
            {
                    InputType.TEXT_FIELD.toString(),
                    "IBAN",
                    KEY_IBAN,
                    GROUP_PF + "|" + GROUP_PJ
            },
            {
                    InputType.DROPDOWN.toString(),
                    "Judet",
                    KEY_COUNTY,
                    GROUP_PF + "|" + GROUP_PJ
            },
            {
                    InputType.TEXT_FIELD.toString(),
                    "Adresa",
                    KEY_ADDRESS,
                    GROUP_PF + "|" + GROUP_PJ,
            },

    };

    // VALIDATORS DEFINITION
    private static final Map<String, List<ValidatorConfig>> ELEMENTS_VALIDATORS = new HashMap<>();

    static {
        // add validators for representative name
        ArrayList<ValidatorConfig> validatorsName = new ArrayList<>();
        ArrayList<ValidatorConfig> validatorsCompany = new ArrayList<>();
        ArrayList<ValidatorConfig> validatorsCif = new ArrayList<>();
        ArrayList<ValidatorConfig> validatorsClientType = new ArrayList<>();

        validatorsClientType.add(new ValidatorConfig(
                ValidatorType.REQUIRED_DROPDOWN,
                new String[]{}
        ));

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

        validatorsCif.add(new ValidatorConfig(
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

        ELEMENTS_VALIDATORS.put(
                KEY_PJ_CIF,
                validatorsCif
        );

        ELEMENTS_VALIDATORS.put(
                KEY_CLIENT_TYPE,
                validatorsClientType
        );
    }
    // END VALIDATORS DEFINITION

    public ClientForm() {
        super();
        setDynamicChangeConfiguration();
    }

    @Override
    public void setDynamicChangeConfiguration() {
        // set dynamic change config for element clientType with value pers fizica
        DynamicChangeConfig dynamicChangeConfigPf = new DynamicChangeConfig();
        dynamicChangeConfigPf.setForElementWithKey(KEY_CLIENT_TYPE);
        dynamicChangeConfigPf.setForElementWithValue(ClientTypeListModel.PF_OPTION_VALUE);
        dynamicChangeConfigPf.setRemoveValidatorsForHiddenElements(true);

        // add shown element for PF
        dynamicChangeConfigPf.setShowElements(
                getFormElementConfig().getGroupedFormKeys(GROUP_PF)
        );

        // add dynamic change for PF to the dynamic change config object
        this.dynamicChangeConfigMap.put("clientTypePf", dynamicChangeConfigPf);

        // ###

        // set dynamic change config for element clientType with value pers juridica
        DynamicChangeConfig dynamicChangeConfigPj = new DynamicChangeConfig();
        dynamicChangeConfigPj.setForElementWithKey(KEY_CLIENT_TYPE);
        dynamicChangeConfigPj.setForElementWithValue(ClientTypeListModel.PJ_OPTION_VALUE);
        dynamicChangeConfigPj.setRemoveValidatorsForHiddenElements(true);

        // add shown element for PJ
        dynamicChangeConfigPj.setShowElements(
                getFormElementConfig().getGroupedFormKeys(GROUP_PJ)
        );

        // add dynamic change for PJ to the dynamic change config object
        this.dynamicChangeConfigMap.put("clientTypePj", dynamicChangeConfigPj);


        // add dymaic change for clientyType for initial preselect key (default: Alege...)
        DynamicChangeConfig dynamicChangeConfigChoose = new DynamicChangeConfig();
        dynamicChangeConfigChoose.setForElementWithKey(KEY_CLIENT_TYPE);
        dynamicChangeConfigChoose.setForElementWithValue(AbstractComboListModel.INITIAL_PRESELECTED_KEY);
        dynamicChangeConfigChoose.setRemoveValidatorsForHiddenElements(true);

        this.dynamicChangeConfigMap.put(AbstractComboListModel.INITIAL_PRESELECTED_KEY, dynamicChangeConfigChoose);
    }

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
