package edu.pitt.apollo.seirepidemicmodeljava.types;

/**
 *
 * Author: Nick Millett Email: nick.millett@gmail.com Date: Mar 18, 2013 Time: 3:56:54 PM Class: SeirModelOutput IDE: NetBeans 6.9.1
 */
public class SeirModelOutput {

	private double[] susceptibleNotVaccineCompliant;
	private double[] susceptibleVaccineCompliantNotVaccinated;
	private double[] existingSusceptibleNewlyVaccinatedSuccess;
	private double[] existingSusceptibleNewlyVaccinatedFailed;
	private double[] existingSusceptibleExistingVaccinatedSuccess;
	private double[] existingSusceptibleExistingVaccinatedFailed;
	// exposed incidence
	private double[] newlyExposedNotVaccinated;
	private double[] existingExposedNotVaccinated;
	private double[] newlyExposedVaccinatedFailed;
	private double[] existingExposedNewlyVaccinatedFailed;
	private double[] existingExposedExistingVaccinatedFailed;
	// infectious incidence
	private double[] newlyInfectiousNotVaccinatedNotAntiviraledSymptomatic;
	private double[] existingInfectiousNotVaccinatedNotAntiviraledSymptomatic;
	private double[] newlyInfectiousNotVaccinatedNotAntiviraledAsymptomatic;
	private double[] existingInfectiousNotVaccinatedNotAntiviraledAsymptomatic;
	// antiviraled
	private double[] existingInfectiousNotVaccinatedNewlyAntiviraledSuccessSymptomatic;
	private double[] existingInfectiousNotVaccinatedNewlyAntiviraledFailedSymptomatic;
	private double[] existingInfectiousNotVaccinatedExistingAntiviraledSuccessSymptomatic;
	private double[] existingInfectiousNotVaccinatedExistingAntiviraledFailedSymptomatic;
	// vaccinated
	private double[] newlyInfectiousVaccinatedFailedNotAntiviraledSymptomatic;
	private double[] existingInfectiousVaccinatedFailedNotAntiviraledSymptomatic;
	private double[] newlyInfectiousVaccinatedFailedNotAntiviraledAsymptomatic;
	private double[] existingInfectiousNewlyVaccinatedFailedNotAntiviraledAsymptomatic;
	private double[] existingInfectiousExistingVaccinatedFailedNotAntiviraledAsymptomatic;
	// antiviraled, vaccinated
	private double[] existingInfectiousVaccinatedNewlyAntiviraledSuccessSymptomatic;
	private double[] existingInfectiousVaccinatedNewlyAntiviraledFailedSymptomatic;
	private double[] existingInfectiousVaccinatedExistingAntiviraledSuccessSymptomatic;
	private double[] existingInfectiousVaccinatedExistingAntiviraledFailedSymptomatic;
	// recovered
	private double[] recoveredNotVaccineCompliantNotAntiviralCompliantAsymptomatic;
	private double[] recoveredNotVaccineCompliantNotAntiviralCompliantSymptomatic;
	private double[] recoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledAsymptomatic;
	private double[] recoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledSymptomatic;
	private double[] newlyRecoveredNotVaccineCompliantNewlyAntiviraledSuccessSymptomatic;
	private double[] existingRecoveredNotVaccineCompliantExistingAntiviraledSuccessSymptomatic;
	private double[] recoveredNotVaccineCompliantAntiviralFailedSymptomatic;
	private double[] recoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantAsymptomatic;
	private double[] recoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantSymptomatic;
	private double[] recoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledAsymptomatic;
	private double[] recoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledSymptomatic;
	private double[] newlyRecoveredVaccineCompliantNotVaccinatedNewlyAntiviraledSuccessSymptomatic;
	private double[] existingRecoveredVaccineCompliantNotVaccinatedExistingAntiviraledSuccessSymptomatic;
	private double[] recoveredVaccineCompliantNotVaccinatedAntiviraledFailedSymptomatic;
	private double[] recoveredVaccinatedFailedNotAntiviralCompliantSymptomatic;
	private double[] recoveredVaccinatedFailedAntiviralCompliantNotAntiviraledSymptomatic;
	private double[] newlyRecoveredVaccinatedFailedNewlyAntiviraledSuccessSymptomatic;
	private double[] existingRecoveredVaccinatedFailedExistingAntiviraledSuccessSymptomatic;
	private double[] recoveredVaccinatedFailedAntiviraledFailedSymptomatic;
	private double[] newlyRecoveredNewlyVaccinatedSuccess;
	private double[] existingRecoveredExistingVaccinatedSuccess;

	private double[] existingRecoveredNewlyVaccinatedFailedNotAntiviralCompliantAsymptomatic;
	private double[] existingRecoveredExistingVaccinatedFailedNotAntiviralCompliantAsymptomatic;

	private double[] existingRecoveredNewlyVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic;
	private double[] existingRecoveredExistingVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic;

	/**
	 * @return the susceptibleNotVaccineCompliant
	 */
	public double[] getSusceptibleNotVaccineCompliant() {
		return susceptibleNotVaccineCompliant;
	}

	/**
	 * @param susceptibleNotVaccineCompliant the susceptibleNotVaccineCompliant to set
	 */
	public void setSusceptibleNotVaccineCompliant(double[] susceptibleNotVaccineCompliant) {
		this.susceptibleNotVaccineCompliant = susceptibleNotVaccineCompliant;
	}

	/**
	 * @return the susceptibleVaccineCompliantNotVaccinated
	 */
	public double[] getSusceptibleVaccineCompliantNotVaccinated() {
		return susceptibleVaccineCompliantNotVaccinated;
	}

