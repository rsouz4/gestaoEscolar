package br.sisacademico.controllers;

import br.sisacademico.model.Curso;
import br.sisacademico.util.AcaoDao;
import br.sisacedemico.dao.CursoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CursoController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            AcaoDao act = AcaoDao.valueOf(request.getParameter("acao"));
            CursoDAO cDAO = new CursoDAO();
            switch (act) {
                case leitura:
                    Map<Curso, Integer> cursos = cDAO.getTodosCursosCountAlunos();

                    //forma 1: Colocando o map na sessão:
                    HttpSession session = request.getSession();
                    session.setAttribute("listaDeCursos", cursos);
                    response.sendRedirect("./relatorios/curso.jsp");

                    //forma 2: Enviando o mapa via parâmetro do request:
                    //request.setAttribute("listaDeCursos", cursos);
                    //request.getRequestDispatcher("/relatorios.curso.jsp").forward(request, response);                    
                    break;
                case cadastro:
                    Curso curso = new Curso();
                    curso.setNomeCurso(request.getParameter("nomeCurso"));
                    curso.setTipoCurso(request.getParameter("tipoCurso"));
                    if (cDAO.insereCurso(curso)) {
                        response.sendRedirect("./relatorios/loader.jsp?pagina=curso");
                    }
                    break;
                case edicao:
                    int idCursoEditado = Integer.parseInt(request.getParameter("idCurso"));
                    String nomeNovo = request.getParameter("nomeCurso");
                    String tipoNovo = request.getParameter("tipoCurso");
                    if(cDAO.atualizaCurso(idCursoEditado, nomeNovo, tipoNovo)) {
                        response.sendRedirect("./relatorios/loader.jsp?pagina=curso");
                    }
                    break;
                case exclusao:
                    int idCurso = Integer.parseInt(request.getParameter("idCurso"));
                    if (cDAO.deleteCurso(idCurso)) {
                        response.sendRedirect("./relatorios/loader.jsp?pagina=curso");
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
            Logger.getLogger(CursoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CursoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
