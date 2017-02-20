package edu.pitt.apollo.seirepidemicmodeljava.file;

import edu.pitt.apollo.seirepidemicmodeljava.exception.SeirEpidemicModelException;
import edu.pitt.apollo.seirepidemicmodeljava.types.SeirModelInput;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * Author: Nick Millett
 * Email: nick.millett@gmail.com
 * Date: Mar 12, 2014
 * Time: 5:42:38 PM
 * Class: ConfigurationFileLoader
 * IDE: NetBeans 6.9.1
 */
public class ConfigurationFileLoader {

    // required double parameters
    private static final String INITIAL_POPULATION = "init_SEIR_counts";
    private static final String REPRODUCTION_NUMBER = "r0";
    private static final String INFECTIOUS_PERIOD = "infectious_period";
    private static final String LATENT_PERIOD = "latent_period";
    private static final String ASYMPTOMATIC_INFECTION_FRACTION = "asymptomatic_fraction";
    // optional double parameters
    private static final String VACCINE_EFFICACY = "vaccination_efficacy";
    private static final String VACCINE_EFFICACY_DELAY = "vaccination_efficacy_delay";
    private static final String VACCINATION_COMPLIANCE = "vaccination_compliance";
    private static final String ANTIVIRAL_EFFICACY = "antiviral_treatment_efficacy";
    private static final String ANTIVIRAL_EFFICACY_DELAY = "antiviral_treatment_efficacy_delay";
    private static final String ANTIVIRAL_COMPLIANCE = "antiviral_treatment_compliance";
    // required integer parameters
    private static final String RUN_LENGTH = "run_length";
    // optional integer array parameters
    private static final String VACCINATION_ADMIN_SCHEDULE = "vaccination_admin_schedule";
    private static final String VACCINATION_SUPPLY_SCHEDULE = "vaccination_supply_schedule";
    private static final String ANTIVIRAL_ADMIN_SCHEDULE = "antiviral_admin_schedule";
    private static final String ANTIVIRAL_SUPPLY_SCHEDULE = "antiviral_supply_schedule";
    // maps for storing properties of the different types
    private static final Map<String, Boolean> DOUBLE_PROPERTIES;
    private static final Map<String, Boolean> DOUBLE_ARRAY_PROPERTIES;
    private static final Map<String, Boolean> INTEGER_PROPERTIES;
    private static final Map<String, Boolean> INTEGER_ARRAY_PROPERTIES;

    static {
        DOUBLE_PROPERTIES = new HashMap<String, Boolean>();
        DOUBLE_PROPERTIES.put(REPRODUCTION_NUMBER, Boolean.TRUE);
        DOUBLE_PROPERTIES.put(INFECTIOUS_PERIOD, Boolean.TRUE);
        DOUBLE_PROPERTIES.put(LATENT_PERIOD, Boolean.TRUE);
        DOUBLE_PROPERTIES.put(ASYMPTOMATIC_INFECTION_FRACTION, Boolean.FALSE);
        DOUBLE_PROPERTIES.put(VACCINE_EFFICACY, Boolean.FALSE);
        DOUBLE_PROPERTIES.put(VACCINE_EFFICACY_DELAY, Boolean.FALSE);
        DOUBLE_PROPERTIES.put(VACCINATION_COMPLIANCE, Boolean.FALSE);
        DOUBLE_PROPERTIES.put(ANTIVIRAL_EFFICACY, Boolean.FALSE);
        DOUBLE_PROPERTIES.put(ANTIVIRAL_EFFICACY_DELAY, Boolean.FALSE);
        DOUBLE_PROPERTIES.put(ANTIVIRAL_COMPLIANCE, Boolean.FALSE);

        DOUBLE_ARRAY_PROPERTIES = new HashMap<String, Boolean>();
        DOUBLE_ARRAY_PROPERTIES.put(INITIAL_POPULATION, Boolean.TRUE);

        INTEGER_PROPERTIES = new HashMap<String, Boolean>();
        INTEGER_PROPERTIES.put(RUN_LENGTH, Boolean.TRUE);

        INTEGER_ARRAY_PROPERTIES = new HashMap<String, Boolean>();
        INTEGER_ARRAY_PROPERTIES.put(VACCINATION_ADMIN_SCHEDULE, Boolean.FALSE);
        INTEGER_ARRAY_PROPERTIES.put(VACCINATION_SUPPLY_SCHEDULE, Boolean.FALSE);
        INTEGER_ARRAY_PROPERTIES.put(ANTIVIRAL_ADMIN_SCHEDULE, Boolean.FALSE);
        INTEGER_ARRAY_PROPERTIES.put(ANTIVIRAL_SUPPLY_SCHEDULE, Boolean.FALSE);
    }