	/**
	 * @param susceptibleVaccineCompliantNotVaccinated the susceptibleVaccineCompliantNotVaccinated to set
	 */
	public void setSusceptibleVaccineCompliantNotVaccinated(double[] susceptibleVaccineCompliantNotVaccinated) {
		this.susceptibleVaccineCompliantNotVaccinated = susceptibleVaccineCompliantNotVaccinated;
	}

	/**
	 * @return the existingSusceptibleNewlyVaccinatedSuccess
	 */
	public double[] getExistingSusceptibleNewlyVaccinatedSuccess() {
		return existingSusceptibleNewlyVaccinatedSuccess;
	}

	/**
	 * @param existingSusceptibleNewlyVaccinatedSuccess the existingSusceptibleNewlyVaccinatedSuccess to set
	 */
	public void setExistingSusceptibleNewlyVaccinatedSuccess(double[] existingSusceptibleNewlyVaccinatedSuccess) {
		this.existingSusceptibleNewlyVaccinatedSuccess = existingSusceptibleNewlyVaccinatedSuccess;
	}

	/**
	 * @return the existingSusceptibleNewlyVaccinatedFailed
	 */
	public double[] getExistingSusceptibleNewlyVaccinatedFailed() {
		return existingSusceptibleNewlyVaccinatedFailed;
	}

	/**
	 * @param existingSusceptibleNewlyVaccinatedFailed the existingSusceptibleNewlyVaccinatedFailed to set
	 */
	public void setExistingSusceptibleNewlyVaccinatedFailed(double[] existingSusceptibleNewlyVaccinatedFailed) {
		this.existingSusceptibleNewlyVaccinatedFailed = existingSusceptibleNewlyVaccinatedFailed;
	}

	/**
	 * @return the existingSusceptibleExistingVaccinatedSuccess
	 */
	public double[] getExistingSusceptibleExistingVaccinatedSuccess() {
		return existingSusceptibleExistingVaccinatedSuccess;
	}

	/**
	 * @param existingSusceptibleExistingVaccinatedSuccess the existingSusceptibleExistingVaccinatedSuccess to set
	 */
	public void setExistingSusceptibleExistingVaccinatedSuccess(double[] existingSusceptibleExistingVaccinatedSuccess) {
		this.existingSusceptibleExistingVaccinatedSuccess = existingSusceptibleExistingVaccinatedSuccess;
	}

	/**
	 * @return the existingSusceptibleExistingVaccinatedFailed
	 */
	public double[] getExistingSusceptibleExistingVaccinatedFailed() {
		return existingSusceptibleExistingVaccinatedFailed;
	}

	/**
	 * @param existingSusceptibleExistingVaccinatedFailed the existingSusceptibleExistingVaccinatedFailed to set
	 */
	public void setExistingSusceptibleExistingVaccinatedFailed(double[] existingSusceptibleExistingVaccinatedFailed) {
		this.existingSusceptibleExistingVaccinatedFailed = existingSusceptibleExistingVaccinatedFailed;
	}

	/**
	 * @return the newlyExposedNotVaccinated
	 */
	public double[] getNewlyExposedNotVaccinated() {
		return newlyExposedNotVaccinated;
	}

	/**
	 * @param newlyExposedNotVaccinated the newlyExposedNotVaccinated to set
	 */
	public void setNewlyExposedNotVaccinated(double[] newlyExposedNotVaccinated) {
		this.newlyExposedNotVaccinated = newlyExposedNotVaccinated;
	}

	/**
	 * @return the existingExposedNotVaccinated
	 */
	public double[] getExistingExposedNotVaccinated() {
		return existingExposedNotVaccinated;
	}

	/**
	 * @param existingExposedNotVaccinated the existingExposedNotVaccinated to set
	 */
	public void setExistingExposedNotVaccinated(double[] existingExposedNotVaccinated) {
		this.existingExposedNotVaccinated = existingExposedNotVaccinated;
	}

	/**
	 * @return the newlyExposedVaccinatedFailed
	 */
	public double[] getNewlyExposedVaccinatedFailed() {
		return newlyExposedVaccinatedFailed;
	}

	/**
	 * @param newlyExposedVaccinatedFailed the newlyExposedVaccinatedFailed to set
	 */
	public void setNewlyExposedVaccinatedFailed(double[] newlyExposedVaccinatedFailed) {
		this.newlyExposedVaccinatedFailed = newlyExposedVaccinatedFailed;
	}

	/**
	 * @return the existingExposedNewlyVaccinatedFailed
	 */
	public double[] getExistingExposedNewlyVaccinatedFailed() {
		return existingExposedNewlyVaccinatedFailed;
	}

	/**
	 * @param existingExposedNewlyVaccinatedFailed the existingExposedNewlyVaccinatedFailed to set
	 */
	public void setExistingExposedNewlyVaccinatedFailed(double[] existingExposedNewlyVaccinatedFailed) {
		this.existingExposedNewlyVaccinatedFailed = existingExposedNewlyVaccinatedFailed;
	}

	/**
	 * @return the existingExposedExistingVaccinatedFailed
	 */
	public double[] getExistingExposedExistingVaccinatedFailed() {
		return existingExposedExistingVaccinatedFailed;
	}

	/**
	 * @param existingExposedExistingVaccinatedFailed the existingExposedExistingVaccinatedFailed to set
	 */
	public void setExistingExposedExistingVaccinatedFailed(double[] existingExposedExistingVaccinatedFailed) {
		this.existingExposedExistingVaccinatedFailed = existingExposedExistingVaccinatedFailed;
	}

	/**
	 * @return the newlyInfectiousNotVaccinatedNotAntiviraledSymptomatic
	 */
	public double[] getNewlyInfectiousNotVaccinatedNotAntiviraledSymptomatic() {
		return newlyInfectiousNotVaccinatedNotAntiviraledSymptomatic;
	}

