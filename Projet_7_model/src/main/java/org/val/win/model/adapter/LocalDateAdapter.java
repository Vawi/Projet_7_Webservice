package org.val.win.model.adapter;

import org.joda.time.LocalDate;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapter pour transformer les Local£Date de joda.time en XMLGregorianDate
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

        public LocalDate unmarshal(String v) throws Exception {
            return new LocalDate(v);
        }

        public String marshal(LocalDate v) throws Exception {
            return v.toString();
        }
}
