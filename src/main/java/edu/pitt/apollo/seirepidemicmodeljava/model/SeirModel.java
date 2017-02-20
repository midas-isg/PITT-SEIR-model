package edu.pitt.apollo.seirepidemicmodeljava.model;

import edu.pitt.apollo.seirepidemicmodeljava.types.SeirModelOutput;
import edu.pitt.apollo.seirepidemicmodeljava.types.SeirModelInput;

/**
 * Author: Nick Millett Email: nick.millett@gmail.com Date: Mar 1, 2013 Time: 12:27:58 PM Class: SeirModel IDE: NetBeans 6.9.1
 */
public class SeirModel {

	public static void main(String[] args) {

		int runLength = 365;
		int population = 1223348;

		SeirModelInput input = new SeirModelInput();
		input.setAntiviralAdminSchedule(new int[runLength]);
		input.setAntiviralSupplySchedule(new int[runLength]);
		input.setAntiviralCompliance(0.0);
		input.setAntiviralEfficacy(0.6);
		input.setAntiviralEfficacyDelay(2.0);
		input.setAsymptomaticInfectionFraction(0.33);
		input.setInfectiousPeriod(6.0);
		input.setInitialExposed(0);
		input.setInitialInfectious(0.001 * population);
		input.setInitialRecovered(0.1 * population);
		input.setInitialSusceptible(0.899 * population);
		input.setLatentPeriod(2.0);
		input.setReproductionNumber(1.4);
		input.setRunLength(runLength);
		input.setVaccineCompliance(0.9);
		input.setVaccineEfficacy(0.47);
		input.setVaccineEfficacyDelay(2.0);

		int[] vaccSupplySchedule = new int[runLength];
		vaccSupplySchedule[0] = 500000000;

		int[] vaccAdminSchedule = new int[runLength];
		for (int i = 61; i < runLength; i++) {
			vaccAdminSchedule[i] = (int) (population * 0.00002);
		}

		input.setVaccineAdminSchedule(vaccAdminSchedule);
		input.setVaccineSupplySchedule(vaccSupplySchedule);
		
		runSeirModel(input);
	}