	/**
	 * @param newlyInfectiousNotVaccinatedNotAntiviraledSymptomatic the newlyInfectiousNotVaccinatedNotAntiviraledSymptomatic to set
	 */
	public void setNewlyInfectiousNotVaccinatedNotAntiviraledSymptomatic(double[] newlyInfectiousNotVaccinatedNotAntiviraledSymptomatic) {
		this.newlyInfectiousNotVaccinatedNotAntiviraledSymptomatic = newlyInfectiousNotVaccinatedNotAntiviraledSymptomatic;
	}

	/**
	 * @return the existingInfectiousNotVaccinatedNotAntiviraledSymptomatic
	 */
	public double[] getExistingInfectiousNotVaccinatedNotAntiviraledSymptomatic() {
		return existingInfectiousNotVaccinatedNotAntiviraledSymptomatic;
	}

	/**
	 * @param existingInfectiousNotVaccinatedNotAntiviraledSymptomatic the existingInfectiousNotVaccinatedNotAntiviraledSymptomatic to set
	 */
	public void setExistingInfectiousNotVaccinatedNotAntiviraledSymptomatic(double[] existingInfectiousNotVaccinatedNotAntiviraledSymptomatic) {
		this.existingInfectiousNotVaccinatedNotAntiviraledSymptomatic = existingInfectiousNotVaccinatedNotAntiviraledSymptomatic;
	}

	/**
	 * @return the newlyInfectiousNotVaccinatedNotAntiviraledAsymptomatic
	 */
	public double[] getNewlyInfectiousNotVaccinatedNotAntiviraledAsymptomatic() {
		return newlyInfectiousNotVaccinatedNotAntiviraledAsymptomatic;
	}

	/**
	 * @param newlyInfectiousNotVaccinatedNotAntiviraledAsymptomatic the newlyInfectiousNotVaccinatedNotAntiviraledAsymptomatic to set
	 */
	public void setNewlyInfectiousNotVaccinatedNotAntiviraledAsymptomatic(double[] newlyInfectiousNotVaccinatedNotAntiviraledAsymptomatic) {
		this.newlyInfectiousNotVaccinatedNotAntiviraledAsymptomatic = newlyInfectiousNotVaccinatedNotAntiviraledAsymptomatic;
	}

	/**
	 * @return the existingInfectiousNotVaccinatedNotAntiviraledAsymptomatic
	 */
	public double[] getExistingInfectiousNotVaccinatedNotAntiviraledAsymptomatic() {
		return existingInfectiousNotVaccinatedNotAntiviraledAsymptomatic;
	}

	/**
	 * @param existingInfectiousNotVaccinatedNotAntiviraledAsymptomatic the existingInfectiousNotVaccinatedNotAntiviraledAsymptomatic to set
	 */
	public void setExistingInfectiousNotVaccinatedNotAntiviraledAsymptomatic(double[] existingInfectiousNotVaccinatedNotAntiviraledAsymptomatic) {
		this.existingInfectiousNotVaccinatedNotAntiviraledAsymptomatic = existingInfectiousNotVaccinatedNotAntiviraledAsymptomatic;
	}

	/**
	 * @return the existingInfectiousNotVaccinatedNewlyAntiviraledSuccessSymptomatic
	 */
	public double[] getExistingInfectiousNotVaccinatedNewlyAntiviraledSuccessSymptomatic() {
		return existingInfectiousNotVaccinatedNewlyAntiviraledSuccessSymptomatic;
	}

	/**
	 * @param existingInfectiousNotVaccinatedNewlyAntiviraledSuccessSymptomatic the existingInfectiousNotVaccinatedNewlyAntiviraledSuccessSymptomatic to set
	 */
	public void setExistingInfectiousNotVaccinatedNewlyAntiviraledSuccessSymptomatic(double[] existingInfectiousNotVaccinatedNewlyAntiviraledSuccessSymptomatic) {
		this.existingInfectiousNotVaccinatedNewlyAntiviraledSuccessSymptomatic = existingInfectiousNotVaccinatedNewlyAntiviraledSuccessSymptomatic;
	}

	/**
	 * @return the existingInfectiousNotVaccinatedNewlyAntiviraledFailedSymptomatic
	 */
	public double[] getExistingInfectiousNotVaccinatedNewlyAntiviraledFailedSymptomatic() {
		return existingInfectiousNotVaccinatedNewlyAntiviraledFailedSymptomatic;
	}

	/**
	 * @param existingInfectiousNotVaccinatedNewlyAntiviraledFailedSymptomatic the existingInfectiousNotVaccinatedNewlyAntiviraledFailedSymptomatic to set
	 */
	public void setExistingInfectiousNotVaccinatedNewlyAntiviraledFailedSymptomatic(double[] existingInfectiousNotVaccinatedNewlyAntiviraledFailedSymptomatic) {
		this.existingInfectiousNotVaccinatedNewlyAntiviraledFailedSymptomatic = existingInfectiousNotVaccinatedNewlyAntiviraledFailedSymptomatic;
	}

	/**
	 * @return the existingInfectiousNotVaccinatedExistingAntiviraledSuccessSymptomatic
	 */
	public double[] getExistingInfectiousNotVaccinatedExistingAntiviraledSuccessSymptomatic() {
		return existingInfectiousNotVaccinatedExistingAntiviraledSuccessSymptomatic;
	}

	/**
	 * @param existingInfectiousNotVaccinatedExistingAntiviraledSuccessSymptomatic the existingInfectiousNotVaccinatedExistingAntiviraledSuccessSymptomatic to set
	 */
	public void setExistingInfectiousNotVaccinatedExistingAntiviraledSuccessSymptomatic(double[] existingInfectiousNotVaccinatedExistingAntiviraledSuccessSymptomatic) {
		this.existingInfectiousNotVaccinatedExistingAntiviraledSuccessSymptomatic = existingInfectiousNotVaccinatedExistingAntiviraledSuccessSymptomatic;
	}

