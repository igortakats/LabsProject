package br.gov.sp.prodesp.cda.gedave.animal.antigenoTuberculina.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.gov.sp.prodesp.cda.gedave.common.util.Util;
import br.gov.sp.prodesp.cda.gedave.pessoa.business.service.GerenciarPessoaFisicaLocal;
import br.gov.sp.prodesp.cda.gedave.sgu.business.service.UsuarioLocal;
import br.gov.sp.prodesp.cda.gedave.sgu.orm.UsuarioORM;
import br.gov.sp.prodesp.exception.business.BusinessException;

/**
 * Servlet implementation class CATSupportServlet
 */
public class CATUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	UsuarioLocal usuarioService;
	
    @EJB
    private GerenciarPessoaFisicaLocal gerenciarPessoaFisicaService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CATUsuarioServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter out = response.getWriter();
		
		String numeroCPF = request.getParameter("cpf");
		String novaSenha = request.getParameter("novaSenha");
		
		if (Util.isEmpty(numeroCPF) || Util.isEmpty(novaSenha)) {
			out.write("CPF ou Nova Senha vazia.");
			return;
		}
		
		try {
			
			UsuarioORM usuario = usuarioService.findByCPF(numeroCPF, false);
			usuario.setSenha(novaSenha);
			out.write("Senha alterada com sucesso para o CPF " + numeroCPF + " / " + usuario.getSenha());
			
		} catch (BusinessException e) {
			e.printStackTrace();
			out.write(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			out.write(e.getMessage());
		} 
		
		out.write(this.getServletContext().getRealPath(""));
		
	}

}
