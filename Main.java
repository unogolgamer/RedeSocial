import java.util.*;

class Pessoa {
    private String nome;
    private int idade;
    private String localizacao;
    private int id;
    private List<Integer> amigos;

    public Pessoa(int id, String nome, int idade, String localizacao) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.localizacao = localizacao;
        this.amigos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void adicionarAmigo(int amigoId) {
        amigos.add(amigoId);
    }

    public void removerAmigo(int amigoId) {
        amigos.remove(Integer.valueOf(amigoId));
    }

    public List<Integer> getAmigos() {
        return amigos;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nome: " + nome + ", Idade: " + idade + ", Localizacao: " + localizacao;
    }
}

class RedeSocial {
    private Map<Integer, Pessoa> pessoas;

    public RedeSocial() {
        this.pessoas = new HashMap<>();
    }

    public void adicionarPessoa(int id, String nome, int idade, String localizacao) {
        Pessoa pessoa = new Pessoa(id, nome, idade, localizacao);
        pessoas.put(id, pessoa);
    }

    public void adicionarAmizade(int pessoaId1, int pessoaId2) {
        Pessoa pessoa1 = pessoas.get(pessoaId1);
        Pessoa pessoa2 = pessoas.get(pessoaId2);

        if (pessoa1 != null && pessoa2 != null) {
            pessoa1.adicionarAmigo(pessoaId2);
            pessoa2.adicionarAmigo(pessoaId1);
        }
    }

    public void mostrarPessoas() {
        for (Pessoa pessoa : pessoas.values()) {
            System.out.println(pessoa);
        }
    }

    public void mostrarRedeDeAmizades(int pessoaId) {
        Pessoa pessoa = pessoas.get(pessoaId);

        if (pessoa != null) {
            System.out.println("Rede de amizades para " + pessoa.getNome() + ":");
            for (int amigoId : pessoa.getAmigos()) {
                Pessoa amigo = pessoas.get(amigoId);
                System.out.println(amigo);
            }
        }
    }

    public void removerPessoa(int pessoaId) {
        Pessoa pessoa = pessoas.get(pessoaId);

        if (pessoa != null) {
            for (Pessoa amigo : pessoas.values()) {
                amigo.removerAmigo(pessoaId);
            }

            pessoas.remove(pessoaId);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        RedeSocial redeSocial = new RedeSocial();

        redeSocial.adicionarPessoa(1, "Alice", 25, "Cidade A");
        redeSocial.adicionarPessoa(2, "Bob", 30, "Cidade B");
        redeSocial.adicionarPessoa(3, "Charlie", 22, "Cidade C");

        redeSocial.adicionarAmizade(1, 2);
        redeSocial.adicionarAmizade(1, 3);

        redeSocial.mostrarPessoas();
        System.out.println();

        redeSocial.mostrarRedeDeAmizades(1);

        // Para remover uma pessoa
        // redeSocial.removerPessoa(2);
    }
}
