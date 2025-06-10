package apresentacao;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Classe principal do sistema com método main.
 * Responsável por inicializar a aplicação.
 * 
 * @author Equipe
 * @version 1.0
 */
public class Main {
    
    /**
     * Método principal que inicia a aplicação.
     * 
     * @param args Argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        // Configurar Look and Feel do sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
        } catch (ClassNotFoundException | InstantiationException | 
                 IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.err.println("Erro ao configurar aparência: " + e.getMessage());
        }
        
        // Executar interface gráfica na thread correta
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    // Criar e exibir a tela principal
                    TelaPrincipal telaPrincipal = new TelaPrincipal();
                    telaPrincipal.setVisible(true);
                } catch (Exception e) {
                    System.err.println("Erro ao inicializar aplicação: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }
}