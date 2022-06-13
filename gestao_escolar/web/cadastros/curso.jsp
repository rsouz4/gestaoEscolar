<%@page import="br.sisacademico.model.Curso"%>
<%@page import="br.sisacedemico.dao.CursoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    
    String acao = "cadastro";
    String txtBotao = "Cadastrar";
    Curso c = new Curso();
    c.setIdCurso(0);
    c.setNomeCurso("");
    c.setTipoCurso("");
    
    if(request.getParameter("idCurso") != null) {
        acao = "edicao";
        txtBotao = "Alterar";
        CursoDAO cDAO = new CursoDAO();
        c = cDAO.getCurso(
                Integer.parseInt(request.getParameter("idCurso")));
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <jsp:include page="../menu.jsp"></jsp:include>
        <div class="container mt-4 pt-4">
            <div style="width: 30%; margin: 0 auto !important;">
                <form method="post" action="../CursoController">
                    <div class="form-group">
                        <label>Nome do curso:</label>
                        <input type="text" class="form-control" required value="<%=c.getNomeCurso()%>" name="nomeCurso">
                    </div>
                    <div class="form-group mt-4">
                        <label>Tupo de curso:</label>
                        <input type="text" class="form-control" required value="<%=c.getTipoCurso()%>" name="tipoCurso">
                    </div>
                    <div class="mt-4">
                        <input type="submit" class="btn btn-primary btn-md w-100" value="<%=txtBotao%>">
                    </div>
                    <input type="hidden" name="acao" value="<%=acao%>">
                    <input type="hidden" name="idCurso" value="<%=c.getIdCurso()%>">
                </form>
            </div>
        </div>
    </body>
</html>