	/**
	 * @return the existingInfectiousNotVaccinatedExistingAntiviraledFailedSymptomatic
	 */
	public double[] getExistingInfectiousNotVaccinatedExistingAntiviraledFailedSymptomatic() {
		return existingInfectiousNotVaccinatedExistingAntiviraledFailedSymptomatic;
	}

	/**
	 * @param existingInfectiousNotVaccinatedExistingAntiviraledFailedSymptomatic the existingInfectiousNotVaccinatedExistingAntiviraledFailedSymptomatic to set
	 */
	public void setExistingInfectiousNotVaccinatedExistingAntiviraledFailedSymptomatic(double[] existingInfectiousNotVaccinatedExistingAntiviraledFailedSymptomatic) {
		this.existingInfectiousNotVaccinatedExistingAntiviraledFailedSymptomatic = existingInfectiousNotVaccinatedExistingAntiviraledFailedSymptomatic;
	}

	/**
	 * @return the newlyInfectiousVaccinatedFailedNotAntiviraledSymptomatic
	 */
	public double[] getNewlyInfectiousVaccinatedFailedNotAntiviraledSymptomatic() {
		return newlyInfectiousVaccinatedFailedNotAntiviraledSymptomatic;
	}

	/**
	 * @param newlyInfectiousVaccinatedFailedNotAntiviraledSymptomatic the newlyInfectiousVaccinatedFailedNotAntiviraledSymptomatic to set
	 */
	public void setNewlyInfectiousVaccinatedFailedNotAntiviraledSymptomatic(double[] newlyInfectiousVaccinatedFailedNotAntiviraledSymptomatic) {
		this.newlyInfectiousVaccinatedFailedNotAntiviraledSymptomatic = newlyInfectiousVaccinatedFailedNotAntiviraledSymptomatic;
	}

	/**
	 * @return the existingInfectiousVaccinatedFailedNotAntiviraledSymptomatic
	 */
	public double[] getExistingInfectiousVaccinatedFailedNotAntiviraledSymptomatic() {
		return existingInfectiousVaccinatedFailedNotAntiviraledSymptomatic;
	}

	/**
	 * @param existingInfectiousVaccinatedFailedNotAntiviraledSymptomatic the existingInfectiousVaccinatedFailedNotAntiviraledSymptomatic to set
	 */
	public void setExistingInfectiousVaccinatedFailedNotAntiviraledSymptomatic(double[] existingInfectiousVaccinatedFailedNotAntiviraledSymptomatic) {
		this.existingInfectiousVaccinatedFailedNotAntiviraledSymptomatic = existingInfectiousVaccinatedFailedNotAntiviraledSymptomatic;
	}

	/**
	 * @return the newlyInfectiousVaccinatedFailedNotAntiviraledAsymptomatic
	 */
	public double[] getNewlyInfectiousVaccinatedFailedNotAntiviraledAsymptomatic() {
		return newlyInfectiousVaccinatedFailedNotAntiviraledAsymptomatic;
	}

	/**
	 * @param newlyInfectiousVaccinatedFailedNotAntiviraledAsymptomatic the newlyInfectiousVaccinatedFailedNotAntiviraledAsymptomatic to set
	 */
	public void setNewlyInfectiousVaccinatedFailedNotAntiviraledAsymptomatic(double[] newlyInfectiousVaccinatedFailedNotAntiviraledAsymptomatic) {
		this.newlyInfectiousVaccinatedFailedNotAntiviraledAsymptomatic = newlyInfectiousVaccinatedFailedNotAntiviraledAsymptomatic;
	}

	/**
	 * @return the existingInfectiousNewlyVaccinatedFailedNotAntiviraledAsymptomatic
	 */
	public double[] getExistingInfectiousNewlyVaccinatedFailedNotAntiviraledAsymptomatic() {
		return existingInfectiousNewlyVaccinatedFailedNotAntiviraledAsymptomatic;
	}

	/**
	 * @param existingInfectiousNewlyVaccinatedFailedNotAntiviraledAsymptomatic the existingInfectiousNewlyVaccinatedFailedNotAntiviraledAsymptomatic to set
	 */
	public void setExistingInfectiousNewlyVaccinatedFailedNotAntiviraledAsymptomatic(double[] existingInfectiousNewlyVaccinatedFailedNotAntiviraledAsymptomatic) {
		this.existingInfectiousNewlyVaccinatedFailedNotAntiviraledAsymptomatic = existingInfectiousNewlyVaccinatedFailedNotAntiviraledAsymptomatic;
	}

	/**
	 * @return the existingInfectiousExistingVaccinatedFailedNotAntiviraledAsymptomatic
	 */
	public double[] getExistingInfectiousExistingVaccinatedFailedNotAntiviraledAsymptomatic() {
		return existingInfectiousExistingVaccinatedFailedNotAntiviraledAsymptomatic;
	}

	/**
	 * @param existingInfectiousExistingVaccinatedFailedNotAntiviraledAsymptomatic the existingInfectiousExistingVaccinatedFailedNotAntiviraledAsymptomatic to set
	 */
	public void setExistingInfectiousExistingVaccinatedFailedNotAntiviraledAsymptomatic(double[] existingInfectiousExistingVaccinatedFailedNotAntiviraledAsymptomatic) {
		this.existingInfectiousExistingVaccinatedFailedNotAntiviraledAsymptomatic = existingInfectiousExistingVaccinatedFailedNotAntiviraledAsymptomatic;
	}