    private static void setDoubleValues(Map<String, Double> map, SeirModelInput input) {

        // required properties
        input.setReproductionNumber(map.get(REPRODUCTION_NUMBER));
        input.setInfectiousPeriod(map.get(INFECTIOUS_PERIOD));
        input.setLatentPeriod(map.get(LATENT_PERIOD));
        input.setAsymptomaticInfectionFraction(map.get(ASYMPTOMATIC_INFECTION_FRACTION));
        input.setVaccineEfficacyDelay(map.get(VACCINE_EFFICACY_DELAY));
        input.setAntiviralEfficacy(map.get(ANTIVIRAL_EFFICACY));
        input.setAntiviralEfficacyDelay(map.get(ANTIVIRAL_EFFICACY_DELAY));
        input.setAntiviralCompliance(map.get(ANTIVIRAL_COMPLIANCE));
        // optional properties
        // if they are not specified in the map (i.e. null), the input will
        // set them to zero
        input.setVaccineEfficacy(map.get(VACCINE_EFFICACY));
        input.setVaccineCompliance(map.get(VACCINATION_COMPLIANCE));
    }

    private static void setIntegerValues(Map<String, Integer> map, SeirModelInput input) {
        // required properties
        input.setRunLength(map.get(RUN_LENGTH));
    }

    private static void setIntegerArrayValues(Map<String, int[]> map, SeirModelInput input) {
        // optional properties
        input.setVaccineAdminSchedule(map.get(VACCINATION_ADMIN_SCHEDULE));
        input.setVaccineSupplySchedule(map.get(VACCINATION_SUPPLY_SCHEDULE));
        input.setAntiviralAdminSchedule(map.get(ANTIVIRAL_ADMIN_SCHEDULE));
        input.setAntiviralSupplySchedule(map.get(ANTIVIRAL_SUPPLY_SCHEDULE));
    }

    private static void setDoubleArrayValues(Map<String, double[]> map, SeirModelInput input) {
        input.setInitialSusceptible(map.get(INITIAL_POPULATION)[0]);
        input.setInitialExposed(map.get(INITIAL_POPULATION)[1]);
        input.setInitialInfectious(map.get(INITIAL_POPULATION)[2]);
        input.setInitialRecovered(map.get(INITIAL_POPULATION)[3]);
    }

    public static SeirModelInput loadConfigurationfile(String fileContents) throws IOException, SeirEpidemicModelException {

        InputStream is = new ByteArrayInputStream(fileContents.getBytes());

        return loadConfigurationFile(is);
    }

    public static SeirModelInput loadConfigurationFile(URL url) throws SeirEpidemicModelException {
        try {
            InputStream is = url.openStream();
            return loadConfigurationFile(is);
        } catch (IOException ex) {
            throw new SeirEpidemicModelException("Error: IO exception opening stream from url \"" + url + "\" to get input file: " + ex.getMessage());
        }
    }

