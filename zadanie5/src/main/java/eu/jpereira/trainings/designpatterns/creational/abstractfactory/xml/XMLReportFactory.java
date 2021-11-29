package eu.jpereira.trainings.designpatterns.creational.abstractfactory.xml;

import eu.jpereira.trainings.designpatterns.creational.abstractfactory.AbstractReportFactory;
import eu.jpereira.trainings.designpatterns.creational.abstractfactory.Report;

public class XMLReportFactory extends Report implements AbstractReportFactory {
    @Override
    public Report create() {
        Report report = new Report();

        report.setReportType("XML");

        report.setBody(new XMLReportBody());
        report.setFooter(new XMLReportFooter());
        report.setHeader(new XMLReportHeader());

        return report;
    }
}