	/**
	 * @return the existingInfectiousVaccinatedNewlyAntiviraledSuccessSymptomatic
	 */
	public double[] getExistingInfectiousVaccinatedNewlyAntiviraledSuccessSymptomatic() {
		return existingInfectiousVaccinatedNewlyAntiviraledSuccessSymptomatic;
	}

	/**
	 * @param existingInfectiousVaccinatedNewlyAntiviraledSuccessSymptomatic the existingInfectiousVaccinatedNewlyAntiviraledSuccessSymptomatic to set
	 */
	public void setExistingInfectiousVaccinatedNewlyAntiviraledSuccessSymptomatic(double[] existingInfectiousVaccinatedNewlyAntiviraledSuccessSymptomatic) {
		this.existingInfectiousVaccinatedNewlyAntiviraledSuccessSymptomatic = existingInfectiousVaccinatedNewlyAntiviraledSuccessSymptomatic;
	}

	/**
	 * @return the existingInfectiousVaccinatedNewlyAntiviraledFailedSymptomatic
	 */
	public double[] getExistingInfectiousVaccinatedNewlyAntiviraledFailedSymptomatic() {
		return existingInfectiousVaccinatedNewlyAntiviraledFailedSymptomatic;
	}

	/**
	 * @param existingInfectiousVaccinatedNewlyAntiviraledFailedSymptomatic the existingInfectiousVaccinatedNewlyAntiviraledFailedSymptomatic to set
	 */
	public void setExistingInfectiousVaccinatedNewlyAntiviraledFailedSymptomatic(double[] existingInfectiousVaccinatedNewlyAntiviraledFailedSymptomatic) {
		this.existingInfectiousVaccinatedNewlyAntiviraledFailedSymptomatic = existingInfectiousVaccinatedNewlyAntiviraledFailedSymptomatic;
	}

	/**
	 * @return the existingInfectiousVaccinatedExistingAntiviraledSuccessSymptomatic
	 */
	public double[] getExistingInfectiousVaccinatedExistingAntiviraledSuccessSymptomatic() {
		return existingInfectiousVaccinatedExistingAntiviraledSuccessSymptomatic;
	}

	/**
	 * @param existingInfectiousVaccinatedExistingAntiviraledSuccessSymptomatic the existingInfectiousVaccinatedExistingAntiviraledSuccessSymptomatic to set
	 */
	public void setExistingInfectiousVaccinatedExistingAntiviraledSuccessSymptomatic(double[] existingInfectiousVaccinatedExistingAntiviraledSuccessSymptomatic) {
		this.existingInfectiousVaccinatedExistingAntiviraledSuccessSymptomatic = existingInfectiousVaccinatedExistingAntiviraledSuccessSymptomatic;
	}

	/**
	 * @return the existingInfectiousVaccinatedExistingAntiviraledFailedSymptomatic
	 */
	public double[] getExistingInfectiousVaccinatedExistingAntiviraledFailedSymptomatic() {
		return existingInfectiousVaccinatedExistingAntiviraledFailedSymptomatic;
	}

	/**
	 * @param existingInfectiousVaccinatedExistingAntiviraledFailedSymptomatic the existingInfectiousVaccinatedExistingAntiviraledFailedSymptomatic to set
	 */
	public void setExistingInfectiousVaccinatedExistingAntiviraledFailedSymptomatic(double[] existingInfectiousVaccinatedExistingAntiviraledFailedSymptomatic) {
		this.existingInfectiousVaccinatedExistingAntiviraledFailedSymptomatic = existingInfectiousVaccinatedExistingAntiviraledFailedSymptomatic;
	}

	/**
	 * @return the recoveredNotVaccineCompliantNotAntiviralCompliantAsymptomatic
	 */
	public double[] getRecoveredNotVaccineCompliantNotAntiviralCompliantAsymptomatic() {
		return recoveredNotVaccineCompliantNotAntiviralCompliantAsymptomatic;
	}

	/**
	 * @param recoveredNotVaccineCompliantNotAntiviralCompliantAsymptomatic the recoveredNotVaccineCompliantNotAntiviralCompliantAsymptomatic to set
	 */
	public void setRecoveredNotVaccineCompliantNotAntiviralCompliantAsymptomatic(double[] recoveredNotVaccineCompliantNotAntiviralCompliantAsymptomatic) {
		this.recoveredNotVaccineCompliantNotAntiviralCompliantAsymptomatic = recoveredNotVaccineCompliantNotAntiviralCompliantAsymptomatic;
	}

	/**
	 * @return the recoveredNotVaccineCompliantNotAntiviralCompliantSymptomatic
	 */
	public double[] getRecoveredNotVaccineCompliantNotAntiviralCompliantSymptomatic() {
		return recoveredNotVaccineCompliantNotAntiviralCompliantSymptomatic;
	}

	/**
	 * @param recoveredNotVaccineCompliantNotAntiviralCompliantSymptomatic the recoveredNotVaccineCompliantNotAntiviralCompliantSymptomatic to set
	 */
	public void setRecoveredNotVaccineCompliantNotAntiviralCompliantSymptomatic(double[] recoveredNotVaccineCompliantNotAntiviralCompliantSymptomatic) {
		this.recoveredNotVaccineCompliantNotAntiviralCompliantSymptomatic = recoveredNotVaccineCompliantNotAntiviralCompliantSymptomatic;
	}

	/**
	 * @return the recoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledAsymptomatic
	 */
	public double[] getRecoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledAsymptomatic() {
		return recoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledAsymptomatic;
	}

	/**
	 * @param recoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledAsymptomatic the recoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledAsymptomatic to set
	 */
	public void setRecoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledAsymptomatic(double[] recoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledAsymptomatic) {
		this.recoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledAsymptomatic = recoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledAsymptomatic;
	}

	/**
	 * @return the recoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledSymptomatic
	 */
	public double[] getRecoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledSymptomatic() {
		return recoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledSymptomatic;
	}

