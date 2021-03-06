package br.sisacedemico.dao;

import br.sisacademico.model.Aluno;
import br.sisacademico.model.Curso;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AlunoDAO {

    private static Statement stm = null;

    public ArrayList<Aluno> getAlunos(Integer... idCurso) throws SQLException {
        ArrayList<Aluno> alunos = new ArrayList<>();

        String query = "SELECT"
                + "    aluno.ID_ALUNO,"
                + "    aluno.RA,"
                + "    aluno.NOME,"
                + "    curso.ID_CURSO,"
                + "    curso.NOME_CURSO,"
                + "    curso.TIPO_CURSO"
                + " FROM"
                + "    TB_ALUNO AS aluno"
                + "    INNER JOIN TB_CURSO AS curso"
                + "        ON aluno.ID_CURSO = curso.ID_CURSO";

        if (idCurso.length != 0) {
            query += " WHERE curso.ID_CURSO = " + idCurso[0];
        }

        query += " ORDER BY aluno.NOME";

        stm = ConnectionFactory.getConnection().createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);

        ResultSet resultados = stm.executeQuery(query);
        
        while(resultados.next()) {
            Aluno a = new Aluno();
            Curso c = new Curso();
            
            a.setIdAluno(resultados.getInt("ID_ALUNO"));
            a.setNome(resultados.getString("NOME"));
            a.setRa(resultados.getInt("RA"));
            
            c.setIdCurso(resultados.getInt("ID_CURSO"));
            c.setNomeCurso(resultados.getString("NOME_CURSO"));
            c.setTipoCurso(resultados.getString("TIPO_CURSO"));
            
            a.setCurso(c);
            
            alunos.add(a);
        }
        
        stm.getConnection().close();
        
        return alunos;
    }
    
    public boolean deleteAluno(int idAluno) {
        try {
            String query = "DELETE FROM TB_ALUNO WHERE ID_ALUNO = ?";
            PreparedStatement stm = ConnectionFactory.getConnection().
                    prepareStatement(query);
            stm.setInt(1, idAluno);
            stm.execute();
            stm.getConnection().close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    public Aluno getAluno(int idAluno) throws SQLException{
        String query = "SELECT"
                + "    aluno.ID_ALUNO,"
                + "    aluno.RA,"
                + "    aluno.NOME,"
                + "    curso.ID_CURSO,"
                + "    curso.NOME_CURSO,"
                + "    curso.TIPO_CURSO"
                + " FROM"
                + "    TB_ALUNO AS aluno"
                + "    INNER JOIN TB_CURSO AS curso"
                + "        ON aluno.ID_CURSO = curso.ID_CURSO"
                + " WHERE aluno.ID_ALUNO = " + idAluno;

        stm = ConnectionFactory.getConnection().createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);

        ResultSet resultados = stm.executeQuery(query);
        
        Aluno a = new Aluno();
        
        while(resultados.next()) {
            
            Curso c = new Curso();
            
            a.setIdAluno(resultados.getInt("ID_ALUNO"));
            a.setNome(resultados.getString("NOME"));
            a.setRa(resultados.getInt("RA"));
            
            c.setIdCurso(resultados.getInt("ID_CURSO"));
            c.setNomeCurso(resultados.getString("NOME_CURSO"));
            c.setTipoCurso(resultados.getString("TIPO_CURSO"));
            
            a.setCurso(c);
            
        }
        
        stm.getConnection().close();
        
        return a;
    }
}
