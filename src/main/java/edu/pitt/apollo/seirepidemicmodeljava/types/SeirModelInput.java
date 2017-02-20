package edu.pitt.apollo.seirepidemicmodeljava.types;

/**
 *
 * Author: Nick Millett
 * Email: nick.millett@gmail.com
 * Date: Mar 1, 2013
 * Time: 12:28:27 PM
 * Class: SeirModelInput
 * IDE: NetBeans 6.9.1
 */
public class SeirModelInput {

    private double initialSusceptible;
    private double initialExposed;
    private double initialInfectious;
    private double initialRecovered;
    private double reproductionNumber;
    private double infectiousPeriod;
    private double latentPeriod;
    private double vaccineEfficacy;
    private double asymptomaticInfectionFraction;
    private double antiviralEfficacy;
    private double antiviralEfficacyDelay;
    private double vaccineEfficacyDelay;
    private int runLength;
    private double antiviralCompliance;
    private double vaccineCompliance;
    private int[] vaccineAdminSchedule;
    private int[] antiviralAdminSchedule;
    private int[] vaccineSupplySchedule;
    private int[] antiviralSupplySchedule;

    /**
     * @return the reproductionNumber
     */
    public double getReproductionNumber() {
        return reproductionNumber;
    }

    /**
     * @param reproductionNumber the reproductionNumber to set
     */
    public void setReproductionNumber(double reproductionNumber) {
        this.reproductionNumber = reproductionNumber;
    }

    /**
     * @return the infectiousPeriod
     */
    public double getInfectiousPeriod() {
        return infectiousPeriod;
    }

    /**
     * @param infectiousPeriod the infectiousPeriod to set
     */
    public void setInfectiousPeriod(double infectiousPeriod) {
        this.infectiousPeriod = infectiousPeriod;
    }

    /**
     * @return the latentPeriod
     */
    public double getLatentPeriod() {
        return latentPeriod;
    }

    /**
     * @param latentPeriod the latentPeriod to set
     */
    public void setLatentPeriod(double latentPeriod) {
        this.latentPeriod = latentPeriod;
    }

    /**
     * @return the vaccineEfficacy
     */
    public double getVaccineEfficacy() {
        return vaccineEfficacy;
    }

    /**
     * @param vaccineEfficacy the vaccineEfficacy to set
     */
    public void setVaccineEfficacy(Double vaccineEfficacy) {
        if (vaccineEfficacy != null) {
            this.vaccineEfficacy = vaccineEfficacy;
        }
    }

    /**
     * @return the asymptomaticInfectionFraction
     */
    public double getAsymptomaticInfectionFraction() {
        return asymptomaticInfectionFraction;
    }

    /**
     * @param asymptomaticInfectionFraction the asymptomaticInfectionFraction to set
     */
    public void setAsymptomaticInfectionFraction(Double asymptomaticInfectionFraction) {
        if (asymptomaticInfectionFraction != null) {
            this.asymptomaticInfectionFraction = asymptomaticInfectionFraction;
        }
    }

    /**
     * @return the antiviralEfficacy
     */
    public double getAntiviralEfficacy() {
        return antiviralEfficacy;
    }

    /**
     * @param antiviralEfficacy the antiviralEfficacy to set
     */
    public void setAntiviralEfficacy(Double antiviralEfficacy) {
        if (antiviralEfficacy != null) {
            this.antiviralEfficacy = antiviralEfficacy;
        }
    }

    /**
     * @return the antiviralEfficacyDelay
     */
    public double getAntiviralEfficacyDelay() {
        return antiviralEfficacyDelay;
    }

    /**
     * @param antiviralEfficacyDelay the antiviralEfficacyDelay to set
     */
    public void setAntiviralEfficacyDelay(Double antiviralEfficacyDelay) {
        if (antiviralEfficacyDelay != null) {
            this.antiviralEfficacyDelay = antiviralEfficacyDelay;
        }
    }

    /**
     * @return the vaccineEfficacyDelay
     */
    public double getVaccineEfficacyDelay() {
        return vaccineEfficacyDelay;
    }

    /**
     * @param vaccineEfficacyDelay the vaccineEfficacyDelay to set
     */
    public void setVaccineEfficacyDelay(Double vaccineEfficacyDelay) {
        if (vaccineEfficacyDelay != null) {
            this.vaccineEfficacyDelay = vaccineEfficacyDelay;
        }
    }

    /**
     * @return the runLength
     */
    public int getRunLength() {
        return runLength;
    }

    /**
     * @param runLength the runLength to set
     */
    public void setRunLength(int runLength) {
        this.runLength = runLength;
    }

    /**
     * @return the antiviralCompliance
     */
    public double getAntiviralCompliance() {
        return antiviralCompliance;
    }

    /**
     * @param antiviralCompliance the antiviralCompliance to set
     */
    public void setAntiviralCompliance(Double antiviralCompliance) {
        if (antiviralCompliance != null) {
            this.antiviralCompliance = antiviralCompliance;
        }
    }

    /**
     * @return the vaccineCompliance
     */
    public double getVaccineCompliance() {
        return vaccineCompliance;
    }

    /**
     * @param vaccineCompliance the vaccineCompliance to set
     */
    public void setVaccineCompliance(Double vaccineCompliance) {
        if (vaccineCompliance != null) {
            this.vaccineCompliance = vaccineCompliance;
        }
    }

    /**
     * @return the initialSusceptible
     */
    public double getInitialSusceptible() {
        return initialSusceptible;
    }

    /**
     * @param initialSusceptible the initialSusceptible to set
     */
    public void setInitialSusceptible(double initialSusceptible) {
        this.initialSusceptible = initialSusceptible;
    }

    /**
     * @return the initialExposed
     */
    public double getInitialExposed() {
        return initialExposed;
    }

    /**
     * @param initialExposed the initialExposed to set
     */
    public void setInitialExposed(double initialExposed) {
        this.initialExposed = initialExposed;
    }

    /**
     * @return the initialInfectious
     */
    public double getInitialInfectious() {
        return initialInfectious;
    }

    /**
     * @param initialInfectious the initialInfectious to set
     */
    public void setInitialInfectious(double initialInfectious) {
        this.initialInfectious = initialInfectious;
    }

    /**
     * @return the initialRecovered
     */
    public double getInitialRecovered() {
        return initialRecovered;
    }

    /**
     * @param initialRecovered the initialRecovered to set
     */
    public void setInitialRecovered(double initialRecovered) {
        this.initialRecovered = initialRecovered;
    }

    public int[] getAntiviralAdminSchedule() {
        return antiviralAdminSchedule;
    }

    public void setAntiviralAdminSchedule(int[] antiviralAdminSchedule) {
        this.antiviralAdminSchedule = antiviralAdminSchedule;
    }

    public int[] getAntiviralSupplySchedule() {
        return antiviralSupplySchedule;
    }

    public void setAntiviralSupplySchedule(int[] antiviralSupplySchedule) {
        this.antiviralSupplySchedule = antiviralSupplySchedule;
    }

    public int[] getVaccineAdminSchedule() {
        return vaccineAdminSchedule;
    }

    public void setVaccineAdminSchedule(int[] vaccineAdminSchedule) {
        this.vaccineAdminSchedule = vaccineAdminSchedule;
    }

    public int[] getVaccineSupplySchedule() {
        return vaccineSupplySchedule;
    }

    public void setVaccineSupplySchedule(int[] vaccineSupplySchedule) {
        this.vaccineSupplySchedule = vaccineSupplySchedule;
    }

}
