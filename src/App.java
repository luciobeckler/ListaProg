import Estruturas.Arvore;

public class App {
    public static void main(String[] args) throws Exception {
        Arvore arvore = new Arvore();

        arvore.Inserir(0);
        arvore.Inserir(4);
        arvore.Inserir(-5);

        arvore.EmOrdem();

    }
}
