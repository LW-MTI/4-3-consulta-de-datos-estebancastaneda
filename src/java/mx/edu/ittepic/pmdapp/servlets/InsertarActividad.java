/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.ittepic.pmdapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mx.edu.ittepic.pmdapp.ejbs.EjbPmd;
import mx.edu.ittepic.pmdapp.util.Util;

/**
 *
 * @author Esteban
 */
@WebServlet(name = "InsertarActividad", urlPatterns = {"/InsertarActividad"})
public class InsertarActividad extends HttpServlet {

    @EJB
    private EjbPmd ejb;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InsertarActividad</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InsertarActividad at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Cache-Control", "no-store");
        PrintWriter p = response.getWriter();

        try {
            String actividad = request.getParameter("actividad");
            Date fechaini = Util.strToDate(request.getParameter("fechaini"), "dd-MM-yyyy");
            Date fechafin = Util.strToDate(request.getParameter("fechafin"), "dd-MM-yyyy");
            //int latitud = Integer.parseInt(request.getParameter("latitud"));
            //int longitud = Integer.parseInt(request.getParameter("longitud"));
            Float latitud = Float.parseFloat(request.getParameter("latitud"));
            Float longitud = Float.parseFloat(request.getParameter("longitud"));
            int idcat = Integer.parseInt(request.getParameter("idcat"));
            int idusuario = Integer.parseInt(request.getParameter("idusuario"));
            int iddepe = Integer.parseInt(request.getParameter("iddepe"));
            
            p.write(ejb.insertarActividad(actividad, fechaini, fechafin, latitud, longitud, idcat, idusuario, iddepe));

        } catch (ParseException e) {
            p.write("{msg:'ERROR: Error al obtener la fecha inicial o final.\n" + e.getMessage() + "'}");
        } catch(NumberFormatException e){
            p.write("{msg:'ERROR: ID de la Actividad debe ser numérico.\n" + e.getMessage() + "'}");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
