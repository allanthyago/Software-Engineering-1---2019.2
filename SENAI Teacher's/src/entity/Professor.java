package entity;

public class Professor {
	private int codigo;
	private String nome;
	private String email;
	private String TelResidencial;
	private String TelCel1;
	private String TelCel2;
	private String FormaPrinc;
	private String AtuaPrinc;
	private String FormaSec;
	private String AtuaSec;
	private String pesquisa;
	private byte[] imagem;
	private byte[] curriculo;
	private String nomeArquivo;
	private Usuario usuario;
	private String palavra;
	
	public String getPalavra() {
		return palavra;
	}
	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelResidencial() {
		return TelResidencial;
	}
	public void setTelResidencial(String telResidencial) {
		TelResidencial = telResidencial;
	}
	public String getTelCel1() {
		return TelCel1;
	}
	public void setTelCel1(String telCel1) {
		TelCel1 = telCel1;
	}
	public String getTelCel2() {
		return TelCel2;
	}
	public void setTelCel2(String telCel2) {
		TelCel2 = telCel2;
	}
	public String getFormaPrinc() {
		return FormaPrinc;
	}
	public void setFormaPrinc(String formaPrinc) {
		FormaPrinc = formaPrinc;
	}
	public String getAtuaPrinc() {
		return AtuaPrinc;
	}
	public void setAtuaPrinc(String atuaPrinc) {
		AtuaPrinc = atuaPrinc;
	}
	public String getFormaSec() {
		return FormaSec;
	}
	public void setFormaSec(String formaSec) {
		FormaSec = formaSec;
	}
	public String getAtuaSec() {
		return AtuaSec;
	}
	public void setAtuaSec(String atuaSec) {
		AtuaSec = atuaSec;
	}

	public byte[] getImagem() {
		return imagem;
	}
	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
	public byte[] getCurriculo() {
		return curriculo;
	}
	public void setCurriculo(byte[] curriculo) {
		this.curriculo = curriculo;
	}
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	public String toString() {
		return nome;
	}

	public String getPesquisa() {
		return pesquisa;
	}
	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}
}
