
package hotelHilton;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "HotelHilton", targetNamespace = "HotelHilton", wsdlLocation = "file:/home/dafarache/NetBeansProjects/DsSys_Assignment2_Client/wsdlFile/HotelHilton.wsdl")
public class HotelHilton
    extends Service
{

    private final static URL HOTELHILTON_WSDL_LOCATION;
    private final static WebServiceException HOTELHILTON_EXCEPTION;
    private final static QName HOTELHILTON_QNAME = new QName("HotelHilton", "HotelHilton");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/home/dafarache/NetBeansProjects/DsSys_Assignment2_Client/wsdlFile/HotelHilton.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        HOTELHILTON_WSDL_LOCATION = url;
        HOTELHILTON_EXCEPTION = e;
    }

    public HotelHilton() {
        super(__getWsdlLocation(), HOTELHILTON_QNAME);
    }

    public HotelHilton(WebServiceFeature... features) {
        super(__getWsdlLocation(), HOTELHILTON_QNAME, features);
    }

    public HotelHilton(URL wsdlLocation) {
        super(wsdlLocation, HOTELHILTON_QNAME);
    }

    public HotelHilton(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, HOTELHILTON_QNAME, features);
    }

    public HotelHilton(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public HotelHilton(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns HotelHiltonPort
     */
    @WebEndpoint(name = "HotelHiltonBinding")
    public HotelHiltonPort getHotelHiltonBinding() {
        return super.getPort(new QName("HotelHilton", "HotelHiltonBinding"), HotelHiltonPort.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns HotelHiltonPort
     */
    @WebEndpoint(name = "HotelHiltonBinding")
    public HotelHiltonPort getHotelHiltonBinding(WebServiceFeature... features) {
        return super.getPort(new QName("HotelHilton", "HotelHiltonBinding"), HotelHiltonPort.class, features);
    }

    private static URL __getWsdlLocation() {
        if (HOTELHILTON_EXCEPTION!= null) {
            throw HOTELHILTON_EXCEPTION;
        }
        return HOTELHILTON_WSDL_LOCATION;
    }

}
