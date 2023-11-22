package hlc.ud03.relacion04;

public class TestAcondicionadorApp {

  public static void main(String[] args) {
    int errores = 0;
    
    if (!assertIgual(Acondicionador.acondicionaElementoHtml("Hola Caracola"), "Hola Caracola", "acondicionaElementoHtml")) {
      errores++;
    }
    
    if (!assertIgual(Acondicionador.acondicionaElementoHtml("H<ola> Ca&racola"), "H&lt;ola&gt; Ca&amp;racola", "acondicionaElementoHtml")) {
      errores++;
    }
    
    if (!assertIgual(Acondicionador.acondicionaAtributoHtml("Hola Caracola"), "Hola Caracola", "acondicionaAtributoHtml")) {
      errores++;
    }
    
    if (!assertIgual(Acondicionador.acondicionaAtributoHtml("H<ola> Ca&ra\"co'la"), "H&lt;ola> Ca&amp;ra&#34;co&#39;la", "acondicionaAtributoHtml")) {
      errores++;
    }
    
    if (!assertIgual(Acondicionador.acondicionaContenidoCss("Hola Caracola"), "Hola Caracola", "acondicionaContenidoCss")) {
      errores++;
    }
    
    if (!assertIgual(Acondicionador.acondicionaContenidoCss("\"1 h ' o \\ l < a & C (a )r /1 ola"), "\\22 1 h \\27 o \\5c l \\3c a \\26 C \\28 a \\29r \\2f 1 ola", "acondicionaContenidoCss")) {
      errores++;
    }
    
    if (!assertIgual(Acondicionador.acondicionaUrl("Hola Caracola"), "Hola%20Caracola", "acondicionaUrl")) {
      errores++;
    }
    
    if (!assertIgual(Acondicionador.acondicionaUrl("@\\/ Ω"), "@%5C%2F%20%CE%A9", "acondicionaUrl")) {
      errores++;
    }
    
    if (!assertIgual(Acondicionador.acondicionaLiteralSql("Hola Caracola"), "Hola Caracola", "acondicionaLiteralSql")) {
      errores++;
    }
    
    if (!assertIgual(Acondicionador.acondicionaLiteralSql("H\"'\\%_ ola"), "H\\\"\\'\\\\\\%\\_ ola", "acondicionaLiteralSql")) {
      errores++;
    }
    
    if (errores == 0) {
      System.out.println("No se produjeron errores");
    } else {
      System.err.println("Se produjeron " + errores + " errores");
    }
  }

  private static boolean assertIgual(String cadena1, String cadena2, String metodo) {
    if (!cadena1.equals(cadena2)) {
      System.err.println("Error en " + metodo);
      return false;
    } else {
      return true;
    }
  }

}
