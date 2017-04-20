package fr.damienraymond.servicejava.tp3.client.infrastructure.jaxrs;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "liste")
public class HyperLiens<T> {

    private List<HyperLien<T>> liens;

    public HyperLiens() {
    }

    public HyperLiens(List<HyperLien<T>> l) {
        this.liens = l;
    }

    @XmlElement(name = "hyperlien")
    public List<HyperLien<T>> getLiens() {
        return liens;
    }

    public void setLiens(List<HyperLien<T>> l) {
        this.liens = l;
    }

    public String toString() {
        String res = "";
        for (HyperLien<T> l : liens) {
            res = res + " " + l.toString();
        }
        return res;
    }
}