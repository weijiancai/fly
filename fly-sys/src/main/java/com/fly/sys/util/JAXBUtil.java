package com.fly.sys.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.StringWriter;

/**
 * @author weijiancai
 * @version 1.0.0
 */
public class JAXBUtil {
    public static String marshal(Object obj) throws Exception {
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

        StringWriter sw = new StringWriter();
        marshaller.marshal(obj, sw);

        return sw.toString();
    }

    public static void marshal(Object obj, File file, Class<?>... classes) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(classes);
        Marshaller marshaller = getMarshaller(context);
        marshaller.marshal(obj, file);
    }

    private static Marshaller getMarshaller(JAXBContext context) throws JAXBException {
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        return marshaller;
    }
}
