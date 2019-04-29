/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import model.bean.Veiculo;

/**
 *
 * @author Near
 */
@XmlRootElement(name="pessoas")
@XmlAccessorType(XmlAccessType.FIELD)
public class Veiculos {
    @XmlElement
    List<Veiculo> veiculo = new ArrayList<Veiculo>();

    public List<Veiculo> getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(List<Veiculo> veiculo) {
        this.veiculo = veiculo;
    }
    
}
