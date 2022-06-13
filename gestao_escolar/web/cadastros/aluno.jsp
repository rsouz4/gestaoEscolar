<%@page import="br.sisacedemico.dao.AlunoDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.sisacademico.model.Curso"%>
<%@page import="br.sisacademico.model.Curso"%>
<%@page import="br.sisacademico.model.Aluno"%>
<%@page import="br.sisacedemico.dao.CursoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    CursoDAO cDAO = new CursoDAO();
    Aluno aluno = new Aluno();
    aluno.setCurso(new Curso());
    aluno.setNome("");
    ArrayList<Curso> cursos = cDAO.getTodosCursos();
    String acao = "cadastro";
    String labelBotao = "Cadastrar";
    
    if(request.getParameter("idAluno") != null) {
        AlunoDAO aDAO = new AlunoDAO();
        int idAluno = Integer.parseInt(request.getParameter("idAluno"));
        aluno = aDAO.getAluno(idAluno);
        acao = "edicao";
        labelBotao = "Salvar";
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="../menu.jsp"></jsp:include>
            <div class="container mt-4">
                <div style="width: 40%; margin: 0 auto !important;">
                    <form method="post" action="../AlunoController">
                        <div class="form-group">
                            <label>RA</label>
                            <input type="number" value="<%=aluno.getRa() == 0 ? "" : aluno.getRa()%>" class="form-control" required name="raAluno" placeholder="Insira o RA">
                        </div>
                        <div class="form-group mt-3">
                            <label>Nome do aluno</label>
                            <input type="text" value="<%=aluno.getNome()%>" class="form-control" required name="nomeAluno" placeholder="Insira o nome do aluno">
                        </div>
                        <div class="form-group mt-3">
                            <label>Curso</label>
                            <select class="form-control" name="idcurso">
                            <% 
                                for (Curso c : cursos) {
                                    String opc = "";
                                    if(aluno.getCurso().getIdCurso() == c.getIdCurso())
                                        opc = "selected";
                            %>
              
                            <option <%=opc%> value="<%=c.getIdCurso()%>"><%=c.getNomeCurso() + " (" + c.getTipoCurso() + ")"%></option>
                            <% }%>
                        </select>
                    </div>
                    <div class="mt-3">
                        <input type="submit" class="btn btn-primary btn-md w-100" value="<%=labelBotao%>">
                    </div>
                    <input type="hidden" name="acao" value="<%=acao%>">
                    <input type="hidden" name="idAluno" value="<%=aluno.getIdAluno()%>">
                </form>
            </div>
        </div>
    </body>

</html>
