package services;

import entities.RegionData;
import java.io.IOException;
import java.io.OutputStream;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import org.apache.tomcat.util.http.fileupload.IOUtils;

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
                        try {
                            IOUtils.copy(CareerMove.class.getResourceAsStream("error.png"), os);
                        } catch (Exception ex2) {
                        }
                    } finally {
                        os.flush();
                        os.close();
                        try {
                            Thread.sleep(900);
                        } catch (InterruptedException ie) {
                        }
                    }
                }
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
                        try {
                            IOUtils.copy(CareerMove.class.getResourceAsStream("error.png"), os);
                        } catch (Exception ex2) {
                        }
                    } finally {
                        os.flush();
                        os.close();
                        try {
                            Thread.sleep(900);
                        } catch (InterruptedException ie) {
                        }
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
                        try {
                            IOUtils.copy(CareerMove.class.getResourceAsStream("error.png"), os);
                        } catch (Exception ex2) {
                        }
                    } finally {
                        os.flush();
                        os.close();
                        try {
                            Thread.sleep(900);
                        } catch (InterruptedException ie) {
                        }
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
                        try {
                            IOUtils.copy(CareerMove.class.getResourceAsStream("error.png"), os);
                        } catch (Exception ex2) {
                        }
                    } finally {
                        os.flush();
                        os.close();
                        try {
                            Thread.sleep(900);
                        } catch (InterruptedException ie) {
                        }
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
    public Response fte(final @QueryParam("left") String left, final @QueryParam("right") String right, final @QueryParam("industry") String industry, final @QueryParam("occupation") String anzsco) {
        StreamingOutput stream;
        stream = new StreamingOutput() {
            @Override
            public void write(OutputStream os) throws IOException,
                    WebApplicationException {
                try {
                    UnempRate.savePNG(left, right, industry, anzsco, os);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    try {
                        IOUtils.copy(CareerMove.class.getResourceAsStream("error.png"), os);
                    } catch (Exception ex2) {
                    }
                } finally {
                    os.flush();
                    os.close();
                }
            }
        };
        MediaType m = new MediaType("image", "image/png");
        return Response.ok(stream)
                .type("image/png").build();
    }
    @GET
    @Path("houseprice.png")
    @Produces("image/png")
    public Response housePrice(final @QueryParam("left") String left, final @QueryParam("right") String right, final @QueryParam("industry") String industry, final @QueryParam("occupation") String anzsco) {
        StreamingOutput stream;
        stream = new StreamingOutput() {
            @Override
            public void write(OutputStream os) throws IOException,
                    WebApplicationException {
                try {
                    HousePrice.savePNG(left, right, industry, anzsco, os);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    try {
                        IOUtils.copy(CareerMove.class.getResourceAsStream("error.png"), os);
                    } catch (Exception ex2) {
                    }
                } finally {
                    os.flush();
                    os.close();
                }
            }
        };
        MediaType m = new MediaType("image", "image/png");
        return Response.ok(stream)
                .type("image/png").build();
    }
    @GET
    @Path("averageannualmvmt.png")
    @Produces("image/png")
    public Response averageAnnualMvmt(final @QueryParam("left") String left, final @QueryParam("right") String right, final @QueryParam("industry") String industry, final @QueryParam("occupation") String anzsco) {
        StreamingOutput stream;
        stream = new StreamingOutput() {
            @Override
            public void write(OutputStream os) throws IOException,
                    WebApplicationException {
                try {
                    HousePrice.savePNG(left, right, industry, anzsco, os);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    try {
                        IOUtils.copy(CareerMove.class.getResourceAsStream("error.png"), os);
                    } catch (Exception ex2) {
                    }
                } finally {
                    os.flush();
                    os.close();
                }
            }
        };
        MediaType m = new MediaType("image", "image/png");
        return Response.ok(stream)
                .type("image/png").build();
    }
    @GET
    @Path("rentprice.png")
    @Produces("image/png")
    public Response rentPrice(final @QueryParam("left") String left, final @QueryParam("right") String right, final @QueryParam("industry") String industry, final @QueryParam("occupation") String anzsco) {
        StreamingOutput stream;
        stream = new StreamingOutput() {
            @Override
            public void write(OutputStream os) throws IOException,
                    WebApplicationException {
                try {
                    RentPrice.savePNG(left, right, industry, anzsco, os);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    try {
                        IOUtils.copy(CareerMove.class.getResourceAsStream("error.png"), os);
                    } catch (Exception ex2) {
                    }
                } finally {
                    os.flush();
                    os.close();
                }
            }
        };
        MediaType m = new MediaType("image", "image/png");
        return Response.ok(stream)
                .type("image/png").build();
    }
    @GET
    @Path("medianincome.png")
    @Produces("image/png")
    public Response medianIncome(final @QueryParam("left") String left, final @QueryParam("right") String right, final @QueryParam("industry") String industry, final @QueryParam("occupation") String anzsco) {
        StreamingOutput stream;
        stream = new StreamingOutput() {
            @Override
            public void write(OutputStream os) throws IOException,
                    WebApplicationException {
                try {
                    MedianIncome.savePNG(left, right, industry, anzsco, os);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    try {
                        IOUtils.copy(CareerMove.class.getResourceAsStream("error.png"), os);
                    } catch (Exception ex2) {
                    }
                } finally {
                    os.flush();
                    os.close();
                }
            }
        };
        MediaType m = new MediaType("image", "image/png");
        return Response.ok(stream)
                .type("image/png").build();
    }
    @GET
    @Path("patentincrease.png")
    @Produces("image/png")
    public Response patentIncrease(final @QueryParam("left") String left, final @QueryParam("right") String right, final @QueryParam("industry") String industry, final @QueryParam("occupation") String anzsco) {
        StreamingOutput stream;
        stream = new StreamingOutput() {
            @Override
            public void write(OutputStream os) throws IOException,
                    WebApplicationException {
                try {
                    PatentIncrease.savePNG(left, right, industry, anzsco, os);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    try {
                        IOUtils.copy(CareerMove.class.getResourceAsStream("error.png"), os);
                    } catch (Exception ex2) {
                    }
                } finally {
                    os.flush();
                    os.close();
                }
            }
        };
        MediaType m = new MediaType("image", "image/png");
        return Response.ok(stream)
                .type("image/png").build();
    }
    @GET
    @Path("trademarksincrease.png")
    @Produces("image/png")
    public Response tradeMarksIncrease(final @QueryParam("left") String left, final @QueryParam("right") String right, final @QueryParam("industry") String industry, final @QueryParam("occupation") String anzsco) {
        StreamingOutput stream;
        stream = new StreamingOutput() {
            @Override
            public void write(OutputStream os) throws IOException,
                    WebApplicationException {
                try {
                    TradeMarksIncrease.savePNG(left, right, industry, anzsco, os);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    try {
                        IOUtils.copy(CareerMove.class.getResourceAsStream("error.png"), os);
                    } catch (Exception ex2) {
                    }
                } finally {
                    os.flush();
                    os.close();
                }
            }
        };
        MediaType m = new MediaType("image", "image/png");
        return Response.ok(stream)
                .type("image/png").build();
    }
    @GET
    @Path("newbusinessincrease.png")
    @Produces("image/png")
    public Response newBusinessIncrease(final @QueryParam("left") String left, final @QueryParam("right") String right, final @QueryParam("industry") String industry, final @QueryParam("occupation") String anzsco) {
        StreamingOutput stream;
        stream = new StreamingOutput() {
            @Override
            public void write(OutputStream os) throws IOException,
                    WebApplicationException {
                try {
                    NewBusinessIncrease.savePNG(left, right, industry, anzsco, os);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    try {
                        IOUtils.copy(CareerMove.class.getResourceAsStream("error.png"), os);
                    } catch (Exception ex2) {
                    }
                } finally {
                    os.flush();
                    os.close();
                }
            }
        };
        MediaType m = new MediaType("image", "image/png");
        return Response.ok(stream)
                .type("image/png").build();
    }
    @GET
    @Path("sa4unemprate.png")
    @Produces("image/png")
    public Response sa4UnempRate(final @QueryParam("left") String left, final @QueryParam("right") String right, final @QueryParam("industry") String industry, final @QueryParam("occupation") String anzsco) {
        StreamingOutput stream;
        stream = new StreamingOutput() {
            @Override
            public void write(OutputStream os) throws IOException,
                    WebApplicationException {
                try {
                    Sa4UnempRate.savePNG(left, right, industry, anzsco, os);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    try {
                        IOUtils.copy(CareerMove.class.getResourceAsStream("error.png"), os);
                    } catch (Exception ex2) {
                    }
                } finally {
                    os.flush();
                    os.close();
                }
            }
        };
        MediaType m = new MediaType("image", "image/png");
        return Response.ok(stream)
                .type("image/png").build();
    }
    @GET
    @Path("totalbusinesses.png")
    @Produces("image/png")
    public Response totalBusinesses(final @QueryParam("left") String left, final @QueryParam("right") String right, final @QueryParam("industry") String industry, final @QueryParam("occupation") String anzsco) {
        StreamingOutput stream;
        stream = new StreamingOutput() {
            @Override
            public void write(OutputStream os) throws IOException,
                    WebApplicationException {
                try {
                    TotalBusinesses.savePNG(left, right, industry, anzsco, os);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    try {
                        IOUtils.copy(CareerMove.class.getResourceAsStream("error.png"), os);
                    } catch (Exception ex2) {
                    }
                } finally {
                    os.flush();
                    os.close();
                }
            }
        };
        MediaType m = new MediaType("image", "image/png");
        return Response.ok(stream)
                .type("image/png").build();
    }

    @GET
    @Path("top5.json")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RegionData> top5(final @QueryParam("left") String left, final @QueryParam("right") String right, final @QueryParam("industry") String industry, final @QueryParam("occupation") String anzsco) {
        List<RegionData> top5 = CareerMove.top5String(industry, anzsco);
        return top5;
    }
    @GET
    @Path("regiondata.json")
    @Produces(MediaType.APPLICATION_JSON)
    public List<RegionData> getRD(final @QueryParam("left") String left, final @QueryParam("right") String right, final @QueryParam("industry") String industry, final @QueryParam("occupation") String anzsco) {
        List<RegionData> result = new ArrayList<RegionData>();
        result.add(CareerMove.findRegionData(left, industry, anzsco));
        result.add(CareerMove.findRegionData(right, industry, anzsco));
        return result;
    }
}