    private static SeirModelInput loadConfigurationFile(InputStream configurationFileInputStream) throws IOException, SeirEpidemicModelException {

        Properties properties = new Properties();
        properties.load(configurationFileInputStream);

        SeirModelInput input = new SeirModelInput();

        Map<String, Double> doubleValuesMap = new HashMap<String, Double>();
        // check required properties
        for (String property : DOUBLE_PROPERTIES.keySet()) {
            if (DOUBLE_PROPERTIES.get(property)
                    && !properties.containsKey(property)) {
                throw new SeirEpidemicModelException("Error: required property \"" + property + "\" was not found in the configuration file");
            }

            if (!properties.containsKey(property)) {
                continue; // skip optional properties which weren't set
            }

            double value;
            String propertyValue = properties.getProperty(property);
            try {
                value = Double.parseDouble(propertyValue);
            } catch (NumberFormatException ex) {
                throw new SeirEpidemicModelException("Error: the value \"" + propertyValue + "\" of property \"" + property + "\" could not be parsed as a double value");
            }

            doubleValuesMap.put(property, value);
        }

        Map<String, Integer> intValuesMap = new HashMap<String, Integer>();
        for (String property : INTEGER_PROPERTIES.keySet()) {
            if (INTEGER_PROPERTIES.get(property)
                    && !properties.containsKey(property)) {
                throw new SeirEpidemicModelException("Error: required property \"" + property + "\" was not found in the configuration file");
            }

            if (!properties.containsKey(property)) {
                continue; // skip optional properties which weren't set
            }

            int value;
            String propertyValue = properties.getProperty(property);
            try {
                value = Integer.parseInt(propertyValue);
            } catch (NumberFormatException ex) {
                throw new SeirEpidemicModelException("Error: the value \"" + propertyValue + "\" of property " + property + "\" could not be parsed as an integer value");
            }

            intValuesMap.put(property, value);
        }

        // set these values now since the run length will be
        // needed to check the array lengths
        setDoubleValues(doubleValuesMap, input);
        setIntegerValues(intValuesMap, input);

        Map<String, double[]> doubleArrayValuesMap = new HashMap<String, double[]>();
        for (String property : DOUBLE_ARRAY_PROPERTIES.keySet()) {
            if (DOUBLE_ARRAY_PROPERTIES.get(property)
                    && !properties.containsKey(property)) {
                throw new SeirEpidemicModelException("Error: required property \"" + property + "\" was not found in the configuration file");
            }

            if (!properties.containsKey(property)) {
                continue; // skip optional properties which weren't set
            }

            String[] values = properties.getProperty(property).split(","); // split the csv values
            if (property.equals(INITIAL_POPULATION) && values.length != 4) {
                throw new SeirEpidemicModelException("Error: the initial SEIR counts list did not contain exactly 4 values");
            }

            double[] doubleValues = new double[values.length];
            for (int i = 0; i < values.length; i++) {
                try {
                    double doubleValue = Double.parseDouble(values[i]);
                    doubleValues[i] = doubleValue;
                } catch (NumberFormatException ex) {
                    throw new SeirEpidemicModelException("Error: value \"" + values[i] + "\" in the time series for property \"" + property + "\" could not be parsed as a double");
                }
            }

            doubleArrayValuesMap.put(property, doubleValues);
        }

        setDoubleArrayValues(doubleArrayValuesMap, input);

        // already been checked and set
        Map<String, int[]> intArrayValuesMap = new HashMap<String, int[]>();
        for (String property : INTEGER_ARRAY_PROPERTIES.keySet()) {
            // none of these are required currently, but check anyway
            if (INTEGER_ARRAY_PROPERTIES.get(property)
                    && !properties.containsKey(property)) {
                throw new SeirEpidemicModelException("Error: required property \"" + property + "\" was not found in the configuration file");
            }

            if (!properties.containsKey(property)) {
                continue; // skip optional properties which weren't set
            }

            String[] values = properties.getProperty(property).split(","); // split the csv time series
			
            int[] intValues = new int[values.length];
            for (int i = 0; i < values.length; i++) {
                try {
                    int intValue = Integer.parseInt(values[i]);
                    intValues[i] = intValue;
                } catch (NumberFormatException ex) {
                    throw new SeirEpidemicModelException("Error: value \"" + values[i] + "\" in the time series for property \"" + property + "\" could not be parsed as an integer");
                }
            }

            intArrayValuesMap.put(property, intValues);
        }

        setIntegerArrayValues(intArrayValuesMap, input);

        return input;
    }
}