	/**
	 * @param recoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledSymptomatic the recoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledSymptomatic to set
	 */
	public void setRecoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledSymptomatic(double[] recoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledSymptomatic) {
		this.recoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledSymptomatic = recoveredNotVaccineCompliantAntiviralCompliantNotAntiviraledSymptomatic;
	}

	/**
	 * @return the newlyRecoveredNotVaccineCompliantNewlyAntiviraledSuccessSymptomatic
	 */
	public double[] getNewlyRecoveredNotVaccineCompliantNewlyAntiviraledSuccessSymptomatic() {
		return newlyRecoveredNotVaccineCompliantNewlyAntiviraledSuccessSymptomatic;
	}

	/**
	 * @param newlyRecoveredNotVaccineCompliantNewlyAntiviraledSuccessSymptomatic the newlyRecoveredNotVaccineCompliantNewlyAntiviraledSuccessSymptomatic to set
	 */
	public void setNewlyRecoveredNotVaccineCompliantNewlyAntiviraledSuccessSymptomatic(double[] newlyRecoveredNotVaccineCompliantNewlyAntiviraledSuccessSymptomatic) {
		this.newlyRecoveredNotVaccineCompliantNewlyAntiviraledSuccessSymptomatic = newlyRecoveredNotVaccineCompliantNewlyAntiviraledSuccessSymptomatic;
	}

	/**
	 * @return the existingRecoveredNotVaccineCompliantExistingAntiviraledSuccessSymptomatic
	 */
	public double[] getExistingRecoveredNotVaccineCompliantExistingAntiviraledSuccessSymptomatic() {
		return existingRecoveredNotVaccineCompliantExistingAntiviraledSuccessSymptomatic;
	}

	/**
	 * @param existingRecoveredNotVaccineCompliantExistingAntiviraledSuccessSymptomatic the existingRecoveredNotVaccineCompliantExistingAntiviraledSuccessSymptomatic to set
	 */
	public void setExistingRecoveredNotVaccineCompliantExistingAntiviraledSuccessSymptomatic(double[] existingRecoveredNotVaccineCompliantExistingAntiviraledSuccessSymptomatic) {
		this.existingRecoveredNotVaccineCompliantExistingAntiviraledSuccessSymptomatic = existingRecoveredNotVaccineCompliantExistingAntiviraledSuccessSymptomatic;
	}

	/**
	 * @return the recoveredNotVaccineCompliantAntiviralFailedSymptomatic
	 */
	public double[] getRecoveredNotVaccineCompliantAntiviralFailedSymptomatic() {
		return recoveredNotVaccineCompliantAntiviralFailedSymptomatic;
	}

	/**
	 * @param recoveredNotVaccineCompliantAntiviralFailedSymptomatic the recoveredNotVaccineCompliantAntiviralFailedSymptomatic to set
	 */
	public void setRecoveredNotVaccineCompliantAntiviralFailedSymptomatic(double[] recoveredNotVaccineCompliantAntiviralFailedSymptomatic) {
		this.recoveredNotVaccineCompliantAntiviralFailedSymptomatic = recoveredNotVaccineCompliantAntiviralFailedSymptomatic;
	}

	/**
	 * @return the recoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantAsymptomatic
	 */
	public double[] getRecoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantAsymptomatic() {
		return recoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantAsymptomatic;
	}

	/**
	 * @param recoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantAsymptomatic the recoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantAsymptomatic to set
	 */
	public void setRecoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantAsymptomatic(double[] recoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantAsymptomatic) {
		this.recoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantAsymptomatic = recoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantAsymptomatic;
	}

	/**
	 * @return the recoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantSymptomatic
	 */
	public double[] getRecoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantSymptomatic() {
		return recoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantSymptomatic;
	}

	/**
	 * @param recoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantSymptomatic the recoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantSymptomatic to set
	 */
	public void setRecoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantSymptomatic(double[] recoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantSymptomatic) {
		this.recoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantSymptomatic = recoveredVaccineCompliantNotVaccinatedNotAntiviralCompliantSymptomatic;
	}

	/**
	 * @return the recoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledAsymptomatic
	 */
	public double[] getRecoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledAsymptomatic() {
		return recoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledAsymptomatic;
	}

	/**
	 * @param recoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledAsymptomatic the recoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledAsymptomatic to set
	 */
	public void setRecoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledAsymptomatic(double[] recoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledAsymptomatic) {
		this.recoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledAsymptomatic = recoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledAsymptomatic;
	}

	/**
	 * @return the recoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledSymptomatic
	 */
	public double[] getRecoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledSymptomatic() {
		return recoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledSymptomatic;
	}

	/**
	 * @param recoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledSymptomatic the recoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledSymptomatic to set
	 */
	public void setRecoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledSymptomatic(double[] recoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledSymptomatic) {
		this.recoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledSymptomatic = recoveredVaccineCompliantNotVaccinatedAntiviralCompliantNotAntiviraledSymptomatic;
	}

	/**
	 * @return the newlyRecoveredVaccineCompliantNotVaccinatedNewlyAntiviraledSuccessSymptomatic
	 */
	public double[] getNewlyRecoveredVaccineCompliantNotVaccinatedNewlyAntiviraledSuccessSymptomatic() {
		return newlyRecoveredVaccineCompliantNotVaccinatedNewlyAntiviraledSuccessSymptomatic;
	}

