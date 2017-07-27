package intelipost.login.bean;

import java.io.Serializable;

public class Perfil implements Serializable{

	private String id;
	private String senha;
	private String nome;
	private String sexo;
	private int idade;
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public String getSexo() {
        return this.sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getIdade() {
        return this.idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

	@Override
	public String toString() {
		return "Perfil [id=" + id + ", nome=" + nome + ", sexo=" + sexo + ", idade=" + idade + "]";
	}
	
}
