package Estruturas;

import javax.swing.text.DefaultStyledDocument.ElementSpec;

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
    if (raiz == null) {
      return false;
    }

    No pai = null;
    No noX = raiz;

    while (noX != null && noX.valor != novoValor) {
      pai = noX;
      if (novoValor < noX.valor) {
        noX = noX.esquerda;
      } else {
        noX = noX.direita;
      }
    }

    if (noX == null) {
      return false;
    }

    if (noX.esquerda == null && noX.direita == null) {
      if (pai == null) {
        raiz = null;
      } else if (pai.esquerda == noX) {
        pai.esquerda = null;
      } else {
        pai.direita = null;
      }
    }

    else if (noX.esquerda == null || noX.direita == null) {
      No filho = (noX.esquerda != null) ? noX.esquerda : noX.direita;

      if (pai == null) {
        raiz = filho;
      } else if (pai.esquerda == noX) {
        pai.esquerda = filho;
      } else {
        pai.direita = filho;
      }
    }

    else {
      No paiSucessor = noX;
      No sucessor = noX.direita;

      while (sucessor.esquerda != null) {
        paiSucessor = sucessor;
        sucessor = sucessor.esquerda;
      }

      noX.valor = sucessor.valor;

      if (paiSucessor.esquerda == sucessor) {
        paiSucessor.esquerda = sucessor.direita;
      } else {
        paiSucessor.direita = sucessor.direita;
      }
    }

    return true;
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

  public void ContaNosFolhas() {
    System.err.println("Quantidade de nós folhas: " + ContaNosFolhas(raiz));
  }

  private int ContaNosFolhas(No elemento) { // Tive que me segurar aqui pra não meter um ContaNosTotais -
                                            // ContaNosNaoFolhas
    if (elemento == null)
      return 0;

    if (elemento.direita == null && elemento.esquerda == null)
      return 1 + ContaNosFolhas(elemento.direita) + ContaNosFolhas(elemento.esquerda);
    else
      return 0 + ContaNosFolhas(elemento.direita) + ContaNosFolhas(elemento.esquerda);
  }

  public void CalculaAltura() {
    System.err.println("A altura da árvore é:" + CalculaAltura(raiz));
  }

  private int CalculaAltura(No elemento) {
    int tamanhoDireita = 0;
    int tamanhoEsquerda = 0;

    if (elemento == null)
      return 0;

    if (elemento.direita != null)
      tamanhoDireita = 1 + CalculaAltura(elemento.direita);
    if (elemento.esquerda != null)
      tamanhoEsquerda = 1 + CalculaAltura(elemento.esquerda);

    return tamanhoDireita > tamanhoEsquerda ? tamanhoDireita : tamanhoEsquerda;
  }

  public void RemoveElementosPares() {
    RemoveElementosPares(raiz);
    System.err.println("Questão 5 ainda em desenvolvimento");
  }

  private No RemoveElementosPares(No elemento) {
    return new No(0);
  }

  public void EspelhaArvore() {
    System.out.println("A árvore espelhada ficou assim:");
    EspelhaArvore(raiz);
  }

  private void EspelhaArvore(No elemento) {
    if (elemento == null) {
      return;
    }

    No aux = new No(0);
    aux = elemento.direita;
    elemento.direita = elemento.esquerda;
    elemento.esquerda = aux;

    EspelhaArvore(elemento.direita);
    EspelhaArvore(elemento.esquerda);
  }

  public void PreOrdemNaoRecursiva() {

  }

  // #endregio

}