	/**
	 * @param newlyRecoveredVaccineCompliantNotVaccinatedNewlyAntiviraledSuccessSymptomatic the newlyRecoveredVaccineCompliantNotVaccinatedNewlyAntiviraledSuccessSymptomatic to set
	 */
	public void setNewlyRecoveredVaccineCompliantNotVaccinatedNewlyAntiviraledSuccessSymptomatic(double[] newlyRecoveredVaccineCompliantNotVaccinatedNewlyAntiviraledSuccessSymptomatic) {
		this.newlyRecoveredVaccineCompliantNotVaccinatedNewlyAntiviraledSuccessSymptomatic = newlyRecoveredVaccineCompliantNotVaccinatedNewlyAntiviraledSuccessSymptomatic;
	}

	/**
	 * @return the existingRecoveredVaccineCompliantNotVaccinatedExistingAntiviraledSuccessSymptomatic
	 */
	public double[] getExistingRecoveredVaccineCompliantNotVaccinatedExistingAntiviraledSuccessSymptomatic() {
		return existingRecoveredVaccineCompliantNotVaccinatedExistingAntiviraledSuccessSymptomatic;
	}

	/**
	 * @param existingRecoveredVaccineCompliantNotVaccinatedExistingAntiviraledSuccessSymptomatic the existingRecoveredVaccineCompliantNotVaccinatedExistingAntiviraledSuccessSymptomatic to set
	 */
	public void setExistingRecoveredVaccineCompliantNotVaccinatedExistingAntiviraledSuccessSymptomatic(double[] existingRecoveredVaccineCompliantNotVaccinatedExistingAntiviraledSuccessSymptomatic) {
		this.existingRecoveredVaccineCompliantNotVaccinatedExistingAntiviraledSuccessSymptomatic = existingRecoveredVaccineCompliantNotVaccinatedExistingAntiviraledSuccessSymptomatic;
	}

	/**
	 * @return the recoveredVaccineCompliantNotVaccinatedAntiviraledFailedSymptomatic
	 */
	public double[] getRecoveredVaccineCompliantNotVaccinatedAntiviraledFailedSymptomatic() {
		return recoveredVaccineCompliantNotVaccinatedAntiviraledFailedSymptomatic;
	}

	/**
	 * @param recoveredVaccineCompliantNotVaccinatedAntiviraledFailedSymptomatic the recoveredVaccineCompliantNotVaccinatedAntiviraledFailedSymptomatic to set
	 */
	public void setRecoveredVaccineCompliantNotVaccinatedAntiviraledFailedSymptomatic(double[] recoveredVaccineCompliantNotVaccinatedAntiviraledFailedSymptomatic) {
		this.recoveredVaccineCompliantNotVaccinatedAntiviraledFailedSymptomatic = recoveredVaccineCompliantNotVaccinatedAntiviraledFailedSymptomatic;
	}

	/**
	 * @return the recoveredVaccinatedFailedNotAntiviralCompliantSymptomatic
	 */
	public double[] getRecoveredVaccinatedFailedNotAntiviralCompliantSymptomatic() {
		return recoveredVaccinatedFailedNotAntiviralCompliantSymptomatic;
	}

	/**
	 * @param recoveredVaccinatedFailedNotAntiviralCompliantSymptomatic the recoveredVaccinatedFailedNotAntiviralCompliantSymptomatic to set
	 */
	public void setRecoveredVaccinatedFailedNotAntiviralCompliantSymptomatic(double[] recoveredVaccinatedFailedNotAntiviralCompliantSymptomatic) {
		this.recoveredVaccinatedFailedNotAntiviralCompliantSymptomatic = recoveredVaccinatedFailedNotAntiviralCompliantSymptomatic;
	}

	/**
	 * @return the recoveredVaccinatedFailedAntiviralCompliantNotAntiviraledSymptomatic
	 */
	public double[] getRecoveredVaccinatedFailedAntiviralCompliantNotAntiviraledSymptomatic() {
		return recoveredVaccinatedFailedAntiviralCompliantNotAntiviraledSymptomatic;
	}

	/**
	 * @param recoveredVaccinatedFailedAntiviralCompliantNotAntiviraledSymptomatic the recoveredVaccinatedFailedAntiviralCompliantNotAntiviraledSymptomatic to set
	 */
	public void setRecoveredVaccinatedFailedAntiviralCompliantNotAntiviraledSymptomatic(double[] recoveredVaccinatedFailedAntiviralCompliantNotAntiviraledSymptomatic) {
		this.recoveredVaccinatedFailedAntiviralCompliantNotAntiviraledSymptomatic = recoveredVaccinatedFailedAntiviralCompliantNotAntiviraledSymptomatic;
	}

	/**
	 * @return the newlyRecoveredVaccinatedFailedNewlyAntiviraledSuccessSymptomatic
	 */
	public double[] getNewlyRecoveredVaccinatedFailedNewlyAntiviraledSuccessSymptomatic() {
		return newlyRecoveredVaccinatedFailedNewlyAntiviraledSuccessSymptomatic;
	}

	/**
	 * @param newlyRecoveredVaccinatedFailedNewlyAntiviraledSuccessSymptomatic the newlyRecoveredVaccinatedFailedNewlyAntiviraledSuccessSymptomatic to set
	 */
	public void setNewlyRecoveredVaccinatedFailedNewlyAntiviraledSuccessSymptomatic(double[] newlyRecoveredVaccinatedFailedNewlyAntiviraledSuccessSymptomatic) {
		this.newlyRecoveredVaccinatedFailedNewlyAntiviraledSuccessSymptomatic = newlyRecoveredVaccinatedFailedNewlyAntiviraledSuccessSymptomatic;
	}

	/**
	 * @return the existingRecoveredVaccinatedFailedExistingAntiviraledSuccessSymptomatic
	 */
	public double[] getExistingRecoveredVaccinatedFailedExistingAntiviraledSuccessSymptomatic() {
		return existingRecoveredVaccinatedFailedExistingAntiviraledSuccessSymptomatic;
	}

