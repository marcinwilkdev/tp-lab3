package eu.jpereira.trainings.designpatterns.creational.abstractfactory.json;

import eu.jpereira.trainings.designpatterns.creational.abstractfactory.AbstractReportFactory;
import eu.jpereira.trainings.designpatterns.creational.abstractfactory.Report;

public class JSONReportFactory extends Report implements AbstractReportFactory {
    @Override
    public Report create() {
        Report report = new Report();

        report.setReportType("JSON");

        report.setFooter(new JSONReportFooter());
        report.setHeader(new JSONReportHeader());
        report.setBody(new JSONReportBody());

        return report;
    }
}
