package br.com.furb.copilador.actionCopilador;

import br.com.furb.copilador.tela.TelaPrincipal;
import java.io.FileInputStream;
import java.io.FileWriter;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.DataFlavor;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Action implements ActionCopilador {

    private TelaPrincipal telaPrincipal;
    private static String dsModification = "Modificado";
    private static String dsNoModification = "Não modificado";
    private Clipboard board;
    private ClipboardOwner selection;

    public Action(TelaPrincipal telaPrincipal) {
        this.telaPrincipal = telaPrincipal;
    }

    @Override
    public void limparTela() {
        telaPrincipal.getEditorJTP().setText("");
        telaPrincipal.getAreaMensagemJTA().setText("");
        telaPrincipal.getDsModificacaoJL().setText("");
        telaPrincipal.getDsDiretorioJL().setText("");
        telaPrincipal.getDsNomeArquivoJL().setText("");
    }

    @Override
    public void atualizarBarraStatus(String dsModficacao) {
        telaPrincipal.getDsModificacaoJL().setText(dsModficacao);
        if (telaPrincipal.getArquivoAtual() != null) {
            telaPrincipal.getDsNomeArquivoJL().setText("           Arquivo: " + telaPrincipal.getArquivoAtual().getName());
            telaPrincipal.getDsDiretorioJL().setText("             Diretório: " + telaPrincipal.getArquivoAtual().getParent());
        } else {
            telaPrincipal.getDsDiretorioJL().setText("");
            telaPrincipal.getDsNomeArquivoJL().setText("");
        }
    }

    @Override
    public void doClickNovo() {
        limparTela();
        telaPrincipal.setArquivoAtual(null);
        atualizarBarraStatus(dsNoModification);
    }

    @Override
    public void doClickAbrir() {
        try {
            if (telaPrincipal.getArquivoAtual() != null) {
                limparTela();
                StringBuilder strContent = new StringBuilder("");
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(telaPrincipal.getArquivoAtual()), "ISO-8859-1"));
                String linha;
                for (linha = br.readLine(); linha != null; linha = br.readLine()) {
                    strContent.append(linha).append("\n");
                }
                br.close();

                telaPrincipal.getEditorJTP().setText(strContent.toString());
                atualizarBarraStatus(dsNoModification);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(telaPrincipal, e.getMessage());
        }
    }

    @Override
    public void doClickSalvar() {
        try {
            if (telaPrincipal.getArquivoAtual() != null) {
                FileWriter writter = new FileWriter(telaPrincipal.getArquivoAtual());
                writter.write(telaPrincipal.getEditorJTP().getText());
                writter.close();
                telaPrincipal.getAreaMensagemJTA().setText("");
                atualizarBarraStatus(dsNoModification);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(telaPrincipal, e.getMessage());
        }
    }

    private void sendToClipbord(String text) {
        board = Toolkit.getDefaultToolkit().getSystemClipboard();
        selection = new StringSelection(text);
        board.setContents((Transferable) selection, selection);
    }

    @Override
    public void doClickCopiar() {
        String copia = telaPrincipal.getEditorSelectedText();
        if (copia != null) {
            sendToClipbord(copia);
        }
    }

    @Override
    public void doClickColar() {
        try {
            String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
            telaPrincipal.getEditorJTP().getDocument().insertString(telaPrincipal.getEditorJTP().getCaretPosition(), data, null);
        } catch (Exception e) {
            Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void doClickRecortar() {
        String recorte = telaPrincipal.getEditorSelectedText();
        sendToClipbord(recorte);
        telaPrincipal.eraseEditorSelectedText();
    }

    @Override
    public void doClickCompilar() {
        telaPrincipal.getAreaMensagemJTA().setText("compilação de programas não foi implementada");
    }

    @Override
    public void doClickGerarCodigo() {
        telaPrincipal.getAreaMensagemJTA().setText("geração de código não foi implementada");
    }

    @Override
    public void doClickEquipe() {
        telaPrincipal.getAreaMensagemJTA().setText("Desenvolvedores:\nAlex August Schlote\nAndré Alex Sestari\nGabriel Felipe Borges de Campos");
    }

    @Override
    public void inicializaPrograma() {
        atualizarBarraStatus(dsNoModification);
    }

    public static String getDsModification() {
        return dsModification;
    }

    public static String getDsNoModification() {
        return dsNoModification;
    }
}