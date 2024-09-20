package Estruturas;

public class No {
  int valor;
  No direita, esquerda;

  public No(int valor) {
    this.valor = valor;
    esquerda = null;
    direita = null;
  }
}