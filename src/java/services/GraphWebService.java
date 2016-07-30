package services;

import java.io.IOException;
import java.io.OutputStream;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author James
 */
@Path("/")
public class GraphWebService {

    public GraphWebService() {
        System.out.println("This is a test!!");
    }

    /**
     *
     * @param region
     */
    @GET
    @Path("righthousingincome.png")
    @Produces("image/png")
    public Response rightHousingGraph(final @QueryParam("right") String region) {
        StreamingOutput stream = new StreamingOutput() {
            @Override
            public void write(OutputStream os) throws IOException,
                    WebApplicationException {
                synchronized (CareerMove.lock) {
                    try {

                        GovHack2.saveJPEG(region, os);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    try {
                        Thread.sleep(900);
                    } catch (InterruptedException ie) {
                    }
                }
                os.flush();
                os.close();
            }
        };
        MediaType m = new MediaType("image", "image/png");
        return Response.ok(stream).type("image/png").build();
    }

    /**
     *
     * @param region
     */
    @GET
    @Path("lefthousingincome.png")
    @Produces("image/png")
    public Response leftHousingGraph(final @QueryParam("left") String region) {
        StreamingOutput stream = new StreamingOutput() {
            @Override
            public void write(OutputStream os) throws IOException,
                    WebApplicationException {
                synchronized (CareerMove.lock) {
                    try {
                        GovHack2.saveJPEG(region, os);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    os.flush();
                    os.close();
                    try {
                        Thread.sleep(900);
                    } catch (InterruptedException ie) {
                    }
                }

            }
        };
        MediaType m = new MediaType("image", "image/png");
        return Response.ok(stream).type("image/png").build();
    }

    @GET
    @Path("ito.png")
    @Produces("image/png")
    public Response ito(final @QueryParam("left") String left, final @QueryParam("right") String right, final @QueryParam("industry") String industry) {
        StreamingOutput stream = new StreamingOutput() {
            @Override
            public void write(OutputStream os) throws IOException,
                    WebApplicationException {
                synchronized (CareerMove.lock) {

                    try {
                        GovHack1.saveJPEG(left, right, industry, os);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    os.flush();
                    os.close();
                    try {
                        Thread.sleep(900);
                    } catch (InterruptedException ie) {
                    }
                }

            }
        };
        MediaType m = new MediaType("image", "image/png");
        return Response.ok(stream).type("image/png").build();
    }

    @GET
    @Path("iva.png")
    @Produces("image/png")
    public Response iva(final @QueryParam("left") String left, final @QueryParam("right") String right, final @QueryParam("industry") String industry) {
        StreamingOutput stream = new StreamingOutput() {
            @Override
            public void write(OutputStream os) throws IOException,
                    WebApplicationException {
                synchronized (CareerMove.lock) {
                    try {
                        GovHack3.saveJPEG(left, right, industry, os);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    os.flush();
                    os.close();
                    try {
                        Thread.sleep(900);
                    } catch (InterruptedException ie) {
                    }
                }

            }
        };
        MediaType m = new MediaType("image", "image/png");
        return Response.ok(stream).type("image/png").build();
    }

    @GET
    @Path("fte.png")
    @Produces("image/png")
    public Response fte(final @QueryParam("left") String left, final @QueryParam("right") String right, final @QueryParam("industry") String industry) {
        StreamingOutput stream = new StreamingOutput() {
            @Override
            public void write(OutputStream os) throws IOException,
                    WebApplicationException {
                synchronized (CareerMove.lock) {
                    try {
                        GovHack4.saveJPEG(left, right, industry, os);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    os.flush();
                    os.close();
                    try {
                        Thread.sleep(900);
                    } catch (InterruptedException ie) {
                    }
                }

            }
        };
        MediaType m = new MediaType("image", "image/png");
        return Response.ok(stream).type("image/png").build();
    }

    @GET
    @Path("unemp.png")
    @Produces("image/png")
    public Response fte(final @QueryParam("left") String left, final @QueryParam("right") String right, final @QueryParam("industry") String industry, final @QueryParam("anzsco") String anzsco) {
        StreamingOutput stream = new StreamingOutput() {
            @Override
            public void write(OutputStream os) throws IOException,
                    WebApplicationException {
                try {
                    UnempRate.savePNG(left, right, industry, anzsco, os);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                os.flush();
                os.close();
            }
        };
        MediaType m = new MediaType("image", "image/png");
        return Response.ok(stream)
                .type("image/png").build();
    }
}
