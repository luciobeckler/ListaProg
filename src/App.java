import Estruturas.Arvore;

public class App {
    public static void main(String[] args) throws Exception {
        Arvore arvore = new Arvore();

        arvore.Inserir(20);
        arvore.Inserir(15);
        arvore.Inserir(25);
        arvore.Inserir(17);
        arvore.Inserir(24);
        arvore.Inserir(30);
        arvore.Inserir(10);
        arvore.Inserir(14);
        arvore.Inserir(13);

        arvore.EmOrdem();

        arvore.ContaNosTotais(); // ! Questão 1
        arvore.ContaNosNaoFolhas(); // ! Questão 2

    }
}
