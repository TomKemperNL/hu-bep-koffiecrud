package jerseydemo;

import domein.KoffieSoort;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

@Path("koffie")
public class KoffieResource {
    @GET
    @Produces("application/json")
    public List<KoffieSoort> getAlleKoffie() {
        System.out.println("Ik zit in een method!");

        return StartupListener.alleKoffie;
    }

    @GET
    @Produces("application/json")
    @Path("{code}")
    public KoffieSoort getKoffie(@PathParam("code") String barcode) {
        for (KoffieSoort kf : StartupListener.alleKoffie) {
            if (kf.getBarcode().equals(barcode)) {
                return kf;
            }
        }
        return null;
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @RolesAllowed("beheerder")
    public void voegSoortToe(
            @Context HttpServletRequest request,
            KoffieSoort kf) {
        StartupListener.alleKoffie.add(kf);
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("{code}")
    @RolesAllowed("beheerder")
    public KoffieSoort updateKoffie(@PathParam("code") String barcode, KoffieSoort gestuurdeKoffie) {
        for (KoffieSoort kf : StartupListener.alleKoffie) {
            if (kf.getBarcode().equals(barcode)) {
                kf.setNaam(gestuurdeKoffie.getNaam());
                kf.setPrijs(gestuurdeKoffie.getPrijs());
                kf.setSterkte(gestuurdeKoffie.getSterkte());
            }
        }
        return null;
    }


    @DELETE
    @Path("{code}")
    @RolesAllowed("beheerder")
    public void verwijderKoffie(@PathParam("code") String barcode) {
        for (KoffieSoort kf : StartupListener.alleKoffie) {
            if (kf.getBarcode().equals(barcode)) {
                StartupListener.alleKoffie.remove(kf);
            }
        }
    }
}
