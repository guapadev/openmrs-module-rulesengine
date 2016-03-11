package org.openmrs.module.rulesengine;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.module.rulesengine.domain.Dose;
import org.openmrs.module.rulesengine.rule.BSABasedDoseRule;
import org.openmrs.web.test.BaseModuleWebContextSensitiveTest;

import static org.junit.Assert.assertEquals;


public class BSABasedDoseRuleTest extends BaseModuleWebContextSensitiveTest {

    private BSABasedDoseRule bsaBasedDoseRule = new BSABasedDoseRule();

    @Before
    public void setUp() throws Exception {
        executeDataSet("DoseRuleTestData.xml");
    }

    @Test
    public void shouldThrowExceptionHeightNotAvailableWhenHeightObsDoesNotExist() {
        Dose calculatedDose;
        try {
            calculatedDose = bsaBasedDoseRule.calculateDose("person_1031_uuid", 5.0);
        } catch (Exception e) {
            calculatedDose=null;
            assertEquals(e.getMessage(), "Height is not available");
        }
        assertEquals(calculatedDose, null);
    }

    @Test
    public void shouldThrowExceptionWeightNotAvailableWhenWeightObsDoesNotExist() {
        Dose calculatedDose;
        try {
            calculatedDose = bsaBasedDoseRule.calculateDose("person_1032_uuid", 5.0);
        } catch (Exception e) {
            calculatedDose = null;
            assertEquals(e.getMessage(), "Weight is not available");
        }
        assertEquals(calculatedDose, null);
    }
}