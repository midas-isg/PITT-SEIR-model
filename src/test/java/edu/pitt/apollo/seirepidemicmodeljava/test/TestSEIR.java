/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pitt.apollo.seirepidemicmodeljava.test;

import edu.pitt.apollo.seirepidemicmodeljava.model.SeirModel;
import edu.pitt.apollo.seirepidemicmodeljava.types.SeirModelInput;
import edu.pitt.apollo.seirepidemicmodeljava.types.SeirModelOutput;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import junit.framework.TestCase;

/**
 *
 * @author nem41
 */
public class TestSEIR extends TestCase {

	public static final double THRESHOLD = 0.00000001;
	public static final String OUTPUT_DIR = "./src/test/resources/";
	// no cm files
	public static final String NO_CM_SUSCEPTIBLE_FILE = OUTPUT_DIR + "no_cm_susceptible.txt";
	public static final String NO_CM_EXPOSED_FILE = OUTPUT_DIR + "no_cm_exposed.txt";
	public static final String NO_CM_INFECTIOUS_FILE = OUTPUT_DIR + "no_cm_infectious.txt";
	public static final String NO_CM_RECOVERED_FILE = OUTPUT_DIR + "no_cm_recovered.txt";
	public static final String NO_CM_NEWLY_EXPOSED_FILE = OUTPUT_DIR + "no_cm_newly_exposed.txt";
	public static final String NO_CM_NEWLY_INFECTIOUS_FILE = OUTPUT_DIR + "no_cm_newly_infectious.txt";
	// vacc cm files
	public static final String VACC_SUSCEPTIBLE_FILE = OUTPUT_DIR + "vacc_susceptible.txt";
	public static final String VACC_EXPOSED_FILE = OUTPUT_DIR + "vacc_exposed.txt";
	public static final String VACC_INFECTIOUS_FILE = OUTPUT_DIR + "vacc_infectious.txt";
	public static final String VACC_RECOVERED_FILE = OUTPUT_DIR + "vacc_recovered.txt";
	public static final String VACC_NEWLY_EXPOSED_FILE = OUTPUT_DIR + "vacc_newly_exposed.txt";
	public static final String VACC_NEWLY_INFECTIOUS_FILE = OUTPUT_DIR + "vacc_newly_infectious.txt";
	public static final String VACC_VACCINES_GIVEN_FILE = OUTPUT_DIR + "vacc_vaccines_given.txt";
	// vacc cm no delay files
	public static final String VACC_SUSCEPTIBLE_NO_DELAY_FILE = OUTPUT_DIR + "vacc_susceptible_no_delay.txt";
	public static final String VACC_EXPOSED_NO_DELAY_FILE = OUTPUT_DIR + "vacc_exposed_no_delay.txt";
	public static final String VACC_INFECTIOUS_NO_DELAY_FILE = OUTPUT_DIR + "vacc_infectious_no_delay.txt";
	public static final String VACC_RECOVERED_NO_DELAY_FILE = OUTPUT_DIR + "vacc_recovered_no_delay.txt";
	public static final String VACC_NEWLY_EXPOSED_NO_DELAY_FILE = OUTPUT_DIR + "vacc_newly_exposed_no_delay.txt";
	public static final String VACC_NEWLY_INFECTIOUS_NO_DELAY_FILE = OUTPUT_DIR + "vacc_newly_infectious_no_delay.txt";
	public static final String VACC_VACCINES_GIVEN_NO_DELAY_FILE = OUTPUT_DIR + "vacc_vaccines_given_no_delay.txt";
	// av cm files
	public static final String AV_SUSCEPTIBLE_FILE = OUTPUT_DIR + "av_susceptible.txt";
	public static final String AV_EXPOSED_FILE = OUTPUT_DIR + "av_exposed.txt";
	public static final String AV_INFECTIOUS_FILE = OUTPUT_DIR + "av_infectious.txt";
	public static final String AV_RECOVERED_FILE = OUTPUT_DIR + "av_recovered.txt";
	public static final String AV_NEWLY_EXPOSED_FILE = OUTPUT_DIR + "av_newly_exposed.txt";
	public static final String AV_NEWLY_INFECTIOUS_FILE = OUTPUT_DIR + "av_newly_infectious.txt";
	public static final String AV_ANTIVIRALS_GIVEN_FILE = OUTPUT_DIR + "av_antivirals_given.txt";
	// av cm no delay files
	public static final String AV_SUSCEPTIBLE_NO_DELAY_FILE = OUTPUT_DIR + "av_susceptible_no_delay.txt";
	public static final String AV_EXPOSED_NO_DELAY_FILE = OUTPUT_DIR + "av_exposed_no_delay.txt";
	public static final String AV_INFECTIOUS_NO_DELAY_FILE = OUTPUT_DIR + "av_infectious_no_delay.txt";
	public static final String AV_RECOVERED_NO_DELAY_FILE = OUTPUT_DIR + "av_recovered_no_delay.txt";
	public static final String AV_NEWLY_EXPOSED_NO_DELAY_FILE = OUTPUT_DIR + "av_newly_exposed_no_delay.txt";
	public static final String AV_NEWLY_INFECTIOUS_NO_DELAY_FILE = OUTPUT_DIR + "av_newly_infectious_no_delay.txt";
	public static final String AV_ANTIVIRALS_GIVEN_NO_DELAY_FILE = OUTPUT_DIR + "av_antivirals_given_no_delay.txt";
	// av and vacc cm files
	public static final String AV_VACC_SUSCEPTIBLE_FILE = OUTPUT_DIR + "av_vacc_susceptible.txt";
	public static final String AV_VACC_EXPOSED_FILE = OUTPUT_DIR + "av_vacc_exposed.txt";
	public static final String AV_VACC_INFECTIOUS_FILE = OUTPUT_DIR + "av_vacc_infectious.txt";
	public static final String AV_VACC_RECOVERED_FILE = OUTPUT_DIR + "av_vacc_recovered.txt";
	public static final String AV_VACC_NEWLY_EXPOSED_FILE = OUTPUT_DIR + "av_vacc_newly_exposed.txt";
	public static final String AV_VACC_NEWLY_INFECTIOUS_FILE = OUTPUT_DIR + "av_vacc_newly_infectious.txt";
	public static final String AV_VACC_ANTIVIRALS_GIVEN_FILE = OUTPUT_DIR + "av_vacc_antivirals_given.txt";
	public static final String AV_VACC_VACCINES_GIVEN_FILE = OUTPUT_DIR + "av_vacc_vaccines_given.txt";
	// av and vacc no delay files
	public static final String AV_VACC_SUSCEPTIBLE_NO_DELAY_FILE = OUTPUT_DIR + "av_vacc_susceptible_no_delay.txt";
	public static final String AV_VACC_EXPOSED_NO_DELAY_FILE = OUTPUT_DIR + "av_vacc_exposed_no_delay.txt";
	public static final String AV_VACC_INFECTIOUS_NO_DELAY_FILE = OUTPUT_DIR + "av_vacc_infectious_no_delay.txt";
	public static final String AV_VACC_RECOVERED_NO_DELAY_FILE = OUTPUT_DIR + "av_vacc_recovered_no_delay.txt";
	public static final String AV_VACC_NEWLY_EXPOSED_NO_DELAY_FILE = OUTPUT_DIR + "av_vacc_newly_exposed_no_delay.txt";
	public static final String AV_VACC_NEWLY_INFECTIOUS_NO_DELAY_FILE = OUTPUT_DIR + "av_vacc_newly_infectious_no_delay.txt";
	public static final String AV_VACC_ANTIVIRALS_GIVEN_NO_DELAY_FILE = OUTPUT_DIR + "av_vacc_antivirals_given_no_delay.txt";
	public static final String AV_VACC_VACCINES_GIVEN_NO_DELAY_FILE = OUTPUT_DIR + "av_vacc_vaccines_given_no_delay.txt";

