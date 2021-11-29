package eu.jpereira.trainings.designpatterns.creational.abstractfactory;

import eu.jpereira.trainings.designpatterns.creational.abstractfactory.json.JSONReportFactory;
import eu.jpereira.trainings.designpatterns.creational.abstractfactory.xml.XMLReportFactory;

public class FactoryProvider {
    public static AbstractReportFactory getFactory(String reportType) {
        if (reportType.equals("JSON")) {
            return new JSONReportFactory();
        } else {
            return new XMLReportFactory();
        }
    }
}
