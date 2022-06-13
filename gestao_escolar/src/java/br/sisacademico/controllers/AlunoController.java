package br.sisacademico.controllers;

import br.sisacademico.model.Aluno;
import br.sisacademico.util.AcaoDao;
import br.sisacedemico.dao.AlunoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AlunoController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        AcaoDao act = AcaoDao.valueOf(request.getParameter("acao"));
        AlunoDAO aDAO = new AlunoDAO();
        try (PrintWriter out = response.getWriter()) {
            switch (act) {
                case leitura:
                    ArrayList<Aluno> alunos;
                    String url = "./relatorios/aluno.jsp";
                    if (request.getParameter("idCurso") == null) {
                        alunos = aDAO.getAlunos();
                    } else {
                        alunos = aDAO.getAlunos(
                                Integer.parseInt(request.getParameter("idCurso")));
                        
                        url += "?idCurso=" + request.getParameter("idCurso");
                    }
                    
                    HttpSession session = request.getSession();
                    session.setAttribute("listaDeAlunos", alunos);
                    
                    response.sendRedirect(url);
                    break;
                case cadastro:
                    out.print("Precisamos implementar...");
                    break;
                case edicao:
                    out.print("Precisamos implementar...");
                    break;
                case exclusao:
                    int idAluno = Integer.parseInt(request.getParameter("idAluno"));
                    if(aDAO.deleteAluno(idAluno)) {
                        if(request.getParameter("idCurso") == null) {
                            response.sendRedirect("./relatorios/loader.jsp?pagina=aluno");
                        } else {
                            int idCurso = Integer.parseInt(request.getParameter("idCurso"));
                            response.sendRedirect("./relatorios/loader.jsp?idCurso=" + idCurso + "&pagina=aluno");
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AlunoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
