package Estruturas;

public class Arvore {
  No raiz;

  public Arvore() {
    raiz = null;
  }

  public void Inserir(int valor) {
    raiz = InserirNovo(valor, raiz);
  }

  // #region Inserção
  private No InserirNovo(int valor, No raiz) {
    if (raiz == null) {
      raiz = new No(valor);
      return raiz;
    }

    if (valor < raiz.valor)
      raiz.esquerda = InserirNovo(valor, raiz.esquerda);
    else if (valor > raiz.valor)
      raiz.direita = InserirNovo(valor, raiz.direita);

    return raiz;
  }
  // #endregion

  // #region Remoção
  public boolean remover(int novoValor) {
    if (raiz == null)
      return false;
    else {
      No pai;
      No noX;
      if (raiz.valor == novoValor) {
        pai = raiz;
        noX = raiz;
      } else {

        pai = encontrarElemento(raiz, novoValor);
        if (pai.valor < novoValor)
          noX = pai.direita;
        else
          noX = pai.esquerda;
      }

      if (noX.direita == null && noX.esquerda == null) {
        if (pai.valor < novoValor)
          pai.direita = null;
        else
          pai.esquerda = null;

      } else {
        if (noX.direita != null && noX.esquerda != null) {
          No noPaiDireitaEsquerda = maisEsquerdaPossivel(noX, noX.direita);
          No substituto = noPaiDireitaEsquerda.esquerda;
          noPaiDireitaEsquerda.esquerda = null;
          substituto.direita = noX.direita;
          substituto.esquerda = noX.esquerda;
          noX.esquerda = null;
          noX.direita = null;

        } else {
          if (noX.direita == null) {
            if (pai.valor > novoValor)
              pai.direita = noX.esquerda;
            else
              pai.esquerda = noX.esquerda;
          }
          if (noX.esquerda == null) {
            if (pai.valor > novoValor)
              pai.direita = noX.direita;
            else
              pai.esquerda = noX.direita;
          }

        }
      }
      return true;
    }
  }

  No encontrarElemento(No atual, int valor) {
    if (atual == null)
      return null;

    if (valor == atual.valor)
      return atual;

    if (valor > atual.valor) {

      if (atual.direita != null && atual.direita.valor == valor)
        return atual;
      return encontrarElemento(atual.direita, valor);
    } else {
      if (atual.esquerda != null && atual.esquerda.valor == valor)
        return atual;
      return encontrarElemento(atual.esquerda, valor);
    }
  }

  private No maisEsquerdaPossivel(No pai, No filhoAtual) {
    if (filhoAtual.esquerda == null)
      return pai;
    return maisEsquerdaPossivel(filhoAtual, filhoAtual.esquerda);
  }
  // #endregion

  // #region Exibição
  public void PreOrdem() {
    PreOrdem(raiz);
    System.out.println();
  }

  private void PreOrdem(No elemento) {
    if (elemento != null) {
      System.out.print(elemento.valor + " ");
      PreOrdem(elemento.esquerda);
      PreOrdem(elemento.direita);
    }
  }

  public void PosOrdem() {
    PosOrdem(raiz);
    System.out.println();
  }

  private void PosOrdem(No elemento) {
    if (elemento != null) {
      PosOrdem(elemento.esquerda);
      PosOrdem(elemento.direita);
      System.out.print(elemento.valor + " ");
    }
  }

  public void EmOrdem() {
    EmOrdem(raiz);
    System.out.println();
  }

  private void EmOrdem(No elemento) {
    if (elemento != null) {

      EmOrdem(elemento.esquerda);
      System.out.print(elemento.valor + " ");
      System.out.println("");
      EmOrdem(elemento.direita);
    }
  }

  public void ContaNosTotais() {
    System.out.println("Quantidade ne nós totais: " + ContaNosTotais(raiz));
  }

  private int ContaNosTotais(No elemento) {
    if (elemento == null)
      return 0;

    return 1 + ContaNosTotais(elemento.direita) + ContaNosTotais(elemento.esquerda);
  }

  public void ContaNosNaoFolhas() {
    System.err.println("Quantidade de nós não folhas:" + ContaNosNaoFolhas(raiz));
  }

  private int ContaNosNaoFolhas(No elemento) {
    if (elemento == null)
      return 0;

    if (elemento.direita != null || elemento.esquerda != null)
      return 1 + ContaNosNaoFolhas(elemento.direita) + ContaNosNaoFolhas(elemento.esquerda);

    return 0;
  }
  // #endregio

}