	/**
	 * @param existingRecoveredVaccinatedFailedExistingAntiviraledSuccessSymptomatic the existingRecoveredVaccinatedFailedExistingAntiviraledSuccessSymptomatic to set
	 */
	public void setExistingRecoveredVaccinatedFailedExistingAntiviraledSuccessSymptomatic(double[] existingRecoveredVaccinatedFailedExistingAntiviraledSuccessSymptomatic) {
		this.existingRecoveredVaccinatedFailedExistingAntiviraledSuccessSymptomatic = existingRecoveredVaccinatedFailedExistingAntiviraledSuccessSymptomatic;
	}

	/**
	 * @return the recoveredVaccinatedFailedAntiviraledFailedSymptomatic
	 */
	public double[] getRecoveredVaccinatedFailedAntiviraledFailedSymptomatic() {
		return recoveredVaccinatedFailedAntiviraledFailedSymptomatic;
	}

	/**
	 * @param recoveredVaccinatedFailedAntiviraledFailedSymptomatic the recoveredVaccinatedFailedAntiviraledFailedSymptomatic to set
	 */
	public void setRecoveredVaccinatedFailedAntiviraledFailedSymptomatic(double[] recoveredVaccinatedFailedAntiviraledFailedSymptomatic) {
		this.recoveredVaccinatedFailedAntiviraledFailedSymptomatic = recoveredVaccinatedFailedAntiviraledFailedSymptomatic;
	}

	/**
	 * @return the newlyRecoveredNewlyVaccinatedSuccess
	 */
	public double[] getNewlyRecoveredNewlyVaccinatedSuccess() {
		return newlyRecoveredNewlyVaccinatedSuccess;
	}

	/**
	 * @param newlyRecoveredNewlyVaccinatedSuccess the newlyRecoveredNewlyVaccinatedSuccess to set
	 */
	public void setNewlyRecoveredNewlyVaccinatedSuccess(double[] newlyRecoveredNewlyVaccinatedSuccess) {
		this.newlyRecoveredNewlyVaccinatedSuccess = newlyRecoveredNewlyVaccinatedSuccess;
	}

	/**
	 * @return the existingRecoveredExistingVaccinatedSuccess
	 */
	public double[] getExistingRecoveredExistingVaccinatedSuccess() {
		return existingRecoveredExistingVaccinatedSuccess;
	}

	/**
	 * @param existingRecoveredExistingVaccinatedSuccess the existingRecoveredExistingVaccinatedSuccess to set
	 */
	public void setExistingRecoveredExistingVaccinatedSuccess(double[] existingRecoveredExistingVaccinatedSuccess) {
		this.existingRecoveredExistingVaccinatedSuccess = existingRecoveredExistingVaccinatedSuccess;
	}

	/**
	 * @return the existingRecoveredNewlyVaccinatedFailedNotAntiviralCompliantAsymptomatic
	 */
	public double[] getExistingRecoveredNewlyVaccinatedFailedNotAntiviralCompliantAsymptomatic() {
		return existingRecoveredNewlyVaccinatedFailedNotAntiviralCompliantAsymptomatic;
	}

	/**
	 * @param existingRecoveredNewlyVaccinatedFailedNotAntiviralCompliantAsymptomatic the existingRecoveredNewlyVaccinatedFailedNotAntiviralCompliantAsymptomatic to set
	 */
	public void setExistingRecoveredNewlyVaccinatedFailedNotAntiviralCompliantAsymptomatic(double[] existingRecoveredNewlyVaccinatedFailedNotAntiviralCompliantAsymptomatic) {
		this.existingRecoveredNewlyVaccinatedFailedNotAntiviralCompliantAsymptomatic = existingRecoveredNewlyVaccinatedFailedNotAntiviralCompliantAsymptomatic;
	}

	/**
	 * @return the existingRecoveredExistingVaccinatedFailedNotAntiviralCompliantAsymptomatic
	 */
	public double[] getExistingRecoveredExistingVaccinatedFailedNotAntiviralCompliantAsymptomatic() {
		return existingRecoveredExistingVaccinatedFailedNotAntiviralCompliantAsymptomatic;
	}

	/**
	 * @param existingRecoveredExistingVaccinatedFailedNotAntiviralCompliantAsymptomatic the existingRecoveredExistingVaccinatedFailedNotAntiviralCompliantAsymptomatic to set
	 */
	public void setExistingRecoveredExistingVaccinatedFailedNotAntiviralCompliantAsymptomatic(double[] existingRecoveredExistingVaccinatedFailedNotAntiviralCompliantAsymptomatic) {
		this.existingRecoveredExistingVaccinatedFailedNotAntiviralCompliantAsymptomatic = existingRecoveredExistingVaccinatedFailedNotAntiviralCompliantAsymptomatic;
	}

	/**
	 * @return the existingRecoveredNewlyVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic
	 */
	public double[] getExistingRecoveredNewlyVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic() {
		return existingRecoveredNewlyVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic;
	}

	/**
	 * @param existingRecoveredNewlyVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic the existingRecoveredNewlyVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic to set
	 */
	public void setExistingRecoveredNewlyVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic(double[] existingRecoveredNewlyVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic) {
		this.existingRecoveredNewlyVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic = existingRecoveredNewlyVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic;
	}

	/**
	 * @return the existingRecoveredExistingVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic
	 */
	public double[] getExistingRecoveredExistingVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic() {
		return existingRecoveredExistingVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic;
	}

	/**
	 * @param existingRecoveredExistingVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic the existingRecoveredExistingVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic to set
	 */
	public void setExistingRecoveredExistingVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic(double[] existingRecoveredExistingVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic) {
		this.existingRecoveredExistingVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic = existingRecoveredExistingVaccinatedFailedAntiviralCompliantNotAntiviraledAsymptomatic;
	}

}
