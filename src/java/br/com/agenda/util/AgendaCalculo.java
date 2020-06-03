/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.util;

import br.com.agenda.action.vo.VOCompromisso;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

/**
 *
 * @author Felipe
 */
public class AgendaCalculo {

    private static final Locale BRAZIL = new Locale("pt", "BR");
    private static final DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);
    /**
     * Mascara de dinheiro para Real Brasileiro34721
     */
    public static final DecimalFormat DINHEIRO_REAL = new DecimalFormat("¤ ###,###,##0.00", REAL);
    /**
     * Mascara de decinal
     */
    public static final DecimalFormat DECIMAL = new DecimalFormat("¤ ###,###,##0.00", REAL);

    public static String mascaraDinheiro(double valor, DecimalFormat moeda) {
        return moeda.format(valor);
    }
    public static double despesas = 0;
    public static double despesasFixas = 0;
    public static double receitas = 0;
    public static double receitasFixas = 0;
    public static double balancoGeral = 0;
    public static double balancoDespesas = 0;
    public static double balancoReceitas = 0;
    public static double valorParcial = 0;

    public static Double somatorioParcialRelatorio(Double valor){
    valorParcial += valor;
    return valorParcial;
    }

    public static Double somatoriaDespesas(Double valor) {
        despesas += valor;
        return despesas;
    }

    public static Double somatoriaDespesasFixas(Double valor) {
        despesasFixas += valor;
        return despesasFixas;
    }

    public static Double somatoriaReceitas(Double valor) {
        receitas += valor;
        return receitas;
    }

    public static Double somatoriaReceitasFixas(Double valor) {
        receitasFixas += valor;
        return receitasFixas;
    }

    public static Double balancoDespesas() {
        balancoDespesas = despesas + despesasFixas;
        return balancoDespesas;
    }

    public static Double balancoReceitas() {
        balancoReceitas = receitas + receitasFixas;
        return balancoReceitas;
    }

    public static Double balanco() {
        balancoGeral = balancoReceitas - balancoDespesas;
        return balancoGeral;
    }

    public static void zerarValores() {
        despesas = 0;
        despesasFixas = 0;
        receitas = 0;
        receitasFixas = 0;
        balancoDespesas = 0;
        balancoGeral = 0;
        balancoReceitas = 0;
    }

  
}
