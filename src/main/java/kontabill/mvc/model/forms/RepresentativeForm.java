package main.java.kontabill.mvc.model.forms;

import main.java.kontabill.layout.elements.forms.model.InputType;
import main.java.kontabill.mvc.model.entities.*;
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
public class RepresentativeForm extends BaseAbstractForm {

    private static final String KEY_REPRESENTATIVE_NAME = "representativeName";
    private static final String KEY_IDENTIFIER = "cnp";

    /**
     * Definition :
     *  - input type
     *  - input label
     *  - input key
     */
    private static final String[][] ELEMENTS_DEFINITION =
    {
            {
                    InputType.TEXT_FIELD.toString(),
                    "Nume reprezentant legal",
                    KEY_REPRESENTATIVE_NAME,
            },
            {
                    InputType.TEXT_FIELD.toString(),
                    "Cnp",
                    KEY_IDENTIFIER
            }
    };

    // VALIDATORS DEFINITION
    private static final Map<String, List<ValidatorConfig>> ELEMENTS_VALIDATORS = new HashMap<>();

    static {
        // add validators for representative name
        ArrayList<ValidatorConfig> validatorsRepresentativeName = new ArrayList<>();


        validatorsRepresentativeName.add(new ValidatorConfig(
                ValidatorType.REQUIRED,
                new String[]{}
        ));
        validatorsRepresentativeName.add(
                new ValidatorConfig(
                        ValidatorType.MIN_LENGTH,
                        new String[]{
                                "3", // number of chars
                        }
                )
        );
        validatorsRepresentativeName.add(new ValidatorConfig(
                ValidatorType.MAX_LENGTH,
                new String[]{
                        "50" // number of chars
                }
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
                KEY_REPRESENTATIVE_NAME,
                validatorsRepresentativeName
        );
        ELEMENTS_VALIDATORS.put(
                KEY_IDENTIFIER,
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
        if(! (entity instanceof Representative)) {
            throw new RuntimeException("Hydrate invalid entity.");
        }

        // hydrate
        Representative representative = (Representative)entity;
        representative
                .setName(
                        getFormElements().get(KEY_REPRESENTATIVE_NAME).getValue()
                )
                .setType(LegalEntity.TYPE_PF)
                .setIdentifier(
                        getFormElements().get(KEY_IDENTIFIER).getValue()
                );

        // if LegalEntity does not already contain LegalEntityDetailPerson set a new object
        if(! (representative.getLegalEntityDetail() instanceof LegalEntityDetailPerson)) {
            representative.setLegalEntityDetail(new LegalEntityDetailPerson());
        }

    }

    public void hydrateForm(Entity entity)
    {
        Representative representative = (Representative) entity;

        getFormElement(KEY_REPRESENTATIVE_NAME).setValue(
                representative.getName()
        );
        getFormElement(KEY_IDENTIFIER).setValue(
                representative.getIdentifier()
        );
    }
}
