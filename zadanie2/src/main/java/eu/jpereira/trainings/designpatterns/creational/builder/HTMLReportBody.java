package eu.jpereira.trainings.designpatterns.creational.builder;

import eu.jpereira.trainings.designpatterns.creational.builder.model.ReportBody;
import eu.jpereira.trainings.designpatterns.creational.builder.model.ReportBodyBuilder;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SoldItem;

import java.util.Iterator;

public class HTMLReportBody implements ReportBody {

	private StringBuffer delegate = new StringBuffer();

	@Override
	public Object getAsString() {
		return this.delegate .toString();
	}

	public void putContent(Object content) {
		this.delegate.append(content);
		
	}

	public HTMLReportBodyBuilder builder() {
		return new HTMLReportBodyBuilder(this);
	}

	public static class HTMLReportBodyBuilder extends ReportBodyBuilder {
		private HTMLReportBody reportBody;

		public HTMLReportBodyBuilder(HTMLReportBody htmlReportBody) {
			this.reportBody = htmlReportBody;
		}

		public HTMLReportBody build() {
			HTMLReportBody reportBody = new HTMLReportBody();
			reportBody.putContent("<span class=\"customerName\">");
			reportBody.putContent(this.customer.getName());
			reportBody.putContent("</span><span class=\"customerPhone\">");
			reportBody.putContent(this.customer.getPhone());
			reportBody.putContent("</span>");

			reportBody.putContent("<items>");

			Iterator<SoldItem> it = this.soldItems.iterator();
			while ( it.hasNext() ) {
				SoldItem soldEntry= it.next();
				reportBody.putContent("<item><name>");
				reportBody.putContent(soldEntry.getName());
				reportBody.putContent("</name><quantity>");
				reportBody.putContent(soldEntry.getQuantity());
				reportBody.putContent("</quantity><price>");
				reportBody.putContent(soldEntry.getUnitPrice());
				reportBody.putContent("</price></item>");
			}
			reportBody.putContent("</items>");

			return reportBody;
		}
	}
}
