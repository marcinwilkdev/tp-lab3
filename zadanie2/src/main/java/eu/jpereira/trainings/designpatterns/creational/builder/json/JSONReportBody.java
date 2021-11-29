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
package eu.jpereira.trainings.designpatterns.creational.builder.json;

import eu.jpereira.trainings.designpatterns.creational.builder.model.ReportBody;
import eu.jpereira.trainings.designpatterns.creational.builder.model.ReportBodyBuilder;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SoldItem;

import java.util.Iterator;

/**
 * For training purposes only!
 * @author jpereira
 *
 */
public class JSONReportBody implements ReportBody{

	private StringBuilder stringBuilder = new StringBuilder();
	/* (non-Javadoc)
	 * @see eu.jpereira.trainings.designpatterns.creational.builder.ReportBody#getAsString()
	 */
	@Override
	public Object getAsString() {
		return this.stringBuilder.toString();
	}

	/**
	 * @param content
	 */
	public void addContent(String content) {
		stringBuilder.append(content);
		
	}

	public JSONReportBodyBuilder builder() {
		return new JSONReportBodyBuilder(this);
	}

	public static class JSONReportBodyBuilder extends ReportBodyBuilder {
		private JSONReportBody reportBody;

		public JSONReportBodyBuilder(JSONReportBody jsonReportBody) {
			this.reportBody = jsonReportBody;
		}

		public JSONReportBody build() {
			//Add customer info
			reportBody.addContent("sale:{customer:{");
			reportBody.addContent("name:\"");
			reportBody.addContent(this.customer.getName());
			reportBody.addContent("\",phone:\"");
			reportBody.addContent(this.customer.getPhone());
			reportBody.addContent("\"}");
			//add array of items
			reportBody.addContent(",items:[");
			Iterator<SoldItem> it = this.soldItems.iterator();
			while ( it.hasNext() ) {
				SoldItem item = it.next();
				reportBody.addContent("{name:\"");
				reportBody.addContent(item.getName());
				reportBody.addContent("\",quantity:");
				reportBody.addContent(String.valueOf(item.getQuantity()));
				reportBody.addContent(",price:");
				reportBody.addContent(String.valueOf(item.getUnitPrice()));
				reportBody.addContent("}");
				if ( it.hasNext() ) {
					reportBody.addContent(",");
				}

			}
			reportBody.addContent("]}");

			return reportBody;
		}
	}
}
