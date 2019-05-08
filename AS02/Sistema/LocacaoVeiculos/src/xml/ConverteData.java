/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Near
 */
public class ConverteData implements Converter {

    @Override
    public void marshal(Object o, HierarchicalStreamWriter writer, MarshallingContext mc) {
        Date data = (Date) o;
        String dataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(data);
        writer.setValue(dataFormatada);
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext uc) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        try {
           data = (Date) sdf.parse(reader.getValue());
        } catch (ParseException ex) {
            Logger.getLogger(ConverteData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    @Override
    public boolean canConvert(Class type) {
        return type.equals(Date.class);
    }
    
}