	public TestSEIR(String testName) {
		super(testName);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	// TODO add test methods here. The name must begin with 'test'. For example:
	// public void testHello() {}
	public void testNoControlMeasures() throws FileNotFoundException {

		SeirModelInput input = getDefaultInput();

		SeirModelOutput output = SeirModel.runSeirModel(input);
		checkSusceptible(output, NO_CM_SUSCEPTIBLE_FILE);
		checkExposed(output, NO_CM_EXPOSED_FILE);
		checkInfectious(output, NO_CM_INFECTIOUS_FILE);
		checkRecovered(output, NO_CM_RECOVERED_FILE);
		checkNewlyExposed(output, NO_CM_NEWLY_EXPOSED_FILE);
		checkNewlyInfectious(output, NO_CM_NEWLY_INFECTIOUS_FILE);
	}

	public void testVaccControlMeasure() throws FileNotFoundException {
		SeirModelInput input = getDefaultInput();

		int[] vaccSupplySchedule = new int[input.getRunLength()];
		int[] vaccAdminSchedule = new int[input.getRunLength()];
		for (int i = 0; i < 50; i++) {
			vaccSupplySchedule[i] = 2000;
			vaccAdminSchedule[i] = 1500;
		}
		for (int i = 50; i < 100; i++) {
			vaccSupplySchedule[i] = 10000;
			vaccAdminSchedule[i] = 6500;
		}
		input.setVaccineAdminSchedule(vaccAdminSchedule);
		input.setVaccineSupplySchedule(vaccSupplySchedule);
		input.setVaccineCompliance(0.9);
		input.setVaccineEfficacy(0.8);
		input.setVaccineEfficacyDelay(3d);

		SeirModelOutput output = SeirModel.runSeirModel(input);

		checkSusceptible(output, VACC_SUSCEPTIBLE_FILE);
		checkExposed(output, VACC_EXPOSED_FILE);
		checkInfectious(output, VACC_INFECTIOUS_FILE);
		checkRecovered(output, VACC_RECOVERED_FILE);
		checkNewlyExposed(output, VACC_NEWLY_EXPOSED_FILE);
		checkNewlyInfectious(output, VACC_NEWLY_INFECTIOUS_FILE);
		checkVaccinesGiven(output, VACC_VACCINES_GIVEN_FILE);
	}

	public void testVaccNoDelayControlMeasure() throws FileNotFoundException {
		SeirModelInput input = getDefaultInput();

		int[] vaccSupplySchedule = new int[input.getRunLength()];
		int[] vaccAdminSchedule = new int[input.getRunLength()];
		for (int i = 0; i < 50; i++) {
			vaccSupplySchedule[i] = 2000;
			vaccAdminSchedule[i] = 1500;
		}
		for (int i = 50; i < 100; i++) {
			vaccSupplySchedule[i] = 10000;
			vaccAdminSchedule[i] = 6500;
		}
		input.setVaccineAdminSchedule(vaccAdminSchedule);
		input.setVaccineSupplySchedule(vaccSupplySchedule);
		input.setVaccineCompliance(0.9);
		input.setVaccineEfficacy(0.8);
		input.setVaccineEfficacyDelay(0d);

		SeirModelOutput output = SeirModel.runSeirModel(input);

		checkSusceptible(output, VACC_SUSCEPTIBLE_NO_DELAY_FILE);
		checkExposed(output, VACC_EXPOSED_NO_DELAY_FILE);
		checkInfectious(output, VACC_INFECTIOUS_NO_DELAY_FILE);
		checkRecovered(output, VACC_RECOVERED_NO_DELAY_FILE);
		checkNewlyExposed(output, VACC_NEWLY_EXPOSED_NO_DELAY_FILE);
		checkNewlyInfectious(output, VACC_NEWLY_INFECTIOUS_NO_DELAY_FILE);
		checkVaccinesGiven(output, VACC_VACCINES_GIVEN_NO_DELAY_FILE);
	}

	public void testAntiviralControlMeasure() throws FileNotFoundException {
		SeirModelInput input = getDefaultInput();

		int[] avSupplySchedule = new int[input.getRunLength()];
		int[] avAdminSchedule = new int[input.getRunLength()];
		for (int i = 0; i < 50; i++) {
			avSupplySchedule[i] = 1000;
			avAdminSchedule[i] = 500;
		}
		for (int i = 50; i < 100; i++) {
			avSupplySchedule[i] = 5000;
			avAdminSchedule[i] = 1250;
		}
		input.setAntiviralAdminSchedule(avAdminSchedule);
		input.setAntiviralSupplySchedule(avSupplySchedule);
		input.setAntiviralCompliance(0.95);
		input.setAntiviralEfficacy(0.9);
		input.setAntiviralEfficacyDelay(1d);

		SeirModelOutput output = SeirModel.runSeirModel(input);

		checkSusceptible(output, AV_SUSCEPTIBLE_FILE);
		checkExposed(output, AV_EXPOSED_FILE);
		checkInfectious(output, AV_INFECTIOUS_FILE);
		checkRecovered(output, AV_RECOVERED_FILE);
		checkNewlyExposed(output, AV_NEWLY_EXPOSED_FILE);
		checkNewlyInfectious(output, AV_NEWLY_INFECTIOUS_FILE);
		checkAntiviralsGiven(output, AV_ANTIVIRALS_GIVEN_FILE);
	}

	public void testAntiviralNoDelayControlMeasure() throws FileNotFoundException {
		SeirModelInput input = getDefaultInput();

		int[] avSupplySchedule = new int[input.getRunLength()];
		int[] avAdminSchedule = new int[input.getRunLength()];
		for (int i = 0; i < 50; i++) {
			avSupplySchedule[i] = 1000;
			avAdminSchedule[i] = 500;
		}
		for (int i = 50; i < 100; i++) {
			avSupplySchedule[i] = 5000;
			avAdminSchedule[i] = 1250;
		}
		input.setAntiviralAdminSchedule(avAdminSchedule);
		input.setAntiviralSupplySchedule(avSupplySchedule);
		input.setAntiviralCompliance(0.95);
		input.setAntiviralEfficacy(0.9);
		input.setAntiviralEfficacyDelay(0d);

		SeirModelOutput output = SeirModel.runSeirModel(input);

		checkSusceptible(output, AV_SUSCEPTIBLE_NO_DELAY_FILE);
		checkExposed(output, AV_EXPOSED_NO_DELAY_FILE);
		checkInfectious(output, AV_INFECTIOUS_NO_DELAY_FILE);
		checkRecovered(output, AV_RECOVERED_NO_DELAY_FILE);
		checkNewlyExposed(output, AV_NEWLY_EXPOSED_NO_DELAY_FILE);
		checkNewlyInfectious(output, AV_NEWLY_INFECTIOUS_NO_DELAY_FILE);
		checkAntiviralsGiven(output, AV_ANTIVIRALS_GIVEN_NO_DELAY_FILE);
	}

	public void testAntiviralAndVaccControlMeasures() throws FileNotFoundException {
		SeirModelInput input = getDefaultInput();

		int[] avSupplySchedule = new int[input.getRunLength()];
		int[] avAdminSchedule = new int[input.getRunLength()];
		for (int i = 0; i < 50; i++) {
			avSupplySchedule[i] = 1000;
			avAdminSchedule[i] = 500;
		}
		for (int i = 50; i < 100; i++) {
			avSupplySchedule[i] = 5000;
			avAdminSchedule[i] = 1250;
		}
		input.setAntiviralAdminSchedule(avAdminSchedule);
		input.setAntiviralSupplySchedule(avSupplySchedule);
		input.setAntiviralCompliance(0.95);
		input.setAntiviralEfficacy(0.9);
		input.setAntiviralEfficacyDelay(1d);

		int[] vaccSupplySchedule = new int[input.getRunLength()];
		int[] vaccAdminSchedule = new int[input.getRunLength()];
		for (int i = 0; i < 50; i++) {
			vaccSupplySchedule[i] = 2000;
			vaccAdminSchedule[i] = 1500;
		}
		for (int i = 50; i < 100; i++) {
			vaccSupplySchedule[i] = 10000;
			vaccAdminSchedule[i] = 6500;
		}
		input.setVaccineAdminSchedule(vaccAdminSchedule);
		input.setVaccineSupplySchedule(vaccSupplySchedule);
		input.setVaccineCompliance(0.9);
		input.setVaccineEfficacy(0.8);
		input.setVaccineEfficacyDelay(3d);

		SeirModelOutput output = SeirModel.runSeirModel(input);

		checkSusceptible(output, AV_VACC_SUSCEPTIBLE_FILE);
		checkExposed(output, AV_VACC_EXPOSED_FILE);
		checkInfectious(output, AV_VACC_INFECTIOUS_FILE);
		checkRecovered(output, AV_VACC_RECOVERED_FILE);
		checkNewlyExposed(output, AV_VACC_NEWLY_EXPOSED_FILE);
		checkNewlyInfectious(output, AV_VACC_NEWLY_INFECTIOUS_FILE);
		checkAntiviralsGiven(output, AV_VACC_ANTIVIRALS_GIVEN_FILE);
		checkVaccinesGiven(output, AV_VACC_VACCINES_GIVEN_FILE);
	}

	public void testAntiviralAndVaccNoDelayControlMeasures() throws FileNotFoundException {
		SeirModelInput input = getDefaultInput();

		int[] avSupplySchedule = new int[input.getRunLength()];
		int[] avAdminSchedule = new int[input.getRunLength()];
		for (int i = 0; i < 50; i++) {
			avSupplySchedule[i] = 1000;
			avAdminSchedule[i] = 500;
		}
		for (int i = 50; i < 100; i++) {
			avSupplySchedule[i] = 5000;
			avAdminSchedule[i] = 1250;
		}
		input.setAntiviralAdminSchedule(avAdminSchedule);
		input.setAntiviralSupplySchedule(avSupplySchedule);
		input.setAntiviralCompliance(0.95);
		input.setAntiviralEfficacy(0.9);
		input.setAntiviralEfficacyDelay(0d);

		int[] vaccSupplySchedule = new int[input.getRunLength()];
		int[] vaccAdminSchedule = new int[input.getRunLength()];
		for (int i = 0; i < 50; i++) {
			vaccSupplySchedule[i] = 2000;
			vaccAdminSchedule[i] = 1500;
		}
		for (int i = 50; i < 100; i++) {
			vaccSupplySchedule[i] = 10000;
			vaccAdminSchedule[i] = 6500;
		}
		input.setVaccineAdminSchedule(vaccAdminSchedule);
		input.setVaccineSupplySchedule(vaccSupplySchedule);
		input.setVaccineCompliance(0.9);
		input.setVaccineEfficacy(0.8);
		input.setVaccineEfficacyDelay(0d);

		SeirModelOutput output = SeirModel.runSeirModel(input);

		checkSusceptible(output, AV_VACC_SUSCEPTIBLE_NO_DELAY_FILE);
		checkExposed(output, AV_VACC_EXPOSED_NO_DELAY_FILE);
		checkInfectious(output, AV_VACC_INFECTIOUS_NO_DELAY_FILE);
		checkRecovered(output, AV_VACC_RECOVERED_NO_DELAY_FILE);
		checkNewlyExposed(output, AV_VACC_NEWLY_EXPOSED_NO_DELAY_FILE);
		checkNewlyInfectious(output, AV_VACC_NEWLY_INFECTIOUS_NO_DELAY_FILE);
		checkAntiviralsGiven(output, AV_VACC_ANTIVIRALS_GIVEN_NO_DELAY_FILE);
		checkVaccinesGiven(output, AV_VACC_VACCINES_GIVEN_NO_DELAY_FILE);
	}

	private SeirModelInput getDefaultInput() {
		SeirModelInput input = new SeirModelInput();
		input.setAsymptomaticInfectionFraction(0.33);
		input.setInfectiousPeriod(6);
		input.setLatentPeriod(2);
		input.setReproductionNumber(1.3);
		input.setInitialSusceptible(1000000);
		input.setInitialExposed(100);
		input.setInitialInfectious(300);
		input.setInitialRecovered(10000);
		input.setRunLength(150);

		return input;
	}

	private void checkSusceptible(SeirModelOutput output, String susceptibleFile) throws FileNotFoundException {
		checkOutput(arraySum(output.getSusceptibleNotVaccineCompliant(),
				output.getSusceptibleVaccineCompliantNotVaccinated(),
				output.getExistingSusceptibleExistingVaccinatedFailed(),
				output.getExistingSusceptibleExistingVaccinatedSuccess(),
				output.getExistingSusceptibleNewlyVaccinatedFailed(),
				output.getExistingSusceptibleNewlyVaccinatedSuccess()), susceptibleFile);
	}

	private void checkExposed(SeirModelOutput output, String exposedFile) throws FileNotFoundException {
		checkOutput(arraySum(output.getNewlyExposedNotVaccinated(),
				output.getNewlyExposedVaccinatedFailed(),
				output.getExistingExposedExistingVaccinatedFailed(),
				output.getExistingExposedNewlyVaccinatedFailed(),
				output.getExistingExposedNotVaccinated()), exposedFile);
	}

	private void checkInfectious(SeirModelOutput output, String infectiousFile) throws FileNotFoundException {

		checkOutput(arraySum(output.getNewlyInfectiousNotVaccinatedNotAntiviraledAsymptomatic(),
				output.getNewlyInfectiousNotVaccinatedNotAntiviraledSymptomatic(),
				output.getNewlyInfectiousVaccinatedFailedNotAntiviraledAsymptomatic(),
				output.getNewlyInfectiousVaccinatedFailedNotAntiviraledSymptomatic(),
				output.getExistingInfectiousExistingVaccinatedFailedNotAntiviraledAsymptomatic(),
				output.getExistingInfectiousNewlyVaccinatedFailedNotAntiviraledAsymptomatic(),
				output.getExistingInfectiousNotVaccinatedExistingAntiviraledFailedSymptomatic(),
				output.getExistingInfectiousNotVaccinatedExistingAntiviraledSuccessSymptomatic(),
				output.getExistingInfectiousNotVaccinatedNewlyAntiviraledFailedSymptomatic(),
				output.getExistingInfectiousNotVaccinatedNewlyAntiviraledSuccessSymptomatic(),
				output.getExistingInfectiousNotVaccinatedNotAntiviraledAsymptomatic(),
				output.getExistingInfectiousNotVaccinatedNotAntiviraledSymptomatic(),
				output.getExistingInfectiousVaccinatedExistingAntiviraledFailedSymptomatic(),
				output.getExistingInfectiousVaccinatedExistingAntiviraledSuccessSymptomatic(),
				output.getExistingInfectiousVaccinatedFailedNotAntiviraledSymptomatic(),
				output.getExistingInfectiousVaccinatedNewlyAntiviraledFailedSymptomatic(),
				output.getExistingInfectiousVaccinatedNewlyAntiviraledSuccessSymptomatic()), infectiousFile);

	}

	private void checkRecovered(SeirModelOutput output, String recoveredFile) throws FileNotFoundException {

		checkOutput(arraySum(output.getRecoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledAsymptomatic(),
				output.getRecoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledSymptomatic(),
				output.getRecoveredNotVaccineCompliantAntiviralFailedSymptomatic(),
				//				output.getRecoveredNotVaccineCompliantAntiviralSuccessSymptomatic(),
				output.getNewlyRecoveredNotVaccineCompliantNewlyAntiviraledSuccessSymptomatic(),
				output.getExistingRecoveredNotVaccineCompliantExistingAntiviraledSuccessSymptomatic(),
				output.getRecoveredNotVaccineCompliantNotAntiviralCompliantAsymptomatic(),
				output.getRecoveredNotVaccineCompliantNotAntiviralCompliantSymptomatic(),
				output.getRecoveredVaccinatedFailedAntiviralCompliantNotAntiviraledSymptomatic(),
				output.getRecoveredVaccinatedFailedAntiviraledFailedSymptomatic(),
				//				output.getRecoveredVaccinatedFailedAntiviraledSuccessSymptomatic(),
				output.getNewlyRecoveredVaccinatedFailedNewlyAntiviraledSuccessSymptomatic(),
				output.getExistingRecoveredVaccinatedFailedExistingAntiviraledSuccessSymptomatic(),
				output.getRecoveredVaccinatedFailedNotAntiviralCompliantSymptomatic(),
				output.getNewlyRecoveredNewlyVaccinatedSuccess(),
				output.getExistingRecoveredExistingVaccinatedSuccess(),
				output.getRecoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledAsymptomatic(),
				output.getRecoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledSymptomatic(),
				output.getRecoveredVaccineCompliantNotVaccinatedAntiviraledFailedSymptomatic(),
				//				output.getRecoveredVaccineCompliantNotVaccinatedAntiviraledSuccessSymptomatic(),
				output.getNewlyRecoveredVaccineCompliantNotVaccinatedNewlyAntiviraledSuccessSymptomatic(),
				output.getExistingRecoveredVaccineCompliantNotVaccinatedExistingAntiviraledSuccessSymptomatic(),
				output.getRecoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantAsymptomatic(),
				output.getRecoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantSymptomatic(),
				output.getExistingRecoveredExistingVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic(),
				output.getExistingRecoveredExistingVaccinatedFailedNotAntiviralCompliantAsymptomatic(),
				output.getExistingRecoveredNewlyVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic(),
				output.getExistingRecoveredNewlyVaccinatedFailedNotAntiviralCompliantAsymptomatic()), recoveredFile);
	}

	private void checkNewlyExposed(SeirModelOutput output, String newlyExposedFile) throws FileNotFoundException {

		checkOutput(arraySum(output.getNewlyExposedNotVaccinated(),
				output.getNewlyExposedVaccinatedFailed()), newlyExposedFile);
	}

	private void checkNewlyInfectious(SeirModelOutput output, String newlyInfectiousFile) throws FileNotFoundException {

		checkOutput(arraySum(output.getNewlyInfectiousNotVaccinatedNotAntiviraledAsymptomatic(),
				output.getNewlyInfectiousNotVaccinatedNotAntiviraledSymptomatic(),
				output.getNewlyInfectiousVaccinatedFailedNotAntiviraledAsymptomatic(),
				output.getNewlyInfectiousVaccinatedFailedNotAntiviraledSymptomatic()), newlyInfectiousFile);
	}

	private void checkVaccinesGiven(SeirModelOutput output, String vaccineFile) throws FileNotFoundException {

		checkOutput(arraySum(output.getExistingSusceptibleNewlyVaccinatedFailed(),
				output.getExistingSusceptibleNewlyVaccinatedSuccess(),
				output.getExistingExposedNewlyVaccinatedFailed(),
				output.getExistingInfectiousNewlyVaccinatedFailedNotAntiviraledAsymptomatic(),
				output.getExistingRecoveredNewlyVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic(),
				output.getExistingRecoveredNewlyVaccinatedFailedNotAntiviralCompliantAsymptomatic(),
				output.getNewlyRecoveredNewlyVaccinatedSuccess()), vaccineFile);
	}

	private void checkAntiviralsGiven(SeirModelOutput output, String antiviralFile) throws FileNotFoundException {

		checkOutput(arraySum(output.getExistingInfectiousNotVaccinatedNewlyAntiviraledFailedSymptomatic(),
				output.getExistingInfectiousNotVaccinatedNewlyAntiviraledSuccessSymptomatic(),
				output.getExistingInfectiousVaccinatedNewlyAntiviraledFailedSymptomatic(),
				output.getExistingInfectiousVaccinatedNewlyAntiviraledSuccessSymptomatic(),
				output.getNewlyRecoveredNotVaccineCompliantNewlyAntiviraledSuccessSymptomatic(),
				output.getNewlyRecoveredVaccinatedFailedNewlyAntiviraledSuccessSymptomatic(),
				output.getNewlyRecoveredVaccineCompliantNotVaccinatedNewlyAntiviraledSuccessSymptomatic()), antiviralFile);

	}

	private void checkOutput(double[] output, String outputFileName) throws FileNotFoundException {

		List<Double> validOutput = readStoredOutput(outputFileName);
		Iterator<Double> iter = validOutput.iterator();
		int i = 0;
		while (iter.hasNext()) {
			double val = iter.next();
			if (Math.abs(output[i] - val) > THRESHOLD) {
				fail("Value " + output[i] + "  does not match " + val);
			}
			i++;
		}
	}

	private double[] arraySum(double[]... arrays) {
		double[] newArray = new double[arrays[0].length]; // assumes all lengths are equal
		for (double[] array : arrays) {
			for (int i = 0; i < array.length; i++) {
				newArray[i] += array[i];
			}
		}

		return newArray;
	}

	private List<Double> readStoredOutput(String fileName) throws FileNotFoundException {

		List<Double> list = new LinkedList<Double>();
		Scanner scanner = new Scanner(new File(fileName));
		scanner.useDelimiter(",");
		while (scanner.hasNextDouble()) {
			list.add(scanner.nextDouble());
		}

		return list;
	}
}
