/**
 * Copyright 2011 Joao Miguel Pereira
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package eu.jpereira.trainings.designpatterns.creational.builder.xml;

import eu.jpereira.trainings.designpatterns.creational.builder.json.JSONReportBody;
import eu.jpereira.trainings.designpatterns.creational.builder.model.ReportBody;
import eu.jpereira.trainings.designpatterns.creational.builder.model.ReportBodyBuilder;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SoldItem;

import java.util.Iterator;

/**
 * This class is for demonstration purpose only!!
 * 
 * @author jpereira
 * 
 */
public class XMLReportBody implements ReportBody {

	private StringBuilder stringBuilder = new StringBuilder();

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.jpereira.trainings.designpatterns.creational.builder.ReportBody#
	 * getAsString()
	 */
	@Override
	public Object getAsString() {

		return stringBuilder.toString();
	}

	public void putContent(Object content) {
		this.stringBuilder.append(content);
	}

	public XMLReportBodyBuilder builder() {
		return new XMLReportBodyBuilder(this);
	}

	public static class XMLReportBodyBuilder extends ReportBodyBuilder {
		private XMLReportBody reportBody;

		public XMLReportBodyBuilder(XMLReportBody xmlReportBody) {
			this.reportBody = xmlReportBody;
		}

		public XMLReportBody build() {
			XMLReportBody reportBody = new XMLReportBody();
			reportBody.putContent("<sale><customer><name>");
			reportBody.putContent(this.customer.getName());
			reportBody.putContent("</name><phone>");
			reportBody.putContent(this.customer.getPhone());
			reportBody.putContent("</phone></customer>");

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
			reportBody.putContent("</items></sale>");

			return reportBody;
		}
	}
	
}