	public static SeirModelOutput runSeirModel(SeirModelInput input) {

		// these parameters do not depend on pre epidemic vaccination
		double symRate = 1 - input.getAsymptomaticInfectionFraction();
		double vaccineCompliance = input.getVaccineCompliance();
		double antiviralCompliance = input.getAntiviralCompliance();
		double r0 = input.getReproductionNumber();
		double infectiousPeriod = input.getInfectiousPeriod();
		double latentPeriod = input.getLatentPeriod();
		double vaccEff = input.getVaccineEfficacy();
		double vaccEffDelay = input.getVaccineEfficacyDelay();
		double antiviralEff = input.getAntiviralEfficacy();
		double antiviralEffDelay = input.getAntiviralEfficacyDelay();
		int len = input.getRunLength();
		double initialExposed = input.getInitialExposed();
		double initialInfectious = input.getInitialInfectious();

		// create arrays for the compartments
		// susceptible
		double[] susceptibleNotVaccineCompliant = new double[len];
		double[] susceptibleVaccineCompliantNotVaccinated = new double[len];
		double[] susceptibleVaccinatedSuccess = new double[len];
		double[] susceptibleVaccinatedFailed = new double[len];
		// susceptible vaccination incidence
		double[] existingSusceptibleNewlyVaccinatedSuccess = new double[len];
		double[] existingSusceptibleNewlyVaccinatedFailed = new double[len];
		double[] existingSusceptibleExistingVaccinatedSuccess = new double[len];
		double[] existingSusceptibleExistingVaccinatedFailed = new double[len];
		// exposed
		double[] exposedNotVaccineCompliant = new double[len];
		double[] exposedVaccineCompliantNotVaccinated = new double[len];
		double[] exposedVaccinatedFailed = new double[len];
		// exposed incidence
		double[] newlyExposedNotVaccinated = new double[len];
		double[] existingExposedNotVaccinated = new double[len];
		double[] newlyExposedVaccinatedFailed = new double[len];
		double[] existingExposedNewlyVaccinatedFailed = new double[len];
		double[] existingExposedExistingVaccinatedFailed = new double[len];
		// infectious
		double[] infectiousNotVaccineCompliantNotAntiviralCompliantAsymptomatic = new double[len];
		double[] infectiousNotVaccineCompliantNotAntiviralCompliantSymptomatic = new double[len];
		double[] infectiousNotVaccineCompliantAntiviralCompliantNotAntiviraledAsymptomatic = new double[len];
		double[] infectiousNotVaccineCompliantAntiviralCompliantNotAntiviraledSymptomatic = new double[len];
		double[] infectiousNotVaccineCompliantAntiviraledSymptomaticSuccess = new double[len];
		double[] infectiousNotVaccineCompliantAntiviraledSymptomaticFailed = new double[len];
		double[] infectiousVaccineCompliantNotVaccinatedNotAntiviralCompliantAsymptomatic = new double[len];
		double[] infectiousVaccineCompliantNotVaccinatedNotAntiviralCompliantSymptomatic = new double[len];
		double[] infectiousVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledAsymptomatic = new double[len];
		double[] infectiousVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledSymptomatic = new double[len];
		double[] infectiousVaccineCompliantNotVaccinatedAntiviraledSymptomaticSuccess = new double[len];
		double[] infectiousVaccineCompliantNotVaccinatedAntiviraledSymptomaticFailed = new double[len];
		double[] infectiousVaccinatedFailedNotAntiviralCompliantAsymptomatic = new double[len];
		double[] infectiousVaccinatedFailedNotAntiviralCompliantSymptomatic = new double[len];
		double[] infectiousVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic = new double[len];
		double[] infectiousVaccinatedFailedAntiviralCompliantNotAntiviraledSymptomatic = new double[len];
		double[] infectiousVaccinatedFailedAntiviraledSymptomaticSuccess = new double[len];
		double[] infectiousVaccinatedFailedAntiviraledSymptomaticFailed = new double[len];
		// infectious incidence
		// not treated
		double[] newlyInfectiousNotVaccinatedNotAntiviraledSymptomatic = new double[len];
		double[] existingInfectiousNotVaccinatedNotAntiviraledSymptomatic = new double[len];
		double[] newlyInfectiousNotVaccinatedNotAntiviraledAsymptomatic = new double[len];
		double[] existingInfectiousNotVaccinatedNotAntiviraledAsymptomatic = new double[len];
		// antiviraled
		double[] existingInfectiousNotVaccinatedNewlyAntiviraledSuccessSymptomatic = new double[len];
		double[] existingInfectiousNotVaccinatedNewlyAntiviraledFailedSymptomatic = new double[len];
		double[] existingInfectiousNotVaccinatedExistingAntiviraledSuccessSymptomatic = new double[len];
		double[] existingInfectiousNotVaccinatedExistingAntiviraledFailedSymptomatic = new double[len];
		// vaccinated
		double[] newlyInfectiousVaccinatedFailedNotAntiviraledSymptomatic = new double[len];
		double[] existingInfectiousVaccinatedFailedNotAntiviraledSymptomatic = new double[len];
		double[] newlyInfectiousVaccinatedFailedNotAntiviraledAsymptomatic = new double[len];
		double[] existingInfectiousNewlyVaccinatedFailedNotAntiviraledAsymptomatic = new double[len];
		double[] existingInfectiousExistingVaccinatedFailedNotAntiviraledAsymptomatic = new double[len];
		// antiviraled, vaccinated
		double[] existingInfectiousVaccinatedNewlyAntiviraledSuccessSymptomatic = new double[len];
		double[] existingInfectiousVaccinatedNewlyAntiviraledFailedSymptomatic = new double[len];
		double[] existingInfectiousVaccinatedExistingAntiviraledSuccessSymptomatic = new double[len];
		double[] existingInfectiousVaccinatedExistingAntiviraledFailedSymptomatic = new double[len];
		// recovered
		double[] recoveredNotVaccineCompliantNotAntiviralCompliantAsymptomatic = new double[len];
		double[] recoveredNotVaccineCompliantNotAntiviralCompliantSymptomatic = new double[len];
		double[] recoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledAsymptomatic = new double[len];
		double[] recoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledSymptomatic = new double[len];

		double[] recoveredNotVaccineCompliantAntiviralSuccessSymptomatic = new double[len];
		double[] newlyRecoveredNotVaccineCompliantNewlyAntiviraledSuccessSymptomatic = new double[len];
		double[] existingRecoveredNotVaccineCompliantExistingAntiviraledSuccessSymptomatic = new double[len];

		double[] recoveredNotVaccineCompliantAntiviralFailedSymptomatic = new double[len];
		double[] recoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantAsymptomatic = new double[len];
		double[] recoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantSymptomatic = new double[len];
		double[] recoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledAsymptomatic = new double[len];
		double[] recoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledSymptomatic = new double[len];

		double[] recoveredVaccineCompliantNotVaccinatedAntiviraledSuccessSymptomatic = new double[len];
		double[] newlyRecoveredVaccineCompliantNotVaccinatedNewlyAntiviraledSuccessSymptomatic = new double[len];
		double[] existingRecoveredVaccineCompliantNotVaccinatedExistingAntiviraledSuccessSymptomatic = new double[len];

		double[] recoveredVaccineCompliantNotVaccinatedAntiviraledFailedSymptomatic = new double[len];
		double[] recoveredVaccinatedFailedNotAntiviralCompliantAsymptomatic = new double[len];
		double[] recoveredVaccinatedFailedNotAntiviralCompliantSymptomatic = new double[len];
		double[] recoveredVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic = new double[len];
		double[] recoveredVaccinatedFailedAntiviralCompliantNotAntiviraledSymptomatic = new double[len];

		double[] recoveredVaccinatedFailedAntiviraledSuccessSymptomatic = new double[len];
		double[] newlyRecoveredVaccinatedFailedNewlyAntiviraledSuccessSymptomatic = new double[len];
		double[] existingRecoveredVaccinatedFailedExistingAntiviraledSuccessSymptomatic = new double[len];

		double[] recoveredVaccinatedFailedAntiviraledFailedSymptomatic = new double[len];
		double[] recoveredVaccinatedSuccess = new double[len];
		// recovered vaccine incidence
		double[] existingRecoveredNewlyVaccinatedFailedNotAntiviralCompliantAsymptomatic = new double[len];
		double[] existingRecoveredExistingVaccinatedFailedNotAntiviralCompliantAsymptomatic = new double[len];

		double[] existingRecoveredNewlyVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic = new double[len];
		double[] existingRecoveredExistingVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic = new double[len];
		double[] newlyRecoveredNewlyVaccinatedSuccess = new double[len];
		double[] existingRecoveredExistingVaccinatedSuccess = new double[len];

		// these compartments don't depend on whether there was preemptive vaccination
		exposedVaccineCompliantNotVaccinated[0] = initialExposed * vaccineCompliance;
		exposedNotVaccineCompliant[0] = initialExposed * (1 - vaccineCompliance);
		exposedVaccinatedFailed[0] = 0.0;

		existingExposedNotVaccinated[0] = initialExposed;
		existingExposedNewlyVaccinatedFailed[0] = 0.0;
		existingExposedExistingVaccinatedFailed[0] = 0.0;

		// infectious not vaccine compliant
		infectiousNotVaccineCompliantNotAntiviralCompliantSymptomatic[0] = initialInfectious * symRate
				* (1 - vaccineCompliance) * (1 - antiviralCompliance);
		infectiousNotVaccineCompliantNotAntiviralCompliantAsymptomatic[0] = initialInfectious * (1 - symRate)
				* (1 - vaccineCompliance) * (1 - antiviralCompliance);
		infectiousNotVaccineCompliantAntiviralCompliantNotAntiviraledSymptomatic[0] = initialInfectious * symRate
				* (1 - vaccineCompliance) * antiviralCompliance;
		infectiousNotVaccineCompliantAntiviralCompliantNotAntiviraledAsymptomatic[0] = initialInfectious * (1 - symRate)
				* (1 - vaccineCompliance) * antiviralCompliance;
		infectiousNotVaccineCompliantAntiviraledSymptomaticSuccess[0] = 0.0;
		infectiousNotVaccineCompliantAntiviraledSymptomaticFailed[0] = 0.0;

		// infectious vaccine compliant
		infectiousVaccineCompliantNotVaccinatedNotAntiviralCompliantSymptomatic[0] = initialInfectious * symRate
				* vaccineCompliance * (1 - antiviralCompliance);
		infectiousVaccineCompliantNotVaccinatedNotAntiviralCompliantAsymptomatic[0] = initialInfectious * (1 - symRate)
				* vaccineCompliance * (1 - antiviralCompliance);
		infectiousVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledSymptomatic[0] = initialInfectious * symRate
				* vaccineCompliance * antiviralCompliance;
		infectiousVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledAsymptomatic[0] = initialInfectious * (1 - symRate)
				* vaccineCompliance * antiviralCompliance;
		infectiousVaccineCompliantNotVaccinatedAntiviraledSymptomaticSuccess[0] = 0.0;
		infectiousVaccineCompliantNotVaccinatedAntiviraledSymptomaticFailed[0] = 0.0;

		// infectious vaccinated
		infectiousVaccinatedFailedNotAntiviralCompliantSymptomatic[0] = 0.0;
		infectiousVaccinatedFailedNotAntiviralCompliantAsymptomatic[0] = 0.0;
		infectiousVaccinatedFailedAntiviralCompliantNotAntiviraledSymptomatic[0] = 0.0;
		infectiousVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic[0] = 0.0;
		infectiousVaccinatedFailedAntiviraledSymptomaticSuccess[0] = 0.0;
		infectiousVaccinatedFailedAntiviraledSymptomaticFailed[0] = 0.0;

		existingInfectiousNotVaccinatedNewlyAntiviraledSuccessSymptomatic[0] = 0.0;
		existingInfectiousNotVaccinatedNewlyAntiviraledFailedSymptomatic[0] = 0.0;

		existingInfectiousNotVaccinatedExistingAntiviraledSuccessSymptomatic[0] = 0.0;
		existingInfectiousNotVaccinatedExistingAntiviraledFailedSymptomatic[0] = 0.0;

		existingInfectiousNotVaccinatedNotAntiviraledAsymptomatic[0] = initialInfectious * (1 - symRate);
		existingInfectiousNotVaccinatedNotAntiviraledSymptomatic[0] = initialInfectious * symRate;
		existingInfectiousVaccinatedNewlyAntiviraledSuccessSymptomatic[0] = 0.0;
		existingInfectiousVaccinatedNewlyAntiviraledFailedSymptomatic[0] = 0.0;
		existingInfectiousNewlyVaccinatedFailedNotAntiviraledAsymptomatic[0] = 0.0;
		existingInfectiousExistingVaccinatedFailedNotAntiviraledAsymptomatic[0] = 0.0;
		existingInfectiousVaccinatedFailedNotAntiviraledSymptomatic[0] = 0.0;

		double initialSusceptible = input.getInitialSusceptible();
		double initialRecovered = input.getInitialRecovered();

		// get the total amount of susceptible people that could be vaccinated
		double susceptibleCompliantWithVaccination = initialSusceptible * vaccineCompliance;
		// everyone not compliant will go in suscpetibleNrv
		susceptibleNotVaccineCompliant[0] = initialSusceptible - susceptibleCompliantWithVaccination;

		// take the amount already vaccinated out of the number compliant with vaccination
		susceptibleVaccineCompliantNotVaccinated[0] = susceptibleCompliantWithVaccination;
		susceptibleVaccinatedSuccess[0] = 0.0;
		susceptibleVaccinatedFailed[0] = 0.0;

		// initial recovered is assumed to be residual immunity
		// so no one in initial recovered will know they are immune
		// hence, would be receptive to vaccination if compliant
		recoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledAsymptomatic[0]
				= initialRecovered * (1 - vaccineCompliance) * antiviralCompliance;
		recoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledSymptomatic[0]
				= 0.0;
		recoveredNotVaccineCompliantAntiviralFailedSymptomatic[0] = 0.0;
		recoveredNotVaccineCompliantAntiviralSuccessSymptomatic[0] = 0.0;

		recoveredNotVaccineCompliantNotAntiviralCompliantAsymptomatic[0]
				= initialRecovered * (1 - vaccineCompliance) * (1 - antiviralCompliance);
		recoveredNotVaccineCompliantNotAntiviralCompliantSymptomatic[0]
				= 0.0;

		recoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledAsymptomatic[0]
				= initialRecovered * vaccineCompliance * antiviralCompliance;
		recoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledSymptomatic[0]
				= 0.0;
		recoveredVaccineCompliantNotVaccinatedAntiviraledFailedSymptomatic[0] = 0.0;
		recoveredVaccineCompliantNotVaccinatedAntiviraledSuccessSymptomatic[0] = 0.0;
		recoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantAsymptomatic[0]
				= initialRecovered * vaccineCompliance * (1 - antiviralCompliance);
		recoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantSymptomatic[0]
				= 0.0;

		recoveredVaccinatedSuccess[0] = 0.0;
		recoveredVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic[0] = 0.0;
		recoveredVaccinatedFailedAntiviralCompliantNotAntiviraledSymptomatic[0] = 0.0;
		recoveredVaccinatedFailedAntiviraledFailedSymptomatic[0] = 0.0;
		recoveredVaccinatedFailedAntiviraledSuccessSymptomatic[0] = 0.0;
		recoveredVaccinatedFailedNotAntiviralCompliantAsymptomatic[0] = 0.0;
		recoveredVaccinatedFailedNotAntiviralCompliantSymptomatic[0] = 0.0;

		double totalPop = initialExposed + initialInfectious + input.getInitialSusceptible() + input.getInitialRecovered();

		// schedules
		int[] vaccAdminSchedule = input.getVaccineAdminSchedule();
		int[] vaccSupplySchedule = input.getVaccineSupplySchedule();
		int[] antiviralAdminSchedule = input.getAntiviralAdminSchedule();
		int[] antiviralSupplySchedule = input.getAntiviralSupplySchedule();

		// check for parameters that are less than the time step
		if (infectiousPeriod
				< 1) {
			infectiousPeriod = 1;
		}
		if (latentPeriod
				< 1) {
			latentPeriod = 1;
		}
		if (antiviralEffDelay != 0.0 && antiviralEffDelay
				< 1) {
			antiviralEffDelay = 1;
		}
		if (vaccEffDelay != 0.0 && vaccEffDelay
				< 1) {
			vaccEffDelay = 1;
		}

		// initialize other parameters
		double ecr = r0 / infectiousPeriod;
		double initBeta = ecr / (totalPop * 1.0);
		double disRate = 1.0 / latentPeriod;
		double recRate = 1.0 / infectiousPeriod;

		double antiviralRecRate;
		if (antiviralEffDelay
				== 0.0) {
			antiviralRecRate = Double.NaN;
		} else {
			antiviralRecRate = 1.0 / antiviralEffDelay;
		}

		double vaccRecRate;
		if (vaccEffDelay
				== 0.0) {
			vaccRecRate = Double.NaN;
		} else {
			vaccRecRate = 1.0 / vaccEffDelay;
		}

		double vaccStock = 0.0;
		double antiviralStock = 0.0;

		// t=0 is the initial values, so start at t=1
		for (int t = 1; t < len; t++) {

			double currSusceptibleNotVaccineCompliant = susceptibleNotVaccineCompliant[t - 1];
			double currSusceptibleVaccineCompliantNotVaccinated = susceptibleVaccineCompliantNotVaccinated[t - 1];
			double currSusceptibleVaccinatedSuccess = susceptibleVaccinatedSuccess[t - 1];
			double currSusceptibleVaccinatedFailed = susceptibleVaccinatedFailed[t - 1];

			double currExposedVaccinatedFailed = exposedVaccinatedFailed[t - 1];
			double currExposedNotVaccineCompliant = exposedNotVaccineCompliant[t - 1];
			double currExposedVaccineCompliantNotVaccinated = exposedVaccineCompliantNotVaccinated[t - 1];

			// infectious
			double currInfectiousNotVaccineCompliantNotAntiviralCompliantAsymptomatic = infectiousNotVaccineCompliantNotAntiviralCompliantAsymptomatic[t - 1];
			double currInfectiousNotVaccineCompliantNotAntiviralCompliantSymptomatic = infectiousNotVaccineCompliantNotAntiviralCompliantSymptomatic[t - 1];
			double currInfectiousNotVaccineCompliantAntiviralCompliantNotAntiviraledAsymptomatic = infectiousNotVaccineCompliantAntiviralCompliantNotAntiviraledAsymptomatic[t - 1];
			double currInfectiousNotVaccineCompliantAntiviralCompliantNotAntiviraledSymptomatic = infectiousNotVaccineCompliantAntiviralCompliantNotAntiviraledSymptomatic[t - 1];
			double currInfectiousNotVaccineCompliantAntiviraledSymptomaticSuccess = infectiousNotVaccineCompliantAntiviraledSymptomaticSuccess[t - 1];
			double currInfectiousNotVaccineCompliantAntiviraledSymptomaticFailed = infectiousNotVaccineCompliantAntiviraledSymptomaticFailed[t - 1];
			double currInfectiousVaccineCompliantNotVaccinatedNotAntiviralCompliantAsymptomatic = infectiousVaccineCompliantNotVaccinatedNotAntiviralCompliantAsymptomatic[t - 1];
			double currInfectiousVaccineCompliantNotVaccinatedNotAntiviralCompliantSymptomatic = infectiousVaccineCompliantNotVaccinatedNotAntiviralCompliantSymptomatic[t - 1];
			double currInfectiousVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledAsymptomatic = infectiousVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledAsymptomatic[t - 1];
			double currInfectiousVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledSymptomatic = infectiousVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledSymptomatic[t - 1];
			double currInfectiousVaccineCompliantNotVaccinatedAntiviraledSymptomaticSuccess = infectiousVaccineCompliantNotVaccinatedAntiviraledSymptomaticSuccess[t - 1];
			double currInfectiousVaccineCompliantNotVaccinatedAntiviraledSymptomaticFailed = infectiousVaccineCompliantNotVaccinatedAntiviraledSymptomaticFailed[t - 1];
			double currInfectiousVaccinatedFailedNotAntiviralCompliantAsymptomatic = infectiousVaccinatedFailedNotAntiviralCompliantAsymptomatic[t - 1];
			double currInfectiousVaccinatedFailedNotAntiviralCompliantSymptomatic = infectiousVaccinatedFailedNotAntiviralCompliantSymptomatic[t - 1];
			double currInfectiousVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic = infectiousVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic[t - 1];
			double currInfectiousVaccinatedFailedAntiviralCompliantNotAntiviraledSymptomatic = infectiousVaccinatedFailedAntiviralCompliantNotAntiviraledSymptomatic[t - 1];
			double currInfectiousVaccinatedFailedAntiviraledSymptomaticFailed = infectiousVaccinatedFailedAntiviraledSymptomaticFailed[t - 1];
			double currInfectiousVaccinatedFailedAntiviraledSymptomaticSuccess;

			// recovered
			double currRecoveredNotVaccineCompliantNotAntiviralCompliantAsymptomatic = recoveredNotVaccineCompliantNotAntiviralCompliantAsymptomatic[t - 1];
			double currRecoveredNotVaccineCompliantNotAntiviralCompliantSymptomatic = recoveredNotVaccineCompliantNotAntiviralCompliantSymptomatic[t - 1];
			double currRecoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledAsymptomatic = recoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledAsymptomatic[t - 1];
			double currRecoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledSymptomatic = recoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledSymptomatic[t - 1];
			double currRecoveredNotVaccineCompliantAntiviralSuccessSymptomatic = recoveredNotVaccineCompliantAntiviralSuccessSymptomatic[t - 1];
			double currRecoveredNotVaccineCompliantAntiviralFailedSymptomatic = recoveredNotVaccineCompliantAntiviralFailedSymptomatic[t - 1];
			double currRecoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantAsymptomatic = recoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantAsymptomatic[t - 1];
			double currRecoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantSymptomatic = recoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantSymptomatic[t - 1];
			double currRecoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledAsymptomatic = recoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledAsymptomatic[t - 1];
			double currRecoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledSymptomatic = recoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledSymptomatic[t - 1];
			double currRecoveredVaccineCompliantNotVaccinatedAntiviraledSuccessSymptomatic = recoveredVaccineCompliantNotVaccinatedAntiviraledSuccessSymptomatic[t - 1];
			double currRecoveredVaccineCompliantNotVaccinatedAntiviraledFailedSymptomatic = recoveredVaccineCompliantNotVaccinatedAntiviraledFailedSymptomatic[t - 1];
			double currRecoveredVaccinatedFailedNotAntiviralCompliantAsymptomatic = recoveredVaccinatedFailedNotAntiviralCompliantAsymptomatic[t - 1];
			double currRecoveredVaccinatedFailedNotAntiviralCompliantSymptomatic = recoveredVaccinatedFailedNotAntiviralCompliantSymptomatic[t - 1];
			double currRecoveredVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic = recoveredVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic[t - 1];
			double currRecoveredVaccinatedFailedAntiviralCompliantNotAntiviraledSymptomatic = recoveredVaccinatedFailedAntiviralCompliantNotAntiviraledSymptomatic[t - 1];
			double currRecoveredVaccinatedFailedAntiviraledSuccessSymptomatic = recoveredVaccinatedFailedAntiviraledSuccessSymptomatic[t - 1];
			double currRecoveredVaccinatedFailedAntiviraledFailedSymptomatic = recoveredVaccinatedFailedAntiviraledFailedSymptomatic[t - 1];
			double currRecoveredVaccinatedSuccess = recoveredVaccinatedSuccess[t - 1];

			if (antiviralEffDelay > 0) {
				currInfectiousVaccinatedFailedAntiviraledSymptomaticSuccess = infectiousVaccinatedFailedAntiviraledSymptomaticSuccess[t - 1];
			} else {
				currInfectiousVaccinatedFailedAntiviraledSymptomaticSuccess = 0;
			}

			// calculate vaccines given between t - 1 and t
			double totalSeeking = currSusceptibleVaccineCompliantNotVaccinated + currExposedVaccineCompliantNotVaccinated
					+ currInfectiousVaccineCompliantNotVaccinatedNotAntiviralCompliantAsymptomatic
					+ currInfectiousVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledAsymptomatic
					+ currRecoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantAsymptomatic
					+ currRecoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledAsymptomatic;
			double vaccSupplyTdy = 0.0; // initial value in case no schedule is set
			if (vaccSupplySchedule != null) {
				vaccSupplyTdy = vaccSupplySchedule[t - 1];
			}
			double vaccAdminTdy = 0.0; // initial value in case no schedule is set
			if (vaccAdminSchedule != null) {
				vaccAdminTdy = vaccAdminSchedule[t - 1];
			}
			double vaccToGiveTdy;
			double vaccPerCapita;
			// first add vaccine supply to stockpile
			vaccStock += vaccSupplyTdy;
			// calculate how many will be given out
			// if no one is seeking the vaccine, none can be given out
			// use absolute value to avoid issues with negative zero
			if (totalSeeking == 0.0) {
				vaccToGiveTdy = 0.0;
				vaccPerCapita = 0.0;
			} else {
				// can only give as many as are in the stockpile
				vaccToGiveTdy = Math.min(vaccAdminTdy, vaccStock);

				if (vaccToGiveTdy > totalSeeking) {
					// can give out at most one vaccine per person
					vaccPerCapita = 1.0;
				} else {
					vaccPerCapita = vaccToGiveTdy / (totalSeeking);
				}
			}

			// take them out of the stockpile
			vaccStock -= vaccToGiveTdy;

			// calculate antivirals given between t - 1 and t
			double totalSeekingAntivirals = currInfectiousNotVaccineCompliantAntiviralCompliantNotAntiviraledSymptomatic
					+ currInfectiousVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledSymptomatic
					+ currInfectiousVaccinatedFailedAntiviralCompliantNotAntiviraledSymptomatic;
			double antiviralSupplyTdy = 0.0; // initial value in case no schedule is set
			if (antiviralSupplySchedule != null) {
				antiviralSupplyTdy = antiviralSupplySchedule[t - 1];
			}
			double antiviralAdminTdy = 0.0; // initial value in case no schedule is set
			if (antiviralAdminSchedule != null) {
				antiviralAdminTdy = antiviralAdminSchedule[t - 1];
			}
			double antiviralsToGiveTdy;
			double antiviralPerCapita;
			// first add antiviral supply to stockpile
			antiviralStock += antiviralSupplyTdy;
			// calculate how many will be given out
			// if no one is seeking antivirals, none can be given out
			// use absolute value to avoid issues with negative zero
			if (totalSeekingAntivirals == 0.0) {
				antiviralsToGiveTdy = 0.0;
				antiviralPerCapita = 0.0;
			} else {
				// can only give as many as are in the stockpile
				antiviralsToGiveTdy = Math.min(antiviralAdminTdy,
						antiviralStock);

				if (antiviralsToGiveTdy > totalSeekingAntivirals) {
					// can only provide at most one antiviral
					// per person
					antiviralPerCapita = 1.0;
				} else {
					antiviralPerCapita = antiviralsToGiveTdy
							/ totalSeekingAntivirals;
				}
			}

			// take them out of the stockpile
			antiviralStock -= antiviralsToGiveTdy;

			// calculate quantities which will be used in the equations
			double totalInfectious = currInfectiousNotVaccineCompliantAntiviralCompliantNotAntiviraledAsymptomatic
					+ currInfectiousNotVaccineCompliantAntiviralCompliantNotAntiviraledSymptomatic
					+ currInfectiousNotVaccineCompliantAntiviraledSymptomaticSuccess
					+ currInfectiousNotVaccineCompliantAntiviraledSymptomaticFailed
					+ currInfectiousNotVaccineCompliantNotAntiviralCompliantAsymptomatic
					+ currInfectiousNotVaccineCompliantNotAntiviralCompliantSymptomatic
					+ currInfectiousVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic
					+ currInfectiousVaccinatedFailedAntiviralCompliantNotAntiviraledSymptomatic
					+ currInfectiousVaccinatedFailedAntiviraledSymptomaticSuccess
					+ currInfectiousVaccinatedFailedAntiviraledSymptomaticFailed
					+ currInfectiousVaccinatedFailedNotAntiviralCompliantAsymptomatic
					+ currInfectiousVaccinatedFailedNotAntiviralCompliantSymptomatic
					+ currInfectiousVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledAsymptomatic
					+ currInfectiousVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledSymptomatic
					+ currInfectiousVaccineCompliantNotVaccinatedAntiviraledSymptomaticSuccess
					+ currInfectiousVaccineCompliantNotVaccinatedAntiviraledSymptomaticFailed
					+ currInfectiousVaccineCompliantNotVaccinatedNotAntiviralCompliantAsymptomatic
					+ currInfectiousVaccineCompliantNotVaccinatedNotAntiviralCompliantSymptomatic;

			double beta = initBeta;

			double pctSuscVacc = 0.0;
			if (vaccPerCapita + beta * totalInfectious > 0.0) {
				pctSuscVacc
						= // the total proportion which will leave the susceptible and
						// vaccine receptive compartment for any reason (cannot be
						// more than 1)
						Math.min(vaccPerCapita + beta * totalInfectious, 1)
						// times the proportion of the total rate leaving that is
						// due to vaccination
						* ((vaccPerCapita) / (vaccPerCapita + beta
						* totalInfectious));
			}
			double pctSuscDisease = 0.0;
			if (vaccPerCapita + beta * totalInfectious > 0.0) {
				pctSuscDisease
						= // the total proportion which will leave the susceptible and
						// vaccine receptive compartment for any reason (cannot be
						// more than 1)
						Math.min(vaccPerCapita + beta * totalInfectious, 1)
						// times the proportion of the total rate leaving that is
						// due to disease (becoming exposed)
						* ((beta * totalInfectious) / (vaccPerCapita + beta
						* totalInfectious));
			}

			double adjustedVaccDiseaseRateForVacc = 0.0;
			if (vaccRecRate + beta * totalInfectious > 0.0) {
				adjustedVaccDiseaseRateForVacc
						= // the total proportion which will leave the vaccinated and
						// compartment for any reason (cannot be
						// more than 1)
						Math.min(vaccRecRate + beta * totalInfectious, 1)
						// times the proportion of the total rate leaving that is
						// due to vaccination
						* ((vaccRecRate) / (vaccRecRate + beta
						* totalInfectious));
			}
			double adjustedVaccDiseaseRateForDisease = 0.0;
			if (vaccRecRate + beta * totalInfectious > 0.0) {
				adjustedVaccDiseaseRateForDisease
						= // the total proportion which will leave the susceptible and
						// vaccine receptive compartment for any reason (cannot be
						// more than 1)
						Math.min(vaccRecRate + beta * totalInfectious, 1)
						// times the proportion of the total rate leaving that is
						// due to disease (becoming exposed)
						* ((beta * totalInfectious) / (vaccRecRate + beta
						* totalInfectious));
			}

			double pctExposedVacc
					= // this should not have any numeric issues
					// because disRate > 0
					// the total proportion which will leave the exposed and
					// vaccine receptive compartment for any reason (cannot be
					// more than 1)
					Math.min(vaccPerCapita + disRate, 1)
					// times the proportion of the total rate leaving that is
					// due to vaccination
					* ((vaccPerCapita) / (vaccPerCapita + disRate));
			double pctExposedDisease
					= // this should not have any numeric
					// issues because disRate > 0
					// the total proportion which will leave the exposed and
					// vaccine receptive compartment for any reason (cannot be
					// more than 1)
					Math.min(vaccPerCapita + disRate, 1)
					// times the proportion of the total rate leaving that is
					// due to disease (becoming infectious)
					* (disRate / (vaccPerCapita + disRate));
			double pctInfVacc
					= // this should not have any numeric issues
					// because recRate > 0
					// the total proportion which will leave the infectious and
					// vaccine receptive compartment for any reason (cannot be
					// more than 1)
					Math.min(vaccPerCapita + recRate, 1)
					// times the proportion of the total rate leaving that is
					// due to vaccination
					* ((vaccPerCapita) / (vaccPerCapita + recRate));
			double pctInfVaccDisease
					= // this should not have any numeric issues
					// because recRate > 0
					// the total proportion which will leave the infectious and
					// vaccine receptive compartment for any reason (cannot be
					// more than 1)
					Math.min(vaccPerCapita + recRate, 1)
					// times the proportion of the total rate leaving that is
					// due to disease (becoming recovered)
					* (recRate / (vaccPerCapita + recRate));
			double pctIAntiviraled
					= // this should not have any numeric
					// issues because recRate > 0
					// the total proportion which will leave the infectious, not
					// receptive to vaccine, receptive to antivirals, and
					// symptomatic compartment for any reason (cannot be more
					// than 1)
					Math.min(antiviralPerCapita + recRate, 1)
					// times the proportion of the total rate leaving that is
					// due to antivirals
					* (antiviralPerCapita / (antiviralPerCapita + recRate));

			double pctIAntiviraledDisease
					= // this should not have any numeric
					// issues because recRate > 0
					// the total proportion which will leave the infectious, not
					// receptive to vaccine, receptive to antivirals, and
					// symptomatic compartment for any reason (cannot be more
					// than 1)
					Math.min(antiviralPerCapita + recRate, 1)
					// times the proportion of the total rate leaving that is
					// due to disease (becoming recovered)
					* (recRate / (antiviralPerCapita + recRate));

			double adjustedAntiviralRecRate
					= // this should not have any numeric
					// issues because recRate > 0
					// the total proportion which will leave the treated with
					// antivirals compartment for any reason (cannot be more
					// than 1)
					Math.min(antiviralRecRate + recRate, 1)
					// times the proportion of the total rate leaving that is
					// due to antiviral taking effect (becoming recovered)
					* (antiviralRecRate / (antiviralRecRate + recRate));

			double adjustedRecRate
					= // this should not have any numeric
					// issues because recRate > 0
					// the total proportion which will leave the treated with
					// antivirals compartment for any reason (cannot be more
					// than 1)
					Math.min(antiviralRecRate + recRate, 1)
					// times the proportion of the total rate leaving that is
					// due to disease (becoming recovered)
					* (recRate / (antiviralRecRate + recRate));

			// calculate total vaccines actually given out (could be less
			// than vacc_to_give_tdy if the rates for a compartment sum to
			// more than 1)
			double vaccActuallyGiven = pctSuscVacc * currSusceptibleVaccineCompliantNotVaccinated
					+ pctExposedVacc * currExposedVaccineCompliantNotVaccinated
					+ pctInfVacc * (currInfectiousVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledAsymptomatic
					+ currInfectiousVaccineCompliantNotVaccinatedNotAntiviralCompliantAsymptomatic)
					+ (currRecoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantAsymptomatic
					+ currRecoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledAsymptomatic) * (vaccPerCapita);
			if (vaccActuallyGiven < vaccToGiveTdy) {
				// if less vaccines were actually given, put the
				// remainder in the stockpile
				vaccStock += vaccToGiveTdy - vaccActuallyGiven;
			}

			double antiviralsActuallyGiven = pctIAntiviraled
					* (currInfectiousNotVaccineCompliantAntiviralCompliantNotAntiviraledSymptomatic
					+ currInfectiousVaccinatedFailedAntiviralCompliantNotAntiviraledSymptomatic
					+ currInfectiousVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledSymptomatic);
			if (antiviralsActuallyGiven < antiviralsToGiveTdy) {
				// if less antivirals were actually given, put the
				// remainder in the stockpile
				antiviralStock += antiviralsToGiveTdy - antiviralsActuallyGiven;
			}

			// ****************************************************************
			// STORE NEW COMPARTMENT VALUES
			// ****************************************************************
			// susceptible receptive to vaccine
			// =
			// (number that were there previously)
			// (minus number that leave due to vaccination)
			// (minus number that leave due to disease)
			susceptibleVaccineCompliantNotVaccinated[t] = currSusceptibleVaccineCompliantNotVaccinated * (1 - pctSuscVacc - pctSuscDisease);

			// susceptible not receptive to vaccine
			// =
			// (number that were there previously)
			// (minus number that leave due to disease)
			susceptibleNotVaccineCompliant[t] = currSusceptibleNotVaccineCompliant
					* (1 - beta * totalInfectious);

			if (vaccEffDelay > 0.0) {
				// only need to update this
				// compartment if the vaccine has a delay to efficacy.
				// Otherwise vaccinated individuals go directly to
				// recovered.

				// susceptible vaccinated susccess
				// =
				// (number that were there previously)
				// (plus number that enter due to vaccination)
				// (minus number that leave due to disease)
				susceptibleVaccinatedSuccess[t] = currSusceptibleVaccinatedSuccess
						* (1 - adjustedVaccDiseaseRateForVacc - adjustedVaccDiseaseRateForDisease)
						+ currSusceptibleVaccineCompliantNotVaccinated * pctSuscVacc * vaccEff;

				existingSusceptibleNewlyVaccinatedSuccess[t]
						= currSusceptibleVaccineCompliantNotVaccinated * pctSuscVacc * vaccEff;
			} else { // store zero in this compartment
				susceptibleVaccinatedSuccess[t] = 0.0;
				existingSusceptibleNewlyVaccinatedSuccess[t] = 0.0;
			}

			susceptibleVaccinatedFailed[t] = currSusceptibleVaccinatedFailed
					* (1 - beta * totalInfectious)
					+ currSusceptibleVaccineCompliantNotVaccinated * pctSuscVacc * (1 - vaccEff);

			existingSusceptibleNewlyVaccinatedFailed[t]
					= currSusceptibleVaccineCompliantNotVaccinated * pctSuscVacc * (1 - vaccEff);

			existingSusceptibleExistingVaccinatedSuccess[t]
					= susceptibleVaccinatedSuccess[t]
					- existingSusceptibleNewlyVaccinatedSuccess[t];

			existingSusceptibleExistingVaccinatedFailed[t]
					= susceptibleVaccinatedFailed[t]
					- existingSusceptibleNewlyVaccinatedFailed[t];

			// exposed receptive to vaccine
			// =
			// (number that were there previously)
			// (plus number that enter due to disease)
			// (minus number that leave due to vaccination)
			// (minus number that leave due to disease)
			exposedVaccineCompliantNotVaccinated[t] = currSusceptibleVaccineCompliantNotVaccinated * pctSuscDisease
					+ currExposedVaccineCompliantNotVaccinated * (1 - pctExposedVacc - pctExposedDisease);

			// exposed not vaccine compliant
			// =
			// (number that were there previously)
			// (plus number that enter due to disease from susceptible not
			// receptive to vaccine)
			// (minus number that leave due to disease)
			exposedNotVaccineCompliant[t] = beta * totalInfectious * currSusceptibleNotVaccineCompliant
					+ currExposedNotVaccineCompliant * (1 - disRate);

			newlyExposedNotVaccinated[t] = beta * totalInfectious * currSusceptibleNotVaccineCompliant
					+ currSusceptibleVaccineCompliantNotVaccinated * pctSuscDisease;
			existingExposedNotVaccinated[t] = exposedNotVaccineCompliant[t] + exposedVaccineCompliantNotVaccinated[t]
					- newlyExposedNotVaccinated[t];

			if (vaccEffDelay > 0.0) {
				// exposed vaccinated
				// =
				// (number that were there previously)
				// (plus number that enter due to vaccination)
				// (plus number that enter due to disease from susceptible
				// and vaccinated)
				// (minus number that leave due to disease)
				exposedVaccinatedFailed[t] = beta * totalInfectious * currSusceptibleVaccinatedFailed
						+ adjustedVaccDiseaseRateForDisease * currSusceptibleVaccinatedSuccess
						+ currExposedVaccineCompliantNotVaccinated * pctExposedVacc
						+ (1 - disRate) * currExposedVaccinatedFailed;

				newlyExposedVaccinatedFailed[t] = beta * totalInfectious * currSusceptibleVaccinatedFailed
						+ adjustedVaccDiseaseRateForDisease * currSusceptibleVaccinatedSuccess;
			} else {
				exposedVaccinatedFailed[t] = (1 - disRate) * currExposedVaccinatedFailed
						+ currSusceptibleVaccinatedFailed * beta * totalInfectious
						+ currExposedVaccineCompliantNotVaccinated * pctExposedVacc;

				newlyExposedVaccinatedFailed[t] = currSusceptibleVaccinatedFailed * beta * totalInfectious;
			}

			existingExposedNewlyVaccinatedFailed[t] = currExposedVaccineCompliantNotVaccinated * pctExposedVacc;
			existingExposedExistingVaccinatedFailed[t] = exposedVaccinatedFailed[t] - newlyExposedVaccinatedFailed[t]
					- existingExposedNewlyVaccinatedFailed[t];

			// infectious vaccine compliant not vaccinated, not
			// antiviral compliant, asymptomatic
			// =
			// (number that were there previously)
			// (plus number of new asymptomatic infectious cases that are antiviral compliant)
			// (minus number that leave due to vaccination)
			// (minus number that leave due to disease transitions)
			infectiousVaccineCompliantNotVaccinatedNotAntiviralCompliantAsymptomatic[t]
					= (1 - symRate) * currExposedVaccineCompliantNotVaccinated * pctExposedDisease * (1 - antiviralCompliance)
					+ currInfectiousVaccineCompliantNotVaccinatedNotAntiviralCompliantAsymptomatic
					* (1 - pctInfVacc - pctInfVaccDisease);

			// infectious vaccine compliant not vaccinated, not
			// antiviral compliant, symptomatic
			// =
			// (number that were there previously)
			// (plus number of new symptomatic infectious cases that are antiviral compliant)
			// (minus number that leave due to disease transitions)
			infectiousVaccineCompliantNotVaccinatedNotAntiviralCompliantSymptomatic[t]
					= symRate * currExposedVaccineCompliantNotVaccinated * pctExposedDisease * (1 - antiviralCompliance)
					+ currInfectiousVaccineCompliantNotVaccinatedNotAntiviralCompliantSymptomatic
					* (1 - recRate);

			// infectious vaccine compliant not vaccinated, antiviral compliant not antiviraled, asymptomatic
			// =
			// (number that were there previously)
			// (plus number of new asymptomatic infectious cases that are antivial compliant)
			// (minus number that leave due to vaccination)
			// (minus number that leave due to disease transitions)
			infectiousVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledAsymptomatic[t]
					= (1 - symRate) * currExposedVaccineCompliantNotVaccinated * pctExposedDisease * antiviralCompliance
					+ currInfectiousVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledAsymptomatic
					* (1 - pctInfVacc - pctInfVaccDisease);

			// infectious vaccine compliant not vaccinated, antiviral compliant not antiviraled, symptomatic
			// (number that were there previously)
			// (plus number of new asymptomatic infectious cases that are antiviral compliant)
			// (minus number that leave due to disease transitions)
			// (minus number that leave due to recieving antiviral treatment)
			infectiousVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledSymptomatic[t]
					= symRate * currExposedVaccineCompliantNotVaccinated * pctExposedDisease * antiviralCompliance
					+ currInfectiousVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledSymptomatic
					* (1 - pctIAntiviraled - pctIAntiviraledDisease);

			if (antiviralEffDelay > 0.0) {
				// only need to update this compartment if there is an
				// antiviral efficacy delay

				// infectious waiting for antiviral efficacy
				// =
				// (number that were there previously)
				// (plus number that enter due to antiviral treatment)
				// (minus number that leave due to disease transitions)
				// (minus number that leave due to antiviral taking effect)
				infectiousVaccineCompliantNotVaccinatedAntiviraledSymptomaticSuccess[t]
						= pctIAntiviraled * antiviralEff * currInfectiousVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledSymptomatic
						+ (1 - adjustedAntiviralRecRate - adjustedRecRate) * currInfectiousVaccineCompliantNotVaccinatedAntiviraledSymptomaticSuccess;
			} else {
				infectiousVaccineCompliantNotVaccinatedAntiviraledSymptomaticSuccess[t] = 0.0;
			}

			infectiousVaccineCompliantNotVaccinatedAntiviraledSymptomaticFailed[t]
					= pctIAntiviraled * (1 - antiviralEff) * currInfectiousVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledSymptomatic
					+ (1 - recRate) * currInfectiousVaccineCompliantNotVaccinatedAntiviraledSymptomaticFailed;

			// infectious not vaccine compliant, not antiviral compliant
			// and asymptomatic
			// =
			// (number that were there previously)
			// (plus number of new asymptomatic infectious cases that are not antiviral compliant)
			// (minus number that leave due to disease transitions)
			infectiousNotVaccineCompliantNotAntiviralCompliantAsymptomatic[t]
					= (1 - symRate) * (1 - antiviralCompliance) * disRate * currExposedNotVaccineCompliant
					+ (1 - recRate) * currInfectiousNotVaccineCompliantNotAntiviralCompliantAsymptomatic;

			// infectious not vaccine compliant, not antiviral compliant
			// and symptomatic
			// =
			// (number that were there previously)
			// (plus number of new symptomatic infectious cases that are not antiviral compliant)
			// (minus number that leave due to disease transitions)
			infectiousNotVaccineCompliantNotAntiviralCompliantSymptomatic[t]
					= symRate * (1 - antiviralCompliance) * disRate * currExposedNotVaccineCompliant
					+ (1 - recRate) * currInfectiousNotVaccineCompliantNotAntiviralCompliantSymptomatic;

			// infectious not vaccine compliant, antiviral compliant
			// and asymptomatic
			// =
			// (number that were there previously)
			// (plus number of new asymptomatic infectious cases that are antiviral compliant)
			// (minus number that leave due to disease transitions)
			infectiousNotVaccineCompliantAntiviralCompliantNotAntiviraledAsymptomatic[t]
					= (1 - symRate) * antiviralCompliance * disRate * currExposedNotVaccineCompliant
					+ (1 - recRate) * currInfectiousNotVaccineCompliantAntiviralCompliantNotAntiviraledAsymptomatic;

			// infectious not vaccine compliant, antiviral compliant
			// and symptomatic
			// =
			// (number that were there previously)
			// (plus number of new symptomatic infectious cases that are antiviral compliant)
			// (minus number that leave due to disease transitions)
			// (minus number that leave due to antiviral treatment)
			infectiousNotVaccineCompliantAntiviralCompliantNotAntiviraledSymptomatic[t]
					= symRate * antiviralCompliance * disRate * currExposedNotVaccineCompliant
					+ (1 - pctIAntiviraled - pctIAntiviraledDisease) * currInfectiousNotVaccineCompliantAntiviralCompliantNotAntiviraledSymptomatic;

			if (antiviralEffDelay > 0.0) {
				// only need to update this compartment if there is an
				// antiviral efficacy delay

				// infectious not vaccine compliant, antiviraled symptomatic
				// =
				// (number that were there previously)
				// (plus number that enter due to antiviral treatment)
				// (minus number that leave due to disease transitions)
				// (minus number that leave due to antiviral taking effect)
				infectiousNotVaccineCompliantAntiviraledSymptomaticSuccess[t]
						= pctIAntiviraled * antiviralEff * currInfectiousNotVaccineCompliantAntiviralCompliantNotAntiviraledSymptomatic
						+ (1 - adjustedAntiviralRecRate - adjustedRecRate) * currInfectiousNotVaccineCompliantAntiviraledSymptomaticSuccess;

				existingInfectiousNotVaccinatedNewlyAntiviraledSuccessSymptomatic[t]
						= pctIAntiviraled * antiviralEff * currInfectiousNotVaccineCompliantAntiviralCompliantNotAntiviraledSymptomatic
						+ pctIAntiviraled * antiviralEff * currInfectiousVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledSymptomatic;
			} else {
				infectiousNotVaccineCompliantAntiviraledSymptomaticSuccess[t] = 0.0;
				existingInfectiousNotVaccinatedNewlyAntiviraledSuccessSymptomatic[t] = 0.0;
			}

			infectiousNotVaccineCompliantAntiviraledSymptomaticFailed[t]
					= pctIAntiviraled * (1 - antiviralEff) * currInfectiousNotVaccineCompliantAntiviralCompliantNotAntiviraledSymptomatic
					+ (1 - recRate) * currInfectiousNotVaccineCompliantAntiviraledSymptomaticFailed;

			// incidence arrays			
			existingInfectiousNotVaccinatedNewlyAntiviraledFailedSymptomatic[t]
					= pctIAntiviraled * (1 - antiviralEff) * currInfectiousNotVaccineCompliantAntiviralCompliantNotAntiviraledSymptomatic
					+ pctIAntiviraled * (1 - antiviralEff) * currInfectiousVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledSymptomatic;

			existingInfectiousNotVaccinatedExistingAntiviraledSuccessSymptomatic[t]
					= infectiousVaccineCompliantNotVaccinatedAntiviraledSymptomaticSuccess[t]
					+ infectiousNotVaccineCompliantAntiviraledSymptomaticSuccess[t]
					- existingInfectiousNotVaccinatedNewlyAntiviraledSuccessSymptomatic[t];

			existingInfectiousNotVaccinatedExistingAntiviraledFailedSymptomatic[t]
					= infectiousVaccineCompliantNotVaccinatedAntiviraledSymptomaticFailed[t]
					+ infectiousNotVaccineCompliantAntiviraledSymptomaticFailed[t]
					- existingInfectiousNotVaccinatedNewlyAntiviraledFailedSymptomatic[t];

			newlyInfectiousNotVaccinatedNotAntiviraledAsymptomatic[t]
					= (1 - symRate) * currExposedVaccineCompliantNotVaccinated * pctExposedDisease
					+ (1 - symRate) * disRate * currExposedNotVaccineCompliant;
			existingInfectiousNotVaccinatedNotAntiviraledAsymptomatic[t]
					= infectiousVaccineCompliantNotVaccinatedNotAntiviralCompliantAsymptomatic[t]
					+ infectiousVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledAsymptomatic[t]
					+ infectiousNotVaccineCompliantNotAntiviralCompliantAsymptomatic[t]
					+ infectiousNotVaccineCompliantAntiviralCompliantNotAntiviraledAsymptomatic[t]
					- newlyInfectiousNotVaccinatedNotAntiviraledAsymptomatic[t];

			newlyInfectiousNotVaccinatedNotAntiviraledSymptomatic[t]
					= symRate * currExposedVaccineCompliantNotVaccinated * pctExposedDisease
					+ symRate * disRate * currExposedNotVaccineCompliant;
			existingInfectiousNotVaccinatedNotAntiviraledSymptomatic[t]
					= infectiousVaccineCompliantNotVaccinatedNotAntiviralCompliantSymptomatic[t]
					+ infectiousVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledSymptomatic[t]
					+ infectiousNotVaccineCompliantNotAntiviralCompliantSymptomatic[t]
					+ infectiousNotVaccineCompliantAntiviralCompliantNotAntiviraledSymptomatic[t]
					- newlyInfectiousNotVaccinatedNotAntiviraledSymptomatic[t];

			// infectious vaccine failed, not antiviral compliant
			// and asymptomatic
			// =
			// (number that were there previously)
			// (plus number of new asymptomatic infectious cases that are not antiviral compliant)
			// (minus number that leave due to disease transitions)
			infectiousVaccinatedFailedNotAntiviralCompliantAsymptomatic[t]
					= (1 - symRate) * (1 - antiviralCompliance) * disRate * currExposedVaccinatedFailed
					+ (1 - recRate) * currInfectiousVaccinatedFailedNotAntiviralCompliantAsymptomatic
					+ pctInfVacc * currInfectiousVaccineCompliantNotVaccinatedNotAntiviralCompliantAsymptomatic;

			// infectious vaccine failed, not antiviral compliant
			// and symptomatic
			// =
			// (number that were there previously)
			// (plus number of new symptomatic infectious cases that are not antiviral compliant)
			// (minus number that leave due to disease transitions)
			infectiousVaccinatedFailedNotAntiviralCompliantSymptomatic[t]
					= symRate * (1 - antiviralCompliance) * disRate * currExposedVaccinatedFailed
					+ (1 - recRate) * currInfectiousVaccinatedFailedNotAntiviralCompliantSymptomatic;

			// infectious vaccine failed, antiviral compliant not antiviraled
			// and asymptomatic
			// =
			// (number that were there previously)
			// (plus number of new asymptomatic infectious cases that are antiviral compliant)
			// (minus number that leave due to disease transitions)
			infectiousVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic[t]
					= (1 - symRate) * antiviralCompliance * disRate * currExposedVaccinatedFailed
					+ (1 - recRate) * currInfectiousVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic
					+ pctInfVacc * currInfectiousVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledAsymptomatic;

			// infectious vaccine failed, antiviral compliant not antiviraled
			// and symptomatic
			// =
			// (number that were there previously)
			// (plus number of new symptomatic infectious cases that are not antiviral compliant)
			// (minus number that leave due to disease transitions)
			// (minus number that leave due to antiviral treatment)
			infectiousVaccinatedFailedAntiviralCompliantNotAntiviraledSymptomatic[t]
					= symRate * antiviralCompliance * disRate * currExposedVaccinatedFailed
					+ (1 - pctIAntiviraled - pctIAntiviraledDisease) * currInfectiousVaccinatedFailedAntiviralCompliantNotAntiviraledSymptomatic;

			if (antiviralEffDelay > 0.0) {
				// only need to update this compartment if there is an
				// antiviral efficacy delay

				// infectious vaccine failed, antiviraled success
				// =
				// (number that were there previously)
				// (plus number that enter due to antiviral treatment)
				// (minus number that leave due to disease transitions)
				// (minus number that leave due to antiviral taking effect)
				infectiousVaccinatedFailedAntiviraledSymptomaticSuccess[t]
						= pctIAntiviraled * antiviralEff * currInfectiousVaccinatedFailedAntiviralCompliantNotAntiviraledSymptomatic
						+ (1 - adjustedAntiviralRecRate - adjustedRecRate) * currInfectiousVaccinatedFailedAntiviraledSymptomaticSuccess;

				existingInfectiousVaccinatedNewlyAntiviraledSuccessSymptomatic[t]
						= pctIAntiviraled * antiviralEff * currInfectiousVaccinatedFailedAntiviralCompliantNotAntiviraledSymptomatic;
			} else {
				infectiousVaccinatedFailedAntiviraledSymptomaticSuccess[t] = 0.0;
				existingInfectiousVaccinatedNewlyAntiviraledSuccessSymptomatic[t] = 0.0;
			}

			infectiousVaccinatedFailedAntiviraledSymptomaticFailed[t]
					= pctIAntiviraled * (1 - antiviralEff) * currInfectiousVaccinatedFailedAntiviralCompliantNotAntiviraledSymptomatic
					+ (1 - recRate) * currInfectiousVaccinatedFailedAntiviraledSymptomaticFailed;

			// incidence arrays
			existingInfectiousVaccinatedNewlyAntiviraledFailedSymptomatic[t]
					= pctIAntiviraled * (1 - antiviralEff) * currInfectiousVaccinatedFailedAntiviralCompliantNotAntiviraledSymptomatic;

			existingInfectiousVaccinatedExistingAntiviraledSuccessSymptomatic[t]
					= infectiousVaccinatedFailedAntiviraledSymptomaticSuccess[t]
					- existingInfectiousVaccinatedNewlyAntiviraledSuccessSymptomatic[t];

			existingInfectiousVaccinatedExistingAntiviraledFailedSymptomatic[t]
					= infectiousVaccinatedFailedAntiviraledSymptomaticFailed[t]
					- existingInfectiousVaccinatedNewlyAntiviraledFailedSymptomatic[t];

			newlyInfectiousVaccinatedFailedNotAntiviraledAsymptomatic[t]
					= (1 - symRate) * disRate * currExposedVaccinatedFailed;

			existingInfectiousNewlyVaccinatedFailedNotAntiviraledAsymptomatic[t]
					= pctInfVacc * currInfectiousVaccineCompliantNotVaccinatedNotAntiviralCompliantAsymptomatic
					+ pctInfVacc * currInfectiousVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledAsymptomatic;

			existingInfectiousExistingVaccinatedFailedNotAntiviraledAsymptomatic[t]
					= infectiousVaccinatedFailedNotAntiviralCompliantAsymptomatic[t]
					+ infectiousVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic[t]
					- existingInfectiousNewlyVaccinatedFailedNotAntiviraledAsymptomatic[t]
					- newlyInfectiousVaccinatedFailedNotAntiviraledAsymptomatic[t];

			newlyInfectiousVaccinatedFailedNotAntiviraledSymptomatic[t]
					= symRate * disRate * currExposedVaccinatedFailed;
			existingInfectiousVaccinatedFailedNotAntiviraledSymptomatic[t]
					= infectiousVaccinatedFailedNotAntiviralCompliantSymptomatic[t]
					+ infectiousVaccinatedFailedAntiviralCompliantNotAntiviraledSymptomatic[t]
					- newlyInfectiousVaccinatedFailedNotAntiviraledSymptomatic[t];

			// recovered not vaccine compliant, not antiviral compliant
			// and asymptomatic
			// =
			// (number that were there previously)
			// (plus number of new asymptomatic recovered cases that are not vaccine compliant, not antiviral compliant)
			recoveredNotVaccineCompliantNotAntiviralCompliantAsymptomatic[t]
					= currRecoveredNotVaccineCompliantNotAntiviralCompliantAsymptomatic
					+ recRate * currInfectiousNotVaccineCompliantNotAntiviralCompliantAsymptomatic;

			// recovered not vaccine compliant, not antiviral compliant
			// and symptomatic
			// =
			// (number that were there previously)
			// (plus number of new symptomatic recovered cases that are not vaccine compliant, not antiviral compliant)
			recoveredNotVaccineCompliantNotAntiviralCompliantSymptomatic[t]
					= currRecoveredNotVaccineCompliantNotAntiviralCompliantSymptomatic
					+ recRate * currInfectiousNotVaccineCompliantNotAntiviralCompliantSymptomatic;

			// recovered not vaccine compliant, antiviral compliant not antiviraled
			// and asymptomatic
			// =
			// (number that were there previously)
			// (plus number of new asymptomatic recovered cases that are not vaccine compliant, antiviral compliant not antiviraled)
			recoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledAsymptomatic[t]
					= currRecoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledAsymptomatic
					+ recRate * currInfectiousNotVaccineCompliantAntiviralCompliantNotAntiviraledAsymptomatic;

			// recovered not vaccine compliant, antiviral compliant not antiviraled
			// and symptomatic
			// =
			// (number that were there previously)
			// (plus number of new symptomatic recovered cases (from disease transition)
			// that are not vaccine compliant, antiviral compliant not antiviraled)
			recoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledSymptomatic[t]
					= currRecoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledSymptomatic
					+ pctIAntiviraledDisease * currInfectiousNotVaccineCompliantAntiviralCompliantNotAntiviraledSymptomatic;

			// recovered not vaccine compliant, antiviraled successfully
			// =
			// (number that were there previously)
			// (plus number of new recovered cases that are not vaccine compliant and antiviraled, due to antiviral working
			// and also disease transitions)
			if (antiviralEffDelay > 0.0) {
				recoveredNotVaccineCompliantAntiviralSuccessSymptomatic[t]
						= currRecoveredNotVaccineCompliantAntiviralSuccessSymptomatic
						+ (adjustedAntiviralRecRate + adjustedRecRate) * currInfectiousNotVaccineCompliantAntiviraledSymptomaticSuccess;
				newlyRecoveredNotVaccineCompliantNewlyAntiviraledSuccessSymptomatic[t] = 0.0;
			} else {
				recoveredNotVaccineCompliantAntiviralSuccessSymptomatic[t]
						= currRecoveredNotVaccineCompliantAntiviralSuccessSymptomatic
						+ pctIAntiviraled * antiviralEff * currInfectiousNotVaccineCompliantAntiviralCompliantNotAntiviraledSymptomatic;
				newlyRecoveredNotVaccineCompliantNewlyAntiviraledSuccessSymptomatic[t]
						= pctIAntiviraled * antiviralEff * currInfectiousNotVaccineCompliantAntiviralCompliantNotAntiviraledSymptomatic;
			}

			existingRecoveredNotVaccineCompliantExistingAntiviraledSuccessSymptomatic[t]
					= recoveredNotVaccineCompliantAntiviralSuccessSymptomatic[t]
					- newlyRecoveredNotVaccineCompliantNewlyAntiviraledSuccessSymptomatic[t];

			recoveredNotVaccineCompliantAntiviralFailedSymptomatic[t]
					= currRecoveredNotVaccineCompliantAntiviralFailedSymptomatic
					+ currInfectiousNotVaccineCompliantAntiviraledSymptomaticFailed * recRate;

			// recovered vaccine compliant not vaccinated, not antiviral compliant
			// and asymptomatic
			// =
			// (number that were there previously)
			// (plus number of new asymptomatic recovered cases that are not vaccine compliant, not antiviral compliant)
			// (minus number that become vaccinated)
			recoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantAsymptomatic[t]
					= currRecoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantAsymptomatic * (1 - vaccPerCapita)
					+ pctInfVaccDisease * currInfectiousVaccineCompliantNotVaccinatedNotAntiviralCompliantAsymptomatic;

			// recovered vaccine compliant not vaccinated, not antiviral compliant
			// and symptomatic
			// =
			// (number that were there previously)
			// (plus number of new asymptomatic recovered cases that are not vaccine compliant, not antiviral compliant)
			recoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantSymptomatic[t]
					= currRecoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantSymptomatic
					+ recRate * currInfectiousVaccineCompliantNotVaccinatedNotAntiviralCompliantSymptomatic;

			// recovered vaccine compliant not vaccinated, antiviral compliant not antiviraled
			// and asymptomatic
			// =
			// (number that were there previously)
			// (plus number of new asymptomatic recovered cases that are not vaccine compliant, not antiviral compliant)
			// (minus number that become vaccinated)
			recoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledAsymptomatic[t]
					= currRecoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledAsymptomatic * (1 - vaccPerCapita)
					+ pctInfVaccDisease * currInfectiousVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledAsymptomatic;

			// recovered vaccine compliant not vaccinated, antiviral compliant not antiviraled
			// and symptomatic
			// =
			// (number that were there previously)
			// (plus number of new asymptomatic recovered cases that are not vaccine compliant, not antiviral compliant)
			recoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledSymptomatic[t]
					= currRecoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledSymptomatic
					+ pctIAntiviraledDisease * currInfectiousVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledSymptomatic;

			// recovered vaccine compliant, antiviraled successfully
			// =
			// (number that were there previously)
			// (plus number of new recovered cases that are not vaccine compliant and antiviraled, due to antiviral working
			// and also disease transitions)
			if (antiviralEffDelay > 0.0) {
				recoveredVaccineCompliantNotVaccinatedAntiviraledSuccessSymptomatic[t]
						= currRecoveredVaccineCompliantNotVaccinatedAntiviraledSuccessSymptomatic
						+ (adjustedAntiviralRecRate + adjustedRecRate) * currInfectiousVaccineCompliantNotVaccinatedAntiviraledSymptomaticSuccess;
				newlyRecoveredVaccineCompliantNotVaccinatedNewlyAntiviraledSuccessSymptomatic[t] = 0.0;
			} else {
				recoveredVaccineCompliantNotVaccinatedAntiviraledSuccessSymptomatic[t]
						= currRecoveredVaccineCompliantNotVaccinatedAntiviraledSuccessSymptomatic
						+ pctIAntiviraled * antiviralEff * currInfectiousVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledSymptomatic;
				newlyRecoveredVaccineCompliantNotVaccinatedNewlyAntiviraledSuccessSymptomatic[t]
						= pctIAntiviraled * antiviralEff * currInfectiousVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledSymptomatic;
			}

			existingRecoveredVaccineCompliantNotVaccinatedExistingAntiviraledSuccessSymptomatic[t]
					= recoveredVaccineCompliantNotVaccinatedAntiviraledSuccessSymptomatic[t]
					- newlyRecoveredVaccineCompliantNotVaccinatedNewlyAntiviraledSuccessSymptomatic[t];

			recoveredVaccineCompliantNotVaccinatedAntiviraledFailedSymptomatic[t]
					= currRecoveredVaccineCompliantNotVaccinatedAntiviraledFailedSymptomatic
					+ recRate * currInfectiousVaccineCompliantNotVaccinatedAntiviraledSymptomaticFailed;

			// recovered vaccinated success, not antiviral compliant
			// and asymptomatic
			// =
			// (number that were there previously)
			// (plus number of new recovered cases from vaccination)
			if (vaccEffDelay > 0.0) {
				recoveredVaccinatedSuccess[t]
						= currRecoveredVaccinatedSuccess
						+ currSusceptibleVaccinatedSuccess * adjustedVaccDiseaseRateForVacc;
				newlyRecoveredNewlyVaccinatedSuccess[t] = 0.0;
			} else {
				recoveredVaccinatedSuccess[t]
						= currRecoveredVaccinatedSuccess
						+ currSusceptibleVaccineCompliantNotVaccinated * pctSuscVacc * vaccEff;
				newlyRecoveredNewlyVaccinatedSuccess[t] = currSusceptibleVaccineCompliantNotVaccinated * pctSuscVacc * vaccEff;
			}

			existingRecoveredExistingVaccinatedSuccess[t]
					= recoveredVaccinatedSuccess[t] - newlyRecoveredNewlyVaccinatedSuccess[t];

			if (antiviralEffDelay > 0.0) {
				recoveredVaccinatedFailedAntiviraledSuccessSymptomatic[t]
						= currRecoveredVaccinatedFailedAntiviraledSuccessSymptomatic
						+ currInfectiousVaccinatedFailedAntiviraledSymptomaticSuccess * adjustedAntiviralRecRate;

				recoveredVaccinatedFailedAntiviraledFailedSymptomatic[t]
						= currRecoveredVaccinatedFailedAntiviraledFailedSymptomatic
						+ currInfectiousVaccinatedFailedAntiviraledSymptomaticFailed * recRate
						+ currInfectiousVaccinatedFailedAntiviraledSymptomaticSuccess * adjustedRecRate;

				newlyRecoveredVaccinatedFailedNewlyAntiviraledSuccessSymptomatic[t] = 0.0;
			} else {
				recoveredVaccinatedFailedAntiviraledSuccessSymptomatic[t]
						= currRecoveredVaccinatedFailedAntiviraledSuccessSymptomatic
						+ currInfectiousVaccinatedFailedAntiviralCompliantNotAntiviraledSymptomatic * pctIAntiviraled * antiviralEff;

				newlyRecoveredVaccinatedFailedNewlyAntiviraledSuccessSymptomatic[t]
						= currInfectiousVaccinatedFailedAntiviralCompliantNotAntiviraledSymptomatic * pctIAntiviraled * antiviralEff;

				recoveredVaccinatedFailedAntiviraledFailedSymptomatic[t]
						= currRecoveredVaccinatedFailedAntiviraledFailedSymptomatic
						+ currInfectiousVaccinatedFailedAntiviraledSymptomaticFailed * recRate;
			}

			existingRecoveredVaccinatedFailedExistingAntiviraledSuccessSymptomatic[t]
					= recoveredVaccinatedFailedAntiviraledSuccessSymptomatic[t]
					- newlyRecoveredVaccinatedFailedNewlyAntiviraledSuccessSymptomatic[t];

			recoveredVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic[t]
					= currRecoveredVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic
					+ currRecoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledAsymptomatic * vaccPerCapita
					+ currInfectiousVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic * recRate;

			existingRecoveredNewlyVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic[t]
					= currRecoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledAsymptomatic * vaccPerCapita;
			existingRecoveredExistingVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic[t]
					= recoveredVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic[t]
					- existingRecoveredNewlyVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic[t];

			recoveredVaccinatedFailedAntiviralCompliantNotAntiviraledSymptomatic[t]
					= currRecoveredVaccinatedFailedAntiviralCompliantNotAntiviraledSymptomatic
					+ currInfectiousVaccinatedFailedAntiviralCompliantNotAntiviraledSymptomatic * pctIAntiviraledDisease;

			recoveredVaccinatedFailedNotAntiviralCompliantAsymptomatic[t]
					= currRecoveredVaccinatedFailedNotAntiviralCompliantAsymptomatic
					+ currInfectiousVaccinatedFailedNotAntiviralCompliantAsymptomatic * recRate
					+ currRecoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantAsymptomatic * vaccPerCapita;

			existingRecoveredNewlyVaccinatedFailedNotAntiviralCompliantAsymptomatic[t]
					= currRecoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantAsymptomatic * vaccPerCapita;
			existingRecoveredExistingVaccinatedFailedNotAntiviralCompliantAsymptomatic[t]
					= recoveredVaccinatedFailedNotAntiviralCompliantAsymptomatic[t]
					- existingRecoveredNewlyVaccinatedFailedNotAntiviralCompliantAsymptomatic[t];

			recoveredVaccinatedFailedNotAntiviralCompliantSymptomatic[t]
					= currRecoveredVaccinatedFailedNotAntiviralCompliantSymptomatic
					+ currInfectiousVaccinatedFailedNotAntiviralCompliantSymptomatic * recRate;
		}

		SeirModelOutput output = new SeirModelOutput();

		// susceptible
		output.setSusceptibleNotVaccineCompliant(susceptibleNotVaccineCompliant);
		output.setExistingSusceptibleExistingVaccinatedFailed(existingSusceptibleExistingVaccinatedFailed);
		output.setExistingSusceptibleExistingVaccinatedSuccess(existingSusceptibleExistingVaccinatedSuccess);
		output.setExistingSusceptibleNewlyVaccinatedFailed(existingSusceptibleNewlyVaccinatedFailed);
		output.setExistingSusceptibleNewlyVaccinatedSuccess(existingSusceptibleNewlyVaccinatedSuccess);
		output.setSusceptibleVaccineCompliantNotVaccinated(susceptibleVaccineCompliantNotVaccinated);
		// exposed incidence
		output.setNewlyExposedNotVaccinated(newlyExposedNotVaccinated);
		output.setNewlyExposedVaccinatedFailed(newlyExposedVaccinatedFailed);
		output.setExistingExposedNotVaccinated(existingExposedNotVaccinated);
		output.setExistingExposedExistingVaccinatedFailed(existingExposedExistingVaccinatedFailed);
		output.setExistingExposedNewlyVaccinatedFailed(existingExposedNewlyVaccinatedFailed);
		// infectious incidence
		output.setNewlyInfectiousNotVaccinatedNotAntiviraledAsymptomatic(newlyInfectiousNotVaccinatedNotAntiviraledAsymptomatic);
		output.setNewlyInfectiousNotVaccinatedNotAntiviraledSymptomatic(newlyInfectiousNotVaccinatedNotAntiviraledSymptomatic);
		output.setNewlyInfectiousVaccinatedFailedNotAntiviraledAsymptomatic(newlyInfectiousVaccinatedFailedNotAntiviraledAsymptomatic);
		output.setNewlyInfectiousVaccinatedFailedNotAntiviraledSymptomatic(newlyInfectiousVaccinatedFailedNotAntiviraledSymptomatic);
		output.setExistingInfectiousNewlyVaccinatedFailedNotAntiviraledAsymptomatic(existingInfectiousNewlyVaccinatedFailedNotAntiviraledAsymptomatic);
		output.setExistingInfectiousExistingVaccinatedFailedNotAntiviraledAsymptomatic(existingInfectiousExistingVaccinatedFailedNotAntiviraledAsymptomatic);
		output.setExistingInfectiousNotVaccinatedExistingAntiviraledFailedSymptomatic(existingInfectiousNotVaccinatedExistingAntiviraledFailedSymptomatic);
		output.setExistingInfectiousNotVaccinatedExistingAntiviraledSuccessSymptomatic(existingInfectiousNotVaccinatedExistingAntiviraledSuccessSymptomatic);
		output.setExistingInfectiousNotVaccinatedNewlyAntiviraledFailedSymptomatic(existingInfectiousNotVaccinatedNewlyAntiviraledFailedSymptomatic);
		output.setExistingInfectiousNotVaccinatedNewlyAntiviraledSuccessSymptomatic(existingInfectiousNotVaccinatedNewlyAntiviraledSuccessSymptomatic);
		output.setExistingInfectiousNotVaccinatedNotAntiviraledAsymptomatic(existingInfectiousNotVaccinatedNotAntiviraledAsymptomatic);
		output.setExistingInfectiousNotVaccinatedNotAntiviraledSymptomatic(existingInfectiousNotVaccinatedNotAntiviraledSymptomatic);
		output.setExistingInfectiousVaccinatedExistingAntiviraledFailedSymptomatic(existingInfectiousVaccinatedExistingAntiviraledFailedSymptomatic);
		output.setExistingInfectiousVaccinatedExistingAntiviraledSuccessSymptomatic(existingInfectiousVaccinatedExistingAntiviraledSuccessSymptomatic);
		output.setExistingInfectiousVaccinatedFailedNotAntiviraledSymptomatic(existingInfectiousVaccinatedFailedNotAntiviraledSymptomatic);
		output.setExistingInfectiousVaccinatedNewlyAntiviraledFailedSymptomatic(existingInfectiousVaccinatedNewlyAntiviraledFailedSymptomatic);
		output.setExistingInfectiousVaccinatedNewlyAntiviraledSuccessSymptomatic(existingInfectiousVaccinatedNewlyAntiviraledSuccessSymptomatic);
		// recovered
		output.setRecoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledAsymptomatic(recoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledAsymptomatic);
		output.setRecoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledSymptomatic(recoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledSymptomatic);
		output.setRecoveredNotVaccineCompliantAntiviralFailedSymptomatic(recoveredNotVaccineCompliantAntiviralFailedSymptomatic);
		output.setNewlyRecoveredNotVaccineCompliantNewlyAntiviraledSuccessSymptomatic(newlyRecoveredNotVaccineCompliantNewlyAntiviraledSuccessSymptomatic);
		output.setExistingRecoveredNotVaccineCompliantExistingAntiviraledSuccessSymptomatic(existingRecoveredNotVaccineCompliantExistingAntiviraledSuccessSymptomatic);
		output.setRecoveredNotVaccineCompliantNotAntiviralCompliantAsymptomatic(recoveredNotVaccineCompliantNotAntiviralCompliantAsymptomatic);
		output.setRecoveredNotVaccineCompliantNotAntiviralCompliantSymptomatic(recoveredNotVaccineCompliantNotAntiviralCompliantSymptomatic);
		output.setRecoveredVaccinatedFailedAntiviralCompliantNotAntiviraledSymptomatic(recoveredVaccinatedFailedAntiviralCompliantNotAntiviraledSymptomatic);
		output.setRecoveredVaccinatedFailedAntiviraledFailedSymptomatic(recoveredVaccinatedFailedAntiviraledFailedSymptomatic);
		output.setNewlyRecoveredVaccinatedFailedNewlyAntiviraledSuccessSymptomatic(newlyRecoveredVaccinatedFailedNewlyAntiviraledSuccessSymptomatic);
		output.setExistingRecoveredVaccinatedFailedExistingAntiviraledSuccessSymptomatic(existingRecoveredVaccinatedFailedExistingAntiviraledSuccessSymptomatic);
		output.setRecoveredVaccinatedFailedNotAntiviralCompliantSymptomatic(recoveredVaccinatedFailedNotAntiviralCompliantSymptomatic);

		output.setRecoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledAsymptomatic(recoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledAsymptomatic);
		output.setRecoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledSymptomatic(recoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledSymptomatic);
		output.setRecoveredVaccineCompliantNotVaccinatedAntiviraledFailedSymptomatic(recoveredVaccineCompliantNotVaccinatedAntiviraledFailedSymptomatic);
		output.setNewlyRecoveredVaccineCompliantNotVaccinatedNewlyAntiviraledSuccessSymptomatic(newlyRecoveredVaccineCompliantNotVaccinatedNewlyAntiviraledSuccessSymptomatic);
		output.setExistingRecoveredVaccineCompliantNotVaccinatedExistingAntiviraledSuccessSymptomatic(existingRecoveredVaccineCompliantNotVaccinatedExistingAntiviraledSuccessSymptomatic);
		output.setRecoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantAsymptomatic(recoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantAsymptomatic);
		output.setRecoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantSymptomatic(recoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantSymptomatic);

		output.setNewlyRecoveredNewlyVaccinatedSuccess(newlyRecoveredNewlyVaccinatedSuccess);
		output.setExistingRecoveredExistingVaccinatedSuccess(existingRecoveredExistingVaccinatedSuccess);

		output.setExistingRecoveredExistingVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic(existingRecoveredExistingVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic);
		output.setExistingRecoveredExistingVaccinatedFailedNotAntiviralCompliantAsymptomatic(existingRecoveredExistingVaccinatedFailedNotAntiviralCompliantAsymptomatic);
		output.setExistingRecoveredNewlyVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic(existingRecoveredNewlyVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic);
		output.setExistingRecoveredNewlyVaccinatedFailedNotAntiviralCompliantAsymptomatic(existingRecoveredNewlyVaccinatedFailedNotAntiviralCompliantAsymptomatic);

		return output;
	}
}
